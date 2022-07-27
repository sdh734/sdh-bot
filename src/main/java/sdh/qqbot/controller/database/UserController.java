

package sdh.qqbot.controller.database;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sdh.qqbot.controller.message.QBotSendMessageController;
import sdh.qqbot.entity.database.User;
import sdh.qqbot.entity.message.MessageEntity;
import sdh.qqbot.entity.message.SendMessageEntity;
import sdh.qqbot.mapper.UserMapper;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户操作接口
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
    }

    /**
     * 根据ID获取User对象
     */
    public static User getUserById(int userId) {
        return userMapper.selectById(userId);
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

    /**
     * 根据收到消息自动添加用户
     *
     * @param message 消息实体
     */
    public static void addUser(MessageEntity message) {
        User user = new User();
        //群聊消息和私聊消息差异化处理
        if ("private".equals(message.getMessageType())) {
            user.setUserId(message.getUserId());
            user.setNickname(message.getSender().getNickname());
            addUser(user);
        } else {
            getUserInfo(message.getGroupId(), message.getUserId());
        }
    }

    /**
     * 根据群号和qq号，获取群成员信息
     *
     * @param groupId 群号
     * @param userId  qq号
     */
    private static void getUserInfo(String groupId, String userId) {
        SendMessageEntity msg = new SendMessageEntity();
        msg.setAction("get_group_member_info");
        msg.setEcho("getGroupMemberInfo");
        Map<String, String> params = new HashMap<>();
        params.put("group_id", groupId);
        params.put("user_id", userId);
        msg.setParams(params);
        QBotSendMessageController.sendMsgBySendMessageEntity(msg);
    }

    /**
     * 用户权限判断
     *
     * @param userId 用户qq号
     * @return 用户权限等级
     */
    public static int getPermissionByUserId(String userId) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", userId));
        return user != null ? user.getUserLevel() : 0;
    }

    /**
     * 用户组判断
     *
     * @param userId 用户qq号
     * @return 用户组名称
     */
    public static String getRoleByUserId(String userId) {

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", userId));
        return user != null ? user.getLevel() : null;
    }

    @PostConstruct
    public void init() {
        userMapper = iUserMapper;
    }

}