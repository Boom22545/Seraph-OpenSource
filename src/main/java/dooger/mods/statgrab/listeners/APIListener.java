package dooger.mods.statgrab.listeners;

import dooger.mods.statgrab.doogerapi.utils.ChatColour;
import dooger.mods.statgrab.doogerapi.utils.ChatUtils;
import dooger.mods.statgrab.doogerapi.utils.ModConfig;
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
