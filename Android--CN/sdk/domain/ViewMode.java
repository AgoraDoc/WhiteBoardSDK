package com.herewhite.sdk.domain;

import com.google.gson.annotations.SerializedName;

/**
 * 视角模式。
 */
public enum ViewMode {
    /**
     * Freedom 自由模式。该模式下用户可以主动调整视角，不会被房间内其他角色影响，也不会影响其他角色。
     */
    @SerializedName("freedom")
    Freedom,
    /**
     * Follower 跟随模式。该模式下用户的视角会跟随主播的视角。
     */
    @SerializedName("follower")
    Follower,
    /**
     * Broadcaster 主播模式。该模式下用户可以主动调整视角，并将自己的视角信息同步到整个房间。
     */
    @SerializedName("broadcaster")
    Broadcaster
}
