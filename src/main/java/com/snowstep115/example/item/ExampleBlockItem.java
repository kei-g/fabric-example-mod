package com.snowstep115.example.item;

import com.snowstep115.example.block.ExampleBlock;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;

public final class ExampleBlockItem extends BlockItem {
    public static final ExampleBlockItem INSTANCE = new ExampleBlockItem();

    public ExampleBlockItem() {
        super(ExampleBlock.INSTANCE, new Settings().group(ItemGroup.MISC));
    }
}
