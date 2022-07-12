package sdh.qqbot.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户实体类
 * @author SDH
 * @since 2022-02-01
 */
@Getter
@Setter
@TableName("t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户QQ号
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户权限等级
     */
    private Integer userLevel;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 用户地区
     */
    private String area;

    /**
     * 用户群名片
     */
    private String card;

    /**
     * 是否允许修改群名片
     */
    private Boolean cardChangeable;

    /**
     * 群号
     */
    private String groupId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 加群时间戳
     */
    private Long joinTime;

    /**
     * 最后发言时间戳
     */
    private Long lastSentTime;

    /**
     * 成员等级
     */
    private String level;

    /**
     * 角色
     */
    private String role;

    /**
     * 是否不良记录成员
     */
    private Boolean unfriendly;

    /**
     * 专属头衔
     */
    private String title;

    /**
     * 专属头衔过期时间戳
     */
    private String titleExpireTime;

    /**
     * 禁言到期时间
     */
    private String shutUpTimestamp;

    @Override
    public String toString() {
        return "QQ：" + this.userId + "，昵称：" + this.nickname;
    }
}
