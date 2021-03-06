package npe.mc.wastemanagement.proxy;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import npe.mc.wastemanagement.WasteManagement;
import npe.mc.wastemanagement.common.ModBlocks;
import npe.mc.wastemanagement.common.ModItems;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		// tut
		OBJLoader.INSTANCE.addDomain(WasteManagement.MOD_ID);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ModBlocks.initModels();
		ModItems.initModels();
	}
}
