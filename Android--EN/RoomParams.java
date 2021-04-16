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
     * Gets whether the whiteboard tools are disabled from responding to users' inputs.
     *
     * @return Whether the whiteboard tools are disabled from responding to users' inputs.
     * - `true`：The whiteboard tools are disabled from responding to users' inputs.
     * - `false`：The whiteboard tools are enabled to respond to users' inputs.
     */
    public boolean isDisableDeviceInputs() {
        return disableDeviceInputs;
    }

    /**
     * Disables the whiteboard tools from responding to users' inputs.
     *
     * @since 2.5.0
     *
     * @note
     * You can call either this method or the {@link WhiteSdkConfiguration#setDisableDeviceInputs(boolean) setDisableDeviceInputs} method. This method call overrides the {@link WhiteSdkConfiguration#setDisableDeviceInputs(boolean) setDisableDeviceInputs} method.
     *
     * @param disableDeviceInputs Whether to disable the whiteboard tools from responding to users' inputs:
     *   - `true`: Disable the whiteboard tools from responding to users' inputs.
     *   - `false`: (Default) Enable the whiteboard tools to respond to users' inputs.
     */
    public void setDisableDeviceInputs(boolean disableDeviceInputs) {
        this.disableDeviceInputs = disableDeviceInputs;
    }

    /**
     * Gets whether the whiteboard is disabled from responding to users' operations.
     *
     * @return Whether the whiteboard is disabled from responding to users' operations:
     * - `true`: The whiteboard is disabled from responding to users' operations.
     * - `false`: The whiteboard is enabled to respond to users' operations.
     */
    public boolean isDisableOperations() {
        return disableOperations;
    }

    /**
     * Disables the whiteboard from responding to users' operations.
     *
     * @since 2.5.0
     *
     * @deprecated This method is deprecated. Use {@link #setDisableDeviceInputs(boolean) setDisableDeviceInputs} and {@link #setDisableCameraTransform(boolean) setDisableCameraTransform} instead.
     *
     * After calling `setDisableOperations(true)`, the users can neither use the whiteboard tools nor adjust the view of the whiteboard.
     *
     * @param disableOperations Whether to disable the whiteboard from responding to users' operations:
     *  - `true`: Disable the whiteboard from responding to users' operations.
     *  - `false`: (Default) Enable the whiteboard to respond to users' operations.
     */
    public void setDisableOperations(boolean disableOperations) {
        this.disableCameraTransform = disableOperations;
        this.disableDeviceInputs = disableOperations;
        this.disableOperations = disableOperations;
    }

    /**
     * Gets whether the Bézier curve optimization is disabled.
     *
     * @return Whether the Bézier curve optimization is disabled:
     * - `true`: The Bézier curve optimization is disabled.
     * - `false`：The Bézier curve optimization is enabled.
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
     * Gets whether adjusting the view of the whiteboard by the local user is disabled.
     *
     * @return Whether adjusting the view of the whiteboard by the local user is disabled:
     * - `true`: Disable the local user from adjusting the view of the whiteboard.
     * - `false`：Enable the local user to adjust the view of the whiteboard.
     */
    public boolean isDisableCameraTransform() {
        return disableCameraTransform;
    }

    /**
     * Disables the local user from adjusting the view of the whiteboard, including moving and zooming the view.
     *
     *
     * @param disableCameraTransform Whether to disable the local user from adjusting the view of the whiteboard:
     *  - `true`: Disable the local user from adjusting the view of the whiteboard.
     *  - `false`: (Default) Enable the local user to adjust the view of the whiteboard.
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
     * Disables/Enables the stroke effect of the pencil.
     * // TODO CT @William I see that "the stroke effect" is used in Interactive Whiteboard release notes, but I am not sure if "stroke effect" match with the original Chinese meaning.
     * // Could you please explain the meaning of "stroke effect"
     * // The original Chinese phrase is "笔锋效果", with which the text written with the pencil on the whiteboard looks more natural and close to handwriting text. Each stroke will end with a tip, rather than stop suddenly.
     * // I am considering using the phrase "the handwriting effect", but not sure if it is proper here.
     *
     * @since 2.12.2
     *
     * @note
     * - In v2.12.2, the SDK sets the default value of `disableNewPencil` as `false`; as of v2.12.3, the SDK changes the default value of `disableNewPencil` to `true`.
     * - To enable the stroke effect, ensure that every user in the room uses one of the following SDKs:
     *      - Android SDK v2.12.3 or later
     *      - iOS SDK v2.12.3 or later
     *      - Web SDK v2.12.5 or later
     *
     * @param disableNewPencil Whether to disable the handwriting effect of the pencil:
     * - `true`: (Default) Disable the handwriting effect of the pencil.
     * - `false`: Enable the handwriting effect of the pencil.
     */
    public void setDisableNewPencil(boolean disableNewPencil) {
        this.disableNewPencil = disableNewPencil;
    }

    private boolean disableNewPencil = true;


    /**
     * Gets the boundary of the local user's view.
     *
     * @return The boundary of the view.
     */
    public CameraBound getCameraBound() {
        return cameraBound;
    }

    /**
     * Sets the boundary of the local user's view.
     *
     * @since 2.5.0
     *
     * @param cameraBound The boundary of the view. See {@link CameraBound CameraBound}.
     */
    public void setCameraBound(CameraBound cameraBound) {
        this.cameraBound = cameraBound;
    }

    public Object getUserPayload() {
        return userPayload;
    }

    /**
     * Sets the customized user information.
     *
     * @since 2.0.0
     *
     * You can pass in customized user information, such as user ID, nickname, and avatar in the `userPayload` and call this method to send the information to the application.
     *
     * @note
     * To ensure the format of the customized user information is correct, the `userPayload` must extend the {@link WhiteObject} class.
     *
     * @param userPayload Customized user information in key-value pairs, for example, `"avatar", "https://example.com/user.png")`.
     *
     */
    public void setUserPayload(Object userPayload) {
        this.userPayload = userPayload;
    }

    private Object userPayload;

    /**
     * Initializes an `RoomParams` instance.
     *
     * @param uuid      The UUID of the room, which is unique identifier of the room. Ensure that the room UUID is the same as the one you use to generate the Room Token.
     * @param roomToken The Room Token for authentication.
     */
    public RoomParams(String uuid, String roomToken) {
        this(uuid, roomToken, (Object) null);
    }

    /**
     * Initializes an `RoomParams` instance with customized user information.
     *
     * @deprecated This method is deprecated. Use {@link RoomParams(String, String, Object) RoomParams} instead.
     *
     * @param uuid       The UUID of the room, which is unique identifier of the room. Ensure that the room UUID is the same as the one you use to generate the Room Token.
     * @param roomToken  The Room Token for authentication.
     * @param memberInfo Customized user information. See {@link MemberInformation MemberInformation}.
     */
    @Deprecated
    public RoomParams(String uuid, String roomToken, MemberInformation memberInfo) {
        this.uuid = uuid;
        this.roomToken = roomToken;
        this.userPayload = memberInfo;
    }

    /**
     * Initializes an `RoomParams` instance with customized user information.
     *
     * @since 2.0.0
     *
     * @param uuid        The UUID of the room, which is unique identifier of the room. Ensure that the room UUID is the same as the one you use to generate the Room Token.
     * @param roomToken   The Room Token for authentication.
     * @param userPayload Customized user information, which must be a subclass of {@link WhiteObject} to ensure the data format is correct.
     */
    public RoomParams(String uuid, String roomToken, Object userPayload) {
        this.uuid = uuid;
        this.roomToken = roomToken;
        this.userPayload = userPayload;
    }

    /**
     * Gets customized user information.
     *
     * @deprecated This method is deprecated. Use {@link #getUserPayload() getUserPayload} instead.
     *
     * @return Customized user information. See {@link MemberInformation MemberInformation}.
     */
    @Deprecated
    public MemberInformation getMemberInfo() {
        if (userPayload instanceof MemberInformation) {
            return (MemberInformation) userPayload;
        }
        return null;
    }

    /**
     * Sets customized user information.
     *
     * @deprecated This method is deprecated. Use {@link #getUserPayload() getUserPayload} instead.
     *
     * @param memberInfo Customized user information. See {@link MemberInformation MemberInformation}.
     */
    @Deprecated
    public void setMemberInfo(MemberInformation memberInfo) {
        this.userPayload = memberInfo;
    }

    /**
     * Gets the UUID of the room.
     *
     * @return The UUID of the room, which is the unique identifier of the room.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Sets the UUID of the room.
     *
     * @param uuid The UUID of the room, which is the unique identifier of the room.
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * Gets the Room Token.
     *
     * @return The Room Token for authentication.
     */
    public String getRoomToken() {
        return roomToken;
    }

    /**
     * Sets the Room Token.
     *
     * @param roomToken The Room Token for authentication.
     */
    public void setRoomToken(String roomToken) {
        this.roomToken = roomToken;
    }

}