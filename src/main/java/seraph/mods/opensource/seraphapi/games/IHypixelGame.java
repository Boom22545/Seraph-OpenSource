package seraph.mods.opensource.seraphapi.games;

public interface IHypixelGame {

    @Deprecated
    void setData(String name);

    String getSidebarName();

    void init();
}