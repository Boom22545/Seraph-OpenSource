package dooger.mods.statgrab.doogerapi.games.bedwars;

import com.google.gson.JsonObject;
import dooger.mods.statgrab.doogerapi.exceptions.ApiReturnedUnSuccessfulException;
import dooger.mods.statgrab.doogerapi.exceptions.InvalidKeyException;
import dooger.mods.statgrab.doogerapi.exceptions.NullJSONFileException;
import dooger.mods.statgrab.doogerapi.exceptions.TooManyHypixelRequestsException;
import dooger.mods.statgrab.doogerapi.utils.ChatColour;
import dooger.mods.statgrab.doogerapi.utils.ChatUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Scoreboard;
import dooger.mods.statgrab.doogerapi.games.IHypixelGame;

import java.text.MessageFormat;

import static dooger.mods.statgrab.doogerapi.games.bedwars.BedwarsModes.ALL;

public final class Bedwars extends BedwarsUtils implements IHypixelGame {

    private boolean isNicked, bedwarsStatsCommand;
    private JsonObject bedwarJsonObject;
    private Scoreboard scoreboard;

    public Bedwars(String name) {
        setData(name);
        bedwarsStatsCommand = false;
    }

    public Bedwars() {
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

    public int getFinalKills(BedwarsModes type) {
        int i = DEFAULT;
        JsonObject bedwars = getObject();
        assert bedwars != null;
        try {
            switch (type) {
                case ALL:
                    i = (bedwars.get(("final_kills_bedwars")).getAsInt());
                    break;
                case SOLOS:
                    i = (bedwars.get(("eight_one_final_kills_bedwars")).getAsInt());
                    break;
                case DOUBLES:
                    i = (bedwars.get(("eight_two_final_kills_bedwars")).getAsInt());
                    break;
                case THREES:
                    i = (bedwars.get(("four_three_final_kills_bedwars")).getAsInt());
                    break;
                case FOURS:
                    i = (bedwars.get(("four_four_final_kills_bedwars")).getAsInt());
                    break;
            }
        } catch (Exception ignored) {
        }
        return i;
    }

    public int getFinalDeaths(BedwarsModes type) {
        int i = DEFAULT;
        JsonObject bedwars = getObject();
        assert bedwars != null;
        try {
            switch (type) {
                case ALL:
                    i = (bedwars.get(("final_deaths_bedwars")).getAsInt());
                    break;
                case SOLOS:
                    i = (bedwars.get(("eight_one_final_deaths_bedwars")).getAsInt());
                    break;
                case DOUBLES:
                    i = (bedwars.get(("eight_two_final_deaths_bedwars")).getAsInt());
                    break;
                case THREES:
                    i = (bedwars.get(("four_three_final_deaths_bedwars")).getAsInt());
                    break;
                case FOURS:
                    i = (bedwars.get(("four_four_final_deaths_bedwars")).getAsInt());
                    break;
            }
        } catch (Exception ignored) {
        }
        return i;
    }

    public int getWinstreak(BedwarsModes type) {
        int i = DEFAULT;
        JsonObject bedwars = getObject();
        assert bedwars != null;
        try {
            switch (type) {
                case ALL:
                    i = bedwars.get("winstreak").getAsInt();
                    break;
                case SOLOS:
                    i = bedwars.get("eight_one_winstreak").getAsInt();
                    break;
                case DOUBLES:
                    i = bedwars.get("eight_two_winstreak").getAsInt();
                    break;
                case THREES:
                    i = bedwars.get("four_three_winstreak").getAsInt();
                    break;
                case FOURS:
                    i = bedwars.get("four_four_winstreak").getAsInt();
                    break;
            }
        } catch (Exception ignored) {
        }
        return i;
    }

    public int getBedwarStars() {
        int i = DEFAULT;
        try {
            JsonObject json = getWholeObject();
            JsonObject player = json.get("player").getAsJsonObject();
            JsonObject stats = player.get("achievements").getAsJsonObject();
            i = (stats.get("bedwars_level").getAsInt());
        } catch (Exception ignored) {
        }
        return i;
    }

    public int getWins(BedwarsModes type) {
        int i = DEFAULT;
        JsonObject bedwars = getObject();
        assert bedwars != null;
        try {
            switch (type) {
                case ALL:
                    i = (bedwars.get(("wins_bedwars")).getAsInt());
                    break;
                case SOLOS:
                    i = (bedwars.get(("eight_one_wins_bedwars")).getAsInt());
                    break;
                case DOUBLES:
                    i = (bedwars.get(("eight_two_wins_bedwars")).getAsInt());
                    break;
                case THREES:
                    i = (bedwars.get(("four_three_wins_bedwars")).getAsInt());
                    break;
                case FOURS:
                    i = (bedwars.get(("four_four_wins_bedwars")).getAsInt());
                    break;
            }
        } catch (Exception ignored) {
        }
        return i;
    }

    public int getLosses(BedwarsModes type) {
        int i = DEFAULT;
        JsonObject bedwars = getObject();
        assert bedwars != null;
        try {
            switch (type) {
                case ALL:
                    i = (bedwars.get(("losses_bedwars")).getAsInt());
                    break;
                case SOLOS:
                    i = (bedwars.get(("eight_one_losses_bedwars")).getAsInt());
                    break;
                case DOUBLES:
                    i = (bedwars.get(("eight_two_losses_bedwars")).getAsInt());
                    break;
                case THREES:
                    i = (bedwars.get(("four_three_losses_bedwars")).getAsInt());
                    break;
                case FOURS:
                    i = (bedwars.get(("four_four_losses_bedwars")).getAsInt());
                    break;
            }
        } catch (Exception ignored) {
        }
        return i;
    }

    public int getBedsLost(BedwarsModes type) {
        int i = 1;
        JsonObject bedwars = getObject();
        assert bedwars != null;
        try {
            switch (type) {
                case ALL:
                    i = (bedwars.get(("beds_lost_bedwars")).getAsInt());
                    break;
                case SOLOS:
                    i = (bedwars.get(("eight_one_beds_lost_bedwars")).getAsInt());
                    break;
                case DOUBLES:
                    i = (bedwars.get(("eight_two_beds_lost_bedwars")).getAsInt());
                    break;
                case THREES:
                    i = (bedwars.get(("four_three_beds_lost_bedwars")).getAsInt());
                    break;
                case FOURS:
                    i = (bedwars.get(("four_four_beds_lost_bedwars")).getAsInt());
                    break;
            }
        } catch (Exception ignored) {
        }
        return i;
    }

    public int getBedsBroken(BedwarsModes type) {
        int i = DEFAULT;
        JsonObject bedwars = getObject();
        assert bedwars != null;
        try {
            switch (type) {
                case ALL:
                    i = (bedwars.get(("beds_broken_bedwars")).getAsInt());
                    break;
                case SOLOS:
                    i = (bedwars.get(("eight_one_beds_broken_bedwars")).getAsInt());
                    break;
                case DOUBLES:
                    i = (bedwars.get(("eight_two_beds_broken_bedwars")).getAsInt());
                    break;
                case THREES:
                    i = (bedwars.get(("four_three_beds_broken_bedwars")).getAsInt());
                    break;
                case FOURS:
                    i = (bedwars.get(("four_four_beds_broken_bedwars")).getAsInt());
                    break;
            }
        } catch (Exception ignored) {
        }
        return i;
    }

    public String getFormattedJoinStats() {
        if (!isNicked) {
            BedwarsModes modes = ALL;
            return MessageFormat.format("{0} {1}, {2}, {3}, {4}", starColor(getBedwarStars()), wsColor(getWinstreak(modes)), fkdrColorDouble(fkdRatioDouble(this, modes)), wlrColorDouble(wlRatioDouble(this, modes)), bblrColorDouble(bblRatioDouble(this, modes)));
        }
        return ChatColour.RED + " is Nicked!";
    }

    public String getSidebarName() {
        return "BED WARS";
    }

    public void init() {}

}
