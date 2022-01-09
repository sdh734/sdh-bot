package sdh.qqbot.utils;

import okhttp3.OkHttpClient;

public class OkHttpInstance {
    private OkHttpInstance() {
    }

    private static class OkHttpUtilInstance {
        private static final OkHttpClient INSTANCE = new OkHttpClient();
    }

    public static OkHttpClient getInstance() {
        return OkHttpUtilInstance.INSTANCE;
    }
}
