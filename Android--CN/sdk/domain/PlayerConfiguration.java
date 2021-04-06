package com.herewhite.sdk.domain;

import java.util.concurrent.TimeUnit;

public class PlayerConfiguration extends WhiteObject {
    private String room;
    private String roomToken;
    private String slice;
    private Long beginTimestamp;
    private Long duration;
    private CameraBound cameraBound;
    private Long step = 500L;

    public Region getRegion() {
        return region;
    }

    /**
     * 类似 {@link com.herewhite.sdk.RoomParams#setRegion(Region)} //TODO WJ 类似？？？需要更具体的描述。
     * @param region //TODO WJ 缺注释
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    private Region region;

    /**
     * player 初始化方法 //TODO WJ 这是 SDK 内部调用的吗？
     * @param room 需要回放的房间 uuid
     * @param roomToken 房间 roomToken
     */
    public PlayerConfiguration(String room, String roomToken) {
        this.room = room;
        this.roomToken = roomToken;
    }

    //TODO WJ 这个方法不需要注释？
    public CameraBound getCameraBound() {
        return cameraBound;
    }

    /** //TODO WJ 缺注释
     * {@link com.herewhite.sdk.Room#setCameraBound(CameraBound)}
     * @param cameraBound
     */
    public void setCameraBound(CameraBound cameraBound) {
        this.cameraBound = cameraBound;
    }

    /**
     * 回放时，时间进度的调用频率 //TODO WJ 什么意思？
     * @param duration 时长长度
     * @param timeUnit 时间单位
     */
    public void setStep(Long duration, TimeUnit timeUnit) {
        this.step = TimeUnit.MILLISECONDS.convert(duration, timeUnit);
    }

    /*
      音频地址，暂不支持视频。
      Player 会自动与音视频播放做同步，保证同时播放，当一方缓冲时，会暂停。
    */
    private String mediaURL;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoomToken() { return roomToken; }

    public void setRoomToken(String roomToken) { this.roomToken = roomToken; }

    /**
     * 文档中隐藏
     * @return
     */
    public String getSlice() {
        return slice;
    }

    /**
     * 文档中隐藏
     * @return
     */
    public void setSlice(String slice) {
        this.slice = slice;
    }

    //TODO WJ 这个不需要注释？
    public Long getBeginTimestamp() {
        return beginTimestamp;
    }

    /**
     * 设置回放的起始时间。
     * @param beginTimestamp 回放房间的起始 UTC 时间戳(毫秒）。例如，想要回放 Wed Mar 10 2021 18:03:34 GMT+0800 (中国标准时间) 的话，需要传入 1615370614269。
     */
    public void setBeginTimestamp(Long beginTimestamp) {
        this.beginTimestamp = beginTimestamp;
    }

    //TODO WJ 缺注释
    public Long getDuration() {
        return duration;
    }

    /**
     * 设置持续时长（毫秒）//TODO WJ 设置什么的时长？参数缺注释。下面的方法都不需要注释？
     * @param duration
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getMediaURL() {
        return mediaURL;
    }

    public void setMediaURL(String mediaURL) {
        this.mediaURL = mediaURL;
    }
}
