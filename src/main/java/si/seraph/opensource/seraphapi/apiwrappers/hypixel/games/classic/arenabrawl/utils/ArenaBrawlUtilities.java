package si.seraph.opensource.seraphapi.apiwrappers.hypixel.games.classic.arenabrawl.utils;

public enum ArenaBrawlUtilities {

    BARRICADE("barricade", "Barricade", "Barricade"), BULL_CHARGE("bull_charge", "Bull Charge", "Bull"),
    GOLEMFALL("golemfall", "Golemfall", "Golem"), PETRIFY("petrify", "Petrify", "Petri"),
    SHADOW_STEP("shadow_step", "Shadow Step", "Shadow"), SWAP("swap", "Swap", "Swap"),
    WALL_OF_VINES("wall_of_vines", "Wall of Vines", "Vines"), MAGNETIC_IMPULSE("magnetic_impulse", "Magnetic Impulse", "Magnet");

    final String utility, utilityName, shortName;

    ArenaBrawlUtilities(String utility, String utilityName, String shortName) {
        this.utility = utility;
        this.utilityName = utilityName;
        this.shortName = shortName;
    }

    @Override
    public String toString() { return utility; }

    public String toAbilityName() { return utilityName; }

    public String toShortName() { return shortName; }

}
