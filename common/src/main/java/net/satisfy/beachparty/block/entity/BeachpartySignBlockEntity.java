package net.satisfy.beachparty.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.satisfy.beachparty.registry.EntityTypeRegistry;
import org.jetbrains.annotations.NotNull;

public class BeachpartySignBlockEntity extends SignBlockEntity {
    public BeachpartySignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return EntityTypeRegistry.SIGN.get();
    }
}