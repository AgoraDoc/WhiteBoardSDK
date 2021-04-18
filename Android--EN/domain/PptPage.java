package com.herewhite.sdk.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by buhe on 2018/8/15.
 */

/**
 * The `PptPage` 类，用于在初始化场景时配置场景的背景图。
 */
public class PptPage extends WhiteObject {

    @SerializedName(value = "src", alternate = {"conversionFileUrl"})
    private String src;
    private Double width;
    private Double height;

    /**
     * The `PptPage` constructor, for initializing a `PptPage` object.
     * <p>
     * 该方法只能在场景初始化的时候调用。
     * This method can only be called when the scene is initialized.
     * <p>
     * 场景背景图的中心点默认为世界坐标系的原点，背景图无法移动，即无法改变背景图在白板内部的位置。
     *
     * @param src    The URL address of the image.
     * @param width  The width (px) of the image.
     * @param height The height (px) of the image.
     */
    public PptPage(String src, Double width, Double height) {
        this.src = src;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets the URL address of the image.
     *
     * @return The URL address of the image.
     */
    public String getSrc() {
        return src;
    }

    /**
     * Sets the URL address of the image.
     *
     * @param src The URL address of the image.
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * Gets the width of the image.
     *
     * @return The width (px) of the image.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the image.
     *
     * @param width The width (px) of the image.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Gets the height of the image.
     *
     * @return The height (px) of the image.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the image.
     *
     * @return The height (px) of the image.
     */
    public void setHeight(double height) {
        this.height = height;
    }
}
