package sdh.qqbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 天气查询返回实体对象
 * @author SDH
 */
@NoArgsConstructor
@Data
public class WeatherEntity {

    @JsonProperty("status")
    private String status;
    @JsonProperty("api_version")
    private String apiVersion;
    @JsonProperty("api_status")
    private String apiStatus;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("tzshift")
    private Integer tzshift;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("server_time")
    private Integer serverTime;
    @JsonProperty("location")
    private List<Double> location;
    @JsonProperty("result")
    private ResultDTO result;

    @NoArgsConstructor
    @Data
    public static class ResultDTO {
        @JsonProperty("realtime")
        private RealtimeDTO realtime;
        @JsonProperty("minutely")
        private MinutelyDTO minutely;
        @JsonProperty("hourly")
        private HourlyDTO hourly;
        @JsonProperty("daily")
        private DailyDTO daily;
        @JsonProperty("primary")
        private Integer primary;
        @JsonProperty("forecast_keypoint")
        private String forecastKeypoint;

        @NoArgsConstructor
        @Data
        public static class RealtimeDTO {
            @JsonProperty("status")
            private String status;
            @JsonProperty("temperature")
            private Integer temperature;
            @JsonProperty("humidity")
            private Double humidity;
            @JsonProperty("cloudrate")
            private Integer cloudrate;
            @JsonProperty("skycon")
            private String skycon;
            @JsonProperty("visibility")
            private Integer visibility;
            @JsonProperty("dswrf")
            private Double dswrf;
            @JsonProperty("wind")
            private WindDTO wind;
            @JsonProperty("pressure")
            private Double pressure;
            @JsonProperty("apparent_temperature")
            private Double apparentTemperature;
            @JsonProperty("precipitation")
            private PrecipitationDTO precipitation;
            @JsonProperty("air_quality")
            private AirQualityDTO airQuality;
            @JsonProperty("life_index")
            private LifeIndexDTO lifeIndex;

            @NoArgsConstructor
            @Data
            public static class WindDTO {
                @JsonProperty("speed")
                private Double speed;
                @JsonProperty("direction")
                private Integer direction;
            }

            @NoArgsConstructor
            @Data
            public static class PrecipitationDTO {
                @JsonProperty("local")
                private LocalDTO local;
                @JsonProperty("nearest")
                private NearestDTO nearest;

                @NoArgsConstructor
                @Data
                public static class LocalDTO {
                    @JsonProperty("status")
                    private String status;
                    @JsonProperty("datasource")
                    private String datasource;
                    @JsonProperty("intensity")
                    private Integer intensity;
                }

                @NoArgsConstructor
                @Data
                public static class NearestDTO {
                    @JsonProperty("status")
                    private String status;
                    @JsonProperty("distance")
                    private Integer distance;
                    @JsonProperty("intensity")
                    private Integer intensity;
                }
            }

            @NoArgsConstructor
            @Data
            public static class AirQualityDTO {
                @JsonProperty("pm25")
                private Integer pm25;
                @JsonProperty("pm10")
                private Integer pm10;
                @JsonProperty("o3")
                private Integer o3;
                @JsonProperty("so2")
                private Integer so2;
                @JsonProperty("no2")
                private Integer no2;
                @JsonProperty("co")
                private Double co;
                @JsonProperty("aqi")
                private AqiDTO aqi;
                @JsonProperty("description")
                private DescriptionDTO description;

                @NoArgsConstructor
                @Data
                public static class AqiDTO {
                    @JsonProperty("chn")
                    private Integer chn;
                    @JsonProperty("usa")
                    private Integer usa;
                }

                @NoArgsConstructor
                @Data
                public static class DescriptionDTO {
                    @JsonProperty("chn")
                    private String chn;
                    @JsonProperty("usa")
                    private String usa;
                }
            }

            @NoArgsConstructor
            @Data
            public static class LifeIndexDTO {
                @JsonProperty("ultraviolet")
                private UltravioletDTO ultraviolet;
                @JsonProperty("comfort")
                private ComfortDTO comfort;

