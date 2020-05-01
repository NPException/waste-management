package npe.mc.wastemanagement.client.tut.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import npe.mc.wastemanagement.WasteManagement;
import npe.mc.wastemanagement.common.tut.block.testcontainer.TestContainer;
import npe.mc.wastemanagement.common.tut.tileentity.TestContainerTileEntity;

@SideOnly(Side.CLIENT)
public class TestContainerGui extends GuiContainer {
	public static final int WIDTH = 180;
	public static final int HEIGHT = 152;

	private static final ResourceLocation background = new ResourceLocation(WasteManagement.MOD_ID, "textures/gui/tut/testcontainer.png");

	public TestContainerGui(TestContainerTileEntity tileEntity, TestContainer container) {
		super(container);
		xSize = WIDTH;
		ySize = HEIGHT;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		mc.getTextureManager().bindTexture(background);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		super.renderHoveredToolTip(mouseX, mouseY);
	}
}
