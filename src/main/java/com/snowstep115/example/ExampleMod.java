package com.snowstep115.example;

import com.snowstep115.example.block.ExampleBlock;
import com.snowstep115.example.block.entity.ExampleBlockEntity;
import com.snowstep115.example.command.ExampleCommand;
import com.snowstep115.example.command.RestoreCommand;
import com.snowstep115.example.command.TrashCommand;
import com.snowstep115.example.container.ExampleContainer;
import com.snowstep115.example.item.ExampleBlockItem;
import com.snowstep115.example.item.ExampleItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {
	private static final Logger LOGGER = LogManager.getLogger(ExampleMod.MODID);
	public static final String MODID = "examplemod";

	public static BlockEntityType<ExampleBlockEntity> EXAMPLE;
	public static ExampleMod INSTANCE;

	public static void info(String format, Object... args) {
		LOGGER.info(String.format(format, args));
	}

	public MinecraftServer server;

	public ExampleMod() {
		ExampleMod.INSTANCE = this;
	}

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier(ExampleMod.MODID, "example"), ExampleBlock.INSTANCE);
		EXAMPLE = Registry.register(Registry.BLOCK_ENTITY, new Identifier(ExampleMod.MODID, "example"),
				BlockEntityType.Builder.create(ExampleBlockEntity::new, ExampleBlock.INSTANCE).build(null));
		CommandRegistry.INSTANCE.register(false, disp -> {
			ExampleCommand.INSTANCE.register(disp);
			RestoreCommand.INSTANCE.register(disp);
			TrashCommand.INSTANCE.register(disp);
		});
		ContainerProviderRegistry.INSTANCE.registerFactory(ExampleContainer.NAME, (syncId, id, player, buf) -> {
			BlockPos bp = buf.readBlockPos();
			BlockEntity be = player.world.getBlockEntity(bp);
			if (be instanceof ExampleBlockEntity)
				return new ExampleContainer(syncId, player.inventory, (ExampleBlockEntity) be);
			return null;
		});
		Registry.register(Registry.ITEM, new Identifier(ExampleMod.MODID, "block_example"), ExampleBlockItem.INSTANCE);
		Registry.register(Registry.ITEM, new Identifier(ExampleMod.MODID, "example"), ExampleItem.INSTANCE);
	}
}
