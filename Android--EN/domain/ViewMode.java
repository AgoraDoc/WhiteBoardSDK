package com.herewhite.sdk.domain;

import com.google.gson.annotations.SerializedName;

/**
 * The user's view mode.
 */
public enum ViewMode {
    /**
     * (Default) Free mode. //TODO William As with "Host" mode below, should you use the official term ("Freedom") here?
     *
     * In this mode, the user can freely adjust their own view. Each user's view setting does not affect the view settings of other users.
     *
     * @note
     * When there is no host in the room, all users are in Freedom view mode by default.
     */
    @SerializedName("freedom")
    Freedom,
    /**
     * Follower mode.
     *
     * In this mode, the user's view follows the view of the host.
     *
     * @note
     * - When one user’s view mode is set as `Broadcaster`, the view mode of the other users in the room (including users that join subsequently) automatically changes to Follower. //TODO William I don't think you need quote marks around the view modes unless you're talking about the value?
     * - When a user in Follower view mode operates the whiteboard, their view mode automatically switches to Freedom mode. //TODO William Does it revert to Follower mode at some point? What triggers that switch?
     * If needed, you can call {@link disableOperations(boolean) disableOperations} to disable the user from operating the whiteboard, so as to lock their view mode.
     */
    @SerializedName("follower")
    Follower,
    /**
     * Host mode. //TODO William "Broadcaster mode"?
     *
     * In this mode, the user can freely adjust their view, and the views of the other users in the room will be synchonized to theirs. 
     * 该模式下用户可以主动调整视角，并将自己的视角同步给房间内所有其他用户。  //TODO William Delete this (translation of previous line), or are you keeping it for a reason?
     *
     * @note
     * - Each room can have only one user in Broadcaster view mode. //TODO William Can another user subsequently become the "Broadcaster"? What would happen if they did? 
     * - When a user’s view mode is set as `Broadcaster`, the view mode of the other users in the room (including users that join subsequently) automatically changes to Follower.
     */
    @SerializedName("broadcaster")
    Broadcaster
}
