package si.seraph.opensource.seraphapi.apiwrappers.hypixel.games;

import net.minecraft.entity.player.EntityPlayer;

public interface IHypixelGame {

    void setData(String name);

    String getSidebarName();

    String getFormattedQueueStats();

    void init();
}
