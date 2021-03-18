package com.herewhite.sdk.domain;


import android.widget.ImageView.ScaleType;

import com.google.gson.annotations.SerializedName;

/**
 * `ContentModeConfig` 类，设置本地用户视野范围的缩放比例。
 *
 * @since 2.5.0
 */
public class ContentModeConfig extends WhiteObject {

    public ContentModeConfig() {
        scale = 1d;
        space = 0d;
        mode = ScaleMode.CENTER;
    }

    /**
     * 缩放比例与缩放模式。
     */
    public enum ScaleMode {
        /**
         * （默认）基于白板 `zoomScale` 的缩放比例。// TODO 白板 `zoomScale` 在哪里设置的？Web 端写的 `WhiteSdkConfiguration`。
         */
        @SerializedName("Scale")
        CENTER,
        /**
         * 图像等比缩放，填满视野范围。
         * // TODO
         * 与 {@link android.widget.ImageView.ScaleType#CENTER_INSIDE} 相似，按比例缩放，将设置的宽高范围，铺满视野
         * Android CenterInside: 图片大小<=View大小&&图片大小<=原始图片大小；和上面的意思不太一样。
         */
        @SerializedName("AspectFit")
        CENTER_INSIDE,
        /**
         * 与 AspectFit 相似。处理时的宽高，为 基准宽高 * scale */
        @SerializedName("AspectFitScale")
        CENTER_INSIDE_SCALE,
        /** 与 AspectFit 相似。处理时的宽高，为 基准宽高 + space */
        @SerializedName("AspectFitSpace")
        CENTER_INSIDE_SPACE,
        /**
         * 与 {@link android.widget.ImageView.ScaleType#CENTER_CROP} 相似，按比例缩放，视野内容会在设置的宽高范围内
         */
        @SerializedName("AspectFill")
        CENTER_CROP,
        /**
         * 与 AspectFill 相似，处理时的宽高，为 基准宽高 + space
         *
         * // TODO 这个不是剪裁？如果不是，和上面的 CENTER_INSIDE_SPACE 有什么差别？
         */
        @SerializedName("AspectFillScale")
        CENTER_CROP_SPACE,
    }

    /**
     * 获取缩放比例。
     *
     * @return 缩放比例。
     */
    public Double getScale() {
        return scale;
    }

    /**
     * 设置缩放比例。
     *
     * @note
     * 该方法仅在以下缩放模式下生效：
     * - {@link ScaleMode#CENTER}
     * - {@link ScaleMode#CENTER_INSIDE_SCALE}
     * - {@link ScaleMode#CENTER_INSIDE_SCALE}
     *
     * @param scale 缩放比例，默认值为 1，即保存图像原始尺寸。
     */
    public void setScale(Double scale) {
        this.scale = scale;
    }

    /**
     * 获取图像相对于视野范围的剪裁或填充空间。
     *
     * @return 图像相对于视野范围的剪裁或填充空间，单位为像素。
     */
    public Double getSpace() {
        return space;
    }

    /**
     * 设置图像相对于视野范围的剪裁或填充空间。
     *
     * // TODO 相对于基准视野范围额外在两边多出来的空间？
     *
     * @note
     * 该方法仅在以下缩放模式下生效：
     * - {@link ScaleMode#CENTER_INSIDE_SPACE}
     * - {@link ScaleMode#CENTER_CROP_SPACE}
     *
     * @param space 图像相对于视野范围的剪裁或填充空间，单位为像素，默认值为 0。// TODO 取值范围？单位？
     */
    public void setSpace(Double space) {
        this.space = space;
    }

    /**
     * 获取设置的缩放模式。
     *
     * @return 缩放模式，详见 {@link ScaleMode ScaleMode}。
     */
    public ScaleMode getMode() {
        return mode;
    }

    /**
     * 设置缩放模式。
     *
     * @param mode 缩放模式，详见 {@link ScaleMode ScaleMode}。
     */
    public void setMode(ScaleMode mode) {
        this.mode = mode;
    }

    private Double scale;
    private Double space;
    private ScaleMode mode;
}
