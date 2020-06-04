package dooger.mods.statgrab.doogerapi.games;

import net.minecraft.entity.player.EntityPlayer;

public interface IHypixelGame {

    @Deprecated
    void setData(String name);

    String getSidebarName();

    void init();
}
