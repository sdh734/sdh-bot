package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 舔狗日记实体类
 * @author fusheng
 */
@NoArgsConstructor
@Data
public class LickTheDogDiaryEntity {

    @JsonProperty("code")
    private String code;

    @JsonProperty("msg")
    private String msg;

}
