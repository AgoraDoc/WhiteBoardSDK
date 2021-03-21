package com.herewhite.sdk;

import android.content.Context;
import androidx.annotation.Nullable;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.herewhite.sdk.domain.AkkoEvent;
import com.herewhite.sdk.domain.BroadcastState;
import com.herewhite.sdk.domain.CameraConfig;
import com.herewhite.sdk.domain.EventEntry;
import com.herewhite.sdk.domain.EventListener;
import com.herewhite.sdk.domain.FrequencyEventListener;
import com.herewhite.sdk.domain.GlobalState;
import com.herewhite.sdk.domain.ImageInformation;
import com.herewhite.sdk.domain.ImageInformationWithUrl;
import com.herewhite.sdk.domain.MemberState;
import com.herewhite.sdk.domain.Promise;
import com.herewhite.sdk.domain.RoomMember;
import com.herewhite.sdk.domain.RoomPhase;
import com.herewhite.sdk.domain.RoomState;
import com.herewhite.sdk.domain.SDKError;
import com.herewhite.sdk.domain.Scene;
import com.herewhite.sdk.domain.SceneState;
import com.herewhite.sdk.domain.ViewMode;

import org.json.JSONObject;
import java.util.UUID;

import wendu.dsbridge.OnReturnValue;

/**
 * `Room` 类，用于操作互动白板实时房间。
 */
public class Room extends Displayer {

    private final SyncDisplayerState<RoomState> syncRoomState;
    private RoomPhase roomPhase = RoomPhase.connected;

    void setDisconnectedBySelf(Boolean disconnectedBySelf) {
        this.disconnectedBySelf = disconnectedBySelf;
    }

    /**
     * 获取用户是否主动断开与白板房间的连接。
     *
     * 该方法可以避免白板 SDK 反复重连，用户不断重新加入房间。
     */
    public Boolean getDisconnectedBySelf() {
        return disconnectedBySelf;
    }

    private Boolean disconnectedBySelf = false;

    /**
     * 获取当前实时房间是否为互动模式。
     *
     * @return 获取当前实时房间是否为互动模式：
     * - `true`：当前实时房间为互动模式
     * - `false`: 当前实时房间为为订阅模式。// TODO 所有加入该房间的用户都只能进行读取操作？
     */
    public Boolean getWritable() {
        return writable;
    }

    void setWritable(Boolean writable) {
        this.writable = writable;
    }

    private Boolean writable;
    private Integer timeDelay;
    private Long observerId;

    /**
     * 文档中隐藏，只有 sdk 内部初始化才有意义
     */
    public Room(String uuid, WhiteboardView bridge, Context context, WhiteSdk sdk, SyncDisplayerState<RoomState> syncRoomState) {
        super(uuid, bridge, context, sdk);
        this.timeDelay = 0;
        this.syncRoomState = syncRoomState;
    }

    SyncDisplayerState<RoomState> getSyncRoomState() {
        return syncRoomState;
    }

    void setRoomPhase(RoomPhase roomPhase) {
        this.roomPhase = roomPhase;
    }

    /**
     * 获取用户在当前互动白板实时房间中的用户 ID。
     *
     * @since 2.4.11
     *
     * @see getMemberId()
     *
     * @return 用户 ID。
     */
    public Long getObserverId() {
        return observerId;
    }

    void setObserverId(Long observerId) {
        this.observerId = observerId;
    }

    //region Set API
    /**
     * 修改互动白板实时房间的公共全局状态。
     *
     * 实时房间的 `globalState` 属性为公共全局变量，房间内所有用户看到的都是相同的 `globalState`，所有互动模式用户都可以读写。修改 `globalState` 属性会立即生效并同步给所有用户。
     *
     * @param globalState 房间公共全局状态，自定义字段，可以传入 {@link GlobalState GlobalState} 子类。// TODO {@link GlobalState GlobalState} 子类是什么？{@link GlobalState GlobalState} 类下面什么都没有。
     */
    public void setGlobalState(GlobalState globalState) {
        syncRoomState.putDisplayerStateProperty("globalState", globalState);
        bridge.callHandler("room.setGlobalState", new Object[]{globalState});
    }

    /**
     * 修改房间内的教具状态。
     *
     * 调用该方法会立刻更新房间的 {@link MemberState MemberState}。
     * 你可以调用 {@link getMemberState() getMemberState} 获取最新设置的教具状态。
     *
     * @param memberState 需要修改的教具状态，详见 {@link MemberState MemberState}。
     */
    public void setMemberState(MemberState memberState) {
        syncRoomState.putDisplayerStateProperty("memberState", memberState);
        bridge.callHandler("room.setMemberState", new Object[]{memberState});
    }

    //region operation


    /**
     * 复制选中内容。
     *
     * @since 2.9.3
     *
     * 该方法会将选中的内容存储到内存中，不会粘贴到白板中。
     *
     * @note
     * 该方法仅当 {@link disableSerialization disableSerialization} 设为 `false` 时生效。
     */
    public void copy() {
        bridge.callHandler("room.sync.copy", new Object[]{});
    }

