package si.seraph.opensource.seraphapi.utils;

import si.seraph.opensource.seraphapi.utils.chat.ChatColour;

import java.util.regex.Pattern;

public interface References {
    String PREFIX = ChatColour.AQUA + "-----------------------[" + ChatColour.WHITE + "SERAPH" + ChatColour.AQUA + "]----------------------\n";
    String SUFFIX = "\n" + ChatColour.AQUA + "-----------------------------------------------------";
    Pattern UUID = Pattern.compile("\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}");
    Pattern UUID_NO_DASHES = Pattern.compile("\\w{32}");
    String MODNAME = "Stats On Join";
}
