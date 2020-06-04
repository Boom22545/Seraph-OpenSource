package si.seraph.opensource.listeners;

import si.seraph.opensource.seraphapi.utils.ChatColour;
import si.seraph.opensource.seraphapi.utils.ChatUtils;
import si.seraph.opensource.seraphapi.utils.ModConfig;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class APIListener {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) {
        String message = e.message.getUnformattedText();
        if (message.startsWith("Your new API key is ")) {
            String API_KEY = message.replace("Your new API key is ", "");
            ChatUtils.sendMessage(ChatColour.GREEN + "API Key set!");
            ModConfig.getInstance().setApiKey(API_KEY);
            ModConfig.getInstance().save();
        }
    }

}