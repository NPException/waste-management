package npe.mc.wastemanagement.common;

import static npe.mc.wastemanagement.util.Delay.delay;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import npe.mc.wastemanagement.common.block.BlockBase;
import npe.mc.wastemanagement.util.Delay;

@SuppressWarnings("StaticNonFinalField")
public final class ModBlocks {
	// add blocks here
	public static BlockBase singularityUnit;


	private static final Delay<List<BlockBase>> blocks = delay(() -> Arrays.asList(
			  // instantiate blocks here (put comma at the start of line to minimize git changes)
			  singularityUnit = new BlockBase(Material.ROCK, "singularity_unit")
	));


	public static void registerBlocks(IForgeRegistry<Block> registry) {
		blocks.get().forEach(registry::register);
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		blocks.get()
				  .stream()
				  .map(BlockBase::createItemBlock)
				  .forEach(registry::register);
	}
}
