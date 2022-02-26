package sdh.qqbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 天气查询城市实体类
 */
@NoArgsConstructor
@Data
public class WeatherCityEntity {

    @JsonProperty("status")
    private String status;
    @JsonProperty("info")
    private String info;
    @JsonProperty("infocode")
    private String infocode;
    @JsonProperty("count")
    private String count;
    @JsonProperty("geocodes")
    private List<GeocodesDTO> geocodes;

    @Override
    public String toString() {
        return "城市名：" + this.geocodes.get(0).getFormattedAddress() + "，经纬度：" + this.geocodes.get(0).getLocation();
    }

    @NoArgsConstructor
    @Data
    public static class GeocodesDTO {
        @JsonProperty("formatted_address")
        private String formattedAddress;
        @JsonProperty("country")
        private String country;
        @JsonProperty("province")
        private String province;
        @JsonProperty("citycode")
        private String citycode;
        @JsonProperty("city")
        private String city;
        @JsonProperty("district")
        private String district;
        @JsonProperty("township")
        private List<?> township;
        @JsonProperty("neighborhood")
        private NeighborhoodDTO neighborhood;
        @JsonProperty("building")
        private BuildingDTO building;
        @JsonProperty("adcode")
        private String adcode;
        @JsonProperty("street")
        private List<?> street;
        @JsonProperty("number")
        private List<?> number;
        @JsonProperty("location")
        private String location;
        @JsonProperty("level")
        private String level;

        @NoArgsConstructor
        @Data
        public static class NeighborhoodDTO {
            @JsonProperty("name")
            private List<?> name;
            @JsonProperty("type")
            private List<?> type;
        }

        @NoArgsConstructor
        @Data
        public static class BuildingDTO {
            @JsonProperty("name")
            private List<?> name;
            @JsonProperty("type")
            private List<?> type;
        }
    }
}
