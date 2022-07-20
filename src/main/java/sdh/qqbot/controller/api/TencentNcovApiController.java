package sdh.qqbot.controller.api;


import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.entity.api.tencent.*;
import sdh.qqbot.entity.api.weather.WeatherCityEntity;
import sdh.qqbot.entity.database.Infectcount;
import sdh.qqbot.service.impl.InfectcountServiceImpl;
import sdh.qqbot.utils.OkHttpUtil;
import sdh.qqbot.utils.PushUtils;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 疫情数据查询接口
 *
 * @author SDH
 */
@RestController
@Slf4j
public class TencentNcovApiController {
    @Autowired
    private InfectcountServiceImpl service;
    static InfectcountServiceImpl infectcountService;

    @PostConstruct
    public void init() {
        infectcountService = service;
    }

    @GetMapping("/api/updateNcov")
    public void updateNcovData() {
        insertByTencentNcovEntity(queryAll());
        PushUtils.push("疫情数据更新成功！");
    }

    /**
     * 查询疫情人数数据
     *
     * @return 腾讯数据实体
     */
    public static TencentNcovEntity queryAll() {
        String json = OkHttpUtil.get(ApiUrlConfig.TENCENTNCOVALL_API);
        return JSON.parseObject(json, TencentNcovEntity.class);
    }

    /**
     * 根据城市字符串查询风险地区，最多到市一级
     *
     * @param cityStr 地址字符串
     * @return 腾讯风险地区实体类
     */
    public static TencentNcovRiskAreaEntity queryRiskArea(String cityStr) {
        WeatherCityEntity city = QueryApiManagerController.queryCityDesByCityName(cityStr);
        String url;
        if (cityStr.length() <= 3) {
            ClassPathResource resource = new ClassPathResource("assets/map.json");
            JSONArray array = JSON.parseArray(resource.readUtf8Str());
            List<JSONObject> jsonObjects = array.toJavaList(JSONObject.class);
            boolean contains = false;
            for (JSONObject a : jsonObjects) {
                contains = a.getString("provinceName").contains(cityStr);
                if (contains) break;
            }
            if (contains) {
                url = ApiUrlConfig.TENCENTNCOVAREA_API + "province=" + city.getGeocodes().get(0).getProvince();
            } else {
                url = ApiUrlConfig.TENCENTNCOVAREA_API + "city=" + city.getGeocodes().get(0).getCity();
            }
        } else {
            url = ApiUrlConfig.TENCENTNCOVAREA_API + "city=" + city.getGeocodes().get(0).getCity();
        }
        String json = OkHttpUtil.get(url);
        return JSON.parseObject(json, TencentNcovRiskAreaEntity.class);
    }

