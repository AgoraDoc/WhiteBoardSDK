/**
 * 断线重连设置。
 * Reconnection configuration.
 */
export declare type ReconnectionOptions = {
    /**
     * 是否开启断线重连：
     * - `true`: 不开启。
     * - `false`: 开启。
     *
     * Whether to enable the reconnection mechanism when the user drops offline:
     * - `true`: Enable the reconnection mechanism.
     * - `false`: Disable the reconnection mechanism.
     */
    disableReconnect: boolean;
};

/**
 * 设备类型。
 * The type of the device.
 */
export declare enum DeviceType {
    /**
     * 桌面设备，即使用键盘鼠标的设备。
     * A desktop device, which supports keyboard and mouse.
     */
    Desktop = "desktop",
    /**
     * 触碰板设备，比如智能手机、平板电脑。
     * A touch screen device, such as a smartphone or tablet.
     */
    Touch = "touch",
    /**
     * 同时支持键盘、鼠标、触碰板的设备。
     * A device that supports keyboard, mouse, and touch screen/touch pad.
     */
    Surface = "surface",
}

/**
 * 由 `React.ComponentType` 类型组成的数组，用于包装白板的界面。默认值为 []。
 * An array of `React.ComponentType` objects, which is used to package the whiteboard view. The default value is [].
 */
export declare type WrappedComponents = ReadonlyArray<ComponentType<{
}>>;

/**
 * Cursor.
 */
export declare interface Cursor {
    /**
     * 光标的容器，在创建之初为空。
     * 你可以通过如下代码向容器中添加 HTML 元素，以改变光标的外观。
     *
     * The container of the cursor, which is empty when the cursor is created.
     * You can use the following code to add HTML elements into the container and change the cursor style.
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
     * 光标所属用户的 ID。
     * The ID of the user operating the cursor.
     */
    readonly memberId: number;

    /**
     * 光标所属用户的一些属性封装。
     * Encapsulated attributes of the user operating the cursor.
     */
    readonly cursorMember: CursorMember;

    /**
     * 光标指示区域中心的 X 轴坐标（以白板左上角为原点）。
     * The X coordinate of the center of the area the cursor is pointing to (taking the left corner of the initial whiteboard as the origin).
     */
    readonly x: number;

    /**
     * 光标指示区域中心的 Y 轴坐标（以白板左上角为原点）。
     * The Y coordinate of the center of the area the cursor is pointing to (taking the left corner of the initial whiteboard as the origin).
     */
    readonly y: number;

    /**
     * 光标图形的宽度。
     * The width of the cursor icon.
     */
    readonly width: number;

    /**
     * 光标图形的高度。
     * The height of the cursor icon.
     */
    readonly height: number;

    onCursorMemberChanged:
    /**
     * `cursorMember` 改变后的回调。
     *
     * 你可以用如下代码监听该回调：
     *
     *
     * Occurs when `cursorMember` changes.
     *
     * You can use the following code to listen for this callback:
     * @example
     * ```typescript
     * cursor.onCursorMemberChanged = function(cursorMember) {
     * }
     * ```
     *
     * @param cursorMember 更新后的 `cursorMember`。
     *                     The updated `cursorMember`.
     */
    (cursorMember: CursorMember)=>void;

    /**
     * 通过注入 JSX 结构来改变光标的外观。
     * Changes the cursor style using JSX.
     *
     * @example
     * ```tsx
     * cursor.setReactNode(
     *     <img src="https://my-resources.com/icon.png"/>
     * );
     * ```
     * @param reactNode 描述光标外观的 JSX。
     *                  The JSX that describes the cursor style.
     */
    setReactNode(reactNode: any): void;

    /**
     * 修改光标的描述。
     * Changes the cursor description.
     *
     * @param description 光标的描述。
     *                    The cursor description.
     */
    setCursorDescription(description: Partial<CursorDescription>): void;

}

/**
 * 光标所属用户的一些属性封装。
 * Encapsulated attributes of the user operating the cursor.
 */
export declare interface CursorMember {
    /**
     * 用户选择的颜色。
     * The stroke color selected by the user.
     */
    readonly color: Color;

    /**
     * 用户选择的白板工具。
     * The whiteboard tool selected by the user.
     */
    readonly appliance: string;

    /**
     * @deprecated
     */
    readonly information: MemberInformation;

}

/**
 * 设置鼠标的光标适配器。
 * Sets the cursor adaptor.
 */
export declare interface CursorAdapter {
    /**
     * 创建光标的描述。该方法在用户光标首次出现之前会被调用。
     * Creates cursor description. This method is called before the user's cursor appears for the first time.
     *
     * @param memberId 用户 ID。
     *                 The ID of the user.
     * @returns 用户光标的描述。
     *          Description of the user's cursor.
     */
    createCursor(memberId: number): CursorDescription & {
        reactNode?: any;
    };

    /**
     * 光标出现在白板上的回调。
     * Occurs when the cursor appears on the whiteboard.
     *
     * @param cursor 光标对象。
     *               The cursor object.
     */
    readonly onAddedCursor: ((cursor: Cursor)=>void) | undefined;

    /**
     * 光标从白板上消失的回调。
     * Occurs when the cursor disappears from the whiteboard.
     *
     * @param cursor 光标对象。
     *               The cursor object.
     */
    readonly onRemovedCursor: ((cursor: Cursor)=>void) | undefined;

    /**
     * 光标在白板上移动的回调。
     * Occurs when the cursor moves on the whiteboard.
     *
     * @param cursor 光标对象。
     * @param positionX 光标指示区域中心的 X 轴坐标（以白板左上角为原点）。
     * @param positionY 光标指示区域中心的 Y 轴坐标（以白板左上角为原点）。
     *
     * @param cursor The cursor object.
     * @param positionX The X coordinate of the center of the area the cursor is pointing to (taking the left corner of the initial whiteboard as the origin).
     * @param positionY The Y coordinate of the center of the area the cursor is pointing to (taking the left corner of the initial whiteboard as the origin).
     */
    readonly onMovingCursor: ((cursor: Cursor, positionX: number, positionY: number)=>void) | undefined;

}

/**
 * 光标的描述。
 * Cursor description.
 */
export declare type CursorDescription = {
    /**
     * 光标指示区域中心的 X 轴坐标（以光标图形的左上角为原点）。
     * The X coordinate of the center of the area the cursor is pointing to (taking the left corner of the cursor icon as the origin).
     */
    x: number;
    /**
     * 光标指示区域中心的 Y 轴坐标（以光标图形的左上角为原点）。
     * The Y coordinate of the center of the area the cursor is pointing to (taking the left corner of the cursor icon as the origin).
     */
    y: number;
    /**
     * 光标图形的宽。
     * The width of the cursor icon.
     */
    width: number;
    /**
     * 光标图形的高。
     * The height of the cursor icon.
     */
    height: number;
};

/**
 * 悬浮条的配置参数。
 * Floating bar configuration.
 */
export declare type FloatBarOptions = {
    /**
     * 悬浮条调色盘的颜色列表。
     * The color palette of the floating bar.
     */
    colors: ReadonlyArray<Readonly<Color>>;
};

/**
 * 创建插件。
 * Creates a plugin.
 *
 * @param plugins 创建的插件。
 *                The plugin to be created.
 */
export declare function createPlugins<C_MAP extends Object>(plugins: Readonly<{
    [K: string]: Plugin<any>;
}>): Plugins<C_MAP>;

/**
 * 组件插件的描述。Agora 支持的自定义插件包括组件插件和不可见插件。
 * Description of component plugins. Agora supports two kinds of custom plugins: component plugins and invisible plugins.
 */
export declare type Plugin<C = {
}, T = any> = {
    /**
     * 组件插件的类型，为该组件插件的唯一识别符。
     * The type of the component plugin, which is the unique identifier of the plugin.
     */
    kind?: string;
    /**
     * 组件插件的外观。
     * The style of the component plugin.
     */
    render: ComponentType<PluginProps<C, T>>;
    /**
     * 属性的默认值。
     * Default attributes.
     */
    defaultAttributes?: T;

    hitTest?:
    /**
     * 碰撞检测。你可以通过碰撞检测定义组件插件可以被选择工具选中的区域。
     *
     * @param plugin 组件插件实例。
     * @param x 可选中区域的中心点的 X 轴坐标。
     * @param y 可选中区域的中心点的 Y 轴坐标。
     * @param selectorRadius 可选中区域的半径。
     *
     * @returns 碰撞检测的结果：
     * - `true`：检测成功。
     * - `false`：检测失败。
     *
     * Hit test, which you can use to define the selectable area of the component plugin for the selection tool.
     *
     * @param plugin The component plugin instance.
     * @param x The X coordinate of the center of the selectable area.
     * @param y The Y coordinate of the center of the selectable area.
     * @param selectorRadius The radius of the selectable area.
     *
     * @returns The result of the hit test:
     * - `true`：The hit test succeeds.
     * - `false`：The hit test fails.
     */
    (plugin: PluginInstance<C, T>, x: number, y: number, selectorRadius: number)=>boolean;

    willInterruptEvent?:
    /**
     * 是否拦截该组件插件的原生事件。
     *
     * @param plugin 组件插件实例。
     * @param event 原生事件。
     *
     * @returns
     * - `true`：拦截。
     * - `false`：不拦截。
     *
     * Whether to interrupt the native events of the component plugin:
     *
     * @param plugin The component plugin instance.
     * @param event The native events.
     *
     * @returns
     * - `true`：Interrupt the native events.
     * - `false`：Not interrupt the native events.
     */
     (plugin: PluginInstance<C, T>, event: NativeEvent)=>boolean;
};

