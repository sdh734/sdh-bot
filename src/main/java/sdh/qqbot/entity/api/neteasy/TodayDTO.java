package sdh.qqbot.entity.api.neteasy;

import lombok.Data;

@Data
public class TodayDTO {
    @com.fasterxml.jackson.annotation.JsonProperty("confirm")
    private Integer confirm;
    @com.fasterxml.jackson.annotation.JsonProperty("suspect")
    private Integer suspect;
    @com.fasterxml.jackson.annotation.JsonProperty("heal")
    private Integer heal;
    @com.fasterxml.jackson.annotation.JsonProperty("dead")
    private Integer dead;
    @com.fasterxml.jackson.annotation.JsonProperty("severe")
    private Integer severe;
    @com.fasterxml.jackson.annotation.JsonProperty("storeConfirm")
    private Integer storeConfirm;
    @com.fasterxml.jackson.annotation.JsonProperty("input")
    private Integer input;
}
