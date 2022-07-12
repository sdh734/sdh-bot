package sdh.qqbot.controller;

import com.alibaba.fastjson.JSON;
import sdh.qqbot.config.ApiKeyConfig;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.entity.LickTheDogDiaryEntity;
import sdh.qqbot.entity.WeatherCityEntity;
import sdh.qqbot.utils.OkHttpUtil;

/**
 * 舔狗日记查询接口
 *
 * @author fusheng
 */
public class LickTheDogDiaryController {

    public static LickTheDogDiaryEntity queryLickTheDogDiary() {
        String LICKTHEDOGDIARY_URL = ApiUrlConfig.LICKTHEDOGDIARY_URL;
        String json = OkHttpUtil.get(LICKTHEDOGDIARY_URL);
        json = json.substring(0, json.lastIndexOf("1"));
        return JSON.parseObject(json, LickTheDogDiaryEntity.class);
    }
}
