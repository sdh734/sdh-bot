package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 古诗词一言响应实体类
 *
 * @author SDH
 */
@NoArgsConstructor
@Data
public class GUSHIEntity {
    @JsonProperty("content")
    private String content;
    @JsonProperty("origin")
    private String origin;
    @JsonProperty("author")
    private String author;
    @JsonProperty("category")
    private String category;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.content).append("\n");
        builder.append("——");
        builder.append(this.author).append("·").append("《").append(this.origin).append("》");
        return builder.toString();
    }
}
