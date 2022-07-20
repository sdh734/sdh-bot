package sdh.qqbot.scheduledTasks;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sdh.qqbot.controller.database.DrawprizeController;
import sdh.qqbot.entity.database.Prize;
import sdh.qqbot.mapper.PrizeMapper;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * 定时抽奖定时任务类
 *
 * @author SDH
 */
@Component
@Slf4j
public class DrawPrizeScheduledTask {
    static PrizeMapper prizeMapper;
    @Autowired
    private PrizeMapper iPrizeMapper;

    @PostConstruct
    public void init() {
        prizeMapper = iPrizeMapper;
    }

    @Scheduled(fixedDelay = 5000)
    @Async
    public void scheduledTask() {
//        log.info("开始执行定时抽奖任务。");
        //获取未抽取的，同时有设置自动开奖时间的奖品列表。
        List<Prize> prizes = prizeMapper.selectList(new QueryWrapper<Prize>().ne("prize_isdraw", 1).gt("prize_drawtime", 0));
        //循环判断当前时间是否超过设定的抽奖时间，如果超过则自动开奖。
        if (prizes.size() > 0) {
            log.info("共查询到" + prizes.size() + "个定时抽奖任务");
            for (Prize p : prizes) {
                log.info("执行奖品为" + p.getPrizeName() + "的定时抽奖任务。");
                LocalDateTime prizeDrawtime = p.getPrizeDrawtime();
                long prizeDrawLongTime = prizeDrawtime.toEpochSecond(ZoneOffset.UTC);
                long nowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
                if (nowTime > prizeDrawLongTime) {
                    DrawprizeController.manualDrawPrize(p);
                    log.info("执奖品为" + p.getPrizeName() + "的定时抽奖任务行成功。");
                } else {
                    log.info("奖品为" + p.getPrizeName() + "的定时抽奖任务执行失败，未到设定的抽奖时间。");
                }
            }
        }
    }
}
