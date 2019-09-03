package com.snowstep115.example.mixin;

import com.snowstep115.example.ExampleMod;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Inject(at = @At("HEAD"), method = "run()V")
    void run(CallbackInfo info) {
        ExampleMod.INSTANCE.server = (MinecraftServer) (Object) this;
    }
}
