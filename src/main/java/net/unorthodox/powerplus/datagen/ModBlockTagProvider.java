package net.unorthodox.powerplus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PowerPlus.MOD_ID, existingFileHelper);
    }
    public static final TagKey<Block> WRENCHABLE = BlockTags.create(ResourceLocation.fromNamespaceAndPath("forge", "wrenches"));
    
    @Override
    protected void addTags(HolderLookup.Provider provider) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(ModBlocks.SCANDIUM_BLOCK.get())
                    .add(ModBlocks.SAMARIUM_BLOCK.get())
                    .add(ModBlocks.EUROPIUM_BLOCK.get())
                    .add(ModBlocks.CERIUM_BLOCK.get())
                    .add(ModBlocks.TERBIUM_BLOCK.get())
                    .add(ModBlocks.SCANDIUM_BENK.get())
                    .add(ModBlocks.SAMARIUM_BENK.get())
                    .add(ModBlocks.EUROPIUM_BENK.get())
                    .add(ModBlocks.CERIUM_BENK.get())
                    .add(ModBlocks.TERBIUM_BENK.get())
                    .add(ModBlocks.BASICTHERMOGENERATOR.get())
                    .add(ModBlocks.ADVANCEDTHERMOGENERATOR.get())
                    .add(ModBlocks.ELITETHERMOGENERATOR.get())
                    .add(ModBlocks.SUPERIORTHERMOGENERATOR.get())
                    .add(ModBlocks.LEGENDARYTHERMOGENERATOR.get())
                    .add(ModBlocks.ASCENDANTTHERMOGENERATOR.get())
                    .add(ModBlocks.RADIANTTHERMOGENERATOR.get())
                    .add(ModBlocks.BASICSOLIDGENERATOR.get())
                    .add(ModBlocks.ADVANCEDSOLIDGENERATOR.get())
                    .add(ModBlocks.ELITESOLIDGENERATOR.get())
                    .add(ModBlocks.SUPERIORSOLIDGENERATOR.get())
                    .add(ModBlocks.LEGENDARYSOLIDGENERATOR.get())
                    .add(ModBlocks.ASCENDANTSOLIDGENERATOR.get())
                    .add(ModBlocks.RADIANTSOLIDGENERATOR.get())
                    .add(ModBlocks.BASICLIQUIDGENERATOR.get())
                    .add(ModBlocks.ADVANCEDLIQUIDGENERATOR.get())
                    .add(ModBlocks.ELITELIQUIDGENERATOR.get())
                    .add(ModBlocks.SUPERIORLIQUIDGENERATOR.get())
                    .add(ModBlocks.LEGENDARYLIQUIDGENERATOR.get())
                    .add(ModBlocks.ASCENDANTLIQUIDGENERATOR.get())
                    .add(ModBlocks.RADIANTLIQUIDGENERATOR.get())
                    .add(ModBlocks.BASICCELL.get())
                    .add(ModBlocks.ADVANCEDCELL.get())
                    .add(ModBlocks.ELITECELL.get())
                    .add(ModBlocks.SUPERIORCELL.get())
                    .add(ModBlocks.LEGENDARYCELL.get())
                    .add(ModBlocks.ASCENDANTCELL.get())
                    .add(ModBlocks.RADIANTCELL.get())
                    .add(ModBlocks.CRYSTALINFUSER.get())
                    .add(ModBlocks.GRINDER.get())
                    .add(ModBlocks.CREATIVETHERMOGENERATOR.get())
                    .add(ModBlocks.CREATIVESOLIDGENERATOR.get())
                    .add(ModBlocks.CREATIVELIQUIDGENERATOR.get())
                    .add(ModBlocks.CREATIVECELL.get())
                    .add(ModBlocks.CHARCOALBLOCK.get());

            this.tag(BlockTags.NEEDS_STONE_TOOL)
                    .add(ModBlocks.CHARCOALBLOCK.get());

            this.tag(BlockTags.NEEDS_IRON_TOOL)
                    .add(ModBlocks.SCANDIUM_BLOCK.get())
                    .add(ModBlocks.SAMARIUM_BLOCK.get())
                    .add(ModBlocks.SCANDIUM_BENK.get())
                    .add(ModBlocks.SAMARIUM_BENK.get())
                    .add(ModBlocks.BASICTHERMOGENERATOR.get())
                    .add(ModBlocks.ADVANCEDTHERMOGENERATOR.get())
                    .add(ModBlocks.ELITETHERMOGENERATOR.get())
                    .add(ModBlocks.SUPERIORTHERMOGENERATOR.get())
                    .add(ModBlocks.LEGENDARYTHERMOGENERATOR.get())
                    .add(ModBlocks.ASCENDANTTHERMOGENERATOR.get())
                    .add(ModBlocks.RADIANTTHERMOGENERATOR.get())
                    .add(ModBlocks.BASICSOLIDGENERATOR.get())
                    .add(ModBlocks.ADVANCEDSOLIDGENERATOR.get())
                    .add(ModBlocks.ELITESOLIDGENERATOR.get())
                    .add(ModBlocks.SUPERIORSOLIDGENERATOR.get())
                    .add(ModBlocks.LEGENDARYSOLIDGENERATOR.get())
                    .add(ModBlocks.ASCENDANTSOLIDGENERATOR.get())
                    .add(ModBlocks.RADIANTSOLIDGENERATOR.get())
                    .add(ModBlocks.BASICLIQUIDGENERATOR.get())
                    .add(ModBlocks.ADVANCEDLIQUIDGENERATOR.get())
                    .add(ModBlocks.ELITELIQUIDGENERATOR.get())
                    .add(ModBlocks.SUPERIORLIQUIDGENERATOR.get())
                    .add(ModBlocks.LEGENDARYLIQUIDGENERATOR.get())
                    .add(ModBlocks.ASCENDANTLIQUIDGENERATOR.get())
                    .add(ModBlocks.RADIANTLIQUIDGENERATOR.get())
                    .add(ModBlocks.CRYSTALINFUSER.get())
                    .add(ModBlocks.GRINDER.get())
                    .add(ModBlocks.CREATIVETHERMOGENERATOR.get())
                    .add(ModBlocks.CREATIVESOLIDGENERATOR.get())
                    .add(ModBlocks.CREATIVELIQUIDGENERATOR.get());
    
            this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                    .add(ModBlocks.EUROPIUM_BLOCK.get())
                    .add(ModBlocks.CERIUM_BLOCK.get())
                    .add(ModBlocks.TERBIUM_BLOCK.get())
                    .add(ModBlocks.EUROPIUM_BENK.get())
                    .add(ModBlocks.CERIUM_BENK.get())
                    .add(ModBlocks.TERBIUM_BENK.get());
    
            this.tag(WRENCHABLE)
                    .add(ModBlocks.GRINDER.get())
                    .add(ModBlocks.CRYSTALINFUSER.get());
    }
}
