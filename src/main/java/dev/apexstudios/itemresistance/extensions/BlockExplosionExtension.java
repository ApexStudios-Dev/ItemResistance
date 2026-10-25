package dev.apexstudios.itemresistance.extensions;

import com.google.errorprone.annotations.ForOverride;
import dev.apexstudios.itemresistance.api.ItemResistance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface BlockExplosionExtension {
    /// See [BlockStateExplosionExtension#isImmuneToExplosion$ItemResistance(ServerLevel, ItemEntity, ServerExplosion)]
    @ForOverride
    default boolean isImmuneToExplosion$ItemResistance(BlockState blockState, ServerLevel level, ItemEntity entity, ServerExplosion explosion) {
        // tagged as explosion immune
        if(blockState.is(ItemResistance.EXPLOSION_IMMUNE.block())) {
            return true;
        }

        var pos = entity.blockPosition();

        // neo extension deems this explosion immune
        if(!blockState.canDropFromExplosion(level, pos, explosion)) {
            return true;
        }

        // default to explosion resistance immunity
        // logic pulled from `ServerExplosion#calculateExplodedPositions`
        // refactored for 1 position and not assuming block exists in world
        var remainingPower = explosion.radius() * (.7F + level.getRandom().nextFloat() * .6F);
        var resistance = explosion.damageCalculator.getBlockExplosionResistance(explosion, level, pos, blockState, blockState.getFluidState());

        if(resistance.isPresent()) {
            remainingPower -= (resistance.get() + .3F) * 3F;
        }

        if(remainingPower > 0F && explosion.damageCalculator.shouldBlockExplode(explosion, level, pos, blockState, remainingPower)) {
            return false;
        }

        return true;
    }

    private Block self() {
        return (Block) this;
    }
}
