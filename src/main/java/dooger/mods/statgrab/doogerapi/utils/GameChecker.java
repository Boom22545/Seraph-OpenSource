package dooger.mods.statgrab.doogerapi.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;

public class GameChecker {

    public static GameChecker getInstance() {
        return new GameChecker();
    }

    public String getSidebar() {
        try {
            if (!(Minecraft.getMinecraft().theWorld.getScoreboard().getObjectiveInDisplaySlot(1).getDisplayName() == null)) {
                ScoreObjective sidebar = Minecraft.getMinecraft().theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
                if (sidebar != null) {
                    return sidebar.getDisplayName().replace("§f§l", "").replace("§l", "").replace("§6", "").replace("§e", "");
                }
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    public boolean isBedwars() {
        if (getSidebar() != null) {
            return getSidebar().equalsIgnoreCase("BED WARS");
        }
        return false;
    }

    public boolean isDuels() {
        if (getSidebar() != null) {
            return getSidebar().contains("DUELS");
        }
        return false;
    }

    public boolean isSkywars() {
        if (getSidebar() != null) {
            return getSidebar().equalsIgnoreCase("SKYWARS");
        }
        return false;
    }

}
