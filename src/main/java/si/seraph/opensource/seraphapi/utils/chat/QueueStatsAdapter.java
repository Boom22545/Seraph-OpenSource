package si.seraph.opensource.seraphapi.utils.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.IHypixelGame;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.bedwars.Bedwars;
import si.seraph.opensource.seraphapi.utils.APIAdapter;
import si.seraph.opensource.seraphapi.utils.Handler;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class QueueStatsAdapter {

    private boolean isOnePlayer;

    @SuppressWarnings("rawtypes")
    protected synchronized void run(IHypixelGame hypixel, String player) {
        Handler.asExecutor(()-> {
            try {
                Thread.sleep(120);
            } catch (InterruptedException ignored) {
            }
            final Collection<EntityPlayer> playerList = Collections.unmodifiableList(Minecraft.getMinecraft().theWorld.playerEntities);
            isOnePlayer = playerList.size() < 2;
            String lPlayer = Minecraft.getMinecraft().thePlayer.getName();
            FutureTask<StringBuilder> futureStringBuilder = new FutureTask<>(isOnePlayer ? ()-> {
                StringBuilder builder = new StringBuilder();
                builder.append(new Bedwars(lPlayer).getFormattedJoinStats(player));
                return builder;
            } : ()-> {
                StringBuilder builder = new StringBuilder();
                FutureTask[] futureTasks = new FutureTask[playerList.size()];
                AtomicInteger ai = new AtomicInteger();
                playerList.parallelStream().forEachOrdered(p -> futureTasks[ai.getAndIncrement()] = new FutureTask<>(()-> new APIAdapter().getData(hypixel, p)));
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
}
