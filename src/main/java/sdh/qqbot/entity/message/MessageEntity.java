package sdh.qqbot.entity.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息实体类 包括私聊及群聊消息
 * @author SDH
 */
@NoArgsConstructor
@Data
public class MessageEntity {
    @JsonProperty("anonymous")
    private Object anonymous;
    @JsonProperty("font")
    private Integer font;
    //群聊ID
    @JsonProperty("group_id")
    private String groupId;
    //消息内容
    @JsonProperty("message")
    private String message;
    //消息ID
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("message_seq")
    private String messageSeq;
    /**
     * 消息类型
     * private 私聊
     * group 群聊
     */
    @JsonProperty("message_type")
    private String messageType;
    /**
     * cqHttp上报消息类型
     * message 消息
     * event 事件
     */
    @JsonProperty("post_type")
    private String postType;
    /**
     * 消息内容文本化
     */
    @JsonProperty("raw_message")
    private String rawMessage;
    @JsonProperty("self_id")
    private String selfId;
    /**
     * 消息发送人信息对象
     */
    @JsonProperty("sender")
    private SenderDTO sender;
    @JsonProperty("sub_type")
    private String subType;
    @JsonProperty("target_id")
    private String targetId;
    @JsonProperty("time")
    private Integer time;
    /**
     * 消息发送人ID
     */
    @JsonProperty("user_id")
    private String userId;

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
        private String userId;
    }
}
