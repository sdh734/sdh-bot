package sdh.qqbot;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSONObject;
import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.element.TextElement;
import com.freewayso.image.combiner.enums.OutputFormat;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sdh.qqbot.controller.api.TencentNcovApiController;
import sdh.qqbot.entity.api.message.MessageEntity;
import sdh.qqbot.entity.api.tencent.TencentNcovEntity;
import sdh.qqbot.module.NcovInfo;
import sdh.qqbot.utils.OkHttpUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class AppApplicationTests {
    @Test
    public void test11() {
        MessageEntity message =new MessageEntity();
        message.setMessage("疫情 长春");
        NcovInfo.NcovInfoManager(message);
    }


    public void test1() {
        String url = "http://bmfw.www.gov.cn/bjww/interface/interfaceJson";
        JSONObject body = new JSONObject();
        body.put("appId", "NcApplication");
        body.put("paasHeader", "zdww");
        body.put("key", "3C502C97ABDA40D0A60FBEE50FAAD1DA");
        body.put("nonceHeader", "123456789abcdefg");
        long time = System.currentTimeMillis() / 1000;
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        String digest = sha256.digestHex(time + "23y0ufFl5YxIyGrI8hWRUZmKkvtSjLQA123456789abcdefg" + time).toUpperCase();
        body.put("timestampHeader", time);
        body.put("signatureHeader", digest);
        String digHeader = sha256.digestHex(time + "fTN2pfuisxTavbTuYVSsNJHetwq5bJvCQkjjtiLM2dCratiA" + time).toUpperCase();
        Headers headers = new Headers.Builder().add("x-wif-nonce", "QkjjtiLM2dCratiA").add("x-wif-paasid", "smt-application").add("x-wif-timestamp", String.valueOf(time)).add("x-wif-signature", digHeader).build();
        String post = OkHttpUtil.post(url, body.toString(), headers);
        log.info(post);
    }


    public void Test2() {
        int offsetY = 80;
        int offsetX = 60;
        int numberFontSize = 40;
        int titleFontSize = 45;
        int wordFontSize = 30;
        int xzrs = 100;//新增人数
        int zyrs = 10;//治愈人数
        int swrs = 0;//死亡人数
        int jwsr = 5;//境外输入
        int zs = 1000;//确诊总数
        int wzz = 10;//无症状人数
        ImageCombiner combiner = new ImageCombiner(640, 1080, Color.white, OutputFormat.JPG);
        TextElement title = combiner.addTextElement("福建-三明疫情情况", titleFontSize, 0, 60).setCenter(true).setColor(Color.black);
        offsetY += title.getHeight();
        //开始绘制感染人数情况
        TextElement text1 = combiner.addTextElement("境外输入", wordFontSize, 0, offsetY).setCenter(true);
        offsetX = combiner.getCanvasWidth() / 2 - text1.getWidth() / 2;
        TextElement text2 = combiner.addTextElement("新增人数", wordFontSize, offsetX - 180, offsetY);
        offsetX += text1.getWidth();
        TextElement text3 = combiner.addTextElement("无症状人数", wordFontSize, offsetX + 50, offsetY);
        offsetY += 40;
        combiner.addTextElement(String.valueOf(wzz), 40, getX(text3, wzz, numberFontSize), offsetY);
        combiner.addTextElement(String.valueOf(xzrs), 40, getX(text2, xzrs, numberFontSize), offsetY);
        combiner.addTextElement(String.valueOf(jwsr), 40, getX(jwsr, combiner.getCanvasWidth(), numberFontSize), offsetY);

        offsetY += 60;
        TextElement text4 = combiner.addTextElement("死亡人数", wordFontSize, offsetX, offsetY).setCenter(true);
        offsetX = combiner.getCanvasWidth() / 2 - text4.getWidth() / 2;
        TextElement text5 = combiner.addTextElement("治愈人数", wordFontSize, offsetX - 180, offsetY);
        offsetX += text4.getWidth();
        TextElement text6 = combiner.addTextElement("确诊总数", wordFontSize, offsetX + 65, offsetY);
        offsetY += 40;
        combiner.addTextElement(String.valueOf(zyrs), numberFontSize, getX(text5, zyrs, numberFontSize), offsetY);
        combiner.addTextElement(String.valueOf(zs), numberFontSize, getX(text6, zs, numberFontSize), offsetY);
        combiner.addTextElement(String.valueOf(swrs), numberFontSize, getX(swrs, combiner.getCanvasWidth(), numberFontSize), offsetY);
        //结束绘制感染人数情况
        //开始绘制风险地区情况
        offsetY += 50;
        offsetY += combiner.addTextElement("风险地区详情", titleFontSize, 0, offsetY).setCenter(true).getHeight();//风险地区标题
        offsetY += 10;
        offsetY += combiner.addTextElement("高风险地区", wordFontSize, 0, offsetY).setCenter(true).getHeight();//高风险地区标题
        offsetY += 10;
        java.util.List<String> hlist = new ArrayList<>();
        hlist.add("111111111111111111111111");
        hlist.add("2222222222222222222222222222222222222222222222222");
        hlist.add("3333333333333333333333333333333333333333333333333333333333333333333333333");
        for (String a : hlist) {
            //循环添加高风险地区数据
            offsetY += 5 + combiner.addTextElement(a, wordFontSize, 0, offsetY).setCenter(true).setAutoBreakLine(500, 5).getHeight();
        }
        List<String> mlist = new ArrayList<>();
        mlist.add("111111111111111111111111");
        mlist.add("2222222222222222222222222222222222222222222222222");
        mlist.add("3333333333333333333333333333333333333333333333333333333333333333333333333");
        offsetY += 10;
        offsetY += combiner.addTextElement("中风险地区", wordFontSize, 0, offsetY).setCenter(true).getHeight();//中风险地区标题
        offsetY += 10;
        for (String a : mlist) {
            //循环添加中风险地区数据
            offsetY += 5 + combiner.addTextElement(a, wordFontSize, 0, offsetY).setCenter(true).setAutoBreakLine(500, 5).getHeight();
        }
        try {
            combiner.combine();
            combiner.save("D:\\a.jpg");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void Test3() {
        TencentNcovEntity query = TencentNcovApiController.queryAll();
        TencentNcovApiController.insertByTencentNcovEntity(query);
    }

    private int getX(int number, int canvasWidth, int fontSize) {
        return canvasWidth / 2 - (String.valueOf(number).length() * (fontSize / 2) + 6) / 2;
    }

    private int getX(TextElement textElement, int number, int fontSize) {
        int a = textElement.getX() + (textElement.getWidth() / 2);
        return a - (String.valueOf(number).length() * (fontSize / 2) + 6) / 2;
    }
}
