package net.unorthodox.powerplus.block.base.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.unorthodox.powerplus.block.entity.basebe.AreaAffectingBE;
import net.unorthodox.powerplus.block.entity.basebe.BaseMachineBE;
import net.unorthodox.powerplus.item.tools.wrench.Wrench;

import javax.annotation.Nullable;


public abstract class BaseMachineBlock extends Block implements EntityBlock {

    protected static final DirectionProperty FACING = BlockStateProperties.FACING;

    public BaseMachineBlock(Properties properties) {
        super(properties);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) {
            return (lvl, pos, blockState, t) -> {
                if (t instanceof BaseMachineBE tile) {
                    tile.tickClient();
                }
            };
        }
        return null;
        }

    public void neighborChanged(BlockState blockState, Level level, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(blockState, level, pos, blockIn, fromPos, isMoving);
        BlockEntity blockEntity = level.getBlockEntity(pos);

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
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
    @Override
    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult hit) {
        if (level.isClientSide)
            return InteractionResult.SUCCESS;

        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() instanceof Wrench)
            return InteractionResult.PASS;

        BlockEntity te = level.getBlockEntity(blockPos);
        if (!isValidBE(te))
            return InteractionResult.FAIL;

        openMenu(player, blockPos);

        return InteractionResult.SUCCESS;
    }

    public abstract void openMenu(Player player, BlockPos blockPos);

    public abstract boolean isValidBE(BlockEntity blockEntity);

}
