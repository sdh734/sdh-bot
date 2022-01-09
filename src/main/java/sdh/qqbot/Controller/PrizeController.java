package sdh.qqbot.Controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.Dao.Prize;
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
@RequestMapping("/prize")
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

    public static Prize getPrizeById(int prizeId) {
        return prizeService.getById(prizeId);
    }
}

