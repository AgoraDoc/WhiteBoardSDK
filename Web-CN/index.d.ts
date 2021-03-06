import { ComponentType, HTMLAttributes, Consumer } from "react";

export { ComponentType, HTMLAttributes, Consumer } from "react";

/**
 * 当前 white-web-sdk 的版本号
 */
export declare const WhiteVersion: string;

export declare const AkkoVersion: any;

export declare const AdminObserverId: number;

export declare type Event = {
    /**
     * 事件名称
     */
    event: string;
    /**
     * 事件的负载
     */
    payload: any;
    /**
     * 事件触发者的用户 ID，若是系统事件，则为 AdminObserverId
     */
    authorId: number;
    scope: Scope;
    phase: EventPhase;
};

export declare enum EventScope {
    System = 0,
    App = 1,
    Custom = 2,
    Magix = 3,
}

export declare enum EventPhase {
    Dispatched = "dispatched",
    Updated = "updated",
    Canceled = "canceled",
}

export declare type ReconnectionOptions = {
    disableReconnect: boolean;
};

export declare type Level = "debug" | "info" | "warn" | "error";

export declare enum DeviceType {
    /**
     * 桌面设备，使用键盘鼠标
     */
    Desktop = "desktop",
    /**
     * 触碰板设备，比如只能手机、平板电脑
     */
    Touch = "touch",
    /**
     * 同时支持键盘、鼠标、触碰板的设备
     */
    Surface = "surface",
}

export declare type Camera = {
    /**
     * 视角的中心对准的点的 x 坐标（世界坐标系）
     */
    centerX: number;
    /**
     * 视角的中心对准的点的 y 坐标（世界坐标系）
     */
    centerY: number;
    /**
     * 视角拉伸会导致物体放大缩小的倍率
     */
    scale: number;
};

export declare type Rectangle = Size & {
    /**
     * 矩形的左上角 x 坐标
     */
    originX: number;
    /**
     * 矩形的左上角 y 坐标
     */
    originY: number;
};

export declare type Size = {
    /**
     * 宽
     */
    width: number;
    /**
     * 高
     */
    height: number;
};

export declare enum AnimationMode {
    /**
     * 以播放渐变动画的方式移动视角
     */
    Continuous = "continuous",
    /**
     * 一瞬间就将视角移动到目标
     */
    Immediately = "immediately",
}

export declare type WrappedComponents = ReadonlyArray<ComponentType<{
}>>;

export declare const CNode: ComponentType<CNodeProps>;

export declare type CNodeProps = HTMLAttributes<HTMLDivElement> & {
    context?: any;
    fixedMode?: boolean;
    onRef?: (element: HTMLDivElement | null)=>void;
};

export declare type UserFonts = {
    [font: string]: string;
};

export declare interface Cursor {
    /**
     * 光标的容器，在创建之初为空。
     * 你可以通过如下代码向容器中添加 HTML 元素，以改变光标的外观。
     *
     * @example
     * ```typescript
     * const icon = document.createElement("img");
     * icon.src = "https://my-resources.com/icon.png";
     * cursor.divElement.append(icon);
     * ```
     */
    readonly divElement: HTMLDivElement;

    /**
     * 光标所属用户的 ID
     */
    readonly memberId: number;

    /**
     * 光标所属用户的一些属性封装
     */
    readonly cursorMember: CursorMember;

    /**
     * 光标指示中心点的 x 坐标（相对于白板左上角）
     */
    readonly x: number;

    /**
     * 光标指示中心点的 y 坐标（相对于白板左上角）
     */
    readonly y: number;

    /**
     * 光标图形的宽度
     */
    readonly width: number;

    /**
     * 光标图形的高度
     */
    readonly height: number;

    /**
     * 成员 cursorMember 改变后的回调。可以用如下代码监听。
     * @params cursorMember 更新后的
     *
     * @example
     * ```typescript
     * cursor.onCursorMemberChanged = function(cursorMember) {
     *     // cursorMember 改变了
     * }
     * ```
     */
    onCursorMemberChanged: (cursorMember: CursorMember)=>void;

    /**
     * 通过注入 JSX 结构来改变光标的外观。
     * @param reactNode 描述光标外观的 JSX
     * @example
     * ```tsx
     * cursor.setReactNode(
     *     <img src="https://my-resources.com/icon.png"/>
     * );
     * ```
     */
    setReactNode(reactNode: any): void;

    /**
     * 中途修改光标的描述
     * @param description 光标的描述
     */
    setCursorDescription(description: Partial<CursorDescription>): void;

}

export declare interface CursorMember {
    /**
     * 用户所选择的 strokeColor
     */
    readonly color: Color;

    /**
     * 用户所选择的教具
     */
    readonly appliance: string;

    /**
     * @deprecated 过期属性
     */
    readonly information: MemberInformation;

}

export declare interface CursorAdapter {
    /**
     * 创建对光标的描述。某个用户的光标首次出现之前会被调用。
     * @param memberId 该用户的 ID
     * @returns 对应用户光标的描述对象
     */
    createCursor(memberId: number): CursorDescription & {
        reactNode?: any;
    };

    /**
     * 在某个光标出现在白板上时回调
     * @param cursor 光标对象
     */
    readonly onAddedCursor: ((cursor: Cursor)=>void) | undefined;

    /**
     * 在某个光标从白板上消失时回调
     * @param cursor 光标对象
     */
    readonly onRemovedCursor: ((cursor: Cursor)=>void) | undefined;

    /**
     * 在某个光标在白板上移动时回调
     * @param cursor 光标对象
     * @param positionX 光标指示中心点的 x 坐标（相对于白板左上角）
     * @param positionY 光标指示中心点的 y 坐标（相对于白板左上角）
     */
    readonly onMovingCursor: ((cursor: Cursor, positionX: number, positionY: number)=>void) | undefined;

}

export declare type CursorDescription = {
    /**
     * 光标指示中心点的 x 坐标（相对光标图形的左上角）
     */
    x: number;
    /**
     * 光标指示中心点的 y 坐标（相对光标图形的左上角）
     */
    y: number;
    /**
     * 光标图形的宽
     */
    width: number;
    /**
     * 光标图形的高
     */
    height: number;
};

export declare type FloatBarOptions = {
    /**
     * 浮动条调色盘的颜色列表
     */
    colors: ReadonlyArray<Readonly<Color>>;
};

export declare function createPlugins<C_MAP extends Object>(plugins: Readonly<{
    [K: string]: Plugin<any>;
}>): Plugins<C_MAP>;

export declare type Plugin<C = {
}, T = any> = {
    kind?: string;
    render: ComponentType<PluginProps<C, T>>;
    defaultAttributes?: T;
    hitTest?: (plugin: PluginInstance<C, T>, x: number, y: number, selectorRadius: number)=>boolean;
    willInterruptEvent?: (plugin: PluginInstance<C, T>, event: NativeEvent)=>boolean;
};

export declare interface Plugins<C_MAP extends Object = {
    [kind: string]: any;
}> {
    readonly plugins: Readonly<{
        [K: string]: RenderPlugin<any>;
    }>;

    setPluginContext<K extends string>(kind: K, context: any): void;

    getPluginContext<K extends string>(kind: K): any | undefined;

}

export declare abstract class InvisiblePlugin<A extends Object> {
    readonly displayer: Displayer;

    readonly callbacks: Callbacks<InvisibleCallbacks<A>>;

    readonly attributes: A;

    constructor(context: InvisiblePluginContext);

    onAttributesUpdate(attributes: A): void;

    onDestroy(): void;

    setAttributes(modifyAttributes: Partial<A>): void;

    destroy(): void;

    _dispose(shouldCallback: boolean): void;

    private kind: any;

    private _displayer: any;

    private _callbacks: any;

    private enableCallbackUpdate: any;

    private disposer: any;

    private autorunAttributesUpdate: any;

}

export declare type InvisiblePluginContext = {
    kind: string;
    displayer: Displayer;
};

export declare type InvisiblePluginClass<K extends string, A extends Object, P extends InvisiblePlugin<A> = InvisiblePlugin<A>> = {
    kind: K;
    onCreate?: (plugin: P)=>void;
    onDestroy?: (plugin: P)=>void;
};

export declare type InvisibleCallbacks<A extends Object> = {
    onAttributesUpdate: (attributes: A)=>void;
    onDestroy: ()=>void;
};

export declare type LoggerOptions = {
    /**
     * 上报 debug 日志的模式
     */
    reportDebugLogMode?: LoggerReportMode;
    /**
     * 上报质量数据的模式
     */
    reportQualityMode?: LoggerReportMode;
    /**
     * 上报 debug 日志的等级过滤
     */
    reportLevelMask?: Level;
    /**
     * 在 Console 打印 debug 日志的等级过滤
     */
    printLevelMask?: Level;
};

/**
 * 日志数据上报模式
 */
export declare enum LoggerReportMode {
    /**
     * 总是上报
     */
    AlwaysReport = "alwaysReport",
    /**
     * 禁止上报
     */
    BanReport = "banReport",
    /**
     * 根据控制台配置来决定是否上报
     */
    DependsOnRemote = "dependsOnRemote",
}

