package com.snowstep115.example.client;

import com.snowstep115.example.block.entity.ExampleBlockEntity;
import com.snowstep115.example.container.ExampleContainer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;

public final class ExampleModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(ExampleGuiContainer.NAME, (syncId, id, player, buf) -> {
            BlockPos bp = buf.readBlockPos();
            BlockEntity be = player.world.getBlockEntity(bp);
            if (be instanceof ExampleBlockEntity) {
                ExampleContainer container = new ExampleContainer(syncId, player.inventory, (ExampleBlockEntity) be);
                return new ExampleGuiContainer(container, player.inventory, new TranslatableText("examplemod.example"));
            }
            return null;
        });
    }
}
