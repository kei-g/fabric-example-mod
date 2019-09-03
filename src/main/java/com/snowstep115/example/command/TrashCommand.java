package com.snowstep115.example.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.snowstep115.example.exposer.EPlayerEntity;
import com.snowstep115.example.exposer.EItemStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public final class TrashCommand implements Command<ServerCommandSource> {
    public static final TrashCommand INSTANCE = new TrashCommand();

    public void register(CommandDispatcher<ServerCommandSource> disp) {
        disp.register(CommandManager.literal("trash").executes(this));
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity mp = context.getSource().getPlayer();
        EPlayerEntity emp = (EPlayerEntity) (Object) mp;
        CompoundTag pdata = emp.getPersistentData();
        ItemStack itemstack = mp.getMainHandStack();
        EItemStack eitemstack = (EItemStack) (Object) itemstack;
        CompoundTag tag = eitemstack.serializeNBT();
        pdata.put("com.snowstep115.examplemod.trash", tag);
        PlayerInventory inv = mp.inventory;
        inv.removeInvStack(inv.selectedSlot);
        return 0;
    }
}
