package sdh.qqbot.entity.api.cdc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CDCNcovInfoEntity {

    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("end_update_time")
        private String endUpdateTime;
        @JsonProperty("hcount")
        private Integer hcount;
        @JsonProperty("mcount")
        private Integer mcount;
        @JsonProperty("highlist")
        private List<HighlistDTO> highlist;
        @JsonProperty("middlelist")
        private List<MiddlelistDTO> middlelist;

        @NoArgsConstructor
        @Data
        public static class HighlistDTO {
            @JsonProperty("type")
            private String type;
            @JsonProperty("province")
            private String province;
            @JsonProperty("city")
            private String city;
            @JsonProperty("county")
            private String county;
            @JsonProperty("area_name")
            private String areaName;
            @JsonProperty("communitys")
            private List<String> communitys;
        }

        @NoArgsConstructor
        @Data
        public static class MiddlelistDTO {
            @JsonProperty("type")
            private String type;
            @JsonProperty("province")
            private String province;
            @JsonProperty("city")
            private String city;
            @JsonProperty("county")
            private String county;
            @JsonProperty("area_name")
            private String areaName;
            @JsonProperty("communitys")
            private List<String> communitys;
        }
    }
}
