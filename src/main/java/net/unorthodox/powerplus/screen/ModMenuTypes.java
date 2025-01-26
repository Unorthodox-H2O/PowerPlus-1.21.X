package net.unorthodox.powerplus.screen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.screen.cells.CellMenu;
import net.unorthodox.powerplus.screen.machines.crystalinfuser.CrystalInfuserMenu;
import net.unorthodox.powerplus.screen.machines.grinder.GrinderMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, PowerPlus.MOD_ID);

    //public static final DeferredHolder<MenuType<?>, MenuType<PedestalMenu>> PEDESTAL_MENU =
      //      registerMenuType("pedestal_menu", PedestalMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<CrystalInfuserMenu>> CRYSTALINFUSER_MENU =
            registerMenuType("crystal_infuser_menu", CrystalInfuserMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<GrinderMenu>> GRINDER_MENU =
            registerMenuType("grinder_menu", GrinderMenu::new);

    public static final DeferredHolder<MenuType<?>, MenuType<CellMenu>> CELL_MENU =
            registerMenuType("cell_menu", CellMenu::new);


    private static <T extends AbstractContainerMenu> DeferredHolder<MenuType<?>, MenuType<T>> registerMenuType(String name,
                                                                                                               IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}