export declare function setAsyncModuleLoadMode(mode: AsyncModuleLoadMode): void;

export declare enum AsyncModuleLoadMode {
    /**
     * 禁止所有缓存操作，每次加载模块的时候都从网上下载
     */
    DisableCache = "disableCache",
    /**
     * 将模块以 blob 的形式缓存在 indexDB
     */
    StoreAsBlob = "storeAsBlob",
    /**
     * 将模块以 base64 字符串的形式缓存在 indexDB
     */
    StoreAsBase64 = "storeAsBase64",
}

export declare enum ViewMode {
    /**
     * 自由模式。不跟随任何人，也不被任何人跟随，自己可以自由操作视角。
     */
    Freedom = "freedom",
    /**
     * 跟随模式。跟随当前房间的主播视角。
     */
    Follower = "follower",
    /**
     * 主播模式。可以自由操作视角，但房间中处于跟随模式的人会跟随。
     */
    Broadcaster = "broadcaster",
}

export declare enum RenderEngine {
    /**
     * 以 svg 的形式渲染
     */
    SVG = "svg",
    /**
     * 以 canvas 的形式渲染
     */
    Canvas = "canvas",
}

/**
 * 判断是否为实时房间
 */
export declare function isRoom(displayer: Displayer): boolean;

/**
 * 判断是否为回放录像播放器
 */
export declare function isPlayer(displayer: Displayer): boolean;

export declare interface Callbacks<CALLBACKS> {
    /**
     * 注册回调函数
     * @param name 注册函数名
     * @param listener 回调函数
     */
    on<NAME extends string>(name: NAME, listener: any): void;

    /**
     * 注册只调用一次的回调函数
     * @param name 注册函数名
     * @param listener 回调函数
     */
    once<NAME extends string>(name: NAME, listener: any): void;

    /**
     * 注销回调函数
     * @param name 注册函数名
     * @param listener 回调函数
     */
    off<NAME extends string>(name?: NAME, listener?: any): void;

}

/**
 * 默认热键配置
 *
 * | 键盘按键                | 效果                     |
 * | :--------------------- | :---------------------- |
 * | Backspace / Delete     | 删除所选对象              |
 * | Shift                  | 锁定放缩长宽比，令其等比放缩 |
 * | ctrl + z / command + z | 撤回                     |
 * | ctrl + y / command + y | 重做                    |
 * | ctrl + c / command + c | 复制                     |
 * | ctrl + v / command + v | 粘贴                    |
 */
export declare const DefaultHotKeys: Partial<HotKeys>;

export declare type HotKeys = {
    /**
     * 复刻
     */
    duplicate: HotKey;
    /**
     * 复制
     */
    copy: HotKey;
    /**
     * 粘贴
     */
    paste: HotKey;
    /**
     * 撤回
     */
    undo: HotKey;
    /**
     * 重做
     */
    redo: HotKey;
    /**
     * 删除
     */
    delete: HotKey;
    /**
     * 锁定放缩比例
     */
    lock: HotKey;
    /**
     * 切换到选择工具（selector）
     */
    changeToSelector: HotKey;
    /**
     * 切换到激光笔（laserPointer）
     */
    changeToLaserPointer: HotKey;
    /**
     * 切换到铅笔工具（pencil）
     */
    changeToPencil: HotKey;
    /**
     * 切换到矩形工具（rectangle）
     */
    changeToRectangle: HotKey;
    /**
     * 切换到圆形工具（ellipse）
     */
    changeToEllipse: HotKey;
    /**
     * 切换到橡皮工具（eraser）
     */
    changeToEraser: HotKey;
    /**
     * 切换到直线工具（straight）
     */
    changeToStraight: HotKey;
    /**
     * 切换到箭头工具（arrow）
     */
    changeToArrow: HotKey;
    /**
     * 切换到抓手工具（handle）
     */
    changeToHand: HotKey;
};

export declare type HotKey = string | HotKeyDescription | ReadonlyArray<string | HotKeyDescription> | HotKeyChecker;

export declare type HotKeyDescription = {
    key: string;
    altKey: boolean | null;
    ctrlKey: boolean | null;
    shiftKey: boolean | null;
};

export declare type HotKeyEvent = {
    nativeEvent?: KeyboardEvent;
    kind: "KeyDown" | "KeyUp";
    key: string;
    altKey: boolean;
    ctrlKey: boolean;
    shiftKey: boolean;
};

export declare type HotKeyChecker = (event: HotKeyEvent, kind: KeyboardKind)=>boolean;

/**
 * 创建 PPT 转换任务
 */
export declare function createPPTTask(params: PPTTaskParams): PPTTask;

export declare type PPT = {
    /**
     * 转换任务的唯一识别符 ``taskUUID``
     */
    uuid: string;
    /**
     * 转换任务的 Token
     */
    kind: PPTKind;
    /**
     * PPT 的宽
     */
    width: number;
    /**
     * PPT 的高
     */
    height: number;
    /**
     * PPT 对应的场景描述列表
     */
    scenes: ReadonlyArray<SceneDefinition>;
};

export declare interface PPTTask {
    /**
     * 转换任务的唯一识别符 ``taskUUID``
     */
    readonly uuid: string;

    /**
     * 转换任务类型
     */
    readonly kind: PPTKind;

    /**
     * 回调函数
     */
    readonly callbacks: Callbacks<PPTTaskCallbacks>;

    /**
     * 自动轮询，等到该任务成功或失败
     */
    checkUtilGet(): Promise<PPT>;

}

export declare enum PPTKind {
    /**
     * PPT 转网页
     */
    Dynamic = "dynamic",
    /**
     * PPT 转图片
     */
    Static = "static",
}

export declare enum PPTTaskStatus {
    /**
     * 正在等待转换
     */
    Waiting = "Waiting",
    /**
     * 正在转换
     */
    Converting = "Converting",
}

export declare enum PPTTaskStep {
    /**
     * 提取资源
     */
    Extracting = "Extracting",
    /**
     * 打包
     */
    Packaging = "Packaging",
    /**
     * 生成预览图
     */
    GeneratingPreview = "GeneratingPreview",
    /**
     * 媒体文件转换
     */
    MediaTranscode = "MediaTranscode",
}

export declare type PPTTaskProgress = {
    /**
     * 转换任务状态
     */
    status: PPTTaskStatus;
    /**
     * 转换进行到的步骤
     */
    currentStep?: PPTTaskStep;
    /**
     * 全部页数
     */
    totalPageSize: number;
    /**
     * 已转换完的页数
     */
    convertedPageSize: number;
    /**
     * 转换进度（百分比）
     */
    convertedPercentage: number;
};

export declare type PPTTaskParams = {
    /**
     * 转换任务的唯一识别符 ``taskUUID``
     */
    uuid: string;
    /**
     * 转换所在的数据中心区域
     */
    region?: string;
    /**
     * 转换任务类型
     */
    kind: PPTKind;
    /**
     * 转换任务的 Token
     */
    taskToken: string;
    /**
     * 轮循转换任务状态的时间间隔（毫秒）
     */
    checkProgressInterval?: number;
    /**
     * 轮循转换任务的超时时间（毫秒）
     */
    checkProgressTimeout?: number;
    /**
     * 回调函数
     */
    callbacks?: PPTTaskCallbacks;
};

export declare type PPTTaskCallbacks = {
    /**
     * 转换任务的进度状态更新
     */
    onProgressUpdated: (progress: PPTTaskProgress)=>void;
    /**
     * 转换任务成功
     */
    onTaskSuccess: (result: PPT)=>void;
    /**
     * 转换任务失败
     */
    onTaskFail: (error: Error)=>void;
};

/**
 * @deprecated
 */
export declare interface LegacyPPTConverter {
    convert(params: LegacyPPTConvertParams): Promise<LegacyPPT>;

}

/**
 * @deprecated
 */
export declare type LegacyPPT = {
    uuid: string;
    kind: PPTKind;
    width: number;
    height: number;
    slideURLs: ReadonlyArray<string>;
    scenes: ReadonlyArray<SceneDefinition>;
};

/**
 * @deprecated
 */
export declare type LegacyPPTConvertParams = {
    url: string;
    kind: PPTKind;
    onProgressUpdated?: (progress: number)=>void;
    checkProgressInterval?: number;
    checkProgressTimeout?: number;
};

/**
 * 镜头拉近、拉远限定在特定值 // TODO 和 camerabound 的宽高无关，缩放比例 调用两该方法，传 maxscale 和 minscale，限定上限和下限，也可以只传一个
 *
 * @param scale 限定所在的特定值
 */
export declare function contentModeScale(scale: number): ContentMode;

/**
 * 镜头拉到让屏幕刚好能显示满限定区域 //  TODO camera bound （视角边界）在屏幕中居中，确保长边先顶到屏幕的短边，正好完全显示，不进行拉伸，也没有裁剪
 */
export declare function contentModeAspectFit(): ContentMode;

/**
 * 镜头拉到让屏幕刚好能显示满限定区域
 * @param scale 再在次基础上放缩特定比例 // TODO 基于上一个方法，再进行一个放缩 （最后本身不带居中）
 */
export declare function contentModeAspectFitScale(scale: number): ContentMode;

