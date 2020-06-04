package dooger.mods.statgrab.doogerapi.games;

import net.minecraft.entity.player.EntityPlayer;

public interface IHypixelGame {

    @Deprecated
    void setData(String name);

    void setData(EntityPlayer player);

    String getPlayerName();

    String getFormattedDecimalStats();

    String getFormattedStats();

    String getSidebarName();

    boolean isCommand();

    void setCommand(boolean b);

    void init();

}
