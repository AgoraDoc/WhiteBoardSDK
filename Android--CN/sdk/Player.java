package com.herewhite.sdk;

import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.herewhite.sdk.domain.EventEntry;
import com.herewhite.sdk.domain.EventListener;
import com.herewhite.sdk.domain.FrequencyEventListener;
import com.herewhite.sdk.domain.PlayerObserverMode;
import com.herewhite.sdk.domain.PlayerPhase;
import com.herewhite.sdk.domain.PlayerState;
import com.herewhite.sdk.domain.PlayerTimeInfo;
import com.herewhite.sdk.domain.Promise;
import com.herewhite.sdk.domain.SDKError;

import java.util.concurrent.ConcurrentHashMap;

import wendu.dsbridge.OnReturnValue;

/**
 * Player 类。如果你在创建房间时设置了录制功能，就可以通过该类对白板房间内的操作进行回放。
 */
public class Player extends Displayer {

    private final ConcurrentHashMap<String, EventListener> eventListenerConcurrentHashMap = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, FrequencyEventListener> frequencyEventListenerConcurrentHashMap = new ConcurrentHashMap<>();

    private final SyncDisplayerState<PlayerState> syncPlayerState;

    private final long timeDuration;
    private final int framesCount;
    private final long beginTimestamp;

    /**
     * 获取白板回放的播放倍速。该方法为同步调用。
     *
     * 该方法获取的是播放倍速，如 1.0、1.5、2.0 倍速。因此回放暂停时，返回值也不会为 0。
     * @return 白板回放的播放倍速。
     * @since 2.5.2
     */
    public double getPlaybackSpeed() {
        return playbackSpeed;
    }

    /**
     * 设置白板回放时的播放倍速。
     * @param playbackSpeed 白板回放的播放倍速。
     * @since 2.5.2
     */
    // TODO 需要补充取值范围，及各取值代表什么意思。
    public void setPlaybackSpeed(double playbackSpeed) {
        this.playbackSpeed = playbackSpeed;
        bridge.callHandler("player.setPlaybackSpeed", new Object[]{playbackSpeed});
    }

    /**
     * 获取白板回放时的播放速率。该方法为异步调用。
     *
     * 该方法获取的是播放倍速，如 1.0、1.5、2.0 倍速。因此回放暂停时，返回值也不会为 0。
     *
     * @note 该方位为异步调用。我们推荐你仅在调试或问题排查时使用。一般情况下可以使用同步方法 {@link #getPlaybackSpeed()} 进行获取。
     * @param promise 完成回调，详见 {@link Promise 类}。
     * @since 2.5.2
     */
    public void getPlaybackSpeed(final Promise<Double> promise) {
        bridge.callHandler("player.state.playbackSpeed", new OnReturnValue<Number>() {
            @Override
            public void onValue(Number value) {
                promise.then(value.doubleValue());
            }
        });
    }

    private double playbackSpeed;

    private long scheduleTime = 0;

    private PlayerPhase playerPhase = PlayerPhase.waitingFirstFrame;

    /**
     * 文档中隐藏，SDK 内部使用
     * Instantiates a new Player.
     *
     * @param room            回放房间 uuid
     * @param bridge          the bridge
     * @param context         the context
     * @param whiteSdk        the white sdk
     * @param playerTimeInfo  the player time info
     * @param syncPlayerState the sync player state
     */
    public Player(String room, WhiteboardView bridge, Context context, WhiteSdk whiteSdk, PlayerTimeInfo playerTimeInfo, SyncDisplayerState<PlayerState> syncPlayerState) {
        super(room, bridge, context, whiteSdk);
        this.syncPlayerState = syncPlayerState;
        this.timeDuration = playerTimeInfo.getTimeDuration();
        this.framesCount = playerTimeInfo.getFramesCount();
        this.beginTimestamp = playerTimeInfo.getBeginTimestamp();
    }

    SyncDisplayerState<PlayerState> getSyncPlayerState() {
        return syncPlayerState;
    }

    void setPlayerPhase(PlayerPhase playerPhase) {
        this.playerPhase = playerPhase;
    }

