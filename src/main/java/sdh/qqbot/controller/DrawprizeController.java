package sdh.qqbot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.dao.Drawprize;
import sdh.qqbot.dao.Prize;
import sdh.qqbot.dao.User;
import sdh.qqbot.dao.Winners;
import sdh.qqbot.entity.MessageEntity;
import sdh.qqbot.service.IBlacklistService;
import sdh.qqbot.service.IDrawprizeService;
import sdh.qqbot.service.IPrizeService;
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
 * @since 2022-01-08
 */
@RestController
public class DrawprizeController {
    @Autowired
    private IDrawprizeService iDrawprizeService;
    @Autowired
    private IPrizeService iPrizeService;
    @Autowired
    private IBlacklistService iBlacklistService;
    @Autowired
    private IWinnersService iWinnersService;

    static IDrawprizeService drawprizeService;
    static IPrizeService prizeService;
    static IBlacklistService blacklistService;
    static IWinnersService winnersService;

    @PostConstruct
    public void init() {
        drawprizeService = this.iDrawprizeService;
        prizeService = iPrizeService;
        blacklistService = iBlacklistService;
        winnersService = iWinnersService;
    }

    /**
     * 参与抽奖方法
     */
    public static void joinDrawPrize(int prizeId, MessageEntity messageEntity) {
        //判断用户是否存在,如不存在则加入用户表中
        User user = UserController.getUserByQQ(messageEntity.getUserId());
        if (user == null) {
            UserController.addUser(messageEntity);
            user = UserController.getUserByQQ(messageEntity.getUserId());
        }
        Drawprize drawprize = new Drawprize();
        drawprize.setPrizeId(prizeId);
        drawprize.setPlayerId(user.getId().toString());
        //判断参与用户是否在黑名单中
        if (!BlacklistController.isBlack(user.getId().toString())) {
            if (drawprizeService.getOne(new QueryWrapper<Drawprize>().eq("prize_id", prizeId).eq("player_id", drawprize.getPlayerId())) != null) {
                QBotSendMessageController.sendMsg(messageEntity, "请勿重复参与!");
            } else {
                drawprizeService.saveOrUpdate(drawprize, new UpdateWrapper<Drawprize>().eq("prize_id", prizeId));
                QBotSendMessageController.sendMsg(messageEntity, "成功参与抽奖!");
            }
        } else {
            QBotSendMessageController.sendMsg(messageEntity, user.getUserName() + ",您在黑名单中,无法参与!");
        }
    }

    /**
     * 手动抽奖
     */
    public static void manualDrawPrize(int prizeId, MessageEntity messageEntity) {
        Prize prize = prizeService.getById(prizeId);
        int userLevel = UserController.getPermissionByUserId(messageEntity.getUserId());
        if (userLevel > 1) {
            if (prize != null) {
                if (prize.getPrizeIsdraw() == 0) {
                    List<User> userList = startDrawPrize(prize);
                    if (userList.size() > 0) {
                        prize.setPrizeIsdraw(1);
                        prizeService.update(prize, new UpdateWrapper<Prize>().eq("id", prize.getId()));
                        QBotSendMessageController.sendMsg(messageEntity, "中奖人员列表:%0d" + QBotSendMessageController.generatorMessageByList(userList));
                    } else {
                        QBotSendMessageController.sendMsg(messageEntity, "该抽奖项无人参与，请查询参与列表");
                    }
                } else {
                    QBotSendMessageController.sendMsg(messageEntity, "该奖品已完成抽奖，请查询中奖列表");
                }
            } else {
                QBotSendMessageController.sendMsg(messageEntity, "无该抽奖项或无人参与，请查询奖项列表或者参与列表");
            }

        } else {
            QBotSendMessageController.sendMsg(messageEntity, "无开奖权限");
        }
    }

