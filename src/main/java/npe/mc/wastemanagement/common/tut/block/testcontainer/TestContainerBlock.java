package npe.mc.wastemanagement.common.tut.block.testcontainer;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import npe.mc.wastemanagement.WasteManagement;
import npe.mc.wastemanagement.common.block.BlockBase;
import npe.mc.wastemanagement.common.tut.tileentity.TestContainerTileEntity;

public class TestContainerBlock extends BlockBase implements ITileEntityProvider {

	public static final int GUI_ID = 1;

	public TestContainerBlock() {
		super(Material.ROCK, "testcontainerblock");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TestContainerTileEntity();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		// Only execute on the server
		if (world.isRemote) {
			return true;
		}
		TestContainerTileEntity te = (TestContainerTileEntity) world.getTileEntity(pos);
		if (te == null) {
			return false;
		}
		player.openGui(WasteManagement.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
}
