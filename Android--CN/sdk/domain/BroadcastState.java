package com.herewhite.sdk.domain;

/**
 * 视角状态，包含视角为主播模式的用户信息。
 */
public class BroadcastState extends WhiteObject {

    private ViewMode mode;
    private Long broadcasterId;
    private RoomMember broadcasterInformation;

    /**
     * 获取视角模式。// TODO 这个方法获取的是整个房间的视角模式，还是某个用户的视角模式？
     *
     * @return 视角模式。
     */
    public ViewMode getMode() {
        return mode;
    }

    /**
     * 获取主播模式用户在房间中的用户 ID。
     *
     * 2.4.6 前，当房间中没有主播时，错误的返回了 0。
     * 2.4.8 修复了该问题。// TODO 修复后，当房间内没有主播时，返回什么？
     *
     * @return 主播模式用户的用户 ID。
     */
    public Long getBroadcasterId() {
        return broadcasterId;
    }

    /**
     * 获取主播模式用户的用户信息。
     *
     * @return 用户信息。// TODO `RoomMember` 哪个是 `broadcasterInformation`？
     */
    public RoomMember getBroadcasterInformation() {
        return broadcasterInformation;
    }
}
