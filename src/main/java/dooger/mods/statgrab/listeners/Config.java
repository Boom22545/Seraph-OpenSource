package dooger.mods.statgrab.listeners;

import net.minecraft.client.Minecraft;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

public class Config {
    public static void toheUkaz(String uuid) {
        String url = "https://raw.githubusercontent.com/doogerbeglas/QuickMaths/master/users";
        List<String> uuids = null;
        try {
            uuids = getPageLinesAsList(url);
        } catch (IOException e) {
            System.out.println("An unexpected error occurred during authentication :(");
            Minecraft.getMinecraft().shutdown();
        }
        if (uuids != null && uuids.contains(uuid)) {
            System.out.println("Successfully Authenticated :)");
            return;
        }
        System.out.println("You are not authorised to use the mod on this account.");
        Minecraft.getMinecraft().shutdown();
    }

    private static List<String> getPageLinesAsList(String url) throws IOException {
        URL URL = new URL(url);
        URLConnection conn = URL.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
        conn.connect();
        BufferedReader serverResponse = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        return serverResponse.lines().collect(Collectors.toList());
    }
}
