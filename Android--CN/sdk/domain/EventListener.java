package com.herewhite.sdk.domain;

/**
 * EventListener 接口类。该类包含你想要注册监听的事件回调。
 */
public interface EventListener {
    /**
     * 收到监听的事件回调。
     * @param eventEntry 具体的监听事件，详见 {@link EvrentEntry}。
     */
    void onEvent(EventEntry eventEntry);
}
