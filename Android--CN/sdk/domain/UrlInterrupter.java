package com.herewhite.sdk.domain;

/**
 * @deprecated 该接口已废弃。请使用 {@link com.herewhite.sdk.CommonCallbacks CommonCallbacks} 中的 {@link com.herewhite.sdk.CommonCallbacks#urlInterrupter(String)} 方法。
 */
public interface UrlInterrupter {
    String urlInterrupter(String sourceUrl);
}
