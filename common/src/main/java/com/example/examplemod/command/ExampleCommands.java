package com.example.examplemod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class ExampleCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher,
                                CommandBuildContext buildContext) {

        dispatcher.register(
            Commands.literal("examplemod")

                // /examplemod greet <player>
                .then(Commands.literal("greet")
                    .then(Commands.argument("player", EntityArgument.player())
                        .executes(ctx -> greet(ctx,
                            EntityArgument.getPlayer(ctx, "player")))))

                // /examplemod heal (no amount so defaults to full health)
                .then(Commands.literal("heal")
                    .requires(src -> src.hasPermission(2))
                    .executes(ctx -> heal(ctx, -1))
                    // /examplemod heal <amount>
                    .then(Commands.argument("amount", IntegerArgumentType.integer(1, 100))
                        .executes(ctx -> heal(ctx,
                            IntegerArgumentType.getInteger(ctx, "amount")))))
        );
    }

    private static int greet(CommandContext<CommandSourceStack> ctx,
                             ServerPlayer target) {
        target.sendSystemMessage(
            Component.literal("Hello from ")
                .append(ctx.getSource().getDisplayName())
                .append("!"));

        ctx.getSource().sendSuccess(
            () -> Component.literal("Greeted " + target.getName().getString()),
            false // false = do not broadcast to admins in the console
        );
        return Command.SINGLE_SUCCESS; // 1 = success
    }

    private static int heal(CommandContext<CommandSourceStack> ctx,
                             int amount) throws CommandSyntaxException {
        ServerPlayer player = ctx.getSource().getPlayerOrException();

        float healAmount = (amount == -1)
            ? player.getMaxHealth() - player.getHealth()
            : amount;

        if (healAmount <= 0) {
            throw new SimpleCommandExceptionType(
                Component.literal("You are already at full health.")
            ).create();
        }

        player.heal(healAmount);
        ctx.getSource().sendSuccess(
            () -> Component.literal("Healed " + String.format("%.1f", healAmount) + " HP"),
            true // true = broadcast to admins so they know a heal was performed
        );
        return Command.SINGLE_SUCCESS;
    }
}