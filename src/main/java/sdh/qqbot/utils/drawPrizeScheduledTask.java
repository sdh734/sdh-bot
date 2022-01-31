package sdh.qqbot.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sdh.qqbot.controller.DrawprizeController;
import sdh.qqbot.dao.Prize;
import sdh.qqbot.mapper.PrizeMapper;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
public class drawPrizeScheduledTask {
    static PrizeMapper prizeMapper;
    @Autowired
    private PrizeMapper iPrizeMapper;

    @PostConstruct
    public void init() {
        prizeMapper = iPrizeMapper;
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduledTask() {
        //获取未抽取的，同时有设置自动开奖时间的奖品列表。
        List<Prize> prizes = prizeMapper.selectList(new QueryWrapper<Prize>().ne("prize_isdraw", 1).gt("prize_drawtime", 0));
        //循环判断当前时间是否超过设定的抽奖时间，如果超过则自动开奖。
        if (prizes.size() > 0) {
            Log.i("共查询到" + prizes.size() + "个定时抽奖任务");
            for (Prize p : prizes) {
                Log.i("执行奖品为" + p.getPrizeName() + "的定时抽奖任务。");
                LocalDateTime prizeDrawtime = p.getPrizeDrawtime();
                long prizeDrawLongTime = prizeDrawtime.toEpochSecond(ZoneOffset.UTC);
                long nowTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
                if (nowTime > prizeDrawLongTime) {
                    DrawprizeController.manualDrawPrize(p);
                    Log.i("执奖品为" + p.getPrizeName() + "的定时抽奖任务行成功。");
                } else {
                    Log.i("奖品为" + p.getPrizeName() + "的定时抽奖任务执行失败，未到设定的抽奖时间。");
                }
            }
        }
    }
}