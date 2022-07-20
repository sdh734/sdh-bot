package sdh.qqbot.module;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.element.TextElement;
import com.freewayso.image.combiner.enums.OutputFormat;
import com.freewayso.image.combiner.enums.ZoomMode;
import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.api.TencentNcovApiController;
import sdh.qqbot.controller.database.InfectcountController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.message.MessageEntity;
import sdh.qqbot.entity.api.tencent.TencentNcovRiskAreaEntity;
import sdh.qqbot.entity.api.weather.WeatherCityEntity;
import sdh.qqbot.entity.database.Infectcount;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 疫情信息查询，包括如下功能：
 * 查询疫情今日确诊人数信息、总人数信息等，风险地区信息。
 *
 * @author SDh
 */
@Slf4j
public class NcovInfo {
    public static void NcovInfoManager(MessageEntity message) {
        String[] msgArr = message.getMessage().split(" ");
        if (msgArr.length == 1) {
            help(message);
        } else {
            String city = msgArr[1];
            //获取风险地区信息
            TencentNcovRiskAreaEntity riskAreaEntity = TencentNcovApiController.queryRiskArea(city);
            List<String> hList = new ArrayList<>();
            List<String> mList = new ArrayList<>();
            getRiskAreaList(riskAreaEntity, hList, mList);
            //获取最新的疫情人数信息
            WeatherCityEntity cityEntity = QueryApiManagerController.queryCityDesByCityName(city);//获取地址对应的省市级信息
            Infectcount infectcount;//人数数据实体类
            String path;
            if (city.length() <= 3) {
                //判断输入的是省份还是市级，返回对应层级的数据
                ClassPathResource resource = new ClassPathResource("assets/map.json");
                JSONArray array = JSON.parseArray(resource.readUtf8Str());
                List<JSONObject> jsonObjects = array.toJavaList(JSONObject.class);
                boolean contains = false;
                for (JSONObject a : jsonObjects) {
                    contains = a.getString("provinceName").contains(city);
                    if (contains) break;
                }
                if (contains) {
                    //省级
                    String provinceName = cityEntity.getGeocodes().get(0).getProvince();
                    String provinceName1 = provinceName.replaceAll("[省|自治区|直辖市|特别行政区]", "");
                    infectcount = InfectcountController.queryNcovInfoByProvinceName(provinceName1);
                    path = GenerateNcovInfoImg(provinceName, infectcount, hList, mList);
                } else {
                    //市级
                    String cityName = cityEntity.getGeocodes().get(0).getCity();
                    String cityName1 = cityName.replaceAll("[市|自治州|盟|地区]", "");
                    infectcount = InfectcountController.queryNcovInfoByCityName(cityName1);
                    path = GenerateNcovInfoImg(cityName, infectcount, hList, mList);
                }
            } else {
                //市级
                String cityName = cityEntity.getGeocodes().get(0).getCity();
                String cityName1 = cityName.replaceAll("[市|自治州|盟|地区]", "");
                infectcount = InfectcountController.queryNcovInfoByCityName(cityName1);
                path = GenerateNcovInfoImg(cityName, infectcount, hList, mList);
            }
            log.info("疫情通报图片路径：" + path);
            QBotSendMessageController.sendMsg(message, "[CQ:image,file=file:///" + path + ",id=40000]");
        }

    }

    /**
     * 帮助信息
     *
     * @param message 消息实体类
     */
    private static void help(MessageEntity message) {
        String stringBuilder = "疫情功能帮助：%0d" + "疫情 [关键词] 关键词为地址参数，可为省份、市级";
        QBotSendMessageController.sendMsg(message, stringBuilder);
    }

    /**
     * 根据腾讯疫情风险地区数据信息，构造高、中风险地区列表
     *
     * @param entity 腾讯疫情风险地区数据信息实体
     * @param hList  高风险地区列表
     * @param mList  中风险地区列表
     * @return 高、中风险地区列表
     */
    private static boolean getRiskAreaList(TencentNcovRiskAreaEntity entity, List<String> hList, List<String> mList) {

        if (entity.getData() == null) {
            return false;
        }

        //高风险
        for (TencentNcovRiskAreaEntity.DataDTO data : entity.getData()) {
            if (data.getType().equals("1")) {
                //高风险
                hList.add(data.getArea());
            } else {
                //中风险
                mList.add(data.getArea());
            }
        }

        return true;
    }

