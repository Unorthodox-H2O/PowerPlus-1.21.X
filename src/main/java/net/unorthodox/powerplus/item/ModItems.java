package net.unorthodox.powerplus.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PowerPlus.PPLUS);

    //Ingots/Raw Materials
    public static final DeferredItem<Item> SCANDIUM;
    public static final DeferredItem<Item> RAW_SCANDIUM;
    public static final DeferredItem<Item> CERIUM;
    public static final DeferredItem<Item> RAW_CERIUM;
    public static final DeferredItem<Item> SAMARIUM;
    public static final DeferredItem<Item> RAW_SAMARIUM;
    public static final DeferredItem<Item> EUROPIUM;
    public static final DeferredItem<Item> RAW_EUROPIUM;
    public static final DeferredItem<Item> TERBIUM;
    public static final DeferredItem<Item> RAW_TERBIUM;
    public static final DeferredItem<Item> FORGED_SCRAP_INGOT;
    public static final DeferredItem<Item> FORGED_SCRAP_DUST;
    public static final DeferredItem<Item> FORGED_SCRAP;

    // Upgrade Items
    public static final DeferredItem<Item> UPGRADEBLANK;
    public static final DeferredItem<Item> ADVANCEDUPGRADEBLANK;
    public static final DeferredItem<Item> UPGRADETIER1;
    public static final DeferredItem<Item> UPGRADETIER2;
    public static final DeferredItem<Item> UPGRADETIER3;
    public static final DeferredItem<Item> UPGRADETIER4;
    public static final DeferredItem<Item> UPGRADETIER5;

    //Stuff I may add someday
    //public static final DeferredItem<Item> WRENCH;
    // public static final DeferredItem<Item> LANTHANUM;
    //public static final DeferredItem<Item> RAW_LANTHANUM;

    public ModItems(){

    }
    static {

        //Ingots/Raw Materials
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

        // Upgrade Items
        UPGRADEBLANK = ITEMS.registerSimpleItem("upgradeblank");
        ADVANCEDUPGRADEBLANK = ITEMS.registerSimpleItem("advancedupgradeblank");
        UPGRADETIER1 = ITEMS.register("upgradetier1",() -> new UpgradeTier1Item(new Item.Properties()));
        UPGRADETIER2 = ITEMS.register("upgradetier2",() -> new UpgradeTier2Item(new Item.Properties()));
        UPGRADETIER3 = ITEMS.register("upgradetier3",() -> new UpgradeTier3Item(new Item.Properties()));
        UPGRADETIER4 = ITEMS.register("upgradetier4",() -> new UpgradeTier4Item(new Item.Properties()));
        UPGRADETIER5 = ITEMS.register("upgradetier5",() -> new UpgradeTier5Item(new Item.Properties()));

        //Stuff I may add someday
        //WRENCH = ITEMS.register("wrench",() -> new Wrench(new Item.Properties().stacksTo(1)));
        //LANTHANUM = ITEMS.register("lanthanum",() -> new Item(new Item.Properties()));
        //RAW_LANTHANUM = ITEMS.register("raw_lanthanum",() -> new Item(new Item.Properties()));


    }
}
