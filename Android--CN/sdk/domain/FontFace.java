package com.herewhite.sdk.domain;

import com.google.gson.annotations.SerializedName;

/**
 * 字体配置文件，与 FontFace 属性 一一对应 // TODO 后半句话是什么意思？与 CSS 中的 FontFace 属性对应。
 *
 * @since 2.11.2
 */
public class FontFace extends WhiteObject {

    /**
     * @param name 字体名称，需要和 CSS 中 `font-family` 字段的值对应。
     * @param src 字体文件的地址，需要和 CSS 中 `src` 字段的值对应。支持的格式包括：
     *  - `url()`: 指向远程字体文件位置，例如，`url("https://white-pan.oss-cn-shanghai.aliyuncs.com/Pacifico-Regular.ttf")`。// TODO CT 需要确认是否正确。（也可以根据 CSS FontFace 支持的其他格式进行填写。）太模糊了
     *  - `local()`: 指定用户本地计算机上的字体，例如，`local('Arial')`。
     */
    public FontFace(String name, String src) {
        this.fontFamily = name;
        this.src = src;
    }

    @SerializedName("font-family")
    private String fontFamily;
    private String src;
    /**
     * 获取字体样式。
     *
     * @return 字体样式。
     */
    public String getFontStyle() {
        return fontStyle;
    }

    /**
     * 设置字体样式。
     * @param fontStyle 字体样式，需要和 CSS 中 `font-style` 字段的值对应。
     * // TODO 是和 `font-style` 字段的值对应，还是和 `font-style` 字段对应？和 `font-style`是什么关系
     * 取值包括：
     * - `normal`：（默认）常规。
     * - `italic`：斜体。
     * - `bold`：加粗。（mozilla 上写的是 oblique https://developer.mozilla.org/zh-CN/docs/Web/CSS/@font-face/font-style）
     */
    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    /**
     * 获取字体粗细。
     *
     * @return 字体粗细。
     */
    public String getFontWeight() {
        return fontWeight;
    }

    /**
     * 设置字体粗细。
     * @param fontWeight 字体粗细，和 CSS 中 `font-weight` 字段的值对应，取值范围包括：// TODO CT 取值？？
     *
     */
    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }

    /**
     * 获取字体的字符编码范围。
     *
     * @return 字体的字符编码范围
     */
    public String getUnicodeRange() {
        return unicodeRange;
    }

    /**
     * 设置字体的字符编码范围，和 CSS 中 `unicode-range` 字段的值对应，取值范围 // TODO ？？
     * @param unicodeRange
     */
    public void setUnicodeRange(String unicodeRange) {
        this.unicodeRange = unicodeRange;
    }

    @SerializedName("font-style")
    private String fontStyle;
    @SerializedName("font-weight")
    private String fontWeight;
    @SerializedName("unicode-range")
    private String unicodeRange;
}