    /**
     * 自动抽奖
     */
    public static void manualDrawPrize(Prize prize) {
        //判断是否已开奖
        if (prize.getPrizeIsdraw() == 0) {
            List<User> userList = startDrawPrize(prize);
            if (userList.size() > 0) {
                prize.setPrizeIsdraw(1);
                prizeService.update(prize, new UpdateWrapper<Prize>().eq("id", prize.getId()));
                //QBotSendMessageController.sendGroupMsg("814012332", "中奖人员列表:%0d" + QBotSendMessageController.generatorMessageByList(userList));
                QBotSendMessageController.sendPrivateMsg(prize.getPrizeFrom(), "您提供的" + prize.getPrizeName() + "的中奖人员列表为:%0d" + QBotSendMessageController.generatorMessageByList(userList));
                for (User u : userList) {
                    QBotSendMessageController.sendPrivateMsg(u.getUserId(), "恭喜你，获得 " + prize.getPrizeName() + " 一份，请找QQ号为：" + prize.getPrizeFrom() + "的群友兑奖。");
                }
            } else {
                QBotSendMessageController.sendGroupMsg("814012332", "奖品为" + prize.getPrizeName() + "的抽奖项无人参与，请查询参与列表");
            }
        } else {
            QBotSendMessageController.sendGroupMsg("814012332", "奖品为" + prize.getPrizeName() + "的奖品已完成抽奖，请查询中奖列表");
        }

    }

    /**
     * 获取参与抽奖人员名单 By 奖品id
     */
    public static void getDrawPrizeListByPrizeId(int prizeId, MessageEntity messageEntity) {
        List<Drawprize> list = drawprizeService.list(new QueryWrapper<Drawprize>().eq("prize_id", prizeId));
        Prize prize = prizeService.getById(prizeId);
        if (list.size() == 0) {
            if (prize.getPrizeIsdraw() == 0) {
                QBotSendMessageController.sendMsg(messageEntity, "无该抽奖项或无人参与，请查询奖项列表或者参与列表");
            } else {
                QBotSendMessageController.sendMsg(messageEntity, "该奖品已完成抽奖，请查询中奖列表");
            }

        } else {
            QBotSendMessageController.sendMsg(messageEntity, "参与抽奖人员列表:%0d" + QBotSendMessageController.generatorMessageByList(list));
        }

    }

    /**
     * 抽取奖品方法,返回中奖者列表
     */
    private static List<User> startDrawPrize(Prize prize) {
        List<User> userList = new ArrayList<>();
        //根据奖品id获取参与名单
        List<Drawprize> drawprizeList = drawprizeService.list(new QueryWrapper<Drawprize>().eq("prize_id", prize.getId()));
        if (drawprizeList.size() > 0) {
            for (int i = 0; i < prize.getPrizeQuantity(); i++) {
                Drawprize drawprize = randomPerson(drawprizeList);
                userList.add(UserController.getUserById(Integer.parseInt(drawprize.getPlayerId())));
                drawprizeList.remove(drawprize);
                //从参与名单中移除
                drawprizeService.removeById(drawprize.getId());
                //加入黑名单一天
                BlacklistController.joinBlackList(drawprize.getPlayerId());
                //加入到中奖者表中
                Winners winners = new Winners();
                winners.setPrizeId(prize.getId());
                winners.setUserId(Integer.valueOf(drawprize.getPlayerId()));
                winnersService.save(winners);
            }
        }
        return userList;
    }

    /**
     * 抽取奖品
     *
     * @param drawprizeList 参与人员列表
     * @return 中奖人
     */
    private static Drawprize randomPerson(List<Drawprize> drawprizeList) {
        int[] randoms = new int[10];
        for (int i = 0; i < 10; i++) {
            int random = (int) Math.floor(Math.random() * drawprizeList.size());
            randoms[i] = random;
        }
        int randomIndex = (int) Math.floor(Math.random() * 10);
        return drawprizeList.get(randoms[randomIndex]);
    }
}

