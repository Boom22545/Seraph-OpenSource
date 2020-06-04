package si.seraph.mods.opensource.seraphapi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.entity.player.EntityPlayer;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import si.seraph.mods.opensource.seraphapi.config.ModConfig;
import si.seraph.mods.opensource.seraphapi.exceptions.ApiReturnedUnSuccessfulException;
import si.seraph.mods.opensource.seraphapi.exceptions.InvalidKeyException;
import si.seraph.mods.opensource.seraphapi.exceptions.NullJSONFileException;
import si.seraph.mods.opensource.seraphapi.exceptions.TooManyHypixelRequestsException;
import si.seraph.mods.opensource.seraphapi.utils.References;

public class HypixelAPI {
    private final String key = ModConfig.getInstance().getApiKey();

    public static HypixelAPI getInstance() { return new HypixelAPI(); }

    public JsonObject getApi(String name) throws TooManyHypixelRequestsException, InvalidKeyException, ApiReturnedUnSuccessfulException, NullJSONFileException {
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
                System.out.println(request.toString().replace(key, "APIKEY") + " Player: " + name);
                if (obj.get("player") == null) {
                    if (obj.get("cause").getAsString().equals("Invalid API key!")) {
                        throw new InvalidKeyException();
                    }
                    throw new NullJSONFileException();
                } else if (obj.get("success").getAsString().equals("false")) {
                    throw new ApiReturnedUnSuccessfulException();
                }
            } catch (IOException e) {
                System.out.println("getApi");
                e.printStackTrace();
            }
            return obj;
        }
    }

    public JsonObject getApi(EntityPlayer player) throws TooManyHypixelRequestsException, InvalidKeyException, ApiReturnedUnSuccessfulException, NullJSONFileException {
        JsonObject obj = new JsonObject();
        if (key == null) {
            throw new InvalidKeyException();
        } else {
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet request = new HttpGet(MessageFormat.format("https://api.hypixel.net/player?key={0}&uuid={1}", key, player.getUniqueID().toString().replace("-", "")));
                obj = new JsonParser().parse(new InputStreamReader(client.execute(request).getEntity().getContent(), StandardCharsets.UTF_8)).getAsJsonObject();
                System.out.println(request.toString().replace(key, "APIKEY") + " Player: " + player.getName());
                if (obj.get("player") == null) {
                    if (obj.get("cause").getAsString().equals("Invalid API key!")) {
                        throw new InvalidKeyException();
                    }
                    throw new NullJSONFileException();
                } else if (obj.get("success").getAsString().equals("false")) {
                    throw new ApiReturnedUnSuccessfulException();
                }
            } catch (IOException e) {
                System.out.println("getApi");
                e.printStackTrace();
            }
            return obj;
        }
    }

    public JsonObject getApi(HypixelAPITypes types, String name) throws TooManyHypixelRequestsException, InvalidKeyException, ApiReturnedUnSuccessfulException, NullJSONFileException {
        JsonObject obj = new JsonObject();
        if (key == null) {
            throw new InvalidKeyException();
        } else {
            String uuid;
            if (name.length() < 32) {
                uuid = getUUID(name).replace("-", "");
            } else if (References.UUID.matcher(name).matches() || References.UUID_NO_DASHES.matcher(name).matches()) {
                uuid = (name).replace("-", "");
            } else {
                uuid = getUUID(name).replace("-", "");
            }
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet httpGet = new HttpGet(MessageFormat.format("https://api.hypixel.net/{0}?key={1}&uuid={2}", types.toApiRequest(), key, uuid));
                JsonParser jsonParser = new JsonParser();
                obj = jsonParser.parse(new InputStreamReader(client.execute(httpGet).getEntity().getContent(), StandardCharsets.UTF_8)).getAsJsonObject();
                System.out.println(httpGet.toString().replace(key, "APIKEY") + " Player: " + name);
            } catch (IOException e) {
                System.out.println("getApi");
                e.printStackTrace();
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
