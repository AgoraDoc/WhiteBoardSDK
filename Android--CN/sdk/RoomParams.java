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
 * `RoomParams` 类，用于配置实时房间的参数。
 *
 * @note 在加入房间前调用。// TODO 是必须在调用 `joinRoom' 前调用吗？// 是的，在 joinRoom 后调用不生效。
 */
public class RoomParams extends WhiteObject {

    private String uuid;
    private String roomToken;

    /**
     * 设置数据中心。
     *
     * @note
     * - 该方法设置的数据中心必须与要加入的互动白板实时房间所在数据中心一致，否则无法加入房间。
     * - 该方法与 `WhiteSdkConfiguration` 类中的 {@link WhiteSdkConfiguration#setRegion(Region) setRegion} 方法作用相同，两个方法只需要调用其中的一个。如果同时调用，该方法会覆盖 `WhiteSdkConfiguration` 类中的 {@link WhiteSdkConfiguration#setRegion(Region) setRegion}。
     * @param region
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * 设置设置的数据中心。
     *
     * @return 设置的数据中心。
     */
    public Region getRegion() {
        return region;
    }

    private Region region;
    private CameraBound cameraBound;

    /**
     * 重连时，最大重连尝试时间，单位：毫秒，默认 45 秒。
     */
    private long timeout = 45000;

    /**
     * 获取用户是否以互动模式加入白板房间。
     *
     * @return 用户是否以互动模式加入白板房间：
     * - `true`：以互动模式加入白板房间。
     * - `false`：以订阅模式加入白板房间。
     */
    public boolean isWritable() {
        return isWritable;
    }

    /**
     * 设置用户是否以互动模式加入白板房间。
     *
     * 以互动模式加入白板房间的用户可以操作白板。
     * 如果设置为 `false`，则用户以订阅模式加入房间，在房间内只具有只读权限，将不会出现在房间的成员列表中，其他用户无法得知该用户的存在。
     *
     * @note
     * 互动模式API，设置为订阅（false）的房间，无法操作影响房间的 API。// TODO 这句话的意思是如果白板房间为订阅模式（即只读模式），则该方法不生效？// 白板房间本身没有互动和只读模式
     *
     * @param writable 用户是否以互动模式加入白板房间：
     * - `true`：（默认）以互动模式加入白板房间。
     * - `false`：以订阅模式加入白板房间。
     */
    public void setWritable(boolean writable) {
        isWritable = writable;
    }

    private boolean isWritable = true;

    /**
     * 获取是否关闭橡皮擦擦除图片功能。
     *
     * @return 是否关闭橡皮擦擦除图片功能：
     * - `true`：橡皮擦不可以擦除图片。
     * - `false`：橡皮擦可以擦除图片。
     */
    public boolean getDisableEraseImage() {
        return disableEraseImage;
    }

    /**
     * 设置是否关闭橡皮擦擦除图片功能。
     *
     * 默认情况下，橡皮擦可以擦除白板上的所有内容，包括图片。你可以调用 `setDisableEraseImage(true)` 设置橡皮擦不能擦除图片。
     *
     * @param disableEraseImage 是否关闭橡皮擦擦除图片功能：
     * - `true`：橡皮擦不可以擦除图片。
     * - `false`：（默认）橡皮擦可以擦除图片。
     */
    public void setDisableEraseImage(boolean disableEraseImage) {
        this.disableEraseImage = disableEraseImage;
    }

    /**
     * 设置加入房间的超时时间。
     *
     * @param timeout 超时时长。
     * @param timeUnit 时长单位。// TODO java 基础类 查看 Android API 文档
     */
    public void setTimeout(long timeout, TimeUnit timeUnit) {
        this.timeout = TimeUnit.MILLISECONDS.convert(timeout, timeUnit);
    }

    private boolean disableEraseImage = false;

    /**
     * 获取是否禁止教具响应用户输入。
     *
     * @return 是否禁止教具响应用户输入：
     * - `true`：禁止教具响应用户输入。
     * - `false`：允许教具响应用户输入。
     */
    public boolean isDisableDeviceInputs() {
        return disableDeviceInputs;
    }

    /**
     * 开启/禁止教具响应用户输入。 // TODO 禁止用户使用教具在白板上输入？一样的意思
     *
     * @since 2.5.0
     *
     * @note
     * 该方法会覆盖 {@link WhiteSdkConfiguration#setDisableDeviceInputs(boolean) setDisableDeviceInputs} 的设置。
     *
     * @param disableDeviceInputs 是否禁止教具响应用户输入：
     * - `true`：禁止教具响应用户输入。
     * - `false`：（默认）允许教具响应用户输入。
     */
    public void setDisableDeviceInputs(boolean disableDeviceInputs) {
        this.disableDeviceInputs = disableDeviceInputs;
    }

