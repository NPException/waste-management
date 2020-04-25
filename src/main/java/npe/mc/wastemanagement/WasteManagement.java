package npe.mc.wastemanagement;

import static npe.mc.wastemanagement.WasteManagement.MOD_ID;
import static npe.mc.wastemanagement.WasteManagement.MOD_NAME;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import npe.mc.wastemanagement.proxy.CommonProxy;

@SuppressWarnings("StaticNonFinalField")

@Mod(modid = MOD_ID, name = MOD_NAME, useMetadata = true)
public final class WasteManagement {
	public static final String MOD_ID = "wastemanagement";
	public static final String MOD_NAME = "Waste Management";

	@SidedProxy(clientSide = "npe.mc.wastemanagement.proxy.ClientProxy",
			  serverSide = "npe.mc.wastemanagement.proxy.ServerProxy")
	public static CommonProxy proxy;

	@Mod.Instance
	public static WasteManagement instance;

	public static Logger logger;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);

		System.out.println(Config.isThisAGoodTutorial);
		System.out.println(Config.yourRealName);
		System.out.println(Config.dimensions.spaceDimId);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}
