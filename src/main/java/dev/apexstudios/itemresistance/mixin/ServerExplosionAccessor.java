package dev.apexstudios.itemresistance.mixin;

import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.ServerExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerExplosion.class)
public interface ServerExplosionAccessor {
    @Accessor("damageCalculator")
    ExplosionDamageCalculator getDamageCalculator();
}
