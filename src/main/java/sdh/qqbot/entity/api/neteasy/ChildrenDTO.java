package sdh.qqbot.entity.api.neteasy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ChildrenDTO {
    @JsonProperty("today")
    private TodayDTO today;
    @JsonProperty("total")
    private TotalDTO total;
    @JsonProperty("extData")
    private ExtDataDTO extData;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private String id;
    @JsonProperty("lastUpdateTime")
    private String lastUpdateTime;
    @JsonProperty("children")
    private List<ChildrenDTO> children;

}
