package com.snowstep115.example.mixin;

import com.snowstep115.example.exposer.EPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements EPlayerEntity {
    private static final String PERSISTENT_KEY = "FabricData";

    private CompoundTag persistentData;

    @Override
    public CompoundTag getPersistentData() {
        if (this.persistentData == null)
            this.persistentData = new CompoundTag();
        return this.persistentData;
    }

    @Inject(at = @At("INVOKE"), method = "readCustomDataFromTag(Lnet/minecraft/nbt/CompoundTag;)V")
    private void fromTag(CompoundTag tag, CallbackInfo info) {
        if (tag.containsKey(PERSISTENT_KEY))
            this.persistentData = tag.getCompound(PERSISTENT_KEY);
    }

    @Inject(at = @At("INVOKE"), method = "writeCustomDataToTag(Lnet/minecraft/nbt/CompoundTag;)V")
    private void toTag(CompoundTag tag, CallbackInfo info) {
        if (this.persistentData != null)
            tag.put(PERSISTENT_KEY, this.persistentData);
    }
}
