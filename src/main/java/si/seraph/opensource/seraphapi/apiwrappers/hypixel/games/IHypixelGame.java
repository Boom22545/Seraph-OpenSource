package si.seraph.opensource.seraphapi.apiwrappers.hypixel.games;

import net.minecraft.entity.player.EntityPlayer;

public interface IHypixelGame {

    void setData(String name);

    void setData(EntityPlayer player);

    String getSidebarName();

    String getFormattedStats();

    String getFormattedQueueStats();

    String getFormattedJoinStats(String playerName);

    void init();
}
