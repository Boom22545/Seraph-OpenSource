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

    private boolean isNicked;
    private JsonObject player, status;

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


    private JsonObject getObject() {
        return player;
    }

    @Override
    public String getSidebarName() {
        return null;
    }

    @Override
    public void init() {
    }

}
