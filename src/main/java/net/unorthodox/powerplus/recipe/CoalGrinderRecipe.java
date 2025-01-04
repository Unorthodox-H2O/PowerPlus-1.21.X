package net.unorthodox.powerplus.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;


public record CoalGrinderRecipe(Ingredient inputItem, ItemStack output) implements Recipe<CoalGrinderRecipeInput> {
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(CoalGrinderRecipeInput pInput, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        return inputItem.test(pInput.getItem(0));
    }

    @Override
    public ItemStack assemble(CoalGrinderRecipeInput pInput, HolderLookup.Provider pRegistries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider pRegistries) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COAL_GRINDER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.GRINDING_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<CoalGrinderRecipe> {
        public static final MapCodec<CoalGrinderRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(CoalGrinderRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(CoalGrinderRecipe::output)
        ).apply(inst, CoalGrinderRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, CoalGrinderRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, CoalGrinderRecipe::inputItem,
                        ItemStack.STREAM_CODEC, CoalGrinderRecipe::output,
                        CoalGrinderRecipe::new);

        @Override
        public MapCodec<CoalGrinderRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, CoalGrinderRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}