    /**
     * 根据人数数据，风险地区列表生成疫情通报图片
     *
     * @param infectcount   人数数据实体类
     * @param hRiskAreaList 高风险地区列表
     * @param mRiskAreaList 低风险地区列表
     * @return 生成的文件绝对路径
     */
    private static String GenerateNcovInfoImg(String title, Infectcount infectcount, List<String> hRiskAreaList, List<String> mRiskAreaList) {
        int offsetY = 240;
        int offsetX;
        int numberFontSize = 75;//数字大小
        int titleFontSize = 120;//标题文字大小
        int wordFontSize = 80;//文字字体大小
        int canvasWidth = 1800;//画布宽度
        int canvasHeight = 1640 //基础高度
                + getHeight(hRiskAreaList, wordFontSize, canvasWidth) //高风险高度
                + getHeight(mRiskAreaList, wordFontSize, canvasWidth)//中风险高度
                + 50; //尾部高度
        BufferedImage bg;
        try {
            ClassPathResource resource = new ClassPathResource("assets/bg.jpg");
            bg = ImageIO.read(resource.getStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ImageCombiner combiner = new ImageCombiner(canvasWidth, canvasHeight, Color.white, OutputFormat.PNG);
        combiner.addImageElement(bg, 0, 0).setCenter(true).setAlpha(.8f).setHeight(combiner.getCanvasHeight()).setWidth(combiner.getCanvasWidth()).setZoomMode(ZoomMode.WidthHeight).setBlur(30);
        TextElement titleElement = combiner.addTextElement(title + "疫情情况", titleFontSize, 0, 240).setCenter(true).setColor(Color.black);
        offsetY += titleElement.getHeight() + 40;
        //开始绘制感染人数情况
        TextElement text1 = combiner.addTextElement("境外输入", wordFontSize, 0, offsetY).setCenter(true);
        offsetX = combiner.getCanvasWidth() / 2 - text1.getWidth() / 2;
        TextElement text2 = combiner.addTextElement("新增人数", wordFontSize, offsetX - 400, offsetY);
        offsetX += text1.getWidth();
        TextElement text3 = combiner.addTextElement("本土无症状", wordFontSize, offsetX + 80, offsetY);
        offsetY += 100;
        combiner.addTextElement(String.valueOf(infectcount.getTodaySuspect()), numberFontSize, getX(text3, infectcount.getTodaySuspect(), numberFontSize), offsetY);
        combiner.addTextElement(String.valueOf(infectcount.getTodayConfirm()), numberFontSize, getX(text2, infectcount.getTodayConfirm(), numberFontSize), offsetY);
        combiner.addTextElement(String.valueOf(infectcount.getTodayInput()), numberFontSize, getX(infectcount.getTodayInput(), combiner.getCanvasWidth(), numberFontSize), offsetY);

        offsetY += 100;
        TextElement text4 = combiner.addTextElement("本土确诊", wordFontSize, offsetX, offsetY).setCenter(true);
        offsetX = combiner.getCanvasWidth() / 2 - text4.getWidth() / 2;
        TextElement text5 = combiner.addTextElement("现存确诊", wordFontSize, offsetX - 400, offsetY);
        offsetX += text4.getWidth();
        TextElement text6 = combiner.addTextElement("累计确诊", wordFontSize, offsetX + 120, offsetY);
        offsetY += 100;
        combiner.addTextElement(String.valueOf(infectcount.getTotalStoreconfirm()), numberFontSize, getX(text5, infectcount.getTodayStoreconfirm(), numberFontSize), offsetY);
        combiner.addTextElement(String.valueOf(infectcount.getTotalConfirm()), numberFontSize, getX(text6, infectcount.getTotalConfirm(), numberFontSize), offsetY);
        combiner.addTextElement(String.valueOf(infectcount.getTodayLocation()), numberFontSize, getX(infectcount.getTodayLocation(), combiner.getCanvasWidth(), numberFontSize), offsetY);
        //结束绘制感染人数情况
        if (hRiskAreaList.size() == 0 && mRiskAreaList.size() == 0) {
            offsetY += 280;
            combiner.addTextElement("无高、中风险地区", titleFontSize - 10, 0, offsetY).setCenter(true).getHeight();//风险地区标题
        } else {
            //开始绘制风险地区情况
            offsetY += 200;
            offsetY += combiner.addTextElement("风险地区详情", titleFontSize - 10, 0, offsetY).setCenter(true).getHeight();//风险地区标题
            offsetY += 20;
            if (hRiskAreaList.size() == 0) {
                offsetY += combiner.addTextElement("无高风险地区", wordFontSize, 0, offsetY).setCenter(true).getHeight();//高风险地区标题
                offsetY += 10;
                offsetY += combiner.addTextElement("中风险地区", wordFontSize, 0, offsetY).setCenter(true).getHeight();//中风险地区标题
                offsetY += 10;
                for (String a : mRiskAreaList) {
                    //循环添加中风险地区数据
                    offsetY += 5 + combiner.addTextElement(a, 60, 0, offsetY).setCenter(true).setAutoBreakLine(600, 6).getHeight();
                }
            } else {
                offsetY += combiner.addTextElement("高风险地区", wordFontSize, 0, offsetY).setCenter(true).getHeight();//高风险地区标题
                offsetY += 30;
                for (String a : hRiskAreaList) {
                    //循环添加高风险地区数据
                    offsetY += 10 + combiner.addTextElement(a, 60, 0, offsetY).setCenter(true).setAutoBreakLine(1300, 6).getHeight();
                }
                offsetY += 30;
                if (mRiskAreaList.size() == 0) {
                    combiner.addTextElement("无中风险地区", wordFontSize, 0, offsetY).setCenter(true).getHeight();//中风险地区标题
                } else {
                    offsetY += combiner.addTextElement("中风险地区", wordFontSize, 0, offsetY).setCenter(true).getHeight();//中风险地区标题
                    offsetY += 30;
                    for (String a : mRiskAreaList) {
                        //循环添加中风险地区数据
                        offsetY += 10 + combiner.addTextElement(a, 60, 0, offsetY).setCenter(true).setAutoBreakLine(1300, 6).getHeight();
                    }
                }
            }
        }
        try {
            combiner.combine();
            FileWriter writer = new FileWriter("temp.png");
            File file = writer.writeFromStream(combiner.getCombinedImageStream());
            return file.getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static int getX(int number, int canvasWidth, int fontSize) {
        return canvasWidth / 2 - (String.valueOf(number).length() * (fontSize / 2) + 6) / 2;
    }

    private static int getX(TextElement textElement, int number, int fontSize) {
        int a = textElement.getX() + (textElement.getWidth() / 2);
        return a - (String.valueOf(number).length() * (fontSize / 2) + 6) / 2;
    }

    private static int getHeight(List<String> list, int fontSize, int width) {
        int count = 0;
        for (String s : list) {
            count += ((s.length() * fontSize) / width + 1) * fontSize;
        }
        return count;
    }
}
