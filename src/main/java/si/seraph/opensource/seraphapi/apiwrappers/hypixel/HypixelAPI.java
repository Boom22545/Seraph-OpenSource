package si.seraph.opensource.seraphapi.apiwrappers.hypixel;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import si.seraph.opensource.seraphapi.apiwrappers.hypixel.exceptions.*;
import si.seraph.opensource.seraphapi.config.ModConfig;
import si.seraph.opensource.seraphapi.utils.References;
import si.seraph.opensource.seraphapi.utils.SeraphLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

public class HypixelAPI implements SeraphLogger {
    private final String key = ModConfig.getInstance().getApiKey();

    public JsonObject getApi(String name) throws TooManyHypixelRequestsException, InvalidKeyException, ApiReturnedUnSuccessfulException, NullJSONFileException, PlayerReturnedNullException {
        JsonObject obj = new JsonObject();
        if (key == null) {
            throw new InvalidKeyException();
        } else {
            String requstURL;
            if (References.UUID.matcher(name).matches() || References.UUID_NO_DASHES.matcher(name).matches()) {
                requstURL = MessageFormat.format("https://api.hypixel.net/player?key={0}&uuid={1}", key, name.replace("-", ""));
            } else {
                requstURL = MessageFormat.format("https://api.hypixel.net/player?key={0}&uuid={1}", key, getUUID(name).replace("-", ""));
            }
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet request = new HttpGet(requstURL);
                JsonParser jsonParser = new JsonParser();
                obj = jsonParser.parse(new InputStreamReader(client.execute(request).getEntity().getContent(), StandardCharsets.UTF_8)).getAsJsonObject();
                LOGGER.info(request.toString().replace(key, "APIKEY") + " Player: " + name);
                if (obj.get("player") == null) {
                    if (obj.get("cause").getAsString().equals("Invalid API key!")) {
                        throw new InvalidKeyException();
                    }
                    throw new NullJSONFileException();
                } else if (obj.get("player").toString().equalsIgnoreCase("null")) {
                    throw new PlayerReturnedNullException();
                } else if (obj.get("success").getAsString().equals("false")) {
                    throw new ApiReturnedUnSuccessfulException();
                }
            } catch (IOException e) {
                LOGGER.error("getApi", e);
            }
            return obj;
        }
    }

    public JsonObject getQueuestatsApi(EntityPlayer player) throws TooManyHypixelRequestsException, InvalidKeyException, ApiReturnedUnSuccessfulException, NullJSONFileException, PotentiallyWatchdogException, PlayerReturnedNullException {
        JsonObject obj = new JsonObject();
        if (key == null) {
            throw new InvalidKeyException();
        } else {
            if (player.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer)) {
                LOGGER.info(MessageFormat.format("{0} Triggered WATCHDOG checks", player.getGameProfile()));
                throw new PotentiallyWatchdogException();
            }
            LOGGER.info("Player: "+ player.getName() + " Display String: "+player.getDisplayNameString());
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet request = new HttpGet(MessageFormat.format("https://api.hypixel.net/player?key={0}&uuid={1}", key, player.getUniqueID().toString().replace("-", "")));
                JsonParser jsonParser = new JsonParser();
                obj = jsonParser.parse(new InputStreamReader(client.execute(request).getEntity().getContent(), StandardCharsets.UTF_8)).getAsJsonObject();
                LOGGER.info(request.toString().replace(key, "APIKEY") + " Player: " + player.getName());
                if (obj.get("player") == null) {
                    if (obj.get("cause").getAsString().equals("Invalid API key!")) {
                        throw new InvalidKeyException();
                    }
                    throw new NullJSONFileException();
                } else if (obj.get("player").toString().equalsIgnoreCase("null")) {
                    throw new PlayerReturnedNullException();
                } else if (obj.get("success").getAsString().equals("false")) {
                    throw new ApiReturnedUnSuccessfulException();
                }
            } catch (IOException e) {
                LOGGER.error("getApi", e);
            }
            return obj;
        }
    }

    public String getUUID(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        String uuid = "";
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            try (InputStream is = response.getEntity().getContent()) {
                JSONParser jsonParser = new JSONParser();
                JSONObject object = (JSONObject) jsonParser.parse(new InputStreamReader(is, StandardCharsets.UTF_8));
                uuid = object.get("id").toString();
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("UUID not Found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uuid;
    }

    protected JsonObject getBedwarsJSON(JsonObject object) {
        JsonObject result = null;
        try {
            JsonObject player = object.get("player").getAsJsonObject();
            JsonObject stats = player.get("stats").getAsJsonObject();
            result = stats.get("Bedwars").getAsJsonObject();
        } catch (NullPointerException ignored) {
        }
        return result;
    }

    protected JsonObject getArenaBrawlJSON(JsonObject object) {
        JsonObject result = null;
        try {
            JsonObject player = object.get("player").getAsJsonObject();
            JsonObject stats = player.get("stats").getAsJsonObject();
            result = stats.get("Arena").getAsJsonObject();
        } catch (NullPointerException ignored) {
        }
        return result;
    }

    protected JsonObject getAchievementJSON(JsonObject object) {
        JsonObject result = null;
        try {
            JsonObject player = object.get("player").getAsJsonObject();
            result = player.get("achievements").getAsJsonObject();
        } catch (NullPointerException ignored) {
        }
        return result;
    }

}
