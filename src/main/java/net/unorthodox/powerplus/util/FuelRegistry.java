package net.unorthodox.powerplus.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.unorthodox.powerplus.block.ModBlocks;
import net.unorthodox.powerplus.item.ModItems;

import java.util.HashMap;
import java.util.Map;

public class FuelRegistry {

    // Map to store the fuel and their burn times
    private static final Map<Item, Integer> FUEL_MAP = new HashMap<>();

    /**
     * Registers a fuel item along with its burn time.
     *
     * @param fuel     The fuel item to register.
     * @param burnTime The burn time of the item in ticks.
     */
    public static void registerFuel(Item fuel, int burnTime) {
        FUEL_MAP.put(fuel, burnTime);
    }

    /**
     * Checks if an item is a registered fuel.
     *
     * @param item The item to check.
     * @return True if the item is listed as fuel, false otherwise.
     */
    public static boolean isFuel(Item item) {
        return FUEL_MAP.containsKey(item);
    }

    /**
     * Retrieves the burn time for a specified fuel item.
     *
     * @param item The item to look up.
     * @return The burn time in ticks or 0 if the item is not registered as fuel.
     */
    public static int getBurnTime(Item item) {
        return FUEL_MAP.getOrDefault(item, 0);
    }

    /**
     * Clears all registered fuel data (useful for reloading or debugging).
     */
    public static void clearRegistry() {
        FUEL_MAP.clear();
    }

    /**
     * Registers all the default fuels with their burn times.
     * This method is called to preload the registry with default values.
     */
    public static void registerFuels() {
        registerFuel(ModItems.CINDERCOAL.get(), 16000);
        registerFuel(ModItems.SMOLDERSTONE.get(), 20000);
        registerFuel(ModItems.CHARCITE.get(), 24000);
        registerFuel(ModItems.VEXCOAL.get(), 28000);
        registerFuel(ModItems.INFERNITE.get(), 32000);
        registerFuel(ModItems.BLIGHTICE.get(), 20000);
        registerFuel(ModItems.EMBERFROST.get(), 28000);
        registerFuel(ModItems.CRYOFLAME.get(), 36000);
        registerFuel(ModItems.GLACIALEMBER.get(), 44000);
        registerFuel(ModItems.BLAZEFROST.get(), 52000);
        
        registerFuel((ModBlocks.CHARCOALBLOCK.asItem()), 16000);

    }
}
