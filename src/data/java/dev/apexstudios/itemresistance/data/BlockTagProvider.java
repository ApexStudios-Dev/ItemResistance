package dev.apexstudios.itemresistance.data;

import dev.apexstudios.apexcore.api.data.DataGenUtil;
import dev.apexstudios.itemresistance.api.ItemResistance;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

final class BlockTagProvider extends TagsProvider<Block> {
    BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.BLOCK, lookupProvider, ItemResistance.ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        DataGenUtil.blockItemTags(BlockItemTagProvider::new, this);

        tag(ItemResistance.EXPLOSION_IMMUNE.block()).addTags(
                BlockTags.DRAGON_IMMUNE,
                BlockTags.DRAGON_TRANSPARENT,
                BlockTags.WITHER_IMMUNE
        );
    }
}
