package net.unorthodox.powerplus.datagen;

import net.minecraft.world.item.Items;
import net.neoforged.fml.common.Mod;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.block.ModBlocks;
import net.unorthodox.powerplus.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.recipes.RecipeCategory.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        //Smelting Recipes
        List<ItemLike> SCANDIUM_SMELTABLES = List.of(ModItems.SCANDIUM,
                ModItems.RAW_SCANDIUM);
        List<ItemLike> SAMARIUM_SMELTABLES = List.of(ModItems.SAMARIUM,
                ModItems.RAW_SAMARIUM);
        List<ItemLike> EUROPIUM_SMELTABLES = List.of(ModItems.EUROPIUM,
                ModItems.RAW_EUROPIUM);
        List<ItemLike> CERIUM_SMELTABLES = List.of(ModItems.CERIUM,
                ModItems.RAW_CERIUM);
        List<ItemLike> TERBIUM_SMELTABLES = List.of(ModItems.TERBIUM,
                ModItems.RAW_TERBIUM);
        List<ItemLike> FORGED_SCRAP_SMELTABLES = List.of(ModItems.FORGED_SCRAP,
                ModItems.FORGED_SCRAP_DUST);


        //Shaped Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SCANDIUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SCANDIUM.get())
                .unlockedBy("has_scandium", has(ModItems.SCANDIUM.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAMARIUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAMARIUM.get())
                .unlockedBy("has_samarium", has(ModItems.SAMARIUM.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.EUROPIUM_BLOCK.get())
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .define('E', ModItems.EUROPIUM.get())
                .unlockedBy("has_europium", has(ModItems.EUROPIUM.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CERIUM_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', ModItems.CERIUM.get())
                .unlockedBy("has_cerium", has(ModItems.CERIUM.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TERBIUM_BLOCK.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', ModItems.TERBIUM.get())
                .unlockedBy("has_terbium", has(ModItems.TERBIUM.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.UPGRADEBLANK.get())
                .pattern("III")
                .pattern("ICI")
                .pattern("III")
                .define('I', Items.IRON_INGOT)
                .define('C', Items.COPPER_BLOCK)
                .unlockedBy("has_upgradeblank", has(ModItems.UPGRADEBLANK.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ADVANCEDUPGRADEBLANK.get())
                .pattern("SDS")
                .pattern("DBD")
                .pattern("SDS")
                .define('D', Items.DIAMOND)
                .define('S', ModItems.FORGED_SCRAP_INGOT.get())
                .define('B', ModItems.UPGRADEBLANK.get())
                .unlockedBy("has_advancedupgradeblank", has(ModItems.ADVANCEDUPGRADEBLANK.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FORGED_SCRAP_INGOT.get())
                .pattern("GIG")
                .pattern("ICI")
                .pattern("GIG")
                .define('I', ModItems.FORGED_SCRAP.get())
                .define('G', Items.GOLD_INGOT)
                .define('C', Items.LAVA_BUCKET)
                .unlockedBy("has_advancedblank", has(ModItems.FORGED_SCRAP_INGOT.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.UPGRADETIER1.get())
                .pattern("CCC")
                .pattern("CBC")
                .pattern("CCC")
                .define('C', Items.COPPER_INGOT)
                .define('B', ModItems.UPGRADEBLANK.get())
                .unlockedBy("has_upgradetier1", has(ModItems.UPGRADETIER1.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.UPGRADETIER2.get())
                .pattern("SSS")
                .pattern("STS")
                .pattern("SSS")
                .define('S', ModItems.ADVANCEDUPGRADEBLANK.get())
                .define('T', ModBlocks.SCANDIUM_BLOCK.get())
                .unlockedBy("has_upgradetier2", has(ModItems.UPGRADETIER2.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.UPGRADETIER3.get())
                .pattern("ISI")
                .pattern("STS")
                .pattern("ISI")
                .define('T', ModItems.ADVANCEDUPGRADEBLANK.get())
                .define('S', ModBlocks.SAMARIUM_BLOCK.get())
                .define('I', Items.IRON_BLOCK)
                .unlockedBy("has_upgradetier3", has(ModItems.UPGRADETIER3.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.UPGRADETIER4.get())
                .pattern("GEG")
                .pattern("ETE")
                .pattern("GEG")
                .define('T', ModItems.ADVANCEDUPGRADEBLANK.get())
                .define('E', ModBlocks.EUROPIUM_BLOCK.get())
                .define('G', Items.GOLD_BLOCK)
                .unlockedBy("has_upgradetier4", has(ModItems.UPGRADETIER4.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.UPGRADETIER5.get())
                .pattern("DCD")
                .pattern("CTC")
                .pattern("DCD")
                .define('T', ModItems.ADVANCEDUPGRADEBLANK.get())
                .define('C', ModBlocks.CERIUM_BLOCK.get())
                .define('D', Items.DIAMOND_BLOCK)
                .unlockedBy("has_upgradetier5", has(ModItems.UPGRADETIER5.get())).save(pRecipeOutput);

        // Shapeless Recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SCANDIUM.get(), 9)
                .requires(ModBlocks.SCANDIUM_BLOCK.get())
                .unlockedBy("has_scandium_block", has(ModBlocks.SCANDIUM_BLOCK.get())).save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAMARIUM.get(), 9)
                .requires(ModBlocks.SAMARIUM_BLOCK.get())
                .unlockedBy("has_samarium_block", has(ModBlocks.SAMARIUM_BLOCK.get())).save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.EUROPIUM.get(), 9)
                .requires(ModBlocks.EUROPIUM_BLOCK.get())
                .unlockedBy("has_europium_block", has(ModBlocks.EUROPIUM_BLOCK.get())).save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CERIUM.get(), 9)
                .requires(ModBlocks.CERIUM_BLOCK.get())
                .unlockedBy("has_cerium_block", has(ModBlocks.CERIUM_BLOCK.get())).save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TERBIUM.get(), 9)
                .requires(ModBlocks.TERBIUM_BLOCK.get())
                .unlockedBy("has_terbium_block", has(ModBlocks.TERBIUM_BLOCK.get())).save(pRecipeOutput);



        oreSmelting(pRecipeOutput, SCANDIUM_SMELTABLES, MISC, ModItems.SCANDIUM.get(), 0.25f, 200, "scandium");
        oreBlasting(pRecipeOutput, SCANDIUM_SMELTABLES, MISC, ModItems.SCANDIUM.get(), 0.25f, 100, "scandium");
        oreSmelting(pRecipeOutput, SAMARIUM_SMELTABLES, MISC, ModItems.SAMARIUM.get(), 0.25f, 200, "samarium");
        oreBlasting(pRecipeOutput, SAMARIUM_SMELTABLES, MISC, ModItems.SAMARIUM.get(), 0.25f, 100, "samarium");
        oreSmelting(pRecipeOutput, EUROPIUM_SMELTABLES, MISC, ModItems.EUROPIUM.get(), 0.25f, 200, "europium");
        oreBlasting(pRecipeOutput, EUROPIUM_SMELTABLES, MISC, ModItems.EUROPIUM.get(), 0.25f, 100, "europium");
        oreSmelting(pRecipeOutput, CERIUM_SMELTABLES, MISC, ModItems.CERIUM.get(), 0.25f, 200, "cerium");
        oreBlasting(pRecipeOutput, CERIUM_SMELTABLES, MISC, ModItems.CERIUM.get(), 0.25f, 100, "cerdium");
        oreSmelting(pRecipeOutput, TERBIUM_SMELTABLES, MISC, ModItems.TERBIUM.get(), 0.25f, 200, "terbium");
        oreBlasting(pRecipeOutput, TERBIUM_SMELTABLES, MISC, ModItems.TERBIUM.get(), 0.25f, 100, "terbium");
        oreSmelting(pRecipeOutput, FORGED_SCRAP_SMELTABLES, MISC, ModItems.FORGED_SCRAP.get(), 0.25f, 200, "forged_scrap");
        oreBlasting(pRecipeOutput, FORGED_SCRAP_SMELTABLES, MISC, ModItems.FORGED_SCRAP.get(), 0.25f, 100, "forged_scrap");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, PowerPlus.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}