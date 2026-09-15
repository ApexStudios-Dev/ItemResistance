package dev.apexstudios.itemresistance.data;

import dev.apexstudios.itemresistance.ItemResistance;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(value = ItemResistance.ID, dist = Dist.CLIENT)
public final class ItemResistanceDataEntryPoint {
    public ItemResistanceDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> {
            event.createProvider(IRLanguageProvider::new);
            event.createProvider(output -> PackMetadataGenerator.forFeaturePack(output, Component.literal("ItemResistance resources")));
        });
    }
}
