package si.seraph.mods.opensource.listeners.statlisteners;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import si.seraph.mods.opensource.seraphapi.games.bedwars.Bedwars;
import si.seraph.mods.opensource.seraphapi.utils.GameChecker;
import si.seraph.mods.opensource.seraphapi.utils.Handler;
import si.seraph.mods.opensource.seraphapi.utils.chat.ChatColour;
import si.seraph.mods.opensource.seraphapi.utils.chat.ChatUtils;

public class StatsOnJoin {

    GameChecker gameChecker = new GameChecker();
    private boolean isOnePlayer;

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
                if (umsg.contains(lPlayer)) {
                    Handler.asExecutor(()-> {
                        try {
                            Thread.sleep(120);
                        } catch (InterruptedException ignored) {
                        }
                        final Collection<EntityPlayer> playerList = Collections.unmodifiableList(Minecraft.getMinecraft().theWorld.playerEntities);
                        isOnePlayer = playerList.size() < 2;
                        FutureTask<StringBuilder> futureStringBuilder = new FutureTask<>(isOnePlayer ? ()-> {
                            StringBuilder builder = new StringBuilder();
                            builder.append(new Bedwars(lPlayer).getFormattedJoinStats(player));
                            return builder;
                        } : ()-> {
                            StringBuilder builder = new StringBuilder();
                            FutureTask[] futureTasks = new FutureTask[playerList.size()];
                            AtomicInteger ai = new AtomicInteger();
                            playerList.parallelStream().forEachOrdered(p -> futureTasks[ai.getAndIncrement()] = new FutureTask<>(()-> new Bedwars(p.getName()).getFormattedQueueStats(p.getName())));
                            Handler.asExecutor(futureTasks);
                            int i = 0;
                            while (i < playerList.size()) {
                                builder.append(futureTasks[i].get()).append("\n");
                                i++;
                            }
                            return builder;
                        });
                        Handler.asExecutor(futureStringBuilder);
                        try {
                            ChatUtils.sendMessage(futureStringBuilder.get().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }
                Handler.asExecutor(()-> {
                    String[] args = umsg.split(" ");
                    Bedwars bw = new Bedwars(args[0]);
                    ChatUtils.sendMessage(bw.getFormattedJoinStats(player));
                });
                event.setCanceled(true);
            } else if (msg.contains("§r§e has quit!")) {
                event.setCanceled(true);
                ChatUtils.sendMessage(ChatColour.RED + "- " + player + ChatColour.RED + " quit");
            }
        }
    }

}