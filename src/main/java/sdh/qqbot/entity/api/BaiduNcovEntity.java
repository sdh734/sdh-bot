package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BaiduNcovEntity {

    @JsonProperty("status")
    private Integer status;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private List<DataDTO> data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("name")
        private String name;
        @JsonProperty("trend")
        private TrendDTO trend;
        @JsonProperty("trendMonth")
        private TrendMonthDTO trendMonth;

        @NoArgsConstructor
        @Data
        public static class TrendDTO {
            @JsonProperty("updateDate")
            private List<String> updateDate;
            @JsonProperty("list")
            private List<ListDTO> list;

            @NoArgsConstructor
            @Data
            public static class ListDTO {
                @JsonProperty("name")
                private String name;
                @JsonProperty("data")
                private List<Integer> data;
            }
        }

        @NoArgsConstructor
        @Data
        public static class TrendMonthDTO {
            @JsonProperty("updateMonth")
            private List<String> updateMonth;
            @JsonProperty("list")
            private List<ListDTO> list;

            @NoArgsConstructor
            @Data
            public static class ListDTO {
                @JsonProperty("name")
                private String name;
                @JsonProperty("data")
                private List<Integer> data;
            }
        }
    }
}
