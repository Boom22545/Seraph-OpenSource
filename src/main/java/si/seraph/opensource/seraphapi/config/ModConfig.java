package si.seraph.opensource.seraphapi.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import si.seraph.opensource.seraphapi.utils.Handler;
import si.seraph.opensource.seraphapi.utils.chat.ChatUtils;
import static si.seraph.opensource.seraphapi.config.ModConfigNames.APIKEY;
import static si.seraph.opensource.seraphapi.config.ModConfigNames.VERSION;

public class ModConfig {

    private static String apiKey;
    private static ModConfig instance;

    public static ModConfig getInstance() {
        if (instance == null) instance = new ModConfig();
        return instance;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String key) { apiKey = key; }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void makeFile() {
        try {
            if (!getFile().exists()) {
                getFile().getParentFile().mkdir();
                getFile().createNewFile();
                try (FileWriter writer = new FileWriter(getFile())) {
                    writer.write("{}");
                    writer.flush();
                    save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadConfigFromFile() {
        if (!getFile().exists()) makeFile();
        apiKey = getString(APIKEY);
    }

    public File getFile() {
        String here = Paths.get("").toAbsolutePath().toString();
        String file = here + File.separator + "StatsOnJoin" + File.separator + "config.json";
        return new File(file);
    }

    public void init() {
        loadConfigFromFile();
    }

    public void save() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(APIKEY.toString(), getApiKey());
        try (Writer writer = new FileWriter(getFile())) {
            Handler.getGson().toJson(map, writer);
        } catch (Exception e) {
            System.out.println("Unable to write to Config File!");
            e.printStackTrace();
            ChatUtils.sendMessage("&cUNABLE TO SAVE CONFIG! Severe error with Seraph version: " + VERSION + ", You should report this!");
        }
    }

    public String getString(ModConfigNames key) {
        JSONParser parser = new JSONParser();
        String s = "";
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(getFile()));
            s = (String) object.get(key.toString());
        } catch (NullPointerException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
