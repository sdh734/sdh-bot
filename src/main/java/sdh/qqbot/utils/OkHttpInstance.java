package sdh.qqbot.utils;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class OkHttpInstance {
    private static final OkHttpClient instance = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .pingInterval(5, TimeUnit.SECONDS)
            .build();

    public static OkHttpClient getInstance() {
        return instance;
    }
}
