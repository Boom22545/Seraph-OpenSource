package seraph.mods.opensource.seraphapi.games;

import com.google.gson.JsonObject;
import seraph.mods.opensource.seraphapi.HypixelAPI;
import seraph.mods.opensource.seraphapi.utils.ChatColour;
import net.minecraft.client.Minecraft;

import java.text.DecimalFormat;
import java.util.UUID;

public abstract class HypixelGameBase extends HypixelAPI implements IHypixelGame {

    private static final String playerUUID = Minecraft.getMinecraft().getSession().getProfile().getId().toString().replace("-", "");
    private JsonObject wholeObject, playerObject;
    protected String playerName, uniqueIDString;
    protected UUID uniqueID;

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

    public String getRankColour() {
        String s = "", staff, rank = "", mvpPlusPlus;
        JsonObject player = getWholeObject().get("player").getAsJsonObject();
            try {
                staff = player.get("rank").getAsString();
            } catch (NullPointerException ignored) {
                staff = "NOT STAFF";
            }
            try {
                mvpPlusPlus = player.get("monthlyPackageRank").getAsString();
            } catch (Exception e) {
                mvpPlusPlus = "NEVER BROUGHT";
            }
            try {
                rank = player.get("newPackageRank").getAsString();
            } catch (NullPointerException e) {
                s = ChatColour.GREY + "";
            }
            if (mvpPlusPlus.equalsIgnoreCase("SUPERSTAR")) {
                s = ChatColour.GOLD + "";
            } else if (!mvpPlusPlus.equalsIgnoreCase("SUPERSTAR")) {
                if (rank.equalsIgnoreCase("MVP_PLUS")) {
                    s = ChatColour.AQUA + "";
                } else if (rank.equalsIgnoreCase("MVP")) {
                    s = ChatColour.AQUA + "";
                } else if (rank.equalsIgnoreCase("VIP_PLUS")) {
                    s = ChatColour.GREEN + "";
                } else if (rank.equalsIgnoreCase("VIP")) {
                    s = ChatColour.GREEN + "";
                }
            }
            try {
                if (staff.equalsIgnoreCase("HELPER")) {
                    s = ChatColour.BLUE + "";
                } else if (staff.equalsIgnoreCase("MODERATOR")) {
                    s = ChatColour.DARK_GREEN + "";
                } else if (staff.equalsIgnoreCase("ADMIN")) {
                    s = ChatColour.RED + "";
                } else if (staff.equalsIgnoreCase("YOUTUBER")) {
                    s = ChatColour.RED + "";
                }
            } catch (Exception ignored) {
            }
        return s + getPlayerName() + ChatColour.RESET + "";
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

    protected void setPlayerObject(JsonObject playerObject) {
        this.playerObject = playerObject.getAsJsonObject();
    }

    public void setPlayerData() {
        this.playerName = getWholeObject().get("player").getAsJsonObject().get("displayname").getAsString();
        this.uniqueIDString = getWholeObject().get("player").getAsJsonObject().get("uuid").getAsString().replace("-", "");
        this.uniqueID= java.util.UUID.fromString(String.format("%s-%s-%s-%s-%s", uniqueIDString.substring(0,8), uniqueIDString.substring(8,13), uniqueIDString.substring(13,18), uniqueIDString.substring(18,23),uniqueIDString.substring(23,uniqueIDString.length()-1)));
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
