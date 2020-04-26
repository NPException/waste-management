package npe.mc.wastemanagement.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import npe.mc.wastemanagement.common.block.BlockBase;
import npe.mc.wastemanagement.util.ModUtils;

public final class ModBlocks {
	// add blocks here
	public static final BlockBase singularityUnit = new BlockBase(Material.ROCK, "singularity_unit");


	public static void registerBlocks(IForgeRegistry<Block> registry) {
		ModUtils.publicStaticValues(ModBlocks.class, BlockBase.class)
				  .forEach(registry::register);
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		ModUtils.publicStaticValues(ModBlocks.class, BlockBase.class)
				  .map(BlockBase::createItemBlock)
				  .forEach(registry::register);
	}
}
