package dev.apexstudios.itemresistance.extensions;

import com.google.errorprone.annotations.ForOverride;
import dev.apexstudios.itemresistance.api.ItemResistance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerExplosion;

public interface ItemExplosionExtension {
    /// See [ItemStackExplosionExtension#isImmuneToExplosion$ItemResistance(ServerLevel, ItemEntity, ServerExplosion)]
    @ForOverride
    default boolean isImmuneToExplosion$ItemResistance(ItemStack stack, ServerLevel level, ItemEntity entity, ServerExplosion explosion) {
        // tagged as explosion immune
        if(stack.is(ItemResistance.EXPLOSION_IMMUNE.item())) {
            return true;
        }

        return false;
    }

    private Item self() {
        return (Item) this;
    }
}
