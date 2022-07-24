package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 查询音乐id内层实体类
 *
 * @author fusheng
 */
@NoArgsConstructor
@Data
public class SongIdCorEntity {

    @JsonProperty("songs")
    private List<String> songs;

    @JsonProperty("songCount")
    private String songCount;
}
