package com.herewhite.sdk;

import com.herewhite.sdk.domain.ConversionInfo;
import com.herewhite.sdk.domain.ConvertException;
import com.herewhite.sdk.domain.ConvertedFiles;

/**
 * // TODO 是否在文档中隐藏？
 */
public interface ConverterCallbacks {
    void onProgress(Double progress, ConversionInfo convertInfo);
    void onFinish(ConvertedFiles ppt, ConversionInfo convertInfo);
    void onFailure(ConvertException e);
}
