package sdh.qqbot.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ImgUrlEntity {

    @JsonProperty("error")
    private String error;
    @JsonProperty("data")
    private List<DataDTO> data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("pid")
        private Integer pid;
        @JsonProperty("p")
        private Integer p;
        @JsonProperty("uid")
        private Integer uid;
        @JsonProperty("title")
        private String title;
        @JsonProperty("author")
        private String author;
        @JsonProperty("r18")
        private Boolean r18;
        @JsonProperty("width")
        private Integer width;
        @JsonProperty("height")
        private Integer height;
        @JsonProperty("tags")
        private List<String> tags;
        @JsonProperty("ext")
        private String ext;
        @JsonProperty("uploadDate")
        private Long uploadDate;
        @JsonProperty("urls")
        private UrlsDTO urls;

        @NoArgsConstructor
        @Data
        public static class UrlsDTO {
            @JsonProperty("small")
            private String small;
        }

    }
}
