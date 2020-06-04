package si.seraph.opensource.mods;

import java.util.Arrays;

import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import si.seraph.opensource.mods.commands.configcommands.SetAPI;
import si.seraph.opensource.mods.commands.statcommands.BedwarStats;
import si.seraph.opensource.mods.listeners.APIListener;
import si.seraph.opensource.seraphapi.config.ModConfig;

@Mod(modid = "seraphopensource", name = "Seraph OpenSource", clientSideOnly = true)
public class Main {

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModConfig.getInstance().init();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		registerCommands(new SetAPI(), new BedwarStats());
		registerListeners(new APIListener());
	}

	private void registerCommands(ICommand... command) {
		Arrays.stream(command).parallel().forEachOrdered(ClientCommandHandler.instance::registerCommand);
	}

	private void registerListeners(Object... objects) {
		Arrays.stream(objects).forEachOrdered(MinecraftForge.EVENT_BUS::register);
	}

}
