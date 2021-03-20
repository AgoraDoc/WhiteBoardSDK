package com.herewhite.sdk.domain;

/**
 * Created by buhe on 2018/8/15.
 */
/**
 * 图片信息。
 */
public class ImageInformation extends WhiteObject {

    private String uuid;
    private Double centerX;
    private Double centerY;
    private Double width;
    private Double height;xian

    /**
     * 获取是否锁定图片。
     *
     * @return 是否锁定图片：
     * - `true`：锁定。
     * - `false`：不锁定。
     *
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * 设置锁定图片。
     *
     * @param locked 是否锁定图片：
     * - `true`：锁定。
     * - `false`：不锁定。// TODO 默认值是？
     *
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    private Boolean locked = false;

    /**
     * 获取图片的 UUID。
     *
     * @return 图片的 UUID，即图片在互动白板实时房间中的唯一标识符。
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置图片的 UUID。
     *
     * 图片的 UUID 是一个字符串，为图片在互动白板实时房间中的标识符。在同一个互动白板实时房间中，每张图片的 UUID 必须是唯一的。
     * 你可以使用 UUID 生成库来生成图片的 UUID。
     *
     * @param uuid 图片的 UUID，字符串格式。
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取图片的中心在世界坐标系中的横向坐标。
     *
     * @return 横向坐标。
     */
    public double getCenterX() {
        return centerX;
    }

    /**
     * 设置图片的中心在世界坐标系中的横向坐标。// TODO 世界坐标系以什么为基准？基准坐标系是什么？
     *
     * @param centerX 横向坐标。// TODO 取值范围？？
     */
    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    /**
     * 获取图片的中心在世界坐标系中的纵向坐标。
     *
     * @return 纵向坐标。
     */
    public double getCenterY() {
        return centerY;
    }

    /**
     * 设置图片的中心在世界坐标系中的纵向坐标。// TODO 世界坐标系以什么为基准？基准坐标系是什么？
     *
     * @param centerX 纵向坐标。// TODO 取值范围？？
     */
    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    /**
     * 获取图片的宽度。
     *
     * @return 图片的宽度，单位为像素。
     */
    public double getWidth() {
        return width;
    }

    /**
     * 设置图片的宽度。
     *
     * @param width 图片的宽度，单位为像素。// TODO 取值范围？？不能大于视野范围的宽度?
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * 获取图片的高度。
     *
     * @return 图片的高度，单位为像素。
     */
    public double getHeight() {
        return height;
    }

    /**
     * 设置图片的高度。
     *
     * @param width 图片的高度，单位为像素。// TODO 取值范围？？不能大于视野范围的高度?
     */
    public void setHeight(double height) {
        this.height = height;
    }
}
