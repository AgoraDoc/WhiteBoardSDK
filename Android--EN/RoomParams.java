package com.herewhite.sdk;

import com.herewhite.sdk.domain.CameraBound;
import com.herewhite.sdk.domain.MemberInformation;
import com.herewhite.sdk.domain.Region;
import com.herewhite.sdk.domain.WhiteObject;

import java.util.concurrent.TimeUnit;

/**
 * Created by buhe on 2018/8/11.
 */

/**
 * Configurations for a {@link Room} instance.
 *
 * @note
 * All methods in this class must be called before joining a room. After successfully joining a room, calling any method in this class does not take effect.
 *
 */
public class RoomParams extends WhiteObject {

    private String uuid;
    private String roomToken;

    /**
     * Sets the data center.
     *
     * @note
     * - The data center set in this method must be the same as the data center of the live Interactive Whiteboard live room to be joined; otherwise the SDK fails to connects to the room.
     * - You can call either this method or the {@link WhiteSdkConfiguration#setRegion(Region) setRegion} method in the `WhiteSdkConfiguration` class to set the data center. If you call both， this method overrides the {@link WhiteSdkConfiguration#setRegion(Region) setRegion} method.
     *
     * @param region The data center. See {@link Region Region}.
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * Gets the data center.
     *
     * @return The data center. See {@link Region Region}.
     */
    public Region getRegion() {
        return region;
    }

    private Region region;
    private CameraBound cameraBound;

    /**
     * Hidden in documentation
     */
    private long timeout = 45000;

    /**
     * Gets whether the user joins the whiteboard room in interactive mode.
     *
     * @return Whether the user joins the whiteboard room in interactive mode:
     * - `true`: The user joins the whiteboard room in interactive mode.
     * - `false`: The user joins the whiteboard room in interactive mode.
     */
    public boolean isWritable() {
        return isWritable;
    }

    /**
     * Sets whether a user join the whiteboard room in interactive mode.
     *
     * Users can join a live Interactive Whiteboard room in one of the following modes:
     * - Interactive mode, in which users have read and write permissions on the whiteboard, appear in the member list of the room, and are visible to all other users in the room.
     * - Subscription mode, in which users read-only access to the whiteboard, do not appear in the member list of the room, and invisible to all other users in the room.
     *
     * @param writable Wether to join the room in interactive mode:
     *                 - `true`: (Default) Join the room in interactive mode.
     *                 - `false`: Join the room in subscription mode.
     */
    public void setWritable(boolean writable) {
        isWritable = writable;
    }

    private boolean isWritable = true;

    /**
     * Gets whether the eraser is disabled from erasing images on the whiteboard.
     *
     * @return Whether the eraser is disabled from erasing images on the whiteboard:
     * - `true`: The eraser is disabled from erasing images.
     * - `false`: The eraser is enabled to erase images.
     */
    public boolean getDisableEraseImage() {
        return disableEraseImage;
    }

    /**
     * Sets whether to disable the eraser from erasing images on the whiteboard.
     *
     * By default, the eraser can erase everything on the whiteboard, including images.
     * You can call `setDisableEraseImage(true)` to set the eraser to not erase the images.
     *
     * @param disableEraseImage Whether to disable the eraser from erasing images on the whiteboard:
     *                          - `true`: Disable the eraser from erasing images.
     *                          - `false`: (Default) Enable the eraser to erase images.
     */
    public void setDisableEraseImage(boolean disableEraseImage) {
        this.disableEraseImage = disableEraseImage;
    }

    /**
     * Sets the timeout for joining a room.
     *
     * @param timeout  The time duration. The default value is 45000.
     * @param timeUnit The unit of the `timeout` parameter. The default value is `MILLISECONDS`. For all supported values, see [TimeUnit](https://www.android-doc.com/reference/java/util/concurrent/TimeUnit.html).
     */
    public void setTimeout(long timeout, TimeUnit timeUnit) {
        this.timeout = TimeUnit.MILLISECONDS.convert(timeout, timeUnit);
    }

    private boolean disableEraseImage = false;

    /**
     * 获取是否禁止教具响应用户输入。// TODO 是教具还是设备？
     *
     * @return 是否禁止教具响应用户输入：
     * - `true`：禁止教具响应用户输入。
     * - `false`：允许教具响应用户输入。
     */
    public boolean isDisableDeviceInputs() {
        return disableDeviceInputs;
    }

