package si.seraph.opensource.seraphapi.utils;

import net.minecraft.entity.player.EntityPlayer;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.IHypixelGame;
import si.seraph.opensource.seraphapi.utils.chat.ChatColour;

public class APIAdapter {

    public String getData(IHypixelGame hypixel, EntityPlayer player) {
        String string = "";
        try {
            hypixel.setData(player);
            string = hypixel.getFormattedQueueStats();
        } catch (Exception ignored) {
        }
        return string;
    }

}
