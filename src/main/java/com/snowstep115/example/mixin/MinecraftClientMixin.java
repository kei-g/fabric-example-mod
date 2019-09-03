package com.snowstep115.example.mixin;

import net.minecraft.client.MinecraftClient;

import com.snowstep115.example.ExampleMod;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	void init(CallbackInfo info) {
		ExampleMod.info("This line is printed by an example mod mixin!");
	}
}
