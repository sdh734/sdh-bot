package sdh.qqbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二次元图片实体类
 * @author fusheng
 */
@Data
@NoArgsConstructor
public class TwoDimensionalSpaceEntity {

    @JsonProperty("code")
    private String code;

    @JsonProperty("imgurl")
    private String imgUrl;

    @JsonProperty("width")
    private String width;

    @JsonProperty("height")
    private String height;
}