/**
 * 镜头拉到让屏幕刚好能显示满限定区域
 * @param space 在此基础上再预留特定长度的空隙 // TODO 在 contentModeAspectFit 基础上，camera bound 上下左右加一定的空隙 多不减，少会加
 */
export declare function contentModeAspectFitSpace(space: number): ContentMode;

/**
 * 镜头拉到让限定区域刚好完整地现实在屏幕上 // TODO 按 camera bound 的宽高比缩放，填充满整个屏幕，多出的部分会采集，短边对屏幕的长边
 */
export declare function contentModeAspectFill(): ContentMode;

/**
 * 镜头拉到让限定区域刚好完整地现实在屏幕上 // 在 contentModeAspectFill 基础上缩放
 * @param scale 再在次基础上放缩特定比例
 */
export declare function contentModeAspectFillScale(scale: number): ContentMode;

export declare type CameraBound = {
    /**
     * 当画面突破边界时的阻尼大小，取值范围为 0.0 ~ 1.0
     * 越大，阻尼越大，取 1.0 时，无论怎么拉，视角都无法突破边界
     * 越小，阻尼越小，取 0.0 时，视角可以被毫无阻力地拉出边界，只有松手的时候才会弹回
     * 默认值是 0.75
     */
    damping?: number;
    /**
     * 边界的中心点在世界坐标系中的 x 坐标，默认值是 0.0
     */
    centerX?: number;
    /**
     * 边界的中心点在世界坐标系中的 y 坐标，默认值是 0.0
     */
    centerY?: number;
    /**
     * 边界在世界坐标系中的宽，如果取 Infinity，则表示不对横向设限制
     * 默认值是 Infinity
     */
    width?: number;
    /**
     * 边界在世界坐标系中的高，如果取 Infinity，则表示不对纵向设限制
     * 默认值是 Infinity
     */
    height?: number;
    /**
     * 对视角放大的限制模式，不填是没有限制
     */
    maxContentMode?: ContentMode;
    /**
     * 对视角缩小的限制模式，不填是没有限制
     */
    minContentMode?: ContentMode;
};

/**
 * 镜头拉近、拉远限制
 * @param screenSize 当前屏幕的尺寸
 * @param boundSize 镜头限定区域在世界坐标系上的尺寸
 * @returns 在当前尺寸下，应该将 scale 限定在哪个值
 */
export declare type ContentMode = (screenSize: Size, boundSize: Size)=>number;

export declare enum ScenePathType {
    /**
     * 该场景不存在
     */
    None = "none",
    /**
     * 场景组
     */
    Dir = "dir",
    /**
     * 场景
     */
    Page = "page",
}

export declare type SceneMap = {
    /**
     * 场景目录路径: 该目录下的场景列表
     */
    [dirPath: string]: WhiteScene[];
};

export declare interface Displayer<CALLBACKS extends DisplayerCallbacks = DisplayerCallbacks> {
    /**
     * 回调函数，你可以通过如下方式来操作回调函数。
     *
     * @example
     * ```typescript
     * function sliceChangeCallback(slice) {
     *     // 监听到 onSliceChanged 回调
     * }
     *
     * // 注册回调函数
     * displayer.callbacks.on("onSliceChanged", sliceChangeCallback);
     *
     * // 注销回调函数
     * displayer.callbacks.off("onSliceChanged", sliceChangeCallback);
     *
     * displayer.callbacks.once("onSliceChanged", function(slice) {
     *     // 仅仅回调一次
     * });
     * ```
     */
    readonly callbacks: Callbacks<CALLBACKS>;

    /**
     * 当前观察者的用户 ID。
     * 若是实时房间，观察者是我自己。若是回放房间，观察者是当前跟随视角的用户，如果当前没有跟随任何用户的视角，则取值 AdminObserverId。
     */
    readonly observerId: number;

    /**
     * 当前房间所在的区域
     */
    readonly region: string;

    /**
     * 当前所处的 ``slice`` 的 UUID
     */
    readonly slice: string;

    /**
     * 该客户端的设备类型，决定 SDK 如何处理鼠标事件和触碰事件
     */
    readonly deviceType: DeviceType;

    /**
     * 该客户端的屏幕类型，用于调整手势识别参数
     */
    readonly screenType: ScreenType;

    /**
     * 状态
     */
    readonly state: DisplayerState;

    /**
     * 当前是否允许对房间进行写操作
     */
    readonly enableWriteNow: boolean;

    /**
     * 按下键盘上哪个键能唤出抓手工具抓住屏幕以供用户拖动，若为 ``undefined`` 则关闭该功能
     */
    readonly handToolKey: string | undefined;

    /**
     * 是否启动抓手工具抓住屏幕以供用户拖动
     */
    handToolActive: boolean;

    /**
     * 是否隐藏其他人鼠标移动到白板组建上时，显示的高亮框。
     * 该属性不会影响自己的高亮框显示。
     */
    disableOthersSelectingBox: boolean;

    /**
     * 是否禁止设备主动操作视角。
     * 禁止后，用户无法使用鼠标滚轮，或在 touch 设备上通过多指手势来改变视角。
     * 该属性不会影响 ``setCameraBound``、``moveCamera``、``moveCameraToContain`` 方法的使用。
     */
    disableCameraTransform: boolean;

    /**
     * 仅当 ``autoResize: false`` 时调用有意义。
     * 当白板的尺寸改变后，必须主动调用该方法让其同步。
     */
    refreshViewSize(): void;

    /**
     * 修改 ``cameraBound`` 以调整视角边界
     */
    setCameraBound(cameraBound: CameraBound): void;

    /**
     * 获取房间内特定用户的自定义状态
     * @param memberId 用户的 ID
     */
    memberState(memberId: number): MemberState;

    /**
     * 移动视角
     */
    moveCamera(camera: Partial<Camera> & Readonly<{
        animationMode?: AnimationMode;
    }>): void;

    /**
     * 移动视角以容纳特定的视觉矩形
     */
    moveCameraToContain(rectangle: Rectangle & Readonly<{
        animationMode?: AnimationMode;
    }>): void;

    /**
     * 将白板绑定到 HTML 元素上
     * @param element 用于容纳白板的 HTML 元素容器。若为 ``null``，表明解除对白板的绑定。
     */
    bindHtmlElement(element: HTMLDivElement | null): void;

    /**
     * 获取特定的不可件插件
     */
    getInvisiblePlugin<A extends Object>(kind: string): InvisiblePlugin<A> | null;

    /**
     * 点的坐标系转换
     * @param point 屏幕坐标系上的点，以屏幕坐上角为原点。
     * @returns 世界坐标系上的点
     */
    convertToPointInWorld(point: {
        x: number;
        y: number;
    }): {
        x: number;
        y: number;
    };

    /**
     * 生成场景预览
     * @param  scenePath 想要获取预览内容的场景的场景路径
     * @param  div 想要展示预览内容的 div
     * @param  width 白板的缩放宽度：将当前白板内容，缩小到真实像素宽度。2.3.8 后，该参数为可选参数，如果不填，则默认为展示内容 div 的宽度。
     * @param  height 白板的缩放高度：将当前白板的内容，缩小到真实像素高度。2.3.8 后，该参数为可选参数，如果不填，则默认为展示内容 div 的高度。
     */
    scenePreview(scenePath: string, div: HTMLElement, width: number | undefined, height: number | undefined): void;

    /**
     * 生成屏幕快照
     */
    generateScreenshot(scenePath?: string, width?: number, height?: number): Promise<string>;

    /**
     * 生成屏幕快照
     */
    fillSceneSnapshot(scenePath: string, div: HTMLElement, width: number, height: number): void;

    /**
     * 添加自定义事件监听器
     * @param event 自定义事件名
     * @param listener 自定义事件监听器
     */
    addMagixEventListener(event: string, listener: EventListener): void;

    /**
     * 添加自定义事件监听器
     * @param event 自定义事件名
     * @param listener 自定义事件监听器
     * @param fireInterval 限定回调间隔（毫秒），如果回调频率过高，会将调用频率降低到每经过一段间隔事件统一调用一次
     *
     * @example
     * ```typescript
     * function listener(events) {
     *     // 注意，事件会被攒起来，因此 events 是一个数组
     *     for (const event of events) {
     *         // 回调事件 event
     *     }
     * }
     * displayer.addMagixEventListener("my-event", listener, 100);
     * ```
     */
    addMagixEventListener(event: string, listener: EventsListener, fireInterval: number): void;

    /**
     * 注销自定义事件监听器
     * @param event 自定定义事件名
     * @param listener 要注销的监听器，若不传，则将该自定义事件之下的所有监听器全部注销
     */
    removeMagixEventListener(event: string, listener?: EventListener): void;

    /**
     * 等待直到特定的自定义事件发生
     * @param filter 用于判断是否是想要的自定义事件，若是，该方法应该返回 ``true``。
     * @returns 当特定自定义事件发生时，返回该事件
     */
    waitMagixEvent(filter: EventFilter): Promise<Event>;

    /**
     * 调整视角以将 PPT 充满屏幕。若当前场景没有 PPT，则什么都不做。
     * @param animationMode 调整视角时的过渡动画模式
     */
    scalePptToFit(animationMode?: AnimationMode): void;

