package sdh.qqbot.Utils;

import okhttp3.*;

import java.io.IOException;

public class OkHttpUtil {
    private static final OkHttpClient client = OkHttpInstance.getInstance();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static String get(String url)  {

        final Request request = new Request.Builder()
                .url(url)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NaN";
    }

    public static String post(String url, String json) {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
