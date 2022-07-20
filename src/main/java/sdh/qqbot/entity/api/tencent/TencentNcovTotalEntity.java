package sdh.qqbot.entity.api.tencent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TencentNcovTotalEntity {

    @JsonProperty("provinceLocalConfirm")
    private Integer provinceLocalConfirm;
    @JsonProperty("continueDayZeroConfirmAdd")
    private Integer continueDayZeroConfirmAdd;
    @JsonProperty("continueDayZeroLocalConfirmAdd")
    private Integer continueDayZeroLocalConfirmAdd;
    @JsonProperty("confirm")
    private Integer confirm;
    @JsonProperty("heal")
    private Integer heal;
    @JsonProperty("adcode")
    private String adcode;
    @JsonProperty("nowConfirm")
    private Integer nowConfirm;
    @JsonProperty("mediumRiskAreaNum")
    private Integer mediumRiskAreaNum;
    @JsonProperty("highRiskAreaNum")
    private Integer highRiskAreaNum;
    @JsonProperty("mtime")
    private String mtime;
    @JsonProperty("showHeal")
    private Boolean showHeal;
    @JsonProperty("wzz")
    private Integer wzz;
    @JsonProperty("continueDayZeroConfirm")
    private Integer continueDayZeroConfirm;
    @JsonProperty("dead")
    private Integer dead;
    @JsonProperty("showRate")
    private Boolean showRate;
}
