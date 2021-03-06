package com.herewhite.sdk;

import android.content.Context;
import android.webkit.JavascriptInterface;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.herewhite.sdk.domain.FontFace;
import com.herewhite.sdk.domain.PlayerConfiguration;
import com.herewhite.sdk.domain.PlayerState;
import com.herewhite.sdk.domain.PlayerTimeInfo;
import com.herewhite.sdk.domain.Promise;
import com.herewhite.sdk.domain.RoomPhase;
import com.herewhite.sdk.domain.RoomState;
import com.herewhite.sdk.domain.SDKError;
import com.herewhite.sdk.domain.UrlInterrupter;

import org.json.JSONObject;

import java.util.Map;

import wendu.dsbridge.OnReturnValue;

public class WhiteSdk {

    private final static Gson gson = new Gson();

    private final WhiteboardView bridge;
    private final Context context;
    private final RoomCallbacksImplement roomCallbacksImplement;
    private final PlayerCallbacksImplement playerCallbacksImplement;

    public CommonCallbacks getCommonCallbacks() {
        return commonCallbacks;
    }

    /**
     * 设置通用回调事件。
     *
     * SDK 通过 `CommonCallbacks` 类向 app 报告 SDK 运行时的各项事件。
     *
     * @param commonCallbacks 通用回调事件，详见 {@link CommonCallbacks CommonCallbacks}
     */
    public void setCommonCallbacks(CommonCallbacks commonCallbacks) {
        this.commonCallbacks = commonCallbacks;
    }

    @Nullable
    private CommonCallbacks commonCallbacks;
    private final boolean onlyCallbackRemoteStateModify;
    @Nullable
    private UrlInterrupter urlInterrupter;

    public AudioMixerImplement getAudioMixerImplement() {
        return audioMixerImplement;
    }

    @Nullable
    private AudioMixerImplement audioMixerImplement;

    /**
     * 查询 SDK 版本号。
     *
     * @return 当前的 SDK 版本号，格式为字符串，如 2.11.20。
     */
    public static String Version() {
        return "2.11.20";
    }

    /**
     * 初始化白板 SDK。
     *
     * 请确保在调用其他 API 前先调用 `WhiteSdk` 创建并初始化白板 SDK 实例。
     *
     * @note
     * //TODO WL 每个 app 可以初始化多个 SDK 实例。你可以通过多实例创建多个 `WhiteboardView`。
     * 每个 app 可以创建多个 `Whiteboardview`，并初始化多个 SDK 实例。
     *
     * @param bridge 白板界面，详见 {@link WhiteboardView WhiteboardView}。
     * @param context 安卓活动 (Android Activity) 的上下文。
     * @param whiteSdkConfiguration SDK 实例的配置，详见 {@link WhiteSdkConfiguration WhiteSdkConfiguration}。
     * @param commonCallbacks 通用回调事件，详见 {@link commonCallbacks commonCallbacks}。
     */
    public WhiteSdk(WhiteboardView bridge, Context context, WhiteSdkConfiguration whiteSdkConfiguration, @Nullable CommonCallbacks commonCallbacks) {
        this(bridge, context, whiteSdkConfiguration, commonCallbacks, null);
    }

