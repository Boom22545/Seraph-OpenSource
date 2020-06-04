package dooger.mods.statgrab.listeners;

import dooger.mods.statgrab.doogerapi.games.bedwars.Bedwars;
import dooger.mods.statgrab.doogerapi.utils.ChatColour;
import dooger.mods.statgrab.doogerapi.utils.ChatUtils;
import dooger.mods.statgrab.doogerapi.utils.GameChecker;
import dooger.mods.statgrab.doogerapi.utils.Handler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StatsOnJoin {

    GameChecker gameChecker = new GameChecker();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (gameChecker.isBedwars()) {
            String msg = event.message.getFormattedText();
            String umsg = event.message.getUnformattedText();
            String[] joinArgs = msg.split(" ");
            String player = joinArgs[0];
            if (msg.contains("§r§e has joined (")) {
                event.setCanceled(true);
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