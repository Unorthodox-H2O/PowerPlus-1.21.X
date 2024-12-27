package net.unorthodox.powerplus.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record InductionSmelterRecipeInput(ItemStack input_1, ItemStack input_2, ItemStack input_3) implements RecipeInput {
    @Override
    public ItemStack getItem(int i) {
        if (i == 0) {
            return input_1;
        } else if (i == 1) {
            return input_2;
        } else if (i == 2) {
            return input_3;
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
    }

    @Override
    public int size() {
        return 3;
    }
}
