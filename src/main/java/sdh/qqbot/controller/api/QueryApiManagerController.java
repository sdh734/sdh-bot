package sdh.qqbot.controller.api;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import okhttp3.Headers;
import okhttp3.internal.http2.Header;
import sdh.qqbot.config.ApiKeyConfig;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.entity.api.TwoDimensionalSpaceEntity;
import sdh.qqbot.entity.api.*;
import sdh.qqbot.entity.api.weather.WeatherCityEntity;
import sdh.qqbot.entity.api.weather.WeatherEntity;
import sdh.qqbot.module.Song;
import sdh.qqbot.utils.OkHttpUtil;

import java.util.HashMap;

/**
 * 查询Api接口统一管理，不涉及数据库操作。
 *
 * @author SDH
 */
public class QueryApiManagerController {
    /**
     * 每日一言接口
     *
     * @author fusheng
     */
    public static String queryAWordADay() {
        String AWORDADAY_URL = ApiUrlConfig.AWORDADAY_URL;
        return OkHttpUtil.get(AWORDADAY_URL);
    }

    /**
     * 古诗词一言查询接口
     *
     * @author SDH
     */
    public static GUSHIEntity queryGUSHI(String type) {
        String gushi_url = ApiUrlConfig.GUSHI_URL;
        gushi_url += type;
        String json = OkHttpUtil.get(gushi_url);
        return JSON.parseObject(json, GUSHIEntity.class);
    }

    /**
     * 每日早报查询接口
     *
     * @author SDh
     */
    public static DailyNewsEntity queryDailyNews() {
        String url = ApiUrlConfig.DAILYNEWS_URL + "?token=" + ApiKeyConfig.DAILYNEWS_TOKEN;
        return JSON.parseObject(OkHttpUtil.get(url), DailyNewsEntity.class);
    }

    /**
     * 舔狗日记查询接口
     *
     * @author fusheng
     */
    public static LickTheDogDiaryEntity queryLickTheDogDiary() {
        String LICKTHEDOGDIARY_URL = ApiUrlConfig.LICKTHEDOGDIARY_URL;
        String json = OkHttpUtil.get(LICKTHEDOGDIARY_URL);
        return JSON.parseObject(json, LickTheDogDiaryEntity.class);
    }

    /**
     * 伤感语录接口
     *
     * @author fusheng
     */
    public static String querySadSayings() {
        String url = ApiUrlConfig.SADSAYINGS_URL;
        return OkHttpUtil.get(url);
    }

    /**
     * 语录查询接口
     *
     * @author fusheng
     */
    public static SayingEntity querySaying(String type) {
        String SAYING_URL = ApiUrlConfig.SAYING_URL;
        SAYING_URL += "?c=";
        SAYING_URL += type;
        SAYING_URL += "&encode=json";
        String json = OkHttpUtil.get(SAYING_URL);
        return JSON.parseObject(json, SayingEntity.class);
    }


    /**
     * 历史上的今天接口
     *
     * @author fusheng
     */
    public static TodayOnHistoryEntity queryTodayOnHistoryEntity() {
        String url = ApiUrlConfig.TODAYONHISTORY_URL;
        String json = OkHttpUtil.get(url);
        return JSON.parseObject(json, TodayOnHistoryEntity.class);
    }

    /**
     * 通过城市字符串查询所在经纬度
     *
     * @param City 城市中文字符串
     * @return 城市实体对象
     */
    public static WeatherCityEntity queryCityDesByCityName(String City) {
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

    /**
     * 二次元图片查询接口
     *
     * @return 图片链接
     */
    public static TwoDimensionalSpaceEntity queryTwoDimensionalSpace() {
        String url = ApiUrlConfig.TWODIMENSIONALSPACE_URL;
        String json = OkHttpUtil.get(url);
        return JSON.parseObject(json, TwoDimensionalSpaceEntity.class);
    }

    /**
     * 摸鱼人查询接口
     *
     * @return 摸鱼人链接
     */
    public static SlackOffEntity querySlackOff() {
        String url = ApiUrlConfig.SLACKOFF;
        String json = OkHttpUtil.get(url);
        return JSON.parseObject(json, SlackOffEntity.class);
    }

    /**
     * cos图片查询接口
     *
     * @return
     */
    public static String queryCosImg() {
        String url = ApiUrlConfig.COSIMG_API;
        return OkHttpUtil.get(url);
    }

    /**
     * 查询歌曲接口
     *
     * @param songName 歌曲名字
     * @return 返回json类型数据
     */
    public static SongModelEntity querySong(String songName) {
        String url = ApiUrlConfig.SONG_API + "?msg=" + songName + "&b=1&type=json";
        String json = OkHttpUtil.get(url);
        return JSON.parseObject(json.substring(5), SongModelEntity.class);
    }

    /**
     * 查询歌曲id接口
     *
     * @param songName
     * @return
     */
    public static SongIdEntity querySongId(String songName) {
        String url = ApiUrlConfig.SONG_163_API + "?s=" + songName + "&offset=0&limit=1&type=1";
        String json = OkHttpUtil.post163Song(url);
        SongIdModelEntity songIdModelEntity = JSON.parseObject(json, SongIdModelEntity.class);
        if (!"200".equals(songIdModelEntity.getCode())) {
            return null;
        }
        SongIdCorEntity songIdCorEntity = JSON.parseObject(songIdModelEntity.getResult(), SongIdCorEntity.class);
        return JSON.parseObject(songIdCorEntity.getSongs().get(0), SongIdEntity.class);
    }

    public static String queryGoodMorning() {
        String url = ApiUrlConfig.GOODMORNING_API;
        return OkHttpUtil.get(url);
    }

    public static String queryTheGirlImg() {
        String url = ApiUrlConfig.THEGIRLIMG_API;
        return OkHttpUtil.getImgUrl(url);
    }

}
