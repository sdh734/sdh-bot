package sdh.qqbot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sdh.qqbot.dao.Blacklist;
import sdh.qqbot.mapper.BlacklistMapper;
import sdh.qqbot.service.IBlacklistService;

/**
 *  黑名单服务实现类
 *
 * @author SDH
 * @since 2022-01-08
 */
@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements IBlacklistService {

}
