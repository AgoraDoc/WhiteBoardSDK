package com.herewhite.sdk;

import com.herewhite.sdk.domain.SDKError;

import org.json.JSONObject;

/**
 * 通用回调，用于 SDK 向 app 发送回调事件通知。
 *
 * 实时房间和回放房间都可以继承该接口下的回调方法。
 *
 * @since 2.9.13
 */
public interface CommonCallbacks {

    /**
     *  SDK 出现未捕获的全局错误回调。
     * @param args
     */
    void throwError(Object args);

    /**
     * 图片拦截回调。
     *
     * @since 2.9.14
     *
     * 要触发该回调，必须在初始化白板 SDK 时，调用 {@link WhiteSdkConfiguration#setEnableInterrupterAPI setEnableInterrupterAPI}(true) 开启图片拦截替换功能。
     * 开启图片拦截替换功能后，在白板中插入图片或场景时，会触发该回调。
     *
     * @note
     * 由于该回调过于频繁，Agora 不推荐使用；在 Android 平台，可以使用 WebView 的拦截功能进行图片拦截。
     *
     * // TODO 是不建议监听该回调，还是不推荐开启图片拦截替换功能，即 setEnableInterrupterAPI(true)？
     * 由于该 API 存在性能问题（调用过于频繁），我们不推荐使用；Android 端，可以使用 WebView 的拦截功能进行拦截
     *
     * @param sourceUrl 图片原地址。
     * @return 替换后的图片地址。
     */
    String urlInterrupter(String sourceUrl);
    /**
     * 播放动态 ppt 中的音视频回调。
     *
     * @since 2.9.13
     */
    void onPPTMediaPlay();

    /**
     * 暂停播放动态 ppt 中的音视频回调。
     *
     * @since 2.9.13
     */
    void onPPTMediaPause();

    /**
     * 接收到自定义消息回调。
     *
     * @since 2.11.4
     *
     * 该回调表述本地用户收到了 bridge 网页发送的消息。// TODO 什么是 bridge 网页？
     *
     * @note
     * 不保证所有用户都能接收到该回调。// TODO ???
     *
     * @param object 内容格式会根据情况发送。发送时，均为 JSON 格式
     * // TODO bridge 网页发送的消息都是 JSON 格式？内容格式会根据情况发送是什么意思？
     */
    void onMessage(JSONObject object);

    /**
     * SDK 初始化失败回调。
     *
     * @since 2.9.14
     *
     * 如果 SDK 初始化失败，调用加入实时房间或回放房间时会处于一直无响应状态，需要重新初始化 SDK。
     * - 初始化 SDK 时候，网络异常，导致获取配置信息失败。
     * - 传入了不合法的 App Identifier。
     * @since 2.9.14
     */
    void sdkSetupFail(SDKError error);
}
