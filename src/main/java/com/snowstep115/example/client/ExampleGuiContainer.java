package com.snowstep115.example.client;

import com.snowstep115.example.ExampleMod;
import com.snowstep115.example.container.ExampleContainer;

import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class ExampleGuiContainer extends AbstractContainerScreen<ExampleContainer> {
    public static final Identifier NAME = new Identifier(ExampleMod.MODID, "example");

    public ExampleGuiContainer(ExampleContainer container, PlayerInventory inv, Text title) {
        super(container, inv, title);
    }

	@Override
    protected void drawBackground(float partialTick, int mouseX, int mouseY) {
    }
}
