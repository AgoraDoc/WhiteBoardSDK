package com.herewhite.sdk.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Animation mode for switching the view.
 *
 * @since 2.3.2
 */
public enum AnimationMode {
    /**
     * (Default) The view switches continuously.
     */
    @SerializedName("continuous")
    Continuous,
    /**
     * The view switches instantly.
     */
    @SerializedName("immediately")
    Immediately
}
