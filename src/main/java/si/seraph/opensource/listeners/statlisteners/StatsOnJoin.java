package si.seraph.opensource.listeners.statlisteners;

import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.bedwars.Bedwars;
import si.seraph.opensource.seraphapi.utils.chat.ChatColour;
import si.seraph.opensource.seraphapi.utils.chat.ChatUtils;
import si.seraph.opensource.seraphapi.utils.GameChecker;
import si.seraph.opensource.seraphapi.utils.Handler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import si.seraph.opensource.seraphapi.utils.chat.QueueStatsAdapter;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsOnJoin extends QueueStatsAdapter {

    GameChecker gameChecker = new GameChecker();
    private boolean isOnePlayer;
    private boolean isActive = false;

    @SuppressWarnings("rawtypes")
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (gameChecker.isBedwars()) {
            String msg = event.message.getFormattedText();
            String umsg = event.message.getUnformattedText();
            String[] joinArgs = msg.split(" ");
            String player = joinArgs[0];
            String lPlayer = Minecraft.getMinecraft().thePlayer.getName();
            if (msg.contains("§r§e has joined (")) {
                if (umsg.contains(lPlayer)) {
                    isActive = false;
                }
                Handler.asExecutor(()-> {
                    String[] args = umsg.split(" ");
                    Bedwars bw = new Bedwars(args[0]);
                    ChatUtils.sendMessage(bw.getFormattedJoinStats(player));
                });
                event.setCanceled(true);
            } else if (msg.contains("§r§e has quit!")) {
                event.setCanceled(true);
                Handler.asExecutor(()-> {
                    ChatUtils.sendMessage(ChatColour.RED + "- " + player + ChatColour.RED + " quit");
                });
            } else if (msg.contains("§eThe game starts in")) {
                if (!isActive) {
                    isActive = true;
                    run(new Bedwars(), player);
                }
            } else if (msg.contains("We don't have enough players! Start cancelled.")) {
                isActive = false;
            }
        }
    }

}