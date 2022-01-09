package sdh.qqbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 群聊消息实体类 已弃用
 */
@NoArgsConstructor
@Data
public class GroupMessageEntity {

    @JsonProperty("anonymous")
    private Object anonymous;
    @JsonProperty("font")
    private Integer font;
    @JsonProperty("group_id")
    private Integer groupId;
    @JsonProperty("message")
    private String message;
    @JsonProperty("message_id")
    private Integer messageId;
    @JsonProperty("message_seq")
    private Integer messageSeq;
    @JsonProperty("message_type")
    private String messageType;
    @JsonProperty("post_type")
    private String postType;
    @JsonProperty("raw_message")
    private String rawMessage;
    @JsonProperty("self_id")
    private Integer selfId;
    @JsonProperty("sender")
    private SenderDTO sender;
    @JsonProperty("sub_type")
    private String subType;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("user_id")
    private Integer userId;

    @NoArgsConstructor
    @Data
    public static class SenderDTO {
        @JsonProperty("age")
        private Integer age;
        @JsonProperty("area")
        private String area;
        @JsonProperty("card")
        private String card;
        @JsonProperty("level")
        private String level;
        @JsonProperty("nickname")
        private String nickname;
        @JsonProperty("role")
        private String role;
        @JsonProperty("sex")
        private String sex;
        @JsonProperty("title")
        private String title;
        @JsonProperty("user_id")
        private Integer userId;
    }
}
