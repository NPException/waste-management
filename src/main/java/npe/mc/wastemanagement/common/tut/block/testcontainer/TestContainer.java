package npe.mc.wastemanagement.common.tut.block.testcontainer;

import java.util.Objects;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import npe.mc.wastemanagement.common.tut.tileentity.TestContainerTileEntity;

public class TestContainer extends Container {

	private final TestContainerTileEntity te;

	public TestContainer(IInventory playerInventory, TestContainerTileEntity te) {
		this.te = te;

		// This container references items out of our own inventory (the 9 slots we hold ourselves)
		// as well as the slots from the player inventory so that the user can transfer items between
		// both inventories. The two calls below make sure that slots are defined for both inventories.
		addOwnSlots();
		addPlayerSlots(playerInventory);
	}

	private void addPlayerSlots(IInventory playerInventory) {
		// Slots for the main inventory
		for (int row = 0; row < 3; ++row) {
			for (int col = 0; col < 9; ++col) {
				int x = 10 + col * 18;
				int y = row * 18 + 70;
				this.addSlotToContainer(new Slot(playerInventory, row * 9 + col + 10, x, y));
			}
		}
		// Slots for the hotbar
		for (int col = 0; col < 9; ++col) {
			int x = 10 + col * 18;
			int y = 58 + 70;
			this.addSlotToContainer(new Slot(playerInventory, col, x, y));
		}
	}

	private void addOwnSlots() {
		IItemHandler itemHandler = Objects.requireNonNull(this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null));
		// Add our own slots
		for (int col = 0; col < itemHandler.getSlots(); col++) {
			int x = 10 + col * 18;
			int y = 6;
			addSlotToContainer(new SlotItemHandler(itemHandler, col, x , y));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < TestContainerTileEntity.SIZE) {
				if (!this.mergeItemStack(itemstack1, TestContainerTileEntity.SIZE, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, TestContainerTileEntity.SIZE, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.canInteractWith(playerIn);
	}
}
