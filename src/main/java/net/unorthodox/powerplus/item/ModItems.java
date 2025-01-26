package net.unorthodox.powerplus.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.item.fuel.FuelItemBase;
import net.unorthodox.powerplus.item.upgrades.benk.*;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PowerPlus.MOD_ID);

    public static final DeferredItem<Item> SCANDIUM = ITEMS.registerSimpleItem("scandium");
    public static final DeferredItem<Item> RAW_SCANDIUM = ITEMS.registerSimpleItem("raw_scandium");
    public static final DeferredItem<Item> SAMARIUM = ITEMS.registerSimpleItem("cerium");
    public static final DeferredItem<Item> RAW_SAMARIUM = ITEMS.registerSimpleItem("raw_cerium");
    public static final DeferredItem<Item> EUROPIUM = ITEMS.registerSimpleItem("samarium");
    public static final DeferredItem<Item> RAW_EUROPIUM = ITEMS.registerSimpleItem("raw_samarium");
    public static final DeferredItem<Item> CERIUM = ITEMS.registerSimpleItem("europium");
    public static final DeferredItem<Item> RAW_CERIUM = ITEMS.registerSimpleItem("raw_europium");
    public static final DeferredItem<Item> TERBIUM = ITEMS.registerSimpleItem("terbium");
    public static final DeferredItem<Item> RAW_TERBIUM = ITEMS.registerSimpleItem("raw_terbium");
    public static final DeferredItem<Item> FORGED_SCRAP_INGOT = ITEMS.registerSimpleItem("forged_scrap_ingot");
    public static final DeferredItem<Item> FORGED_SCRAP_DUST = ITEMS.registerSimpleItem("forged_scrap_dust");
    public static final DeferredItem<Item> FORGED_SCRAP = ITEMS.registerSimpleItem("forged_scrap");
    public static final DeferredItem<Item> COPPER_DUST = ITEMS.registerSimpleItem("copper_dust");
    public static final DeferredItem<Item> IRON_DUST = ITEMS.registerSimpleItem("iron_dust");
    public static final DeferredItem<Item> GOLD_DUST = ITEMS.registerSimpleItem("gold_dust");

    //Benk Upgrades
    public static final DeferredItem<Item> UPGRADEBLANK = ITEMS.registerSimpleItem("upgradeblank");
    public static final DeferredItem<Item> ADVANCEDUPGRADEBLANK = ITEMS.registerSimpleItem("advancedupgradeblank");
    public static final DeferredItem<Item> UPGRADETIER1 = ITEMS.register("upgradetier1",() -> new UpgradeTier1Item(new Item.Properties()));
    public static final DeferredItem<Item> UPGRADETIER2 = ITEMS.register("upgradetier2",() -> new UpgradeTier2Item(new Item.Properties()));
    public static final DeferredItem<Item> UPGRADETIER3 = ITEMS.register("upgradetier3",() -> new UpgradeTier3Item(new Item.Properties()));
    public static final DeferredItem<Item> UPGRADETIER4 = ITEMS.register("upgradetier4",() -> new UpgradeTier4Item(new Item.Properties()));
    public static final DeferredItem<Item> UPGRADETIER5 = ITEMS.register("upgradetier5",() -> new UpgradeTier5Item(new Item.Properties()));

    // -- Fuels
    public static final DeferredItem<Item> CINDERCOAL = ITEMS.registerItem("cindercoal", properties -> new FuelItemBase(properties, 16000), new Item.Properties());
    public static final DeferredItem<Item> SMOLDERSTONE = ITEMS.registerItem("smolderstone", properties -> new FuelItemBase(properties, 20000), new Item.Properties());
    public static final DeferredItem<Item> CHARCITE = ITEMS.registerItem("charcite", properties -> new FuelItemBase(properties, 24000), new Item.Properties());
    public static final DeferredItem<Item> VEXCOAL = ITEMS.registerItem("vexcoal", properties -> new FuelItemBase(properties, 28000), new Item.Properties());
    public static final DeferredItem<Item> INFERNITE = ITEMS.registerItem("infernite", properties -> new FuelItemBase(properties, 32000), new Item.Properties());

    public static final DeferredItem<Item> BLIGHTICE = ITEMS.registerItem("blightice", properties -> new FuelItemBase(properties, 20000), new Item.Properties());
    public static final DeferredItem<Item> EMBERFROST = ITEMS.registerItem("emberfrost", properties -> new FuelItemBase(properties, 28000), new Item.Properties());
    public static final DeferredItem<Item> CRYOFLAME = ITEMS.registerItem("cryoflame", properties -> new FuelItemBase(properties, 36000), new Item.Properties());
    public static final DeferredItem<Item> GLACIALEMBER = ITEMS.registerItem("glacialember", properties -> new FuelItemBase(properties, 44000), new Item.Properties());
    public static final DeferredItem<Item> BLAZEFROST = ITEMS.registerItem("blazefrost", properties -> new FuelItemBase(properties, 52000), new Item.Properties());

    // -- Tools
    public static final DeferredItem<Item> WRENCH = ITEMS.registerItem("wrench", properties -> new Item(properties.stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    }
