package sdh.qqbot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sdh.qqbot.entity.database.Prize;
import sdh.qqbot.mapper.PrizeMapper;
import sdh.qqbot.service.IPrizeService;

/**
 *  奖品服务实现类
 *
 * @author SDH
 * @since 2022-01-09
 */
@Service
public class PrizeServiceImpl extends ServiceImpl<PrizeMapper, Prize> implements IPrizeService {

}
