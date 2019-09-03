package com.snowstep115.example.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.snowstep115.example.ExampleMod;
import com.snowstep115.example.exposer.EItemStack;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;

public final class ExampleCommand implements Command<ServerCommandSource> {
    public static final ExampleCommand INSTANCE = new ExampleCommand();

    public void register(CommandDispatcher<ServerCommandSource> disp) {
        disp.register(CommandManager.literal("example").executes(this));
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity mp = context.getSource().getPlayer();
        ItemStack itemstack = new ItemStack(Items.GOLDEN_APPLE);
        EItemStack example = (EItemStack) (Object) itemstack;
        CompoundTag tag = example.serializeNBT();
        mp.sendChatMessage(new LiteralText(tag.toString()), MessageType.CHAT);
        mp.sendChatMessage(new LiteralText(ExampleMod.INSTANCE.server.getLevelName()), MessageType.CHAT);
        return 0;
    }
}
