package sdh.qqbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

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

    @JsonProperty("newslist")
    private List<HashMap<String,String>> newsList;


}
