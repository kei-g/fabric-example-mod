package com.snowstep115.example.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public final class ExampleItem extends Item {
    public static final ExampleItem INSTANCE = new ExampleItem();

    public ExampleItem() {
        super(new Settings().group(ItemGroup.MISC));
    }
}