                @NoArgsConstructor
                @Data
                public static class UltravioletDTO {
                    @JsonProperty("index")
                    private Integer index;
                    @JsonProperty("desc")
                    private String desc;
                }

                @NoArgsConstructor
                @Data
                public static class ComfortDTO {
                    @JsonProperty("index")
                    private Integer index;
                    @JsonProperty("desc")
                    private String desc;
                }
            }
        }

        @NoArgsConstructor
        @Data
        public static class MinutelyDTO {
            @JsonProperty("status")
            private String status;
            @JsonProperty("datasource")
            private String datasource;
            @JsonProperty("precipitation_2h")
            private List<Integer> precipitation2h;
            @JsonProperty("precipitation")
            private List<Integer> precipitation;
            @JsonProperty("probability")
            private List<Integer> probability;
            @JsonProperty("description")
            private String description;
        }

        @NoArgsConstructor
        @Data
        public static class HourlyDTO {
            @JsonProperty("status")
            private String status;
            @JsonProperty("description")
            private String description;
            @JsonProperty("precipitation")
            private List<PrecipitationDTO> precipitation;
            @JsonProperty("temperature")
            private List<TemperatureDTO> temperature;
            @JsonProperty("wind")
            private List<WindDTO> wind;
            @JsonProperty("humidity")
            private List<HumidityDTO> humidity;
            @JsonProperty("cloudrate")
            private List<CloudrateDTO> cloudrate;
            @JsonProperty("skycon")
            private List<SkyconDTO> skycon;
            @JsonProperty("pressure")
            private List<PressureDTO> pressure;
            @JsonProperty("visibility")
            private List<VisibilityDTO> visibility;
            @JsonProperty("dswrf")
            private List<DswrfDTO> dswrf;
            @JsonProperty("air_quality")
            private AirQualityDTO airQuality;

            @NoArgsConstructor
            @Data
            public static class AirQualityDTO {
                @JsonProperty("aqi")
                private List<AqiDTO> aqi;
                @JsonProperty("pm25")
                private List<Pm25DTO> pm25;

                @NoArgsConstructor
                @Data
                public static class AqiDTO {
                    @JsonProperty("datetime")
                    private String datetime;
                    @JsonProperty("value")
                    private ValueDTO value;

                    @NoArgsConstructor
                    @Data
                    public static class ValueDTO {
                        @JsonProperty("chn")
                        private Integer chn;
                        @JsonProperty("usa")
                        private Integer usa;
                    }
                }

                @NoArgsConstructor
                @Data
                public static class Pm25DTO {
                    @JsonProperty("datetime")
                    private String datetime;
                    @JsonProperty("value")
                    private Integer value;
                }
            }

            @NoArgsConstructor
            @Data
            public static class PrecipitationDTO {
                @JsonProperty("datetime")
                private String datetime;
                @JsonProperty("value")
                private Integer value;
            }

            @NoArgsConstructor
            @Data
            public static class TemperatureDTO {
                @JsonProperty("datetime")
                private String datetime;
                @JsonProperty("value")
                private Integer value;
            }

            @NoArgsConstructor
            @Data
            public static class WindDTO {
                @JsonProperty("datetime")
                private String datetime;
                @JsonProperty("speed")
                private Double speed;
                @JsonProperty("direction")
                private Integer direction;
            }

            @NoArgsConstructor
            @Data
            public static class HumidityDTO {
                @JsonProperty("datetime")
                private String datetime;
                @JsonProperty("value")
                private Double value;
            }

            @NoArgsConstructor
            @Data
            public static class CloudrateDTO {
                @JsonProperty("datetime")
                private String datetime;
                @JsonProperty("value")
                private Integer value;
            }

            @NoArgsConstructor
            @Data
            public static class SkyconDTO {
                @JsonProperty("datetime")
                private String datetime;
                @JsonProperty("value")
                private String value;
            }

            @NoArgsConstructor
            @Data
            public static class PressureDTO {
                @JsonProperty("datetime")
                private String datetime;
                @JsonProperty("value")
                private Double value;
            }