    /**
     * 初始化白板 SDK。
     *
     * 请确保在调用其他 API 前先调用 `WhiteSdk` 创建并初始化白板 SDK 实例。
     *
     * //TODO WL 多实例 note 这里不需要加吗？
     *
     * @param bridge 白板界面，详见 {@link WhiteboardView WhiteboardView}。
     * @param context 安卓活动 (Android Activity) 的上下文。
     * @param whiteSdkConfiguration SDK 实例的配置，详见 {@link WhiteSdkConfiguration WhiteSdkConfiguration}。
     * @param commonCallbacks 通用回调事件，详见 {@link commonCallbacks commonCallbacks}。
     * @param audioMixerBridge RTC 混音设置，详见 {@link AudioMixerBridge AudioMixerBridge}。当你同时使用 Agora RTC SDK 和互动白板 SDK, 且互动白板中展示的动态 PPT 中包含音频文件时，你可以调用 `AudioMixerBridge` 接口，将动态 PPT 中的所有音频交给 Agora RTC SDK 进行混音播放。
     */
    public WhiteSdk(WhiteboardView bridge, Context context, WhiteSdkConfiguration whiteSdkConfiguration, @Nullable CommonCallbacks commonCallbacks, @Nullable AudioMixerBridge audioMixerBridge) {
        this.bridge = bridge;
        this.context = context;
        this.roomCallbacksImplement = new RoomCallbacksImplement(context);
        this.playerCallbacksImplement = new PlayerCallbacksImplement();
        this.onlyCallbackRemoteStateModify = whiteSdkConfiguration.isOnlyCallbackRemoteStateModify();
        this.commonCallbacks = commonCallbacks;
        if (audioMixerBridge != null) {
            this.audioMixerImplement = new AudioMixerImplement(bridge, audioMixerBridge);
            bridge.addJavascriptObject(this.audioMixerImplement, "rtc");
            whiteSdkConfiguration.setEnableRtcIntercept(true);
        }

        bridge.addJavascriptObject(this, "sdk");
        bridge.addJavascriptObject(this.roomCallbacksImplement, "room");
        bridge.addJavascriptObject(this.playerCallbacksImplement, "player");

        if (whiteSdkConfiguration.isOnlyCallbackRemoteStateModify()) {
            // JavaScript 必须将所有 state 变化回调提供给 native。
            // 该属性的实现在 native 代码中体现。
            whiteSdkConfiguration.setOnlyCallbackRemoteStateModify(false);
        }
        bridge.callHandler("sdk.newWhiteSdk", new Object[]{whiteSdkConfiguration});

        whiteSdkConfiguration.setOnlyCallbackRemoteStateModify(this.onlyCallbackRemoteStateModify);
    }

    /**
     * 初始化白板 SDK。
     *
     * 请确保在调用其他 API 前先调用 `WhiteSdk` 创建并初始化白板 SDK 实例。
     *
     * //TODO WL 多实例 note 这里不需要加吗？
     *
     * @param bridge 白板界面，详见 {@link WhiteboardView WhiteboardView}。
     * @param context 安卓活动 (Android Activity) 的上下文。
     * @param whiteSdkConfiguration SDK 实例的配置，详见 {@link WhiteSdkConfiguration WhiteSdkConfiguration}。
     */
    public WhiteSdk(WhiteboardView bridge, Context context, WhiteSdkConfiguration whiteSdkConfiguration) {
        this(bridge, context, whiteSdkConfiguration, (CommonCallbacks) null);
    }

    /**
     * 初始化白板 SDK。
     *
     * 请确保在调用其他 API 前先调用 `WhiteSdk` 创建并初始化白板 SDK 实例。
     *
     * //TODO WL 多实例 note 这里不需要加吗？
     *
     * @param bridge 白板界面，详见 {@link WhiteboardView WhiteboardView}。
     * @param context 安卓活动 (Android Activity) 的上下文。
     * @param whiteSdkConfiguration SDK 实例的配置，详见 {@link WhiteSdkConfiguration WhiteSdkConfiguration}。
     * @param urlInterrupter 图片拦截替换设置，详见 {@link urlInterrupter}。@deprecated 该参数已废弃。请改用 `CommonCallbacks` 接口中的 {@link CommonCallbacks#urlInterrupter(String) urlInterrupter} 方法。
     */
    public WhiteSdk(WhiteboardView bridge, Context context, WhiteSdkConfiguration whiteSdkConfiguration, UrlInterrupter urlInterrupter) {
        this(bridge, context, whiteSdkConfiguration);
        this.urlInterrupter = urlInterrupter;
    }

    /**
     * 加入互动白板实时房间。
     *
     * @param roomParams 互动白板房间的参数配置，详见 {@link RoomParams RoomParams}。
     * @param roomPromise `Promise<Room>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `joinRoom` 的调用结果：
     *   - 如果方法调用成功，将返回房间对象。
     *   - 如果方法调用失败，将返回错误信息。
     */
    public void joinRoom(final RoomParams roomParams, final Promise<Room> roomPromise) {
        this.joinRoom(roomParams, null, roomPromise);
    }

