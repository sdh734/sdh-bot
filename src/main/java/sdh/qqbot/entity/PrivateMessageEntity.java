package sdh.qqbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 私聊消息实体类 已弃用
 */
@lombok.NoArgsConstructor
@lombok.Data
public class PrivateMessageEntity {

    @JsonProperty("font")
    private Integer font;
    @JsonProperty("message")
    private String message;
    @JsonProperty("message_id")
    private Integer messageId;
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
    @JsonProperty("target_id")
    private Integer targetId;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("user_id")
    private Integer userId;

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class SenderDTO {
        @JsonProperty("age")
        private Integer age;
        @JsonProperty("nickname")
        private String nickname;
        @JsonProperty("sex")
        private String sex;
        @JsonProperty("user_id")
        private Integer userId;
    }
}
