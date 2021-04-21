package com.herewhite.sdk.domain;

/**
 * @deprecated This interface is deprecated. Use the {@link com.herewhite.sdk.CommonCallback#urlInterrupter(String) urlInterrupter} method in {@link com.herewhite.sdk.CommonCallback CommonCallback} instead.
 */
public interface UrlInterrupter {
    String urlInterrupter(String sourceUrl);
}
