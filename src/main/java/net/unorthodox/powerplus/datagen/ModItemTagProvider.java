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
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.unorthodox.powerplus.util.ModTags.Items.*;

public class ModItemTagProvider extends ItemTagsProvider {
    //Add Public Static Providers
    public static final TagKey<Item> WRENCHES = forgeTag("wrenches");
    public static final TagKey<Item> TOOLS_WRENCH = forgeTag("tools/wrench");
    public static final TagKey<Item> RAW_MATERIALS = forgeTag("raw_materials");
    public static final TagKey<Item> INGOTS = forgeTag("ingots");
    public static final TagKey<Item> DUSTS = forgeTag("dusts");
    public static final TagKey<Item> BENK_ACTIVATION_TIER_1 = ItemTags.create(ResourceLocation.fromNamespaceAndPath("powerplus", "benk_activation_tier_1"));

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, PowerPlus.MOD_ID, existingFileHelper);
    }

    private static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    protected void addTags(HolderLookup.Provider provider) {
        tag(WRENCHES)
                .add(ModItems.WRENCH.get());
        tag(TOOLS_WRENCH)
                .add(ModItems.WRENCH.get());
        tag(DUSTS)
                .add(ModItems.COPPER_DUST.get())
                .add(ModItems.IRON_DUST.get())
                .add(ModItems.GOLD_DUST.get());
        tag(RAW_MATERIALS)
                .add(Items.RAW_COPPER)
                .add(Items.RAW_IRON)
                .add(Items.RAW_GOLD);
        tag(INGOTS)
                .add(ModItems.SCANDIUM.get())
                .add(ModItems.SAMARIUM.get())
                .add(ModItems.EUROPIUM.get())
                .add(ModItems.CERIUM.get())
                .add(ModItems.TERBIUM.get())
                .add(ModItems.FORGED_SCRAP_INGOT.get());
        /*
        tag(ItemTags.SWORDS)
                //.add(Registration.FerricoreSword.get())
                //.add(Registration.EclipseAlloySword.get());
        tag(ItemTags.PICKAXES)

        tag(ItemTags.SHOVELS)

        tag(ItemTags.AXES)

        tag(ItemTags.HOES)

        tag(Tags.Items.INGOTS)

        tag(Tags.Items.RAW_MATERIALS)

        tag(Tags.Items.GEMS)

        tag(Tags.Items.STORAGE_BLOCKS)

        tag(ItemTags.FOOT_ARMOR)

        tag(ItemTags.LEG_ARMOR)

        tag(ItemTags.CHEST_ARMOR)

        tag(ItemTags.HEAD_ARMOR)

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)

        tag(ItemTags.BOW_ENCHANTABLE)

        tag(ItemTags.DURABILITY_ENCHANTABLE)

        tag(BOWS)

        tag(RANGED_WEAPON)

        tag(MELEE_WEAPON)
                .add(Registration.FerricoreSword.get())
                .add(Registration.FerricoreAxe.get())
                .add(Registration.BlazegoldSword.get())
                .add(Registration.BlazegoldAxe.get())
                .add(Registration.CelestigemSword.get())
                .add(Registration.CelestigemAxe.get())
                .add(Registration.EclipseAlloySword.get())
                .add(Registration.EclipseAlloyAxe.get())
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());

        tag(MINING_TOOL)
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());
        tag(PAXEL)
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());
        tag(ItemTags.CLUSTER_MAX_HARVESTABLES)
                .add(Registration.FerricorePickaxe.get())
                .add(Registration.BlazegoldPickaxe.get())
                .add(Registration.CelestigemPickaxe.get())
                .add(Registration.EclipseAlloyPickaxe.get())
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());
        tag(PARADOX_DENY)
                .add(Items.BEDROCK);
        tag(STORAGEBLOCKS)
                .add(Registration.CharcoalBlock_ITEM.get());
        tag(CHARCOALBLOCKS)
                .add(Registration.CharcoalBlock_ITEM.get());

         */

        // -- Upgrades
        this.tag(BENK_ACTIVATION_TIER_1).add(Items.REDSTONE);
        this.tag(TRANSFORMABLE_TIER1_ITEMS).add(Items.COPPER_INGOT);
        this.tag(TRANSFORMABLE_TIER2_ITEMS).add(Items.IRON_INGOT);
        this.tag(TRANSFORMABLE_TIER3_ITEMS).add(Items.GOLD_INGOT);
        this.tag(TRANSFORMABLE_TIER4_ITEMS).add(Items.DIAMOND);
        this.tag(TRANSFORMABLE_TIER5_ITEMS).add(Items.NETHERITE_INGOT);

    }
    public String getName(){ return "Power Plus Item Tags";}
}

 /*
public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(packOutput, lookupProvider, blockTags.contentsGetter(), PowerPlus.MOD_ID, helper);
    }

    public static final TagKey<Item> WRENCHES = forgeTag("wrenches");
    public static final TagKey<Item> TOOLS_WRENCH = forgeTag("tools/wrench");
    public static final TagKey<Item> RAW_MATERIALS = forgeTag("raw_materials");
    public static final TagKey<Item> DUSTS = forgeTag("dusts");

    public static final TagKey<Item> RAW_FERRICORE = forgeTag("raw_materials/ferricore");
    public static final TagKey<Item> RAW_BLAZEGOLD = forgeTag("raw_materials/blazegold");
    public static final TagKey<Item> RAW_ECLIPSEALLOY = forgeTag("raw_materials/eclipsealloy");
    public static final TagKey<Item> INGOT_FERRICORE = forgeTag("ingots/ferricore");
    public static final TagKey<Item> INGOT_BLAZEGOLD = forgeTag("ingots/blazegold");
    public static final TagKey<Item> INGOT_ECLIPSEALLOY = forgeTag("ingots/eclipsealloy");

    public static final TagKey<Item> BOWS = forgeTag("tools/bow");
    public static final TagKey<Item> RANGED_WEAPON = forgeTag("tools/ranged_weapon");
    public static final TagKey<Item> MELEE_WEAPON = forgeTag("tools/melee_weapon");
    public static final TagKey<Item> MINING_TOOL = forgeTag("tools/mining_tool");
    public static final TagKey<Item> PAXEL = forgeTag("tools/paxel");
    public static final TagKey<Item> STORAGEBLOCKS = forgeTag("storage_blocks");
    public static final TagKey<Item> CHARCOALBLOCKS = forgeTag("storage_blocks/charcoal");


    private static TagKey<Item> forgeTag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", name));
    }

    public static final TagKey<Item> FUEL_CANISTER_DENY = ItemTags.create(ResourceLocation.fromNamespaceAndPath(JustDireThings.MODID, "deny_fuel_canister"));
    public static final TagKey<Item> AUTO_SMELT_DENY = ItemTags.create(ResourceLocation.fromNamespaceAndPath(JustDireThings.MODID, "auto_smelt_deny"));
    public static final TagKey<Item> AUTO_SMOKE_DENY = ItemTags.create(ResourceLocation.fromNamespaceAndPath(JustDireThings.MODID, "auto_smoke_deny"));
    public static final TagKey<Item> GOO_REVIVE_TIER_1 = ItemTags.create(ResourceLocation.fromNamespaceAndPath(JustDireThings.MODID, "goo_revive_tier_1"));
    public static final TagKey<Item> GOO_REVIVE_TIER_2 = ItemTags.create(ResourceLocation.fromNamespaceAndPath(JustDireThings.MODID, "goo_revive_tier_2"));
    public static final TagKey<Item> GOO_REVIVE_TIER_3 = ItemTags.create(ResourceLocation.fromNamespaceAndPath(JustDireThings.MODID, "goo_revive_tier_3"));
    public static final TagKey<Item> GOO_REVIVE_TIER_4 = ItemTags.create(ResourceLocation.fromNamespaceAndPath(JustDireThings.MODID, "goo_revive_tier_4"));
    public static final TagKey<Item> PARADOX_DENY = ItemTags.create(ResourceLocation.fromNamespaceAndPath(JustDireThings.MODID, "paradox_deny"));

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(AUTO_SMELT_DENY);
        tag(AUTO_SMOKE_DENY);
        tag(FUEL_CANISTER_DENY)
                .add(Items.LAVA_BUCKET);
        tag(ItemTags.SWORDS)
                .add(Registration.FerricoreSword.get())
                .add(Registration.BlazegoldSword.get())
                .add(Registration.CelestigemSword.get())
                .add(Registration.EclipseAlloySword.get());
        tag(ItemTags.PICKAXES)
                .add(Registration.FerricorePickaxe.get())
                .add(Registration.BlazegoldPickaxe.get())
                .add(Registration.CelestigemPickaxe.get())
                .add(Registration.EclipseAlloyPickaxe.get())
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());
        tag(ItemTags.SHOVELS)
                .add(Registration.FerricoreShovel.get())
                .add(Registration.BlazegoldShovel.get())
                .add(Registration.CelestigemShovel.get())
                .add(Registration.EclipseAlloyShovel.get())
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());
        tag(ItemTags.AXES)
                .add(Registration.FerricoreAxe.get())
                .add(Registration.BlazegoldAxe.get())
                .add(Registration.CelestigemAxe.get())
                .add(Registration.EclipseAlloyAxe.get())
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());
        tag(ItemTags.HOES)
                .add(Registration.FerricoreHoe.get())
                .add(Registration.BlazegoldHoe.get())
                .add(Registration.CelestigemHoe.get())
                .add(Registration.EclipseAlloyHoe.get());
        tag(Tags.Items.INGOTS)
                .add(Registration.FerricoreIngot.get())
                .add(Registration.BlazegoldIngot.get())
                .add(Registration.EclipseAlloyIngot.get());
        tag(Tags.Items.RAW_MATERIALS)
                .add(Registration.RawFerricore.get())
                .add(Registration.RawBlazegold.get())
                .add(Registration.RawEclipseAlloy.get());
        tag(Tags.Items.GEMS)
                .add(Registration.Celestigem.get());
        tag(WRENCHES)
                .add(Registration.FerricoreWrench.get());
        tag(TOOLS_WRENCH)
                .add(Registration.FerricoreWrench.get());
        tag(Tags.Items.STORAGE_BLOCKS)
                .add(Registration.FerricoreBlock_ITEM.get())
                .add(Registration.BlazeGoldBlock_ITEM.get())
                .add(Registration.CelestigemBlock_ITEM.get())
                .add(Registration.EclipseAlloyBlock_ITEM.get());
        tag(ItemTags.FOOT_ARMOR)
                .add(Registration.FerricoreBoots.get())
                .add(Registration.BlazegoldBoots.get())
                .add(Registration.CelestigemBoots.get())
                .add(Registration.EclipseAlloyBoots.get());
        tag(ItemTags.LEG_ARMOR)
                .add(Registration.FerricoreLeggings.get())
                .add(Registration.BlazegoldLeggings.get())
                .add(Registration.CelestigemLeggings.get())
                .add(Registration.EclipseAlloyLeggings.get());
        tag(ItemTags.CHEST_ARMOR)
                .add(Registration.FerricoreChestplate.get())
                .add(Registration.BlazegoldChestplate.get())
                .add(Registration.CelestigemChestplate.get())
                .add(Registration.EclipseAlloyChestplate.get());
        tag(ItemTags.HEAD_ARMOR)
                .add(Registration.FerricoreHelmet.get())
                .add(Registration.BlazegoldHelmet.get())
                .add(Registration.CelestigemHelmet.get())
                .add(Registration.EclipseAlloyHelmet.get());
        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(Registration.FerricoreBoots.get())
                .add(Registration.BlazegoldBoots.get())
                .add(Registration.CelestigemBoots.get())
                .add(Registration.EclipseAlloyBoots.get());
        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(Registration.FerricoreLeggings.get())
                .add(Registration.BlazegoldLeggings.get())
                .add(Registration.CelestigemLeggings.get())
                .add(Registration.EclipseAlloyLeggings.get());
        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(Registration.FerricoreChestplate.get())
                .add(Registration.BlazegoldChestplate.get())
                .add(Registration.CelestigemChestplate.get())
                .add(Registration.EclipseAlloyChestplate.get());
        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(Registration.FerricoreHelmet.get())
                .add(Registration.BlazegoldHelmet.get())
                .add(Registration.CelestigemHelmet.get())
                .add(Registration.EclipseAlloyHelmet.get());
        tag(ItemTags.BOW_ENCHANTABLE)
                .add(Registration.FerricoreBow.get())
                .add(Registration.BlazegoldBow.get())
                .add(Registration.CelestigemBow.get())
                .add(Registration.EclipseAlloyBow.get());
        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(Registration.FerricoreBow.get())
                .add(Registration.BlazegoldBow.get())
                .add(Registration.CelestigemBow.get())
                .add(Registration.EclipseAlloyBow.get());
        tag(RAW_FERRICORE)
                .add(Registration.RawFerricore.get());
        tag(RAW_BLAZEGOLD)
                .add(Registration.RawBlazegold.get());
        tag(RAW_ECLIPSEALLOY)
                .add(Registration.RawEclipseAlloy.get());
        tag(INGOT_FERRICORE)
                .add(Registration.FerricoreIngot.get());
        tag(INGOT_BLAZEGOLD)
                .add(Registration.BlazegoldIngot.get());
        tag(INGOT_ECLIPSEALLOY)
                .add(Registration.EclipseAlloyIngot.get());
        tag(GOO_REVIVE_TIER_1)
                .add(Items.SUGAR)
                .add(Items.ROTTEN_FLESH);
        tag(GOO_REVIVE_TIER_2)
                .add(Items.NETHER_WART)
                .add(Items.BLAZE_POWDER);
        tag(GOO_REVIVE_TIER_3)
                .add(Items.CHORUS_FRUIT)
                .add(Items.ENDER_PEARL);
        tag(GOO_REVIVE_TIER_4)
                .add(Items.SCULK)
                .add(Items.SCULK_CATALYST);
        tag(BOWS)
                .add(Registration.FerricoreBow.get())
                .add(Registration.BlazegoldBow.get())
                .add(Registration.CelestigemPickaxe.get())
                .add(Registration.EclipseAlloyBow.get());
        tag(RANGED_WEAPON)
                .add(Registration.FerricoreBow.get())
                .add(Registration.BlazegoldBow.get())
                .add(Registration.CelestigemPickaxe.get())
                .add(Registration.EclipseAlloyBow.get());
        tag(MELEE_WEAPON)
                .add(Registration.FerricoreSword.get())
                .add(Registration.FerricoreAxe.get())
                .add(Registration.BlazegoldSword.get())
                .add(Registration.BlazegoldAxe.get())
                .add(Registration.CelestigemSword.get())
                .add(Registration.CelestigemAxe.get())
                .add(Registration.EclipseAlloySword.get())
                .add(Registration.EclipseAlloyAxe.get())
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());
        tag(MINING_TOOL)
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());
        tag(PAXEL)
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());
        tag(ItemTags.CLUSTER_MAX_HARVESTABLES)
                .add(Registration.FerricorePickaxe.get())
                .add(Registration.BlazegoldPickaxe.get())
                .add(Registration.CelestigemPickaxe.get())
                .add(Registration.EclipseAlloyPickaxe.get())
                .add(Registration.CelestigemPaxel.get())
                .add(Registration.EclipseAlloyPaxel.get());
        tag(PARADOX_DENY)
                .add(Items.BEDROCK);
        tag(STORAGEBLOCKS)
                .add(Registration.CharcoalBlock_ITEM.get());
        tag(CHARCOALBLOCKS)
                .add(Registration.CharcoalBlock_ITEM.get());
    }

    @Override
    public String getName() {
        return "JustDireThings Item Tags";
    }

  */
