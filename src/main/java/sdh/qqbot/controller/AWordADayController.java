package sdh.qqbot.controller;

import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.utils.OkHttpUtil;

/**
 * @author fusheng
 * 每日一言接口
 */
public class AWordADayController {
    public static String queryAWordADay(){
        String AWORDADAY_URL = ApiUrlConfig.AWORDADAY_URL;
        return OkHttpUtil.get(AWORDADAY_URL);
    }
}
