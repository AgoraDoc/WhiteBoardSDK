package com.herewhite.sdk.domain;

import com.google.gson.annotations.SerializedName;

/**
 * The phase of the whiteboard playback.
 */
public enum PlayerPhase {
    /**
     * The SDK is waiting for the first frame of the playback, which is the initial phase.
     */
    waitingFirstFrame,
    /**
     * The playback is playing.
     */
    playing,
    /**
     * The playback pauses.
     */
    pause,
    /**
     * The playback stops.
     */
    @SerializedName("stop")
    stopped,
    /**
     * The playback finishes.
     */
    ended,
    /**
     * The playback is buffering.
     */
    buffering,
}