    /**
     * Disables the whiteboard tools from responding to the user's inputs.
     *
     * @since 2.5.0
     *
     * @note 该方法会覆盖 {@link WhiteSdkConfiguration#setDisableDeviceInputs(boolean) setDisableDeviceInputs} 的设置。
     *
     * @param disableDeviceInputs 是否禁止教具响应用户输入：
     *   - `true`：禁止教具响应用户输入。
     *   - `false`：（默认）允许教具响应用户输入。
     */
    public void setDisableDeviceInputs(boolean disableDeviceInputs) {
        this.disableDeviceInputs = disableDeviceInputs;
    }

    /**
     * 获取是否禁止白板响应用户的操作。
     *
     * @return 是否禁止白板响应用户的操作。
     * - `true`：禁止白板响应用户的操作。
     * - `false`：允许白板响应用户的操作。
     */
    public boolean isDisableOperations() {
        return disableOperations;
    }

    /**
     * Disables the whiteboard from responding to the user's operations.
     *
     *
     * @since 2.5.0
     *
     * @deprecated This method is deprecated. Use {@link #setDisableDeviceInputs(boolean) setDisableDeviceInputs} and {@link #setDisableCameraTransform(boolean) setDisableCameraTransform} instead.
     *
     * This method 禁止白板响应用户任何操作后，用户无法使用教具输入内容，也无法对白板进行视角缩放和视角移动。
     *
     * @param disableOperations 是否禁止白板响应用户的操作：
     *  - `true`：禁止白板响应用户的操作。
     *  - `false`：（默认）允许白板响应用户的操作。
     */
    public void setDisableOperations(boolean disableOperations) {
        this.disableCameraTransform = disableOperations;
        this.disableDeviceInputs = disableOperations;
        this.disableOperations = disableOperations;
    }

    /**
     * 获取是否关闭贝塞尔曲线优化。
     *
     * @return 是否关闭贝塞尔曲线优化：
     * - `true`: 关闭贝塞尔曲线优化。
     * - `false`: 开启贝塞尔曲线优化。
     */
    public boolean isDisableBezier() {
        return disableBezier;
    }

    /**
     * Disables/Enables the Bézier curve optimization.
     *
     * @since 2.5.0
     *
     * @param disableBezier Whether to disable the Bézier curve optimization:
     * - `true`: Disable.
     * - `false`: (Default) Enable.
     *
     */
    public void setDisableBezier(boolean disableBezier) {
        this.disableBezier = disableBezier;
    }

    private boolean disableDeviceInputs = false;
    private boolean disableOperations = false;

    /**
     * 获取是否禁止本地用户操作白板视角。
     *
     * @return 是否禁止本地用户操作白板视角：
     * - `true`：禁止本地用户操作白板视角。
     * - `false`：允许本地用户操作白板视角。
     */
    public boolean isDisableCameraTransform() {
        return disableCameraTransform;
    }

    /**
     * 禁止/允许本地用户操作白板的视角，包括缩放和移动视角。
     *
     * @param disableCameraTransform 是否禁止本地用户操作白板视角：
     *                               - `true`：禁止本地用户操作白板视角。
     *                               - `false`：（默认）允许本地用户操作白板视角。
     */
    public void setDisableCameraTransform(boolean disableCameraTransform) {
        this.disableCameraTransform = disableCameraTransform;
    }

    private boolean disableCameraTransform = false;
    private boolean disableBezier = false;

    public boolean isDisableNewPencil() {
        return disableNewPencil;
    }

    /**
     * 关闭/开启笔锋效果。
     * @since 2.12.2
     *
     * @note
     * - 在 2.12.2 版本中，`setDisableNewPencil` 的默认值为 `false`，自 2.12.3 版本起，`setDisableNewPencil` 的默认值改为 `true`。
     * - 为正常显示笔迹，在开启笔峰效果前，请确保该房间内的所有用户使用如下 SDK：
     *      - Android SDK 2.12.3 版或之后
     *      - iOS SDK 2.12.3 版或之后
     *      - Web SDK 2.12.5 版或之后
     *
     * @param disableNewPencil 是否关闭笔锋效果：
     * - true: （默认）关闭笔锋效果。
     * - false: 开启笔锋效果。
     */
    public void setDisableNewPencil(boolean disableNewPencil) {
        this.disableNewPencil = disableNewPencil;
    }

    private boolean disableNewPencil = true;


    /**
     * 获取视角边界。
     *
     * @return 视角边界。
     */
    public CameraBound getCameraBound() {
        return cameraBound;
    }

