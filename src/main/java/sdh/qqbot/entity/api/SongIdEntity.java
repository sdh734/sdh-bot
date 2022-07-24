package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 音乐id实体类
 * @author fusheng
 */
@NoArgsConstructor
@Data
public class SongIdEntity {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("position")
    private String position;

    @JsonProperty("alias")
    private List<String> alias;

    @JsonProperty("status")
    private String status;

    @JsonProperty("fee")
    private String fee;

    @JsonProperty("copyrightId")
    private String copyrightId;

    @JsonProperty("disc")
    private String disc;

    @JsonProperty("no")
    private String no;

    @JsonProperty("artists")
    private List<String> artists;

    @JsonProperty("album")
    private String album;
}
