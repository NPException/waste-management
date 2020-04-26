package npe.mc.wastemanagement.common;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import npe.mc.wastemanagement.common.item.ItemBase;

@SuppressWarnings("StaticNonFinalField")
public final class ModItems {
	// add items here
	public static ItemBase singularityAnalyzer;


	public static void registerItems(IForgeRegistry<Item> registry) {
		registry.registerAll(
				  // instantiate items here (put comma at the start of line to minimize git changes)
				  singularityAnalyzer = new ItemBase("singularity_analyzer")
		);
	}
}
