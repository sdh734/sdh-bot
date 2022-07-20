package sdh.qqbot.entity.api.tencent;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TencentNcovEntity {

    @JsonProperty("ret")
    private Integer ret;
    @JsonProperty("info")
    private String info;
    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("diseaseh5Shelf")
        private NcovDTO diseaseh5Shelf;

        @NoArgsConstructor
        @Data
        public static class NcovDTO {
            @JsonProperty("areaTree")
            private List<AreaTreeDTO> areaTree;
            @JsonProperty("lastUpdateTime")
            private String lastUpdateTime;
            @JsonProperty("chinaTotal")
            private ChinaTotalDTO chinaTotal;
            @JsonProperty("chinaAdd")
            private ChinaAddDTO chinaAdd;
            @JsonProperty("isShowAdd")
            private Boolean isShowAdd;
            @JsonProperty("showAddSwitch")
            private ShowAddSwitchDTO showAddSwitch;

            @NoArgsConstructor
            @Data
            public static class ChinaTotalDTO {
                @JsonProperty("confirm")
                private Integer confirm;
                @JsonProperty("nowSevere")
                private Integer nowSevere;
                @JsonProperty("importedCase")
                private Integer importedCase;
                @JsonProperty("showLocalConfirm")
                private Integer showLocalConfirm;
                @JsonProperty("noInfectH5")
                private Integer noInfectH5;
                @JsonProperty("mediumRiskAreaNum")
                private Integer mediumRiskAreaNum;
                @JsonProperty("suspect")
                private Integer suspect;
                @JsonProperty("localConfirmH5")
                private Integer localConfirmH5;
                @JsonProperty("confirmAdd")
                private Integer confirmAdd;
                @JsonProperty("dead")
                private Integer dead;
                @JsonProperty("local_acc_confirm")
                private Integer localAccConfirm;
                @JsonProperty("localWzzAdd")
                private Integer localWzzAdd;
                @JsonProperty("localConfirmAdd")
                private Integer localConfirmAdd;
                @JsonProperty("nowConfirm")
                private Integer nowConfirm;
                @JsonProperty("heal")
                private Integer heal;
                @JsonProperty("noInfect")
                private Integer noInfect;
                @JsonProperty("showlocalinfeciton")
                private Integer showlocalinfeciton;
                @JsonProperty("nowLocalWzz")
                private Integer nowLocalWzz;
                @JsonProperty("deadAdd")
                private Integer deadAdd;
                @JsonProperty("mtime")
                private String mtime;
                @JsonProperty("highRiskAreaNum")
                private Integer highRiskAreaNum;
                @JsonProperty("localConfirm")
                private Integer localConfirm;
                @JsonProperty("mRiskTime")
                private String mRiskTime;
            }

            @NoArgsConstructor
            @Data
            public static class ChinaAddDTO {
                @JsonProperty("nowSevere")
                private Integer nowSevere;
                @JsonProperty("importedCase")
                private Integer importedCase;
                @JsonProperty("noInfect")
                private Integer noInfect;
                @JsonProperty("localConfirm")
                private Integer localConfirm;
                @JsonProperty("confirm")
                private Integer confirm;
                @JsonProperty("heal")
                private Integer heal;
                @JsonProperty("dead")
                private Integer dead;
                @JsonProperty("suspect")
                private Integer suspect;
                @JsonProperty("localConfirmH5")
                private Integer localConfirmH5;
                @JsonProperty("nowConfirm")
                private Integer nowConfirm;
                @JsonProperty("noInfectH5")
                private Integer noInfectH5;
            }

            @NoArgsConstructor
            @Data
            public static class ShowAddSwitchDTO {
                @JsonProperty("confirm")
                private Boolean confirm;
                @JsonProperty("suspect")
                private Boolean suspect;
                @JsonProperty("importedCase")
                private Boolean importedCase;
                @JsonProperty("nowConfirm")
                private Boolean nowConfirm;
                @JsonProperty("nowSevere")
                private Boolean nowSevere;
                @JsonProperty("noInfect")
                private Boolean noInfect;
                @JsonProperty("localConfirm")
                private Boolean localConfirm;
                @JsonProperty("localinfeciton")
                private Boolean localinfeciton;
                @JsonProperty("all")
                private Boolean all;
                @JsonProperty("dead")
                private Boolean dead;
                @JsonProperty("heal")
                private Boolean heal;
            }

            @NoArgsConstructor
            @Data
            public static class AreaTreeDTO {
                @JsonProperty("children")
                private List<TencentNcovChildrenEntity> children;
                @JsonProperty("name")
                private String name;
                @JsonProperty("today")
                private TencentNcovTodayEntity today;
                @JsonProperty("total")
                private TencentNcovTotalEntity total;
            }
        }
    }
}
