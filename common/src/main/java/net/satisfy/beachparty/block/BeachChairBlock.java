package net.satisfy.beachparty.block;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.satisfy.beachparty.util.BeachpartyUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class BeachChairBlock extends HorizontalDirectionalBlock {

    public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;

    private static final Supplier<VoxelShape> headShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0.875, 0, 0, 1, 1, 1));
        shape = Shapes.or(shape, Shapes.box(0.874375, 0.5, 0, 0.936875, 1.5, 0.875));
        shape = Shapes.or(shape, Shapes.box(0, 0, 0, 0.875, 0.375, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0, 0.375, 0, 0.875, 0.5, 0.875));
        shape = Shapes.or(shape, Shapes.box(0, 1.5, 0, 0.9375, 1.6875, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0, 1.4375, 0.9375, 0.875, 1.625, 1.0625));
        return shape;
    };

    public static final Map<Direction, VoxelShape> HEAD_SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.EAST, direction, headShapeSupplier.get()));
        }
    });

    private static final Supplier<VoxelShape> foodShapeSupplier = () -> {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.or(shape, Shapes.box(0, 0, 0, 0.125, 1, 1));
        shape = Shapes.or(shape, Shapes.box(0.063125, 0.5, 0, 0.125625, 1.5, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.125, 0, 0, 1, 0.375, 0.8125));
        shape = Shapes.or(shape, Shapes.box(0.125, 0.375, 0, 1, 0.5, 0.875));
        shape = Shapes.or(shape, Shapes.box(0.0625, 1.5, 0, 1, 1.6875, 0.9375));
        shape = Shapes.or(shape, Shapes.box(0.125, 1.4375, 0.9375, 1, 1.625, 1.0625));
        return shape;
    };

    public static final Map<Direction, VoxelShape> FOOD_SHAPE = Util.make(new HashMap<>(), map -> {
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            map.put(direction, BeachpartyUtil.rotateShape(Direction.EAST, direction, foodShapeSupplier.get()));
        }
    });


    public @NotNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        return state.getValue(PART) == BedPart.HEAD ? HEAD_SHAPE.get(direction) : FOOD_SHAPE.get(direction);
    }

    public BeachChairBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(PART, BedPart.FOOT));
    }

    public @NotNull BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (direction == getDirectionTowardsOtherPart(state.getValue(PART), state.getValue(FACING))) {
            return neighborState.is(this) && neighborState.getValue(PART) != state.getValue(PART) ? state : Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
        }
    }

    private static Direction getDirectionTowardsOtherPart(BedPart part, Direction direction) {
        return part == BedPart.FOOT ? direction : direction.getOpposite();
    }

    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (!world.isClientSide && player.isCreative()) {
            removeOtherPart(world, pos, state, player);
        }

        super.playerWillDestroy(world, pos, state, player);
    }


    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction direction = ctx.getHorizontalDirection().getClockWise();
        BlockPos blockPos = ctx.getClickedPos();
        BlockPos blockPos2 = blockPos.relative(direction);
        Level world = ctx.getLevel();
        return world.getBlockState(blockPos2).canBeReplaced(ctx) && world.getWorldBorder().isWithinBounds(blockPos2) ? this.defaultBlockState().setValue(FACING, direction) : null;
    }

    public static Direction getOppositePartDirection(BlockState state) {
        Direction direction = state.getValue(FACING);
        return state.getValue(PART) == BedPart.HEAD ? direction.getOpposite() : direction;
    }

    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.setPlacedBy(world, pos, state, placer, itemStack);
        if (!world.isClientSide) {
            placeOtherPart(world, pos, state);
        }
    }

    @Override
    public @NotNull InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand
            hand, BlockHitResult hit) {
        return BeachpartyUtil.onUse(world, player, hand, hit, 0);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART);
    }

    private void placeOtherPart(Level world, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.relative(state.getValue(FACING));
        world.setBlock(blockPos, state.setValue(PART, BedPart.HEAD), Block.UPDATE_ALL);
        world.blockUpdated(pos, Blocks.AIR);
        state.updateNeighbourShapes(world, pos, Block.UPDATE_ALL);
    }

    private void removeOtherPart(Level world, BlockPos pos, BlockState state, Player player) {
        BedPart bedPart = state.getValue(PART);
        if (bedPart == BedPart.FOOT) {
            BlockPos blockPos = pos.relative(getDirectionTowardsOtherPart(bedPart, state.getValue(FACING)));
            BlockState blockState = world.getBlockState(blockPos);
            if (blockState.is(this) && blockState.getValue(PART) == BedPart.HEAD) {
                world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
                if (player != null)
                    world.levelEvent(player, LevelEvent.PARTICLES_DESTROY_BLOCK, blockPos, Block.getId(blockState));
            }
        }
    }
}