    /**
     * 加入互动白板实时房间。
     *
     * @param roomParams    互动白板房间的参数配置，详见 {@link RoomParams RoomParams}。
     * @param roomCallbacks 房间事件回调，详见 {@link RoomCallbacks RoomCallbacks}。在重连时，如果不传 `roomCallback` 参数，则会使用之前上一次设置的 `roomCallback`。如果要释放 `roomCallback`，可以调用 {@link releaseRoom(String) releaseRoom}。
     * @param roomPromise   `Promise<Room>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `joinRoom` 的调用结果：
     *   - 如果方法调用成功，则返回房间对象。
     *   - 如果方法调用失败，则返回错误信息。
     */
    public void joinRoom(final RoomParams roomParams, final RoomCallbacks roomCallbacks, final Promise<Room> roomPromise) {
        try {
            if (roomCallbacks != null) {
                this.roomCallbacksImplement.setListener(roomCallbacks);  // 覆盖
            }
            bridge.callHandler("sdk.joinRoom", new Object[]{roomParams}, new OnReturnValue<String>() {
                @Override
                public void onValue(String roomString) {
                    JsonObject jsonObject = gson.fromJson(roomString, JsonObject.class);
                    SDKError promiseError = SDKError.promiseError(jsonObject);
                    if (promiseError != null) {
                        try {
                            roomPromise.catchEx(promiseError);
                        } catch (AssertionError a) {
                            throw a;
                        } catch (Throwable e) {
                            Logger.error("An exception occurred while catch joinRoom method exception", e);
                        }
                    } else {
                        boolean disableCallbackWhilePutting = onlyCallbackRemoteStateModify;
                        JsonObject jsonState = jsonObject.getAsJsonObject("state");
                        SyncDisplayerState<RoomState> syncRoomState = new SyncDisplayerState<>(RoomState.class, jsonState.toString(), disableCallbackWhilePutting);
                        Room room = new Room(roomParams.getUuid(), bridge, context, WhiteSdk.this, syncRoomState);
                        Long observerId = jsonObject.get("observerId").getAsLong();
                        Boolean isWritable = jsonObject.get("isWritable").getAsBoolean();
                        room.setObserverId(observerId);
                        room.setWritable(isWritable);
                        room.setRoomPhase(RoomPhase.connected);
                        roomCallbacksImplement.setRoom(room);
                        roomPromise.then(room);
                    }
                }
            });
        } catch (AssertionError a) {
            throw a;
        } catch (Exception e) {
            roomPromise.catchEx(new SDKError(e.getMessage()));
        }
    }

    /**
     * 创建互动白板回放房间。
     *
     * @param playerConfiguration 回放房间的配置，详见 {@link PlayerConfiguration PlayerConfiguration}。
     * @param playerPromise `Promise<Player>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `createPlayer` 的调用结果：
     *   - 如果方法调用成功，将返回新创建的回放房间对象。
     *   - 如果方法调用失败，将返回错误信息。
     */
    public void createPlayer(final PlayerConfiguration playerConfiguration, final Promise<Player> playerPromise) {
        createPlayer(playerConfiguration, null, playerPromise);
    }

    /**
     * 创建互动白板回放房间。
     *
     * @param playerConfiguration 回放房间的配置，详见 {@link PlayerConfiguration PlayerConfiguration}。
     * @param playerEventListener 回放房间事件回调。如果使用同一个互动白板 SDK 创建多个回放房间且在创建新的回放房间时该参数设为 null, 则新创建的回放房间会使用上一次创建回放房间时传入的 `playerEventListener`。
     *
     * @param playerPromise `Promise<Player>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `createPlayer` 的调用结果：
     *   - 如果方法调用成功，将返回新创建的回放房间对象。
     *   - 如果方法调用失败，将返回错误信息。
     */
    public void createPlayer(final PlayerConfiguration playerConfiguration, PlayerEventListener playerEventListener, final Promise<Player> playerPromise) {
        try {
            if (playerEventListener != null) {
                this.playerCallbacksImplement.setListener(playerEventListener);
            }
            bridge.callHandler("sdk.replayRoom", new Object[]{
                    playerConfiguration
            }, new OnReturnValue<String>() {
                @Override
                public void onValue(String roomString) {
                    JsonObject jsonObject = gson.fromJson(roomString, JsonObject.class);
                    SDKError promiseError = SDKError.promiseError(jsonObject);
                    if (promiseError != null) {
                        try {
                            playerPromise.catchEx(promiseError);
                        } catch (AssertionError a) {
                            throw a;
                        } catch (Throwable e) {
                            Logger.error("An exception occurred while catch createPlayer method exception", e);
                        }
                    } else {
                        JsonObject timeInfo = jsonObject.getAsJsonObject("timeInfo");
                        PlayerTimeInfo playerTimeInfo = gson.fromJson(timeInfo.toString(), PlayerTimeInfo.class);
                        SyncDisplayerState<PlayerState> syncPlayerState =  new SyncDisplayerState(PlayerState.class, "{}", true);
                        Player player = new Player(playerConfiguration.getRoom(), bridge, context, WhiteSdk.this, playerTimeInfo, syncPlayerState);
                        playerCallbacksImplement.setPlayer(player);
                        playerPromise.then(player);
                    }
                }
            });
        } catch (AssertionError a) {
            throw a;
        } catch (Exception e) {
            playerPromise.catchEx(new SDKError(e.getMessage()));
        }
    }

