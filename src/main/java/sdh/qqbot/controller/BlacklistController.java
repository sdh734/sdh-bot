package sdh.qqbot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.dao.Blacklist;
import sdh.qqbot.mapper.BlacklistMapper;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SDH
 * @since 2022-01-08
 */
@RestController
public class BlacklistController {
    //@Autowired
    //private IBlacklistService iBlacklistService;

    //static IBlacklistService blacklistService;
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

        //blacklistService.saveOrUpdate(blacklist, new UpdateWrapper<Blacklist>().eq("blackuser_id", userId));
    }

    /**
     * 移除黑名单
     */
    public static void removeBlackList(String userId) {
        blacklistMapper.delete(new QueryWrapper<Blacklist>().eq("blackuser_id", userId));
//        blacklistService.remove(new QueryWrapper<Blacklist>().eq("blackuser_id", userId));
    }

    @PostConstruct
    public void init() {
        //blacklistService = iBlacklistService;
        blacklistMapper = iBlacklistMapper;
    }
}