/**
 * 组件插件。
 * Component plugin.
 */
export declare interface Plugins<C_MAP extends Object = {
    [kind: string]: any;
}> {
    /**
     * 组件插件的描述。
     * Description of the component plugin.
     */
    readonly plugins: Readonly<{
        [K: string]: RenderPlugin<any>;
    }>;
    /**
     * 设置组件插件的上下文。
     * Sets the context of the component plugin.
     *
     * @param kind 组件插件的类型。
     *             The type of the component plugin.
     * @param context 组件插件的上下文。
     *             The context of the component plugin.
     */
    setPluginContext<K extends string>(kind: K, context: any): void;
    /**
     * 获取组件插件的上下文。
     * Gets the context of the component plugin.
     *
     * @param kind 组件插件的类型。
     *             The type of the component plugin.
     */
    getPluginContext<K extends string>(kind: K): any | undefined;

}

/**
 * 不可见插件的描述。
 *
 * @since 2.10.0
 *
 * Agora 支持的自定义插件包括组件插件和不可见插件。
 * 不可见插件全局唯一，只能建立一个实例。不可见插件没有外观，只有一个会在全房间自动同步的 attributes 状态。
 *
 * Description of invisible plugins.
 * @since 2.10.0
 *
 * Agora supports two kinds of custom plugins: component plugins and invisible plugins. Note that:
 * - An invisible plugin is a global and unique variable in a room; it can have one instance only.
 * - An invisible plugin does not have appearance. Its attributes are automatically synchronized in the room.
 */
export declare abstract class InvisiblePlugin<A extends Object> {
    /**
     * 不可见插件的 `Displayer` 对象。
     * The `Displayer` object of the invisible plugin.
     */
    readonly displayer: Displayer;

    /**
     * 不可见插件的事件回调。
     * Event callbacks of the invisible plugin.
     */
    readonly callbacks: Callbacks<InvisibleCallbacks<A>>;

    /**
     * 不可见插件的属性。
     * Attributes of the invisible plugin.
     */
    readonly attributes: A;

    /**
     * 不可见插件的构造函数。
     * @param context 不可见插件的上下文。
     *
     * The constructor of the invisible plugin.
     * @param context The context of the invisible plugin.
     */
    constructor(context: InvisiblePluginContext);

    /**
     * 不可见插件属性更新的回调。
     * Occurs when the attributes of the invisible plugin are updated.
     *
     * @param attributes 更新的 attributes。
     *                   The updated attributes.
     */
    onAttributesUpdate(attributes: A): void;

    /**
     * 不可见插件删除的回调。
     * Occurs when the invisible plugin is deleted.
     */
    onDestroy(): void;

    /**
     * 设置不可见插件的属性。
     * Sets the attributes of the invisible plugin.
     *
     * @param modifyAttributes The attributes of the invisible plugin.
     */
    setAttributes(modifyAttributes: Partial<A>): void;

    /**
     * 删除不可见插件。
     * Deletes the invisible plugin.
     */
    destroy(): void;

    /**
     * @ignore
     */
    _dispose(shouldCallback: boolean): void;

    private kind: any;

    private _displayer: any;

    private _callbacks: any;

    private enableCallbackUpdate: any;

    private disposer: any;

    private autorunAttributesUpdate: any;

}

/**
 * 不可见插件的上下文。
 * The context of the invisible plugin.
 */
export declare type InvisiblePluginContext = {
    /**
     * 不可见插件的类型。
     * The type of the invisible plugin.
     */
    kind: string;
    /**
     * 不可见插件的 `displayer` 对象。
     * The `Displayer` object of the invisible plugin.
     */
    displayer: Displayer;
};


/**
 * 不可见插件。
 * Invisible plugin.
 */
export declare type InvisiblePluginClass<K extends string, A extends Object, P extends InvisiblePlugin<A> = InvisiblePlugin<A>> = {
    /**
     * 插件的类型。
     * The type of the invisible plugin.
     */
    kind: K;

    onCreate?:
    /**
     * 创建插件实例的回调。
     * Occurs when the invisible plugin is created.
     *
     * @param plugin 创建的插件实例。
     *               The created invisible plugin.
     */
    (plugin: P)=>void;

    onDestroy?:
    /**
     * 删除插件实例的回调。
     * Occurs when the invisible plugin is deleted.
     *
     * @param plugin 删除的插件实例。
     *               The deleted invisible plugin.
     */
    (plugin: P)=>void;
};

/**
 * 不可见插件的回调事件。
 * Callbacks for the invisible plugin.
 */
export declare type InvisibleCallbacks<A extends Object> = {

    onAttributesUpdate:
    /**
     * 插件实例属性更新的回调。
     * Occurs when the attributes of the invisible plugin are updated.
     *
     * @param attributes 更新的插件实例属性。
     *                   The updated attributes.
     */
    (attributes: A)=>void;

    onDestroy:
    /**
     * 删除插件实例的回调。
     * Occurs when the invisible plugin is deleted.
     */
     ()=>void;
};


/**
 * 设置异步模块的加载模式。
 * Sets the load mode of asynchronous modules.
 *
 * @param mode 异步模块的加载模式，详见 {@link AsyncModuleLoadMode}。
 *             The load mode of asynchronous modules. See {@link AsyncModuleLoadMode}.
 */
export declare function setAsyncModuleLoadMode(mode: AsyncModuleLoadMode): void;

/**
 * 异步模块的加载模式。
 * The load mode of an asynchronous module.
 */
export declare enum AsyncModuleLoadMode {
    /**
     * 禁止所有缓存操作，每次加载模块的时候都从网上下载。
     * Disables cache, which implies that the asynchronous module is downloaded from the Internet every time.
     */
    DisableCache = "disableCache",
    /**
     * 将模块以 blob 的形式缓存在 indexDB。
     * Caches the module in indexDB in blob form.
     */
    StoreAsBlob = "storeAsBlob",
    /**
     * 将模块以 base64 字符串的形式缓存在 indexDB。
     * Caches the module in indexDB in base64 string form.
     */
    StoreAsBase64 = "storeAsBase64",
}

/**
 * 判断 `displayer` 对象是否为 `Room` 实例。
 * Determines whether the `displayer` object is a `Room` instance.
 *
 * @param displayer `displayer` 对象。
 *                   The `displayer` object.
 *
 * @returns `displayer` 对象是否为 `Room` 实例：
 * - `true`：该对象是 `Room` 实例。
 * - `false`：该对象不是 `Room` 实例。
 *
 * @returns Whether the `displayer` object is a `Room` instance:
 * - `true`：A `Room` instance.
 * - `false`：Not a `Room` instance.
 */
export declare function isRoom(displayer: Displayer): boolean;

/**
 * 判断 `displayer` 对象是否为 `Player` 实例。
 * Determines whether the `displayer` object is a `Player` instance.
 *
 * @param displayer `displayer` 对象。
 *                   The `displayer` object.
 *
 * @returns `displayer` 对象是否为 `Player` 实例：
 * - `true`：该对象是 `Player` 实例。
 * - `false`：该对象不是 `Player` 实例。
 *
 * @returns Whether the `displayer` object is a `Player` instance：
 * - `true`：A `Player` instance.
 * - `false`：Not a `Player` instance.
 */
export declare function isPlayer(displayer: Displayer): boolean;

/**
 * 回调函数。
 * Callbacks.
 *
 */
export declare interface Callbacks<CALLBACKS> {
    /**
     * 注册回调函数。
     * @param name 注册函数名。
     * @param listener 回调函数。
     *
     * Registers a callback.
     * @param name The callback name.
     * @param listener The callback function.
     */
    on<NAME extends string>(name: NAME, listener: any): void;

    /**
     * 注册只调用一次的回调函数。
     * @param name 注册函数名。
     * @param listener 回调函数。
     *
     * Registers a callback that is used once only.
     * @param name The callback name.
     * @param listener The callback function.
     */
    once<NAME extends string>(name: NAME, listener: any): void;

    /**
     * 注销回调函数。
     * @param name 注册函数名。
     * @param listener 回调函数。
     *
     * Unregisters a callback.
     * @param name The callback name.
     * @param listener The callback function.
     */
    off<NAME extends string>(name?: NAME, listener?: any): void;

}

