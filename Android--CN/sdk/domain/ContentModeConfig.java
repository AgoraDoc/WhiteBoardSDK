package com.herewhite.sdk.domain;


import android.widget.ImageView.ScaleType;

import com.google.gson.annotations.SerializedName;

/**
<<<<<<< HEAD
 * `ContentModeConfig` 类，设置本地用户视野范围的缩放比例。
 *
=======
 * ContentModeConfig 类，该类描述视野缩放比例。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
 * @since 2.5.0
 */
public class ContentModeConfig extends WhiteObject {

    public ContentModeConfig() {
        scale = 1d;
        space = 0d;
        mode = ScaleMode.CENTER;
    }

    /**
<<<<<<< HEAD
     * 缩放比例与缩放模式。
     */
    public enum ScaleMode {
        /**
         * （默认）基于白板 `zoomScale` 的缩放比例。// TODO 白板 `zoomScale` 在哪里设置的？Web 端写的 `WhiteSdkConfiguration`。
=======
     * ScaleMode 枚举类。该类描述白板视野的缩放模式。
     */
    public enum ScaleMode {
        /** 
         * 基于白板 zoomScale 的缩放比例，默认处理。 
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
         */
        @SerializedName("Scale")
        CENTER,
        /**
<<<<<<< HEAD
         * 图像等比缩放，填满视野范围。
         * // TODO
         * 与 {@link android.widget.ImageView.ScaleType#CENTER_INSIDE} 相似，按比例缩放，将设置的宽高范围，铺满视野
         * Android CenterInside: 图片大小<=View大小&&图片大小<=原始图片大小；和上面的意思不太一样。
=======
         * CENTER 模式，表示按比例缩放视野，并将设置的宽高范围铺满视野。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
         */
        @SerializedName("AspectFit")
        CENTER_INSIDE,
        /**
<<<<<<< HEAD
         * 与 AspectFit 相似。处理时的宽高，为 基准宽高 * scale */
=======
         * CENTER_INSIDE_SCALE 模式，表示与 AspectFit 相似。处理时的宽高，为 基准宽高 * scale。
         */
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
        @SerializedName("AspectFitScale")
        CENTER_INSIDE_SCALE,
        /**
         * CENTER_INSIDE_SPACE 模式，表示与 AspectFit 相似。处理时的宽高，为 基准宽高 + space。
         */
        @SerializedName("AspectFitSpace")
        CENTER_INSIDE_SPACE,
        /**
<<<<<<< HEAD
         * 与 {@link android.widget.ImageView.ScaleType#CENTER_CROP} 相似，按比例缩放，视野内容会在设置的宽高范围内
=======
         * CENTER_CROP 模式，与 {@link android.widget.ImageView.ScaleType#CENTER_CROP} 相似，按比例缩放，视野内容会在设置的宽高范围内。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
         */
        @SerializedName("AspectFill")
        CENTER_CROP,
        /**
<<<<<<< HEAD
         * 与 AspectFill 相似，处理时的宽高，为 基准宽高 + space
         *
         * // TODO 这个不是剪裁？如果不是，和上面的 CENTER_INSIDE_SPACE 有什么差别？
=======
         * CENTER_CROP_SPACE 模式，与 AspectFill 相似，处理时的宽高，为 基准宽高 + space。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
         */
        @SerializedName("AspectFillScale")
        CENTER_CROP_SPACE,
    }

    /**
<<<<<<< HEAD
     * 获取缩放比例。
     *
     * @return 缩放比例。
=======
     * 获取当前视野的缩放比例。
     * @return 当前视野的缩放比例。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public Double getScale() {
        return scale;
    }

    /**
<<<<<<< HEAD
     * 设置缩放比例。
     *
     * @note
     * 该方法仅在以下缩放模式下生效：
     * - {@link ScaleMode#CENTER}
     * - {@link ScaleMode#CENTER_INSIDE_SCALE}
     * - {@link ScaleMode#CENTER_INSIDE_SCALE}
     *
     * @param scale 缩放比例，默认值为 1，即保存图像原始尺寸。
=======
     * 设置视野的缩放比例。
     *
     * 当缩放模式为 `CENTER` 或 `CENTER_INSIDE_SCALE` 时，该方法有效。你可以通过 {@link #getMode()} 方法获取当前的缩放模式。
     *
     * @param scale 视野的缩放比例。默认值为 1。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    // 取值范围
    public void setScale(Double scale) {
        this.scale = scale;
    }

    /**
<<<<<<< HEAD
     * 获取图像相对于视野范围的剪裁或填充空间。
     *
     * @return 图像相对于视野范围的剪裁或填充空间，单位为像素。
=======
     * 获取基准视野范围以外，页面两边多出来的空间。
     * @return 视野范围以外，页面两边多出来的空间。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public Double getSpace() {
        return space;
    }

    /**
<<<<<<< HEAD
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
=======
     * 设置基准视野范围以外页面两边多出来的空间。
     *
     * 当缩放模式为 `CENTER_CROP_SPACE` 或 `CENTER_CROP_SPACE` 时，该方法有效。你可以通过 {@link #getMode()} 方法获取当前的缩放模式。
     *
     * @param space 视野范围以外，页面两边多出来的空间，默认值为 0。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    // TODO 单位和取值范围？
    public void setSpace(Double space) {
        this.space = space;
    }

    /**
<<<<<<< HEAD
     * 获取设置的缩放模式。
     *
     * @return 缩放模式，详见 {@link ScaleMode ScaleMode}。
=======
     * 获取缩放模式。
     * @return 当前视野的缩放模式。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public ScaleMode getMode() {
        return mode;
    }

    /**
     * 设置缩放模式。
     *
<<<<<<< HEAD
     * @param mode 缩放模式，详见 {@link ScaleMode ScaleMode}。
=======
     * @param mode 白板视野的缩放模式，默认值为 `CENTER`。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public void setMode(ScaleMode mode) {
        this.mode = mode;
    }

    private Double scale;
    private Double space;
    private ScaleMode mode;
}