            @NoArgsConstructor
            @Data
            public static class VisibilityDTO {
                @JsonProperty("datetime")
                private String datetime;
                @JsonProperty("value")
                private Integer value;
            }

            @NoArgsConstructor
            @Data
            public static class DswrfDTO {
                @JsonProperty("datetime")
                private String datetime;
                @JsonProperty("value")
                private Double value;
            }
        }

        @NoArgsConstructor
        @Data
        public static class DailyDTO {
            @JsonProperty("status")
            private String status;
            @JsonProperty("astro")
            private List<AstroDTO> astro;
            @JsonProperty("precipitation")
            private List<PrecipitationDTO> precipitation;
            @JsonProperty("temperature")
            private List<TemperatureDTO> temperature;
            @JsonProperty("wind")
            private List<WindDTO> wind;
            @JsonProperty("humidity")
            private List<HumidityDTO> humidity;
            @JsonProperty("cloudrate")
            private List<CloudrateDTO> cloudrate;
            @JsonProperty("pressure")
            private List<PressureDTO> pressure;
            @JsonProperty("visibility")
            private List<VisibilityDTO> visibility;
            @JsonProperty("dswrf")
            private List<DswrfDTO> dswrf;
            @JsonProperty("air_quality")
            private AirQualityDTO airQuality;
            @JsonProperty("skycon")
            private List<SkyconDTO> skycon;
            @JsonProperty("skycon_08h_20h")
            private List<Skycon08h20hDTO> skycon08h20h;
            @JsonProperty("skycon_20h_32h")
            private List<Skycon20h32hDTO> skycon20h32h;
            @JsonProperty("life_index")
            private LifeIndexDTO lifeIndex;

            @NoArgsConstructor
            @Data
            public static class AirQualityDTO {
                @JsonProperty("aqi")
                private List<AqiDTO> aqi;
                @JsonProperty("pm25")
                private List<Pm25DTO> pm25;

                @NoArgsConstructor
                @Data
                public static class AqiDTO {
                    @JsonProperty("date")
                    private String date;
                    @JsonProperty("max")
                    private MaxDTO max;
                    @JsonProperty("avg")
                    private AvgDTO avg;
                    @JsonProperty("min")
                    private MinDTO min;

                    @NoArgsConstructor
                    @Data
                    public static class MaxDTO {
                        @JsonProperty("chn")
                        private Integer chn;
                        @JsonProperty("usa")
                        private Integer usa;
                    }

                    @NoArgsConstructor
                    @Data
                    public static class AvgDTO {
                        @JsonProperty("chn")
                        private Integer chn;
                        @JsonProperty("usa")
                        private Integer usa;
                    }

                    @NoArgsConstructor
                    @Data
                    public static class MinDTO {
                        @JsonProperty("chn")
                        private Integer chn;
                        @JsonProperty("usa")
                        private Integer usa;
                    }
                }

                @NoArgsConstructor
                @Data
                public static class Pm25DTO {
                    @JsonProperty("date")
                    private String date;
                    @JsonProperty("max")
                    private Integer max;
                    @JsonProperty("avg")
                    private Integer avg;
                    @JsonProperty("min")
                    private Integer min;
                }
            }

            @NoArgsConstructor
            @Data
            public static class LifeIndexDTO {
                @JsonProperty("ultraviolet")
                private List<UltravioletDTO> ultraviolet;
                @JsonProperty("carWashing")
                private List<CarWashingDTO> carWashing;
                @JsonProperty("dressing")
                private List<DressingDTO> dressing;
                @JsonProperty("comfort")
                private List<ComfortDTO> comfort;
                @JsonProperty("coldRisk")
                private List<ColdRiskDTO> coldRisk;

                @NoArgsConstructor
                @Data
                public static class UltravioletDTO {
                    @JsonProperty("date")
                    private String date;
                    @JsonProperty("index")
                    private String index;
                    @JsonProperty("desc")
                    private String desc;
                }

