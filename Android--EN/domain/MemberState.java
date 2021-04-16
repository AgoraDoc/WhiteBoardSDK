package com.herewhite.sdk.domain;

/**
 * Created by buhe on 2018/8/11.
 */

/**
 * Settings of the whiteboard tool in use.
 */
public class MemberState extends WhiteObject {
    private String currentApplianceName;
    private int[] strokeColor;
    private Double strokeWidth;
    private Double textSize;

    public MemberState() {
    }

    /**
     * Gets the name of the whiteboard tool in use.
     *
     * @return The name of the whiteboard tool in use.
     */
    public String getCurrentApplianceName() {
        return currentApplianceName;
    }

    /**
     * Sets the currently-used whiteboard tool.
     *
     * @param currentApplianceName The whiteboard tool. See {@link Appliance}.
     */
    public void setCurrentApplianceName(String currentApplianceName) {
        this.currentApplianceName = currentApplianceName;
    }

    /**
     * Gets the stroke color.
     *
     * @return The stroke color in the RGB format, for example, `0, 0, 255` (blue).
     */
    public int[] getStrokeColor() {
        return strokeColor;
    }

    /**
     * Sets the stroke color.
     *
     * @param strokeColor The stroke color in the RGB format, for example, `0, 0, 255` (blue).
     */
    public void setStrokeColor(int[] strokeColor) {
        this.strokeColor = strokeColor;
    }


    /**
     * Gets the stroke width.
     *
     * @return The stroke width.
     */
    public double getStrokeWidth() {
        return strokeWidth;
    }

    /**
     * Sets the stroke width.
     *
     * @param strokeWidth The stroke width.
     */
    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }


    /**
     * Gets the text size.
     *
     * @return The text size.
     */
    public double getTextSize() {
        return textSize;
    }

    /**
     * Sets the text size.
     *
     * @param textSize The text size. The Chrome browser automatically adjusts the fonts smaller than 12 to 12.
     */
    public void setTextSize(double textSize) {
        this.textSize = textSize;
    }
}
