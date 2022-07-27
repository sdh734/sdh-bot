package sdh.qqbot.controller.message

import sdh.qqbot.entity.message.MessageEntity
import sdh.qqbot.module.*
import sdh.qqbot.module.about.AboutMe
import sdh.qqbot.module.about.QueryHelp
import sdh.qqbot.module.drawprize.DrawPrize
import sdh.qqbot.module.music.Song
import sdh.qqbot.module.ncov.NcovInfo
import sdh.qqbot.module.news.DailyNews
import sdh.qqbot.module.picture.*
import sdh.qqbot.module.word.*
import sdh.qqbot.module.word.history.TodayOnHistory
import sdh.qqbot.module.word.poetry.GUSHICI
import sdh.qqbot.module.word.quotation.*
import sdh.qqbot.module.word.repeater.Repeater
import sdh.qqbot.module.word.weather.queryWeather
import sdh.qqbot.utils.pinyinconvert.PinYinUtils

/**
 * @author jj_wen
 * @date 2022/7/27 15:21
 **/
object MessageDict {

    /**
     *   key: 匹配关键字列表
     * value: 键字匹配后执行的方法
     */
    private val dict: MutableMap<Array<out String>, (message: MessageEntity) -> Any> = mutableMapOf()

    init {
        put("色图") { message ->
            if ("private" == message.messageType) {
                //私聊开启色图
                SexPicture.sendSexPicture(message)
            } else {
                QBotSendMessageController.sendMsg(message, "群聊未开启此功能。",null)
            }
        }

        put("抽奖") { message ->
            DrawPrize.DrawPrizeManager(message)
        }

        put("天气") { message ->
            queryWeather.weatherManager(message)
        }

        put("壁纸") { message ->
            wallpaperModule.wallpaperManager(message)
        }

        put("语录") { message ->
            QuerySaying.sayingManager(message)
        }

        put("每日一言") { message ->
            AWordADay.aWordADayManager(message)
        }

        put("古诗词") { message ->
            GUSHICI.gushiManager(message)
        }

        put("舔狗日记") { message ->
            QueryLickTheDogDiary.diaryManager(message)
        }

        put("历史上的今天") { message ->
            TodayOnHistory.todayOnHistoryManager(message)
        }

        put("到点了") { message ->
            QuerySadSayings.sadSayingsManager(message)
        }

        put("早报") { message ->
            DailyNews.dailyNewsManager(message)
        }

        put("二次元") { message ->
            TwoDimensionalSpace.twoDimensionalSpaceEntity(message)
        }

        put("撩人") { message ->
            Prologue.prologueManager(message)
        }

        put("摸鱼") { message ->
            SlackOff.slackOffManager(message)
        }

        put("cos") { message ->
            CosImg.cosImgManager(message)
        }

        put("点歌") { message ->
            Song.songManager(message)
        }

        put("疫情") { message ->
            NcovInfo.NcovInfoManager(message)
        }

        put("早安语录") { message ->
            GoodMorning.goodMorningManager(message)
        }

        put("安慰语录") { message ->
            Comfort.comfortManager(message)
        }

        put("妹纸图", "妹子图", "妹纸图片", "妹子图片") { message ->
            TheGirlImg.theGirlImgManager(message)
        }

        put("帮助") { message ->
            QueryHelp.helpManager(message)
        }

        put("关于") { message ->
            AboutMe.aboutMeManager(message)
        }

        put("关于") { message ->
            AboutMe.aboutMeManager(message)
        }
    }

    /**
     * 方便 key 为多个值时写起来方便
     *
     * @param text 关键字
     * @param func 匹配到关键字时执行的函数
     */
    private fun put(vararg text: String, func: (message: MessageEntity) -> Any) {
        dict[text] = func
    }


    /**
     * 字典匹配 key，并且执行对应字典函数
     *
     * @param message 消息体
     */
    @JvmStatic
    fun matchAndRun(messageEntity: MessageEntity) {
        matchDict(messageEntity).run {
            this(messageEntity)
        }
    }

    /**
     * 字典匹配 key
     *
     * @param messageEntity 消息体
     */
    @JvmStatic
    fun matchDict(messageEntity: MessageEntity): (message: MessageEntity) -> Any {
        val msg = messageEntity.message?.let {
            it.split(" ")[0]
        } ?: ""

        dict.mapKeys { entry ->
            // 根据 pinyin 去匹配
            if (entry.key.any { PinYinUtils.isEquals(it, msg) }) {
                // 从字典查询到了，返回函数
                return entry.value
            }
        }

        // 没匹配到，则复读
        //复读机
        if ("group" == messageEntity.messageType) {
            // 返回复读一下
            return { item ->
                Repeater.repeaterManager(item)
            }
        }

        // 什么都没匹配到，返回空函数
        return {}
    }
}

