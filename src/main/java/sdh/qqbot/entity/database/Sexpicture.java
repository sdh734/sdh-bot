package sdh.qqbot.entity.database;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 色图实体类
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
