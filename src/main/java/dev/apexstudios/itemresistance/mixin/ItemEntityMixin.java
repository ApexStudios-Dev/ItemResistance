package dev.apexstudios.itemresistance.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.apexstudios.itemresistance.ItemResistance;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ServerExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {
    @Shadow public abstract ItemStack getItem();

    @ModifyReturnValue(
            method = "ignoreExplosion",
            at = @At("RETURN")
    )
    private boolean ignoreExplosion(boolean original, @Local(argsOnly = true) Explosion explosion) {
        var self = ItemEntity.class.cast(this);

        if(explosion instanceof ServerExplosion sExplosion && !ItemResistance.shouldExplode(self.blockPosition(), getItem(), sExplosion))
            return true;

        return original;
    }
}
