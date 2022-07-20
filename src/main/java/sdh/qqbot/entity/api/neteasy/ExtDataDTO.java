package sdh.qqbot.entity.api.neteasy;

import lombok.Data;

@Data
public class ExtDataDTO {
    @com.fasterxml.jackson.annotation.JsonProperty("noSymptom")
    private Integer noSymptom;
    @com.fasterxml.jackson.annotation.JsonProperty("incrNoSymptom")
    private Integer incrNoSymptom;
}
