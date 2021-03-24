package com.herewhite.sdk.domain;

/**
 * 场景状态。
 */
public class SceneState extends WhiteObject {

    private Scene[] scenes;
    private String scenePath;
    private int index;

    /**
     * 获取房间内所有场景列表。// TODO 是房间内的所有场景吗？还是当前场景组下的所有场景？Web 写的是房间内的所有场景。
     *
     * @return 房间内所有场景列表。
     */
    public Scene[] getScenes() {
        return scenes;
    }
    /**
     * 获取当前场景的路径。
     *
     * @return 当前场景的路径。
     */
    public String getScenePath() {
        return scenePath;
    }
    /**
     * 获取当前场景在所属场景组中的索引号。
     *
     * @return 当前场景在所属场景组中的索引号。
     */
    public int getIndex() {
        return index;
    }

}