/**
 * 默认的快捷键。
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
/**
 * The default hotkeys.
 *
 * | Keys              | Action                     |
 * | :--------------------- | :---------------------- |
 * | Backspace / Delete     | Delete the selected object            |
 * | Shift                  | Resize proportionately |
 * | ctrl + z / command + z | Undo                   |
 * | ctrl + y / command + y | Redo                   |
 * | ctrl + c / command + c | Copy                     |
 * | ctrl + v / command + v | Paste                    |
 */
export declare const DefaultHotKeys: Partial<HotKeys>;

/**
 * 快捷键对应的效果。
 */
/**
 * The action triggered by the hotkey.
 */
export declare type HotKeys = {
    /**
     * 复制并粘贴。
     * Copy and paste.
     */
    duplicate: HotKey;
    /**
     * 复制。
     * Copy.
     */
    copy: HotKey;
    /**
     * 粘贴。
     * Paste.
     */
    paste: HotKey;
    /**
     * 撤回。
     * Undo.
     */
    undo: HotKey;
    /**
     * 重做。
     * Redo.
     */
    redo: HotKey;
    /**
     * 删除。
     * Delete.
     */
    delete: HotKey;
    /**
     * 锁定缩放比例。
     * Resize proportionately.
     */
    lock: HotKey;
    /**
     * 切换到选择工具（`selector`）。
     * Switch to selection tool (`selector`).
     */
    changeToSelector: HotKey;
    /**
     * 切换到激光笔（`laserPointer`）。
     * Switch to laser point (`laserPointer`).
     */
    changeToLaserPointer: HotKey;
    /**
     * 切换到铅笔工具（`pencil`）。
     * Switch to pen tool (`pencil`).
     */
    changeToPencil: HotKey;
    /**
     * 切换到矩形工具（`rectangle`）。
     * Switch to rectangle tool (`rectangle`).
     */
    changeToRectangle: HotKey;
    /**
     * 切换到椭圆工具（`ellipse`）。
     * Switch to ellipse tool (`ellipse`).
     */
    changeToEllipse: HotKey;
    /**
     * 切换到橡皮工具（`eraser`）。
     * Switch to eraser (`eraser`).
     */
    changeToEraser: HotKey;
    /**
     * 切换到直线工具（`straight`）。
     * Switch to line tool (`straight`).
     */
    changeToStraight: HotKey;
    /**
     * 切换到箭头工具（`arrow`）。
     * Switch to arrow tool (`arrow`).
     */
    changeToArrow: HotKey;
    /**
     * 切换到抓手工具（`hand`）。
     * Switch to hand tool (`hand`).
     */
    changeToHand: HotKey;
};

/**
 * 自定义快捷键。
 */
/**
 * Customized hotkey.
 */
export declare type HotKey = string | HotKeyDescription | ReadonlyArray<string | HotKeyDescription> | HotKeyChecker;

/**
 * 自定义快捷键的描述。
 */
/**
 * Description of the customized hotkeys.
 */
export declare type HotKeyDescription = {
    /**
     * 指定的键。
     */
    /**
     * A specified key.
     */
    key: string;
    /**
     * 是否使用 alt 键。
     */
    /**
     * Whether the alt key is used.
     */
    altKey: boolean | null;
    /**
     * 是否使用 ctrl 键。
     */
    /**
     * Whether the ctrl key is used.
     */
    ctrlKey: boolean | null;
    /**
     * 是否使用 shift 键。
     */
    /**
     * Whether the shift key is used.
     */
    shiftKey: boolean | null;
};

/**
 * 自定义快捷键的事件。
 */
/**
 * The event of the customized hotkeys.
 */
export declare type HotKeyEvent = {
    /**
     * 浏览器的原生事件。
     * Thr browser event.
     */
    nativeEvent?: KeyboardEvent;
    /**
     * 快捷键的事件类型。
     * - `KeyDown`：键被按下。
     * - `KeyUp`：键被松开。
     */
    /**
     * The event of the hotkeys:
     * - `KeyDown`：The hotkeys are pressed.
     * - `KeyUp`：The hotkeys are released.
     */
    kind: "KeyDown" | "KeyUp";
    /**
     * 指定的键。
     * The specified key.
     */
    key: string;
    /**
     * 是否使用 alt 键。
     * Whether the alt key is used.
     */
    altKey: boolean;
    /**
     * 是否使用 ctrl 键。
     * Whether the ctrl key is used.
     */
    ctrlKey: boolean;
    /**
     * 是否使用 shift 键。
     * Whether the shift key is used.
     */
    shiftKey: boolean;
};

/**
 * 快捷键检查器。
 *
 * @param event 自定义快捷键的事件。
 * @param kind 键盘的类型。
 *
 * @returns 是否为指定的快捷键：
 * - `true`：是指定的快捷键。
 * - `false`：不是指定的快捷键。
 */
/**
 * Checks the hotkeys.
 *
 * @param event The event of the customized hotkeys.
 * @param kind The type of the keyboard.
 *
 * @returns Whether the hotkeys are the customized hotkeys:
 * - `true`：The hotkeys are the customized hotkeys.
 * - `false`：The hotkeys are not the customized hotkeys.
 */
export declare type HotKeyChecker = (event: HotKeyEvent, kind: KeyboardKind)=>boolean;

/**
 * 创建 PPT 文件转换任务。
 *
 * @param params 转换任务的参数。
 * @returns 转换任务。
 */
/**
 * Creates a PPT file-conversion task.
 *
 * @param params The parameters of the file-conversion task.
 * @returns The created task.
 */
export declare function createPPTTask(params: PPTTaskParams): PPTTask;

/**
 * PPT 文件转换任务。
 * PPT file-conversion task.
 */
export declare interface PPTTask {
    /**
     * 在转换过程中进行自动轮询，获取转换任务的实时状态。当转换任务状态为成功或失败时，自动轮询停止。
     * @returns 如果方法调用成功，会返回 PPT 文件的描述。
     */
    /**
     * Sets to regularly query the real-time state of the file-conversion task. The query stops once the task succeeds or fails.
     */
    checkUtilGet(): Promise<PPT>;

}

/**
 * PPT 文件转换任务的进度详情。
 * The progress details of the PPT file-conversion task.
 */
export declare type PPTTaskProgress = {
    /**
     * 转换任务的状态。
     * The state of the PPT file-conversion task.
     */
    status: PPTTaskStatus;
    /**
     * 转换任务当前的步骤。
     * The current step of the PPT file-conversion task.
     *
     * @since 2.10.3
     */
    currentStep?: PPTTaskStep;
    /**
     * PPT 文件的总页数。
     * The number of the total pages of the PPT file.
     */
    totalPageSize: number;
    /**
     * 已转换的页数。
     * The number of converted pages.
     */
    convertedPageSize: number;
    /**
     * 转换进度（百分比）。
     * The conversion progress (in percentage).
     */
    convertedPercentage: number;
};

/**
 * PPT 文件转换任务的参数。
 * The parameters of the file-conversion task.
 */
export declare type PPTTaskParams = {
    /**
     * 转换任务的 UUID，即转换任务的唯一识别符。
     * The unique identifier (UUID) of the file-conversion task.
     */
    uuid: string;
    /**
     * 处理该转换任务的数据中心。
     * The data center that processes the file-conversion task.
     */
    region?: string;
    /**
     * 转换任务的类型。
     * The type of the PPT file-conversion task.
     */
    kind: PPTKind;
    /**
     * 转换任务的 Task Token。
     * The Task Token for authentication.
     */
    taskToken: string;
    /**
     * 轮询转换任务状态的时间间隔（毫秒）。
     * The interval (ms) to regularly query the state of the file-conversion task.
     */
    checkProgressInterval?: number;
    /**
     * 轮询的超时时间（毫秒）。
     * The timeout (ms) of the regular query.
     */
    checkProgressTimeout?: number;
    /**
     * 转换任务的回调函数。
     * The callbacks for the file-conversion task.
     */
    callbacks?: PPTTaskCallbacks;
};

/**
 * PPT 文件转换任务的回调函数。
 * The callbacks for the PPT file-conversion task.
 */
export declare type PPTTaskCallbacks = {
    /**
     * 转换任务的进度更新回调。
     * @param progress 转换任务的进度详情。
     */
    /**
     * Occurs when the task progress is updated.
     * @param progress The task progress details.
     */
    onProgressUpdated: (progress: PPTTaskProgress)=>void;
    /**
     * 转换任务成功回调。
     * @param result PPT 文件的描述。
     */
    /**
     * Occurs when the task succeeds.
     * @param result Description of the PPT file.
     */
    onTaskSuccess: (result: PPT)=>void;
    /**
     * 转换任务失败回调。
     * @param error 发生的错误。
     */
    /**
     * Occurs when the task fails.
     * @param error The error message.
     */
    onTaskFail: (error: Error)=>void;
};

/**
 * 场景列表。
 * List of scenes.
 */
export declare type SceneMap = {
    /**
     * 某一场景目录下的所有场景。
     * The list of scenes under a scene directory.
     */
    [dirPath: string]: WhiteScene[];
};

