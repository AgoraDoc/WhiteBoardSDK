package com.herewhite.sdk.domain;

/**
 * CameraBound 类。该类描述白板的基础视野范围，用于实现视角限制业务。
 * @since 2.5.0
 */
public class CameraBound extends WhiteObject {
    /**
     * 获取基础视野中心点在 x 轴上的位置。
     * @return 基础视野中心点在 x 轴上的位置。
     */
    public Double getCenterX() {
        return centerX;
    }

    /**
     * 设置基础视野中心点在 x 轴上的位置。
     *
     * @param centerX 基础视野中心点在 x 轴上的位置，默认值为 0。
     */
    // TODO 默认值表示什么意思。
    public void setCenterX(Double centerX) {
        this.centerX = centerX;
    }

    /**
     * 获取基础视野中心点在 y 轴上的位置。
     * @return 基础视野中心点在 y 轴上的位置。 
     */
    public Double getCenterY() {
        return centerY;
    }

    /**
     * 设置基础视野中心点在 y 轴上的位置。
     *
     * @param centerY 基础视野点在 y 轴上的位置，默认值为 0。
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
     * 设置基础视野的宽度。
     *
     * 该方法可以配合 {@link #setMinContentMode(ContentModeConfig)} 和 {@link #setMaxContentMode(contentModeConfig)} 方法使用，用来设置视野的缩放比例。
     *
     * @param width 基础视野的宽度。如果不设置该参数，则表示无穷宽。
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * 获取基础视野的高度。
     * @return 基础视野的高度。
     */
    public Double getHeight() {
        return height;
    }

    /**
     * 设置基础视野的高度。
     *
     * 该方法可以配合 {@link #setMinContentMode(ContentModeConfig)} 和 {@link #setMaxContentMode(ContentModeConfig)} 方法使用，用来设置视野的缩放比例。
     *
     * @param height 基础视野的高度。如果不设置该参数，则表示无穷高。
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 获取基础视野的最大缩放比例。
     * @return 基础视野的最大缩放比例。
     */
    public ContentModeConfig getMaxContentMode() {
        return maxContentMode;
    }

    /**
     * 设置基础视野的最大缩放比例。
     *
     * @param maxContentMode 基础视野的最大缩放比例，详见 {@link ContentModeConfig}。如果不设置该参数，则不限制基础视野的最大缩放比例。
     */
    public void setMaxContentMode(ContentModeConfig maxContentMode) {
        this.maxContentMode = maxContentMode;
    }

    /**
     * 获取基础视野的最小缩放比例。
     * @return 基础视野的最小缩放比例。
     */
    public ContentModeConfig getMinContentMode() {
        return minContentMode;
    }

    /**
     * 设置基础视野的最小缩放比例，不传则不会限制最小比例
     *
     * @param minContentMode 基础视野的最小缩放比例，详见 {@link ContentModeConfig}。如果不设置该参数，则不限制基础视野的最小缩放比例。
     */
    public void setMinContentMode(ContentModeConfig minContentMode) {
        this.minContentMode = minContentMode;
    }

    /**
     * 获取视野越出页面边界时手势的阻力参数。
     * @return 视野越出页面边界时手势的阻力参数。
     */
    public Double getDamping() {
        return damping;
    }

    /**
     *
     * 设置视野越出页面边界时手势的阻力参数。
     *
     * @param damping 视野越出页面边界时手势的阻力参数，取值范围为 [0.0,1.0]。当使用多个指尖触碰改变视角时，视野有可能会越出页面边界。设置该值
     * 可以调整越出边界的阻力。取值越大，则用户感受到的阻力越大。
     * - 0.0: 完全感受不到阻力。
     * - 1.0: 视野无法越出页面边界。
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
     * 效果类似 sdkConfig 删除的 zoomMinScale， zoomMaxScale 效果
     * @param miniScale
     * @param maxScale
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
