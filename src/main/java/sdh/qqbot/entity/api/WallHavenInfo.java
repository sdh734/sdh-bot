package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * WallHaven壁纸数据实体类
 * @author SDH
 */
@NoArgsConstructor
@Data
public class WallHavenInfo {

    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("id")
        private String id;
        @JsonProperty("url")
        private String url;
        @JsonProperty("short_url")
        private String shortUrl;
        @JsonProperty("uploader")
        private UploaderDTO uploader;
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
        @JsonProperty("tags")
        private List<TagsDTO> tags;

        @NoArgsConstructor
        @Data
        public static class UploaderDTO {
            @JsonProperty("username")
            private String username;
            @JsonProperty("group")
            private String group;
            @JsonProperty("avatar")
            private AvatarDTO avatar;

            @NoArgsConstructor
            @Data
            public static class AvatarDTO {
                @JsonProperty("200px")
                private String $200px;
                @JsonProperty("128px")
                private String $128px;
                @JsonProperty("32px")
                private String $32px;
                @JsonProperty("20px")
                private String $20px;
            }
        }

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

        @NoArgsConstructor
        @Data
        public static class TagsDTO {
            @JsonProperty("id")
            private Integer id;
            @JsonProperty("name")
            private String name;
            @JsonProperty("alias")
            private String alias;
            @JsonProperty("category_id")
            private Integer categoryId;
            @JsonProperty("category")
            private String category;
            @JsonProperty("purity")
            private String purity;
            @JsonProperty("created_at")
            private String createdAt;
        }
    }
}
