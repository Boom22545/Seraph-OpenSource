package si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.classic.arenabrawl.utils;

public enum ArenaBrawlOffensives {

    BOULDER_TOSS("boulder_toss", "Boulder Toss", "Boulder"), COOKIE_SHOTGUN("cookie_shotgun", "Cookie Shotgun", "Cookie"),
    FALCON_PUNCH("falcon_punch", "Falcon Punch", "Falcon"), FIREBALL("fireball", "Fireball", "Fireball"),
    FLAME_BREATH("flame_breath", "Flame Breath", "Flame"), FREEZING_BREATH("freezing_breath", "Freezing Breath", "Freezing"),
    ROCKET_PIG("rocket_pig", "Guided Pig 2000", "Pig"), LIGHTNING_STRIKE("lightning_strike", "Lightning Strike", "Lightning"),
    MELON_LAUNCHER("melon_launcher", "Melon Launcher", "Melon"), PUMPKING_LAUNCHER("pumpkin_launcher", "Pumpkin Launcher", "Pumpkin"),
    PROXIMITY_MINE("proximity_mine", "Proximity Mine", "Proxi"), ROCKET_CHICKEN("rocket_chicken", "Rocket Chicken", "Chicken"),
    SEISMIC_WAVE("seismic_wave", "Seismic Wave", "Seismic"), SNOWBALL("snowball", "Snowball", "Snowball"),
    FLAME_SWORD("flame_sword", "Flame Sword", "F Sword"), ANCIENT_BREATH("ancient_breath", "Ancient Breath", "Ancient");

    final String offensive, offensiveName, shortName;

    ArenaBrawlOffensives(String offensive, String offensiveName, String shortName) {
        this.offensive = offensive;
        this.offensiveName = offensiveName;
        this.shortName = shortName;
    }

    @Override
    public String toString() { return offensive; }

    public String toAbilityName() { return offensiveName; }

    public String toShortName() { return shortName; }

}
