package sdh.qqbot.entity.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

/**
 * @author fusheng
 */
@NoArgsConstructor
@Data
public class SongModelEntity{
    @JsonProperty("app")
    private String app;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("view")
    private String view;

    @JsonProperty("ver")
    private String ver;

    @JsonProperty("prompt")
    private String prompt;

    @JsonProperty("appID")
    private String appId;

    @JsonProperty("sourceName")
    private String sourceName;

    @JsonProperty("actionData")
    private String actionData;

    @JsonProperty("actionData_A")
    private String actionDataA;

    @JsonProperty("sourceUrl")
    private String sourceUrl;

    @JsonProperty("meta")
    private HashMap<String,String> meta;
}