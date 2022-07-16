package sdh.qqbot.controller.database;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.entity.database.Blacklist;
import sdh.qqbot.mapper.BlacklistMapper;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 抽奖黑名单接口
 *
 * @author SDH
 * @since 2022-01-08
 */
@RestController
public class BlacklistController {
    static BlacklistMapper blacklistMapper;
    @Autowired
    private BlacklistMapper iBlacklistMapper;

    /*
    判断用户是否在黑名单中
     */
    public static boolean isBlack(String userId) {
        Blacklist blacker = blacklistMapper.selectOne(new QueryWrapper<Blacklist>().eq("blackuser_id", userId).ge("black_time", LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault())));
        return blacker != null;
    }

    /**
     * 加入黑名单
     */
    public static void joinBlackList(String userId) {
        Blacklist blacklist = new Blacklist();
        blacklist.setBlackuserId(userId);
        Date d = new Date();
        long time = 1000 * 60 * 60 * 24;
        d.setTime(d.getTime() + time);
        blacklist.setBlackTime(LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()));
        Blacklist one = blacklistMapper.selectOne(new QueryWrapper<Blacklist>().eq("blackuser_id", userId));
        if (one != null) {
            blacklist.setId(one.getId());
            blacklistMapper.updateById(blacklist);
        } else {
            blacklistMapper.insert(blacklist);
        }

    }

    /**
     * 移除黑名单
     */
    public static void removeBlackList(String userId) {
        blacklistMapper.delete(new QueryWrapper<Blacklist>().eq("blackuser_id", userId));
    }

    @PostConstruct
    public void init() {
        blacklistMapper = iBlacklistMapper;
    }
}

