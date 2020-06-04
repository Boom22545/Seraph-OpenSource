package dooger.mods.statgrab.listeners;

import dooger.mods.statgrab.doogerapi.games.bedwars.Bedwars;
import dooger.mods.statgrab.doogerapi.utils.ChatColour;
import dooger.mods.statgrab.doogerapi.utils.ChatUtils;
import dooger.mods.statgrab.doogerapi.utils.GameChecker;
import dooger.mods.statgrab.doogerapi.utils.Handler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsOnJoin {

    GameChecker gameChecker = new GameChecker();
    boolean truefalse;

    @SuppressWarnings("rawtypes")
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (gameChecker.isBedwars()) {
            String msg = event.message.getFormattedText();
            String umsg = event.message.getUnformattedText();
            String[] joinArgs = msg.split(" ");
            String player = joinArgs[0];
            if (msg.contains("§r§e has joined (")) {
                String lPlayer = Minecraft.getMinecraft().thePlayer.getName();
                event.setCanceled(true);
                if (umsg.contains(lPlayer)) {
                    StringBuilder builder = new StringBuilder();
                    Handler.asExecutor(()-> {
                        
                    });
                }
                Handler.asExecutor(()-> {
                    String[] args = umsg.split(" ");
                    Bedwars bw = new Bedwars(args[0]);
                    ChatUtils.sendMessage(ChatColour.GREEN + "+ " + player + ChatColour.GOLD + " - " + bw.getFormattedJoinStats());
                });
            } else if (msg.contains("§r§e has quit!")) {
                event.setCanceled(true);
                Handler.asExecutor(()-> {
                    ChatUtils.sendMessage(ChatColour.RED + "- " + player + ChatColour.RED + " quit");
                });
            }
        }
    }

}