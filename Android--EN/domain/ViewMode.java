package com.herewhite.sdk.domain;

import com.google.gson.annotations.SerializedName;

/**
 * The user's view mode.
 */
public enum ViewMode {
    /**
     * (Default) Free mode.
     *
     * In this mode, the user can freely adjust their own view. Each user's view setting does not affect others.
     *
     * @note
     * When there is no host in the room, all users are in `Freedom` view mode by default.
     */
    @SerializedName("freedom")
    Freedom,
    /**
     * Follower mode.
     *
     * In this mode, the user's view follows the view of the host.
     *
     * @note
     * - When a user’s view mode is set as `Broadcaster`, the view mode of other users in the room (including newly-joined users) automatically changes to `Follower`.
     * - When a user in the `Follower` view mode operates the whiteboard, their view mode automatically switches to the `Freedom` mode.
     * If needed, you can call {@link disableOperations(boolean) disableOperations} to disable the user from operating the whiteboard, so as to lock their view mode.
     */
    @SerializedName("follower")
    Follower,
    /**
     * Host mode.
     *
     * In this mode, the user can freely adjust their view and synchronize their view to other users in the room.
     * 该模式下用户可以主动调整视角，并将自己的视角同步给房间内所有其他用户。
     *
     * @note
     * - Each room can have only one user in the `host` view mode.
     * - - When a user’s view mode is set as `Broadcaster`, the view mode of other users in the room (including newly-joined users) automatically changes to `Follower`.
     */
    @SerializedName("broadcaster")
    Broadcaster
}
