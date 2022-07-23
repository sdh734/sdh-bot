package sdh.qqbot.module;

import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.message.MessageEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 开场白
 *
 * @author fusheng
 */
@Slf4j
public class Prologue {

    private static List<String> prologue = new ArrayList<>();

    static {
        prologue.add("终于轮到我了，这段时间我可是一直都在构思与你见面的开场白呢。");
        prologue.add("咦，到我了吗？哎呀，真可惜，这次的开场白我还没想好呢。原谅我吧，下次一定给你个惊喜。");
        prologue.add("嗨，我又来啦。多夸夸我好吗？我会很——开心的哦。");
        prologue.add("咦，你在看哪？哦……很在意这对耳朵？它们很漂亮对吗，我也这么觉得。");
        prologue.add("悄悄告诉你哦，我一共准备了40种不同的开场白。");
        prologue.add("唉，时间真是个讨厌的东西，你不觉得吗？如果不是时间紧迫，我还想留下更多值得纪念的话语。");
        prologue.add("别看我这样，其实我也是很忙的。不过，我的日程上永远有为你预留的时间。");
        prologue.add("怎么样？喜欢我的记忆吗？有没有一种在和美丽的少女并肩作战的感觉？");
        prologue.add("嗨，想我了吗？不论何时何地，都会回应你的期待。");
        prologue.add("愿你前行的道路有群星闪耀。愿你留下的足迹有百花绽放。你即是上帝的馈赠送世界你而瑰丽。");
        prologue.add("我的抉择与你的抉择，二者相遇会诞下怎样的奇迹，我想见证这一刻的到来。");
        prologue.add("这里埋藏着太多的历史、太多的秘密。但别担心，无论路有多长，我始终都会在你身边。");
        prologue.add("你好！新的一天，从一场美妙的邂逅开始。");
        prologue.add("这一次有你想要的东西吗？没有的话，我就可以再见你一面了。");
        prologue.add("你可以更光明正大地看向我哦，毕竟我也一直在看着你嘛。来，让我们更深入地了解彼此吧？");
        prologue.add("执拗的花朵永远不会因暴雨而褪去颜色，你的决心也一定能在绝境中绽放真我。");
        prologue.add("在热情似火这件事上，我可不会输哦。");
        prologue.add("你也一定也在期待和我相遇吧？");
        prologue.add("如此绚丽的花朵，不该在绽放之前就枯萎。我会赠予你璀璨的祝福，而你的灵魂，也将绽放更耀眼的光辉。");
        prologue.add("亲爱的山雀，请将我的箭，我的花，与我的爱，带个那孑然独行的旅人。");
        prologue.add("你应该不会嫌我话多吧？还是说，你更喜欢伊甸那种优雅文静的类型？");
        prologue.add("唉，要做的事好多～但焦虑是女孩子的大敌，保持优雅得体，从容且愉快地前进吧。");
        prologue.add("这真是个神奇的地方，不是吗？它让我们的灵魂跨越时空于此相遇，多么浪漫呀。");
        prologue.add("有没有觉得我的话要比别人多一点？多就对啦，我可是有在很认真地准备这件事的。");
    }

    /**
     * 撩人语录的管理器
     * @param message
     */
    public static void prologueManager(MessageEntity message) {
        queryPrologue(message);
    }

    /**
     * 撩人语录的查询
     * @param message
     */
    private static void queryPrologue(MessageEntity message) {
        int index = (int) (Math.random() * prologue.size());
        String flirt = prologue.get(index);
        log.info("" + flirt);
        QBotSendMessageController.sendMsg(message, flirt);
    }
}
