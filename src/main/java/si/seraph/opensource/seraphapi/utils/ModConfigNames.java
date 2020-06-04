package si.seraph.opensource.seraphapi.utils;

public enum ModConfigNames {

    APIKEY("ApiKey"), VERSION("Version");

    private String name;

    ModConfigNames(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
