package npe.mc.wastemanagement.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import npe.mc.wastemanagement.common.block.BlockBase;

@SuppressWarnings("StaticNonFinalField")
public final class ModBlocks {
	// add blocks here
	public static BlockBase singularityUnit;

	// registration functionality //

	private static List<BlockBase> blocks;

	public static void registerBlocks(IForgeRegistry<Block> registry) {
		blocks = new ArrayList<>();

		// add blocks here
		blocks.add(singularityUnit = new BlockBase(Material.ROCK, "singularity_unit"));

		for (Block block : blocks) {
			registry.register(block);
		}
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (BlockBase block : blocks) {
			registry.register(block.createItemBlock());
		}
	}
}
