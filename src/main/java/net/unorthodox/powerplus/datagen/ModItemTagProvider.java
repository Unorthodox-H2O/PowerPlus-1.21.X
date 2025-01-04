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
import org.jetbrains.annotations.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.unorthodox.powerplus.util.ModTags.Items.*;


public class ModItemTagProvider extends ItemTagsProvider {
    //Add Public Static Providers
    public static final TagKey<Item> BENK_ACTIVATION_TIER_1 = ItemTags.create(ResourceLocation.fromNamespaceAndPath("powerplus", "benk_activation_tier_1"));

    //public static final TagKey<Item> WRENCHES = forgeTag("tools/wrench");
    //public static final TagKey<Item> BOWS = forgeTag("tools/bow");
    //public static final TagKey<Item> RANGED_WEAPON = forgeTag("tools/ranged_weapon");
    //public static final TagKey<Item> MELEE_WEAPON = forgeTag("tools/melee_weapon");
    //public static final TagKey<Item> MINING_TOOL = forgeTag("tools/mining_tool");
    //public static final TagKey<Item> PAXEL = forgeTag("tools/paxel");
    //End

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, PowerPlus.MOD_ID, existingFileHelper);
    }
    private static TagKey<Item> forgeTag(String name){
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("C", name));

    }


    protected void addTags(HolderLookup.Provider provider) {
        //Add Tag Details
        this.tag(BENK_ACTIVATION_TIER_1)
                .add(Items.REDSTONE);
        this.tag(TRANSFORMABLE_TIER1_ITEMS)
                .add(Items.COPPER_INGOT);
        this.tag(TRANSFORMABLE_TIER2_ITEMS)
                .add(Items.IRON_INGOT);
        this.tag(TRANSFORMABLE_TIER3_ITEMS)
                .add(Items.GOLD_INGOT);
        this.tag(TRANSFORMABLE_TIER4_ITEMS)
                .add(Items.DIAMOND);
        this.tag(TRANSFORMABLE_TIER5_ITEMS)
                .add(Items.NETHERITE_INGOT);



        //this.tag(TOOLS_WRENCH).add((Item)Registration.ArtifexWrench.get());
        //this.tag(BOWS).add((Item)Registration.FerricoreBow.get()).add((Item)Registration.BlazegoldBow.get()).add((Item)Registration.CelestigemPickaxe.get()).add((Item)Registration.EclipseAlloyBow.get());
        //this.tag(RANGED_WEAPON).add((Item)Registration.FerricoreBow.get()).add((Item)Registration.BlazegoldBow.get()).add((Item)Registration.CelestigemPickaxe.get()).add((Item)Registration.EclipseAlloyBow.get());
        //this.tag(MELEE_WEAPON).add((Item)Registration.FerricoreSword.get()).add((Item)Registration.FerricoreAxe.get()).add((Item)Registration.BlazegoldSword.get()).add((Item)Registration.BlazegoldAxe.get()).add((Item)Registration.CelestigemSword.get()).add((Item)Registration.CelestigemAxe.get()).add((Item)Registration.EclipseAlloySword.get()).add((Item)Registration.EclipseAlloyAxe.get()).add((Item)Registration.CelestigemPaxel.get()).add((Item)Registration.EclipseAlloyPaxel.get());
        //this.tag(MINING_TOOL).add((Item)Registration.CelestigemPaxel.get()).add((Item)Registration.EclipseAlloyPaxel.get());
        //this.tag(PAXEL).add((Item)Registration.CelestigemPaxel.get()).add((Item)Registration.EclipseAlloyPaxel.get());

        //End
    }
    public String getName(){ return "Power Plus Item Tags";}
}
