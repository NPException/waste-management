package npe.mc.wastemanagement.common.item;

import net.minecraft.item.Item;

public class ItemBase extends Item {

	public ItemBase(String name) {
		setRegistryName(name);
		setUnlocalizedName(name);
	}
}
