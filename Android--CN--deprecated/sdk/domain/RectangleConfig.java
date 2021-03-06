package com.herewhite.sdk.domain;

/**
 * `RectangleConfig` 类。该类配置白板的视觉矩形。
 * 通过视觉矩形，你可以设置视野范围内需要关注的内容。
 * 该方法可用于保证同样的内容在不同的设备上都可以显示完整。
 *
 * @since 2.2.0
 */
public class RectangleConfig extends WhiteObject {
    private Double originX;
    private Double originY;
    private Double width;
    private Double height;

    /**
     * `RectangleConfig` 构造函数。
     *
     * 在该函数中，你需要传入 `width`，`height` 和 `mode`。SDK 会根据你传入 `width` 和 `height` 计算视觉矩形左上角原点
     * 在世界坐标系中的位置 `originX` 和 `originY`, 即 `originX = - width / 2.0d`，`originY = - height / 2.0d`。
     *
     * 该方法适用于需要快速显示完整 PPT 内容的场景。
     *
     * @param width 白板视觉矩形的宽度。视觉矩形的宽度不能小于实际展示内容的宽度，否则用户将看不见超出的部分。
     * @param height 白板视觉矩形的高度。视觉矩形的高度不能小于实际展示内容的宽度，否则用户将看不见超出的部分。
     * @param mode 白板的动画模式，详见 {@link AnimationMode}。
     */
    public RectangleConfig(Double width, Double height, AnimationMode mode) {
        this.width = width;
        this.height = height;
        this.originX = - width / 2.0d;
        this.originY = - height / 2.0d;
        this.animationMode = mode;
    }

    /**
     * `RectangleConfig` 构造函数。
     *
     * 在该函数中，你需要传入 `width` 和 `height`。SDK 会根据你传入 `width` 和 `height` 计算视觉矩形左上角原点
     * 在世界坐标系中的位置 `originX` 和 `originY`, 即 `originX = - width / 2.0d`，`originY = - height / 2.0d`。
     *
     * 该方法不支持设置动画模式，SDK 会默认将动画模式设置为 `Continuous`。
     *
     * 该方法适用于需要快速显示完整 PPT 内容的场景。
     *
     * @param width  视觉矩形宽度。视觉矩形的宽度不能小于实际展示内容的宽度，否则用户将看不见超出的部分。
     * @param height 视觉矩形高度。视觉矩形的高度不能小于实际展示内容的宽度，否则用户将看不见超出的部分。
     */
    public RectangleConfig(Double width, Double height) {
        this(width, height, AnimationMode.Continuous);
    }

    /**
     * `RectangleConfig` 构造函数。
     *
     * 在该函数中，你需要传入 `originX`、`originY`、`width` 和 `height`。SDK 会根据你传入的 `originX`、`originY`、`width` 和 `height` 确定视觉矩形在世界坐标系（即白板内部坐标系）中的位置和大小。
     *
     * 该方法不支持设置动画模式，而是使用默认的连续动画 `Continuous` 模式。
     *
     * @param originX  视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 X 轴坐标。
     * @param originY 视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 Y 轴坐标。
     * @param width 视觉矩形的宽度。视觉矩形的宽度不能小于实际展示内容的宽度，否则用户将看不见超出的部分。
     * @param height 视觉矩形的高度。视觉矩形的高度不能小于实际展示内容的宽度，否则用户将看不见超出的部分。
     */
    // 注意，originX，originY 为白板内部坐标系坐标。白板内部坐标系 这句话是什么意思。
    public RectangleConfig(Double originX, Double originY, Double width, Double height) {
        this(originX, originY, width, height, AnimationMode.Continuous);
    }

    /**
     * `RectangleConfig` 构造函数。
     *
     * 在该函数中，你需要传入 `originX`、`originY`、`width`、`height` 和 `mode`。
     * SDK 会根据你传入的 `originX`、`originY`、`width`、`height` 和 `mode` 确定视觉矩形在世界坐标系（即白板内部坐标系）中的位置、大小和动画模式。
     *
     * @param originX  视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 X 轴坐标。
     * @param originY 视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 Y 轴坐标。
     * @param width 视觉矩形的宽度。视觉矩形的宽度不能小于实际展示内容的宽度，否则用户将看不见超出的部分。
     * @param height 视觉矩形的高度。视觉矩形的高度不能小于实际展示内容的宽度，否则用户将看不见超出的部分。
     * @param mode 视觉矩形的动画模式，详见 {@link AnimationMode}。
     */
    public RectangleConfig(Double originX, Double originY, Double width, Double height, AnimationMode mode) {
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        this.animationMode = mode;
    }

    /**
     * 获取视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 X 轴坐标。
     * @return 视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 X 轴坐标。
     */
    public Double getOriginX() {
        return originX;
    }

    /**
     * 设置视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 X 轴坐标。
     *
     * @param originX 视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 X 轴坐标。
     */
    public void setOriginX(Double originX) {
        this.originX = originX;
    }

    /**
     * 获取视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 Y 轴坐标。
     *
     * @return 视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 Y 轴坐标。
     */
    public Double getOriginY() {
        return originY;
    }

    /**
     * 设置视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 Y 轴坐标。
     *
     * @param originY 视觉矩形左上角原点在世界坐标系（即白板内部坐标系）中的 Y 轴坐标。
     */
    public void setOriginY(Double originY) {
        this.originY = originY;
    }

    /**
     * 获取视觉矩形的宽度。
     *
     * @return 视觉矩形的宽度。
     */
    public Double getWidth() {
        return width;
    }

    /**
     * 设置视觉矩形的宽度。
     *
     * @param width 视觉矩形的宽度。
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * 获取视觉矩形的高度。
     *
     * @return 视觉矩形的高度。
     */
    public Double getHeight() {
        return height;
    }

    /**
     * 设置视觉矩形的高度。
     *
     * @param height 视觉矩形的高度。
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 获取视觉矩形的动画模式。
     *
     * @return 视觉矩形的动画模式，详见 {@link AnimationMode}。
     */
    public AnimationMode getAnimationMode() {
        return animationMode;
    }

    /**
     * 设置视觉矩形的动画模式。
     *
     * @param animationMode 视觉矩形的动画模式，详见 {@link AnimationMode}。
     */
    public void setAnimationMode(AnimationMode animationMode) {
        this.animationMode = animationMode;
    }

    private AnimationMode animationMode;
}
