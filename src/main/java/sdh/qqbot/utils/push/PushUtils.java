package sdh.qqbot.utils.push;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.config.ApiKeyConfig;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.utils.okhttp.OkHttpUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 消息推送工具
 */
@Slf4j
public class PushUtils {
    public static boolean push(String message) {
        if (!Objects.equals(ApiKeyConfig.SERVERCHAN_TOKEN, "")) {
            log.info("开始使用Server酱推送微信消息。");
            String url = ApiUrlConfig.SERVERCHAN_URL + "?title=" + URLEncoder.encode(message, StandardCharsets.UTF_8);
            OkHttpUtil.get(url);
            log.info("微信消息发送成功。");
        } else if (!Objects.equals(ApiKeyConfig.PUSHPLUS_TOKEN, "")) {
            log.info("开始使用PushPlus推送微信消息。");
            JSONObject request = new JSONObject();
            request.put("token", ApiKeyConfig.PUSHPLUS_TOKEN);
            request.put("title", "Notify");
            request.put("content", message);
            request.put("template", "txt");
            OkHttpUtil.post(ApiUrlConfig.PUSHPLUS_URL, request.toString(), null);
            log.info("微信消息发送成功。");
            return true;
        } else {
            log.info("微信消息发送失败，未配置推送平台Token。");
            return false;
        }
        return false;
    }
}
