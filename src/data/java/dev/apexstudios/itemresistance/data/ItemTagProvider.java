package dev.apexstudios.itemresistance.data;

import dev.apexstudios.apexcore.api.data.DataGenUtil;
import dev.apexstudios.itemresistance.api.ItemResistance;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;

final class ItemTagProvider extends TagsProvider<Item> {
    ItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.ITEM, lookupProvider, ItemResistance.ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        DataGenUtil.itemBlockItem(BlockItemTagProvider::new, this);
    }
}
