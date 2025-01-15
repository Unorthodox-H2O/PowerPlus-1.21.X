package net.unorthodox.powerplus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.unorthodox.powerplus.util.ModTags.Items.*;


public class ModItemTagProvider extends ItemTagsProvider {
    //Add Public Static Providers
    public static final TagKey<Item> BENK_ACTIVATION_TIER_1 = ItemTags.create(ResourceLocation.fromNamespaceAndPath("powerplus", "benk_activation_tier_1"));
    public static final TagKey<Item> FORGE_DUSTS = ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "dusts"));
    public static final TagKey<Item> FORGE_INGOTS = ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "ingots"));
    public static final TagKey<Item> FORGE_WRENCHES = ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "wrenches"));

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, PowerPlus.MOD_ID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider provider) {
        // -- Upgrades
        this.tag(BENK_ACTIVATION_TIER_1).add(Items.REDSTONE);
        this.tag(TRANSFORMABLE_TIER1_ITEMS).add(Items.COPPER_INGOT);
        this.tag(TRANSFORMABLE_TIER2_ITEMS).add(Items.IRON_INGOT);
        this.tag(TRANSFORMABLE_TIER3_ITEMS).add(Items.GOLD_INGOT);
        this.tag(TRANSFORMABLE_TIER4_ITEMS).add(Items.DIAMOND);
        this.tag(TRANSFORMABLE_TIER5_ITEMS).add(Items.NETHERITE_INGOT);

        // -- Misc Tags
        this.tag(FORGE_WRENCHES).add(ModItems.WRENCH.get());
        
        // -- Dusts
        this.tag(FORGE_DUSTS).add(
                ModItems.IRON_DUST.get(),
                ModItems.IRON_DUST.get()
        );

        // -- Ingots
        this.tag(FORGE_INGOTS).add(
                ModItems.SCANDIUM.get(),
                ModItems.SAMARIUM.get(),
                ModItems.EUROPIUM.get(),
                ModItems.CERIUM.get(),
                ModItems.TERBIUM.get(),
                ModItems.FORGED_SCRAP_INGOT.get()
        );
    }
    public String getName(){ return "Power Plus Item Tags";}
}