    /**
     * 粘贴复制的内容。
     *
     * @since 2.9.3
     *
     * 该方法会将 {@link copy copy} 方法复制的内容粘贴到白板中（用户当前的视野中间）。
     *
     * @note
     * - 该方法仅当 {@link disableSerialization disableSerialization} 设为 `false` 时生效。
     * // TODO Web 的 copy、paste、duplicate 方法有这条限制，Android 和 iOS 的也是吗？还有其他方法有这条限制吗？
     * - 多次调用该方法时，不能保证粘贴的内容每次都在用户当前的视野中间，可能会出现随机偏移。
     */
    public void paste() {
        bridge.callHandler("room.sync.paste", new Object[]{});
    }

    /**
     * 复制并粘贴选中的内容。
     *
     * @since 2.9.3
     *
     * 该方法会将选中的内容复制并粘贴到白板中（用户当前的视野中间）。
     *
     * @note
     * - 该方法仅当 {@link disableSerialization disableSerialization} 设为 `false` 时生效。
     * - 多次调用该方法时，不能保证粘贴的内容每次都在用户当前的视野中间，可能会出现随机偏移。
     */
    public void duplicate() {
        bridge.callHandler("room.sync.duplicate", new Object[]{});
    }

    /**
     * 删除选中的内容。
     *
     * @since 2.9.3
     */
    public void deleteOperation() {
        bridge.callHandler("room.sync.delete", new Object[]{});
    }

    /**
     * 开启/禁止本地序列化。
     *
     * @since 2.9.3
     *
     * 设置 `disableSerialization(true)` 后，以下方法将不生效：// TODO 以下这些方法是可以调用，但是不生效，还是调用时会报错？
     * - `redo`
     * - `undo`
     * - `duplicate`
     * - `copy`
     * - `paste`
     *
     * @warning
     * 如果你使用以下版本的白板 SDK，请勿调用该方法，否则会导致 app 客户端崩溃。(房间内有任何一个用户使用以下版本的白板 SDK，都请勿调用该方法，否则会到 app 客户端崩溃。)
     * - Web 2.9.2 以下版本
     * - Android 2.9.3 以下版本
     * - iOS 2.9.3 以下版本
     *
     * @param disable 是否禁止本地序列化：
     * - `true`：（默认）禁止开启本地序列化；
     * - `false`： 开启本地序列化，即可以对本地操作进行解析。
     */
    public void disableSerialization(boolean disable) {
        bridge.callHandler("room.sync.disableSerialization", new Object[]{disable});
    }

    /**
     * 重做，即回退撤销操作。
     *
     * @since 2.9.3
     *
     * @note
     * 该方法仅当 {@link disableSerialization disableSerialization} 设为 `false` 时生效。
     *
     */
    public void redo() {
        bridge.callHandler("room.redo", new Object[]{});
    }

    /**
     * 撤销上一步操作。
     *
     * @since 2.9.3
     *
     * @note
     * 该方法仅当 {@link disableSerialization disableSerialization} 设为 `false` 时生效。
     */
    public void undo() {
        bridge.callHandler("room.undo", new Object[]{});
    }
    //endregion

    /**
     * 切换视角模式。
     *
     * 互动白板实时房间支持对用户设置以下视角：
     * - `Broadcaster`: 主播模式。
     * - `Follower`：跟随模式。
     * - `Freedom'：（默认）自由模式。
     *
     * 该方法的设置会影响房间内所有用户的视角：
     * - 当房间内不存在视角为主播模式的用户时，所有用户的视角都默认为自由模式。
     * - 当一个用户的视角设置为主播模式后，房间内其他所有用户（包括新加入房间的用户）的视角会被自动设置为跟随模式。
     * - 当跟随模式的用户进行白板操作时，其视角会自动切换为自由模式。你可以调用 {@link disableOperations(boolean) disableOperations}(true) 禁止跟随模式的用户操作白板，以保证其保持跟随模式。
     *
     *
     * 切换视角后，需要等待服务器更新 `BroadcastState` 属性，才能通过 {@link getBroadcastState() getBroadcastState} 获取最新设置的视角模式。
     * 你可以使用 {@link getBroadcastState(Promise) getBroadcastState} 强制更新信息。// TODO 不是很理解，怎么强制更新？
     *
     * @param viewMode 视角模式，详见 {@link ViewMode ViewMode}。
     */
    public void setViewMode(ViewMode viewMode) {
        bridge.callHandler("room.setViewMode", new Object[]{viewMode.name()});
    }

    //endregion

    /**
     * 主动断开与互动白板实时房间实例的连接。
     *
     * 该方法会把与当前房间实例相关的所有资源释放掉。如果要再次加入房间，需要重新调用 `joinRoom`。
     *
     * @note
     * 调用该方法不会触发回调。如果需要收到断开连接的回调，请使用 {@link disconnect(Promise) disconnect}。
     */
    public void disconnect() {
        disconnect(null);
    }

