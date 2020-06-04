package si.seraph.mods.opensource.seraphapi.games.bedwars;

import java.text.MessageFormat;

import com.google.gson.JsonObject;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Scoreboard;

import si.seraph.mods.opensource.seraphapi.exceptions.ApiReturnedUnSuccessfulException;
import si.seraph.mods.opensource.seraphapi.exceptions.InvalidKeyException;
import si.seraph.mods.opensource.seraphapi.exceptions.NullJSONFileException;
import si.seraph.mods.opensource.seraphapi.exceptions.TooManyHypixelRequestsException;
import si.seraph.mods.opensource.seraphapi.games.IHypixelGame;
import si.seraph.mods.opensource.seraphapi.utils.chat.ChatColour;
import si.seraph.mods.opensource.seraphapi.utils.chat.ChatUtils;

public final class Bedwars extends BedwarsUtils implements IHypixelGame {

    private boolean isNicked;
    private final boolean bedwarsStatsCommand;
    private JsonObject bedwarJsonObject, achievementJsonObject;
    private Scoreboard scoreboard;
    /* KEEP CHAT COLOUR RESET AFTER! */
    private final String queuePrefix = ChatColour.YELLOW + "*" + ChatColour.RESET;
    private final String separator = ChatColour.GOLD + "-" + ChatColour.RESET;
    private final String joinPrefix = ChatColour.GREEN + "+" + ChatColour.RESET;

    public Bedwars(String name) {
        setData(name);
        bedwarsStatsCommand = false;
    }

    public void setData(String name) {
        isNicked = false;
        boolean isFunctional = false;
        try {
            setWholeObject(getApi(name));
            isFunctional = true;
        } catch (TooManyHypixelRequestsException e) {
            ChatUtils.sendMessage("Too Many Requests!");
        } catch (ApiReturnedUnSuccessfulException e) {
            ChatUtils.sendMessage("The api returned not successful, cause: " + e);
        } catch (NullJSONFileException e) {
            isNicked = true;
        } catch (InvalidKeyException e) {
            ChatUtils.sendMessage("Invalid API Key!");
        } catch (NullPointerException e) {
            System.out.println("setData");
            e.printStackTrace();
        }
        try {
            if (!isNicked && isFunctional) {
                this.bedwarJsonObject = getBedwarsJSON(getWholeObject());
                this.achievementJsonObject = getAchievementJSON(getWholeObject());
                setPlayerData();
                scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
            } else {
                this.playerName = name;
            }
        } catch (NullPointerException e) {
            if (!isNicked) {
                System.out.println("Maybe they have never played bedwars before");
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    private JsonObject getObject() {
        return bedwarJsonObject;
    }
    private JsonObject getAchievementObject() { return achievementJsonObject; }

    public int getFinalKills() {
        try {
            return Integer.parseInt(getObject().get("final_kills_bedwars").toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public int getFinalDeaths() {
        try {
            return Integer.parseInt(getObject().get("final_deaths_bedwars").toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public int getWinstreak() {
        try {
            return Integer.parseInt(getObject().get("winstreak").toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public int getBedwarStars() {
        try {
            return Integer.parseInt(getAchievementObject().get("bedwars_level").toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public int getWins() {
        try {
           return Integer.parseInt(getObject().get("wins_bedwars").toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public int getLosses() {
        try {
            return Integer.parseInt(getObject().get("losses_bedwars").toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public int getBedsLost() {
        try {
            return Integer.parseInt(getObject().get("beds_lost_bedwars").toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public int getBedsBroken() {
        try {
            return Integer.parseInt(getObject().get("beds_broken_bedwars").toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public String getFormattedStats() {
        if (!isNicked) {
            return MessageFormat.format("{0} {1} {2} {3}, {4}, {5}, {6}", getRankColourWithPrefix(), separator, starColor(getBedwarStars()), wsColor(getWinstreak()), fkdrColorDouble(fkdRatioDouble(this)), wlrColorDouble(wlRatioDouble(this)), bblrColorDouble(bblRatioDouble(this)));
        }
        return ChatColour.RED + " is Nicked!";
    }

    public String getFormattedJoinStats(String playerName) {
        if (!isNicked) {
            return MessageFormat.format("{0} {1} {2} {3}, {4}, {5}, {6}, {7}", joinPrefix, playerName, separator, starColor(getBedwarStars()), wsColor(getWinstreak()), fkdrColorDouble(fkdRatioDouble(this)), wlrColorDouble(wlRatioDouble(this)), bblrColorDouble(bblRatioDouble(this)));
        }
        return ChatColour.RED + " is Nicked!";
    }

    public String getFormattedQueueStats(String playerName) {
        if (!isNicked) {
            return MessageFormat.format("{0} {1} {2} {3}, {4}, {5}, {6}, {7}", queuePrefix, ChatColour.GRAY + playerName, separator, starColor(getBedwarStars()), wsColor(getWinstreak()), fkdrColorDouble(fkdRatioDouble(this)), wlrColorDouble(wlRatioDouble(this)), bblrColorDouble(bblRatioDouble(this)));
        }
        return ChatColour.RED + " is Nicked!";
    }

    public String getSidebarName() {
        return "BED WARS";
    }

    public void init() {}

}