                @NoArgsConstructor
                @Data
                public static class CarWashingDTO {
                    @JsonProperty("date")
                    private String date;
                    @JsonProperty("index")
                    private String index;
                    @JsonProperty("desc")
                    private String desc;
                }

                @NoArgsConstructor
                @Data
                public static class DressingDTO {
                    @JsonProperty("date")
                    private String date;
                    @JsonProperty("index")
                    private String index;
                    @JsonProperty("desc")
                    private String desc;
                }

                @NoArgsConstructor
                @Data
                public static class ComfortDTO {
                    @JsonProperty("date")
                    private String date;
                    @JsonProperty("index")
                    private String index;
                    @JsonProperty("desc")
                    private String desc;
                }

                @NoArgsConstructor
                @Data
                public static class ColdRiskDTO {
                    @JsonProperty("date")
                    private String date;
                    @JsonProperty("index")
                    private String index;
                    @JsonProperty("desc")
                    private String desc;
                }
            }

            @NoArgsConstructor
            @Data
            public static class AstroDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("sunrise")
                private SunriseDTO sunrise;
                @JsonProperty("sunset")
                private SunsetDTO sunset;

                @NoArgsConstructor
                @Data
                public static class SunriseDTO {
                    @JsonProperty("time")
                    private String time;
                }

                @NoArgsConstructor
                @Data
                public static class SunsetDTO {
                    @JsonProperty("time")
                    private String time;
                }
            }

            @NoArgsConstructor
            @Data
            public static class PrecipitationDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("max")
                private Integer max;
                @JsonProperty("min")
                private Integer min;
                @JsonProperty("avg")
                private Integer avg;
            }

            @NoArgsConstructor
            @Data
            public static class TemperatureDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("max")
                private Double max;
                @JsonProperty("min")
                private Integer min;
                @JsonProperty("avg")
                private Double avg;
            }

            @NoArgsConstructor
            @Data
            public static class WindDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("max")
                private MaxDTO max;
                @JsonProperty("min")
                private MinDTO min;
                @JsonProperty("avg")
                private AvgDTO avg;

                @NoArgsConstructor
                @Data
                public static class MaxDTO {
                    @JsonProperty("speed")
                    private Double speed;
                    @JsonProperty("direction")
                    private Integer direction;
                }

                @NoArgsConstructor
                @Data
                public static class MinDTO {
                    @JsonProperty("speed")
                    private Double speed;
                    @JsonProperty("direction")
                    private Double direction;
                }

                @NoArgsConstructor
                @Data
                public static class AvgDTO {
                    @JsonProperty("speed")
                    private Double speed;
                    @JsonProperty("direction")
                    private Double direction;
                }
            }

            @NoArgsConstructor
            @Data
            public static class HumidityDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("max")
                private Double max;
                @JsonProperty("min")
                private Double min;
                @JsonProperty("avg")
                private Double avg;
            }

            @NoArgsConstructor
            @Data
            public static class CloudrateDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("max")
                private Double max;
                @JsonProperty("min")
                private Integer min;
                @JsonProperty("avg")
                private Double avg;
            }

            @NoArgsConstructor
            @Data
            public static class PressureDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("max")
                private Double max;
                @JsonProperty("min")
                private Double min;
                @JsonProperty("avg")
                private Double avg;
            }

            @NoArgsConstructor
            @Data
            public static class VisibilityDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("max")
                private Integer max;
                @JsonProperty("min")
                private Double min;
                @JsonProperty("avg")
                private Double avg;
            }

            @NoArgsConstructor
            @Data
            public static class DswrfDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("max")
                private Double max;
                @JsonProperty("min")
                private Integer min;
                @JsonProperty("avg")
                private Double avg;
            }

            @NoArgsConstructor
            @Data
            public static class SkyconDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("value")
                private String value;
            }

            @NoArgsConstructor
            @Data
            public static class Skycon08h20hDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("value")
                private String value;
            }

            @NoArgsConstructor
            @Data
            public static class Skycon20h32hDTO {
                @JsonProperty("date")
                private String date;
                @JsonProperty("value")
                private String value;
            }
        }
    }

}
