package si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.classic.arenabrawl.utils;

import si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.HypixelGameBase;
import si.seraph.opensource.seraphapi.utils.chat.ChatColour;

public abstract class ArenaBrawlUtils extends HypixelGameBase {

    // Formatted Offensive
    public String formatOffensive(String offensive) {
        String output = "";
        try {
            ArenaBrawlOffensives offensives = ArenaBrawlOffensives.valueOf(offensive.toUpperCase());
            output = offensives.toShortName();
        } catch (Exception ex) {
            output = "ERR";
        }
        return ChatColour.RED + output;
    }

    public String formatUtility(String utility) {
        String output = "";
        try {
            ArenaBrawlUtilities utilities = ArenaBrawlUtilities.valueOf(utility.toUpperCase());
            output = utilities.toShortName();
        } catch (Exception ex) {
            output = "ERR";
        }
        return ChatColour.YELLOW + output;
    }

    public String formatSupport(String support) {
        String output = "";
        try {
            ArenaBrawlSupports supports = ArenaBrawlSupports.valueOf(support.toUpperCase());
            output = supports.toShortName();
        } catch (Exception ex) {
           output = "ERR";
        }
        return ChatColour.GREEN + output;
    }

    public String formatUltimate(String ultimate) {
        String output = "";
        try {
            ArenaBrawlUltimates ultimates = ArenaBrawlUltimates.valueOf(ultimate.toUpperCase());
            output = ultimates.toShortName();
        } catch (EnumConstantNotPresentException ex) {
            ex.printStackTrace();
            output = "ERR";
        }
        return ChatColour.GOLD + output;
    }

    //TODO Add color assignment to the Enum to reduce else if's
    public String formatRune(String rune) {
        String output = "";
        try {
            ArenaBrawlRunes runes = ArenaBrawlRunes.valueOf(rune.toUpperCase());
            output = runes.toAbilityName();
        } catch (Exception ex) {
            output = "Rune Not Found!";
        }
        if (rune.equalsIgnoreCase("slowing")) {
            return ChatColour.LIGHT_PURPLE + output;
        } else if (rune.equalsIgnoreCase("speed")) {
            return ChatColour.WHITE + output;
        } else if (rune.equalsIgnoreCase("energy")) {
            return ChatColour.YELLOW + output;
        } else if (rune.equalsIgnoreCase("damage")) {
            return ChatColour.RED + output;
        } else if (rune.equalsIgnoreCase("defense")) {
            return ChatColour.GOLD + output;
        } else {
            return output;
        }
    }

}
