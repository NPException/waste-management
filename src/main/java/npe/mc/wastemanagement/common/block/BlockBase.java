package npe.mc.wastemanagement.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block {

	public BlockBase(Material material, String name) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	@SuppressWarnings("ConstantConditions")
	public Item createItemBlock() {
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}

	@SuppressWarnings("NullableProblems")
	@Override
	public BlockBase setCreativeTab(CreativeTabs tab) {
		super.setCreativeTab(tab);
		return this;
	}
}
