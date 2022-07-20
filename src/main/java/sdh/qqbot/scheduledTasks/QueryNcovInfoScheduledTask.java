package sdh.qqbot.scheduledTasks;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sdh.qqbot.controller.api.TencentNcovApiController;
import sdh.qqbot.entity.api.tencent.TencentNcovEntity;

@Component
public class QueryNcovInfoScheduledTask {
    /**
     * 每隔6小时获取数据并保存
     */
    @Scheduled(cron = "0 0 0/6 * * ? ")
    @Async
    public void run() {
        TencentNcovEntity query = TencentNcovApiController.queryAll();
        TencentNcovApiController.insertByTencentNcovEntity(query);
    }
}
