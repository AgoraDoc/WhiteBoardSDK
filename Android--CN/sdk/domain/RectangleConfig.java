package com.herewhite.sdk.domain;

/**
 * RectangleConfig 类。该类配置白板的视觉矩形。通过视觉矩形，你可以设置视野范围内需要关注的内容。
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
     * RectangleConfig 构造函数。该函数通过宽、高和动画模式定义视觉矩形。
     *
     * SDK 将白板初始化时的中点固定视为白板的视角中心，然后根据传入的宽高，计算白板视觉矩形相对于场景左上角的 x 和 y 坐标。
     * 该方法适用于需要快速显示完整 PPT 内容的场景。
     *
     * @param width 白板视觉矩形的宽度。该参数的值为实际展示内容的最小宽度。
     * @param height 白板视觉矩形的高度。该参数的值为实际展示内容的最小高度。
     * @param mode 白板的动画模式，详见 {@link AnimationMode}。
     */
    // TODO width 的参数解释感觉不太好懂，需要优化。
    public RectangleConfig(Double width, Double height, AnimationMode mode) {
        this.width = width;
        this.height = height;
        this.originX = - width / 2.0d;
        this.originY = - height / 2.0d;
        this.animationMode = mode;
    }

    /**
     * RectangleConfig 构造函数。该函数通过宽和高提供视觉矩形的构建方法。
     *
     * SDK 将白板初始化时的中点固定视为白板的视角中心，然后根据传入的宽高，计算白板视觉矩形相对于场景左上角的 x 和 y 坐标。
     * 该方法不支持设置动画模式，而是使用默认的连续动画 `Continuous` 模式。适用于需要快速显示完整 PPT 内容的场景。
     *
     * @param width  视觉矩形宽度（实际展示内容的最小宽度）
     * @param height 视觉矩形高度（实际展示内容的最小高度）
     */
    // TODO 2个 RectangleConfig 构造都有适用于快速显示完成 ppt 的描述，是不是手抖了。
    public RectangleConfig(Double width, Double height) {
        this(width, height, AnimationMode.Continuous);
    }

    /**
     * RectangleConfig 构造函数。该函数通过相对于场景左上角的 x 坐标、y 坐标，以及宽和高提供视觉矩形的构建方法。
     *
     * 该方法不支持设置动画模式，而是使用默认的连续动画 `Continuous` 模式。
     * @param originX  视觉矩形相对于场景左上角的 x 坐标。
     * @param originY 视觉矩形相对于场景左上角的 y 坐标。
     * @param width 视觉矩形的宽度。
     * @param height 视觉矩形的高度。
     */
    // 注意，originX，originY 为白板内部坐标系坐标。白板内部坐标系 这句话是什么意思。
    public RectangleConfig(Double originX, Double originY, Double width, Double height) {
        this(originX, originY, width, height, AnimationMode.Continuous);
    }

    /**
     * RectangleConfig 构造函数。该函数通过相对于场景左上角的 x 坐标、y 坐标、宽、高以及动画模式提供视觉矩形的构造方法。
     *
     * @param originX  视觉矩形相对于场景左上角的 x 坐标。
     * @param originY 视觉矩形相对于场景左上角的 y 坐标。
     * @param width 视觉矩形的宽度。
     * @param height 视觉矩形的高度。
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
     * 获取视觉矩形相对于场景左上角的 x 坐标。
     * @return 视觉矩形相对于场景左上角的 x 坐标。
     */
    public Double getOriginX() {
        return originX;
    }

    /**
     * 设置视觉矩形相对于场景左上角的 x 坐标。
     */
    public void setOriginX(Double originX) {
        this.originX = originX;
    }

    /**
     * 获取视觉矩形相对于场景左上角的 y 坐标。
     * @return 视觉矩形相对于场景左上角的 y 坐标。
     */
    public Double getOriginY() {
        return originY;
    }

    /**
     * 设置视觉矩形相对于场景左上角的 y 坐标。
     */
    public void setOriginY(Double originY) {
        this.originY = originY;
    }

    /**
     * 获取视觉矩形的宽度。
     * @return 视觉矩形的宽度。
     */
    public Double getWidth() {
        return width;
    }

    /**
     * 设置视觉决定的宽度。
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * 获取视觉矩形的高度。
     * @return 视觉矩形的高度。
     */
    public Double getHeight() {
        return height;
    }

    /**
     * 设置视觉矩形的高度。
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 获取视觉矩形的动画模式。
     * @return 视觉矩形的动画模式，详见 {@link AnimationMode}。
     */
    public AnimationMode getAnimationMode() {
        return animationMode;
    }

    /**
     * 设置视觉矩形的动画模式。
     */
    public void setAnimationMode(AnimationMode animationMode) {
        this.animationMode = animationMode;
    }

    private AnimationMode animationMode;
}
