package sdh.qqbot.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class WallHavenSearchResult {

    @JsonProperty("data")
    private List<DataDTO> data;
    @JsonProperty("meta")
    private MetaDTO meta;

    @NoArgsConstructor
    @Data
    public static class MetaDTO {
        @JsonProperty("current_page")
        private Integer currentPage;
        @JsonProperty("last_page")
        private Integer lastPage;
        @JsonProperty("per_page")
        private Integer perPage;
        @JsonProperty("total")
        private Integer total;
        @JsonProperty("query")
        private String query;
        @JsonProperty("seed")
        private Object seed;
    }

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("url")
        private String url;
        @JsonProperty("short_url")
        private String shortUrl;
        @JsonProperty("views")
        private Integer views;
        @JsonProperty("favorites")
        private Integer favorites;
        @JsonProperty("source")
        private String source;
        @JsonProperty("purity")
        private String purity;
        @JsonProperty("category")
        private String category;
        @JsonProperty("dimension_x")
        private Integer dimensionX;
        @JsonProperty("dimension_y")
        private Integer dimensionY;
        @JsonProperty("resolution")
        private String resolution;
        @JsonProperty("ratio")
        private String ratio;
        @JsonProperty("file_size")
        private Integer fileSize;
        @JsonProperty("file_type")
        private String fileType;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("colors")
        private List<String> colors;
        @JsonProperty("path")
        private String path;
        @JsonProperty("thumbs")
        private ThumbsDTO thumbs;

        @NoArgsConstructor
        @Data
        public static class ThumbsDTO {
            @JsonProperty("large")
            private String large;
            @JsonProperty("original")
            private String original;
            @JsonProperty("small")
            private String small;
        }
    }
}
