package sdh.qqbot.utils.okhttp;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * OkHttp单例
 *
 * @author SDH
 */
public class OkHttpInstance {
    private static final OkHttpClient instance = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .pingInterval(30, TimeUnit.SECONDS)
            .build();

    public static OkHttpClient getInstance() {
        return instance;
    }
}
