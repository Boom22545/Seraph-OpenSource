package si.seraph.opensource.seraphapi.apiwrappers.hypixel;

import com.google.gson.JsonObject;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.exceptions.*;
import si.seraph.opensource.seraphapi.utils.chat.ChatUtils;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.HypixelGameBase;


public class Player extends HypixelGameBase {

    private boolean isNicked, notReal;
    private JsonObject player, status;

    public Player(String name) {
        setData(name);
    }

    public void setData(String name) {
        isNicked = false;
        notReal = false;
        try {
            setWholeObject(getApi(name));
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
    public String getFormattedQueueStats() {
        return null;
    }

    @Override
    public void init() {
    }

}
