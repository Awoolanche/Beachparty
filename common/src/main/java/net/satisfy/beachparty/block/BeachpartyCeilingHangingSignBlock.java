package net.satisfy.beachparty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.satisfy.beachparty.block.entity.BeachpartyHangingSignBlockEntity;

public class BeachpartyCeilingHangingSignBlock extends CeilingHangingSignBlock {
    public BeachpartyCeilingHangingSignBlock(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BeachpartyHangingSignBlockEntity(pos, state);
    }
}