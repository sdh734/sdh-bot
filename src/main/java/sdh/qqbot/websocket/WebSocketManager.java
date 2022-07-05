package sdh.qqbot.websocket;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sdh.qqbot.config.ApiUrlConfig;
import sdh.qqbot.utils.OkHttpInstance;

/**
 * OkHttp WebSocket连接管理类，实现定时发送心跳，定时判断连接状态并重连
 */
@Slf4j
@Component
public class WebSocketManager {
    private final static int RECONNECT_MILLIS = 10000;//重连检查时间间隔，毫秒
    private final static int HEART_MILLIS = 5 * 1000;//心跳发送时间间隔，毫秒
    private static WebSocketManager webSocketManager;

    private final OkHttpClient okHttpClient = OkHttpInstance.getInstance();//OkHttpClient对象
    private final Request request;//OkHttp Request对象
    private WebSocket IWebSocket;//OkHttp WebSocket对象
    private IReceiveMessage receiveMessage;//接收消息处理模块

    private boolean isConnect = false;//连接标志
    private int connectCount = 0;//连接次数

    private static final String WS_URL = ApiUrlConfig.WS_URL;

    public WebSocketManager() {
        request = new Request.Builder().get().url(WS_URL).build();
    }

    /**
     * 单例方法 获取唯一的WebSocketManager，如果不存在就创建
     *
     * @return WebSocketManager
     */
    public synchronized static WebSocketManager getInstance() {
        if (webSocketManager == null) {
            webSocketManager = new WebSocketManager();
        }
        return webSocketManager;
    }

    /**
     * 释放单例, 及其所引用的资源
     */
    public static void release() {
        try {
            if (webSocketManager != null) {
                webSocketManager = null;
            }
        } catch (Exception e) {
            log.error("WebSocketManager，release : " + e.toString());
        }
    }

    /**
     * 初始化字段
     *
     * @param message 实现消息处理接口的类对象
     */
    public void init(IReceiveMessage message) {
        receiveMessage = message;
        connect();
    }

    /**
     * 连接方法
     */
    public void connect() {
        if (isConnect()) {
            log.info("WebSocket 已经连接！");
            return;
        }
        okHttpClient.newWebSocket(request, createListener());
    }

    /**
     * 创建WebSocket监听器。
     *
     * @return 创建的WebSocket监听器
     */
    private WebSocketListener createListener() {
        return new WebSocketListener() {
            @Override
            public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosed(webSocket, code, reason);
                IWebSocket = null;
                isConnect = false;
                if (receiveMessage != null) {
                    receiveMessage.onClose();
                }
            }

            @Override
            public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                super.onClosing(webSocket, code, reason);
                IWebSocket = null;
                isConnect = false;
                if (receiveMessage != null) {
                    receiveMessage.onClose();
                }
            }

            @Override
            public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                if (response != null) {
                    log.info("WS连接失败，：" + response.message());
                }
                log.info("WS连接失败异常原因：" + t.getMessage());
                isConnect = false;
                if (receiveMessage != null) {
                    receiveMessage.onConnectFailed();
                }
                reconnect();
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                super.onMessage(webSocket, text);
                if (receiveMessage != null) {
                    receiveMessage.onMessage(text);
                }
            }

            @Override
            public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
                if (receiveMessage != null) {
                    receiveMessage.onMessage(bytes.base64());
                }
            }

            @Override
            public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                super.onOpen(webSocket, response);
                log.info("WS打开连接：" + response);
                IWebSocket = webSocket;
                isConnect = response.code() == 101;
                if (!isConnect) {
                    reconnect();
                } else {
                    log.info("WS连接成功。");
                    if (receiveMessage != null) {
                        receiveMessage.onConnectSuccess();
                    }
                }
            }
        };
    }

    /**
     * 重新连接WS
     */
    public void reconnect() {
        log.info("开始重新连接WS。。。");
        if (IWebSocket != null) {
            close();
            IWebSocket = null;
        }
        connect();
        connectCount++;
    }

    /**
     * 判断WS是否连接
     */
    public boolean isConnect() {
        return IWebSocket != null && isConnect;
    }

    /**
     * 关闭连接
     */
    public void close() {
        if (isConnect()) {
            IWebSocket.cancel();
            IWebSocket.close(-1, "客户端主动关闭连接");
        }
    }

    /**
     * 定时发送心跳包
     */
    @Scheduled(fixedDelay = HEART_MILLIS, initialDelay = 10000)
    @Async
    void sendHeartBeat() {
//        log.info("开始发送心跳包。");
        boolean isSend = sendMessage("HeartBeat：" + System.currentTimeMillis());
        if (!isSend) {
            log.info("心跳包发送失败，请检查WS连接");
        }
    }

    /**
     * 使用SpringBoot定时任务，定时检查WS连接。
     */
    @Scheduled(fixedDelay = RECONNECT_MILLIS, initialDelay = 10000)
    @Async
    void inspectWebSocketConnect() {
//        log.info("开始检查WS连接。。。");
        if (!isConnect()) {
            log.info("WS连接出错，准备重连。");
            reconnect();
        }
    }

    /**
     * 发送消息
     *
     * @param text 消息字符串
     * @return 是否发送成功
     */
    public boolean sendMessage(String text) {
        if (!isConnect()) return false;
        return IWebSocket.send(text);
    }
}