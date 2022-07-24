package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 点歌实体类
 * @author fusheng
 */
@Data
@NoArgsConstructor
public class SongEntity {

    @JsonProperty("action")
    private String action;

    @JsonProperty("android_pkg_name")
    private String androidPkgName;

    @JsonProperty("app_type")
    private String appType;

    @JsonProperty("appid")
    private String appId;

    @JsonProperty("ctime")
    private String ctime;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("jumpUrl")
    private String jumpUrl;

    //音乐url
    @JsonProperty("musicUrl")
    private String musicUrl;

    //图片url
    @JsonProperty("preview")
    private String preview;

    @JsonProperty("sourceMsgId")
    private String sourceMsgId;

    @JsonProperty("source_icon")
    private String sourceIcon;

    @JsonProperty("source_url")
    private String sourceUrl;

    @JsonProperty("tag")
    private String tag;

    @JsonProperty("title")
    private String title;

    @JsonProperty("uin")
    private String uin;
}


