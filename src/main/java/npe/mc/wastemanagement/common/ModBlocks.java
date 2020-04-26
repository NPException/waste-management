package npe.mc.wastemanagement.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import npe.mc.wastemanagement.common.block.BlockBase;
import npe.mc.wastemanagement.util.ModUtils;

public final class ModBlocks {
	////////////
	// BLOCKS //
	////////////

	public static final BlockBase singularityUnit = new BlockBase(Material.ROCK, "singularity_unit");


	/////////////
	// METHODS //
	/////////////

	public static void registerBlocks(IForgeRegistry<Block> registry) {
		ModUtils.publicStaticValues(ModBlocks.class, BlockBase.class)
				  .forEach(registry::register);
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		//noinspection ConstantConditions
		ModUtils.publicStaticValues(ModBlocks.class, BlockBase.class)
				  .stream()
				  .map(block -> new ItemBlock(block).setRegistryName(block.getRegistryName()))
				  .forEach(registry::register);
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		ModUtils.publicStaticValues(ModBlocks.class, BlockBase.class)
				  .forEach(BlockBase::initModel);
	}
}
