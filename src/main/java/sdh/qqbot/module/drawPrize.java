package sdh.qqbot.module;

import org.springframework.stereotype.Component;
import sdh.qqbot.controller.DrawprizeController;
import sdh.qqbot.controller.PrizeController;
import sdh.qqbot.controller.QBotSendMessageController;
import sdh.qqbot.controller.WinnersController;
import sdh.qqbot.dao.Prize;
import sdh.qqbot.dao.User;
import sdh.qqbot.entity.MessageEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 抽奖功能模块
 */
@Component
public class drawPrize {
    /**
     * 抽奖功能管理模块,提供消息内容解析处理功能.
     *
     * @param message 消息对象
     */
    public static void DrawPrizeManager(MessageEntity message) {
        String[] msgArray = message.getMessage().split(" ");
        StringBuilder stringBuilder = new StringBuilder("抽奖命令帮助");
        stringBuilder.append("%0d------------%0d");
        stringBuilder.append("抽奖 添加奖品 奖品名称 奖品数量(不写默认为1) 抽取时间(不写默认手动抽取,格式:2022-01-12T12:00)");
        stringBuilder.append("%0d------------%0d");
        stringBuilder.append("抽奖 奖品列表");
        stringBuilder.append("%0d------------%0d");
        stringBuilder.append("抽奖 奖品详情 奖品id(通过奖品详情获取)");
        stringBuilder.append("%0d------------%0d");
        stringBuilder.append("抽奖 参与抽奖 奖品id");
        stringBuilder.append("%0d------------%0d");
        stringBuilder.append("抽奖 开奖 奖品id(只有管理员有权限)");
        stringBuilder.append("%0d------------%0d");
        stringBuilder.append("抽奖 参与列表 奖品id");
        stringBuilder.append("%0d------------%0d");
        stringBuilder.append("抽奖 中奖列表 奖品id(不填则默认查询发送用户中奖情况)");

        switch (msgArray.length) {
            case 1:
                //未带任何参数 默认返回抽奖功能帮助菜单
                QBotSendMessageController.sendMsg(message, stringBuilder.toString());
                break;
            case 2:
                //只带一个参数,仅支持查看奖品列表
                if ("奖品列表".equals(msgArray[1])) {
                    QBotSendMessageController.sendMsg(message, "奖品列表:%0d"
                            + QBotSendMessageController.generatorMessageByList(PrizeController.getPrizeList()));
                } else if ("中奖列表".equals(msgArray[1])) {
                    List<Prize> prizeListByUserQQ = WinnersController.getPrizeListByUserQQ(message.getUserId());
                    QBotSendMessageController.sendMsg(message, message.getSender().getNickname() + "的中奖列表:%0d"
                            + QBotSendMessageController.generatorMessageByList(prizeListByUserQQ));
                } else {
                    QBotSendMessageController.sendMsg(message, "参数错误");
                }
                break;
            case 3:
                switch (msgArray[1]) {
                    case "添加奖品":
                        Prize prize = new Prize();
                        prize.setPrizeName(msgArray[2]);
                        prize.setPrizeFrom(message.getUserId());
                        int addflag1 = PrizeController.addPrize(prize);
                        if (addflag1 == 0) {
                            QBotSendMessageController.sendMsg(message, "奖品添加成功");
                        } else {
                            QBotSendMessageController.sendMsg(message, "奖品添加失败，请查询奖品列表是否有同名奖品");
                        }

                        break;
                    case "奖品详情":
                        try {
                            Prize prize1 = PrizeController.getPrizeById(Integer.parseInt(msgArray[2]));
                            if (prize1 != null) {
                                QBotSendMessageController.sendMsg(message, prize1.toString());
                            } else {
                                QBotSendMessageController.sendMsg(message, "无该抽奖项");
                            }
                        } catch (Exception e) {
                            QBotSendMessageController.sendMsg(message, "参数错误");
                        }
                        break;
                    case "参与抽奖":
                        try {
                            //获取奖品信息
                            Prize prize2 = PrizeController.getPrizeById(Integer.parseInt(msgArray[2]));
                            //判断奖品是否存在
                            if (prize2 != null) {
                                //参与抽奖
                                DrawprizeController.joinDrawPrize(Integer.parseInt(msgArray[2]), message);
                            } else {
                                //奖品不存在
                                QBotSendMessageController.sendMsg(message, "无该抽奖项");
                            }
                        } catch (Exception e) {
                            QBotSendMessageController.sendMsg(message, "参数错误");
                        }
                        break;
                    case "开奖":
                        //手动开奖 参数 奖项id
                        //涉及类型转换，使用try
                        try {
                            DrawprizeController.manualDrawPrize(Integer.parseInt(msgArray[2]), message);
                        } catch (Exception e) {
                            QBotSendMessageController.sendMsg(message, "参数错误");
                        }
                        break;
                    case "参与列表":
                        try {
                            DrawprizeController.getDrawPrizeListByPrizeId(Integer.parseInt(msgArray[2]), message);
                        } catch (Exception e) {
                            QBotSendMessageController.sendMsg(message, "参数错误");
                        }
                        break;
                    case "中奖列表":
                        try {
                            List<User> userListByPrizeId = WinnersController.getUserListByPrizeId(Integer.parseInt(msgArray[2]));
                            if (userListByPrizeId.size() == 0) {
                                QBotSendMessageController.sendMsg(message, "未开奖或者无此奖品");
                            } else {
                                QBotSendMessageController.sendMsg(message, "奖品ID为" + Integer.parseInt(msgArray[2]) + "的中奖者列表:%0d"
                                        + QBotSendMessageController.generatorMessageByList(userListByPrizeId));
                            }
                        } catch (Exception e) {
                            QBotSendMessageController.sendMsg(message, "参数错误");
                        }
                        break;
                }
                break;
            case 4:
                if ("添加奖品".equals(msgArray[1])) {
                    Prize prize = new Prize();
                    prize.setPrizeName(msgArray[2]);
                    prize.setPrizeFrom(message.getUserId());
                    try {
                        prize.setPrizeQuantity(Integer.parseInt(msgArray[3]));
                    } catch (Exception e) {
                        String datestr = msgArray[3];
//                        Log.i(datestr);
                        prize.setPrizeDrawtime(LocalDateTime.parse(datestr));
                    }

                    int addflag2 = PrizeController.addPrize(prize);
                    if (addflag2 == 0) {
                        QBotSendMessageController.sendMsg(message, "奖品添加成功");
                    } else {
                        QBotSendMessageController.sendMsg(message, "奖品添加失败，请查询奖品列表是否有同名奖品");
                    }
                } else {
                    QBotSendMessageController.sendMsg(message, "参数错误");
                }
                break;
            case 5:
                if ("添加奖品".equals(msgArray[1])) {
                    Prize prize = new Prize();
                    prize.setPrizeName(msgArray[2]);
                    prize.setPrizeQuantity(Integer.parseInt(msgArray[3]));
                    prize.setPrizeFrom(message.getUserId());
                    String datestr = msgArray[4];
//                    Log.i(datestr);
                    prize.setPrizeDrawtime(LocalDateTime.parse(datestr));
                    int addflag3 = PrizeController.addPrize(prize);
                    if (addflag3 == 0) {
                        QBotSendMessageController.sendMsg(message, "奖品添加成功");
                    } else {
                        QBotSendMessageController.sendMsg(message, "奖品添加失败，请查询奖品列表是否有同名奖品");
                    }
                } else {
                    QBotSendMessageController.sendMsg(message, "参数错误");
                }
                break;
            default:
        }
    }
}
