package sdh.qqbot.entity.api;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取网易云id外层的实体类
 * @author fusheng
 */
@Data
@NoArgsConstructor
public class SongIdModelEntity {


    @JsonProperty("result")
    private String result;

    @JsonProperty("code")
    private String code;
}
