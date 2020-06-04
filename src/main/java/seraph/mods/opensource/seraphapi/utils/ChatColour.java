package seraph.mods.opensource.seraphapi.utils;

import java.util.regex.Pattern;

public enum ChatColour {

    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GREY('7'),
    DARK_GREY('8'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    CYAN('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f'),
    MAGIC('k', true),
    BOLD('l', true),
    STRIKETHROUGH('m', true),
    UNDERLINE('n', true),
    ITALIC('o', true),
    RESET('r');

    public static final char COLOUR_CHAR = '\u00A7';
    private static final Pattern STRIP_COLOUR_PATTERN = Pattern.compile("(?i)" + COLOUR_CHAR + "[0-9A-FK-OR]");

    private final char code;
    private final boolean isFormat;
    private final String toString;

    ChatColour(char code) {
        this(code, false);
    }

    ChatColour(char code, boolean isFormat) {
        this.code = code;
        this.isFormat = isFormat;
        this.toString = new String(new char[]{COLOUR_CHAR, code});
    }

    public static String translateAlternateColourCodes(String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == '&' && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = ChatColour.COLOUR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }


    @Override
    public String toString() {
        return this.toString;
    }

}
