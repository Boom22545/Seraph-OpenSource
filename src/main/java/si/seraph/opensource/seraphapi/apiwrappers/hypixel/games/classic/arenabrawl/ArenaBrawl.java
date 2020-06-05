package si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.classic.arenabrawl;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.Scoreboard;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.exceptions.*;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.IHypixelGame;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.classic.arenabrawl.utils.ArenaBrawlUtils;
import si.seraph.opensource.seraphapi.utils.chat.ChatColour;
import si.seraph.opensource.seraphapi.utils.chat.ChatUtils;

import java.text.MessageFormat;

public class ArenaBrawl extends ArenaBrawlUtils implements IHypixelGame {

    private boolean isNicked, arenaBrawlStatsCommand, notReal, watchdog;
    private JsonObject arenaBrawlJsonObject, achievementJsonObject;
    private Scoreboard scoreboard;
    /* KEEP CHAT COLOUR RESET AFTER! */
    private String queuePrefix = ChatColour.LIGHT_PURPLE + "*" + ChatColour.RESET, separator = ChatColour.GOLD + "-" + ChatColour.RESET;
    private String joinPrefix = ChatColour.GREEN + "+" + ChatColour.RESET;

    public ArenaBrawl(String name) {
        setData(name);
        arenaBrawlStatsCommand = false;
    }

    public ArenaBrawl() {
        arenaBrawlStatsCommand = false;
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
            LOGGER.error("setData", e);
        } catch (PlayerReturnedNullException e) {
            isNicked = true;
        }
        try {
            if (!isNicked && isFunctional && !notReal) {
                this.arenaBrawlJsonObject = getArenaBrawlJSON(getWholeObject());
                setPlayerData();
                scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
            } else {
                this.playerName = name;
            }
        } catch (NullPointerException e) {
            if (!isNicked) {
                LOGGER.error("Maybe they have never played arena brawl before", e);
            }
            LOGGER.error(e);
        }
    }

    public void setData(EntityPlayer player) {
        boolean isFunctional = false;
        isNicked = false;
        notReal = false;
        setEntityPlayer(player);
        setEntityPlayer(player);
        try {
            setWholeObject(getQueuestatsApi(player));
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
            LOGGER.error("setData", e);
        } catch (PotentiallyWatchdogException e) {
            watchdog = true;
        } catch (PlayerReturnedNullException e) {
            notReal = true;
        }
        try {
            if (!isNicked && isFunctional && !watchdog && !notReal) {
                this.arenaBrawlJsonObject = getArenaBrawlJSON(getWholeObject());
                setPlayerData();
                scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
            }
        } catch (NullPointerException e) {
            if (!isNicked) {
                LOGGER.error("Maybe they have never played arena brawl before", e);
            }
            LOGGER.error(e);
        }
    }

    private JsonObject getObject() {
        return arenaBrawlJsonObject;
    }

    public String getOffensive() {
        try {
            return getObject().get("offensive").toString().replace("\"", "");
        } catch (Exception ex) {
            return "not_selected";
        }
    }

    public String getUtility() {
        try {
            return getObject().get("utility").toString().replace("\"", "");
        } catch (Exception ex) {
            return "not_selected";
        }
    }

    public String getSupport() {
        try {
            return getObject().get("support").toString().replace("\"", "");
        } catch (Exception ex) {
            return "not_selected";
        }
    }

    public String getUltimate() {
        try {
            return getObject().get("ultimate").toString().replace("\"", "");
        } catch (Exception ex) {
            return "not_selected";
        }
    }

    public String getRune() {
        try {
            return getObject().get("active_rune").toString().replace("\"", "");
        } catch (Exception ex) {
            return "not_selected";
        }
    }

    @Override
    public String getSidebarName() {
        return null;
    }

    @Override
    public String getFormattedStats() {
        if (!isNicked) {
            if (!notReal) {
                return MessageFormat.format("{0} {1} {2}, {3}, {4}, {5}, {6}", getRankColourWithPrefix(), separator, formatRune(getRune()), formatOffensive(getOffensive()), formatUtility(getUtility()), formatSupport(getSupport()), formatUltimate(getUltimate()));
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
                return MessageFormat.format("{0} {1} {2} {3}, {4}, {5}, {6}, {7}", joinPrefix, getRankColour(), separator, formatRune(getRune()), formatOffensive(getOffensive()), formatUtility(getUtility()), formatSupport(getSupport()), formatUltimate(getUltimate()));
            } else {
                return ChatColour.RED + getPlayerName() + " is not a real player!";
            }
        } else {
            return ChatColour.RED + getPlayerName() + " is nicked!";
        }
    }

    @Override
    public String getFormattedJoinStats(String playerName) {
        if (!isNicked) {
            if (!notReal) {
                return MessageFormat.format("{0} {1} {2} {3}, {4}, {5}, {6}, {7}", joinPrefix, playerName, separator, formatRune(getRune()), formatOffensive(getOffensive()), formatUtility(getUtility()), formatSupport(getSupport()), formatUltimate(getUltimate()));
            } else {
                return ChatColour.RED + getPlayerName() + " is not a real player!";
            }
        } else {
            return ChatColour.RED + getPlayerName() + " is nicked!";
        }
    }

    @Override
    public void init() {

    }
}
