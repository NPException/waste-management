package npe.mc.wastemanagement;

import static npe.mc.wastemanagement.WasteManagement.MOD_ID;
import static npe.mc.wastemanagement.WasteManagement.MOD_NAME;

import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import npe.mc.wastemanagement.common.ModBlocks;
import npe.mc.wastemanagement.proxy.CommonProxy;

@SuppressWarnings("StaticNonFinalField")

@Mod(modid = MOD_ID, name = MOD_NAME, useMetadata = true)
public final class WasteManagement {
	public static final String MOD_ID = "wastemanagement";
	public static final String MOD_NAME = "Waste Management";

	@SidedProxy(clientSide = "npe.mc.wastemanagement.proxy.ClientProxy",
			  serverSide = "npe.mc.wastemanagement.proxy.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance
	public static WasteManagement instance;

	public static Logger logger;

	public static final CreativeTabs TAB = new CreativeTabs(WasteManagement.MOD_ID + ".tab") {
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() {
			return new ItemStack(Item.getItemFromBlock(ModBlocks.singularityUnit));
		}
	};

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);

		MinecraftForge.EVENT_BUS.register(this);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}

	@SubscribeEvent
	public void onConfigChangedEvent(OnConfigChangedEvent event) {
		if (event.getModID().equals(WasteManagement.MOD_ID)) {
			ConfigManager.sync(WasteManagement.MOD_ID, Config.Type.INSTANCE);
		}
	}
}