    /**
     * 获取是否禁止白板响应用户任何操作。
     *
     * @return 是否禁止白板响应用户任何操作。
     * - `true`：禁止白板响应用户输入。
     * - `false`：允许白板响应用户输入。
     *
     */
    public boolean isDisableOperations() {
        return disableOperations;
    }

    /**
     * 允许/禁止白板响应用户任何操作。// TODO 禁止用户在白板房间内进行任何操作？
     *
     * @since 2.5.0
     *
     * @deprecated 该方法已废弃。请使用 {@link setDisableDeviceInputs(boolean) setDisableDeviceInputs} 和 {@link setDisableCameraTransform(boolean) setDisableCameraTransform}。
     *
     * 禁止白板响应用户任何操作后，用户无法使用教具输入内容，也无法对白板进行视角缩放和视角移动。
     *
     * @param disableOperations 是否禁止白板响应用户操作：
     * - `true`：禁止白板响应用户输入。
     * - `false`：（默认）允许白板响应用户输入。
     *
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
     * 设置是否关闭贝塞尔曲线优化。
     *
     * @since 2.5.0
     *
     * @param disableBezier 是否关闭贝塞尔曲线优化：
     * - `true`: 关闭贝塞尔曲线优化。
     * - `false`: （默认）开启贝塞尔曲线优化。
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
     * - `true`：禁止本地用户操作白板视角。
     * - `false`：（默认）允许本地用户操作白板视角。
     */
    public void setDisableCameraTransform(boolean disableCameraTransform) {
        this.disableCameraTransform = disableCameraTransform;
    }

    private boolean disableCameraTransform = false;
    private boolean disableBezier = false;

    /**
     * 获取视野范围。
     *
     * @return 视野范围。
     */
    public CameraBound getCameraBound() {
        return cameraBound;
    }

    /**
     * 设置本地用户的视野范围。
     *
     * @since 2.5.0
     *
     * @param cameraBound 视野范围，详见 {@link CameraBound CameraBound}。
     *
     */
    public void setCameraBound(CameraBound cameraBound) {
        this.cameraBound = cameraBound;
    }

    /**
     * 获取自定义的用户信息。
     *
     * @return 自定义的用户信息。
     */
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
     *
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
     * @param uuid 房间 UUID， 即房间唯一标识符。
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
     * @param uuid 房间 UUID， 即房间唯一标识符。
     * @param roomToken  用于鉴权的 Room Token。生成该 Room Token 的房间 UUID 必须和上面传入的房间 UUID 一致。
     * @param memberInfo 用户信息。{@link MemberInformation MemberInformation} 类已经废弃。请使用 {@link #RoomParams(String, String, Object)} 传入用户信息。
     *
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
     * @param uuid 房间 UUID， 即房间唯一标识符。
     * @param roomToken 用于鉴权的 Room Token。生成该 Room Token 的房间 UUID 必须和上面传入的房间 UUID 一致。
     * @param userPayload 自定义用户信息，必须为 key-value 结构。
     * 参考 {@link #setUserPayload(Object)} key-value 结构，请使用自定义后的 {@link WhiteObject} 子类。// TODO ???
     * // TODO 初始化房间配置参数时传入自定义用户信息 调用 setUserPayload 什么关系？初始化的时候传入了 userPayload，就不需要再调用 setUserPayload 了？如果调用了，会怎样？// 初始化后调用 setUserPayload 可以更新用户信息
     *
     */
    public RoomParams(String uuid, String roomToken, Object userPayload) {
        this.uuid = uuid;
        this.roomToken = roomToken;
        this.userPayload = userPayload;
    }

    /**
     * 获取设置的用户信息。
     *
     * @deprecated 该方法已废弃。请使用 {@link getUserPayload() getUserPayload}。
     *
     * @return 用户信息。
     */
    @Deprecated
    public MemberInformation getMemberInfo() {
        if (userPayload instanceof MemberInformation) {
            return (MemberInformation)userPayload;
        }
        return null;
    }

    /**
     * 设置用户信息。
     *
     * @deprecated 该方法已废弃。请使用 {@link getUserPayload() getUserPayload}。
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
     * @param uuid 房间 UUID，即房间的唯一标识符。
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
     * @param roomToken 用于鉴权的 Room Token。生成该 Room Token 的房间 UUID 必须和上面传入的房间 UUID 一致。
     */
    public void setRoomToken(String roomToken) {
        this.roomToken = roomToken;
    }

}
