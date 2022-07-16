package sdh.qqbot.controller.database;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.database.Drawprize;
import sdh.qqbot.entity.database.Prize;
import sdh.qqbot.entity.database.User;
import sdh.qqbot.entity.api.Winners;
import sdh.qqbot.entity.api.MessageEntity;
import sdh.qqbot.mapper.BlacklistMapper;
import sdh.qqbot.mapper.DrawprizeMapper;
import sdh.qqbot.mapper.PrizeMapper;
import sdh.qqbot.mapper.WinnersMapper;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 抽奖接口
 *
 * @author SDH
 * @since 2022-01-08
 */
@RestController
public class DrawprizeController {
    static DrawprizeMapper drawprizeMapper;
    static PrizeMapper prizeMapper;
    static BlacklistMapper blacklistMapper;
    static WinnersMapper winnersMapper;
    @Autowired
    private DrawprizeMapper iDrawprizeMapper;
    @Autowired
    private PrizeMapper iPrizeMapper;
    @Autowired
    private BlacklistMapper iBlacklistMapper;
    @Autowired
    private WinnersMapper iWinnersMapper;

    @PostConstruct
    public void init() {
        drawprizeMapper = iDrawprizeMapper;
        prizeMapper = iPrizeMapper;
        blacklistMapper = iBlacklistMapper;
        winnersMapper = iWinnersMapper;
    }

    /**
     * 参与抽奖方法
     */
    public static void joinDrawPrize(int prizeId, MessageEntity messageEntity) {
        //判断用户是否存在
        User user = UserController.getUserByQQ(messageEntity.getUserId());
        Drawprize drawprize = new Drawprize();
        drawprize.setPrizeId(prizeId);
        drawprize.setPlayerId(user.getId().toString());
        //判断群成员等级
        int level = Integer.parseInt(user.getLevel());
        if (level >= 2) {
            Prize prize = prizeMapper.selectById(prizeId);
            //判断是否已抽取
            if (prize.getPrizeIsdraw() == 0) {
                //判断参与用户是否在黑名单中
                if (!BlacklistController.isBlack(user.getId().toString())) {
                    if (drawprizeMapper.selectOne(new QueryWrapper<Drawprize>().eq("prize_id", prizeId).eq("player_id", drawprize.getPlayerId())) != null) {
                        QBotSendMessageController.sendMsg(messageEntity, user.getNickname() + ",请勿重复参与!");
                    } else {
                        drawprizeMapper.insert(drawprize);
                        QBotSendMessageController.sendMsg(messageEntity, user.getNickname() + ",恭喜您成功参与抽奖!");
                    }
                } else {
                    QBotSendMessageController.sendMsg(messageEntity, user.getNickname() + ",您在黑名单中,无法参与!");
                }
            } else {
                QBotSendMessageController.sendMsg(messageEntity, user.getNickname() + ",该奖品已完成抽奖，请查询中奖列表。");
            }

        } else {
            QBotSendMessageController.sendMsg(messageEntity, user.getNickname() + ",您的群成员等级未达标!");
        }


    }

