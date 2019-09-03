package com.snowstep115.example.mixin;

import com.snowstep115.example.exposer.EItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements EItemStack {
    @Shadow
    public abstract CompoundTag toTag(CompoundTag tag);

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag = toTag(tag);
        return tag;
    }
}
