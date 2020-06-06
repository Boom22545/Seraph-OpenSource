package si.seraph.opensource.seraphapi.apiwrappers.hypixel;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.exceptions.*;
import si.seraph.opensource.seraphapi.utils.chat.ChatUtils;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.HypixelGameBase;


public class Player extends HypixelGameBase {

    private boolean isNicked, watchdog;
    private JsonObject player;

    public Player(String name) {
        setData(name);
    }

    public void setData(String name) {
        isNicked = false;
        try {
            setWholeObject(getApi(name));
        } catch (TooManyHypixelRequestsException e) {
            ChatUtils.sendMessage("Too Many Requests!");
        } catch (ApiReturnedUnSuccessfulException e) {
            ChatUtils.sendMessage("The api returned not successful, cause: " + e);
        } catch (InvalidKeyException e) {
            ChatUtils.sendMessage("Invalid API Key!");
        } catch (NullPointerException e) {
            LOGGER.error("setData", e);
        } catch (PlayerReturnedNullException e) {
            isNicked = true;
        }
        try {
            if (!isNicked) {
                this.player = getWholeObject().get("player").getAsJsonObject();
                setPlayerData();
            } else {
                this.playerName = name;
            }
        } catch (NullPointerException e) {
            if (!isNicked) {
                LOGGER.error("Maybe they have never played hypixel before", e);
            }
            LOGGER.error(e);
        }
    }

    public void setData(EntityPlayer player) {
        boolean isFunctional = false;
        isNicked = false;
        setEntityPlayer(player);
        setEntityPlayer(player);
        try {
            setWholeObject(getQueuestatsApi(player));
            isFunctional = true;
        } catch (TooManyHypixelRequestsException e) {
            ChatUtils.sendMessage("Too Many Requests!");
        } catch (ApiReturnedUnSuccessfulException e) {
            ChatUtils.sendMessage("The api returned not successful, cause: " + e);
        } catch (InvalidKeyException e) {
            ChatUtils.sendMessage("Invalid API Key!");
        } catch (NullPointerException e) {
            LOGGER.error("setData", e);
        } catch (PotentiallyWatchdogException e) {
            watchdog = true;
        } catch (PlayerReturnedNullException e) {
            isNicked = true;
        }
        try {
            if (!isNicked && isFunctional && !watchdog) {
                getWholeObject().get("player").getAsJsonObject();
                setPlayerData();
            }
        } catch (NullPointerException e) {
            if (!isNicked) {
                LOGGER.error("Maybe they have never played hypixel before", e);
            }
            LOGGER.error(e);
        }
    }

    private JsonObject getObject() {
        return player;
    }

    @Override
    public String getSidebarName() {
        return null;
    }

    @Override
    public String getFormattedStats() {
        return null;
    }

    @Override
    public String getFormattedQueueStats() {
        return null;
    }

    @Override
    public String getFormattedJoinStats(String playerName) {
        return null;
    }

    @Override
    public void init() {
    }

}