    /**
     * 手动抽奖，用户权限等级大于1或者是群内管理员即可手动开奖
     */
    public static void manualDrawPrize(int prizeId, MessageEntity messageEntity) {

//        Prize prize = prizeService.getById(prizeId);
        Prize prize = prizeMapper.selectById(prizeId);
        int userLevel = UserController.getPermissionByUserId(messageEntity.getUserId());
        String role = UserController.getRoleByUserId(messageEntity.getUserId());
        //判断用户权限大于1或者是群内管理员
        if (userLevel > 1 || "owner".equals(role) || "admin".equals(role)) {
            if (prize != null) {
                if (prize.getPrizeIsdraw() == 0) {
                    List<User> userList = startDrawPrize(prize);
                    if (userList.size() > 0) {
                        prize.setPrizeIsdraw(1);
                        prizeMapper.update(prize, new UpdateWrapper<Prize>().eq("id", prize.getId()));
                        QBotSendMessageController.sendGroupMsg("814012332", "奖品为：" + prize.getPrizeName() + "的中奖人员列表:%0d" + QBotSendMessageController.generatorMessageByList(userList));
                        QBotSendMessageController.sendPrivateMsg(prize.getPrizeFrom(), "您提供的" + prize.getPrizeName() + "的中奖人员列表为:%0d" + QBotSendMessageController.generatorMessageByList(userList));
                        for (User u : userList) {
                            QBotSendMessageController.sendPrivateMsg(u.getUserId(), "恭喜你，获得 " + prize.getPrizeName() + " 一份，请找QQ号为：" + prize.getPrizeFrom() + "的群友兑奖。");
                        }
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
                prizeMapper.update(prize, new UpdateWrapper<Prize>().eq("id", prize.getId()));
                QBotSendMessageController.sendGroupMsg("814012332", "奖品为：" + prize.getPrizeName() + "的中奖人员列表:%0d" + QBotSendMessageController.generatorMessageByList(userList));
                QBotSendMessageController.sendPrivateMsg(prize.getPrizeFrom(), "您提供的" + prize.getPrizeName() + "的中奖人员列表为:%0d" + QBotSendMessageController.generatorMessageByList(userList));
                for (User u : userList) {
                    QBotSendMessageController.sendPrivateMsg(u.getUserId(), "恭喜你，获得 " + prize.getPrizeName() + " 一份，请找QQ号为：" + prize.getPrizeFrom() + "的群友兑奖。");
                }
            } else {
                QBotSendMessageController.sendGroupMsg("814012332", "奖品为 " + prize.getPrizeName() + " 的抽奖项无人参与，请查询参与列表");
            }
        } else {
            QBotSendMessageController.sendGroupMsg("814012332", "奖品为 " + prize.getPrizeName() + " 的奖品已完成抽奖，请查询中奖列表");
        }

    }

    /**
     * 获取参与抽奖人员名单 By 奖品id
     */
    public static void getDrawPrizeListByPrizeId(int prizeId, MessageEntity messageEntity) {
        List<Drawprize> list = drawprizeMapper.selectList(new QueryWrapper<Drawprize>().eq("prize_id", prizeId));
        Prize prize = prizeMapper.selectById(prizeId);
        if (prize.getPrizeIsdraw() == 0) {
            if (list.size() == 0) {
                QBotSendMessageController.sendMsg(messageEntity, "无该抽奖项或无人参与，请查询奖项列表或者参与列表");
            } else {
                StringBuilder builder = new StringBuilder("参与抽奖人员列表:%0d");

                for (Drawprize d : list) {
                    User user = UserController.getUserById(Integer.parseInt(d.getPlayerId()));
                    builder.append("奖品名称：").append(prize.getPrizeName()).append("，参与人：").append(user.getNickname()).append("%0d");
                }
                QBotSendMessageController.sendMsg(messageEntity, builder.toString());
            }
        } else {
            QBotSendMessageController.sendMsg(messageEntity, "该奖品已完成抽奖，请查询中奖列表");
        }
    }

    /**
     * 抽取奖品方法,返回中奖者列表
     */
    private static List<User> startDrawPrize(Prize prize) {
        List<User> userList = new ArrayList<>();
        //根据奖品id获取参与名单
        List<Drawprize> drawprizeList = drawprizeMapper.selectList(new QueryWrapper<Drawprize>().eq("prize_id", prize.getId()));
        if (drawprizeList.size() > 0) {
            for (int i = 0; i < prize.getPrizeQuantity(); i++) {
                Drawprize drawprize = randomPerson(drawprizeList);
                userList.add(UserController.getUserById(Integer.parseInt(drawprize.getPlayerId())));
                drawprizeList.remove(drawprize);
                //加入黑名单一天
                BlacklistController.joinBlackList(drawprize.getPlayerId());
                //加入到中奖者表中
                Winners winners = new Winners();
                winners.setPrizeId(prize.getId());
                winners.setUserId(Integer.valueOf(drawprize.getPlayerId()));
                winnersMapper.insert(winners);
            }
            //抽奖完成删除参与名单
            drawprizeMapper.delete(new QueryWrapper<Drawprize>().eq("prize_id", prize.getId()));
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

