package com.herewhite.sdk;

/**
 * `AudioMixerBridge` 接口，用于桥接 RTC SDK 的混音方法和白板 SDK。// TODO 确认一下是否正确？
 *
 * @since 2.9.15
 *
 * 当用户同时使用音视频功能和互动白板，且在互动白板中展示的动态 PPT 包含音频文件时，可能遇到以下问题：
 * - 播放 PPT 内的音频时声音很小。
 * - 播放 PPT 内的音频时有回声。
 *
 * 为解决上述问题，你可以使用 `AudioMixerBridge` 接口，以调用 RTC SDK 的混音方法播放动态 PPT 中的音频文件。
 *
 * @note
 * 仅当用户使用的 RTC SDK 支持混音方法时，该方法才会生效。
 * // TODO 这个方法是否只在 Agora RTC SDK 上做过验证？如果是，这里直接改成 Agora RTC SDK 比较好。
 */
public interface AudioMixerBridge {

    // TODO 以下接口是否仅为内部调用，可以在文档中隐藏？
    /**
     * 进行混音，在混音后，需要将混音结果通过 {@link AudioMixerImplement#setMediaState(long, long)} 传递给动态 ppt 内部。
     * @param filepath 文件路径，可以是本地文件或者网络地址
     * @param loopback true 则音频不通过 rtc 传播
     * @param replace true 则只播文件声音，不播麦克风声音，false 则是将文件和麦克风混音
     * @param cycle 循环播放文件的次数，-1 是无限循环
     */
    void startAudioMixing(String filepath, boolean loopback, boolean replace, int cycle);

    /**
     * 停止混音
     */
    void stopAudioMixing();

    /**
     * 设置混音文件的播放进度，相当于对混音源文件进行 seek 操作
     * @param position 播放进度参数
     */
    void setAudioMixingPosition(int position);
}
