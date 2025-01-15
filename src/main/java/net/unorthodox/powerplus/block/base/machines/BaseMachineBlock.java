package net.unorthodox.powerplus.block.base.machines;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.unorthodox.powerplus.block.entity.basebe.AreaAffectingBE;


public abstract class BaseMachineBlock extends Block implements EntityBlock {

    protected static final DirectionProperty FACING = BlockStateProperties.FACING;

    public BaseMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    public BlockState rRotate(BlockState blockState, LevelAccessor level, BlockPos pos, Rotation rotation) {
        BlockState newState = rRotate(blockState, rotation);
        if (level.getBlockEntity(pos) instanceof AreaAffectingBE areaAffectingBE) {
            areaAffectingBE.handleRotate(
                    blockState.getValue(FACING),
                    newState.getValue(FACING)
            );
        }
        return newState;
    }

    public BlockState rRotate(BlockState state, Rotation rotation) {
        Direction currentDir = state.getValue(FACING);
        Direction nextDir = rotation.rotate(currentDir);
        return state.setValue(FACING, nextDir);
    }
}
