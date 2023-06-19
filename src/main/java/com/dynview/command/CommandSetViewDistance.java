package com.dynview.command;

import com.dynview.ViewDistHandler.ServerDynamicViewDistanceManager;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;

public class CommandSetViewDistance implements IMCOPCommand
{

    private static final String RANGE_ARG = "chunkdistance";

    @Override
    public int onExecute(final CommandContext<CommandSourceStack> context)
    {
        context.getSource().sendFailure(Component.literal("You have to enter a value for the chunk view distance [0-32]"));
        return 0;
    }

    private int executeWithPage(final CommandContext<CommandSourceStack> context)
    {
        if (!checkPreCondition(context))
        {
            return 0;
        }

        ServerDynamicViewDistanceManager.getInstance().setCurrentChunkViewDist(IntegerArgumentType.getInteger(context, RANGE_ARG));
        context.getSource().sendSystemMessage(Component.literal("Set view distance to:" + IntegerArgumentType.getInteger(context, RANGE_ARG)));
        return 1;
    }

    @Override
    public String getName()
    {
        return "setviewdistance";
    }

    @Override
    public LiteralArgumentBuilder<CommandSourceStack> build()
    {
        return IMCCommand.newLiteral(getName())
          .then(IMCCommand.newArgument(RANGE_ARG, IntegerArgumentType.integer(1)).executes(this::executeWithPage)).executes(this::checkPreConditionAndExecute);
    }
}
