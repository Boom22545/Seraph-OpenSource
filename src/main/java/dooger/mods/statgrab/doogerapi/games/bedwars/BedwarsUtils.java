package dooger.mods.statgrab.doogerapi.games.bedwars;

import dooger.mods.statgrab.doogerapi.games.HypixelGameBase;
import dooger.mods.statgrab.doogerapi.utils.ChatColour;

public abstract class BedwarsUtils extends HypixelGameBase {

    // WLR Method Double
    public double wlRatioDouble(Bedwars bw, BedwarsModes modes) {
        try {
            return formatDouble(bw.getWins(modes), bw.getLosses(modes));
        } catch (Exception ex) {
            return formatDouble(bw.getWins(modes), DEFAULT);
        }
    }

    // WLR Colors Double
    public String wlrColorDouble(double wlr) {
        String string = "";
        if (wlr < 1) {
            string = ChatColour.DARK_GREY + "WLR: " + ChatColour.DARK_GREY + wlr;
        } else if (wlr < 2) {
            string = ChatColour.GREY + "WLR: " + ChatColour.GREY + wlr;
        } else if (wlr < 4) {
            string = ChatColour.WHITE + "WLR: " + ChatColour.WHITE + wlr;
        } else if (wlr < 6) {
            string = ChatColour.WHITE + "WLR: " + ChatColour.GOLD + wlr;
        } else if (wlr < 7) {
            string = ChatColour.WHITE + "WLR: " + ChatColour.DARK_GREEN + wlr;
        } else if (wlr < 10) {
            string = ChatColour.WHITE + "WLR: " + ChatColour.RED + wlr;
        } else if (wlr < 15) {
            string = ChatColour.WHITE + "WLR: " + ChatColour.DARK_RED + wlr;
        } else if (wlr < 50) {
            string = ChatColour.WHITE + "WLR: " + ChatColour.LIGHT_PURPLE + wlr;
        } else {
            string = ChatColour.WHITE + "WLR: " + ChatColour.DARK_PURPLE + wlr;
        }
        return string + ChatColour.WHITE;
    }

    // BBLR Method Double
    public double bblRatioDouble(Bedwars bw, BedwarsModes modes) {
        try {
            return formatDouble(bw.getBedsBroken(modes), bw.getBedsLost(modes));
        } catch (Exception ex) {
            return formatDouble(bw.getBedsBroken(modes) ,DEFAULT);
        }
    }

    // BBLR Colors Double
    public String bblrColorDouble(double bblr) {
        String string = "";
        if (bblr < 1) {
            string = ChatColour.DARK_GREY + "BBLR: " + bblr;
        } else if (bblr < 2) {
            string = ChatColour.GREY + "BBLR: " + ChatColour.GREY + bblr;
        } else if (bblr < 4) {
            string = ChatColour.WHITE + "BBLR: " + ChatColour.WHITE + bblr;
        } else if (bblr < 6) {
            string = ChatColour.WHITE + "BBLR: " + ChatColour.GOLD + bblr;
        } else if (bblr < 7) {
            string = ChatColour.WHITE + "BBLR: " + ChatColour.DARK_GREEN + bblr;
        } else if (bblr < 10) {
            string = ChatColour.WHITE + "BBLR: " + ChatColour.RED + bblr;
        } else if (bblr < 15) {
            string = ChatColour.WHITE + "BBLR: " + ChatColour.DARK_RED + bblr;
        } else if (bblr < 50) {
            string = ChatColour.WHITE + "BBLR: " + ChatColour.LIGHT_PURPLE + bblr;
        } else {
            string = ChatColour.WHITE + "BBLR: " + ChatColour.DARK_PURPLE + bblr;
        }
        return string + ChatColour.WHITE;
    }

