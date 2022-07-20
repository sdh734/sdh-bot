package sdh.qqbot.controller.database;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.entity.database.Infectcount;
import sdh.qqbot.service.impl.InfectcountServiceImpl;

import javax.annotation.PostConstruct;

/**
 * <p>
 * 中国疫情每日数据总和汇总 前端控制器
 * </p>
 *
 * @author SDH
 * @since 2022-07-17
 */
@RestController
public class InfectcountController {
    static InfectcountServiceImpl infectcountService;

    @Autowired
    private InfectcountServiceImpl infectcountServiceImpl;

    @PostConstruct
    public void init() {
        infectcountService = infectcountServiceImpl;
    }

    /**
     * 根据省名字查询疫情情况
     *
     * @return 省级疫情情况
     */
    public static Infectcount queryNcovInfoByProvinceName(String provinceName) {
        return infectcountService.getOne(new QueryWrapper<Infectcount>()
                .eq("province_name", provinceName)
                .eq("city_name", "")
                .orderByDesc("update_date")
                .last("limit 1"));
    }

    /**
     * 根据市级名称查询疫情情况
     *
     * @return 市级疫情情况
     */
    public static Infectcount queryNcovInfoByCityName(String cityName) {
        return infectcountService.getOne(new QueryWrapper<Infectcount>()
                .eq("city_name", cityName)
                .orderByDesc("update_date")
                .last("limit 1"));
    }
}
