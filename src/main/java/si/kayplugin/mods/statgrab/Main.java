package si.kayplugin.mods.statgrab;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import si.kayplugin.mods.statgrab.listeners.StatListener;

@Mod(modid = "statgrab", name = "Stat Grab", clientSideOnly = true)
public class Main {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new StatListener());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

}
