package si.seraph.opensource;

import si.seraph.opensource.seraphapi.config.ModConfig;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import si.seraph.opensource.listeners.APIListener;
import si.seraph.opensource.listeners.statlisteners.StatsOnJoin;

import java.util.Arrays;

@Mod(modid = "seraphopensource", name = "Seraph OpenSource", clientSideOnly = true)
public class Main {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModConfig.getInstance().init();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        registerListeners(new APIListener(), new StatsOnJoin());
    }

    private void registerCommands(ICommand... command) {
        Arrays.stream(command).parallel().forEachOrdered(ClientCommandHandler.instance::registerCommand);
    }

    private void registerListeners(Object... objects) {
        for (Object o : objects) {
            MinecraftForge.EVENT_BUS.register(o);
        }
    }

}
