package npe.mc.wastemanagement.common.item;

import net.minecraft.item.Item;
import npe.mc.wastemanagement.WasteManagement;

public class ItemBase extends Item {

	public ItemBase(String name) {
		setRegistryName(name);
		setUnlocalizedName(WasteManagement.MOD_ID + "." + name);
	}
}
