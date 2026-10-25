package dev.apexstudios.itemresistance.data;

import dev.apexstudios.apexcore.api.data.DataGenUtil;
import dev.apexstudios.itemresistance.api.ItemResistance;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(value = ItemResistance.ID, dist = Dist.CLIENT)
public final class ItemResistanceDataEntryPoint {
    static final Component PACK_DESC = DataGenUtil.packDescription(ItemResistance.ID);

    public ItemResistanceDataEntryPoint(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> {
            event.createProvider(output -> DataGenUtil.metadata(output, PACK_DESC, PackType.SERVER_DATA));
            event.createProvider(LangProvider::new);
            event.createProvider(BlockTagProvider::new);
            event.createProvider(ItemTagProvider::new);
        });
    }
}