/**
 * The `Displayer` interface is the basic interface for interactive whiteboard rooms. It is extended by {@link Room} and {@link Player}.
 */
export declare interface Displayer<CALLBACKS extends DisplayerCallbacks = DisplayerCallbacks> {

    /**
     * 用户 ID。
     * - 在实时房间中，为本地用户的用户 ID。
     * - 在回放房间中，当用户的 `ObserverMode` 为 `Directory` 时，`observerId` 为被跟随的用户的用户 ID; 当用户的 `ObserverMode` 为 `Freedom` 时，为 `AdminObserverId`。
     */
    /**
     * The ID of the user.
     * - In a live Interactive Whiteboard room, `observerId` is the ID of the local user.
     * - During a whiteboard playback:
     *   - If the user's `ObserverMode` is `Directory`, `observerId` is the ID of the user whose view is displayed in the playback.
     *   - If the user's `ObserverMode` is `Freedom`, `observerId` is `AdminObserverId`.
     */
    readonly observerId: number;

    /**
     * 该客户端的设备类型，决定 SDK 如何处理鼠标事件和触碰事件。
     */
    /**
     * The type of the user's device, which determines how the SDK handles mouse and touch events.
     */
    readonly deviceType: DeviceType;

    /**
     * 用户的屏幕类型，用于调整手势识别参数。
     */
    /**
     * The type of the user's screen, which is used to adjust parameters for gesture recognition.
     */
    readonly screenType: ScreenType;

    /**
     * 用户当前是否可写:
     *
     * @since 2.10.0
     *
     * - `true`：可写。
     * - `false`：不可写。
     */
    /**
     * Currently whether the user has write permission:
     *
     * @since 2.10.0
     *
     * - `true`：The user has write permission.
     * - `false`：The user does not have write permission.
     */
    readonly enableWriteNow: boolean;

    /**
     * 设置抓手工具的快捷键。用户可以使用抓手工具拖动页面。如果设为 `undefined`，表示不启用快捷键功能。
     */
    /**
     * Sets hotkeys for the hand tool. The user can use the hand tool to move the page. If you set this parameter to `undefined`, the hand tool has no hotkeys.
     */
    readonly handToolKey: string | undefined;

    /**
     * 是否隐藏其他人的鼠标移动到白板组件上时显示的高亮框。
     *
     * @since 2.12.0
     *
     * - `true`：隐藏。
     * - `false`：不隐藏。
     *
     * 该属性不影响自己的高亮框显示。
     */
    /**
     * Whether to disable the highlighted box when another user puts their cursors on an object on the whiteboard:
     *
     * @since 2.12.0
     *
     * - `true`：Disable the highlighted box.
     * - `false`：Enable the highlighted box.
     *
     * This parameter does not affect the highlighted box of the local user.
     */
    disableOthersSelectingBox: boolean;

    /**
     * 是否禁止用户通过触屏手势或鼠标滚轮移动或缩放调整视角。
     * Whether to disable the user from adjusting (moving or zooming) the view through touch screen gestures or mouses.
     *
     * @since 2.11.0
     *
     * - `true`：Disable the user from adjusting the view.
     * - `false`：(Default) Enable the user to adjust the view.
     *
     * 该属性不影响 `setCameraBound`、`moveCamera`、`moveCameraToContain` 方法的使用。
     * This method does not affect the `setCameraBound`, `moveCamera`, or `moveCameraToContain` methods.
     */
    disableCameraTransform: boolean;

    /**
     * 将白板绑定到 HTML 元素上。
     * Binds the whiteboard to an HTML container.
     *
     * @param element 用于容纳白板的 HTML 元素容器。若为 `null`，表示解除对白板的绑定。
     *                The HTML container for the whiteboard. You can unbind the whiteboard by setting this parameter to `null`.
     */
    bindHtmlElement(element: HTMLDivElement | null): void;

    /**
     * 获取指定的不可见插件。
     * Gets the specified invisible plugin.
     *
     * @param kind 不可见插件的类型。
     *             The type of the invisible plugin.
     * @returns 指定的不可见插件。
     *          The specified invisible plugin.
     */
    getInvisiblePlugin<A extends Object>(kind: string): InvisiblePlugin<A> | null;

    /**
     * 生成特定场景的预览图。
     * Generates the preview of the specified scene.
     *
     * @param  scenePath 特定场景的路径。
     *                   The path of the specified scene.
     * @param  div 用于显示预览内容的 div。
     *              The div for the preview.
     * @param  width 预览图的宽度。自 2.3.8 起，该参数为可选参数，如果不填，则默认为展示预览内容的 div 的宽度。
     *               The width of the preview. This paramter is optional since v2.3.8. If you do not set this parameter, it is set to the width of the div for the preview.
     * @param  height 预览图的高度。自 2.3.8 起，该参数为可选参数，如果不填，则默认为展示预览内容的 div 的高度。
     *               The height of the preview. This paramter is optional since v2.3.8. If you do not set this parameter, it is set to the height of the div for the preview.
     */
    scenePreview(scenePath: string, div: HTMLElement, width: number | undefined, height: number | undefined): void;

    /**
     * 生成特定场景的屏幕快照。
     * Generates the snapshot of the specified scene.
     *
     * @param scenePath 特定场景的路径。
     *                  The path of the specified scene.
     * @param div 用于显示屏幕快照的 div。
     *            The div for the snapshot.
     * @param width 屏幕快照的宽度。自 2.3.8 起，该参数为可选参数，如果不填，则默认为展示屏幕快照的 div 的宽度。
     *              The width of the snapshot. This paramter is optional since v2.3.8. If you do not set this parameter, it is set to the width of the div for the snapshot.
     * @param height 屏幕快照的高度。自 2.3.8 起，该参数为可选参数，如果不填，则默认为展示屏幕快照的 div 的高度。
     *               The height of the snapshot. This paramter is optional since v2.3.8. If you do not set this parameter, it is set to the height of the div for the snapshot.
     */
    fillSceneSnapshot(scenePath: string, div: HTMLElement, width: number, height: number): void;

    /**
     * 等待特定的自定义事件发生。
     * Waits for a customized event.
     *
     * @param filter 事件过滤器。
     *               The event filter.
     * @returns 当特定自定义事件发生时，返回该事件。
     *          The specified customized event when it occurs.
     */
    waitMagixEvent(filter: EventFilter): Promise<Event>;
}

/**
 * `Displayer` 对象的事件回调。
 * Callbacks for `Displayer` objects.
 */
export declare type DisplayerCallbacks = {

    onEnableWriteNowChanged:
    /**
     * 用户当前可写状态发生改变的回调。
     * Occurs when the user's write permission changes:
     *
     * @since 2.10.0
     * @param enableWriteNow 用户当前是否可写：
     * - `true`：可写。
     * - `false`：不可写。
     *
     * @param enableWriteNow Whether the user has write permission at the moment:
     * - `true`：The user has write permission.
     * - `false`：The user does not have write permission.
     */
    (enableWriteNow: boolean)=>void;

    onHandToolActive:
    /**
     * 抓手工具的启用状态发生改变的回调。
     * @param active 抓手工具是否启用:
     * - `true`：启用。
     * - `false`：不启用。
     */
    /**
     * Occurs when the hand tool is enabled or disabled.
     * @param active Whether the hand tool is enabled:
     * - `true`：Enabled.
     * - `false`：Disabled.
     */
     (active: boolean)=>void;

    onSliceChanged:
    /**
     * 所处分片发生改变的回调。
     * @param slice 所处分片的 UUID。
     */
    /**
     * Occurs when the current slice changes.
     * @param slice The unique identifier (UUID) of the slice.
     */
    (slice: string)=>void;

    onRenderDuration:
    /**
     * 渲染时长回调。
     * @param renderDuration 渲染时长。
     */
    /**
     * Reports the render duration.
     * @param renderDuration The render duration.
     */
    (renderDuration: number)=>void;

    onPPTLoadProgress:
    /**
     * PPT 文件转网页时，加载 PPT 文件的进度回调。
     *
     * @param uuid 转换任务的 UUID。
     * @param progress 加载进度，取值范围为 [0.0, 1.0]。
     */
    /**
     * Reports the progress of loading the PPT file when converting it to web pages.
     *
     * @param uuid The unique identifier of the conversion task (Task UUID).
     * @param progress The loading progress. The value ranges is [0.0, 1.0].
     */
    (uuid: string, progress: number)=>void;

    onPPTMediaPlay:
    /**
     * 播放动态 PPT 中媒体文件的回调。
     * @param shapeId 插入媒体文件的 shape 对象的 ID。
     * @param type 媒体文件的类型。
     */
    /**
     * Occurs when a media file in the dynamic PPT starts playing.
     * @param shapeId The ID of the shape where the media file is inserted.
     * @param type The type of the media file.
     */
    (shapeId: string, type: MediaType)=>void;

    onPPTMediaPause:
    /**
     * 暂停播放动态 PPT 中媒体文件的回调。
     * @param shapeId 插入媒体文件的 shape 对象的 ID。
     * @param type 媒体文件的类型。
     */
    /**
     * Occurs when a media file in the dynamic PPT stops playing.
     * @param shapeId The ID of the shape where the media file is inserted.
     * @param type The type of the media file.
     */
    (shapeId: string, type: MediaType)=>void;
};

