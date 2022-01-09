package sdh.qqbot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.dao.User;
import sdh.qqbot.entity.MessageEntity;
import sdh.qqbot.service.IUserService;

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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    static IUserService userService;

    @PostConstruct
    public void init() {
        userService = this.iUserService;
    }

    /**
     * 根据QQ获取User对象
     */
    public static User getUserByQQ(String userQQ) {
        return userService.getOne(new QueryWrapper<User>().eq("user_id", userQQ));
    }

    /**
     * 根据ID获取User对象
     */
    public static User getUserById(int userId) {
        return userService.getOne(new QueryWrapper<User>().eq("id", userId));
    }

    /**
     * 新增用户
     */
    public static void addUser(User user) {
        userService.saveOrUpdate(user, new UpdateWrapper<User>().eq("user_id", user.getUserId()));
    }

    public static void addUser(MessageEntity message) {
        User user = new User();
        user.setUserId(message.getUserId().toString());
        user.setUserName(message.getSender().getNickname());
        userService.saveOrUpdate(user, new UpdateWrapper<User>().eq("user_id", user.getUserId()));
    }

    /**
     * 用户权限判断
     *
     * @param userId 用户qq号
     * @return 用户权限等级
     */
    public static int getPermissionByUserId(String userId) {
        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", userId));
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

}

