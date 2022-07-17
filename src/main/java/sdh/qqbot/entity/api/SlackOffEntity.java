package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * 摸鱼实体类
 * @author fusheng
 */
@NoArgsConstructor
@Data
public class SlackOffEntity {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private HashMap<String,String> data;
}
