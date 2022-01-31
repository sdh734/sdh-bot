package sdh.qqbot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.dao.User;
import sdh.qqbot.entity.MessageEntity;
import sdh.qqbot.mapper.UserMapper;

import javax.annotation.PostConstruct;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SDH
 * @since 2022-01-08
 */
@RestController
public class UserController {
    static UserMapper userMapper;
    @Autowired
    private UserMapper iUserMapper;

    /**
     * 根据QQ获取User对象
     */
    public static User getUserByQQ(String userQQ) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("user_id", userQQ));
//        return userService.getOne(new QueryWrapper<User>().eq("user_id", userQQ));
    }

    /**
     * 根据ID获取User对象
     */
    public static User getUserById(int userId) {
        return userMapper.selectById(userId);
//        return userService.getOne(new QueryWrapper<User>().eq("id", userId));
    }

    /**
     * 新增用户
     */
    public static void addUser(User user) {
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", user.getUserId()));
        if (user1 != null) {
            user.setId(user1.getId());
            userMapper.updateById(user);
        } else {
            userMapper.insert(user);
        }

    }

    public static void addUser(MessageEntity message) {
        User user = new User();
        user.setUserId(message.getUserId());
        user.setUserName(message.getSender().getNickname());
        addUser(user);
    }

    /**
     * 用户权限判断
     *
     * @param userId 用户qq号
     * @return 用户权限等级
     */
    public static int getPermissionByUserId(String userId) {

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", userId));
        /*
          如果数据库不存在该用户则新增
         */
        if (user == null) {
            User user1 = new User();
            user1.setUserId(userId);
            addUser(user1);
        }
        return user != null ? user.getUserLevel() : 0;
    }

    @PostConstruct
    public void init() {
        userMapper = iUserMapper;
    }

}

