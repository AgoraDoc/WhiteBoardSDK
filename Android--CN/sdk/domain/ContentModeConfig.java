package com.herewhite.sdk.domain;


import android.widget.ImageView.ScaleType;

import com.google.gson.annotations.SerializedName;

/**
 * ContentModeConfig 类，该类描述视野缩放比例。
 * @since 2.5.0
 */
public class ContentModeConfig extends WhiteObject {

    public ContentModeConfig() {
        scale = 1d;
        space = 0d;
        mode = ScaleMode.CENTER;
    }

    /**
     * ScaleMode 枚举类。该类描述白板视野的缩放模式。
     */
    public enum ScaleMode {
        /** 
         * 基于白板 zoomScale 的缩放比例，默认处理。 
         */
        @SerializedName("Scale")
        CENTER,
        /**
         * CENTER 模式，表示按比例缩放视野，并将设置的宽高范围铺满视野。
         */
        @SerializedName("AspectFit")
        CENTER_INSIDE,
        /**
         * CENTER_INSIDE_SCALE 模式，表示与 AspectFit 相似。处理时的宽高，为 基准宽高 * scale。
         */
        @SerializedName("AspectFitScale")
        CENTER_INSIDE_SCALE,
        /**
         * CENTER_INSIDE_SPACE 模式，表示与 AspectFit 相似。处理时的宽高，为 基准宽高 + space。
         */
        @SerializedName("AspectFitSpace")
        CENTER_INSIDE_SPACE,
        /**
         * CENTER_CROP 模式，与 {@link android.widget.ImageView.ScaleType#CENTER_CROP} 相似，按比例缩放，视野内容会在设置的宽高范围内。
         */
        @SerializedName("AspectFill")
        CENTER_CROP,
        /**
         * CENTER_CROP_SPACE 模式，与 AspectFill 相似，处理时的宽高，为 基准宽高 + space。
         */
        @SerializedName("AspectFillScale")
        CENTER_CROP_SPACE,
    }

    /**
     * 获取当前视野的缩放比例。
     * @return 当前视野的缩放比例。
     */
    public Double getScale() {
        return scale;
    }

    /**
     * 设置视野的缩放比例。
     *
     * 当缩放模式为 `CENTER` 或 `CENTER_INSIDE_SCALE` 时，该方法有效。你可以通过 {@link #getMode()} 方法获取当前的缩放模式。
     *
     * @param scale 视野的缩放比例。默认值为 1。
     */
    // 取值范围
    public void setScale(Double scale) {
        this.scale = scale;
    }

    /**
     * 获取基准视野范围以外，页面两边多出来的空间。
     * @return 视野范围以外，页面两边多出来的空间。
     */
    public Double getSpace() {
        return space;
    }

    /**
     * 设置基准视野范围以外页面两边多出来的空间。
     *
     * 当缩放模式为 `CENTER_CROP_SPACE` 或 `CENTER_CROP_SPACE` 时，该方法有效。你可以通过 {@link #getMode()} 方法获取当前的缩放模式。
     *
     * @param space 视野范围以外，页面两边多出来的空间，默认值为 0。
     */
    // TODO 单位和取值范围？
    public void setSpace(Double space) {
        this.space = space;
    }

    /**
     * 获取缩放模式。
     * @return 当前视野的缩放模式。
     */
    public ScaleMode getMode() {
        return mode;
    }

    /**
     * 设置缩放模式。
     *
     * @param mode 白板视野的缩放模式，默认值为 `CENTER`。
     */
    public void setMode(ScaleMode mode) {
        this.mode = mode;
    }

    private Double scale;
    private Double space;
    private ScaleMode mode;
}
