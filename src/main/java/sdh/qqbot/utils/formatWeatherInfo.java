package sdh.qqbot.utils;

import sdh.qqbot.entity.WeatherEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 输出格式化后的天气信息
 */
public class formatWeatherInfo {
    private static final WeatherCode weatherCode = WeatherCode.getInstance();
    private static final String strDateFormat = "MM月dd日";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdf1 = new SimpleDateFormat(strDateFormat);


    /**
     * 天气预报
     *
     * @param weather  天气实体类
     * @param dayCount 预报天数最大值 5
     * @return 格式化后的字符串
     */
    public static String format(WeatherEntity weather, int dayCount) {
        StringBuilder builder = new StringBuilder("当前天气情况：%0d");
        builder.append("当前温度：").append(weather.getResult().getRealtime().getTemperature()).append("℃%0d");
        builder.append("当前天气：").append(weatherCode.queryByCode(weather.getResult().getRealtime().getSkycon())).append("%0d");
        builder.append("温馨提示：").append(weather.getResult().getForecastKeypoint()).append("%0d");
        builder.append("%0d");
        builder.append("天气预报：%0d");
        if (dayCount > 4) {
            dayCount = 4;
        }
        for (int i = 0; i <= dayCount; i++) {
            String date = weather.getResult().getDaily().getTemperature().get(i).getDate();
            date = date.split("T")[0];
            Date date1 = null;
            try {
                date1 = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            builder.append(sdf1.format(date1)).append("天气预报：%0d");
            builder.append("天气情况：").append(weatherCode.queryByCode(weather.getResult().getDaily().getSkycon().get(i).getValue())).append("%0d");
            builder.append("最高温度：").append(weather.getResult().getDaily().getTemperature().get(i).getMax()).append("℃；");
            builder.append("最低温度：").append(weather.getResult().getDaily().getTemperature().get(i).getMin()).append("℃%0d");
        }
        return builder.toString();
    }
}
