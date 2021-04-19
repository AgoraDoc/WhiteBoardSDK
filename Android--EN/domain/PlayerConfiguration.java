package com.herewhite.sdk.domain;

import java.util.concurrent.TimeUnit;

/**
 * Configuration of the `Player` instance.
 */
public class PlayerConfiguration extends WhiteObject {
    private String room;
    private String roomToken;
    private String slice;
    private Long beginTimestamp;
    private Long duration;
    private CameraBound cameraBound;
    private Long step = 500L;

    /**
     * 获取待回放的互动白板房间所在的数据中心。
     *
     * @return 待回放的互动白板房间所在的数据中心。，详见 {@link Region Region}。
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Sets the data center for the `Player` instance.
     * 设置待回放的互动白板房间所在的数据中心。
     *
     * The data center set in this method must be the same as that in {@link com.herewhite.sdk.RoomParams.setRegion(Region region) setRegion}.
     *
     * @param region The data center for the `Player` instance. See {@link Region Region}.
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    private Region region;

    /**
     * The `PlayerConfiguration` constructor, for initializing a `PlayerConfiguration` object.
     *
     * @param room      The unique identifier of the room, which must be the same as the one set in `roomParams` parameter of the `joinRoom` method.
     * @param roomToken The Room Token for authentication, which must be the same as the one set in `roomParams` parameter of the `joinRoom` method.
     */
    public PlayerConfiguration(String room, String roomToken) {
        this.room = room;
        this.roomToken = roomToken;
    }

    /**
     * 获取本地用户观看回放时的视角边界。
     *
     * @return 视角边界，详见 {@link CameraBound CameraBound}。
     */
    public CameraBound getCameraBound() {
        return cameraBound;
    }

    /**
     * 设置本地用户观看回放时的视角边界。
     *
     * 该方法设置的视角边界必须和 {@link com.herewhite.sdk.RoomParams#setCameraBound(CameraBound)} 中设置视角边界一致。
     *
     * @param cameraBound 视角边界，详见 {@link CameraBound CameraBound}。
     */
    public void setCameraBound(CameraBound cameraBound) {
        this.cameraBound = cameraBound;
    }

    /**
     * Sets the frequency that the SDK reports the current playback position callback.
     *
     * @param duration The time interval between two playback position callbacks. By default, the SDK reports the current playback position every 0.5 seconds.
     * @param timeUnit The unit of the `duration` parameter. The default value is `MILLISECONDS`. For all supported values, see [TimeUnit](https://www.android-doc.com/reference/java/util/concurrent/TimeUnit.html).
     */
    public void setStep(Long duration, TimeUnit timeUnit) {
        this.step = TimeUnit.MILLISECONDS.convert(duration, timeUnit);
    }


    /**
     * Hidden in documentation.
     */
    private String mediaURL;

    /**
     * Gets the room UUID.
     *
     * @return The the room UUID.
     */
    public String getRoom() {
        return room;
    }

    /**
     * 设置待回放的互动白板房间的 UUID。
     *
     * @param room 房间 UUID，即房间唯一标识符，必须和初始化互动白板房间实例时设置的房间 UUID 一致。
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * 获取待回放的互动白板房间的 Room Token。
     *
     * @return 互动白板房间的 Room Token。
     */
    public String getRoomToken() {
        return roomToken;
    }

    /**
     * 设置待回放的互动白板房间的 Room Token。
     *
     * @return 用于鉴权的 Room Token，必须和初始化互动白板房间实例时设置的 Room Token 一致。
     */
    public void setRoomToken(String roomToken) {
        this.roomToken = roomToken;
    }

    /**
     * 文档中隐藏
     *
     * @return
     */
    public String getSlice() {
        return slice;
    }

    /**
     * 文档中隐藏
     *
     * @return
     */
    public void setSlice(String slice) {
        this.slice = slice;
    }

    /**
     * 白板回放的起始时间。
     *
     * @return Unix 时间戳（毫秒），表示回放的起始 UTC 时间。
     */
    public Long getBeginTimestamp() {
        return beginTimestamp;
    }

    /***
     * 设置白板回放的起始时间。
     *
     * 在该方法中，你需要传入单位为毫秒的 Unix 时间戳，表示 UTC 时间，例如，如果要将回放的起始时间设为 2021-03-10 18:03:34 GMT+0800，你需要传入 `1615370614269`。
     *
     * @param beginTimestamp Unix 时间戳（毫秒），表示回放的起始 UTC 时间。例如，`1615370614269` 表示 2021-03-10 18:03:34 GMT+0800。
     */
    public void setBeginTimestamp(Long beginTimestamp) {
        this.beginTimestamp = beginTimestamp;
    }

    /**
     * 设置回放的持续时长。
     *
     * @return 回放的持续时长，单位为毫秒。
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * 设置回放的持续时长。
     *
     * @param duration 回放的持续时长，单位为毫秒。
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    /**
     * 文档中隐藏
     * @return
     */
    public String getMediaURL() {
        return mediaURL;
    }

    /**
     * 文档中隐藏
     * @param mediaURL
     */
    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }
}
