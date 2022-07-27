package sdh.qqbot;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sdh.qqbot.utils.okhttp.OkHttpUtil;

@SpringBootTest
@Slf4j
class AppApplicationTests {
    @Test
    public void test() {
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


}
