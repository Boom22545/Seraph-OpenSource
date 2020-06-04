package dooger.mods.statgrab.doogerapi.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class ChatUtils {

    public static void sendMessage(String message) {
        try {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColour.translateAlternateColourCodes(message)));
        } catch (Exception ignored) {

        }
    }

    public static void sendMessage(StringBuilder stringBuilder) {
        try {
            ;
            for (String string : stringBuilder.toString().split("\n", 1)) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ChatColour.translateAlternateColourCodes(string)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
