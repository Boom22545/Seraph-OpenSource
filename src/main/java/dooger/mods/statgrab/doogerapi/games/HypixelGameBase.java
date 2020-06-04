package dooger.mods.statgrab.doogerapi.games;

import com.google.gson.JsonObject;
import dooger.mods.statgrab.doogerapi.HypixelAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import java.text.DecimalFormat;
import java.util.UUID;

public abstract class HypixelGameBase extends HypixelAPI implements IHypixelGame {

    private static final String playerUUID = Minecraft.getMinecraft().getSession().getProfile().getId().toString().replace("-", "");
    private JsonObject wholeObject, playerObject;
    protected String playerName, uniqueIDString;
    protected UUID uniqueID;
    protected EntityPlayer entityPlayer;
    protected final int DEFAULT = 0;

    public String getPlayerUUID() {
        return playerUUID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getUniqueID() {
        return uniqueIDString;
    }

    public UUID getUUID() {
        return uniqueID;
    }

    public JsonObject getWholeObject() {
        return wholeObject.getAsJsonObject();
    }

    public void setWholeObject(JsonObject wholeObject) {
        this.wholeObject = wholeObject.getAsJsonObject();
        setPlayerObject(wholeObject.get("player").getAsJsonObject());
    }

    public double formatDouble(int int1, int int2) {
        double d;
        double result = (double) int1 / (double) int2;
        DecimalFormat format = new DecimalFormat("##.##");
        String formattedString = format.format(result).replace(",", ".");
        try {
            d = Double.parseDouble(formattedString);
        } catch (NumberFormatException e) {
            System.out.println("formatDouble Error, passing " + int1 + " as response");
            d = int1;
        }
        return d;
    }

    protected JsonObject getPlayerObject() {
        return playerObject.getAsJsonObject();
    }

    protected void setPlayerObject(JsonObject playerObject) {
        this.playerObject = playerObject.getAsJsonObject();
    }

    public void setPlayerData() {
        this.playerName = getWholeObject().get("player").getAsJsonObject().get("displayname").getAsString();
        this.uniqueIDString = getWholeObject().get("player").getAsJsonObject().get("uuid").getAsString().replace("-", "");
        this.uniqueID= java.util.UUID.fromString(String.format("%s-%s-%s-%s-%s", uniqueIDString.substring(0,8), uniqueIDString.substring(8,13), uniqueIDString.substring(13,18), uniqueIDString.substring(18,23),uniqueIDString.substring(23,uniqueIDString.length()-1)));
    }

    public EntityPlayer getEntityPlayer() {
        return entityPlayer;
    }

    public void setEntityPlayer(EntityPlayer entityPlayer) {
        this.entityPlayer = entityPlayer;
    }

    public String formatTime(int seconds) {
        int p1 = seconds % 60;
        int p2 = seconds / 60;
        int p3 = p2 % 60;
        p2 = p2 / 60;
        try {
            String string = "";
            if (p2 > 0) {
                string = (p2 + "h");
            } else if (p2 < 0 && p3 > 0) {
                string = (p3 + "m");
            } else if (p2 < 0 && p3 < 0) {
                string = (p1 + "s");
            }
            return string;
        } catch (Exception ex) {
            return "No playtime";
        }
    }

}
