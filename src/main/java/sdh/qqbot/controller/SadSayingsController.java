package sdh.qqbot.controller;

import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.utils.OkHttpUtil;

/**
 * 伤感语录接口
 * @author fusheng
 */
public class SadSayingsController {

    public static String querySadSayings(){
        String url = ApiUrlConfig.SADSAYINGS_URL;
        String json = OkHttpUtil.get(url);
        return json;
    }
}
