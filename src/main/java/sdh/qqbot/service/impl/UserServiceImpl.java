package sdh.qqbot.service.impl;

import sdh.qqbot.dao.User;
import sdh.qqbot.mapper.UserMapper;
import sdh.qqbot.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
