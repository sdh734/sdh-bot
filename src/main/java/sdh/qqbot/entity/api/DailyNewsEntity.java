package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 每日早报返回数据实体类
 *
 * @author SDh
 */
@NoArgsConstructor
@Data
public class DailyNewsEntity {

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("log_id")
    private Long logId;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("date")
        private String date;
        @JsonProperty("news")
        private List<String> news;
        @JsonProperty("weiyu")
        private String weiyu;
        @JsonProperty("image")
        private String image;
        @JsonProperty("head_image")
        private String headImage;
    }
}