    void setScheduleTime(long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    /**
     * 开始白板回放。
     */
    public void play() {
        bridge.callHandler("player.play", new Object[]{});
    }

    /**
     * 暂停白板回放。
     */
    // TODO 暂停后想恢复播放怎么整。
    public void pause() {
        bridge.callHandler("player.pause", new Object[]{});
    }

    /**
     * 停止白板回放。
     *
     * 白板回放停止后，Player 资源会被释放。如果想要重新播放，则需要重新初始化 Player 实例。
     */
    public void stop() {
        bridge.callHandler("player.stop", new Object[]{});
    }

    /**
     * 设置白板回放的跳转时间。
     *
     * 通常来说白板回放是从头开始的。如果你想要从特定的时间开始回放，则可以通过该方法设置。
     *
     * @param seekTime 白板回放的跳转时间，单位为毫秒。
     */
    public void seekToScheduleTime(long seekTime) {
        bridge.callHandler("player.seekToScheduleTime", new Object[]{seekTime});
    }

    //region Event API
    void fireMagixEvent(EventEntry eventEntry) {
        EventListener eventListener = eventListenerConcurrentHashMap.get(eventEntry.getEventName());
        if (eventListener != null) {
            try {
                eventListener.onEvent(eventEntry);
            } catch (Throwable e) {
                Logger.error("An exception occurred while sending the event", e);
            }
        }
    }

    void fireHighFrequencyEvent(EventEntry[] eventEntries) {
        FrequencyEventListener eventListener = frequencyEventListenerConcurrentHashMap.get(eventEntries[0].getEventName());
        if (eventListener != null) {
            try {
                eventListener.onEvent(eventEntries);
            } catch (AssertionError a) {
                throw a;
            } catch (Throwable e) {
                Logger.error("An exception occurred while sending the event", e);
            }
        }
    }

    //endregion

    /**
     * 设置白板回放的查看模式。
     *
     * @param mode 白板回放的查看模式，详见 {@link PlayerObserverMode}。
     */
    public void setObserverMode(PlayerObserverMode mode) {
        bridge.callHandler("player.setObserverMode", new Object[]{mode.name()});
    }

    //region Get API

    /**
     * 获取白板回放房间的播放状态。该方法为同步调用。
     *
     * 在 Player 生命周期内，你可以调用该方法获取当前回放房间的播放状态。其中初始状态为 `waitingFirstFrame`，表示正在等待白板回放的第一帧。
     *
     * @note 成功调用 {@link #stop()}、{@link #play()}、{@link #pause()} 等方法均会影响回放房间的播放状态，但是该状态不会立即更新。
     *
     * @return 回放房间的播放状态，详见 {@link PlayerPhase}。
     * @since 2.4.0
     */
    public PlayerPhase getPlayerPhase() {
        return this.playerPhase;
    }

    /**
     * 获取白板回放房间的播放状态。该方法为异步调用。
     *
     * 在 Player 生命周期内，你可以调用该方法获取当前回放房间的播放状态。其中初始状态为 `waitingFirstFrame`，表示正在等待白板回放的第一帧。
     *
     * @note
     * - 成功调用 {@link #stop()}、{@link #play()}、{@link #pause()} 等方法均会影响回放房间的播放状态，但是该状态不会立即更新。
     * - 该方位为异步调用。我们推荐你仅在调试或问题排查时使用。一般情况下可以使用同步方法 {@link #getPlayerPhase()} 进行获取。
     *
     * @param promise 完成回调，详见 {@link Promise 类}。
     */
    public void getPhase(final Promise<PlayerPhase> promise) {
        bridge.callHandler("player.getBroadcastState", new Object[]{}, new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                try {
                    promise.then(PlayerPhase.valueOf(String.valueOf(o)));
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from getPhase", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in getPhase promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }

    /**
     * 获取回放房间的播放器状态。该方法为同步调用。
     *
     * 如果回放房间的播放状态 Player phase 为 `waitingFirstFrame`，则该方法返回 Null。
     *
     * @return 回放房间状态，详见 {@link PlayerState}。
     * @since 2.4.0
     */
    public PlayerState getPlayerState() {
        if (playerPhase == PlayerPhase.waitingFirstFrame) {
            return null;
        }
        return this.syncPlayerState.getDisplayerState();
    }

    /**
     * 获取回放房间的播放器状态。该方法为异步调用。
     *
     * @note 该方位为异步调用。我们推荐你仅在调试或问题排查时使用。一般情况下可以使用同步方法 {@link #getPlayerState()} 进行获取。
     *
     * @param promise 完成回调，详见 {@link Promise 类}。
     */
    public void getPlayerState(final Promise<PlayerState> promise) {
        bridge.callHandler("player.state.playerState", new Object[]{}, new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                try {
                    PlayerState playerState = gson.fromJson(String.valueOf(o), PlayerState.class);
                    promise.then(playerState);
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from getPlayerState", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in getPlayerState promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }

    /**
     * 获取白板回放的时间信息，该方法为同步调用。
     *
     * 该方法获取的时间信息，包含当前的播放时间，回放文件的总时长，以及开始播放的 UTC 时间戳，单位为毫秒。
     *
     * @note 该方法获取的当前播放时间可能不准确。
     *
     * @return 播放时间信息，详见 {@link PlayerTimeInfo}。
     * @since 2.4.0
     */
    public PlayerTimeInfo getPlayerTimeInfo() {
        return new PlayerTimeInfo(this.scheduleTime, this.timeDuration, this.framesCount, this.beginTimestamp);
    }

    /**
     * 获取白板回放的时间信息，该方法为异步调用。
     *
     * 该方法获取的时间信息，包含当前的播放时间，回放文件的总时长，以及开始播放的 UTC 时间戳，单位为毫秒。
     *
     * @note 该方法为异步调用。我们推荐你仅在调试或问题排查时使用。一般情况下可以使用同步方法 {@link #getPlayerInfo()} 进行获取。
     *
     * @param promise 完成回调，详见 {@link Promise 类}。
     */
    public void getPlayerTimeInfo(final Promise<PlayerTimeInfo> promise) {
        bridge.callHandler("player.state.timeInfo", new Object[]{}, new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                promise.then(getPlayerTimeInfo());
            }
        });
    }
    //endregion
}
