package sdh.qqbot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sdh.qqbot.entity.database.Winners;
import sdh.qqbot.mapper.WinnersMapper;
import sdh.qqbot.service.IWinnersService;

/**
 *  抽奖获胜者服务实现类
 *
 * @author SDH
 * @since 2022-01-09
 */
@Service
public class WinnersServiceImpl extends ServiceImpl<WinnersMapper, Winners> implements IWinnersService {

}
