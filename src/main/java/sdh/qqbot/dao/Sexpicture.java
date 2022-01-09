package sdh.qqbot.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("t_sexpicture")
public class Sexpicture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 色图本地数据库ID
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 色图p站id
     */
      private String pictureId;

    /**
     * 色图url
     */
    private String pictureUrl;


}
