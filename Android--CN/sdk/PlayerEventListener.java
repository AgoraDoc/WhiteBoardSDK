package com.herewhite.sdk;

import com.herewhite.sdk.domain.PlayerPhase;
import com.herewhite.sdk.domain.PlayerState;
import com.herewhite.sdk.domain.SDKError;

/**
 * 回放房间的事件回调接口。
 */
public interface PlayerEventListener {
    /**
     * 播放状态切换回调。
     */
    void onPhaseChanged(PlayerPhase phase);

    /**
     * 首帧加载回调。
     */
    void onLoadFirstFrame();

    /**
     * 分片切换回调，需要了解分片机制。目前无实际用途。//TODO WJ 这个要放到文档里吗？
     */
    void onSliceChanged(String slice);

    /**
     * 回放状态发生变化的回调，只会包含实际发生改变的属性。
     */
    void onPlayerStateChanged(PlayerState modifyState);

    /**
     * 出错导致回放暂停的回调。
     */
    void onStoppedWithError(SDKError error);

    /**
     * 进度时间变化 //TODO WJ 这个是什么意思？
     */
    void onScheduleTimeChanged(long time);

    /**
     * 添加帧出错的回调。
     */
    void onCatchErrorWhenAppendFrame(SDKError error);
    /**
     * 渲染时出错的回调。
     */
    void onCatchErrorWhenRender(SDKError error);
}
