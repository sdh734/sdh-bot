package sdh.qqbot.entity.message;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * WS发送消息实体类。
 *
 * @author SDh
 */
@NoArgsConstructor
@Data
public class SendMessageEntity {
    /**
     * 指定要调用的 API
     */
    @JsonProperty("action")
    private String action;
    /**
     * Go-CQ参数Map
     */
    @JsonProperty("params")
    private Map<String, String> params;
    /**
     * 自定义的返回值，可用于后期撤回功能开发
     */
    @JsonProperty("echo")
    private String echo;

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("action", action);
        if (params != null) {
            json.put("params", paramsToJSON());
        }
        if (echo != null) {
            json.put("echo", echo);
        }
        return json.toString();
    }

    private JSONObject paramsToJSON() {
        Set<String> keys = params.keySet();
        JSONObject json = new JSONObject();
        for (String s : keys) {
            json.put(s, params.get(s));
        }
        return json;
    }
}
