package dooger.mods.statgrab.doogerapi;

import com.google.gson.JsonObject;
import dooger.mods.statgrab.doogerapi.utils.ChatUtils;
import net.minecraft.entity.player.EntityPlayer;
import dooger.mods.statgrab.doogerapi.exceptions.ApiReturnedUnSuccessfulException;
import dooger.mods.statgrab.doogerapi.exceptions.InvalidKeyException;
import dooger.mods.statgrab.doogerapi.exceptions.NullJSONFileException;
import dooger.mods.statgrab.doogerapi.exceptions.TooManyHypixelRequestsException;
import dooger.mods.statgrab.doogerapi.games.HypixelGameBase;


public class Player extends HypixelGameBase {

    private boolean isNicked, isCommand;
    private JsonObject player, status;

    public Player(String name) {
        setData(name);
    }

    public void setData(String name) {
        isNicked = false;
        try {
            setWholeObject(getApi(name));
            try {
                setStatus(getApi(HypixelAPITypes.SESSION, name));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            if (!isNicked) {
                this.player = getWholeObject().get("player").getAsJsonObject();
                setPlayerData();
            } else {
                this.playerName = name;
            }
        } catch (NullPointerException e) {
            if (!isNicked) {
                System.out.print("Maybe they have never played bedwars before");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setData(EntityPlayer player) {
        isNicked = false;
        try {
            setWholeObject(getApi(player));
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
            if (!isNicked) {
                this.player = getWholeObject().get("player").getAsJsonObject();
                setPlayerData();
            } else {
                this.playerName = player.getName();
            }
        } catch (NullPointerException e) {
            if (!isNicked) {
                System.out.println("Error getting player's normal data");
                e.printStackTrace();
            }
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
    public boolean isCommand() {
        return isCommand;
    }

    @Override
    public void setCommand(boolean b) {
        isCommand = b;
    }

    @Override
    public void init() {

    }

    public String getFormattedStats() {
        return null;
    }

    public String getFormattedDecimalStats() {
        return null;
    }

    public JsonObject getStatus() {
        return status;
    }

    public void setStatus(JsonObject status) {
        this.status = status;
    }

}
