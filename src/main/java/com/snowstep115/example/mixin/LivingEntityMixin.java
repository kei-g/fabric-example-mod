package com.snowstep115.example.mixin;

import com.snowstep115.example.ExampleMod;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(at = @At("HEAD"), method = "sendPickup(Lnet/minecraft/entity/Entity;I)V")
    void onSendPickup(Entity entity, int unknown, CallbackInfo info) {
        ExampleMod.info("pickup %s, %d", entity.getEntityName(), unknown);
        if (entity instanceof ItemEntity) {
            ItemEntity ie = (ItemEntity) entity;
            Object me = this;
            if (me instanceof PlayerEntity) {
                PlayerEntity pe = (PlayerEntity) me;
                pe.sendMessage(new LiteralText(String.format("pickup %s, %d", ie.getStack(), unknown)));
            }
        }
    }
}