    /**
     * 设置本地用户的视角边界。
     *
     * @since 2.5.0
     *
     * @param cameraBound 视角边界，详见 {@link CameraBound CameraBound}。
     */
    public void setCameraBound(CameraBound cameraBound) {
        this.cameraBound = cameraBound;
    }

    public Object getUserPayload() {
        return userPayload;
    }

    /**
     * 自定义用户信息。
     *
     * @since 2.0.0
     *
     * @note
     * - 必须使用 {@link WhiteObject} 子类，以保证字段结构正确
     * - 自定义的用户信息会被完整透传。
     * <p>
     * 如果要在白板房间中显示用户头像，请在 `userPayload` 中传入 `avatar` 字段并添加用户头像的地址，例如 `"avatar", "https://example.com/user.png")`。
     * 从 {@link MemberInformation MemberInformation} 迁移，只需要在 `userPayload` 中，传入相同字段即可。
     *
     * @param userPayload 自定义的用户信息，必须为 key-value 结构。
     */
    public void setUserPayload(Object userPayload) {
        this.userPayload = userPayload;
    }

    private Object userPayload;

    /**
     * 初始化房间配置参数。
     *
     * @param uuid      房间 UUID， 即房间唯一标识符。
     * @param roomToken 用于鉴权的 Room Token。生成该 Room Token 的房间 UUID 必须和上面传入的房间 UUID 一致。
     */
    public RoomParams(String uuid, String roomToken) {
        this(uuid, roomToken, (Object) null);
    }

    /**
     * 初始化房间配置参数并传入用户信息。
     *
     * @deprecated 该方法已经废弃。请使用 {@link RoomParams(String, String, Object) RoomParams}。
     *
     * @param uuid       房间 UUID， 即房间唯一标识符。
     * @param roomToken  用于鉴权的 Room Token。生成该 Room Token 的房间 UUID 必须和上面传入的房间 UUID 一致。
     * @param memberInfo 用户信息。{@link MemberInformation MemberInformation} 类已经废弃。请使用 {@link #RoomParams(String, String, Object)} 传入用户信息。
     * @deprecated 该方法已经废弃。请使用 {@link RoomParams(String, String, Object) RoomParams}。
     */
    @Deprecated
    public RoomParams(String uuid, String roomToken, MemberInformation memberInfo) {
        this.uuid = uuid;
        this.roomToken = roomToken;
        this.userPayload = memberInfo;
    }

    /**
     * 初始化房间配置参数并传入自定义的用户信息。
     *
     * @since 2.0.0
     *
     * @param uuid        房间 UUID， 即房间唯一标识符。
     * @param roomToken   用于鉴权的 Room Token。生成该 Room Token 的房间 UUID 必须和上面传入的房间 UUID 一致。
     * @param userPayload 自定义用户信息，必须为 {@link WhiteObject} 子类。
     */
    public RoomParams(String uuid, String roomToken, Object userPayload) {
        this.uuid = uuid;
        this.roomToken = roomToken;
        this.userPayload = userPayload;
    }

    /**
     * 获取自定义的用户信息。
     *
     * @deprecated 该方法已废弃。请使用 {@link #getUserPayload() getUserPayload}。
     *
     * @return 自定义用户信息，详见 {@link MemberInformation}。
     */
    @Deprecated
    public MemberInformation getMemberInfo() {
        if (userPayload instanceof MemberInformation) {
            return (MemberInformation) userPayload;
        }
        return null;
    }

    /**
     * 自定义用户信息。
     *
     * @deprecated 该方法已废弃。请使用 {@link #getUserPayload() getUserPayload}。
     *
     * @param memberInfo 用户信息，详见 {@link MemberInformation MemberInformation}。
     */
    @Deprecated
    public void setMemberInfo(MemberInformation memberInfo) {
        this.userPayload = memberInfo;
    }

    /**
     * 获取房间 UUID。
     *
     * @return 房间 UUID，即房间的唯一标识符。
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置房间 UUID。
     *
     * @param uuid 房间 UUID，即房间的唯一标识符。
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取 Room Token。
     *
     * @return Room Token。
     */
    public String getRoomToken() {
        return roomToken;
    }

    /**
     * 设置 Room Token。
     *
     * @param roomToken 用于鉴权的 Room Token。生成该 Room Token 的房间 UUID 必须和上面传入的房间 UUID 一致。
     */
    public void setRoomToken(String roomToken) {
        this.roomToken = roomToken;
    }

}
