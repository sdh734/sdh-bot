package sdh.qqbot.controller;

import com.alibaba.fastjson.JSON;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.entity.GUSHIEntity;
import sdh.qqbot.utils.OkHttpUtil;

/**
 * 古诗词一言查询接口
 *
 * @author SDH
 */
public class AWordGUSHIController {
    public static GUSHIEntity queryGUSHI(String type) {
        String gushi_url = ApiUrlConfig.GUSHI_URL;
        gushi_url += type;
        String json = OkHttpUtil.get(gushi_url);
        return JSON.parseObject(json, GUSHIEntity.class);
    }
}
