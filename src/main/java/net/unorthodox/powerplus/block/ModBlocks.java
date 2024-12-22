package net.unorthodox.powerplus.block;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.block.benk.*;
import net.unorthodox.powerplus.block.thermo.*;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PowerPlus.MOD_ID);

        public static final DeferredBlock<Block> SCANDIUM_BLOCK;
        public static final DeferredBlock<Block> SAMARIUM_BLOCK;
        public static final DeferredBlock<Block> EUROPIUM_BLOCK;
        public static final DeferredBlock<Block> CERIUM_BLOCK;
        public static final DeferredBlock<Block> TERBIUM_BLOCK;
        public static final DeferredBlock<Block> BASIC_CASING;
        public static final DeferredBlock<Block> ADVANCED_CASING;
        public static final DeferredBlock<Block> ELITE_CASING;
        public static final DeferredBlock<Block> SUPERIOR_CASING;
        public static final DeferredBlock<Block> LEGENDARY_CASING;
        public static final DeferredBlock<Block> ASCENDANT_CASING;
        public static final DeferredBlock<Block> RADIANT_CASING;
        public static final DeferredBlock<Block> SCANDIUM_BENK;
        public static final DeferredBlock<Block> SAMARIUM_BENK;
        public static final DeferredBlock<Block> EUROPIUM_BENK;
        public static final DeferredBlock<Block> CERIUM_BENK;
        public static final DeferredBlock<Block> TERBIUM_BENK;
        public static final DeferredBlock<Block> BASICTHERMOGENERATOR;
        public static final DeferredBlock<Block> ADVANCEDTHERMOGENERATOR;
        public static final DeferredBlock<Block> ELITETHERMOGENERATOR;
        public static final DeferredBlock<Block> SUPERIORTHERMOGENERATOR;
        public static final DeferredBlock<Block> LEGENDARYTHERMOGENERATOR;
        public static final DeferredBlock<Block> ASCENDANTTHERMOGENERATOR;
        public static final DeferredBlock<Block> RADIANTTHERMOGENERATOR;
        public static final DeferredBlock<Block> CREATIVETHERMOGENERATOR;


    public ModBlocks() {
        }

        static {
            SCANDIUM_BLOCK = BLOCKS.register("scandium_block",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            SAMARIUM_BLOCK = BLOCKS.register("samarium_block",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            EUROPIUM_BLOCK = BLOCKS.register("europium_block",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            CERIUM_BLOCK = BLOCKS.register("cerium_block",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            TERBIUM_BLOCK = BLOCKS.register("terbium_block",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            BASIC_CASING = BLOCKS.register("basic_casing",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            ADVANCED_CASING = BLOCKS.register("advanced_casing",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            ELITE_CASING = BLOCKS.register("elite_casing",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            SUPERIOR_CASING = BLOCKS.register("superior_casing",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            LEGENDARY_CASING = BLOCKS.register("legendary_casing",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            ASCENDANT_CASING = BLOCKS.register("ascendant_casing",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            RADIANT_CASING = BLOCKS.register("radiant_casing",() -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
            SCANDIUM_BENK = BLOCKS.register("scandium_benk",() -> new ScandiumBenk(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
            SAMARIUM_BENK = BLOCKS.register("samarium_benk",() -> new SamariumBenk(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
            EUROPIUM_BENK = BLOCKS.register("europium_benk",() -> new EuropiumBenk(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
            CERIUM_BENK = BLOCKS.register("cerium_benk",() -> new CeriumBenk(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
            TERBIUM_BENK = BLOCKS.register("terbium_benk",() -> new TerbiumBenk(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
            BASICTHERMOGENERATOR = BLOCKS.register("basicthermogenerator",() -> new BasicThermoGenerator(BlockBehaviour.Properties.of()
                    .noCollission().requiresCorrectToolForDrops()));
            ADVANCEDTHERMOGENERATOR = BLOCKS.register("advancedthermogenerator",() -> new AdvancedThermoGenerator(BlockBehaviour.Properties.of()
                    .noCollission().requiresCorrectToolForDrops()));
            ELITETHERMOGENERATOR = BLOCKS.register("elitethermogenerator",() -> new EliteThermoGenerator(BlockBehaviour.Properties.of()
                    .noCollission().requiresCorrectToolForDrops()));
            SUPERIORTHERMOGENERATOR = BLOCKS.register("superiorthermogenerator",() -> new SuperiorThermoGenerator(BlockBehaviour.Properties.of()
                    .noCollission().requiresCorrectToolForDrops()));
            LEGENDARYTHERMOGENERATOR = BLOCKS.register("legendarythermogenerator",() -> new LegendaryThermoGenerator(BlockBehaviour.Properties.of()
                    .noCollission().requiresCorrectToolForDrops()));
            ASCENDANTTHERMOGENERATOR = BLOCKS.register("ascendantthermogenerator",() -> new AscendantThermoGenerator(BlockBehaviour.Properties.of()
                    .noCollission().requiresCorrectToolForDrops()));
            RADIANTTHERMOGENERATOR = BLOCKS.register("radiantthermogenerator",() -> new RadiantThermoGenerator(BlockBehaviour.Properties.of()
                    .noCollission().requiresCorrectToolForDrops()));
            CREATIVETHERMOGENERATOR = BLOCKS.register("creativethermogenerator",() -> new CreativeThermoGenerator(BlockBehaviour.Properties.of()
                    .noCollission().requiresCorrectToolForDrops()));
        }
    }


