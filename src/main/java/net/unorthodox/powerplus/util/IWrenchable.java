package net.unorthodox.powerplus.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.unorthodox.powerplus.item.tools.wrench.Wrench;

public interface IWrenchable {

    default boolean onBlockActivated(Player player, Level world, BlockPos pos, BlockState state, Direction side, float hitX, float hitY, float hitZ) {
        ItemStack heldItem = player.getInventory().getSelected();

        // Check if the held item is your wrench
        if (heldItem.getItem() instanceof Wrench) {
            if (player.isCrouching()) {
                // Handle removal logic
                world.removeBlock(pos, false);
            } else {
                // Handle rotation logic
                if (state.hasProperty(net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING)) {
                    Direction currentDirection = state.getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING);
                    Direction nextDirection = currentDirection.getClockWise();
                    world.setBlock(pos, state.setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING, nextDirection), 3);
                }
            }
            return true;
        }
        return false;
    }
}
