package com.herewhite.sdk.domain;

/**
 * `FrequencyEventListener` 接口类。该类包含你想要注册监听的高频事件回调。 // TODO WL 哪些是高频事件，如何区分？
 */
public interface FrequencyEventListener {
    /**
     * 收到监听的事件回调，详见 {@link EventEntry}。
     */
    void onEvent(EventEntry[] events);
}
