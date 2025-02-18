package net.unorthodox.powerplus.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.unorthodox.powerplus.block.ModBlocks;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        // Blocks that drop themselves
        dropSelf(ModBlocks.SCANDIUM_BLOCK.get());
        dropSelf(ModBlocks.SAMARIUM_BLOCK.get());
        dropSelf(ModBlocks.EUROPIUM_BLOCK.get());
        dropSelf(ModBlocks.CERIUM_BLOCK.get());
        dropSelf(ModBlocks.TERBIUM_BLOCK.get());
        dropSelf(ModBlocks.BASIC_CASING.get());
        dropSelf(ModBlocks.ADVANCED_CASING.get());
        dropSelf(ModBlocks.ELITE_CASING.get());
        dropSelf(ModBlocks.SUPERIOR_CASING.get());
        dropSelf(ModBlocks.LEGENDARY_CASING.get());
        dropSelf(ModBlocks.ASCENDANT_CASING.get());
        dropSelf(ModBlocks.RADIANT_CASING.get());
        dropSelf(ModBlocks.CHARCOALBLOCK.get());

        dropSelf(ModBlocks.SCANDIUM_BENK.get());
        dropSelf(ModBlocks.SAMARIUM_BENK.get());
        dropSelf(ModBlocks.EUROPIUM_BENK.get());
        dropSelf(ModBlocks.CERIUM_BENK.get());
        dropSelf(ModBlocks.TERBIUM_BENK.get());

        dropSelf(ModBlocks.BASICTHERMOGENERATOR.get());
        dropSelf(ModBlocks.ADVANCEDTHERMOGENERATOR.get());
        dropSelf(ModBlocks.ELITETHERMOGENERATOR.get());
        dropSelf(ModBlocks.SUPERIORTHERMOGENERATOR.get());
        dropSelf(ModBlocks.LEGENDARYTHERMOGENERATOR.get());
        dropSelf(ModBlocks.ASCENDANTTHERMOGENERATOR.get());
        dropSelf(ModBlocks.RADIANTTHERMOGENERATOR.get());
        dropSelf(ModBlocks.CREATIVETHERMOGENERATOR.get());

        dropSelf(ModBlocks.BASICLIQUIDGENERATOR.get());
        dropSelf(ModBlocks.ADVANCEDLIQUIDGENERATOR.get());
        dropSelf(ModBlocks.ELITELIQUIDGENERATOR.get());
        dropSelf(ModBlocks.SUPERIORLIQUIDGENERATOR.get());
        dropSelf(ModBlocks.LEGENDARYLIQUIDGENERATOR.get());
        dropSelf(ModBlocks.ASCENDANTLIQUIDGENERATOR.get());
        dropSelf(ModBlocks.RADIANTLIQUIDGENERATOR.get());
        dropSelf(ModBlocks.CREATIVELIQUIDGENERATOR.get());

        dropSelf(ModBlocks.BASICSOLIDGENERATOR.get());
        dropSelf(ModBlocks.ADVANCEDSOLIDGENERATOR.get());
        dropSelf(ModBlocks.ELITESOLIDGENERATOR.get());
        dropSelf(ModBlocks.SUPERIORSOLIDGENERATOR.get());
        dropSelf(ModBlocks.LEGENDARYSOLIDGENERATOR.get());
        dropSelf(ModBlocks.ASCENDANTSOLIDGENERATOR.get());
        dropSelf(ModBlocks.RADIANTSOLIDGENERATOR.get());
        dropSelf(ModBlocks.CREATIVESOLIDGENERATOR.get());

        dropSelf(ModBlocks.BASICCELL.get());
        dropSelf(ModBlocks.ADVANCEDCELL.get());
        dropSelf(ModBlocks.ELITECELL.get());
        dropSelf(ModBlocks.SUPERIORCELL.get());
        dropSelf(ModBlocks.LEGENDARYCELL.get());
        dropSelf(ModBlocks.ASCENDANTCELL.get());
        dropSelf(ModBlocks.RADIANTCELL.get());
        dropSelf(ModBlocks.CREATIVECELL.get());

        dropSelf(ModBlocks.CRYSTALINFUSER.get());
        dropSelf(ModBlocks.GRINDER.get());

    }
        // Blocks that drop Something Else


        @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;

    }

}
