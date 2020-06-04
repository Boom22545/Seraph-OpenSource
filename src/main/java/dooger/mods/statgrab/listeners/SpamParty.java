package dooger.mods.statgrab.listeners;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SpamParty {

    SpamPartyCommand command = new SpamPartyCommand();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent chat) {
        String umsg = chat.message.getUnformattedText();
        String[] args = umsg.split(" ");
        if (command.isEnabled) {
            if (umsg.contains("The party invite to ") && umsg.contains("has expired.")) {
                String playerName = "";
                if (args[5].contains("[")) {
                    playerName = args[6];
                } else {
                    playerName = args[5];
                }
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/p invite " + playerName);
            }
        }
    }

}
