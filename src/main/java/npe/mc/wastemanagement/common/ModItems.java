package npe.mc.wastemanagement.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import npe.mc.wastemanagement.common.item.ItemBase;

@SuppressWarnings("StaticNonFinalField")
public final class ModItems {
	// add items here
	public static ItemBase singularityAnalyzer;

	// registration functionality //

	public static void registerItems(IForgeRegistry<Item> registry) {
		List<Item> items = new ArrayList<>();

		// add items here
		items.add(singularityAnalyzer = new ItemBase("singularity_analyzer"));

		for (Item item : items) {
			registry.register(item);
		}
	}
}
