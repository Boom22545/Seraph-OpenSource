package si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.bedwars;

import com.google.gson.JsonObject;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.exceptions.*;
import si.seraph.opensource.seraphapi.utils.chat.ChatColour;
import si.seraph.opensource.seraphapi.utils.chat.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Scoreboard;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.IHypixelGame;

import java.text.MessageFormat;

public final class Bedwars extends BedwarsUtils implements IHypixelGame {

    private boolean isNicked, bedwarsStatsCommand, notReal;
    private JsonObject bedwarJsonObject, achievementJsonObject;
    private Scoreboard scoreboard;
    /* KEEP CHAT COLOUR RESET AFTER! */
    private String queuePrefix = ChatColour.LIGHT_PURPLE + "*" + ChatColour.RESET, separator = ChatColour.GOLD + "-" + ChatColour.RESET;
    private String joinPrefix = ChatColour.GREEN + "+" + ChatColour.RESET;

    public Bedwars(String name) {
        setData(name);
        bedwarsStatsCommand = false;
    }

    public Bedwars() {
        bedwarsStatsCommand = false;
    }

    public void setData(String name) {
        isNicked = false;
        notReal = false;
        boolean isFunctional = false;
        try {
            setWholeObject(getApi(name));
            isFunctional = true;
        } catch (TooManyHypixelRequestsException e) {
            ChatUtils.sendMessage("Too Many Requests!");
        } catch (ApiReturnedUnSuccessfulException e) {
            ChatUtils.sendMessage("The api returned not successful, cause: " + e);
        } catch (NullJSONFileException e) {
            notReal = true;
        } catch (InvalidKeyException e) {
            ChatUtils.sendMessage("Invalid API Key!");
        } catch (NullPointerException e) {
            System.out.println("setData");
            e.printStackTrace();
        } catch (PlayerReturnedNullException e) {
            isNicked = true;
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
            if (!notReal) {
                return MessageFormat.format("{0} {1} - {2}, {3}, {4}, {5}", starColor(getBedwarStars()), getRankColourWithPrefix(), wsColor(getWinstreak()), fkdrColorDouble(fkdRatioDouble(this)), wlrColorDouble(wlRatioDouble(this)), bblrColorDouble(bblRatioDouble(this)));
            } else {
                return ChatColour.RED + getPlayerName() + " is not a real player!";
            }
        } else {
            return ChatColour.RED + getPlayerName() + " is nicked!";
        }
    }

    public String getFormattedJoinStats(String playerName) {
        if (!isNicked) {
            if (!notReal) {
                return MessageFormat.format("{0} {1} {2} {3} {4}, {5}, {6}, {7}", joinPrefix, starColor(getBedwarStars()), playerName, separator, wsColor(getWinstreak()), fkdrColorDouble(fkdRatioDouble(this)), wlrColorDouble(wlRatioDouble(this)), bblrColorDouble(bblRatioDouble(this)));
            } else {
                return ChatColour.RED + getPlayerName() + " is not a real player!";
            }
        } else {
            return ChatColour.RED + getPlayerName() + " is nicked!";
        }
    }

    @Override
    public String getFormattedQueueStats() {
        if (!isNicked) {
            if (!notReal) {
                return MessageFormat.format("{0} {1} {2} {3} {4}, {5}, {6}, {7}", queuePrefix, starColor(getBedwarStars()), getRankColour(), separator, wsColor(getWinstreak()), fkdrColorDouble(fkdRatioDouble(this)), wlrColorDouble(wlRatioDouble(this)), bblrColorDouble(bblRatioDouble(this)));
            } else {
                return ChatColour.RED + getPlayerName() + " is not a real player!";
            }
        } else {
            return ChatColour.RED + getPlayerName() + " is nicked!";
        }
    }

    public String getSidebarName() {
        return "BED WARS";
    }

    public void init() {}

}
