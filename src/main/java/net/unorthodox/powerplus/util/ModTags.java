package net.unorthodox.powerplus.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.unorthodox.powerplus.PowerPlus;


public class ModTags {
    public static class Blocks{

        //add block tags
        public static final TagKey<Block> NEEDS_WRENCH = createTag("needs_wrench");

        private static final TagKey<Block>  createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(PowerPlus.MOD_ID, name));
        }

    }

    public static class Items{
        //add item tags
        public static final TagKey<Item> TRANSFORMABLE_TIER1_ITEMS = createTag("transformable_tier1_items");
        public static final TagKey<Item> TRANSFORMABLE_TIER2_ITEMS = createTag("transformable_tier2_items");
        public static final TagKey<Item> TRANSFORMABLE_TIER3_ITEMS = createTag("transformable_tier3_items");
        public static final TagKey<Item> TRANSFORMABLE_TIER4_ITEMS = createTag("transformable_tier4_items");
        public static final TagKey<Item> TRANSFORMABLE_TIER5_ITEMS = createTag("transformable_tier5_items");
        public static final TagKey<Item> BENK_ACTIVE_TIER_1 = createTag("benk_active_tier_1");
        public static final TagKey<Item> BENK_ACTIVE_TIER_2 = createTag("benk_active_tier_2");
        public static final TagKey<Item> BENK_ACTIVE_TIER_3 = createTag("benk_active_tier_3");
        public static final TagKey<Item> BENK_ACTIVE_TIER_4 = createTag("benk_active_tier_4");
        public static final TagKey<Item> BENK_ACTIVE_TIER_5 = createTag("benk_active_tier_5");

//        public static final TagKey<Item> WRENCHES = createTag("wrenches");

    }

        private static final TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(PowerPlus.MOD_ID, name));
        }


    }

