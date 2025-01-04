package net.unorthodox.powerplus.block.entity.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.BlastFurnaceMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.unorthodox.powerplus.block.entity.ModBlockEntities;

public class CoalGrinderBlockEntity {
    public CoalGrinderBlockEntity(BlockPos pos, BlockState blockState) {
        super();
    }

    protected Component getDefaultName() {
        return Component.translatable("container.coal_grinder");
    }

    protected int getBurnDuration(ItemStack fuel) {
        return super.getBurnDuration(fuel) / 2;
    }

    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new BlastFurnaceMenu(id, player, this,this.dataAccess);
    }
}