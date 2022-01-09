package sdh.qqbot.service.impl;

import sdh.qqbot.dao.Blacklist;
import sdh.qqbot.mapper.BlacklistMapper;
import sdh.qqbot.service.IBlacklistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SDH
 * @since 2022-01-08
 */
@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements IBlacklistService {

}
