package sdh.qqbot.entity.api.tencent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TencentNcovTodayEntity {

    @JsonProperty("tip")
    private String tip;
    @JsonProperty("wzz_add")
    private Integer wzzAdd;
    @JsonProperty("local_confirm_add")
    private Integer localConfirmAdd;
    @JsonProperty("abroad_confirm_add")
    private Integer abroadConfirmAdd;
    @JsonProperty("dead_add")
    private Integer deadAdd;
    @JsonProperty("confirm")
    private Integer confirm;
    @JsonProperty("confirmCuts")
    private Integer confirmCuts;
    @JsonProperty("isUpdated")
    private Boolean isUpdated;
}
