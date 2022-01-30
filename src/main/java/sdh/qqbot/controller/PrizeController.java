package sdh.qqbot.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.dao.Prize;
import sdh.qqbot.service.IPrizeService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SDH
 * @since 2022-01-09
 */
@RestController
public class PrizeController {
    @Autowired
    private IPrizeService iPrizeService;
    static IPrizeService prizeService;


    @PostConstruct
    public void init() {
        prizeService = iPrizeService;
    }

    /**
     * 添加奖品
     */
    public static void addPrize(Prize prize) {
        prizeService.saveOrUpdate(prize, new UpdateWrapper<Prize>().eq("prize_name", prize.getPrizeName()));
    }

    /**
     * 获取奖品列表
     *
     * @return 返回奖品列表
     */
    public static List<Prize> getPrizeList() {
        return prizeService.list();
    }

    /**
     * 通过奖品ID获取奖品详情
     *
     * @param prizeId 奖品ID
     * @return 奖品对象
     */
    public static Prize getPrizeById(int prizeId) {
        return prizeService.getById(prizeId);
    }


}

