package npe.mc.wastemanagement.common.tut.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import npe.mc.wastemanagement.common.block.BlockBase;
import npe.mc.wastemanagement.common.tut.tileentity.DataTileEntity;

public class DataBlock extends BlockBase implements ITileEntityProvider {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", Plane.HORIZONTAL);

	public DataBlock() {
		super(Material.ROCK, "datablock");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new DataTileEntity();
	}

	private static DataTileEntity getTE(World world, BlockPos pos) {
		return (DataTileEntity) world.getTileEntity(pos);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			  EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			// We only count on the server side.

			if (side == state.getValue(FACING)) {
				int counter;
				if (hitY < .5f) {
					counter = getTE(world, pos).decrement();
				} else {
					counter = getTE(world, pos).increment();
				}
				TextComponentTranslation component = new TextComponentTranslation("message.wastemanagement.counter_par", counter);
				component.getStyle().setColor(TextFormatting.GREEN);
				player.sendStatusMessage(component, false);
			}
		}
		// Return true also on the client to make sure that MC knows we handled this and will not try to place
		// a block on the client
		return true;
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		world.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		// Since we only allow horizontal rotation we need only 2 bits for facing. North, South, West, East start at index 2 so we have to add 2 here.
		return getDefaultState().withProperty(FACING, EnumFacing.getFront((meta & 3) + 2));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		// Since we only allow horizontal rotation we need only 2 bits for facing. North, South, West, East start at index 2 so we have to subtract 2 here.
		return state.getValue(FACING).getIndex() - 2;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING);
	}
}
