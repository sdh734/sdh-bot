package sdh.qqbot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import sdh.qqbot.entity.database.User;
import sdh.qqbot.mapper.UserMapper;
import sdh.qqbot.service.IUserService;

/**
 * 用户服务实现类
 *
 * @author SDH
 * @since 2022-02-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
