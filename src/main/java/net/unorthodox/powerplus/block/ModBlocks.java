package net.unorthodox.powerplus.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.block.benk.*;
import net.unorthodox.powerplus.block.liquidgen.*;
import net.unorthodox.powerplus.block.machines.*;
import net.unorthodox.powerplus.block.solidgen.*;
import net.unorthodox.powerplus.block.thermo.*;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.unorthodox.powerplus.item.ModItems;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PowerPlus.MOD_ID);

        public static final DeferredBlock<Block> SCANDIUM_BLOCK = registerBlock("scandium_block",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> SAMARIUM_BLOCK = registerBlock("samarium_block",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> EUROPIUM_BLOCK = registerBlock("europium_block",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> CERIUM_BLOCK = registerBlock("cerium_block",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> TERBIUM_BLOCK = registerBlock("terbium_block",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> BASIC_CASING = registerBlock("basic_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> ADVANCED_CASING = registerBlock("advanced_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> ELITE_CASING = registerBlock("elite_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> SUPERIOR_CASING = registerBlock("superior_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> LEGENDARY_CASING = registerBlock("legendary_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> ASCENDANT_CASING = registerBlock("ascendant_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> RADIANT_CASING = registerBlock("radiant_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> SCANDIUM_BENK = registerBlock("scandium_benk",
                () -> new ScandiumBenk(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
        public static final DeferredBlock<Block> SAMARIUM_BENK = registerBlock("samarium_benk",
                () -> new SamariumBenk(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
        public static final DeferredBlock<Block> EUROPIUM_BENK = registerBlock("europium_benk",
                () -> new EuropiumBenk(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
        public static final DeferredBlock<Block> CERIUM_BENK = registerBlock("cerium_benk",
                () -> new CeriumBenk(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
        public static final DeferredBlock<Block> TERBIUM_BENK = registerBlock("terbium_benk",
                () -> new TerbiumBenk(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));

        public static final DeferredBlock<Block> BASICTHERMOGENERATOR = registerBlock("basicthermogenerator",
                () -> new BasicThermoGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ADVANCEDTHERMOGENERATOR = registerBlock("advancedthermogenerator",
                () -> new AdvancedThermoGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ELITETHERMOGENERATOR = registerBlock("elitethermogenerator",
                () -> new EliteThermoGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> SUPERIORTHERMOGENERATOR = registerBlock("superiorthermogenerator",
                () -> new SuperiorThermoGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> LEGENDARYTHERMOGENERATOR = registerBlock("legendarythermogenerator",
                () -> new LegendaryThermoGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ASCENDANTTHERMOGENERATOR = registerBlock("ascendantthermogenerator",
                () -> new AscendantThermoGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> RADIANTTHERMOGENERATOR = registerBlock("radiantthermogenerator",
                () -> new RadiantThermoGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> CREATIVETHERMOGENERATOR = registerBlock("creativethermogenerator",
                () -> new CreativeThermoGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));

        public static final DeferredBlock<Block> BASICLIQUIDGENERATOR = registerBlock("basicliquidgenerator",
            () -> new BasicLiquidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ADVANCEDLIQUIDGENERATOR = registerBlock("advancedliquidgenerator",
                () -> new AdvancedLiquidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ELITELIQUIDGENERATOR = registerBlock("eliteliquidgenerator",
                () -> new EliteLiquidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> SUPERIORLIQUIDGENERATOR = registerBlock("superiorliquidgenerator",
                () -> new SuperiorLiquidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> LEGENDARYLIQUIDGENERATOR = registerBlock("legendaryliquidgenerator",
                () -> new LegendaryLiquidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ASCENDANTLIQUIDGENERATOR = registerBlock("ascendantliquidgenerator",
                () -> new AscendantLiquidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> RADIANTLIQUIDGENERATOR = registerBlock("radiantliquidgenerator",
                () -> new RadiantLiquidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> CREATIVELIQUIDGENERATOR = registerBlock("creativeliquidgenerator",
                () -> new CreativeLiquidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));

        public static final DeferredBlock<Block> BASICSOLIDGENERATOR = registerBlock("basicsolidgenerator",
            () -> new BasicSolidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ADVANCEDSOLIDGENERATOR = registerBlock("advancedsolidgenerator",
                () -> new AdvancedSolidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ELITESOLIDGENERATOR = registerBlock("elitesolidgenerator",
                () -> new BasicSolidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> SUPERIORSOLIDGENERATOR = registerBlock("superiorsolidgenerator",
                () -> new SuperiorSolidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> LEGENDARYSOLIDGENERATOR = registerBlock("legendarysolidgenerator",
                () -> new LegendarySolidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ASCENDANTSOLIDGENERATOR = registerBlock("ascendantsolidgenerator",
                () -> new AscendantSolidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> RADIANTSOLIDGENERATOR = registerBlock("radiantsolidgenerator",
                () -> new RadiantSolidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> CREATIVESOLIDGENERATOR = registerBlock("creativesolidgenerator",
                () -> new CreativeSolidGenerator(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));

        public static final DeferredBlock<Block> CRYSTALINFUSER = registerBlock("crystalinfuser",
                () -> new CrystalInfuser(BlockBehaviour.Properties.of().noOcclusion().requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    }


