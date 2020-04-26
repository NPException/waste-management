package npe.mc.wastemanagement.common;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import npe.mc.wastemanagement.common.item.ItemBase;
import npe.mc.wastemanagement.util.ModUtils;

public final class ModItems {
	///////////
	// ITEMS //
	///////////

	public static final ItemBase singularityAnalyzer = new ItemBase("singularity_analyzer");


	/////////////
	// METHODS //
	/////////////

	public static void registerItems(IForgeRegistry<Item> registry) {
		ModUtils.publicStaticValues(ModItems.class, ItemBase.class)
				  .forEach(registry::register);
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		ModUtils.publicStaticValues(ModItems.class, ItemBase.class)
				  .forEach(ItemBase::initModel);
	}
}
