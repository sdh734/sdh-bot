package sdh.qqbot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.dao.Prize;
import sdh.qqbot.dao.User;
import sdh.qqbot.dao.Winners;
import sdh.qqbot.mapper.WinnersMapper;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 抽奖获胜者操作接口
 *
 * @author SDH
 * @since 2022-01-09
 */
@RestController
public class WinnersController {
    static WinnersMapper winnersMapper;
    @Autowired
    private WinnersMapper iWinnersMapper;

    /**
     * 根据奖品ID查询中奖者名单.
     */
    public static List<User> getUserListByPrizeId(int prizeId) {
        List<Winners> winnersList = winnersMapper.selectList(new QueryWrapper<Winners>().eq("prize_id", prizeId));
        List<User> users = new ArrayList<>();
        for (Winners i : winnersList) {
            users.add(UserController.getUserById(i.getUserId()));
        }
        return users;
    }

    /**
     * 根据中奖者ID查询中奖物品.
     */
    public static List<Prize> getPrizeListByUserId(int userId) {
        List<Winners> winnersList = winnersMapper.selectList(new QueryWrapper<Winners>().eq("user_id", userId));
        List<Prize> prizes = new ArrayList<>();
        for (Winners i : winnersList) {
            prizes.add(PrizeController.getPrizeById(i.getPrizeId()));
        }
        return prizes;
    }

    /**
     * 根据QQ号查询中奖物品清单
     *
     * @param userQQ 用户QQ号
     * @return 中奖物品清单
     */
    public static List<Prize> getPrizeListByUserQQ(String userQQ) {
        User user = UserController.getUserByQQ(userQQ);
        List<Winners> winnersList = winnersMapper.selectList(new QueryWrapper<Winners>().eq("user_id", user.getId()));
        List<Prize> prizes = new ArrayList<>();
        for (Winners i : winnersList) {
            prizes.add(PrizeController.getPrizeById(i.getPrizeId()));
        }
        return prizes;
    }

    @PostConstruct
    public void init() {
        winnersMapper = iWinnersMapper;
    }
}

