package net.unorthodox.powerplus.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PowerPlus.MOD_ID);
    public static final DeferredItem<Item> SCANDIUM;
    public static final DeferredItem<Item> RAW_SCANDIUM;
    public static final DeferredItem<Item> SAMARIUM;
    public static final DeferredItem<Item> RAW_SAMARIUM;
    public static final DeferredItem<Item> EUROPIUM;
    public static final DeferredItem<Item> RAW_EUROPIUM;
    public static final DeferredItem<Item> CERIUM;
    public static final DeferredItem<Item> RAW_CERIUM;
    public static final DeferredItem<Item> TERBIUM;
    public static final DeferredItem<Item> RAW_TERBIUM;
    public static final DeferredItem<Item> FORGED_SCRAP_INGOT;
    public static final DeferredItem<Item> FORGED_SCRAP_DUST;
    public static final DeferredItem<Item> FORGED_SCRAP;

    //Benk Upgrades
    public static final DeferredItem<Item> UPGRADEBLANK;
    public static final DeferredItem<Item> ADVANCEDUPGRADEBLANK;


    public ModItems(){
    }
    static {
        SCANDIUM = ITEMS.registerSimpleItem("scandium");
        RAW_SCANDIUM = ITEMS.registerSimpleItem("raw_scandium");
        CERIUM = ITEMS.registerSimpleItem("cerium");
        RAW_CERIUM = ITEMS.registerSimpleItem("raw_cerium");
        SAMARIUM = ITEMS.registerSimpleItem("samarium");
        RAW_SAMARIUM = ITEMS.registerSimpleItem("raw_samarium");
        EUROPIUM = ITEMS.registerSimpleItem("europium");
        RAW_EUROPIUM = ITEMS.registerSimpleItem("raw_europium");
        TERBIUM = ITEMS.registerSimpleItem("terbium");
        RAW_TERBIUM= ITEMS.registerSimpleItem("raw_terbium");
        FORGED_SCRAP_INGOT = ITEMS.registerSimpleItem("forged_scrap_ingot");
        FORGED_SCRAP_DUST = ITEMS.registerSimpleItem("forged_scrap_dust");
        FORGED_SCRAP = ITEMS.registerSimpleItem("forged_scrap");
        UPGRADEBLANK = ITEMS.registerSimpleItem("upgradeblank");
        ADVANCEDUPGRADEBLANK = ITEMS.registerSimpleItem("advancedupgradeblank");
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
