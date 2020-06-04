package dooger.mods.statgrab.listeners;

import dooger.mods.statgrab.doogerapi.utils.Handler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Anti4v4 {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent chat) {
        String umsg = chat.message.getUnformattedText();
        if (umsg.contains("Not enough players joined so the game did not start.")) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/play arena_4v4");
        } else if (umsg.contains("dooguh has joined(") || umsg.contains("ZarkZ has joined(")) {
            Handler.asExecutor(() -> {
                try {
                    Thread.sleep(1500);
                } catch (Exception ignored) {
                }
            });
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/play arena_4v4");
        }
    }

}
