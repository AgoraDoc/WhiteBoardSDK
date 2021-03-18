package com.herewhite.sdk.domain;

/**
<<<<<<< HEAD
 * `CameraBound` 类，用于设置用户的视野范围。
 *
 * @since 2.5.0
 */
public class CameraBound extends WhiteObject {

    /**
     * 获取用户视野范围的原点对应白板原点的横向偏移。
     *
     * @return 用户视野范围的原点对应白板原点的横向偏移。
=======
 * CameraBound 类。该类描述白板的基础视野范围，用于实现视角限制业务。
 * @since 2.5.0
 */
public class CameraBound extends WhiteObject {
    /**
     * 获取基础视野中心点在 x 轴上的位置。
     * @return 基础视野中心点在 x 轴上的位置。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public Double getCenterX() {
        return centerX;
    }

    /**
<<<<<<< HEAD
     * 设置用户视野范围的原点对应白板原点的横向偏移。
     *
     * @param centerX 用户视野范围的原点对应白板原点的横向偏移。默认值为 0，即与白板原点的横轴坐标相同。// TODO 取值范围？？
=======
     * 设置基础视野中心点在 x 轴上的位置。
     *
     * @param centerX 基础视野中心点在 x 轴上的位置，默认值为 0。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    // TODO 默认值表示什么意思。
    public void setCenterX(Double centerX) {
        this.centerX = centerX;
    }

    /**
<<<<<<< HEAD
     * 获取用户视野范围的原点对应白板原点的纵向偏移。
     *
     * @return 用户视野范围的原点对应白板原点的纵向偏移。
=======
     * 获取基础视野中心点在 y 轴上的位置。
     * @return 基础视野中心点在 y 轴上的位置。 
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public Double getCenterY() {
        return centerY;
    }

    /**
<<<<<<< HEAD
     * 设置用户视野范围的原点对应白板原点的纵向偏移。
     *
     * @param centerY 用户视野范围的原点对应白板原点的纵向偏移。默认值为 0，即与白板原点的纵轴左边相同。// TODO 取值范围？？
=======
     * 设置基础视野中心点在 y 轴上的位置。
     *
     * @param centerY 基础视野点在 y 轴上的位置，默认值为 0。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public void setCenterY(Double centerY) {
        this.centerY = centerY;
    }

    /**
     * 获取基础视野的宽度。
     * @return 基础视野的宽度。
     */
    public Double getWidth() {
        return width;
    }

