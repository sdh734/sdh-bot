package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.QBotSendMessageController;
import sdh.qqbot.controller.WeatherController;
import sdh.qqbot.entity.MessageEntity;
import sdh.qqbot.entity.WeatherCityEntity;
import sdh.qqbot.entity.WeatherEntity;
import sdh.qqbot.utils.formatWeatherInfo;

/**
 * 天气查询模块
 */
@Slf4j
public class queryWeather {
    public static void weatherManager(MessageEntity message) {
        String[] msgArr = message.getMessage().split(" ");
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

    private static void queryWeatherByCityString(MessageEntity message, String city) {
        log.info("开始查询" + city + "的天气");
        //获取经纬度
        WeatherCityEntity weatherCityEntity = WeatherController.queryCityByString(city);
        log.info(weatherCityEntity.toString());
        //根据经纬度查询天气
        WeatherEntity weatherEntity = WeatherController.queryWeatherByLocation(weatherCityEntity.getGeocodes().get(0).getLocation());
        QBotSendMessageController.sendMsg(message, formatWeatherInfo.format(weatherEntity, 2));
    }

    private static void queryWeatherByCityStringAndDayCount(MessageEntity message, String city, int dayCount) {
        log.info("开始查询" + city + "的天气");
        //获取经纬度
        WeatherCityEntity weatherCityEntity = WeatherController.queryCityByString(city);
        log.info(weatherCityEntity.toString());
        //根据经纬度查询天气
        WeatherEntity weatherEntity = WeatherController.queryWeatherByLocation(weatherCityEntity.getGeocodes().get(0).getLocation());
        QBotSendMessageController.sendMsg(message, formatWeatherInfo.format(weatherEntity, dayCount));
    }
}