    /**
     * 查看房间是否有回放数据。
     *
     * @since 2.11.0
     *
     * @param playerConfiguration 回放房间的配置，详见 {@link PlayerConfiguration PlayerConfiguration}。
     * @param playablePromise `Promise<Boolean>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `isPlayable` 的调用结果：
     *   - 如果方法调用成功，则返回 `true`。
     *   - 如果方法调用失败，则返回 `false`。
     *
     */
    public void isPlayable(final PlayerConfiguration playerConfiguration, final Promise<Boolean> playablePromise) {
        bridge.callHandler("sdk.isPlayable", new Object[]{playerConfiguration}, new OnReturnValue<Boolean>() {
            @Override
            public void onValue(Boolean retValue) {
                playablePromise.then(retValue);
            }
        });
    }

    /**
     * 声明在本地白板中可用的字体。
     *
     * 调用该方法声明的字体可用于显示 PPT 中的文字和教具输入的文字。
     *
     * 该方法和 {@link loadFontFaces loadFontFaces} 都可以声明在本地白板中可用的字体，区别是 `setupFontFaces` 没有回调，因为无法判断字体声明是否正确；`loadFontFaces` 会触发回调，报告每一种的预加载结果。
     *
     * @since 2.11.2
     *
     * @note
     * - 该方法只对本地白板生效，不影响远端白板的字体显示。
     * - 通过该方法声明的字体，只有当被使用时，才会触发下载。
     * - 不同的字体在不同设备上的渲染可能不同，例如，在某些设备上，要等字体加载完成后，才会渲染文字；而在另外一些设备上，会先使用默认的字体渲染文字，等指定的字体加载完毕后，再整体刷新。
     * - 每次调用该方法都会覆盖原来的字体声明。
     * - 请勿同时调用该方法和 `loadFontFaces` 方法。否则，无法预期行为。
     *
     * @param fontFaces `FontFace` 实例，详见 {@link FontFace FontFace}。
     *
     */
    public void setupFontFaces(FontFace[] fontFaces) {
        bridge.callHandler("sdk.updateNativeFontFaceCSS", new Object[]{fontFaces});
    }

    /**
     * 声明在本地白板中可用的字体并预加载。
     *
     * 调用该方法预加载的字体可以用于显示 PPT 中的文字和教具输入的文字。
     *
     * 该方法和 {@link loadFontFaces loadFontFaces} 都可以声明在本地白板中可用的字体，区别是 `setupFontFaces` 没有回调，因为无法判断字体声明是否正确；`loadFontFaces` 会触发回调，报告每一种的预加载结果。
     *
     * @since 2.11.2
     *
     * @note
     * - 该方法只对本地白板生效，不影响远端白板的字体显示。
     * - 使用该方法预加载的字体，只有当该字体被使用时，才会触发下载。
     * - 不同的字体在不同设备上的渲染可能不同，例如，在某些设备上，要等字体加载完成后，才会渲染文字；而在另外一些设备上，会先使用默认的字体渲染文字，等指定的字体加载完毕后，再整体刷新。
     * - 通过该方法预加载的字体无法删除，每次调用都会在原来的基础上新增。
     * - 请勿同时调用该方法和 `setupFontFaces` 方法。否则，无法预期行为。
     *
     * @param fontFaces `FontFace` 实例 ，详见 {@link FontFace FontFace}。
     * @param loadPromise `Promise<JSONObject>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `loadFontFaces` 的调用结果：
     *   - 如果方法调用成功，则返回 `FontFace` 对象
     *   - 如果方法调用失败，则返回错误信息。
     *
     * 每加载完成一种字体，会触发一个回调，报告该字体是否加载成功。传入的 `FontFace` 实例中有多少种字体，就会有多少个回调。
     *
     */
    public void loadFontFaces(FontFace[] fontFaces, final Promise<JSONObject>loadPromise) {
        bridge.callHandler("sdk.asyncInsertFontFaces", new Object[]{fontFaces}, new OnReturnValue<JSONObject>() {
            @Override
            public void onValue(JSONObject retValue) {
                loadPromise.then(retValue);
            }
        });
    }

