package dev.apexstudios.itemresistance.data;

import dev.apexstudios.itemresistance.api.ItemResistance;
import java.util.function.Function;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.tags.BlockItemTagId;

final class BlockItemTagProvider extends BlockItemTagsProvider {
    BlockItemTagProvider(Function<BlockItemTagId, CombinedAppender> tagSupplier) {
        super(tagSupplier);
    }

    @Override
    public void run() {
        tag(ItemResistance.EXPLOSION_IMMUNE);
    }
}
