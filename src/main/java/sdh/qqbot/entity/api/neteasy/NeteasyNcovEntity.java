package sdh.qqbot.entity.api.neteasy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class NeteasyNcovEntity {

    @JsonProperty("reqId")
    private Long reqId;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("timestamp")
    private Long timestamp;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("chinaTotal")
        private ChinaTotalDTO chinaTotal;
        @JsonProperty("chinaDayList")
        private List<ChinaDayListDTO> chinaDayList;
        @JsonProperty("lastUpdateTime")
        private String lastUpdateTime;
        @JsonProperty("overseaLastUpdateTime")
        private String overseaLastUpdateTime;
        @JsonProperty("areaTree")
        private List<AreaTreeDTO> areaTree;

        @NoArgsConstructor
        @Data
        public static class ChinaTotalDTO {
            @JsonProperty("today")
            private TodayDTO today;
            @JsonProperty("total")
            private TotalDTO total;
            @JsonProperty("extData")
            private ExtDataDTO extData;
        }

        @NoArgsConstructor
        @Data
        public static class ChinaDayListDTO {
            @JsonProperty("date")
            private String date;
            @JsonProperty("today")
            private TodayDTO today;
            @JsonProperty("total")
            private TotalDTO total;
            @JsonProperty("extData")
            private ExtDataDTO extData;
            @JsonProperty("lastUpdateTime")
            private Object lastUpdateTime;
        }

        @NoArgsConstructor
        @Data
        public static class AreaTreeDTO {
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

        @NoArgsConstructor
        @Data
        public static class ChildrenDTO {
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
    }
}
