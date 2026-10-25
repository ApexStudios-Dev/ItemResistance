package dev.apexstudios.itemresistance.mixin;

import dev.apexstudios.itemresistance.extensions.BlockStateExplosionExtension;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockState.class)
public abstract class BlockStateMixin implements BlockStateExplosionExtension {
}
