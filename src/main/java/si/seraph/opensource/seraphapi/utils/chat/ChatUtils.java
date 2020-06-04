package si.seraph.opensource.seraphapi.utils.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import si.seraph.opensource.seraphapi.utils.chat.ChatColour;

public class ChatUtils {

    public static void sendMessage(String message) {
        try {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColour.translateAlternateColourCodes(message)));
        } catch (Exception ignored) {

        }
    }
}
