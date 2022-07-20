package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.entity.api.message.MessageEntity;
import sdh.qqbot.entity.api.weather.WeatherCityEntity;
import sdh.qqbot.entity.api.weather.WeatherEntity;
import sdh.qqbot.utils.formatWeatherInfo;

/**
 * 天气查询模块
 *
 * @author SDH
 */
@Slf4j
public class queryWeather {
    public static void weatherManager(MessageEntity message) {
        String[] msgArr = message.getMessage().split(" ");
        if ("帮助".equals(msgArr[1])) {
            QBotSendMessageController.sendMsg(message, help());
            return;
        }
        String city;
        switch (msgArr.length) {
            case 2:
                city = msgArr[1];
                queryWeatherByCityString(message, city);
                break;
            case 3:
                city = msgArr[1];
                try {
                    int dayCount = Integer.parseInt(msgArr[2]);
                    queryWeatherByCityStringAndDayCount(message, city, dayCount);
                } catch (Exception e) {
                    QBotSendMessageController.sendMsg(message, "参数错误");
                }
                break;
        }

    }

    /**
     * 查询天气
     *
     * @param message 消息实体
     * @param city    城市信息
     */
    private static void queryWeatherByCityString(MessageEntity message, String city) {
        log.info("开始查询" + city + "的天气");
        //获取经纬度
        WeatherCityEntity weatherCityEntity = QueryApiManagerController.queryCityDesByCityName(city);
        log.info(weatherCityEntity.toString());
        //根据经纬度查询天气
        WeatherEntity weatherEntity = QueryApiManagerController.queryWeatherByLocation(weatherCityEntity.getGeocodes().get(0).getLocation());
        QBotSendMessageController.sendMsg(message, formatWeatherInfo.format(weatherEntity, 2, weatherCityEntity.getGeocodes().get(0).getFormattedAddress()));
    }

    /**
     * 查询天气
     *
     * @param message  消息实体
     * @param city     城市信息
     * @param dayCount 预报天数
     */
    private static void queryWeatherByCityStringAndDayCount(MessageEntity message, String city, int dayCount) {
        log.info("开始查询" + city + "的天气");
        //获取经纬度
        WeatherCityEntity weatherCityEntity = QueryApiManagerController.queryCityDesByCityName(city);
        log.info(weatherCityEntity.toString());
        //根据经纬度查询天气
        WeatherEntity weatherEntity = QueryApiManagerController.queryWeatherByLocation(weatherCityEntity.getGeocodes().get(0).getLocation());
        QBotSendMessageController.sendMsg(message, formatWeatherInfo.format(weatherEntity, dayCount, weatherCityEntity.getGeocodes().get(0).getFormattedAddress()));
    }

    /**
     * 天气查询帮助
     *
     * @return 帮助文本
     */
    private static String help() {
        StringBuilder builder = new StringBuilder();
        builder.append("天气帮助：").append("%0d");
        builder.append("天气 [地址]（越详细越好） [预报天数](可选，最多7天)");
        return builder.toString();
    }
}
