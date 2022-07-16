package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 语录查询实体类
 * @author fusheng
 */
@NoArgsConstructor
@Data
public class SayingEntity {
    @JsonProperty("code")
    private String code;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("type")
    private String type;

    @JsonProperty("text")
    private String text;
}
