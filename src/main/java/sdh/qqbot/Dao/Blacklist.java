package sdh.qqbot.Dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

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
@TableName("t_blacklist")
public class Blacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 黑名单id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 黑名单用户qq
     */
    private String blackuserId;

    /**
     * 黑名单时间,默认1天
     */
    private LocalDateTime blackTime;


}
