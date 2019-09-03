package com.snowstep115.example.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.snowstep115.example.exposer.EPlayerEntity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public final class RestoreCommand implements Command<ServerCommandSource> {
    public static final RestoreCommand INSTANCE = new RestoreCommand();

    public void register(CommandDispatcher<ServerCommandSource> disp) {
        disp.register(CommandManager.literal("restore").executes(this));
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity mp = context.getSource().getPlayer();
        EPlayerEntity emp = (EPlayerEntity) (Object) mp;
        CompoundTag pdata = emp.getPersistentData();
        CompoundTag tag = pdata.getCompound("com.snowstep115.examplemod.trash");
        ItemStack itemstack = ItemStack.fromTag(tag);
        PlayerInventory inv = mp.inventory;
        inv.insertStack(itemstack);
        inv.markDirty();
        return 0;
    }
}
