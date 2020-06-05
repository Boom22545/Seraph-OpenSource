package si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.classic.arenabrawl.utils;

public enum ArenaBrawlRunes {

    SLOWING("slowing", "Slowing"), SPEED("speed", "Speed"), ENERGY("energy", "Energy"), DAMAGE("damage", "Damage"),
    TANK("tank", "Tank");

    final String rune, runeName;

    ArenaBrawlRunes(String rune, String runeName) {
        this.rune = rune;
        this.runeName = runeName;
    }

    @Override
    public String toString() { return rune; }

    public String toAbilityName() { return runeName; }

}
