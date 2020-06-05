package si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.classic.arenabrawl.utils;

public enum ArenaBrawlSupports {

    BONE_SHIELD("bone_shield", "Bone Shield", "Bone"), CACTUS_SHIELD("cactus_shield", "Cactus Shield", "Cactus"),
    HEALING_TOTEM("healing_totem", "Healing Totem", "Totem"), HOLY_WATER("holy_water", "Holy Water", "Holy"),
    LIFE_LEECH("life_leech", "Life Leech", "Leech"), SONG_OF_POWER("song_of_power", "Song of Power", "Song"),
    STAR_SHIELD("star_shield", "Star Shield", "Star"), TREE_OF_LIFE("tree_of_life", "Tree of Life", "Tree"),
    SPIRIT_LINK("spirit_link", "Spirit Link", "Spirit"), VAMPIRIC_CHAIN("vampiric_chain", "Vampiric Chain", "Vampire"),
    TIME_WARP("time_warp", "Time Warp", "Time");

    final String support, supportName, shortName;

    ArenaBrawlSupports(String support, String supportName, String shortName) {
        this.support = support;
        this.supportName = supportName;
        this.shortName = shortName;
    }

    @Override
    public String toString() { return support; }

    public String toAbilityName() { return supportName; }

    public String toShortName() { return shortName; }

}