    /**
     * 查看特定场景的类型
     * @param path 该场景的路径
     */
    scenePathType(path: string): ScenePathType;

    /**
     * 获取当前房间中所有场景信息
     */
    entireScenes(): SceneMap;

}

export declare type DisplayerState = {
    /**
     * 房间的公共全局变量。房间里所有人看到的都是同一份。
     */
    globalState: GlobalState;
    /**
     * 房间的所有可读写用户列表
     */
    roomMembers: ReadonlyArray<RoomMember>;
    /**
     * 场景状态
     */
    sceneState: SceneState;
    /**
     * 视角状态
     */
    cameraState: CameraState;
};

export declare type DisplayerCallbacks = {
    /**
     * 表示 ``displayer.enableWriteNow`` 发生了改变。
     */
    onEnableWriteNowChanged: (enableWriteNow: boolean)=>void;
    /**
     * 表示 ``displayer.handToolKey`` 发生了改变。
     */
    onHandToolActive: (active: boolean)=>void;
    /**
     * ``displayer.slice`` 发生了变化。
     */
    onSliceChanged: (slice: string)=>void;
    /**
     * 表示某个用户（``userId`` 所指示的用户）的行为在同步过来的时候报错了。
     * 一般而言，这个报错通常是可以忽略的，具体请根据业务情况来自行决定是否监听它。
     *
     * @param userId 哪个用户同步时报的错
     * @param error 错误对象
     */
    onCatchErrorWhenAppendFrame: (userId: number, error: Error)=>void;
    /**
     * 表示渲染画面时发生了错误。
     */
    onCatchErrorWhenRender: (error: Error)=>void;
    onRenderDuration: (renderDuration: number)=>void;
    /**
     * 加载转网页 PPT 的进度回调。
     *
     * @param uuid 对应 PPT 的 ``taskUUID``
     * @param progress 进度，一个 0.0 ～ 1.0 的实数
     */
    onPPTLoadProgress: (uuid: string, progress: number)=>void;
    /**
     * 表明转网页 PPT 中某个 ``shape`` 对应的媒体资源开始播放了
     */
    onPPTMediaPlay: (shapeId: string, type: MediaType)=>void;
    /**
     * 表明转网页 PPT 中某个 ``shape`` 对应的媒体资源暂停了
     */
    onPPTMediaPause: (shapeId: string, type: MediaType)=>void;
};

export declare interface Room extends Displayer {
    /**
     * 房间的 ``uuid``，用于唯一标识该房间。
     */
    readonly uuid: string;

    /**
     * 房间当前的 Session 的 ``uuid``。会被上报作为日志字段（如果开启了自动日志上报功能）。如果当前尚未与服务器建立连接，则为 ``undefined``。
     */
    readonly session: string;

    /**
     * 房间 Token，即加入房间时传入的字段。
     */
    readonly roomToken: string;

    /**
     * 房间连接所处的阶段
     */
    readonly phase: RoomPhase;

    /**
     * 业务状态
     * 阅读 [《房间与回放的业务状态管理》](/javascript-zh/home/business-state-management) 、[《房间业务状态管理》](/document-zh/home/business-state-management) 以了解更多。
     */
    readonly state: RoomState;

    /**
     * 房间是否「可写」
     */
    readonly isWritable: boolean;

    /**
     * 可撤销步骤。即此时此刻，还可以调用多少次 ``room.undo()`` 方法。
     */
    readonly canUndoSteps: number;

    /**
     * 可重做步骤。即此时此刻，还可以调用多少次 ``room.redo()`` 方法。
     */
    readonly canRedoSteps: number;

    /**
     * 是否禁止设备操作。
     * 注意区分「只读模式」与「禁止设备操作」，具体参考进阶教程[《禁止设备操作｜禁止操作》](/javascript-zh/home/disable#禁止设备操作)。
     */
    disableDeviceInputs: boolean;

    /**
     * 是否禁止橡皮擦除图片
     */
    disableEraseImage: boolean;

    /**
     * 是否禁止如下方法（默认值是 ``true``）。可修改。
     *
     * - ``room.redo``
     * - ``room.undo``
     * - ``room.duplicate``
     * - ``room.copy``
     * - ``room.paste``
     *
     * 房间内所有人的 ``white-web-sdk`` 的版本必须不低于 ``2.9.3`` 时，才能将该值设为 ``false``。房间内任何一个人将该值设为 ``false``，都会导致房间内低于 ``2.9.3`` 的用户前端报错且不能正常使用。
     */
    disableSerialization: boolean;

    /**
     * 主动延迟接收的远端消息，单位是毫秒，默认值 ``0``。将该值设置成大于 ``0`` 的数字，可以人为造成类似网络时延的效果。可修改。
     */
    timeDelay: number;

    /**
     * 修改 ``room.isWritable`` 的值。
     * 该操作会向服务器发起请求，因此会返回一个 ``Promise`` 对象。注意通过 ``.catch()`` 的方式处理异常流程，因为请求可能被服务器拒绝，或失败。
     */
    setWritable(isWritable: boolean): Promise<void>;

    /**
     * 用于修改房间的 ``globalState`` 的字段。可以通过如下方法修改特定字段。
     *
     * @example
     * ```typescript
     * room.setGlobalState({
     *     foobar: "hello world",
     * });
     * ```
     *
     * 也可以通过将某个字段置为 ``undefined``，来删掉该字段。
     *
     * @example
     * ```typescript
     * room.setGlobalState({
     *     foobar: undefined,
     * });
     * ```
     */
    setGlobalState(modifyState: Partial<GlobalState>): GlobalState;

    /**
     * 用于修改房间的 ``memberState`` 的字段。可以通过如下方法修改特定字段。
     *
     * @example
     * ```typescript
     * room.setMemberState({
     *     foobar: "hello world",
     * });
     * ```
     *
     * @example
     * 也可以通过将某个字段置为 ``undefined``，来删掉该字段。
     *
     * ```typescript
     * room.setMemberState({
     *     foobar: undefined,
     * });
     * ```
     */
    setMemberState(modifyState: Partial<MemberState>): MemberState;

    /**
     * 修改房间的 ``room.state.broadcastState.mode`` 的值。会改变自己或房间内其他人的视角模式。
     */
    setViewMode(viewMode: ViewMode): void;

    /**
     * 修改当前场景地址，会将切换当前房间的场景。
     */
    setScenePath(scenePath: string): void;

    /**
     * 修改当前场景的索引号，会将切换当前房间的场景。
     */
    setSceneIndex(index: number): void;

    /**
     * 向房间内广播自定义事件。其中，第一参数 ``event`` 是事件的名称，房间内其他成员可以通过它注册监听事件。
     * 第二参数 ``payload`` 是负载，可以是任意类型的值，可根据具体业务自行设计。
     */
    dispatchMagixEvent(event: string, payload: any): void;

    /**
     * 在房间中插入场景。参考[《插入新场景｜场景管理》](/javascript-zh/home/scenes-management#插入新场景)。
     * @param path 想要插入到的场景组地址
     * @param scenes 场景描述列表
     * @param index 从哪个索引所在位置插入
     */
    putScenes(path: string, scenes: ReadonlyArray<SceneDefinition>, index?: number): void;

    /**
     * 清理当前场景内所有内容。第一参数 ``retianPpt``
     * @param retainPpt 表明是否保留 PPT 背景不被清理，默认值是 ``true``
     */
    cleanCurrentScene(retainPpt?: boolean): void;

    /**
     * 在房间中删除场景。参考[《删除场景｜场景管理》](/javascript-zh/home/scenes-management#删除场景)。
     */
    removeScenes(path: string): void;

    /**
     * 在房间中移动场景。参考[《移动场景｜场景管理》](/javascript-zh/home/scenes-management#移动场景)。
     */
    moveScene(originalPath: string, targetPath: string): void;

    /**
     * 在当前场景中插入图片
     * 该操作往往需要和 ``room.completeImageUpload`` 配合使用，具体例子可参考[《插入图片｜教具》](/javascript-zh/home/tools#插入图片)。
     */
    insertImage(imageInfo: ImageInformation): void;

    /**
     * 改变图片的锁定状态。若图片被锁定，则无法被框选、无法被拖动、无法被改变大小
     * @param uuid 图片的 UUID
     */
    lockImage(uuid: string, locked: boolean): void;

    /**
     * 设置图片资源。第一参数 ``uuid`` 是图片的唯一标识符，应该与 ``room.insertImage`` 中传入的 ``uuid`` 字段相同。
     * 第二参数 ``src`` 是图片资源的 URL。具体例子可参考[《插入图片｜教具》](/javascript-zh/home/tools#插入图片)。
     */
    completeImageUpload(uuid: string, src: string): void;

    createInvisiblePlugin<K extends string, A extends Object, P extends InvisiblePlugin<A>>(pluginClass: InvisiblePluginClass<K, A, P>, attributes: A): Promise<InvisiblePlugin<A>>;

    insertPlugin(kind: string, description?: PluginDescription): Identifier;

    removePlugin(identifier: Identifier): boolean;

    updatePlugin(identifier: Identifier, description: PluginDescription): boolean;

