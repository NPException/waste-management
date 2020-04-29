package npe.mc.wastemanagement.common;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import npe.mc.wastemanagement.WasteManagement;
import npe.mc.wastemanagement.common.block.BlockBase;
import npe.mc.wastemanagement.common.tut.block.BlinkingBlock;
import npe.mc.wastemanagement.common.tut.block.DataBlock;
import npe.mc.wastemanagement.common.tut.tileentity.BlinkingTileEntity;
import npe.mc.wastemanagement.common.tut.tileentity.DataTileEntity;
import npe.mc.wastemanagement.util.ModUtils;

public final class ModBlocks {
	////////////
	// BLOCKS //
	////////////

	public static final BlockBase singularityUnit = new BlockBase(Material.ROCK, "singularity_unit");

	// tutorial blocks
	public static final DataBlock dataBlock = new DataBlock();
	public static final BlinkingBlock blinkingBlock = new BlinkingBlock();


	///////////////////
	// TILE ENTITIES //
	///////////////////

	private static final List<Class<? extends TileEntity>> tileClasses = Arrays.asList(
			  BlinkingTileEntity.class,
			  DataTileEntity.class
	);


	/////////////
	// METHODS //
	/////////////

	private static void registerTileClass(Class<? extends TileEntity> tileClass) {
		GameRegistry.registerTileEntity(tileClass,
				  new ResourceLocation(WasteManagement.MOD_ID,
							 "tile_" + tileClass.getSimpleName().toLowerCase()));
	}

	public static void registerBlocks(IForgeRegistry<Block> registry) {
		ModUtils.publicStaticValues(ModBlocks.class, BlockBase.class)
				  .forEach(registry::register);
		tileClasses.forEach(ModBlocks::registerTileClass);
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		for (BlockBase block : ModUtils.publicStaticValues(ModBlocks.class, BlockBase.class)) {
			//noinspection ConstantConditions
			registry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		ModUtils.publicStaticValues(ModBlocks.class, BlockBase.class)
				  .forEach(BlockBase::initModel);
	}
}
