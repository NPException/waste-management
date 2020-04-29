package npe.mc.wastemanagement.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import npe.mc.wastemanagement.WasteManagement;

public class BlockBase extends Block {

	public BlockBase(Material material, String name) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(WasteManagement.MOD_ID + "." + name);
		setCreativeTab(WasteManagement.TAB);
	}

	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}
