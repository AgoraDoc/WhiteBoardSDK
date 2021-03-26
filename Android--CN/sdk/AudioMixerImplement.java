package com.herewhite.sdk;

import android.webkit.JavascriptInterface;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * `AudioMixerImplement` 类。// TODO 这个类的作用是？
 */
public class AudioMixerImplement {

    AudioMixerImplement(WhiteboardView bridge, AudioMixerBridge mixerBridge) {
        this.bridge = bridge;
        this.mixerBridge = mixerBridge;
    }

    /**
     * 设置音乐文件播放状态。
     *
     * 你需要在 RTC SDK 触发的 `onAudioMixingStateChanged` 回调中调用该方法，将音乐文件播放状态传递给白板 SDK。
     * 通知白板 SDK 是否播放 PPT 中的视频，以确保 PPT 的音画同步。
     * // TODO 是将音乐文件播放状态传递给白板 SDK，还是传递给 PPT？这样做的目的是通知 PPT 播放视频吗？
     * @note
     * 如果 RTC SDK 没有混音状态回调方法，会导致播放的 PPT音画不同步。
     *
     * @param state 音乐文件播放状态：
     *  - MEDIA_ENGINE_AUDIO_EVENT_MIXING_PLAY(710): RTC SDK 成功调用 `startAudioMixing` 播放音乐文件或 `resumeAudioMixing`  恢复播放音乐文件。
     *  - MEDIA_ENGINE_AUDIO_EVENT_MIXING_PAUSED(711)：RTC SDK 成功调用 `pauseAudioMixing` 暂停播放音乐文件。
     *  - MEDIA_ENGINE_AUDIO_EVENT_MIXING_STOPPED(713)：RTC SDK 成功调用 `stopAudioMixing` 停止播放音乐文件。
     *  - MEDIA_ENGINE_AUDIO_EVENT_MIXING_ERROR(714)：音乐文件播放失败。SDK 会在 `errorCode` 参数中返回具体的报错原因。
     * @param errorCode 音乐文件播放失败的原因：
     * - MEDIA_ENGINE_AUDIO_ERROR_MIXING_OPEN(701)：音乐文件打开出错。
     * - MEDIA_ENGINE_AUDIO_ERROR_MIXING_TOO_FREQUENT(702)：音乐文件打开太频繁。
     * - MEDIA_ENGINE_AUDIO_EVENT_MIXING_INTERRUPTED_EOF(703)：音乐文件播放异常中断。
     */
    public void setMediaState(long state, long errorCode) {
        this.bridge.callHandler("rtc.callback", new Object[]{state, errorCode});
    }

    private WhiteboardView bridge;
    private AudioMixerBridge mixerBridge;

    @JavascriptInterface
    public void startAudioMixing(Object args) {
        if (this.mixerBridge != null && args instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject)args;
            try {
                String filePath = jsonObject.getString("filePath");
                boolean loopback = jsonObject.getBoolean("loopback");
                boolean replace = jsonObject.getBoolean("replace");
                int cycle = jsonObject.getInt("cycle");
                this.mixerBridge.startAudioMixing(filePath, loopback, replace, cycle);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @JavascriptInterface
    public void stopAudioMixing(Object args) {
        if (this.mixerBridge != null) {
            this.mixerBridge.stopAudioMixing();
        }
    }

    @JavascriptInterface
    public void setAudioMixingPosition(Object args) {
        if (this.mixerBridge != null) {
            int pos = Integer.valueOf((Integer) args);
            this.mixerBridge.setAudioMixingPosition(pos);
        }
    }
}
