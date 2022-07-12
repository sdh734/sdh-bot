package sdh.qqbot.controller;

import com.alibaba.fastjson.JSON;
import sdh.qqbot.config.ApiKeyConfig;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.entity.WeatherCityEntity;
import sdh.qqbot.entity.WeatherEntity;
import sdh.qqbot.utils.OkHttpUtil;

/**
 * 查询天气接口
 * @author SDH
 */
public class WeatherController {

    /**
     * 通过城市字符串查询所在经纬度
     *
     * @param City 城市中文字符串
     * @return 城市实体对象
     */
    public static WeatherCityEntity queryCityByString(String City) {
        String AMAP_URL = ApiUrlConfig.AMAP_URL;
        AMAP_URL += ApiKeyConfig.AMAP_TOKEN;
        AMAP_URL += "&address=";
        AMAP_URL += City;
        String json = OkHttpUtil.get(AMAP_URL);
        return JSON.parseObject(json, WeatherCityEntity.class);
    }

    /**
     * 根据经纬度查询天气
     *
     * @param location 城市经纬度
     * @return 天气对象
     */
    public static WeatherEntity queryWeatherByLocation(String location) {
        String CY_URL = ApiUrlConfig.CY_URL + ApiKeyConfig.CY_TOKEN + "/";
        CY_URL += location;
        CY_URL += "/weather.json";
        String json = OkHttpUtil.get(CY_URL);
        return JSON.parseObject(json, WeatherEntity.class);
    }
}
