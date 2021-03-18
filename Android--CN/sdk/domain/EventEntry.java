package com.herewhite.sdk.domain;

/**
 * EventEntry 类。该类包含 SDK 可以触发的自定义事件回调。
 */
public class EventEntry extends WhiteObject {
    private String eventName;
    private Object payload;
    private String scope;
    private long authorId;

    public String getScope() {
        return scope;
    }

    public long getAuthorId() {
        return authorId;
    }

    public String getEventName() {
        return eventName;
    }

    public Object getPayload() {
        return payload;
    }
}
