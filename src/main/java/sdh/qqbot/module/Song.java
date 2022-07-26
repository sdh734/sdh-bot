package sdh.qqbot.module;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import sdh.qqbot.controller.api.QueryApiManagerController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.api.SongEntity;
import sdh.qqbot.entity.api.SongIdEntity;
import sdh.qqbot.entity.api.SongModelEntity;
import sdh.qqbot.entity.api.message.MessageEntity;

/**
 * 点歌
 *
 * @author fusheng
 */
@Slf4j
public class Song {

    /**
     * 点歌管理器
     * @param message
     */
    public static void songManager(MessageEntity message) {

        String[] msgArr = message.getMessage().split(" ");
        String songName = message.getMessage().substring(3);
        querySong(message,songName);
    }

    /**
     * 酷我音乐
     * @param message
     * @param songName
     */
    private static void querySongKuwoManager(MessageEntity message, String songName) {
        SongModelEntity songModelEntity = QueryApiManagerController.querySong(songName);
        if (songModelEntity.getMeta().size() == 0) {
            return;
        }
        SongEntity songEntity = JSON.parseObject(songModelEntity.getMeta().get("music"), SongEntity.class);
        String musicUrl = songEntity.getMusicUrl();
        String preview = songEntity.getPreview();
        String title = songEntity.getTitle();
        String desc = songEntity.getDesc();
        log.info("歌曲链接:" + musicUrl);
        log.info("图片链接:" + preview);
        log.info("歌名:" + title);
        log.info("歌手:" + desc);
        String cqMsg = "[CQ:music,type=custom,audio=" + musicUrl + ",title=" + title + ",image = " + preview + ",content=" + desc + "]";
        QBotSendMessageController.sendMsg(message, cqMsg);
    }

    /**
     * 网易云
     * @param message
     * @param songName
     */
    public static void querySong(MessageEntity message, String songName) {
        SongIdEntity songIdEntity = QueryApiManagerController.querySongId(songName);
        if (songIdEntity == null){
            return;
        }
        String songId = songIdEntity.getId();
        log.info("歌曲id：" + songId);
        log.info("歌曲名称：" + songName);
        String cqMsg = " [CQ:music,type=163,id=" + songId + "]";
        QBotSendMessageController.sendMsg(message, cqMsg);
    }
}