    getPluginAttributes(identifier: Identifier): any | undefined;

    getPluginRectangle(identifier: string): Rectangle | undefined;

    /**
     * 复刻当前选择工具所选的所有组键件。只有在 ``room.disableSerialization`` 为 ``false`` 的时候可调用。
     */
    duplicate(): void;

    /**
     * 复制当前选择工具所选的所有组键件。只有在 ``room.disableSerialization`` 为 ``false`` 的时候可调用。
     */
    copy(): void;

    /**
     * 粘贴上一次复制的组件。只有在 ``room.disableSerialization`` 为 ``false`` 的时候可调用。
     */
    paste(): void;

    /**
     * 撤回上一个动作。只有在 ``room.disableSerialization`` 为 ``false`` 的时候可调用。
     */
    undo(): number;

    /**
     * 重做上一个动作。只有在 ``room.disableSerialization`` 为 ``false`` 的时候可调用。
     */
    redo(): number;

    /**
     * 删除当前选择工具所选的所有组件。
     */
    delete(): void;

    /**
     * PPT 翻页到下一页。如果当前 PPT 没有动画，则切换到顺位索引 +1 的场景，如果有动画，则播放下一个动画。
     */
    pptNextStep(): void;

    /**
     * PPT 翻页到前一页。如果当前 PPT 没有动画，则切换到顺位索引 -1 的场景，如果有动画，则切换到上一个动画播放完毕的样子。
     */
    pptPreviousStep(): void;

    /**
     * 令房间断开连接。这是一个异步方法，会返回 ``Promise`` 对象。
     */
    disconnect(): Promise<void>;

}

export declare enum RoomPhase {
    /**
     * 连接中
     */
    Connecting = "connecting",
    /**
     * 已连接
     */
    Connected = "connected",
    /**
     * 正在重连
     */
    Reconnecting = "reconnecting",
    /**
     * 断开连接中
     */
    Disconnecting = "disconnecting",
    /**
     * 已断开连接
     */
    Disconnected = "disconnected",
}

export declare enum RoomErrorLevel {
    /**
     * 当无权写时执行写操作，则直接抛出错误
     */
    ThrowError = "throwError",
    /**
     * 当无权写时执行写操作，拦截操作，并在控制台打印警告
     */
    Warn = "warn",
    /**
     * 当无权写时执行写操作，拦截操作，什么也不做
     */
    Ignore = "ignore",
}

export declare type RoomCallbacks = DisplayerCallbacks & {
    /**
     * 表明 ``room.phase`` 发生了变化。
     * 它会传一个 ``phase`` 参数，其含义可参考[《实时房间状态管理》](/document-zh/home/room-state-management)。
     */
    onPhaseChanged: (phase: RoomPhase)=>void;
    /**
     * 表明 ``room.state`` 发生了变化。
     * 具体参考[《房间与回放的业务状态管理》](/javascript-zh/home/business-state-management)。
     */
    onRoomStateChanged: (modifyState: Partial<RoomState>)=>void;
    /**
     * 实时房间出错了，并因此断开了连接
     */
    onDisconnectWithError: (error: Error)=>void;
    /**
     * 被提出房间，并因此断开了连接。
     *
     * @param reason 被踢出房间的原因
     *
     * | ``reason`` 字段 | 描述                          |
     * | :--------------| :--------------------------- |
     * | kickByAdmin    | 被管理员踢出                   |
     * | roomDelete     | 房间被删除                     |
     * | roomZombie     | 房间不活跃                     |
     * | roomBan        | 房间被封禁                     |
     * | GatewayAdjust  | 网关调整                       |
     * | replaceByOther | 被另一个用户顶替，当前用户被迫下线 |
     * | crash          | 服务器崩溃                     |
     */
    onKickedWithReason: (reason: string)=>void;
    /**
     * 表明 ``room.canUndoSteps`` 发生了变化
     */
    onCanUndoStepsUpdate: (canUndoSteps: number)=>void;
    /**
     * 表明 ``room.canRedoSteps`` 发生了变化
     */
    onCanRedoStepsUpdate: (canUndoSteps: number)=>void;
    /**
     * 拦截器，声明是否拦截掉白板的监听的键盘事件，若返回 ``true`` 则拦截掉。拦截掉后，该键盘事件不会引发白板的业务响应。
     */
    willInterceptKeyboardEvent: (event: KeyboardEvent)=>boolean;
    /**
     * 白板监听到了键盘按下事件。
     */
    onKeyDown: (event: KeyboardEvent)=>void;
    /**
     * 白板监听到了键盘松开事件。
     */
    onKeyUp: (event: KeyboardEvent)=>void;
};

export declare type SceneDefinition = {
    /**
     * 场景名字
     */
    name?: string;
    /**
     * PPT 描述
     */
    ppt?: PptDescription;
};

export declare interface Player extends Displayer {
    /**
     * 录像所属的房间 UUID
     */
    readonly roomUUID: string;

    /**
     * 当前录像所属的分片唯一识别符
     */
    readonly slice: string;

    /**
     * 当前是否能立即播放（可能会缓冲）
     */
    readonly isPlayable: boolean;

    /**
     * 回放播放器连接的阶段
     */
    readonly phase: PlayerPhase;

    /**
     * 业务状态
     * 阅读 [《房间与回放的业务状态管理》](/javascript-zh/home/business-state-management) 、[《房间业务状态管理》](/document-zh/home/business-state-management) 以了解更多。
     */
    readonly state: PlayerState;

    /**
     * 播放进度（毫秒），录像开始时为 0
     */
    readonly progressTime: number;

    /**
     * 录像时长（毫秒）
     */
    readonly timeDuration: number;

    /**
     * 录像总帧数
     */
    readonly framesCount: number;

    /**
     * 录像开始的绝对时间戳（unix 时间，毫秒）
     */
    readonly beginTimestamp: number;

    /**
     * 播放速度倍率，取 1.0 表明正常速度播放
     */
    playbackSpeed: number;

    /**
     * @deprecated 请使用 progressTime 代替
     * @see progressTime
     */
    readonly scheduleTime: number;

    /**
     * 播放
     */
    play(): void;

    /**
     * 暂停
     */
    pause(): void;

    /**
     * 停止
     */
    stop(): void;

    /**
     * 跳转到特定进度
     */
    seekToProgressTime(progressTime: number): void;

    /**
     * 修改观察模式
     */
    setObserverMode(observerMode: ObserverMode): void;

    /**
     * @deprecated 请使用 seekToProgressTime 代替
     * @see seekToProgressTime
     */
    seekToScheduleTime(scheduleTime: number): void;

}

export declare enum PlayerPhase {
    /**
     * 等待首帧
     */
    WaitingFirstFrame = "waitingFirstFrame",
    /**
     * 正在播放
     */
    Playing = "playing",
    /**
     * 暂停
     */
    Pause = "pause",
    /**
     * 播放器已停止
     */
    Stopped = "stop",
    /**
     * 录像播到末尾
     */
    Ended = "ended",
    /**
     * 正在缓冲
     */
    Buffering = "buffering",
}

export declare type PlayerState = DisplayerState & {
    /**
     * 观察模式
     */
    observerMode: ObserverMode;
};

export declare type PlayerCallbacks = DisplayerCallbacks & {
    /**
     * 表明 ``player.isPlayable`` 发生了改变。
     */
    onIsPlayableChanged: (isPlayable: boolean)=>void;
    /**
     * 表明 ``player.phase`` 发生了改变。
     */
    onPhaseChanged: (phase: PlayerPhase)=>void;
    /**
     * 表明首帧加载完毕，该回调在 ``player`` 整个声明周期中最多调一次。
     * 在首帧加载出来之前，白板不会显示录像中的任何东西，也无法在代码中读取 ``player.state``。
     */
    onLoadFirstFrame: ()=>void;
    /**
     * 表明 ``player.state`` 发生了变化。具体参考[《房间与回放的业务状态管理》](/javascript-zh/home/business-state-management)。
     */
    onPlayerStateChanged: (modifyState: Partial<PlayerState>)=>void;
    /**
     * 回放发生了错误，并因此失败，``player.phase`` 的状态变成了 ``PlayerPhase.Stopped``。
     */
    onStoppedWithError: (error: Error)=>void;
    /**
     * 表明 ``player.progressTime`` 发生了变化。
     */
    onProgressTimeChanged: (progressTimestamp: number)=>void;
};

export declare type BroadcastState = {
    /**
     * 视角跟随模式
     */
    mode: ViewMode;
    /**
     * 当前房间主播的用户 ID。若不存在主播，则为 ``undefined``
     */
    broadcasterId?: number;
    /**
     * @deprecated 过期属性
     */
    broadcasterInformation?: MemberInformation;
};

export declare enum ObserverMode {
    /**
     * 导演模式
     */
    Directory = "directory",
    /**
     * 自由模式
     */
    Freedom = "freedom",
}

export declare type RoomMember = {
    /**
     * 用户 ID
     */
    memberId: number;
    /**
     * 用户的状态
     */
    memberState: MemberState;
    /**
     * 当前用户的 session ID
     */
    session: string;
    /**
     * 当前用户的自定义负载，在该用户调用 ``joinRoom`` 时传入
     */
    payload: any;
};

