package sdh.qqbot.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author SDH
 * @since 2022-01-08
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
    private String userName;

    /**
     * 用户权限等级
     */
    private Integer userLevel;

    @Override
    public String toString() {
        return "QQ:" + this.userId + "，昵称:" + this.userName;
    }
}
