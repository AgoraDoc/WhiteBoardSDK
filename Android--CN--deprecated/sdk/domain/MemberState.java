package com.herewhite.sdk.domain;

/**
 * Created by buhe on 2018/8/11.
 */
/**
 * `MemberState` 类，用于设置互动白板实时房间的教具状态。// TODO WL 要不要说明一下成员函数可以多次调用？
 */
public class MemberState extends WhiteObject {
    private String currentApplianceName;
    private int[] strokeColor;
    private Double strokeWidth;
    private Double textSize;

    public MemberState() {
    }

    /**
     * 获取互动白板实时房间内当前使用的教具名称。
     *
     * @return 互动白板实时房间内当前使用的教具名称。
     */
    public String getCurrentApplianceName() {
        return currentApplianceName;
    }

    /**
     * 设置互动白板实时房间内使用的教具。
     *
     * @param 教具名称，详见 {@link Appliance Appliance}。
     */
    public void setCurrentApplianceName(String currentApplianceName) {
        this.currentApplianceName = currentApplianceName;
    }

    /**
     * 获取用户设置的线条颜色。
     *
     * @return 线条颜色，为 RGB 格式，例如，[0, 0, 255] 表示蓝色。
     */
    public int[] getStrokeColor() {
        return strokeColor;
    }

    /**
     * 设置线条颜色。
     *
     * @param 线条颜色，为 RGB 格式，例如，[0, 0, 255] 表示蓝色。
     */
    public void setStrokeColor(int[] strokeColor) {
        this.strokeColor = strokeColor;
    }


    /**
     * 获取用户设置的线条粗细。
     *
     * @return 线条粗细。
     */
    public double getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * 设置线条粗细。
     *
     * @param strokeWidth 线条粗细。
     */
    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }


    /**
     * 获取用户设置的字体大小。
     *
     * @param textSize 字体大小。
     */
    public double getTextSize() {
        return textSize;
    }

    /**
     * 设置字体大小。
     *
     * @param textSize 字体大小。Chrome 浏览器对于小于 12 的字体会自动调整为 12。
     */
    public void setTextSize(double textSize) {
        this.textSize = textSize;
    }
}