export declare type RoomState = DisplayerState & {
    /**
     * 我自己的自定义状态，归我所有，我可以读取或修改。
     * 房间里其他人只能读取，不能修改。
     */
    memberState: MemberState;
    /**
     * 视角跟随状态
     */
    broadcastState: Readonly<BroadcastState>;
    /**
     * @deprecated 过期属性
     * 请使用 ``room.cameraState.scale`` 代替
     */
    zoomScale: number;
};

export declare type SceneState = {
    /**
     * 房间内所有场景列表
     */
    scenes: ReadonlyArray<WhiteScene>;
    /**
     * 当前场景地址
     */
    scenePath: string;
    /**
     * 当前场景的名字
     */
    sceneName: string;
    /**
     * 当前场景所在的父场景组地址
     */
    contextPath: string;
    /**
     * 当前场景处于其父场景组之中的位置索引
     */
    index: number;
};

export declare const DisplayerConsumer: Consumer<Displayer>;

export declare const RoomConsumer: Consumer<Room | undefined>;

export declare const PlayerConsumer: Consumer<Player | undefined>;

export declare type MemberState = {
    /**
     * 当前用户所选择的教具
     */
    currentApplianceName: ApplianceNames;
    /**
     * 教具绘制线条的颜色
     */
    strokeColor: Color;
    /**
     * 教具绘制线条的粗细
     */
    strokeWidth: number;
    /**
     * 文字的字体大小
     */
    textSize: number;
    /**
     * 使用 ``shape`` 教具时，绘制图形的具体类型
     */
    shapeType?: ShapeType;
    /**
     * @deprecated 已过期
     */
    pencilOptions: PencilOptions;
};

export declare enum ApplianceNames {
    /**
     * 选择工具
     */
    selector = "selector",
    /**
     * 激光笔
     */
    laserPointer = "laserPointer",
    /**
     * 铅笔工具
     */
    pencil = "pencil",
    /**
     * 矩形工具
     */
    rectangle = "rectangle",
    /**
     * 圆形工具
     */
    ellipse = "ellipse",
    /**
     * 图形工具
     */
    shape = "shape",
    /**
     * 橡皮工具
     */
    eraser = "eraser",
    /**
     * 文字工具
     */
    text = "text",
    /**
     * 直线工具
     */
    straight = "straight",
    /**
     * 箭头工具
     */
    arrow = "arrow",
    /**
     * 抓手工具
     */
    hand = "hand",
}

export declare type PluginProps<C, T> = {
    plugin: PluginInstance<C, T>;
    margin: Margin;
    origin: Point;
    size: Size;
    scale: number;
    cnode?: any;
};

export declare interface PluginInstance<C, A> {
    readonly kind: string;

    readonly identifier: Identifier;

    readonly context: C;

    readonly originX: number;

    readonly originY: number;

    readonly width: number;

    readonly height: number;

    readonly selectable: boolean;

    readonly isDraging: boolean;

    readonly isResizing: boolean;

    readonly isPlaying: boolean;

    readonly playerTimestamp: number;

    readonly playbackSpeed: number;

    attributes: A;

    putAttributes(attributes: Partial<A>): void;

    removeAttributeKeys<K extends string>(...keys: K[]): void;

    update(description: PluginDescription<A>): void;

    remove(): void;

}

export declare type PluginDescription<A = any> = {
    originX?: number;
    originY?: number;
    width?: number;
    height?: number;
    selectable?: boolean;
    attributes?: A;
};

/**
 * 颜色，按照 RGB 的顺序填充颜色到数组中，每个数组的取值是 0~255。
 * 例如 rgb(0,255,17) 对应的 ``Color`` 为 ``[0, 255, 17]``
 */
export declare type Color = number[];

export declare type GlobalState = {
};

export declare type PptDescription = {
    /**
     * PPT 资源的 URL
     */
    src: string;
    /**
     * PPT 的宽
     */
    width: number;
    /**
     * PPT 的高
     */
    height: number;
    /**
     * PPT 的预览图的 URL
     */
    previewURL?: string;
};

export declare type ImageInformation = {
    /**
     * 图片的唯一识别符
     */
    uuid: string;
    /**
     * 图片中点在世界坐标系中的 x 坐标
     */
    centerX: number;
    /**
     * 图片中点在世界坐标系中的 y 坐标
     */
    centerY: number;
    /**
     * 图片中点在世界坐标系中的宽
     */
    width: number;
    /**
     * 图片中点在世界坐标系中的高
     */
    height: number;
    /**
     * 图片是否被锁定
     */
    locked: boolean;
    /**
     * 图片是否禁止非等比放缩
     */
    uniformScale?: boolean;
};

export declare type WorldViewRectangle = {
    originX: number;
    originY: number;
    width: number;
    height: number;
};

export declare function injectCustomStyle(icons: UserCursorIcons): void;

export declare enum CursorNames {
    Hand = "cursor-hand",
    HandGrasp = "cursor-hand-grasp",
    LaserPointer = "cursor-laserPointer",
    Selector = "cursor-selector",
    Pencil = "cursor-pencil",
    Eraser = "cursor-eraser",
    Rectangle = "cursor-rectangle",
    Ellipse = "cursor-ellipse",
    Shape = "cursor-shape",
    Straight = "cursor-straight",
    Arrow = "cursor-arrow",
    Text = "cursor-text",
    Nwse = "cursor-nwse",
    Nesw = "cursor-nesw",
    Ns = "cursor-ns",
    Ew = "cursor-ew",
}

export declare enum ScreenType {
    /**
     * 桌面设备
     */
    Desktop = "desktop",
    /**
     * 只能手机
     */
    Phone = "phone",
    /**
     * 平板电脑
     */
    Pad = "pad",
    /**
     * 电视
     */
    TV = "tv",
}

export declare type WhiteWebSdkConfiguration = {
    /**
     * 从管理控制台获取的参数，用于让 Netless 识别该客户端属于哪个账号的哪个项目，以便处理好权限和计费相关事宜
     * 你可以阅读[《AppIdentifier｜项目与权限》](/home/project-and-authority#appidentifier)来了了解如何获取 appIdentifier
     */
    appIdentifier: string;
    /**
     * 连接的数据中心
     */
    region?: string;
    /**
     * 设置成 ``true`` 后，``displayer.state`` 会变成一个 MobX observable object，其成员变量只读，相当于都被修饰了 ``@observable``。
     * 这意味着，当它的成员发生变化时，你可以用 MobX 的方式监听它的变化并自动响应。
     */
    useMobXState?: boolean;
    /**
     * 该客户端的设备类型，决定 SDK 如何处理鼠标事件和触碰事件。如果填写错误，会导致 SDK 对设备输入响应行为不符合预期。如果不填，则会根据内在逻辑自动判断设备的类型。
     */
    deviceType?: DeviceType;
    /**
     * 该客户端的屏幕类型，用于调整手势识别参数，默认为 ``ScreenType.Desktop``。
     */
    screenType?: ScreenType;
    /**
     * 对画面的渲染模式，默认值为 ``RenderEngine.Canvas``
     */
    renderEngine?: RenderEngine;
    /**
     * 为 PPT 提供自定义字体文件。
     * 如果你使用了 PPT 转网页服务，并且 PPT 中使用了非默认字体，并将转化后的文件在房间里展示。
     * 你必须正确配置这个字段，才能让 PPT 正确展示。
     */
    fonts?: UserFonts;
    /**
     * 抓手工具热键。按下该键时，会自动切换成**抓手工具**（``currentApplianceName="hand"``），松开后，切回原来的工具。如果不传，则关闭该功能。
     */
    handToolKey?: string;
    /**
     * 设置文字工具（``currentApplianceName="text"``）的字体。若不传，文字工具则展示浏览器默认字体
     */
    fontFamily?: string;
    /**
     * 是否一次性加载 PPT 转网页的所有资源，默认是 ``false``。启用后，会在第一页时，就加载所有页面内容，会造成一定性能问题，已不推荐使用。
     */
    preloadDynamicPPT?: boolean;
    /**
     * SDK 如何处理日志上报，默认是开启自动上报。更多关于日志的信息，可以参考[《线上日志》](/document-zh/home/oneline-log)
     */
    loggerOptions?: LoggerOptions;
    /**
     * 断线重连设置，如果传入 ``false`` 或 ``{disableReconnect: true}`` 则可以关闭断线重连。
     * 默认开启自动断线重连。
     * 你可以参考[《实时房间状态管理》](/document-zh/home/room-state-management)了解更多关于断线重连相关的状态变化。
     */
    reconnectionOptions?: Partial<ReconnectionOptions> | false;
    /**
     * 默认为``true``。开启该功能后，若是主动调用``room`` 对象的方法来修改``room.state`` 的值，导致值变化，是不会调用回调方法通知说``room.state`` 发生改变了的。
     *
     * 关闭该功能后，只要``room.state`` 发生改变，就会回调。
     *
     * 如何判断是主动修改``room.state``？
     * 如果调用这些方法：``room.setGlobalState``、``room.setMemberState``、``room.setViewMode``、``room.setScenePath``、``room.setSceneIndex``、``room.moveCamera``、``room.moveCameraToContain``、``room.putScenes``、``room.removeScenes``、``room.moveScene``。
     * 那么通过调用这些方法所修改的字段本身的变化，都是为 ** 主动修改 ** 导致的变化。
     */
    onlyCallbackRemoteStateModify?: boolean;
    /**
     * 插件列表。
     */
    plugins?: Plugins;
    /**
     * 不可见插件列表。
     */
    invisiblePlugins?: ReadonlyArray<InvisiblePluginClass<string, any, any>>;
    /**
     * 默认值为 ``[]``。这是一个装满 ``React.ComponentType`` 类型的数组，用于包装白板的 view。你可以用它对白板的 view 进行自定义包装。例如使用如下代码。
     *
     * @example
     * ```tsx
     * import React from "react";
     *
     * class WrappedCompnent extends React.Component {
     *
     *     render() {
     *         return (
     *             <div>
     *                 <span>hello world</span>
     *                 {this.props.children}
     *             </div>
     *         );
     *     }
     * }
     *
     * const whiteWebSdk = new WhiteWebSdk({
     *     appIdentifier: "$APP_IDENTIFIER",
     *     wrappedComponents: [WrappedCompnent],
     * });
     * ```
     */
    wrappedComponents?: WrappedComponents;
    /**
     * > 2.11.12 新增 useServerWrap 功能
     *
     * 动态 ppt 专用参数。
     * 目前字段 ``useServerWrap`` 默认关闭，后续版本会变更为打开状态。开启后，
     * 会使用服务器端排版结果，避免各平台显示效果不一致。
     * 该功能要求 ppt 在 2021-02-10 进行转换，之前转换的 ppt 没有服务器端排版结果。
     */
    pptParams?: PptParams;
    /**
     * 将白板中的图片等资源的 URL 拦截并替换。例如，如下代码可以自动给所有图片 URL 加一个尾缀。
     *
     * @example
     * ```typescript
     * const whiteWebSdk = new WhiteWebSdk({
     *     appIdentifier: "$APP_IDENTIFIER",
     *     urlInterrupter: function(url) {
     *         return url + "?token=bm1n4pisugWrWK";
     *     },
     * });
     * ```
     */
    urlInterrupter?: (url: string)=>string;
    /**
     * 用于处理 ``WhiteWebSdk`` 初始化失败的回调。
     *
     * @example
     * ```typescript
     * const whiteWebSdk = new WhiteWebSdk({
     *     appIdentifier: "$APP_IDENTIFIER",
     *     onWhiteSetupFailed: function(error) {
     *         console.error(error);
     *     },
     * });
     * ```
     * 如果是因为参数原因构造失败，会在调用 ``new WhiteWebSdk({...})`` 时抛出错误。该回调仅用来处理涉及网络或鉴权失败时的错误处理。
     */
    onWhiteSetupFailed?: (error: Error)=>void;
};

