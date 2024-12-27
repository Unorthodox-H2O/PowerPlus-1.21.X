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

public record InductionSmelterRecipe
        (Ingredient inputItem_1, Ingredient inputItem_2, Ingredient inputItem_3, ItemStack output) implements Recipe<InductionSmelterRecipeInput> {

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem_1);
        list.add(inputItem_2);
        list.add(inputItem_3);
        return list;
    }

    @Override
    public boolean matches(InductionSmelterRecipeInput pInput, Level level) {
        if(level.isClientSide()) {
            return false;
        }

        return inputItem_1.test(pInput.getItem(0))
        && inputItem_2.test(pInput.getItem(1))
        && inputItem_3.test(pInput.getItem(2));
    }

    @Override
    public ItemStack assemble(InductionSmelterRecipeInput inductionSmelterRecipeInput, HolderLookup.Provider provider) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.INDUCTIONSMELTER_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.INDUCTIONSMELTER_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<InductionSmelterRecipe>{

        public static final MapCodec<InductionSmelterRecipe> CODEC = RecordCodecBuilder.mapCodec(inst ->inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(InductionSmelterRecipe::inputItem_1),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(InductionSmelterRecipe::inputItem_2),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(InductionSmelterRecipe::inputItem_3),
                ItemStack.CODEC.fieldOf("result").forGetter(InductionSmelterRecipe::output)
        ).apply(inst, InductionSmelterRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, InductionSmelterRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, InductionSmelterRecipe::inputItem_1,
                        Ingredient.CONTENTS_STREAM_CODEC, InductionSmelterRecipe::inputItem_2,
                        Ingredient.CONTENTS_STREAM_CODEC, InductionSmelterRecipe::inputItem_3,
                        ItemStack.STREAM_CODEC, InductionSmelterRecipe::output,
                        InductionSmelterRecipe::new);

        @Override
        public MapCodec<InductionSmelterRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, InductionSmelterRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
