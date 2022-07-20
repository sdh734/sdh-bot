package sdh.qqbot.controller.api;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.entity.api.neteasy.NeteasyNcovEntity;
import sdh.qqbot.entity.api.neteasy.TodayDTO;
import sdh.qqbot.entity.api.neteasy.TotalDTO;
import sdh.qqbot.entity.database.Infectcount;
import sdh.qqbot.mapper.InfectcountMapper;
import sdh.qqbot.utils.OkHttpUtil;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 网易疫情数据接口
 *
 * @author SDH
 */
@Controller
@Slf4j
public class NeteasyNcovApiController {

    static InfectcountMapper infectcountMapper;

    @Autowired
    private InfectcountMapper iInfectcountMapper;

    @PostConstruct
    public void init() {
        infectcountMapper = iInfectcountMapper;
    }

    private static final String url = ApiUrlConfig.NETEASYNCOV_API;

    /**
     * 查询网易疫情数据
     *
     * @return 疫情数据实体类
     */
    public static NeteasyNcovEntity query() {
        String json = OkHttpUtil.get(url);
        return JSONObject.parseObject(json, NeteasyNcovEntity.class);
    }

    public static void insertByNeteasyNcovEntity(NeteasyNcovEntity entity) {
        //中国数据
        NeteasyNcovEntity.DataDTO.ChinaTotalDTO chinaTotal = entity.getData().getChinaTotal();
        Infectcount country = new Infectcount();
        country.setCountryName("中国");
        setToday(country, chinaTotal.getToday());
        setTotal(country, chinaTotal.getTotal());
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            country.setUpdateDate(f.parse(entity.getData().getLastUpdateTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        infectcountMapper.insert(country);
        List<NeteasyNcovEntity.DataDTO.AreaTreeDTO> treeDTOList = entity.getData().getAreaTree();
        treeDTOList.forEach((a) -> {
            //循环查找中国数据
            if (a.getName().equals("中国")) {
                List<NeteasyNcovEntity.DataDTO.ChildrenDTO> children = a.getChildren();
                children.forEach((b) -> {
                    //循环每个省
                    log.info(b.toString());
                    Infectcount province = new Infectcount();
                    province.setCountryName("中国");
                    province.setProvinceName(b.getName());
                    try {
                        province.setUpdateDate(f.parse(b.getLastUpdateTime()));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    setToday(province, b.getToday());
                    setTotal(province, b.getTotal());
                    infectcountMapper.insert(province);
                    b.getChildren().forEach((c) -> {
                        //循环每个市
                        log.info(c.toString());
                        Infectcount city = new Infectcount();
                        city.setCountryName("中国");
                        city.setProvinceName(b.getName());
                        city.setCityName(c.getName());
                        try {
                            city.setUpdateDate(f.parse(c.getLastUpdateTime()));
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        setTotal(city, c.getTotal());
                        setToday(city, c.getToday());
                        infectcountMapper.insert(city);
                    });
                });
            }
        });


    }

    /**
     * 设置today数据
     *
     * @param infectcount 要设置的数据库实体类
     * @param today       today数据
     */
    private static void setToday(Infectcount infectcount, TodayDTO today) {
        infectcount.setTodayConfirm(today.getConfirm() == null ? 0 : today.getConfirm());
        infectcount.setTodayHeal(today.getHeal() == null ? 0 : today.getHeal());
        infectcount.setTodayDead(today.getDead() == null ? 0 : today.getDead());
        infectcount.setTodayInput(today.getInput() == null ? 0 : today.getInput());
        infectcount.setTodaySevere(today.getSevere() == null ? 0 : today.getSevere());
        infectcount.setTodayStoreconfirm(today.getStoreConfirm() == null ? 0 : today.getStoreConfirm());
        infectcount.setTodaySuspect(today.getSuspect() == null ? 0 : today.getSuspect());
    }

    /**
     * 设置total数据
     *
     * @param infectcount 要设置的数据库实体类
     * @param total       total数据
     */
    private static void setTotal(Infectcount infectcount, TotalDTO total) {
        infectcount.setTotalConfirm(total.getConfirm() == null ? 0 : total.getConfirm());
        infectcount.setTotalHeal(total.getHeal() == null ? 0 : total.getHeal());
        infectcount.setTotalDead(total.getDead() == null ? 0 : total.getDead());
        infectcount.setTotalInput(total.getInput() == null ? 0 : total.getInput());
        infectcount.setTotalSevere(total.getSevere() == null ? 0 : total.getSevere());
        infectcount.setTotalSuspect(total.getSuspect() == null ? 0 : total.getSuspect());
        infectcount.setTotalStoreconfirm(total.getConfirm() - total.getDead() - total.getHeal());
    }
}