export declare type ConstructRoomParams = {
    /**
     * 设置鼠标光标适配器，参考[《鼠标光标适配器》](/javascript-zh/home/cursor-adapter)。
     */
    cursorAdapter?: CursorAdapter;
    /**
     * 是否关闭「自动适配尺寸」功能，默认为 ``false``。
     * 关闭后，每当白板的 view 的尺寸改变时，必须主动调用 ``room.refreshViewSize()`` 以适配。
     */
    disableAutoResize?: boolean;
    /**
     * 是否禁止设备主动操作视角，默认是 ``false``。
     * 禁止后，用户无法使用鼠标滚轮，或在 touch 设备上通过多指手势来改变视角。
     * 该属性不会影响 ``setCameraBound``、``moveCamera``、``moveCameraToContain`` 方法的使用。
     */
    disableCameraTransform?: boolean;
    /**
     * 不可见插件列表
     */
    invisiblePlugins?: ReadonlyArray<InvisiblePluginClass<string, any, any>>;
    /**
     * 默认值为 ``[]``。这是一个装满 ``React.ComponentType`` 类型的数组，用于包装白板的 view。你可以用它对白板的 view 进行自定义包装。
     */
    wrappedComponents?: WrappedComponents;
    /**
     * 自定义视角边界。设置后，会将视角控制在该属性描述的边界之内。默认值为 ``undefined``。
     * 若为 ``undefined``，则表明视角没有任何限制。
     */
    cameraBound?: CameraBound;
    /**
     * 是否隐藏其他人鼠标移动到白板组建上时，显示的高亮框。默认值是 ``false``。
     * 该属性不会影响自己的高亮框显示。
     */
    disableOthersSelectingBox?: boolean;
};

export declare type JoinRoomParams = ConstructRoomParams & {
    /**
     * 房间的 ``uuid``，在构造房间时会返回，是该房间的唯一标识符。
     */
    uuid: string;
    /**
     * 连接的数据中心。优先级高于 ``WhiteWebSdkConfiguration`` 中的 ``region`` 字段
     */
    region?: string;
    /**
     * 房间的 Token，用于鉴权。阅读[《访问密钥｜项目与鉴权》](/document-zh/home/project-and-authority#访问密钥)以便了解如何签出房间的 Token。
     */
    roomToken: string;
    /**
     * 可以是任意类型的数据结构，用于描述当前用户的自定义信息。房间内用户在加入房间设置的 ``userPayload`` 可被房间内其他用户通过如下代码读取。
     *
     * @example
     * ```javascript
     * for (var member of room.state.roomMembers) {
     *     // 读取房间内某个用户的 userPayload 字段
     *     console.log(member.userPayload);
     * }
     * ```
     */
    userPayload?: any;
    /**
     * 是否以**可写模式**加入房间，默认是 ``true``，若为 ``false`` 则为**只读模式**。
     * 注意区分「只读模式」与「禁止设备操作」，具体参考进阶教程[《只读模式｜禁止操作》](/javascript-zh/home/disable#只读模式)。
     */
    isWritable?: boolean;
    /**
     * 是否禁止设备操作，默认是 ``false``。
     * 注意区分「只读模式」与「禁止设备操作」，具体参考进阶教程[《禁止设备操作｜禁止操作》](/javascript-zh/home/disable#禁止设备操作)。
     */
    disableDeviceInputs?: boolean;
    /**
     * 默认值为 ``false``。表明是否允许使用用 ``pencil`` 工具画点。
     * 如果设置成 ``false``，则当使用 ``pencil`` 工具单击白板绘制的点不会保留在屏幕上。该参数只有在 ``disableNewPencil`` 的值为 ``false`` 时才会起作用。
     */
    enableDrawPoint?: boolean;
    /**
     * 默认值为 ``false``。
     * 是否禁止 2.12.3 以及之后版本的带笔锋的铅笔工具（参考 [《版本历史 - 2.13.3》](/javascript-zh/home/js-changelog#h-2123-2021-03-15)）。
     * 若为 ``true``，则使用 2.12.3 之前的没有笔锋的铅笔工具。
     */
    disableNewPencil?: boolean;
    /**
     * 是否禁止橡皮擦除图片，默认值为 ``false``。
     */
    disableEraseImage?: boolean;
    /**
     * 在用选择工具（``currentApplianceName="selector"``）选中物体后，出现的浮动条。
     * 若为 ``false``（默认值），则永远不会出现浮动条。若为 ``true``，则会出现浮动条。
     */
    floatBar?: boolean | Partial<FloatBarOptions>;
    /**
     * 用于配置热键。若不传，则使用**默认热键**方案，具体如下。
     *
     * | 键盘按键                | 效果                     |
     * | :--------------------- | :---------------------- |
     * | Backspace / Delete     | 删除所选对象              |
     * | Shift                  | 锁定放缩长宽比，令其等比放缩 |
     * | ctrl + z / command + z | 撤回                     |
     * | ctrl + y / command + y | 重做                    |
     * | ctrl + c / command + c | 复制                     |
     * | ctrl + v / command + v | 粘贴                    |
     *
     * 如果你想关闭热键功能，可以将其值置为 ``{}``。
     */
    hotKeys?: Partial<HotKeys>;
    /**
     * 决定了，在房间没有写权限时，如果主动调用了写操作，此时 SDK 应该做何响应
     */
    rejectWhenReadonlyErrorLevel?: RoomErrorLevel;
};

export declare type ReplayRoomParams = ConstructRoomParams & {
    /**
     * 指定回放特定的「录像片段」的 ``uuid``。可以在房间尚在录制的时候，从 ``room.slice`` 获取。
     * 当传入该参数后，表明只回放特定片段，因此禁止再传入 ``room``、``beginTimestamp``、``duration``。
     */
    slice?: string;
    /**
     * 指定特定房间的 ``uuid``，在构造房间时会返回，是该房间的唯一标识符。传入该参数后，禁止传入 ``slice`` 参数。
     * 如果仅传该参数，不传 ``beginTimestamp`` 和 ``duration``，则表明回放该房间的所有录像片段。
     * 如果同时传入，``beginTimestamp`` 和 ``duration``，则表明回放该房间在此范围之内的所有录像片段。
     */
    room?: string;
    /**
     * 连接的数据中心。优先级高于 ``WhiteWebSdkConfiguration`` 中的 ``region`` 字段
     */
    region?: string;
    /**
     * 表明回放开始的时间戳（[unix 时间](https://zh.wikipedia.org/wiki/UNIX%E6%97%B6%E9%97%B4)，单位是毫秒）。
     * 此参数必须和 ``room``、``duration`` 一起使用，且使用时禁止传入 ``slice`` 参数。
     */
    beginTimestamp?: number;
    /**
     * 表明回放区间的时长（单位是毫秒）。此参数必须和 ``room``、``beginTimestamp`` 一起使用，且使用时禁止传入 ``slice`` 参数。
     */
    duration?: number;
    roomToken?: string;
};