    /**
     * 设置文字教具在本地白板中使用的字体。
     *
     * @since 2.11.2
     *
     * @note
     * - 该方法只对本地白板生效，不影响远端白板的字体显示。
     * - 该方法只能设置文字教具使用的字体，不能用于 PPT 中的文字显示。
     *
     * @param names 字体名称。如果用户系统中不存在该字体，则文字教具无法使用该字体。请确保你已经调用 `setupFontFaces` 或 `loadFontFaces` 将指定字体加载到本地白板中。
     *
     */
    public void updateTextFont(String[] names) {
        bridge.callHandler("sdk.updateNativeTextareaFont", new Object[]{names});
    }

    /**
     * 释放互动白板实时房间并删除 `RoomCallbacks` 回调。
     *
     * @since 2.4.12
     */
    public void releaseRoom() {
        roomCallbacksImplement.setListener(null);
    }

    /**
     * 释放互动白板实时房间并删除 `RoomCallbacks` 回调。
     *
     * @deprecated
     * 该方法已废弃。请改用 {@link releaseRoom() releaseRoom}。
     *
     * @param uuid 房间 UUID。该参数无实际意义，因为一个 WhiteSDK 实例只能对应一个实时房间，不需要使用 UUID 指定房间。
     */
    @Deprecated
    public void releaseRoom(String uuid) {
        releaseRoom();
    }

    /**
     * 释放回放房间并删除 `PlayerEventListener` 回调。
     *
     * @since 2.4.12
     */
    public void releasePlayer() {
        playerCallbacksImplement.setListener(null);
    }

    /**
     * 释放回放房间并删除 `PlayerEventListener` 回调。
     * @deprecated
     * 该方法已废弃。请改用 {@link releasePlayer() releasePlayer}。由于一个 WhiteSDK 实例，只能对应一个回放房间，所以不再需要使用 player uuid 进行定位
     *
     * @param uuid 回放房间的 UUID。该参数无实际意义，由于一个 WhiteSDK 实例只能对应一个回放房间，不需要使用 UUID 指定回放房间。
     */
    @Deprecated
    public void releasePlayer(String uuid) {
        releasePlayer();
    }

    //region SDK Callbacks

    @JavascriptInterface
    public String urlInterrupter(Object args) {
        if (this.commonCallbacks != null) {
            return this.commonCallbacks.urlInterrupter(String.valueOf(args));
        } else if (this.urlInterrupter == null) {
            return String.valueOf(args);
        }
        return this.urlInterrupter.urlInterrupter(String.valueOf(args));
    }

    @JavascriptInterface
    public void throwError(Object args) {
        Logger.info("WhiteSDK JS error: " + gson.fromJson(String.valueOf(args), Map.class));
        if (this.commonCallbacks != null) {
            this.commonCallbacks.throwError(args);
        }
    }

    @JavascriptInterface
    public void logger(Object args) {
        Logger.info("WhiteSDK logger: " + gson.fromJson(String.valueOf(args), Map.class));
    }

    @JavascriptInterface
    public void postMessage(Object args) {
        if (this.commonCallbacks != null) {
            try {

                JSONObject object = new JSONObject((String) args);
                this.commonCallbacks.onMessage(object);
            } catch (Throwable throwable) {

            }
        }
    }

    @JavascriptInterface
    public void onPPTMediaPlay(Object args) {
        if (this.commonCallbacks != null) {
            this.commonCallbacks.onPPTMediaPlay();
        }
    }

    @JavascriptInterface
    public void onPPTMediaPause(Object args) {
        if (this.commonCallbacks != null) {
            this.commonCallbacks.onPPTMediaPause();
        }
    }

    @JavascriptInterface
    public void setupFail(Object object) {
        if (this.commonCallbacks != null && object instanceof JSONObject) {
            SDKError sdkError = SDKError.parseError((JSONObject) object);
            this.commonCallbacks.sdkSetupFail(sdkError);
        }
    }
    //endregion
}