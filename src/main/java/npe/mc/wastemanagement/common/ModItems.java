package npe.mc.wastemanagement.common;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import npe.mc.wastemanagement.common.item.ItemBase;
import npe.mc.wastemanagement.util.ModUtils;

@SuppressWarnings("StaticNonFinalField")
public final class ModItems {
	// add items here
	public static ItemBase singularityAnalyzer = new ItemBase("singularity_analyzer");


	public static void registerItems(IForgeRegistry<Item> registry) {
		ModUtils.publicStaticValues(ModItems.class, Item.class)
				  .forEach(registry::register);
	}
}
