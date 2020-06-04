package si.seraph.mods.opensource.listeners;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import si.seraph.mods.opensource.seraphapi.config.ModConfig;
import si.seraph.mods.opensource.seraphapi.utils.Handler;
import si.seraph.mods.opensource.seraphapi.utils.chat.ChatColour;
import si.seraph.mods.opensource.seraphapi.utils.chat.ChatUtils;

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
