package sdh.qqbot.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("t_drawprize")
public class Drawprize implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参与抽奖id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 奖品id
     */
    private Integer prizeId;

    /**
     * 参与人qq号
     */
    private String playerId;

    /**
     * 参与时间
     */
    private LocalDateTime addtime;

    @Override
    public String toString() {
        return "奖品ID:"+this.prizeId+",参与人员ID:"+this.playerId;
    }
}
