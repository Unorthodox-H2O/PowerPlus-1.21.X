package net.unorthodox.powerplus.util;

import net.minecraft.world.item.Item;

import java.util.Objects;

// Represents a pair of items for paired transformations
public class ItemPair {
    private final Item item1;
    private final Item item2;

    public ItemPair(Item item1, Item item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public Item getItem1() {
        return item1;
    }

    public Item getItem2() {
        return item2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if objects are the same
        if (o == null || getClass() != o.getClass()) return false;

        ItemPair itemPair = (ItemPair) o;

        // Ensure item1 and item2 match in any order
        return (Objects.equals(item1, itemPair.item1) && Objects.equals(item2, itemPair.item2)) ||
                (Objects.equals(item1, itemPair.item2) && Objects.equals(item2, itemPair.item1));
    }

    @Override
    public int hashCode() {
        // Ensure consistent ordering of items for hashCode
        return Objects.hash(item1) + Objects.hash(item2);
    }

    @Override
    public String toString() {
        return "ItemPair{" +
                "item1=" + item1 +
                ", item2=" + item2 +
                '}';
    }
}
