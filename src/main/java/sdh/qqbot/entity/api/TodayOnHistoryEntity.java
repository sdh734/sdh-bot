package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 历史上的今天实体类
 * @author fusheng
 */
@Data
@NoArgsConstructor
public class TodayOnHistoryEntity {

    @JsonProperty("code")
    private String code;

    @JsonProperty("day")
    private String day;

    @JsonProperty("content")
    private List<String> content;
}