    /**
<<<<<<< HEAD
     * 设置用户视野范围的宽度。
     *
     * 配合 {@link #setMinContentMode(ContentModeConfig)} {@link #setMinContentMode(ContentModeConfig)} 使用，
     * 用来描述，最大最小缩放比例。// TODO 怎么配合的？最大缩放比例不能超过设置的宽、高范围？
     *
     * @param width 用户视野范围的宽度。如果不设，表表示宽度无限制。
=======
     * 设置基础视野的宽度。
     *
     * 该方法可以配合 {@link #setMinContentMode(ContentModeConfig)} 和 {@link #setMaxContentMode(contentModeConfig)} 方法使用，用来设置视野的缩放比例。
     *
     * @param width 基础视野的宽度。如果不设置该参数，则表示无穷宽。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
<<<<<<< HEAD
     * 获取用户视野范围的宽度。
     *
     * @return 用户视野范围的宽度。
=======
     * 获取基础视野的高度。
     * @return 基础视野的高度。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public Double getHeight() {
        return height;
    }

    /**
<<<<<<< HEAD
     * 设置用户视野范围的高度。
     *
     * 配合 {@link #setMinContentMode(ContentModeConfig)} {@link #setMinContentMode(ContentModeConfig)} 使用，
     * 用来描述，最大最小缩放比例。 // TODO 怎么配合的？最大缩放比例不能超过设置的宽、高范围？
     *
     * @param height 用户视野范围的高度。如果不设，表表示宽度无限制。
=======
     * 设置基础视野的高度。
     *
     * 该方法可以配合 {@link #setMinContentMode(ContentModeConfig)} 和 {@link #setMaxContentMode(ContentModeConfig)} 方法使用，用来设置视野的缩放比例。
     *
     * @param height 基础视野的高度。如果不设置该参数，则表示无穷高。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public void setHeight(Double height) {
        this.height = height;
    }

<<<<<<< HEAD

    /**
     * 获取视图的最大缩放比例。
     *
     * @return 视图的最大缩放比例。
=======
    /**
     * 获取基础视野的最大缩放比例。
     * @return 基础视野的最大缩放比例。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public ContentModeConfig getMaxContentMode() {
        return maxContentMode;
    }

    /**
<<<<<<< HEAD
     * 设置视图的最大缩放比例。
     *
     * 不传则不会限制最大比例 // TODO 如果视野范围设置了宽高，这里不设置会怎样？
     *
     * // TODO Content 是指什么？白板内容？图像？视图？视觉矩阵？
     *
     * @param maxContentMode 视图的最大缩放比例，详见 {@link ContentModeConfig ContentModeConfig}。如果不设，则表示无限制。
=======
     * 设置基础视野的最大缩放比例。
     *
     * @param maxContentMode 基础视野的最大缩放比例，详见 {@link ContentModeConfig}。如果不设置该参数，则不限制基础视野的最大缩放比例。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public void setMaxContentMode(ContentModeConfig maxContentMode) {
        this.maxContentMode = maxContentMode;
    }

<<<<<<< HEAD

    /**
     * 获取视图的最小缩放比例。
     *
     * @return 视图的最小缩放比例。
=======
    /**
     * 获取基础视野的最小缩放比例。
     * @return 基础视野的最小缩放比例。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public ContentModeConfig getMinContentMode() {
        return minContentMode;
    }

    /**
<<<<<<< HEAD
     * 设置视图的最小缩放比例。
     *
     * 不传则不会限制最小比例 // TODO 如果视野范围设置了宽高，这里不设置会怎样？不设表示可以在视野范围内任意缩放？
     *
     * @param minContentMode 视图的最小缩放比例，详见 {@link ContentModeConfig ContentModeConfig}。如果不设，则表示无限制。
=======
     * 设置基础视野的最小缩放比例，不传则不会限制最小比例
     *
     * @param minContentMode 基础视野的最小缩放比例，详见 {@link ContentModeConfig}。如果不设置该参数，则不限制基础视野的最小缩放比例。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public void setMinContentMode(ContentModeConfig minContentMode) {
        this.minContentMode = minContentMode;
    }

    /**
<<<<<<< HEAD
     * 获取用户将视图移出视野范围边界时感受到的阻力。
     *
     * @return 用户将视图移出视野范围边界时感受到的阻力。
=======
     * 获取视野越出页面边界时手势的阻力参数。
     * @return 视野越出页面边界时手势的阻力参数。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public Double getDamping() {
        return damping;
    }

    /**
     * 设置用户将视图移出视野范围边界时感受到的阻力。
     *
<<<<<<< HEAD
     * 该方法仅在用户使用多指触碰方式移动视角时生效。// TODO 是这样吗？
     *
     * @param damping 阻力大小，取值范围为 [0.0,1.0]。取值越大，用户感受到的阻力越大。取值为 0.0 时，完全无阻力，用户可继续移动视图；取值为 1.0 时，则用户无法将视图移出边界。
=======
     * 设置视野越出页面边界时手势的阻力参数。
     *
     * @param damping 视野越出页面边界时手势的阻力参数，取值范围为 [0.0,1.0]。当使用多个指尖触碰改变视角时，视野有可能会越出页面边界。设置该值
     * 可以调整越出边界的阻力。取值越大，则用户感受到的阻力越大。
     * - 0.0: 完全感受不到阻力。
     * - 1.0: 视野无法越出页面边界。
>>>>>>> e9b27d3c944ffb0aa7826cedcefd85ce8aa69d3c
     */
    public void setDamping(Double damping) {
        this.damping = damping;
    }

    private Double damping;
    private Double centerX;
    private Double centerY;
    private Double width;
    private Double height;
    private ContentModeConfig maxContentMode;
    private ContentModeConfig minContentMode;

    public CameraBound() {
        super();
    }

    /**
     * 视野范围。// TODO 是用来初始化视野范围吗？
     * @param miniScale 最小缩放比例。
     * @param maxScale 最大缩放比例。
     */
    public CameraBound(Double miniScale, Double maxScale) {
        this();
        ContentModeConfig miniConfig = new ContentModeConfig();
        miniConfig.setScale(miniScale);
        this.minContentMode = miniConfig;

        ContentModeConfig maxConfig = new ContentModeConfig();
        maxConfig.setScale(maxScale);
        this.maxContentMode = maxConfig;

    }
}
