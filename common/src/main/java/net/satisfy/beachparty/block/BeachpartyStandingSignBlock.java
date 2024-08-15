package net.satisfy.beachparty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.satisfy.beachparty.block.entity.BeachpartySignBlockEntity;
import org.jetbrains.annotations.NotNull;

public class BeachpartyStandingSignBlock extends StandingSignBlock {
    public BeachpartyStandingSignBlock(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new BeachpartySignBlockEntity(pos, state);
    }
}