package com.snowstep115.example.block;

import com.snowstep115.example.ExampleMod;
import com.snowstep115.example.block.entity.ExampleBlockEntity;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public final class ExampleBlock extends Block implements BlockEntityProvider {
    public static final ExampleBlock INSTANCE = new ExampleBlock();

    public ExampleBlock() {
        super(FabricBlockSettings.of(Material.PORTAL).build());
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        boolean result = super.activate(state, world, pos, player, hand, hit);
        ContainerProviderRegistry.INSTANCE.openContainer(new Identifier(ExampleMod.MODID, "example"), player, buf -> {
        });
        return result;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new ExampleBlockEntity();
    }
}
