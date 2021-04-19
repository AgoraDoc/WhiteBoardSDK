package com.herewhite.sdk.domain;

/**
 * The mode for watching the whiteboard playback.
 */
public enum PlayerObserverMode {

    /**
     * (Default) Follower mode.
     *
     * In this mode, the user watches the playback from one of the following views:
     * - The view of the host, if there is a host in the live Interactive Whiteboard room when the recording is taken.
     * - The view of the first user who joins the room in interactive mode, If there is not a host in the live Interactive Whiteboard room when the recording is taken.
     * - The initial view of the whiteboard, If there is neither a host nor users in interactive mode in the live Interactive Whiteboard room when the recording is taken.
     *
     * @note
     * If the user in `directory` mode adjusts their view through touch screen gestures, the SDK automatically switches their mode to `freedom`.
     */
    directory,

    /**
     * Freedom mode.
     *
     * In this mode, the user can freely adjust their view when watching the playback.
     */
    freedom
}
