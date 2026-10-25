package dev.apexstudios.itemresistance.core;

import dev.apexstudios.itemresistance.api.ItemResistance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.ExplosionEvent;

@Mod(ItemResistance.ID)
public final class ItemResistanceEntryPoint {
    public ItemResistanceEntryPoint(IEventBus modBus) {
        ItemResistance.REGISTREE.register(modBus);

        NeoForge.EVENT_BUS.addListener(ExplosionEvent.Start.class, event -> {
            var gameRules = ((ServerLevel) event.getLevel()).getGameRules();
            event.setCanceled(!gameRules.get(ItemResistance.ALLOW_EXPLOSIONS.value()));
        });

        NeoForge.EVENT_BUS.addListener(ExplosionEvent.Detonate.class, event -> {
            var explosion = event.getExplosion();
            var level = (ServerLevel) event.getLevel();
            var itr = event.getAffectedEntities().iterator();

            while(itr.hasNext()) {
                var entity = itr.next();

                if(!(entity instanceof ItemEntity item)) {
                    continue;
                }

                if(ItemResistance.isImmuneToExplosion(item.getItem(), level, item, explosion)) {
                    itr.remove();
                }
            }
        });
    }
}
