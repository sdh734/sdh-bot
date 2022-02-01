package sdh.qqbot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.dao.Prize;
import sdh.qqbot.mapper.PrizeMapper;

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
    static PrizeMapper prizeMapper;

    @Autowired
    private PrizeMapper iPrizeMapper;

    @PostConstruct
    public void init() {
        prizeMapper = iPrizeMapper;
    }

    /**
     * 添加奖品
     */
    public static int addPrize(Prize prize) {
        if (prizeMapper.exists(new QueryWrapper<Prize>().eq("prize_name", prize.getPrizeName()))) {
            return -1;
        } else {
            prizeMapper.insert(prize);
            return 0;
        }
    }


    /**
     * 获取未抽取奖品列表
     *
     * @return 返回奖品列表
     */
    public static List<Prize> getPrizeList(boolean flag) {
        if (flag) {
            return prizeMapper.selectList(new QueryWrapper<Prize>().eq("prize_isdraw", 1));
        } else {
            return prizeMapper.selectList(new QueryWrapper<Prize>().eq("prize_isdraw", 0));
        }

    }

    /**
     * 通过奖品ID获取奖品详情
     *
     * @param prizeId 奖品ID
     * @return 奖品对象
     */
    public static Prize getPrizeById(int prizeId) {
        return prizeMapper.selectById(prizeId);
    }


}

