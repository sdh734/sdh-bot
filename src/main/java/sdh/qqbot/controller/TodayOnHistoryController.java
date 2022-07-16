package sdh.qqbot.controller;

import com.alibaba.fastjson.JSON;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.entity.TodayOnHistoryEntity;
import sdh.qqbot.utils.OkHttpUtil;

/**
 * 历史上的今天接口
 * @author fusheng
 */
public class TodayOnHistoryController {

    public static TodayOnHistoryEntity queryTodayOnHistoryEntity(){
        String url = ApiUrlConfig.TODAYONHISTORY_URL;
        String json = OkHttpUtil.get(url);
        return JSON.parseObject(json,TodayOnHistoryEntity.class);
    }
}
