package sdh.qqbot.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 天气代码
 *
 * @author SDH
 */
public class WeatherCode {
    Map<String, String> sky = new HashMap<>();

    private WeatherCode() {
        sky.put("CLEAR_DAY", "晴");
        sky.put("CLEAR_NIGHT", "晴");
        sky.put("PARTLY_CLOUDY_DAY", "多云");
        sky.put("PARTLY_CLOUDY_NIGHT", "多云");
        sky.put("CLOUDY", "阴");
        sky.put("LIGHT_HAZE", "轻度雾霾");
        sky.put("MODERATE_HAZE", "中度雾霾");
        sky.put("HEAVY_HAZE", "重度雾霾");
        sky.put("LIGHT_RAIN", "小雨");
        sky.put("MODERATE_RAIN", "中雨");
        sky.put("HEAVY_RAIN", "大雨");
        sky.put("STORM_RAIN", "暴雨");
        sky.put("FOG", "雾");
        sky.put("LIGHT_SNOW", "小雪");
        sky.put("MODERATE_SNOW", "中雪");
        sky.put("HEAVY_SNOW", "大雪");
        sky.put("STORM_SNOW", "暴雪");
        sky.put("DUST", "浮尘");
        sky.put("SAND", "沙尘");
        sky.put("WIND", "大风");
    }

    public static WeatherCode getInstance() {
        return WeatherCodeInstance.INSTANCE;
    }

    public String queryByCode(String code) {
        return sky.get(code);
    }

    private static class WeatherCodeInstance {
        private static final WeatherCode INSTANCE = new WeatherCode();
    }
}
