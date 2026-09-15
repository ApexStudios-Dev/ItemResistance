package dev.apexstudios.itemresistance.data;

import dev.apexstudios.itemresistance.ItemResistance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(value = ItemResistance.ID, dist = Dist.CLIENT)
public final class ItemResistanceDataEntryPoint {
    public ItemResistanceDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> event.createProvider(IRLanguageProvider::new));
    }
}
