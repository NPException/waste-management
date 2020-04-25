package npe.mc.wastemanagement;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

@SuppressWarnings({"StaticNonFinalField"})

@Config(modid = WasteManagement.MOD_ID,
		  name = WasteManagement.MOD_NAME)
public final class ModConfig {

	@Comment("Allow to skip the day by sleeping")
	public static boolean skipDayInBed = true;

	@Comment("Number of items required for the small black hole to form")
	public static int smallBlackHoleThreshold = 64;
}