export declare type PlayableCheckingParams = {
    /**
     * 连接的数据中心
     */
    region?: string;
    /**
     * 指定特定房间的 ``uuid``，在构造房间时会返回，是该房间的唯一标识符。传入该参数后，禁止传入 ``slice`` 参数。
     * 如果仅传该参数，不传 ``beginTimestamp`` 和 ``duration``，则表明回放该房间的所有录像片段。
     * 如果同时传入，``beginTimestamp`` 和 ``duration``，则表明回放该房间在此范围之内的所有录像片段。
     */
    room?: string;
    /**
     * 指定回放特定的「录像片段」的 ``uuid``。可以在房间尚在录制的时候，从 ``room.slice`` 获取。
     * 当传入该参数后，表明只回放特定片段，因此禁止再传入 ``room``、``beginTimestamp``、``duration``。
     */
    slice?: string;
    /**
     * 表明回放开始的时间戳（[unix 时间](https://zh.wikipedia.org/wiki/UNIX%E6%97%B6%E9%97%B4)，单位是毫秒）。
     * 此参数必须和 ``room``、``duration`` 一起使用，且使用时禁止传入 ``slice`` 参数。
     */
    beginTimestamp?: number;
    /**
     * 表明回放区间的时长（单位是毫秒）。此参数必须和 ``room``、``beginTimestamp`` 一起使用，且使用时禁止传入 ``slice`` 参数。
     */
    duration?: number;
};

export declare class WhiteWebSdk {
    /**
     * 默认所在区域
     * 如果在调用 ``joinRoom``、``replayRoom``、``isPlayable`` 时没有指定 ``region``，则以此属性为准。
     */
    readonly region: string;

    /**
     * 该客户端的设备类型，决定 SDK 如何处理鼠标事件和触碰事件
     */
    readonly deviceType: DeviceType;

    /**
     * 该客户端的屏幕类型，用于调整手势识别参数
     */
    readonly screenType: ScreenType;

    /**
     * 对画面的渲染模式
     */
    readonly renderEngine: RenderEngine;

    constructor(params: WhiteWebSdkConfiguration);

    /**
     * 加入一个实时房间
     * @param params 加入房间的参数
     * @param callbacks 回调函数
     * @returns 异步地返回房间
     * @throws 如果加入房间失败，则会异步地抛出错误
     *
     * @example
     * ```typescript
     * const joinRoomParams = {...};
     * const roomCallbacks = {...};
     *
     * whiteWebSdk.joinRoom(joinRoomParams, roomCallbacks)
     *            .then(function (room) {
     *                // 加入房间成功，拿到 room 对象
     *            })
     *            .catch(function (error) {
     *                // 加入房间失败，拿到 error 对象
     *            });
     * ```
     */
    joinRoom(params: JoinRoomParams, callbacks?: Partial<RoomCallbacks>): Promise<Room>;

    /**
     * 判断房间是否可播放
     */
    isPlayable(params: PlayableCheckingParams): Promise<boolean>;

    /**
     * 回放房间录像
     * @param params 回放房间录像参数
     * @param callbacks 回调函数
     * @returns 异步地返回房间
     * @throws 如果加入房间失败，则会异步地抛出错误
     *
     * @example
     * ```typescript
     * const replayRoomParams = {...};
     * const replayCallbacks = {...};
     *
     * whiteWebSdk.replayRoom(replayRoomParams, replayCallbacks)
     *            .then(function (player) {
     *                // 回放成功，拿到 player 对象
     *            })
     *            .catch(function (error) {
     *                // 回放失败，拿到 error 对象
     *            });
     * ```
     */
    replayRoom(params: ReplayRoomParams, callbacks?: Partial<PlayerCallbacks>): Promise<Player>;

    /**
     * @deprecated
     */
    pptConverter(roomToken: string): LegacyPPTConverter;

    private static netState: any;

    private whiteLoggerFactory: any;

    private qualityLoggerFactory: any;

    private akkoApp: any;

    private boundless: any;

    private plugins: any;

    private invisiblePlugins: any;

    private wrappedComponents: any;

    private preloadDynamicPPT: any;

    private fonts: any;

    private handToolKey: any;

    private fontFamily: any;

    private useMobXState: any;

    private onlyCallbackRemoteStateModify: any;

    private urlInterrupter: any;

    private pptParams: any;

    private standardizeCameraBound: any;

    private enableReportQuality: any;

    private isCanvasRenderEngineAvailable: any;

    private parseAppIdentifier: any;

    private assertPlugins: any;

    private assertInvisiblePlugins: any;

    private isValidInvisiblePluginClass: any;

    private mergeArray: any;

}

export declare enum Scope {
    System = "system",
    App = "app",
    Custom = "custom",
    Magix = "magix",
}

/**
 * @deprecated
 */
export declare type MemberInformation = {
    id: number;
    nickName: string;
    isOwner: boolean;
    avatar?: string;
};

export declare type NativeEvent = MouseEvent | WheelEvent | KeyboardEvent | TouchEvent;

export declare type RenderPlugin<C = {
}, T = any> = {
    kind: string;
    render: ComponentType<PluginProps<C, T>>;
    defaultAttributes?: T;
    hitTest?: (plugin: PluginInstance<C, T>, x: number, y: number, selectorRadius: number)=>boolean;
    willInterruptEvent?: (plugin: PluginInstance<C, T>, event: NativeEvent)=>boolean;
};

export declare enum KeyboardKind {
    Mac = "mac",
    Windows = "windows",
}

export declare type WhiteScene = {
    name: string;
    ppt?: PptDescription;
};

export declare type EventListener = (event: Event)=>void;

export declare type EventsListener = (events: Event[])=>void;

export declare type EventFilter = (event: Event)=>boolean;

export declare type CameraState = Camera & {
    /**
     * 屏幕的宽
     */
    width: number;
    /**
     * 屏幕的高
     */
    height: number;
};

export declare type MediaType = "video" | "audio";

export declare type Identifier = string;

export declare enum ShapeType {
    /**
     * 三角形
     */
    Triangle = "triangle",
    /**
     * 菱形
     */
    Rhombus = "rhombus",
    /**
     * 五角星
     */
    Pentagram = "pentagram",
    /**
     * 说话泡泡
     */
    SpeechBalloon = "speechBalloon",
}

export declare type PencilOptions = {
    enableDrawPoint: boolean;
    /**
     * @deprecated no effect in new pencil
     */
    disableBezier: boolean;
    /**
     * @deprecated no effect in new pencil
     */
    sparseWidth: number;
    /**
     * @deprecated no effect in new pencil
     */
    sparseHump: number;
};

export declare type Margin = {
    top: number;
    bottom: number;
    left: number;
    right: number;
};

export declare type Point = {
    x: number;
    y: number;
};

export declare type UserCursorIcons = {
    [key: string]: ReadonlyArray<string>;
};

export declare type PptParams = {
    scheme?: string;
    rtcClient?: RTCClient;
    useServerWrap?: boolean;
};

export declare type RTCClient = {
    /**
     * filePath: 文件路径，可以是本地文件或者网络地址
     * loopback: true 则音频不通过 rtc 传播
     * replace: true 则只播文件声音，不播麦克风声音，false 则是将文件和麦克风混音
     * cycle: 循环播放文件的次数，-1 是无限循环
     * callback: native 收到回调事件，需要用户注册监听，在接到回调后调用该方法
     * state:
     *  710: 成功调用 startAudioMixing 或 resumeAudioMixing
     *  711: 成功调用 pauseAudioMixing
     *  713: 成功调用 stopAudioMixing
     *  714: 播放失败，error code 会有具体原因
     */
    startAudioMixing: (filePath: string, loopback: boolean, replace: boolean, cycle: number, callback?: (state: number, errorCode: number)=>void)=>number;
    stopAudioMixing: (callback?: (state: number, errorCode: number)=>void)=>number;
    pauseAudioMixing: ()=>number;
    resumeAudioMixing: ()=>number;
    /**
     * 音量为 0 到 100 间整数，默认为 100
     */
    adjustAudioMixingVolume?: (volume: number)=>number;
    adjustAudioMixingPlayoutVolume?: (volume: number)=>number;
    adjustAudioMixingPublishVolume?: (volume: number)=>number;
    getAudioMixingPlayoutVolume?: ()=>number;
    getAudioMixingPublishVolume?: ()=>number;
    getAudioMixingDuration?: ()=>number;
    getAudioMixingCurrentPosition?: ()=>number;
    /**
     * 进度为毫秒
     */
    setAudioMixingPosition: (position: number)=>number;
};


