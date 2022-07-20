package sdh.qqbot.entity.api.tencent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TencentNcovChildrenEntity {
    @JsonProperty("date")
    private String date;
    @JsonProperty("today")
    private TencentNcovTodayEntity today;
    @JsonProperty("total")
    private TencentNcovTotalEntity total;
    @JsonProperty("children")
    private List<TencentNcovChildrenEntity> children;
    @JsonProperty("name")
    private String name;
    @JsonProperty("adcode")
    private String adcode;
}
