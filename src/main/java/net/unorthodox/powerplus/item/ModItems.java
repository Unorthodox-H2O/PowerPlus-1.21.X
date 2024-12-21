package net.unorthodox.powerplus.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PowerPlus.MOD_ID);

    public static final DeferredItem<Item> SCANDIUM = ITEMS.registerSimpleItem("scandium");


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
