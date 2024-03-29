package sdh.qqbot.utils.werther;

import sdh.qqbot.entity.api.weather.WeatherEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 天气信息格式化工具类
 *
 * @author SDH
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
    public static String format(WeatherEntity weather, int dayCount, String city) {
        StringBuilder builder = new StringBuilder(city + "天气情况：\n");
        builder.append("当前温度：").append(weather.getResult().getRealtime().getTemperature()).append("℃\n");
        builder.append("当前天气：").append(weatherCode.queryByCode(weather.getResult().getRealtime().getSkycon())).append("\n");
        builder.append("温馨提示：").append(weather.getResult().getForecastKeypoint()).append("\n");
        builder.append("\n");
        builder.append("天气预报：\n");
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
            builder.append(sdf1.format(date1)).append("天气预报：\n");
            builder.append("天气情况：").append(weatherCode.queryByCode(weather.getResult().getDaily().getSkycon().get(i).getValue())).append("\n");
            builder.append("最高温度：").append(weather.getResult().getDaily().getTemperature().get(i).getMax()).append("℃；");
            builder.append("最低温度：").append(weather.getResult().getDaily().getTemperature().get(i).getMin()).append("℃\n");
        }
        return builder.toString();
    }
}
