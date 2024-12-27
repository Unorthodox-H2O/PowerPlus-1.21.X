package net.unorthodox.powerplus.block.liquidgen;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;


public class EliteLiquidGenerator extends HorizontalDirectionalBlock {
    public static final MapCodec<EliteLiquidGenerator> CODEC = simpleCodec(EliteLiquidGenerator::new);
    public static final VoxelShape SHAPE = Block.box(2,0,2,16,16,16);
    protected static final VoxelShape COLLISION_SHAPE = Block.box(2,0,2,16,16,16);



    public EliteLiquidGenerator(Properties properties) {
        super(properties);
    }
    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return COLLISION_SHAPE;
    }

    @Override
    protected VoxelShape getShape(BlockState State, BlockGetter Level, BlockPos Pos, CollisionContext Context){
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

}