/**
 * The `Room` interface contains methods to operate a live Interactive Whiteboard room.
 */
export declare interface Room extends Displayer {

    /**
     * 房间当前 session 的 UUID。如果开启了自动日志上报功能，日志中会上报该参数。如果上报时尚未与服务器建立连接，则为 `undefined`。
     */
    /**
     * The unique identifier (UUID) of the current session in the room. If you enable the SDK to report logs all the time, this parameter is reported in the logs.
     * If the SDK has not connected to the server when reporting logs, this parameter is shown as `undefined`.
     */
    readonly session: string;

    /**
     * 是否禁止用户通过鼠标、键盘、触摸屏幕操作白板工具。
     * - `true`： 禁止。
     * - `false`：不禁止。
     *
     * 该方法与 `isWritable` 的区别详见[《禁止设备操作｜禁止操作》](/javascript-zh/home/disable#禁止设备操作)。
     */
    /**
     * Whether to disable the user from operating the whiteboard tools through the mouse, keyboard, or touchscreen:
     * - `true`：Disable the user from operating the whiteboard tools.
     * - `false`：Enable the user to operate the whiteboard tools.
     *
     * This method is different from `isWritable`. See [Prohibited operation](https://developer.netless.link/javascript-en/home/disable#Prohibit-device-operation).
     */
    disableDeviceInputs: boolean;

    /**
     * 远端白板画面的同步延时，单位为毫秒，默认值为 `0`。如果将该值设为大于 `0`，可以模拟网络延时的效果，防止用户感知错位。
     * The delay time (ms) for synchronizing the whiteboard contents of the local user to the remote users. The default value is `0`.
     * You can set it to a value greater than `0` so that the remote user has smooth watching experience when the network is poor.
     */
    timeDelay: number;

    /**
     * 设置音视频和白板的同步延时。该方法会修改 {@link timeDelay} 的值。
     *
     * @since 2.12.11
     * @param timestamp 同步延时，为 unix 时间戳，单位为毫秒。
     */
    /**
     * The delay time for synchronizing audios and videos to the whiteboard. This parameter overrides {@link timeDelay}.
     *
     * @since 2.12.11
     * @param timestamp The Unix timestamp (ms) representing the delay time.
     */
    syncBlockTimstamp(timestamp: number): void;

    /**
     * 在白板中插入组件插件实例。
     *
     * @param kind 组件插件的类型，是组件的唯一标识符。
     * @param description 组件插件的描述参数。
     *
     * @returns 组件插件实例在房间内的唯一标识符。
     */
    /**
     * Inserts a component plugin instance to the whiteboard.
     *
     * @param kind The type of the component plugin, which is the unique identifier of the plugin.
     * @param description Description of the component plugin.
     *
     * @returns The unique identifier of the plugin in the room.
     */
    insertPlugin(kind: string, description?: PluginDescription): Identifier;

    /**
     * 删除组件插件实例。
     *
     * @param identifier 组件插件实例在房间内的唯一标识符。
     */
    /**
     * Deletes a component plugin instance
     *
     * @param identifier The unique identifier of the plugin in the room.
     */
    removePlugin(identifier: Identifier): boolean;

    /**
     * 修改组件插件实例的描述。
     *
     * @param identifier 组件插件实例在房间内的唯一标识符。
     * @param description 组件插件实例的新描述。
     */
    /**
     * Updates the description of the component plugin instance.
     *
     * @param identifier The unique identifier of the plugin in the room.
     * @param description The updated description.
     */
    updatePlugin(identifier: Identifier, description: PluginDescription): boolean;

    /**
     * 获取组件插件实例的属性。
     *
     * @param identifier 组件插件实例在房间内的唯一标识符。
     */
    /**
     * Gets the attributes of the component plugin instance.
     *
     * @param identifier The unique identifier of the plugin in the room.
     */
    getPluginAttributes(identifier: Identifier): any | undefined;

    /**
     * 获取组件插件实例在白板上的位置信息。
     *
     * @param identifier 组件插件实例在房间内的唯一标识符。
     * @returns 组件插件实例的矩形。
     */
    /**
     * Gets the position of the component plugin instance on the whiteboard.
     *
     * @param identifier The unique identifier of the plugin in the room.
     * @returns The rectangle for the component plugin instance.
     */
    getPluginRectangle(identifier: string): Rectangle | undefined;

}

/**
 * 没有可写权限的用户进行写操作时，SDK 的应对方式。
 * The response of the SDK when a user without write permission attempts to write.
 */
export declare enum RoomErrorLevel {
    /**
     * 直接报错。
     * The SDK reports an error.
     */
    ThrowError = "throwError",
    /**
     * 拦截该操作，并在控制台打印警告。
     * The SDK interrupts the user's attempt and prints a warning in Console.
     */
    Warn = "warn",
    /**
     * 拦截该操作，但不报错也不打印警告。
     * The SDK interrupts the user's attempt but does not report an error or print a warning.
     */
    Ignore = "ignore",
}

export declare type RoomCallbacks = DisplayerCallbacks & {

    onKickedWithReason:
    /**
     * 用户被服务器移出房间回调。
     *
     * @param reason 用户被移出房间的原因。
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
    /**
     * Occurs when the local user is removed from the live Interactive Whiteboard room.
     *
     * @param reason The reason why the user is removed from the room.
     *
     * | `reason`  | Description                         |
     * | :--------------| :--------------------------- |
     * | kickByAdmin    | The user is removed by the admin.                   |
     * | roomDelete     | The room is deleted.             |
     * | roomZombie     | The room is not active.                    |
     * | roomBan        | The room is disabled.                   |
     * | GatewayAdjust  | The gateway is adjusted.                     |
     * | replaceByOther | The user is replaced by another user.|
     * | crash          | The server crashes.                  |
     */
    (reason: string)=>void;

    willInterceptKeyboardEvent:
    /**
     * 是否拦截监听的键盘事件：
     * - `true`: 拦截。拦截后，该键盘事件不会触发回调。
     * - `false`: 不拦截。
     */
    /**
     * Whether to intercept the keyboard event for which you have set a listener:
     * - `true`: Intercept the keyboard event, which implies that the event does not trigger the callback.
     * - `false`: Not intercept the keyboard event.
     */
    (event: KeyboardEvent)=>boolean;

    onKeyDown:
    /**
     * 键盘按下事件回调。
     * Occurs when a key is pressed.
     */
    (event: KeyboardEvent)=>void;

    onKeyUp:
    /**
     * 键盘松开事件回调。
     * Occurs when a key is released.
     */
    (event: KeyboardEvent)=>void;
};

