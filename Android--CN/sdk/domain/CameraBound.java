package com.herewhite.sdk.domain;

/**
 * `CameraBound` 类，用于设置用户的视野范围。
 *
 * @since 2.5.0
 */
public class CameraBound extends WhiteObject {

    /**
     * 获取用户视野范围的原点对应白板原点的横向偏移。
     *
     * @return 用户视野范围的原点对应白板原点的横向偏移。
     */
    public Double getCenterX() {
        return centerX;
    }

    /**
     * 设置用户视野范围的原点对应白板原点的横向偏移。视野范围的
     * // TODO 视野范围的原点在世界坐标系上的横向坐标
     * 世界坐标系 = 白板内部坐标系 = 加入房间白板未移动的情况下以白板的中心为原点
     * 图片、文字和 PPT 是显示在白板上的，视野范围相当于一个窗口
     *
     * @param centerX 用户视野范围的原点对应白板原点的横向偏移。默认值为 0，即与白板原点的横轴坐标相同。// TODO 取值范围？？// 无限制
     */
    public void setCenterX(Double centerX) {
        this.centerX = centerX;
    }

    /**
     * 获取用户视野范围的原点对应白板原点的纵向偏移。
     *
     * @return 用户视野范围的原点对应白板原点的纵向偏移。
     */
    public Double getCenterY() {
        return centerY;
    }

    /**
     * 设置用户视野范围的原点对应白板原点的纵向偏移。
     *
     * @param centerY 用户视野范围的原点对应白板原点的纵向偏移。默认值为 0，即与白板原点的纵轴左边相同。// TODO 取值范围？？// 无限制
     */
    public void setCenterY(Double centerY) {
        this.centerY = centerY;
    }

    public Double getWidth() {
        return width;
    }

    /**
     * 设置用户视野范围的宽度。
     *
     * 配合 {@link #setMinContentMode(ContentModeConfig)} {@link #setMinContentMode(ContentModeConfig)} 使用，
     * 用来描述，最大最小缩放比例。// TODO 怎么配合的？最大缩放比例不能超过设置的宽、高范围？
     *
     * @param width 用户视野范围的宽度。如果不设，表表示宽度无限制。
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * 获取用户视野范围的宽度。
     *
     * @return 用户视野范围的宽度。
     */
    public Double getHeight() {
        return height;
    }

    /**
     * 设置用户视野范围的高度。
     *
     * 配合 {@link #setMinContentMode(ContentModeConfig)} {@link #setMinContentMode(ContentModeConfig)} 使用，
     * 用来描述，最大最小缩放比例。 // TODO 怎么配合的？最大缩放比例不能超过设置的宽、高范围？
     *
     * @param height 用户视野范围的高度。如果不设，表表示宽度无限制。
     */
    public void setHeight(Double height) {
        this.height = height;
    }


    /**
     * 获取视图的最大缩放比例。
     *
     * @return 视图的最大缩放比例。
     */
    public ContentModeConfig getMaxContentMode() {
        return maxContentMode;
    }

    /**
     * 设置视野范围的最大缩放比例。
     *
     * 不传则不会限制最大比例 // TODO 和 Web 开发确认一下
     *
     * @param maxContentMode 视图的最大缩放比例，详见 {@link ContentModeConfig ContentModeConfig}。如果不设，则表示无限制。
     */
    public void setMaxContentMode(ContentModeConfig maxContentMode) {
        this.maxContentMode = maxContentMode;
    }


    /**
     * 获取视图的最小缩放比例。
     *
     * @return 视图的最小缩放比例。
     */
    public ContentModeConfig getMinContentMode() {
        return minContentMode;
    }

    /**
     * 设置视图的最小缩放比例。
     *
     * 不传则不会限制最小比例 // TODO 不传可能会有 bug 默认最小 0.1 和 Web 开发确认一下
     *
     * @param minContentMode 视图的最小缩放比例，详见 {@link ContentModeConfig ContentModeConfig}。如果不设，则表示无限制。
     */
    public void setMinContentMode(ContentModeConfig minContentMode) {
        this.minContentMode = minContentMode;
    }

    /**
     * 获取用户将视图移出视野范围边界时感受到的阻力。
     *
     * @return 用户将视图移出视野范围边界时感受到的阻力。
     */
    public Double getDamping() {
        return damping;
    }

    /**
     * 设置视野范围边界时感受到的阻力。
     *
     * 该方法仅在用户使用多指触碰方式移动视角时生效。// TODO 移动端只有手指
     * 设置为 0 的时候，可以缩放，但是手松开的时候，会弹回去；设置为 1，完全不能超过边界。
     *
     * @param damping 阻力大小，取值范围为 [0.0,1.0]。取值越大，用户感受到的阻力越大。取值为 0.0 时，完全无阻力，用户可继续移动视图；取值为 1.0 时，则用户无法将视图移出边界。
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
     * 初始化视野范围。// 仅设置最大最小缩放比例，其他参数不让设，简便方法
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
