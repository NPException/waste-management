package npe.mc.wastemanagement.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import npe.mc.wastemanagement.WasteManagement;

public class ItemBase extends Item {

	public ItemBase(String name) {
		setRegistryName(name);
		setUnlocalizedName(WasteManagement.MOD_ID + "." + name);
		setCreativeTab(WasteManagement.TAB);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		//noinspection ConstantConditions
		ModelResourceLocation resLoc = new ModelResourceLocation(getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(this, 0, resLoc);
	}
}
