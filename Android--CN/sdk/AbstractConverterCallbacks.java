package com.herewhite.sdk;

import com.herewhite.sdk.domain.ConversionInfo;
import com.herewhite.sdk.domain.ConvertException;
import com.herewhite.sdk.domain.ConvertedFiles;

/**
 * 文档中隐藏
 */
public class AbstractConverterCallbacks implements ConverterCallbacks {
    @Override
    public void onProgress(Double progress, ConversionInfo convertInfo) {

    }

    @Override
    public void onFinish(ConvertedFiles ppt, ConversionInfo convertInfo) {

    }

    @Override
    public void onFailure(ConvertException e) {

    }
}