    /**
     * 根据腾讯接口更新数据库数据
     *
     * @param entity 腾讯数据实体
     */
    public static void insertByTencentNcovEntity(TencentNcovEntity entity) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //开始更新中国总数据
        TencentNcovEntity.DataDTO.NcovDTO.ChinaTotalDTO chinaTotalDTO = entity.getData().getDiseaseh5Shelf().getChinaTotal();
        TencentNcovEntity.DataDTO.NcovDTO.ChinaAddDTO chinaAddDTO = entity.getData().getDiseaseh5Shelf().getChinaAdd();
        Infectcount china = new Infectcount();
        china.setCountryName("中国");
        setChinaToday(china, chinaAddDTO);
        setChinaTotal(china, chinaTotalDTO);
        try {
            china.setUpdateDate(f.parse(entity.getData().getDiseaseh5Shelf().getLastUpdateTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        infectcountService.save(china);
        List<TencentNcovChildrenEntity> children = entity.getData().getDiseaseh5Shelf().getAreaTree().get(0).getChildren();
        children.forEach((a) -> {
            //循环每个省
            Infectcount pri = new Infectcount();
            pri.setCountryName("中国");
            pri.setProvinceName(a.getName());
            try {
                pri.setUpdateDate(f.parse(a.getTotal().getMtime()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            setToday(pri, a.getToday());
            setTotal(pri, a.getTotal());
            infectcountService.save(pri);
            a.getChildren().forEach((b) -> {
                //循环每个市
                Infectcount city = new Infectcount();
                city.setCountryName("中国");
                city.setProvinceName(a.getName());
                city.setCityName(b.getName());
                try {
                    city.setUpdateDate(f.parse(b.getTotal().getMtime()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                setTotal(city, b.getTotal());
                setToday(city, b.getToday());
                infectcountService.save(city);
            });
        });
    }

    /**
     * 设置国内今日总数
     *
     * @param infectcount 要设置的数据库实体类
     * @param today       腾讯数据ChinaToday实体
     */
    private static void setChinaToday(Infectcount infectcount, TencentNcovEntity.DataDTO.NcovDTO.ChinaAddDTO today) {
        //今日确诊
        infectcount.setTodayConfirm(today.getConfirm() == null ? 0 : today.getConfirm());
        //今日治愈
        infectcount.setTodayHeal(today.getHeal() == null ? 0 : today.getHeal());
        //今日死亡
        infectcount.setTodayDead(today.getDead() == null ? 0 : today.getDead());
        //今日境外输入
        infectcount.setTodayInput(today.getImportedCase() == null ? 0 : today.getImportedCase());
        //今日危重症
        infectcount.setTodaySevere(today.getNowSevere() == null ? 0 : today.getNowSevere());
        //今日现存确诊
        infectcount.setTodayStoreconfirm(today.getNowConfirm());
        //今日新增无症状
        infectcount.setTodaySuspect(today.getNoInfectH5() == null ? 0 : today.getNoInfectH5());
        //今日本土确诊
        infectcount.setTodayLocation(today.getLocalConfirmH5());
    }

    /**
     * 设置国内累计数据
     *
     * @param infectcount 要设置的数据库实体类
     * @param total       腾讯数据ChinaTotal数据
     */
    private static void setChinaTotal(Infectcount infectcount, TencentNcovEntity.DataDTO.NcovDTO.ChinaTotalDTO total) {
        //累计确诊
        infectcount.setTotalConfirm(total.getConfirm() == null ? 0 : total.getConfirm());
        //累计治愈
        infectcount.setTotalHeal(total.getHeal() == null ? 0 : total.getHeal());
        //累计死亡
        infectcount.setTotalDead(total.getDead() == null ? 0 : total.getDead());
        //累计境外输入
        infectcount.setTotalInput(total.getImportedCase() == null ? 0 : total.getImportedCase());
        //现有危重症
        infectcount.setTotalSevere(total.getNowSevere() == null ? 0 : total.getNowSevere());
        //现有本土无症状
        infectcount.setTotalSuspect(total.getNowLocalWzz() == null ? 0 : total.getNowLocalWzz());
        //现有本土确诊
        infectcount.setTotalLocation(total.getLocalConfirm());
        //现存确诊
        infectcount.setTotalStoreconfirm(total.getNowConfirm());
    }

    /**
     * 设置城市今日总数
     *
     * @param infectcount 要设置的数据库实体类
     * @param today       腾讯数据实体
     */
    private static void setToday(Infectcount infectcount, TencentNcovTodayEntity today) {
        //今日确诊
        infectcount.setTodayConfirm(today.getConfirm() == null ? 0 : today.getConfirm());
        //今日境外输入
        infectcount.setTodayInput(today.getAbroadConfirmAdd() == null ? 0 : today.getAbroadConfirmAdd());
        //今日新增无症状
        infectcount.setTodaySuspect(today.getWzzAdd() == null ? 0 : today.getWzzAdd());
        //今日本土确诊
        infectcount.setTodayLocation(today.getLocalConfirmAdd());
    }

    /**
     * 设置城市累计数据
     *
     * @param infectcount 要设置的数据库实体类
     * @param total       total数据
     */
    private static void setTotal(Infectcount infectcount, TencentNcovTotalEntity total) {
        //累计确诊
        infectcount.setTotalConfirm(total.getConfirm() == null ? 0 : total.getConfirm());
        //累计治愈
        infectcount.setTotalHeal(total.getHeal() == null ? 0 : total.getHeal());
        //累计死亡
        infectcount.setTotalDead(total.getDead() == null ? 0 : total.getDead());
        //现存确诊
        infectcount.setTotalStoreconfirm(total.getNowConfirm());
    }
}
