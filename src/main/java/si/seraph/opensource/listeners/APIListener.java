package si.seraph.opensource.listeners;

import si.seraph.opensource.seraphapi.utils.Handler;
import si.seraph.opensource.seraphapi.utils.chat.ChatColour;
import si.seraph.opensource.seraphapi.utils.chat.ChatUtils;
import si.seraph.opensource.seraphapi.config.ModConfig;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class APIListener {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) {
        String message = e.message.getUnformattedText();
        if (message.startsWith("Your new API key is ")) {
            Handler.asExecutor(()-> {
                String API_KEY = message.replace("Your new API key is ", "");
                ChatUtils.sendMessage(ChatColour.GREEN + "Seraph OpenSource found and set API Key!");
                ModConfig.getInstance().setApiKey(API_KEY);
                ModConfig.getInstance().save();
            });
        }
    }

}