    /**
     * 主动断开与互动白板实时房间实例的连接。
     *
     * 该方法会把与当前房间实例相关的所有资源释放掉。如果要再次加入房间，需要重新调用 `joinRoom`。
     *
     * 你可以在该方法中传入 'Promise<Object>' 接口实例，以获取方法调用结果。
     *
     * @param promise 'Promise<Object>' 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `disconnect` 的调用结果：
     * - 如果方法调用成功，则返回房间对象。// TODO 方法调用成功，返回什么？
     * - 如果方法调用失败，则返回错误码。// TODO 方法调用失败，是返回错误码，还是错误信息？
     */
    public void disconnect(@Nullable final Promise<Object> promise) {
        setDisconnectedBySelf(true);
        bridge.callHandler("room.disconnect", new Object[]{}, new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                if (promise == null) {
                    return;
                }
                try {
                    promise.then(gson.fromJson(String.valueOf(o), GlobalState.class));
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from disconnect", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in disconnect promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }
    //region image
    /**
     * 插入图片显示区域
     *
     * SDK 会根据你传入的 `ImageInformation` 在白板上设置并插入图片的显示区域。
     * 调用该方法后，还需要调用 {@link completeImageUpload(String, String) completeImageUpload} 传入图片的 Url 地址，以在该显示区域插入并展示图片。
     *
     * @note
     * 你也可以调用 {@link insertImage(imageInformationWithUrl) insertImage} 方法同时传入图片信息和图片的 Url 地址，在白板中插入并展示图片。
     *
     * @param imageInfo 图片信息，详见 {@link ImageInformation ImageInformation}。
     */
    public void insertImage(ImageInformation imageInfo) {
        bridge.callHandler("room.insertImage", new Object[]{imageInfo});
    }

    /**
     * 展示图片。
     *
     * 该方法可以将指定的网络图片展示到指定的图片显示区域。
     *
     * @note
     * 调用该方法前，请确保你已经调用 {@link insertImage(imageInfo) insertImage} 方法在白板上插入了图片的显示区域。
     *
     * @param uuid 图片显示区域的 UUID, 即在 {@link insertImage(ImageInformation) insertImage} 方法的 {@link ImageInformation ImageInformation} 中传入的图片 UUID。
     * @param url  图片的 URL 地址。必须确保 app 客户端能访问该 URL，否则无法正常展示图片。
     */
    public void completeImageUpload(String uuid, String url) {
        bridge.callHandler("room.completeImageUpload", new Object[]{uuid, url});
    }

    /**
     * 插入并展示图片
     *
     * 该方法封装了 {@link insertImage(imageInfo) insertImage} 和 {@link completeImageUpload(String, String) completeImageUpload} 方法。
     * 你可以在该方法中同时传入图片信息和图片的 URL，直接在白板中插入图片的显示区域并展示图片。
     *
     * @param imageInformationWithUrl 图片信息及图片的 URL 地址，详见 {@link ImageInformationWithUrl ImageInformationWithUrl}。
     */
    public void insertImage(ImageInformationWithUrl imageInformationWithUrl) {
        ImageInformation imageInformation = new ImageInformation();
        String uuid = UUID.randomUUID().toString();
        imageInformation.setUuid(uuid);
        imageInformation.setCenterX(imageInformationWithUrl.getCenterX());
        imageInformation.setCenterY(imageInformationWithUrl.getCenterY());
        imageInformation.setHeight(imageInformationWithUrl.getHeight());
        imageInformation.setWidth(imageInformationWithUrl.getWidth());
        this.insertImage(imageInformation);
        this.completeImageUpload(uuid, imageInformationWithUrl.getUrl());
    }
    //endregion

    //region GET API
    /**
     * 获取房间的全局状态。该方法为同步调用。
     *
     * @since 2.4.0
     *
     * @note
     * - 对于通过 {@link com.herewhite.sdk.domain.WhiteDisplayerState#setCustomGlobalStateClass(Class) setCustomGlobalStateClass} 方法设置的自定义 `GlobalState`，在获取后，可以直接进行强转。
     * - 调用 {@link #setGlobalState(GlobalState)} 方法后，可以立刻调用该方法。
     *
     * @return 房间的全局状态，详见 {@link GlobalState GlobalState}。
     */
    public GlobalState getGlobalState() {
        return syncRoomState.getDisplayerState().getGlobalState();
    }

    /**
     * 强制获取房间全局状态。该方法为异步调用。// TODO 这里为什么是强制获取？
     *
     * @deprecated 该方法已废弃。请使用 {@link #getGlobalState()}。
     *
     * @note
     * 对于通过 {@link com.herewhite.sdk.domain.WhiteDisplayerState#setCustomGlobalStateClass(Class) setCustomGlobalStateClass} 方法设置的自定义 `GlobalState`，在获取后，可以直接进行强转。
     *
     * @param promise `Promise<GlobalState>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `getGlobalState` 的调用结果：
     * - 如果方法调用成功，则返回 `GlobalState` 对象。
     * - 如果方法调用失败，则返回错误码。
     */
    public void getGlobalState(final Promise<GlobalState> promise) {
        getGlobalState(GlobalState.class, promise);
    }

    /**
     * 异步API 获取房间全局状态，根据传入的 Class 类型，在回调中返回对应的实例
     *
     * @param <T>      globalState 反序列化的类
     * @param classOfT 泛型 T 的 class 类型
     * @param promise  完成回调，其中返回值传入的 class 的实例
     * @since 2.4.8
     */
    private  <T>void getGlobalState(final Class<T> classOfT, final Promise<T> promise) {
        bridge.callHandler("room.getGlobalState", new Object[]{}, new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
            T customState = null;
            try {
                customState = gson.fromJson(String.valueOf(o), classOfT);
            } catch (AssertionError a) {
                throw a;
            } catch (Throwable e) {
                Logger.error("An exception occurred while parse json from getGlobalState for customState", e);
                promise.catchEx(new SDKError((e.getMessage())));
            }
            if (customState == null) {
                return;
            }
            try {
                promise.then(customState);
            } catch (AssertionError a) {
                throw a;
            } catch (JsonSyntaxException e) {
                Logger.error("An JsonSyntaxException occurred while parse json from getGlobalState", e);
                promise.catchEx(new SDKError(e.getMessage()));
            } catch (Throwable e) {
                Logger.error("An exception occurred in getGlobalState promise then method", e);
                promise.catchEx(new SDKError(e.getMessage()));
            }
            }
        });
    }

    /**
     * 获取当前用户的教具状态。
     *
     * @since 2.4.0
     *
     * @note
     * - 该方法为同步调用。
     * - 调用 {@link #setMemberState(MemberState)} 方法后，可以立即调用 {@link getMemberState() getMemberState} 获取最新的教具状态。
     *
     * @return 用户教具状态，详见 {@link MemberState}。
     */
    public MemberState getMemberState() {
        return syncRoomState.getDisplayerState().getMemberState();
    }

    /**
     * 获取当前用户的教具状态。
     *
     * @note
     * 该方法为异步调用。
     *
     * @param promise `Promise<MemberState>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `getMemberState` 的调用结果：
     * - 如果方法调用成功，则返回用户教具状态，详见 {@link MemberState MemberState}。
     * - 如果方法调用失败，则返回错误码。
     *
     */
    public void getMemberState(final Promise<MemberState> promise) {
        bridge.callHandler("room.getMemberState", new OnReturnValue<String>() {
            @Override
            public void onValue(String o) {
                try {
                    promise.then(gson.fromJson(String.valueOf(o), MemberState.class));
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from getMemberState", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in getMemberState promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }

    /**
     * 获取实时房间用户列表。
     *
     * @note
     * - 该方法为同步调用。
     * - 房间的用户列表仅包含互动模式（具有读写权限）的用户，不包含订阅模式（只读权限）的用户。
     *
     * @return 用户列表，详见 {@link RoomMember RoomMember}。
     */
    public RoomMember[] getRoomMembers() {
        return syncRoomState.getDisplayerState().getRoomMembers();
    }

    /**
     * 获取实时房间用户列表。
     *
     * @note
     * - 该方法为异步调用。
     * - 房间的用户列表仅包含互动模式（具有读写权限）的用户，不包含订阅模式（只读权限）的用户。
     *
     * @param promise `Promise<RoomMember[]>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `getRoomMembers` 的调用结果：
     * - 如果方法调用成功，则返回用户列表，详见 {@link RoomMember RoomMember}。
     * - 如果方法调用失败，则返回错误码。
     */
    public void getRoomMembers(final Promise<RoomMember[]> promise) {
        bridge.callHandler("room.getRoomMembers", new Object[]{}, new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                try {
                    promise.then(gson.fromJson(String.valueOf(o), RoomMember[].class));
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from getRoomMembers", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in getRoomMembers promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }

    /**
     * 获取用户视角状态。
     *
     * @since 2.4.0
     *
     * @note
     * - 该方法为同步调用。
     * - 调用 {@link #setViewMode(ViewMode)} 修改用户视角后，无法立刻通过 {@link getBroadcastState getBroadcastState} 获取最新的用户视角状态。
     * 如果需要立即获取最新的用户视角状态，可以调用 {@link #getBroadcastState(Promise)}。
     *
     * @return 用户视角状态，详见 {@link BroadcastState BroadcastState}。
     */
    public BroadcastState getBroadcastState() {
        return syncRoomState.getDisplayerState().getBroadcastState();
    }

    /**
     * 获取用户视角状态。
     *
     * @note
     * - 该方法为异步调用。
     * - 调用 {@link #setViewMode(ViewMode)} 修改用户视角后，无法立刻通过 {@link getBroadcastState getBroadcastState} 获取最新的用户视角状态。如果需要
     * 立即获取最新的用户视角状态，可以调用 {@link #getBroadcastState(Promise)}。
     *
     * @param promise `Promise<BroadcastState>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `getBroadcastState` 的调用结果：
     * - 如果方法调用成功，则返回用户视角状态，详见 {@link BroadcastState BroadcastState}。
     * - 如果方法调用失败，则返回错误码。
     */
    public void getBroadcastState(final Promise<BroadcastState> promise) {
        bridge.callHandler("room.getBroadcastState", new Object[]{}, new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                try {
                    promise.then(gson.fromJson(String.valueOf(o), BroadcastState.class));
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from getBroadcastState", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in getBroadcastState promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }

    /**
     * 获取房间当前场景组下的场景状态。
     *
     * @since 2.4.0
     *
     * @note
     * - 该方法为同步调用。
     * - 调用以下方法修改或新增场景后，无法通过 {@link getSceneState() getSceneState} 立即获取最新的场景状态。此时，如果需要立即获取最新的场景状态，可以调用 {@link #getSceneState(Promise)}。
     *  - {@link #setScenePath(String, Promise)}
     *  - {@link #setScenePath(String)}
     *  - {@link #putScenes(String, Scene[], int)}
     *
     * @return 当前场景组下的场景状态，详见 {@link SceneState SceneState}。
     */
    public SceneState getSceneState() {
        return syncRoomState.getDisplayerState().getSceneState();
    }

    /**
     * 获取房间当前场景组下的场景状态。
     *
     * @note
     * - 该方法为异步调用。
     * - 调用以下方法修改或新增场景后，你可以通过 {@link #getSceneState(Promise)} 立即获取最新的场景状态。
     *  - {@link #setScenePath(String, Promise)}
     *  - {@link #setScenePath(String)}
     *  - {@link #putScenes(String, Scene[], int)}
     *
     * @param promise `Promise<SceneState>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `getSceneState` 的调用结果：
     * - 如果方法调用成功，则返回场景状态，详见 {@link SceneState SceneState}。
     * - 如果方法调用失败，则返回错误码。
     */
    public void getSceneState(final Promise<SceneState> promise) {
        bridge.callHandler("room.getSceneState", new Object[]{}, new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                try {
                    promise.then(gson.fromJson(String.valueOf(o), SceneState.class));
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from getSceneState", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in getSceneState promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }

    /**
     * 获取房间当前场景组下的场景列表。
     *
     * @since 2.4.0
     *
     * @note
     * - 该方法为同步调用。
     * - 调用以下方法修改或新增场景后，无法通过 {@link getScenes() getScenes} 立即获取最新的场景列表。此时，如果需要立即获取最新的场景列表，可以调用 {@link #getScenes(Promise)}。
     *  - {@link #setScenePath(String, Promise)}
     *  - {@link #setScenePath(String)}
     *  - {@link #putScenes(String, Scene[], int)}
     *
     * @return 当前场景组下的场景列表，详见 {@link Scene Scene}。
     */
    public Scene[] getScenes() {
        return this.getSceneState().getScenes();
    }

    /**
     * 获取房间当前场景组下的场景列表。
     *
     * @note
     * - 该方法为异步调用。
     * - 调用以下方法修改或新增场景后，可以调用 {@link #getScenes(Promise)}，立即获取最新的场景列表。
     *  - {@link #setScenePath(String, Promise)}
     *  - {@link #setScenePath(String)}
     *  - {@link #putScenes(String, Scene[], int)}
     *
     * @param promise `Promise<Scene[]>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `getScenes` 的调用结果：
     * - 如果方法调用成功，则返回场景列表，详见 {@link Scene Scene}。
     * - 如果方法调用失败，则返回错误码。
     */
    public void getScenes(final Promise<Scene[]> promise) {
        bridge.callHandler("room.getScenes", new Object[]{}, new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                try {
                    promise.then(gson.fromJson(String.valueOf(o), Scene[].class));
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from getScenes", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in getScenes promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }


    /**
     * 获取当前用户的视野缩放比例。
     *
     * @since 2.4.0
     *
     * // TODO 是不是已经废弃了？ Web 的已经废弃。改用什么？
     *
     * @note
     * - 该方法为同步调用。
     * - 调用 {@link #zoomChange(double)} 或 {@link #moveCamera(CameraConfig)} 调整视野缩放比例后，无法通过 {@link getZoomScale() getZoomScale} 立即获取最新的缩放比例。此时，如果需要立即获取最新的缩放比例，可以调用 {@link #getZoomScale(Promise)}。
     *
     * @return 视野缩放比例。
     */
    public double getZoomScale() {
        return syncRoomState.getDisplayerState().getZoomScale();
    }

    /**
     * 获取当前用户的视野缩放比例。
     *
     * // TODO 是否已废弃？改用什么？
     * @note
     * - 该方法为异步调用。
     * - 调用 {@link #zoomChange(double)} 或 {@link #moveCamera(CameraConfig)} 调整视野缩放比例后，如果需要立即获取最新的缩放比例，可以调用 {@link #getZoomScale(Promise)}。
     *
     * @param promise `Promise<Number>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `getZoomScale` 的调用结果：
     * - 如果方法调用成功，则返回视野缩放比例。
     * - 如果方法调用失败，则返回错误码。
     */
    public void getZoomScale(final Promise<Number> promise) {
        bridge.callHandler("room.getZoomScale", new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                try {
                    promise.then(gson.fromJson(String.valueOf(o), Number.class));
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from getZoomScale", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in getZoomScale promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }

    /**
     * 获取房间连接状态。
     *
     * @since 2.4.0
     *
     * @note
     * - 该方法为同步调用。
     * - 调用 {@link #disconnect()} 或 {@link #disconnect(Promise)} 断开 SDK 与实时房间的连接后，无法立即通过 {@link getRoomPhase() getRoomPhase} 获取最新的房间连接状态。
     * 此时，你可以调用 {@link #getRoomPhase()} 立即获取最新的房间连接状态。
     *
     * @return 房间的连接状态，详见 {@link RoomPhase RoomPhase}。
     */
    public RoomPhase getRoomPhase() {
        return this.roomPhase;
    }

    /**
     * 获取房间连接状态。
     *
     * @note
     * - 该方法为异步调用。
     * - 调用 {@link #disconnect()} 或 {@link #disconnect(Promise)} 断开 SDK 与实时房间的连接后，无法立即通过 {@link getRoomPhase() getRoomPhase} 获取最新的房间连接状态。
     * 此时，你可以调用 {@link #getRoomPhase()} 立即获取最新的房间连接状态。
     *
     * @param promise `Promise<RoomPhase>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `getRoomPhase` 的调用结果：
     * - 如果方法调用成功，则返回房间连接状态，详见 {@link RoomPhase RoomPhase}。
     * - 如果方法调用失败，则返回错误码。
     */
    public void getRoomPhase(final Promise<RoomPhase> promise) {
        bridge.callHandler("room.getRoomPhase", new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                try {
                    promise.then(RoomPhase.valueOf(String.valueOf(o)));
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from getRoomPhase", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in getRoomPhase promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }

    /**
     * 获取实时房间所有状态。
     *
     * @since 2.4.0
     *
     * @note
     * - 该方法为同步调用。
     * - 修改房间的状态属性后，无法立即通过 {@link getRoomState() getRoomState} 获取最新的房间状态。此时，如果需要立即获取最新的房间状态，可以调用 {@link #getRoomState(Promise)} 获取。
     *
     * @return 房间当前的所有状态，详见 {@link RoomState RoomState}。
     */
    public RoomState getRoomState() {
        return syncRoomState.getDisplayerState();
    }

    /**
     * 获取实时房间所有状态。
     *
     * @note
     * - 该方法为同步调用。
     * - 修改房间的状态属性后，无法立即通过 {@link getRoomState() getRoomState} 获取最新的房间状态。此时，如果需要立即获取最新的房间状态，可以调用 {@link #getRoomState(Promise)} 获取。
     *
     * @param promise `Promise<RoomState>` 接口实例，详见 {@link Promise<T> Promise<T>}。你可以通过该接口获取 `getRoomState` 的调用结果：
     * - 如果方法调用成功，则返回房间所有状态，详见 {@link RoomState RoomState}。
     * - 如果方法调用失败，则返回错误码。
     */
    public void getRoomState(final Promise<RoomState> promise) {
        bridge.callHandler("room.state.getDisplayerState", new OnReturnValue<Object>() {
            @Override
            public void onValue(Object o) {
                try {
                    promise.then(gson.fromJson(String.valueOf(o), RoomState.class));
                } catch (AssertionError a) {
                    throw a;
                } catch (JsonSyntaxException e) {
                    Logger.error("An JsonSyntaxException occurred while parse json from getDisplayerState", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                } catch (Throwable e) {
                    Logger.error("An exception occurred in getDisplayerState promise then method", e);
                    promise.catchEx(new SDKError(e.getMessage()));
                }
            }
        });
    }
    //endregion

    //region Scene API
    /**
     * 切换至特定的场景,如需同时获取报错，或完成回调，请使用 {@link #setScenePath(String, Promise)}
     *
     * 所有人都会同时切换到对应场景中
     *
     * 切换失败的几种原因：
     *  1. 路径不合法，请确定场景路径的定义。（以 "/" 开头）
     *  2. 场景路径，对应的场景不存在。
     *  3. 传入的地址，是场景目录，而不是场景路径。
     *
     * @param path 想要切换的场景 的场景路径(场景目录+场景名）
     */
    public void setScenePath(String path) {
        bridge.callHandler("room.setScenePath", new Object[]{path});
    }

    /**
     * 切换至特定的场景
     *
     * 所有人都会同时切换到对应场景中
     *
     * 切换失败的几种原因：
     *  1. 路径不合法，请确定场景路径的定义。（以 "/" 开头）
     *  2. 场景路径，对应的场景不存在。
     *  3. 传入的地址，是场景目录，而不是场景路径。
     *
     * @param path 想要切换的场景 的场景目录
     * @param promise 完成回调，如果出错会进入 catchEx
     */
    public void setScenePath(String path, final Promise<Boolean> promise) {
        bridge.callHandler("room.setScenePath", new Object[]{path}, new OnReturnValue<String>() {
            @Override
            public void onValue(String result) {
                SDKError sdkError = SDKError.promiseError(result);
                if (sdkError != null) {
                    promise.catchEx(sdkError);
                } else {
                    promise.then(true);
                }
            }
        });
    }

    /**
     * 在当前场景目录中，切换当前场景。
     *
     * 当 index 超出当前目录的场景数，会报错，进入 promise 错误回调
     *
     * @param index 目标场景在当前场景目录下的 index。
     * @param promise 设置完后回调
     */
    public void setSceneIndex(Integer index, @Nullable final Promise<Boolean> promise) {
        bridge.callHandler("room.setSceneIndex", new Object[]{index}, new OnReturnValue<String>() {
            @Override
            public void onValue(String result) {
                if (promise == null) {
                    return;
                }
                SDKError sdkError = SDKError.promiseError(result);
                if (sdkError != null) {
                    promise.catchEx(sdkError);
                } else {
                    promise.then(true);
                }
            }
        });
    }

    /**
     * 插入场景API，该 API 并不会自动切换到对应场景
     *
     * 向特定场景目录中，插入多个场景。
     * 插入场景后，如果要将显示插入的场景，需要调用 {@link #setScenePath(String)} API，设置当前插入场景。
     *
     * <pre>
     * {@code
     * room.putScenes("ppt", new Scene[]{new Scene("page1", new PptPage("https://white-pan.oss-cn-shanghai.aliyuncs.com/101/image/alin-rusu-1239275-unsplash_opt.jpg", 1024d, 768d))}, 0);
     * room.setScenePath("ppt" + "/page1");
     * }
     * </pre>
     *
     * @param dir    场景目录，不能为场景路径（不能向文件中插入文件）
     * @param scenes 插入的场景数组，单个场景为 {@link Scene}
     * @param index  插入的场景数组中，第一个场景在该目录中的索引位置；填写的数字，超出该场景目录中已有场景的个数时，
     *               会排在最后。
     */
    public void putScenes(String dir, Scene[] scenes, int index) {
        bridge.callHandler("room.putScenes", new Object[]{dir, scenes, index});
    }

    /**
     * 移动/重命名场景
     *
     * 当移动的当前场景目录时，当前场景路径也会自动改变。
     * targetDirOrPath 情况：
     *  1. 目录：将 sourcePath 场景 移动至该目录中，场景名称不变。
     *  2. 场景路径：将 sourcePath 场景，移动到该场景路径对应的目录中，并将 sourcePath 场景改名。
     *
     * @param sourcePath 需要移动的场景路径(只接受场景路径，无法移动目录)
     * @param targetDirOrPath 场景目录或场景路径
     */
    public void moveScene(String sourcePath, String targetDirOrPath) {
        bridge.callHandler("room.moveScene", new Object[]{sourcePath, targetDirOrPath});
    }

    /**
     * 移除场景或者场景组。房间中至少会存在一个场景。删除时，会自动清理不存在任何场景的场景目录。
     *
     * 1. 删光房间内的场景：自动生成一个名为 init，场景路径为 "/init" 的初始场景（房间初始化时的默认场景）
     * 2. 删除当前场景：场景会指向被删除场景同级目录中后一个场景（即 index 不发生改变）。
     * 3. 删除包含当前场景的场景目录 dirA：向上递归，寻找场景目录同级的场景目录。
     *      3.1 如果上一级目录中，还有其他场景目录 dirB（可映射文件夹概念），排在被删除的场景目录 dirA 后面，则当前场景会变成
     *      dirB 中的第一个场景（index 为 0）；
     *      3.2 如果上一级目录中，在 dirA 后不存在场景目录，则查看当前目录是否存在场景；
     *          如果存在，则该场景成为当前目录（index 为 0 的场景目录）。
     *      3.3 如果上一级目录中，dirA 后没有场景目录，当前上一级目录，也不存在任何场景；
     *          则查看是否 dirA 前面是否存在场景目录 dirC，选择 dir C 中的第一顺位场景
     *      3.4 以上都不满足，则继续向上递归执行该逻辑。
     *
     * @param dirOrPath 场景目录，或者场景路径。传入目录会删除目录下所有场景。
     */
    public void removeScenes(String dirOrPath) {
        bridge.callHandler("room.removeScenes", new Object[]{dirOrPath});
    }

    /**
     * 清屏 API，清理当前场景的所有内容
     *
     * @param retainPpt 是否保留 ppt 内容。true:保留 ppt；false：连 ppt 一起清空。
     */
    public void cleanScene(boolean retainPpt) {
        bridge.callHandler("room.cleanScene", new Object[]{retainPpt});
    }
    //endregion

    //region PPT
    /**
     * 动态 PPT 下一步操作。当前 ppt 页面的动画已全部执行完成时，会进入下一页 ppt 页面（场景）
     * @since 2.2.0
     */
    public void pptNextStep() {
        bridge.callHandler("ppt.nextStep", new Object[]{});
    }

    /**
     * 动态 PPT 上一步操作。当前 ppt 页面的动画全部回退完成时，会回滚至上一页 ppt 页面（场景）
     * @since 2.2.0
     */
    public void pptPreviousStep() {
        bridge.callHandler("ppt.previousStep", new Object[]{});
    }
    //endregion

    /**
     * 改变房间缩放比例
     * @deprecated 使用 {@link #moveCamera(CameraConfig)} 调整缩放比例，新 API 同时支持动画选项
     * @param scale 缩放比例，2x 表示内容放大两倍。
     */
    @Deprecated
    public void zoomChange(double scale) {
        CameraConfig config = new CameraConfig();
        config.setScale(scale);
        this.moveCamera(config);
    }

    /**
     * 返回 debug 用信息
     * @param promise
     * @since 2.6.2
     */
    public void debugInfo(final Promise<JSONObject> promise) {
        bridge.callHandler("room.state.debugInfo", new OnReturnValue<JSONObject>() {
            @Override
            public void onValue(JSONObject retValue) {
                promise.then(retValue);
            }
        });
    }


    //region Disable
    /**
     * 禁止操作，不响应用户任何操作。
     *
     * @param disableOperations true:不响应用户操作；false:响应用户操作。默认:false。
     */
    public void disableOperations(final boolean disableOperations) {
        disableCameraTransform(disableOperations);
        disableDeviceInputs(disableOperations);
    }

    /**
     * 设置读写模式
     * @param writable 是否可写
     * @param promise 完成回调，并同时返回房间的读写状态
     * @since 2.6.1
     */
    public void setWritable(final boolean writable, @Nullable final Promise<Boolean> promise) {
        bridge.callHandler("room.setWritable", new Object[]{writable}, new OnReturnValue<String>() {
            @Override
            public void onValue(String result) {
                SDKError sdkError = SDKError.promiseError(result);
                if (promise == null) {
                    return;
                }

                if (sdkError != null) {
                    promise.catchEx(sdkError);
                } else {
                    JsonObject jsonObject = gson.fromJson(result, JsonObject.class);
                    Boolean isWritable = jsonObject.get("isWritable").getAsBoolean();
                    Long ObserverId = jsonObject.get("observerId").getAsLong();
                    setWritable(isWritable);
                    setObserverId(ObserverId);
                    promise.then(isWritable);
                }
            }
        });
    }

    /**
     * 中途切换橡皮对图片擦除的逻辑
     * @param disable true 时，禁止擦除橡皮；false 时允许擦除。
     * @since 2.9.3
     */
    public void disableEraseImage(boolean disable) {
        bridge.callHandler("room.sync.disableEraseImage", new Object[]{disable});
    }

    /**
     * 禁止用户视角变化（缩放，移动）。禁止后，开发者仍然可以通过 SDK API 移动视角。
     *
     * @param disableCameraTransform true:禁止用户主动改变视角；false:允许用户主动改变视角。默认:false。
     * @since 2.2.0
     */
    public void disableCameraTransform(final boolean disableCameraTransform) {
        bridge.callHandler("room.disableCameraTransform", new Object[]{disableCameraTransform});
    }

    /**
     * 禁止用户教具操作
     *
     * @param disableOperations true:禁止用户教具操作；false:响应用户教具输入操作。默认:false。
     * @since 2.2.0
     */
    public void disableDeviceInputs(final boolean disableOperations) {
        bridge.callHandler("room.disableDeviceInputs", new Object[]{disableOperations});
    }
    //endregion

    //region Delay API
    /**
     * 主动延时 API，延迟播放远端白板同步画面（自己画的内容，会立即显示。防止用户感知错位）
     *
     * @param delaySec 延时秒数
     */
    public void setTimeDelay(Integer delaySec) {
        bridge.callHandler("room.setTimeDelay", new Object[]{delaySec * 1000});
        this.timeDelay = delaySec;
    }

    /**
     * 获取当前主动延时秒数
     *
     * @return 延时秒数
     */
    public Integer getTimeDelay() {
        return this.timeDelay;
    }
    //endregion

    //region Event API
    /**
     * 自定义事件回调
     *
     * @param eventEntry {@link EventEntry} 自定义事件内容，相对于 {@link AkkoEvent} 多了发送者的 memberId
     */
    void fireMagixEvent(EventEntry eventEntry) {
        EventListener eventListener = eventListenerConcurrentHashMap.get(eventEntry.getEventName());
        if (eventListener != null) {
            try {
                eventListener.onEvent(eventEntry);
            } catch (AssertionError a) {
                throw a;
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

    /**
     * 发送自定义事件，所有注册监听事件的客户端，都会收到通知。
     *
     * @param eventEntry 自定义事件内容，{@link AkkoEvent}
     */
    public void dispatchMagixEvent(AkkoEvent eventEntry) {
        bridge.callHandler("room.dispatchMagixEvent", new Object[]{eventEntry});
    }

    //endregion
}