    // Winstreak Colors
    public String wsColor(int ws) {
        String string = "";
        if (ws < 1) {
            string = ChatColour.DARK_GREY + "WS: " + ChatColour.DARK_GREY + ws;
        } else if (ws < 50) {
            string = ChatColour.GREY + "WS: " + ChatColour.GREY + ws;
        } else if (ws < 200) {
            string = ChatColour.WHITE + "WS: " + ChatColour.WHITE + ws;
        } else if (ws < 350) {
            string = ChatColour.WHITE + "WS: " + ChatColour.GOLD + ws;
        } else if (ws < 500) {
            string = ChatColour.WHITE + "WS: " + ChatColour.DARK_GREEN + ws;
        } else if (ws < 650) {
            string = ChatColour.WHITE + "WS: " + ChatColour.RED + ws;
        } else if (ws < 800) {
            string = ChatColour.WHITE + "WS: " + ChatColour.DARK_RED + ws;
        } else if (ws < 1000) {
            string = ChatColour.WHITE + "WS: " + ChatColour.LIGHT_PURPLE + ws;
        } else {
            string = ChatColour.WHITE + "WS: " + ChatColour.DARK_PURPLE + ws;
        }
        return string + ChatColour.WHITE;
    }

    // FKDR Method Double
    public double fkdRatioDouble(Bedwars bw, BedwarsModes modes) {
        try {
            return formatDouble(bw.getFinalKills(modes), bw.getFinalDeaths(modes));
        } catch (Exception ex) {
            return formatDouble(bw.getFinalKills(modes),DEFAULT);
        }
    }

    // FKDR Colors Double
    public String fkdrColorDouble(double fkdr) {
        String string = "";
        if (fkdr < 1) {
            string = ChatColour.DARK_GREY + "FKDR: " + fkdr;
        } else if (fkdr < 5) {
            string = ChatColour.GREY + "FKDR: " + ChatColour.GREY + fkdr;
        } else if (fkdr < 10) {
            string = ChatColour.WHITE + "FKDR: " + ChatColour.WHITE + fkdr;
        } else if (fkdr < 20) {
            string = ChatColour.WHITE + "FKDR: " + ChatColour.GOLD + fkdr;
        } else if (fkdr < 35) {
            string = ChatColour.WHITE + "FKDR: " + ChatColour.DARK_GREEN + fkdr;
        } else if (fkdr < 60) {
            string = ChatColour.WHITE + "FKDR: " + ChatColour.RED + fkdr;
        } else if (fkdr < 100) {
            string = ChatColour.WHITE + "FKDR: " + ChatColour.DARK_RED + fkdr;
        } else if (fkdr < 500) {
            string = ChatColour.WHITE + "FKDR: " + ChatColour.LIGHT_PURPLE + fkdr;
        } else {
            string = ChatColour.WHITE + "FKDR: " + ChatColour.DARK_PURPLE + fkdr;
        }
        return string + ChatColour.WHITE;
    }

    // Star Integer Seperation
    public static String starSeperation(int star) {
        char[] chars = String.valueOf(star).toCharArray();
        return ChatColour.RED + "" + chars[0] + ChatColour.GOLD + "" + chars[1] + ChatColour.YELLOW + "" + chars[2] + ChatColour.GREEN + "" + chars[3];
    }

    // Star Colors
    public String starColor(int star) {
        String string = "";
        if (star < 100) {
            string = ChatColour.GREY + String.valueOf(star);
        } else if (star < 200) {
            string = ChatColour.WHITE + String.valueOf(star);
        } else if (star < 300) {
            string = ChatColour.GOLD + String.valueOf(star);
        } else if (star < 400) {
            string = ChatColour.AQUA + String.valueOf(star);
        } else if (star < 500) {
            string = ChatColour.DARK_GREEN + String.valueOf(star);
        } else if (star < 600) {
            string = ChatColour.DARK_AQUA + String.valueOf(star);
        } else if (star < 700) {
            string = ChatColour.DARK_RED + String.valueOf(star);
        } else if (star < 800) {
            string = ChatColour.LIGHT_PURPLE + String.valueOf(star);
        } else if (star < 900) {
            string = ChatColour.BLUE + String.valueOf(star);
        } else if (star < 1000) {
            string = ChatColour.DARK_PURPLE + String.valueOf(star);
        } else {
            string = starSeperation(star) + ChatColour.AQUA;
        }
        return string + "\u272B" + ChatColour.WHITE;
    }
}
