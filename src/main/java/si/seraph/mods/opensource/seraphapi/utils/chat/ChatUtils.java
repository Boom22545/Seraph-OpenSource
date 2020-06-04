package si.seraph.mods.opensource.seraphapi.utils.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ChatUtils {

    public static void sendMessage(String message) {
        try {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColour.translateAlternateColourCodes(message)));
        } catch (Exception ignored) {

        }
    }
}
