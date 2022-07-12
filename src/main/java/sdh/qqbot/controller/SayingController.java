package sdh.qqbot.controller;

import com.alibaba.fastjson.JSON;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.entity.SayingEntity;
import sdh.qqbot.utils.OkHttpUtil;

/**
 * 语录查询接口
 *
 * @author fusheng
 */
public class SayingController {
    public static SayingEntity querySaying(String type) {
        String SAYING_URL = ApiUrlConfig.SAYING_URL;
        SAYING_URL += "?c=";
        SAYING_URL += type;
        SAYING_URL += "&encode=json";
        String json = OkHttpUtil.get(SAYING_URL);
        return JSON.parseObject(json, SayingEntity.class);
    }
}
