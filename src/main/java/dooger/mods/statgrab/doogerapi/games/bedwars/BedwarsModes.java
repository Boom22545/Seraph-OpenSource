package dooger.mods.statgrab.doogerapi.games.bedwars;

public enum BedwarsModes {

    ALL(""), SOLOS("eight_one_", "solos"), DOUBLES("eight_two_", "doubles"),
    THREES("four_three_", "threes"), FOURS("four_four_", "fours");

    final String mode, gameModeName;

    BedwarsModes(String mode, String gameModeName) {
        this.mode = mode;
        this.gameModeName = gameModeName;
    }

    BedwarsModes(String mode) {
        this.mode = mode;
        this.gameModeName = "";
    }

    @Override
    public String toString() {
        return mode;
    }

    public String toMode() {
        return mode;
    }

    public String toGameString() {
        return gameModeName;
    }

}
