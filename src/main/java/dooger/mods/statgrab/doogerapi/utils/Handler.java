package dooger.mods.statgrab.doogerapi.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Handler {

    public static final Locale LOCALE = getLocale();

    private static Locale getLocale() {
        return new Locale(System.getProperty("user.language"), System.getProperty("user.country"));
    }

    public static void asExecutor(Runnable runnable) {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("Quick Maths" + "-%d").build());
        executorService.submit(runnable);
    }

    public static Gson getGson() {
        return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().setDateFormat(getDate().toPattern()).create();
    }

    public static SimpleDateFormat getDate() {
        return new SimpleDateFormat("EEEEE dd MMMMM yyyy", LOCALE);
    }

}
