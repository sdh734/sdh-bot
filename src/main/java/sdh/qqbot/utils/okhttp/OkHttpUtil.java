package sdh.qqbot.utils.okhttp;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

/**
 * OkHttp工具类
 *
 * @author SDh
 */
public class OkHttpUtil {
    private static final OkHttpClient client = OkHttpInstance.getInstance();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static String get(String url) {
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.66 Safari/537.36 Edg/103.0.1264.44")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NaN";
    }

    public static String post(String url, String requestJSON, Headers headers) {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(requestJSON, JSON))
                .build();
        try {
            Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String post163Song(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36(KHTML, like Gecko) Chrome/35.0.1916.138 Safari/537.36")
                .addHeader("Cookie", "NMTID=00OP8GEUDjo7mKQLUugrNNvI6t73UMAAAGCK7Sejg")
                .build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getImgUrl(String url) {
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.66 Safari/537.36 Edg/103.0.1264.44")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return Objects.requireNonNull(response.request().url()).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NaN";
    }
}
