package sdh.qqbot.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.Dao.Prize;
import sdh.qqbot.Dao.User;
import sdh.qqbot.Dao.Winners;
import sdh.qqbot.service.IWinnersService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
@RequestMapping("/winners")
public class WinnersController {
    @Autowired
    private IWinnersService iWinnersService;

    static IWinnersService winnersService;

    @PostConstruct
    public void init() {
        winnersService = iWinnersService;
    }

    /**
     * 根据奖品ID查询中奖者名单.
     */
    public static List<User> getUserListByPrizeId(int prizeId) {
        List<Winners> winnersList = winnersService.list(new QueryWrapper<Winners>().eq("prize_id", prizeId));
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
        List<Winners> winnersList = winnersService.list(new QueryWrapper<Winners>().eq("user_id", userId));
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
        List<Winners> winnersList = winnersService.list(new QueryWrapper<Winners>().eq("user_id", user.getId()));
        List<Prize> prizes = new ArrayList<>();
        for (Winners i : winnersList) {
            prizes.add(PrizeController.getPrizeById(i.getPrizeId()));
        }
        return prizes;
    }
}

