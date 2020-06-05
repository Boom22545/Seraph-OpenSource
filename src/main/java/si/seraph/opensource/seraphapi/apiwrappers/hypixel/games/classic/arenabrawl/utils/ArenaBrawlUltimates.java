package si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.classic.arenabrawl.utils;

public enum ArenaBrawlUltimates {

    SHIELD_WALL("shield_wall", "Shield Wall", "Shield"), BERSEK("berserk", "Berserk", "Berserk"),
    REFLECT_DAMAGE("reflect_damage", "Reflect Damage", "Reflect"), ARACHNID("broodmother", "Broodmother", "Brood"),
    DOOM_SHROOM("doom_shroom", "Doom Shroom", "Shroom");

    final String ultimate, ultimateName, shortName;

    ArenaBrawlUltimates(String ultimate, String ultimateName, String shortName) {
        this.ultimate = ultimate;
        this.ultimateName = ultimateName;
        this.shortName = shortName;
    }

    @Override
    public String toString() { return ultimate; }

    public String toAbilityName() { return ultimateName; }

    public String toShortName() { return shortName; }

}
