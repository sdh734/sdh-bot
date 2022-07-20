package sdh.qqbot.entity.api.tencent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TencentNcovRiskAreaEntity {

    @JsonProperty("data")
    private List<DataDTO> data;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("version")
    private Integer version;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("_id")
        private String id;
        @JsonProperty("area")
        private String area;
        @JsonProperty("type")
        private String type;
        @JsonProperty("sid")
        private Integer sid;
        @JsonProperty("_ctime")
        private String ctime;
        @JsonProperty("_mtime")
        private String mtime;
        @JsonProperty("province")
        private String province;
        @JsonProperty("city")
        private String city;
        @JsonProperty("adcode")
        private String adcode;
        @JsonProperty("district_adcode")
        private String districtAdcode;
    }
}
