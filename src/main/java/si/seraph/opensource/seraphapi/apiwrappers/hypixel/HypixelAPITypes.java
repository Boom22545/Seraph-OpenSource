package si.seraph.opensource.seraphapi.apiwrappers.hypixel;

public enum HypixelAPITypes {
    PLAYER("player");

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
