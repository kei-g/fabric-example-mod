package com.snowstep115.example.container;

import com.snowstep115.example.ExampleMod;
import com.snowstep115.example.block.entity.ExampleBlockEntity;

import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.Identifier;

public final class ExampleContainer extends Container {
    public static final Identifier NAME = new Identifier(ExampleMod.MODID, "example");

    public ExampleContainer(int id, PlayerInventory inv, ExampleBlockEntity be) {
        super(null, id);
    }

	@Override
    public boolean canUse(PlayerEntity player) {
        return true;
	}
}
