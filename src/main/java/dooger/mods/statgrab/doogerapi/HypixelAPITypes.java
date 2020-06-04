package dooger.mods.statgrab.doogerapi;

public enum HypixelAPITypes {
    PLAYER("player"), GUILD("guild"), FRIENDS("friends"), KEY("key"), SESSION("status"), SKYBLOCK("skyblock");

    final String string;

    HypixelAPITypes(String s) {
        this.string = s;
    }

    @Override
    public String toString() {
        return string;
    }

    public String toApiRequest() {
        return string;
    }

    public final boolean equals(HypixelAPITypes types) {
        return this == types;
    }
}
