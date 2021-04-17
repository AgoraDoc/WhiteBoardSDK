package com.herewhite.sdk.domain;

import com.google.gson.annotations.SerializedName;

/**
 * The user's view mode.
 */
public enum ViewMode {
    /**
     * (Default) Freedom mode. 
     *
     * In this mode, the user can freely adjust their own view. Each user's view setting does not affect the view settings of other users.
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
     * - When one user’s view mode is set as `Broadcaster`, the view mode of the other users in the room (including users that join subsequently) automatically changes to `Follower`. 
     //TODO William I don't think you need quote marks around the view modes unless you're talking about the value?
     // TODO CT Words in quote marks will be rendered as code format in generated documents. I think `Freedom`, `Follower` and `Broadcaster` should be in code format, because they are the values of the view mode.
     * - When a user in `Follower` view mode operates the whiteboard, their view mode automatically switches to `Freedom` mode. 
     //TODO William Does it revert to Follower mode at some point? What triggers that switch?
     // TODO CT I think that will happen the next time when a user swithces to `Broadcaster` view mode. I will double check with the developer.
     * If needed, you can call {@link disableOperations(boolean) disableOperations} to disable the user from operating the whiteboard, so as to lock their view mode.
     */
    @SerializedName("follower")
    Follower,
    /**
     * Host mode. 
     //TODO William "Broadcaster mode"?
     // TODO CT I agree that for the consistency between the code and the mode desvription, "Broadcaster mode" is better. 
     However, we were askded to change all "broadcaster" to "host" for the `broadcaster` user role in RTC API documentation. (the code is still `broadcaster`) I think the change is 
     *
     * In this mode, the user can freely adjust their view and synchronize thier view to other users in the room. 
     * // TODO CT The user in `broadcaster` view mode can synchronize his or her view to other users, which means other users watch the whiteboars in the same view as the host.
     *
     * @note
     * - Each room can have only one user in `Broadcaster` view mode. 
     //TODO William Can another user subsequently become the "Broadcaster"? What would happen if they did? 
     // TODO CT I need to futher check with the developer.
     * - When a user’s view mode is set as `Broadcaster`, the view mode of the other users in the room (including users that join subsequently) automatically changes to `Follower`.
     */
    @SerializedName("broadcaster")
    Broadcaster
}
