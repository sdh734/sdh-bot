package sdh.qqbot.entity.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 抽奖获胜者实体类
 *
 * @author SDH
 * @since 2022-01-09
 */
@Getter
@Setter
@TableName("t_winners")
public class Winners implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 奖品ID
     */
    private Integer prizeId;

    /**
     * 中奖者ID
     */
    private Integer userId;

    /**
     * 中奖时间
     */
    private LocalDateTime drawTime;


}