export declare type PlayerCallbacks = DisplayerCallbacks & {

    onIsPlayableChanged:
    /**
     * 能否立即播放的状态发生改变的回调。
     * @param isPlayable 是否能立即播放：
     * - `true`：可以立即播放。
     * - `false`：不能立即播放。
     */
    /**
     * Occurs when whether the playback can start immediately changes.
     *
     * @param isPlayable Whether the playback can start immediately:
     * - `true`：The playback can start immediately.
     * - `false`：The playback cannot start immediately.
     */
    (isPlayable: boolean)=>void;

/**
 * 自定义输入源（例如点阵笔）的接口。
 * The interface for custom inputs (such as digital pens).
 *
 * @since 2.12.11
 */
export declare interface CustomInput {
    /**
     * 输入按下操作。
     *
     * @param x 按下的点在屏幕坐标系（以屏幕左上角为原点）的 x 轴坐标。
     * @param y 按下的点在屏幕坐标系（以屏幕左上角为原点）的 y 轴坐标。
     */
    /**
     * Inputs a press.
     *
     * @param x The X coordinate of the point pressed in the screen coordinate system (taking the upper left corner as the origin).
     * @param y The Y coordinate of the point pressed in the screen coordinate system (taking the upper left corner as the origin).
     */
    inputDown(x: number, y: number): void;

    /**
     * 输入移动操作。
     *
     * @param x 移动的点在屏幕坐标系（以屏幕左上角为原点）的 x 轴坐标。
     * @param y 移动的点在屏幕坐标系（以屏幕左上角为原点）的 y 轴坐标。
     */
    /**
     * Inputs a movement.
     *
     * @param x The X coordinate of the point moved in the screen coordinate system (taking the upper left corner as the origin).
     * @param y The Y coordinate of the point moved in the screen coordinate system (taking the upper left corner as the origin).
     */
    inputMove(x: number, y: number): void;

    /**
     * 输入松开操作。
     *
     * @param x 抬起的点在屏幕坐标系（以屏幕左上角为原点）的 x 轴坐标。
     * @param y 抬起的点在屏幕坐标系（以屏幕左上角为原点）的 y 轴坐标。
     */
    /**
     * Inputs a release.
     *
     * @param x The X coordinate of the point released in the screen coordinate system (taking the upper left corner as the origin).
     * @param y The Y coordinate of the point released in the screen coordinate system (taking the upper left corner as the origin).
     */
    inputUp(x: number, y: number): void;

}

export declare enum ApplianceNames {
    /**
     * 互动工具。该工具没有默认行为，可供所有插件使用。目前主要用于 H5 课件。
     * Clicker. This tool has no default behavior and can be used by any plugin. Currently is is mainly used for H5 files.
     */
    clicker = "clicker",
}

/**
 * 组件插件的外观设置。
 * Style configuration of the component plugin.
 */
export declare type PluginProps<C, T> = {
    /**
     * 组件插件实例。
     * The component plugin instance.
     */
    plugin: PluginInstance<C, T>;
    /**
     * 组件插件图标周围的空白。
     * The spaces around the component plugin icon.
     */
    margin: Margin;
    /**
     * 组件插件图标的中心点在世界坐标系中（以白板初始化时的中心点为原点的坐标系）的坐标。
     * The coordinates of the center of the component plugin icon in the world coordinate system (taking the center of the initial whiteboard as the origin).
     */
    origin: Point;
    /**
     * 组件插件图标的大小。
     * The size of the component plugin icon.
     */
    size: Size;
    /**
     * 组件插件图标的缩放比例。
     * The scale factor of the component plugin icon.
     */
    scale: number;
    /**
     * @ignore
     */
    cnode?: any;
};

/**
 * 组件插件实例的接口。
 * The interface for component plugin instances.
 */
export declare interface PluginInstance<C, A> {
    /**
     * 组件插件的类型，是组件插件的唯一标识符。
     * The type of the component plugin, which is the unique identifier of the component plugin.
     */
    readonly kind: string;

    /**
     * 组件插件实例在房间内的唯一标识符。
     * The unique identifier of the component plugin in the room.
     */
    readonly identifier: Identifier;

    /**
     * 自定义的组件插件上下文。
     * The customized context of the component plugin.
     */
    readonly context: C;

    /**
     * 组件插件实例的左上角在世界坐标系（以白板初始化时的中心点为原点的坐标系）中的 X 轴坐标。
     * The X coordinate of the left corner of the component plugin instance in the world coordinate system (taking the center of the initial whiteboard as the origin).
     */
    readonly originX: number;

    /**
     * 组件插件实例的左上角在世界坐标系（以白板初始化时的中心点为原点的坐标系）中的 Y 轴坐标。
     * The Y coordinate of the left corner of the component plugin instance in the world coordinate system (taking the center of the initial whiteboard as the origin).
     */
    readonly originY: number;

    /**
     * 组件插件实例的宽。
     * The width of the component plugin instance.
     */
    readonly width: number;

    /**
     * 组件插件实例的高。
     * The height of the component plugin instance.
     */
    readonly height: number;

    /**
     * 能否选中组件插件实例：
     * - `true`：能选中。
     * - `false`：不能选中。
     *
     * Whether the component plugin instance can be selected:
     * - `true`：The component plugin instance can be selected.
     * - `false`：The component plugin instance cannot be selected.
     */
    readonly selectable: boolean;

    /**
     * 是否正在拖拽组件插件实例。
     * - `true`：在拖拽。
     * - `false`：不在拖拽。
     *
     * Whether the component plugin instance is being dragged:
     * - `true`：The component plugin instance is being dragged.
     * - `false`：The component plugin instance is not being dragged.
     */
    readonly isDraging: boolean;

    /**
     * 是否正在改变组件插件实例的大小。
     * - `true`：在改变。
     * - `false`：不在改变。
     *
     * Whether the component plugin instance is being resized:
     * - `true`：The component plugin instance is being resized.
     * - `false`：The component plugin instance is not being resized.
     */
    readonly isResizing: boolean;

    /**
     * 是否正在播放组件插件实例。
     * - `true`：在播放。在白板实时房间内，取值一直为 `true`。
     * - `false`：不在播放。
     *
     * * Whether the component plugin instance is being played:
     * - `true`：The component plugin instance is being played. In a live Interactive Whiteboard room, this parameter stays as `true`.
     * - `false`：The component plugin instance is not being played.
     */
    readonly isPlaying: boolean;

    /**
     * 组件插件实例当前播放位置的时间戳（若为实时房间，则恒为 0）。
     * The Unix timestamp of the current playback position of the component plugin instance (this parameter stays as `0` in a live Interactive Whiteboard room).
     */
    readonly playerTimestamp: number;

    /**
     * 组件插件实例当前的播放速率倍率（若为实时房间，则恒为 `1.0`）。
     * The current playback speed of the component plugin instance (this parameter stays as `1.0` in a live Interactive Whiteboard room).
     */
    readonly playbackSpeed: number;

    /**
     * 组件插件实例的属性。
     * The attributes of the component plugin instance.
     */
    attributes: A;

    /**
     * 修改组件插件属性中的部分字段。
     * @param attributes 要修改的字段。
     *
     * Modifies certain attributes of the component plugin instance.
     * @param attributes The attributes to be modified.
     */
    putAttributes(attributes: Partial<A>): void;

    /**
     * 删除组件插件属性中的部分字段。
     * @param keys 要删除的字段。
     *
     * Deletes certain attributes of the component plugin instance.
     * @param keys The attributes to be deleted.
     */
    removeAttributeKeys<K extends string>(...keys: K[]): void;

    /**
     * 修改组件插件实例的描述。
     * @param description 要修改的描述。
     *
     * Modifies the description of the component plugin instance.
     * @param keys The description of the component plugin instance.
     */
    update(description: PluginDescription<A>): void;

    /**
     * 删除组件插件实例。
     * Deletes a component plugin instance.
     */
    remove(): void;

}
/**
 * 组件插件的描述参数。
 * Description of component plugin instances.
 */
export declare type PluginDescription<A = any> = {
    /**
     * 组件插件实例的左上角在世界坐标系（以白板初始化时的中心点为原点的坐标系）中的 X 轴坐标。
     * The X coordinate of the left corner of the component plugin instance in the world coordinate system (taking the center of the initial whiteboard as the origin).
     */
    originX?: number;
    /**
     * 组件插件实例的左上角在世界坐标系（以白板初始化时的中心点为原点的坐标系）中的 Y 轴坐标。
     * The Y coordinate of the left corner of the component plugin instance in the world coordinate system (taking the center of the initial whiteboard as the origin).
     */
    originY?: number;
    /**
     * 组件插件实例的宽。
     * The width of the component plugin instance.
     */
    width?: number;
    /**
     * 组件插件实例的高。
     * The width of the component plugin instance.
     */
    height?: number;
    /**
     * 能否选中实例。
     * - `true`：能选中。
     * - `false`：不能选中
     *
     * Whether the component plugin instance can be selected:
     * - `true`：The component plugin instance can be selected.
     * - `false`：The component plugin instance cannot be selected.
     */
    selectable?: boolean;
    /**
     * 实例的属性。
     * The attributes of the component plugin instance.
     */
    attributes?: A;
};

/**
 * 颜色，格式为 RGB。例如 `[0, 0, 255]` 代表蓝色。
 * Color in RGB format. For example, `[0, 0, 255]` stands for blue.
 */
export declare type Color = number[];


/**
 * Image information.
 */
export declare type ImageInformation = {
    /**
     * 图片是否只能等比放缩。
     * Whether the image can only be resized proportionately.
     *
     * @since  2.12.0
     *
     * - `true`: The image can only be resized proportionately.
     * - `false`: The image can be resized disproportionately.
     */
    uniformScale?: boolean;
};

/**
 * 光标名称。
 * The cursor name.
 */
export declare enum CursorNames {
    /**
     * 张开的手。
     * Hand.
     */
    Hand = "cursor-hand",
    /**
     * 握紧的手。
     * Grasp.
     */
    HandGrasp = "cursor-hand-grasp",
    /**
     * 指向西北和东南的双向箭头。
     * A double-headed arrow pointing to northwest and southeast.
     */
    Nwse = "cursor-nwse",
    /**
     * 指向东北和西南的双向箭头。
     * A double-headed arrow pointing to northeast and southwest.
     */
    Nesw = "cursor-nesw",
    /**
     * 指向南北的双向箭头。
     * A double-headed arrow pointing to north and south.
     */
    Ns = "cursor-ns",
    /**
     * 指向东西的双向箭头。
     * A double-headed arrow pointing to east and west.
     */
    Ew = "cursor-ew",
}

/**
 * 屏幕类型。
 * The type of the user's screen.
 */
export declare enum ScreenType {
    /**
     * 桌面设备的屏幕。
     * The screen of a desktop device.
     */
    Desktop = "desktop",
    /**
     * 智能手机的屏幕。
     * The screen of a smartphone.
     */
    Phone = "phone",
    /**
     * 平板电脑的屏幕。
     * The screen of a tablet.
     */
    Pad = "pad",
    /**
     * 电视的屏幕。
     * The screen of a TV.
     */
    TV = "tv",
}

/**
 * Configuration for the `WhiteWebSdk` instance.
 *
 * **Note**
 *
 * After the `WhiteWebSdk` instance is initialized, you cannot call any method in the `WhiteWebSdkConfiguration` class to modify the configuration of the `WhiteWebSdk` instance.
 */
export declare type WhiteWebSdkConfiguration = {
    /**
     * 是否用 MobX 监听 `displayer.state`：
     * - `true`：用 MobX 监听 `displayer.state`。此时 `displayer.state` 会变成 MobX observable object，详见 [Creating observable state](https://mobx.js.org/observable-state.html#observable)。
     * - `false`：不用 MobX 监听 `displayer.state`。
     *
     * Whether to use MobX to listen for `displayer.state`:
     * - `true`：Use MobX to listen for `displayer.state`, which turns `displayer.state` to a MobX observable object. See [Creating observable state](https://mobx.js.org/observable-state.html#observable).
     * - `false`：Not use MobX to listen for `displayer.state`.
     */
    useMobXState?: boolean;
    /**
     * 该客户端的设备类型，决定 SDK 如何处理鼠标事件和触碰事件。
     * The type of the device, which determines how the SDK handles mouse and touch events.
     *
     * 如果填写错误，会导致 SDK 对设备输入的响应行为不符合预期。如果不填，SDK 会根据内在逻辑自动判断设备的类型。
     * If you pass in an incorrect value, the SDK may not respond to the device inputs as expected.
     * If you do not pass in a value, the SDK determines the device type using its internal logic.
     */
    deviceType?: DeviceType;
    /**
     * 该客户端的屏幕类型，用于调整手势识别参数。默认为 `Desktop`。
     * The type of the user's screen, which is used to adjust parameters for gesture recognition.
     */
    screenType?: ScreenType;
    /**
     * 抓手工具对应的快捷键。
     * The hotkey for the hand tool.
     *
     * 按下该键时，会自动切换成**抓手工具**（`currentApplianceName="hand"`）；松开后，切回原来的工具。如果不传，则关闭该快捷键功能。
     * When you press the hotkey, the whiteboard tool automatically switches to the hand tool (`currentApplianceName="hand"`).
     * Once you release the key, the whiteboard tool switches back to your original tool in use.
     * If you do not set this parameter, the hotkey switch function is disabled.
     */
    handToolKey?: string;
    /**
     * 设置文字工具（`currentApplianceName="text"`）的字体。若不传，SDK 会使用浏览器的默认字体。
     * The font of the characters entered by the text tool. If you do not set this parameter, the SDK uses the default font of the browser.
     */
    fontFamily?: string;
    /**
     * 断线重连设置。默认开启自动断线重连。
     * Reconnection configuration. The reconnection mechanism is enabled by default.
     *
     * 如果要关闭断线重连，你可以将 `reconnectionOptions` 设为 `false` 或将 `disableReconnect` 设为 `true`。
     * 详见[《实时房间状态管理》](/document-zh/home/room-state-management)。
     * To disable the reconnection mechanism, set `reconnectionOptions` as `false` or `disableReconnect` as `true`.
     * See [Real-time room status management](https://developer.netless.link/document-zh/home/room-state-management)
     */
    reconnectionOptions?: Partial<ReconnectionOptions> | false;
    /**
     * 由 `React.ComponentType` 类型组成的数组，用于包装白板的界面。默认值为 []。
     * An array of `React.ComponentType` objects, which is used to package the whiteboard view. The default value is [].
     *
     * 你可以使用如下代码对白板的界面进行自定义包装：
     * Refer to the following code to package the whiteboard view:
     *
     * @example
     * ```tsx
     * import React from "react";
     *
     * class WrappedComponent extends React.Component {
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
     *     wrappedComponents: [WrappedComponent],
     * });
     * ```
     */
    wrappedComponents?: WrappedComponents;
    urlInterrupter?:
    /**
     * 将白板中图片等资源的 URL 拦截并替换。
     * Intercepts the URL address of a resource on the whiteboard (for example, an image) and replaces it.
     *
     * 例如，你可以使用如下代码给所有图片的 URL 增加尾缀：
     * For example, you can use the following code to add a suffix for the URL addresses of all images:
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
     *
     * @param url 图片等资源原来的 URL。
     *            The original URL address of the resource.
     * @returns 替换后的 URL。
     *          The new URL address.
     */
    (url: string)=>string;
    onWhiteSetupFailed?:
    /**
     * `WhiteWebSdk` 初始化失败的回调。
     * Occurs when the `WhiteWebSdk` instance fails to initialize.
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
     * 如果是因为参数原因初始化失败，会在调用 `new WhiteWebSdk({...})` 时提示发生错误。该回调仅用于网络或鉴权失败时的错误处理。
     * If there is an error in the parameters, you can get the error message when calling `new WhiteWebSdk({...})`.
     * This callback only reports network and authentication errors.
     *
     * @param error 错误信息。
     *              An error message.
     */
    (error: Error)=>void;
};

/**
 * Configurations for a {@link Room} object.
 *
 * **Note**
 *
 * All methods in this class must be called before calling {@link joinRoom}. Any methods in this class that are called after successfully joining a room do not take effect.
 */
export declare type ConstructRoomParams = {
    /**
     * 设置鼠标的光标适配器，详见 [《鼠标光标适配器》](/javascript-zh/home/cursor-adapter)。
     * Sets the cursor adaptor. See [Mouse cursor adapter](https://developer.netless.link//javascript-zh/home/cursor-adapter)
     */
    cursorAdapter?: CursorAdapter;
    /**
     * 是否关闭自动适配尺寸功能。
     * - `true`：关闭。关闭后，如果视角的尺寸发生改变，必须主动调用 `refreshViewSize` 来保证适配。
     * - `false`：（默认）不关闭。
     *
     * Whether to disable the auto resize function:
     * - `true`：Disable the function. In this case, you need to call `refreshViewSize` every time the size of the view changes.
     * - `false`：(Default) Enable the function.
     */
    disableAutoResize?: boolean;
    /**
     * 由 `React.ComponentType` 类型组成的数组，用于自定义包装白板的界面。默认值为 []。
     * An array of `React.ComponentType` objects, which is used to package the whiteboard view. The default value is [].
     */
    wrappedComponents?: WrappedComponents;
    /**
     * 是否隐藏其他人的鼠标移动到白板组件上时显示的高亮框。
     *
     * @since 2.12.0
     *
     * - `true`：隐藏。
     * - `false`：不隐藏。
     *
     * 该属性不影响自己的高亮框显示。
     */
    /**
     * Whether to disable the highlighted box when another user puts their cursors on an object on the whiteboard:
     *
     * @since 2.12.0
     *
     * - `true`：Disable the highlighted box.
     * - `false`：Enable the highlighted box.
     *
     * This parameter does not affect the highlighted box of the local user.
     */
    disableOthersSelectingBox?: boolean;
};

/**
 * 加入房间的参数设置。
 * Configuration of joining a room.
 */
export declare type JoinRoomParams = ConstructRoomParams & {
    /**
     * 自定义用户信息。该参数可以是任意类型的数据结构。
     * Customized user information. This parameter can use any data type.
     *
     * 房间内其他用户可以通过如下代码读取特定用户在加入房间时设置的 `userPayload`：
     * Refer to the following code to enable other users to read the `userPayload` of a specific user, which is set when the user joining the room:
     *
     * @example
     * ```javascript
     * for (var member of room.state.roomMembers) {
     *     console.log(member.userPayload);
     * }
     * ```
     */
    userPayload?: any;
    /**
     * 是否禁止用户通过鼠标、键盘、触摸屏幕操作白板工具。
     * - `true`： 禁止。
     * - `false`：（默认）不禁止。
     *
     * 该方法与 `isWritable` 的区别详见[《禁止设备操作｜禁止操作》](/javascript-zh/home/disable#禁止设备操作)。
     *
     * Whether to disable the user from operating the whiteboard tools through the mouse, keyboard, or touchscreen:
     * - `true`：Disable the user from operating the whiteboard tools.
     * - `false`：Enable the user to operate the whiteboard tools.
     *
     * This method is different from `isWritable`. See [Prohibited operation](https://developer.netless.link/javascript-en/home/disable#Prohibit-device-operation).
     */
    disableDeviceInputs?: boolean;
    /**
     * 是否允许使用画笔工具（`pencil`）画点。
     * - `true`： 允许。
     * - `false`：（默认）不允许。此时使用画笔工具单击白板绘制的点不会保留在屏幕上。
     *
     * **Note**
     *
     * 该参数只有在 `disableNewPencil` 设为 `false` 时生效。
     */
    /**
     * Whether to enable the pen tool (`pencil`) to draw a point:
     * - `true`： Enable the pen tool to draw a point.
     * - `false`：(Default) Disable the pen tool from drawing a point. In this case, clicking on the whiteboard with the pen tool does not draw a point on the screen.
     *
     * **Note**
     *
     * This parameter takes effect only when `disableNewPencil` is set to `false`.
     */
    enableDrawPoint?: boolean;
    /**
     * 使用选择工具（`currentApplianceName="selector"`）选中物体后，是否出现浮动条：
     * - `true`： 出现。
     * - `false`：（默认）不出现。
     *
     * Whether to enable the floating bar when selecting an object using the selection tool (`currentApplianceName="selector"`):
     * - `true`： Enable the floating bar.
     * - `false`：(Default) Disable the floating bar.
     */
    floatBar?: boolean | Partial<FloatBarOptions>;
    /**
     * 添加自定义快捷键。若不传，则使用**默认快捷键键**方案，具体如下：
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
     * 如果你想关闭快捷键功能，可以将该参数的其值设为 `{}`。
     */
    /**
     * Add custom hotkeys. If you do not set this parameter, the SDK uses the following default hotkeys:
     *
     * | Keys              | Action                     |
     * | :--------------------- | :---------------------- |
     * | Backspace / Delete     | Delete the selected object            |
     * | Shift                  | Resize proportionately |
     * | ctrl + z / command + z | Undo                   |
     * | ctrl + y / command + y | Redo                   |
     * | ctrl + c / command + c | Copy                     |
     * | ctrl + v / command + v | Paste                    |
     *
     * To disable the default hotkeys, set this paramter as `{}`.
     */
    hotKeys?: Partial<HotKeys>;
    /**
     * 没有可写权限的用户进行写操作时，SDK 的应对方式。
     * The response of the SDK when a user without write permission attempts to write.
     */
    rejectWhenReadonlyErrorLevel?: RoomErrorLevel;
};

/**
 * Configurations of the `Player` object.
 */
export declare type ReplayRoomParams = ConstructRoomParams & {
    /**
     * 回放的录像片段的 UUID。你可以在房间录制的时候从 `room.slice` 获取。
     * The unique identifier (UUID) of the slice in the playback, which you can get from `room.slice` during the recording.
     *
     * 该参数需要和 `room` 同时传入。传入该参数表明只回放特定片段，因此禁止再传入 `beginTimestamp` 和 `duration`。
     * This parameter must be passed in together with `room`.
     * Do not pass in `beginTimestamp` or `duration` after passing in this parameter, which indicates playing a specific clip from the recording.
     */
    slice?: string;
    /**
     * Room UUID, the unique identifier of a room. This parameter is returned after the room is created successfully.
     *
     * - 如果只传该参数，不传 `beginTimestamp` 和 `duration`，则表明回放该房间的所有录像片段。
     * - 如果同时传入该参数和 `beginTimestamp` 和 `duration`，则表明回放该房间在对应时间范围内的所有录像片段。
     *
     * - If you pass in this parameter only, the SDK replays all recordings of the room.
     * - If you pass in this parameter together with `beginTimestamp` and `duration`, the SDK replays the recordings within the specified time range.
     */
    room: string;
    /**
     * The Unix timestamp (ms) representing the starting UTC time of the playback.
     *
     * 该参数必须和 `room`、`duration` 一起使用，而且使用时禁止传入 `slice` 参数。
     * This parameter must be passed in together with `room` and `duration` and not together with `slice`.
     */
    beginTimestamp?: number;
    /**
     * The playback duration (ms).
     *
     * 该参数必须和 `room`、`beginTimestamp` 一起使用，而且使用时禁止传入 `slice` 参数。
     * This parameter must be passed in together with `room` and `beginTimestamp` and not together with `slice`.
     */
    duration?: number;
};

/**
 * 检查白板房间是否可以回放的参数配置。
 * Configurations of checking if the whiteboard content of a live room can be replayed.
 */
export declare type PlayableCheckingParams = {
};

/**
 * The `WhiteWebSdk` class initializes a `WhiteWebSdk` instance.
 */
export declare class WhiteWebSdk {
    /**
     * The data center.
     *
     * 如果在调用 `joinRoom`、`replayRoom`、`isPlayable` 时没有指定 `region`，则以该参数的值为准。
     * If you do not specify `region` when calling `joinRoom`, `replayRoom`, or `isPlayable`, this parameter takes effect.
     */
    readonly region: string;

    /**
     * 该客户端的设备类型，决定 SDK 如何处理鼠标事件和触碰事件。
     * The device type, which determines how the SDK handles mouse and touch events.
     */
    readonly deviceType: DeviceType;

    /**
     * 用户的屏幕类型，用于调整手势识别参数。
     * The type of the user's screen, which is used to adjust parameters for gesture recognition.
     */
    readonly screenType: ScreenType;

}

/**
 * @deprecated 该类已废弃。请使用 `UserPayload` 自定义用户信息。
 * @deprecated You can use `UserPayload` instead.
 */
export declare type MemberInformation = {
    /**
     * The ID of the user.
     */
    id: number;
    /**
     * The nickname of the user.
     */
    nickName: string;
    /**
     * @ignore
     */
    isOwner: boolean;
    /**
     * The avatar of the user.
     */
    avatar?: string;
};


/**
 * 组件插件的渲染。
 * Rendering of the component plugin.
 */
export declare type RenderPlugin<C = {
}, T = any> = {
    /**
     * 组件插件类型，为该组件插件的唯一识别符。
     * The type of the component plugin, which is the unique identifier of the component plugin.
     */
    kind: string;
    /**
     * 组件插件的外观。
     * The style of the component plugin.
     */
    render: ComponentType<PluginProps<C, T>>;
    /**
     * 组件插件属性的默认值。
     * The default attributes of the component plugin.
     */
    defaultAttributes?: T;

    hitTest?:
    /**
     * 碰撞检测。你可以通过碰撞检测定义组件插件可以被选择工具选中的区域。
     *
     * @param plugin 组件插件实例。
     * @param x 可选中区域的中心点的 X 轴坐标。
     * @param y 可选中区域的中心点的 Y 轴坐标。
     * @param selectorRadius 可选中区域的半径。
     *
     * @returns 碰撞检测的结果：
     * - `true`：检测成功。
     * - `false`：检测失败。
     *
     * Hit test, which you can use to define the selectable area of the component plugin for the selection tool.
     *
     * @param plugin The component plugin instance.
     * @param x The X coordinate of the center of the selectable area.
     * @param y The Y coordinate of the center of the selectable area.
     * @param selectorRadius The radius of the selectable area.
     *
     * @returns The result of the hit test:
     * - `true`：The hit test succeeds.
     * - `false`：The hit test fails.
     */
    (plugin: PluginInstance<C, T>, x: number, y: number, selectorRadius: number)=>boolean;

    willInterruptEvent?:
    /**
     * 是否拦截该组件插件的原生事件。
     *
     * @param plugin 组件插件实例。
     * @param event 原生事件。
     *
     * @returns
     * - `true`：拦截。
     * - `false`：不拦截。
     *
     * Whether to interrupt the native events of the component plugin:
     *
     * @param plugin The component plugin instance.
     * @param event The native events.
     *
     * @returns
     * - `true`：Interrupt the native events.
     * - `false`：Not interrupt the native events.
     */
    (plugin: PluginInstance<C, T>, event: NativeEvent)=>boolean;
};

/**
 * 键盘类型。
 * The keyboard type.
 */
export declare enum KeyboardKind {
    /**
     * Mac 电脑的键盘。
     * Mac keyboard.
     */
    Mac = "mac",
    /**
     * Windows 电脑的键盘。
     * Windows keyboard.
     */
    Windows = "windows",
}

/**
 * 场景。
 * A scene.
 */
export declare type WhiteScene = {
    /**
     * 场景名称。
     * The scene name.
     */
    name: string;
    /**
     * 场景所包含的 PPT 页面，详见 {@link PptDescription}。
     * The PPT slide of the scene. See {@link PptDescription}.
     */
    ppt?: PptDescription;
};

/**
 * 图形工具的种类。
 * The shape types provided by the shape tool.
 *
 * @since 2.12.0
 */
export declare enum ShapeType {
    /**
     * Triangle.
     */
    Triangle = "triangle",
    /**
     * Rhombus
     */
    Rhombus = "rhombus",
    /**
     * Pentagram.
     */
    Pentagram = "pentagram",
    /**
     * Speech ballon.
     */
    SpeechBalloon = "speechBalloon",
}

/**
 * 空白区域的设置。
 * Margin configuration.
 */
export declare type Margin = {
    /**
     * 上方的空白。
     * The top margin.
     */
    top: number;
    /**
     * 下方的空白。
     * The bottom margin.
     */
    bottom: number;
    /**
     * 左边的空白。
     * The left margin.
     */
    left: number;
    /**
     * 右边的空白。
     * The right margin.
     */
    right: number;
};
