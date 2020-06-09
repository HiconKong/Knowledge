# svg 基本显示原理
  - canvas
  - viewport
  - origin:左上角(0，0)，向右为x，向下为y
  - viewbox:(min-x,min-y,width,height)
  - preserveAspectRatio
  - px(default),em,ex,px,pt,pc,cm,mm,in,%
  - eg:viewbox="0 0 100 100"
    - 在画布上划分出来一个 100x100 大小的区域，放在 (0, 0) 点上
    - 把 SVG 图形缩放成合适这个区域的样子
    - 将整个区域（包括里面的图形）放大到铺满整个观察孔
    - 将这个坐标系统按比例映射到到初始坐标系统上

# 基本属性 CommonPathProps:
  - FillProps
    - fill?: Color;
      - (int32ARGBColor | rgbaArray | string)
    - fillOpacity?: NumberProp;
    - fillRule?: FillRule;
      - evenodd
      - nonzero
  - StrokeProps
    - stroke?: Color;
    - strokeWidth?: NumberProp;
    - strokeOpacity?: NumberProp;
    - strokeDasharray?: ReadonlyArray<NumberProp> | NumberProp;
    - strokeDashoffset?: NumberProp;
    - strokeLinecap?: Linecap;
      - 'butt' | 'square' | 'round'
    - strokeLinejoin?: Linejoin;
      - 'miter' | 'bevel' | 'round'
    - strokeMiterlimit?: NumberProp;
  - ClipProps
    - clipRule?: FillRule;
    - clipPath?: string;
  - TransformProps
    - transform?: ColumnMajorTransformMatrix | string | TransformObject;
      - TransformObject
        - translate?: NumberArray;
        - translateX?: NumberProp;
        - translateY?: NumberProp;
        - origin?: NumberArray;
        - originX?: NumberProp;
        - originY?: NumberProp;
        - scale?: NumberArray;
        - scaleX?: NumberProp;
        - scaleY?: NumberProp;
        - skew?: NumberArray;
        - skewX?: NumberProp;
        - skewY?: NumberProp;
        - rotation?: NumberProp;
        - x?: NumberArray;
        - y?: NumberArray;
  - VectorEffectProps
    - vectorEffect:
      - 'none'
      - 'non-scaling-stroke'
      - 'nonScalingStroke'
      - 'default'
      - 'inherit'
      - 'uri';
  - ResponderProps
    - pointerEvents?: 'box-none' | 'none' | 'box-only' | 'auto';
  - TouchableProps
    - disabled?: boolean;
    - onPress?: (event: GestureResponderEvent) => void;
    - onPressIn?: (event: GestureResponderEvent) => void;
    - onPressOut?: (event: GestureResponderEvent) => void;
    - onLongPress?: (event: GestureResponderEvent) => void;
    - delayPressIn?: number;
    - delayPressOut?: number;
    - delayLongPress?: number;
  - DefinitionProps
    - id?: string;
  - CommonMarkerProps
    - marker?: string;
    - markerStart?: string;
    - markerMid?: string;
    - markerEnd?: string;
  - CommonMaskProps
    - mask?: string;
# 视图容器 Svg:
  - props:
    - width?: NumberProp;
    - height?: NumberProp;
    - viewBox?: string;
    - preserveAspectRatio?: string;
    - color?: Color;
    - title?: string;

# 矩形 Rect
  - props:
    - x?: NumberProp;
      - desc:The x prop defines the left position of the rectangle
    - y?: NumberProp;
      - desc:The y prop defines the top position of the rectangle
    - width?: NumberProp;
    - height?: NumberProp;
    - rx?: NumberProp;
      - desc:The rx defines the x-axis radius of the ellipse used to round off the corners of the rectangle
    - ry?: NumberProp;
      - desc:The ry defines the y-axis radius of the ellipse used to round off the corners of the rectangle
    - opacity?: NumberProp;

# 圆形 Circle
  - props:
    - cx?: NumberProp;
    - cy?: NumberProp;
    - opacity?: NumberProp;
    - r?: NumberProp;
# 椭圆形 Ellipse
  - props:
    - cx?: NumberProp;
    - cy?: NumberProp;
    - opacity?: NumberProp;
    - rx?: NumberProp;
    - ry?: NumberProp;
# 直线 Line
  - props:
    - opacity?: NumberProp;
    - x1?: NumberProp;
    - x2?: NumberProp;
    - y1?: NumberProp;
    - y2?: NumberProp;
# 多边形 Polygon
  - props:
    - opacity?: NumberProp;
    - points?: string | ReadonlyArray<NumberProp>;
# 多线段 Polyline
  - props:
    - opacity?: NumberProp;
    - points?: string | ReadonlyArray<NumberProp>;
# 路径 Path:
  * 常见命令
    - M/m (x,y)	移动当前位置
    - L/l (x,y)	从当前位置绘制线段到指定位置
    - H/h (x)	从当前位置绘制水平线到达指定的 x 坐标
    - V/v (y)	从当前位置绘制竖直线到达指定的 y 坐标
    - Z/z 	闭合当前路径
    - C/c (x1,y1,x2,y2,x,y)	从当前位置绘制三次贝塞尔曲线到指定位置
    - S/s (x2,y2,x,y)	从当前位置光滑绘制三次贝塞尔曲线到指定位置
    - Q/q (x1,y1,x,y)	从当前位置绘制二次贝塞尔曲线到指定位置
    - T/t (x,y)	从当前位置光滑绘制二次贝塞尔曲线到指定位置
    - A/a (rx,ry,xr,laf,sf,x,y)	从当前位置绘制弧线到指定位置
  * 命令基本规律
    - 参数之间可以用空格或逗号隔开，如果下一个数值是负数，才可以省略
    - 区分大小写：大写表示坐标参数为绝对位置，小写则为相对位置 
    - 最后的参数表示最终要到达的位置
    - 上一个命令结束的位置就是下一个命令开始的位置
    - 命令可以重复参数表示重复执行同一条命令
  * 移动和直线命令
    - M(x,  y):移动画笔，后面如果有重复参数，会当做是L命令处理
    - L(x,  y):绘制直线到指定位置
    - H(x):绘制水平线到指定的x位置
    - V(y):绘制竖直线到指定的y位置 
    - m、l、h、v:使用相对位置绘制。
  * 弧线命令(A/a)
    - rx（radius-x）弧线所在椭圆的x半轴长
    - ry（radius-y）弧线所在椭圆的y半轴长
    - xr（xAxis-rotation）弧线所在椭圆的长轴角度
    - laf（large-arc-flag）是否选择弧长较长的那一段弧1为大弧,0 为小弧
    - sf（sweep-flag）是否选择逆时针方向的那一段弧1为顺时针,0 为逆时针
    - x,y 弧的终点位置
  * 贝塞尔曲线
# TSpan
  - desc:调整文本和字体的属性以及当前文本的位置、绝对或相对坐标值 
  - props:
    - x?: NumberArray;
    - y?: NumberArray;
    - dx?: NumberArray;
    - dy?: NumberArray;
    - rotate?: NumberArray;
    - inlineSize?: NumberProp;
# TextPath
  - desc: 只要在textPath元素内部放置文本，并通过其xlink:href属性值引用<path>元素，我们就可以让文字块呈现在<path>元素给定的路径上了
  - props:
    - xlinkHref?: string;
    - href?: string;
    - startOffset?: NumberProp;
    - method?: TextPathMethod;
    - spacing?: TextPathSpacing;
      - 'auto' | 'exact'
    - midLine?: TextPathMidLine;
      - 'sharp' | 'smooth'
# G
  - desc:元素g是用来组合对象的容器。添加到g元素上的变换会应用到其所有的子元素上。添加到g元素的属性会被其所有的子元素继承。此外，g元素也可以用来定义复杂的对象，之后可以通过<use>元素来引用它们。
  - props:
    - opacity?: NumberProp;
# Use
  - desc:use元素在SVG文档内取得目标节点，并在别的地方复制它们。它的效果等同于这些节点被深克隆到一个不可见的DOM中，然后将其粘贴到use元素的位置
  - props:
    - xlinkHref?: string;
    - href?: string;
    - width?: NumberProp;
    - height?: NumberProp;
    - x?: NumberProp;
    - y?: NumberProp;
    - opacity?: NumberProp;
# Symbol
  - desc:symbol元素用来定义一个图形模板对象，它可以用一个<use>元素实例化。symbol元素对图形的作用是在同一文档中多次使用，添加结构和语义。结构丰富的文档可以更生动地呈现出来，类似讲演稿或盲文，从而提升了可访问性。注意，一个symbol元素本身是不呈现的。只有symbol元素的实例（亦即，一个引用了symbol的 <use>元素）才能呈现
  - props:
    - id?: string;
    - viewBox?: string;
    - preserveAspectRatio?: string;
    - opacity?: NumberProp;
# Defs
  - desc:SVG 允许我们定义以后需要重复使用的图形元素。 建议把所有需要再次使用的引用元素定义在defs元素里面。这样做可以增加SVG内容的易读性和可访问性。 在defs元素中定义的图形元素不会直接呈现。 你可以在你的视口的任意地方利用 <use>元素呈现这些元素。
# 图片 Image
  - props:
    - x?: NumberProp;
    - y?: NumberProp;
    - width?: NumberProp;
    - height?: NumberProp;
    - xlinkHref?: ReactNative.ImageProps['source'];
    - href?: ReactNative.ImageProps['source'];
    - preserveAspectRatio?: string;
    - opacity?: NumberProp;
    - clipPath?: string;
    - id?: string;
# 剪切路径 ClipPath
  - props:
    - id?: string;
# LinearGradient
  - desc:定义线性渐变，用于图形元素的填充或描边。
  - props:
    - x1?: NumberProp;
    - x2?: NumberProp;
    - y1?: NumberProp;
    - y2?: NumberProp;
    - gradientUnits?: Units;
      - 'userSpaceOnUse' | 'objectBoundingBox'
    - gradientTransform?: ColumnMajorTransformMatrix | string;
    - id?: string;
# RadialGradient
  - desc:定义径向渐变，以对图形元素进行填充或描边
  - props:
    - fx?: NumberProp;
    - fy?: NumberProp;
    - rx?: NumberProp;
    - ry?: NumberProp;
    - cx?: NumberProp;
    - cy?: NumberProp;
    - r?: NumberProp;
    - gradientUnits?: Units;
    - gradientTransform?: ColumnMajorTransformMatrix | string;
    - id?: string;
# Mask
  - desc:将任何其他图形对象或'G'元素用作将当前对象合成到背景中的alpha蒙版
  - props:
    - id?: string;
    - x?: NumberProp;
    - y?: NumberProp;
    - width?: NumberProp;
    - height?: NumberProp;
    - maskTransform?: ColumnMajorTransformMatrix | string;
    - maskUnits?: TMaskUnits;
      - 'userSpaceOnUse' | 'objectBoundingBox'
    - maskContentUnits?: TMaskUnits;
# Pattern
  - desc:使用预定义的图形对一个对象进行填充或描边，就要用到pattern元素。pattern元素让预定义图形能够以固定间隔在x轴和y轴上重复（或平铺）从而覆盖要涂色的区域。先使用pattern元素定义图案，然后在给定的图形元素上用属性fill或属性stroke引用用来填充或描边的图案。
  - props:
    - id?: string;
    - x?: NumberProp;
    - y?: NumberProp;
    - width?: NumberProp;
    - height?: NumberProp;
    - patternTransform?: ColumnMajorTransformMatrix | string;
    - patternUnits?: Units;
    - patternContentUnits?: Units;
    - viewBox?: string;
    - preserveAspectRatio?: string;

# 相关概念理解
  * 极坐标系:在平面内由极点、极轴和极径组成的坐标系
  * 笛卡尔坐标系:直角坐标系和斜坐标系的统称
  * 弧度:角的度量单位，一周的弧度数为2π
  * 角度:两条相交直线中的任何一条与另一条相叠合时必须转动的量的量度

# UIID 105 浴霸温度表盘设计思路
  - 利用path 绘制两个环形，其中一个作为底色，另一个用于标示温度值
    - 确定开口弧度(π / 4)
    - 建立极坐标系，确定起点，终点，圆心，半径，弧度(极角)
    - 将极坐标系的信息转换成起始点的(x1,y1),终点(x2,y2)
    - 建立笛卡尔坐标系，将起始点和终点绘制在坐标系上
    - 根据已知起点(x1,y1) 终点(x2,y2)以及圆半径radius,利用svg-path(标签)-a(弧线)绘制出两个圆弧，根据path标签的属性取得对应弧形
  - 利用circle 绘制停止点
    - 计算停止点的弧度(当前点对应弧度+开口弧度)
      - 总弧度:2 * π - 2 * openingRadian
      - 当前值对应弧度:(2 * π * - 2 * openingRadian) * (max-value)/(max-min)
      - 开口弧度: openingRadian 
        - 停止点的弧度:((π - openingRadian) * 2 * (max - value)) / (max - min) + openingRadian
    - 将停止点的弧度转化为(x3,y3)
  - 利用circle 绘制内圈(内圈半径innerRadius))
    - 获取圆心坐标center(svg画布宽高的一半)
    - 对整个圆弧进行等分，份数为(2 * (Math.PI - openingRadian)) / ((max - min) * 2)
    - 遍历每一份i进行画圆
      - x : center.x - innerRadius * Math.sin(i)
      - y = center.y + innerRadius * Math.cos(i);
    - 根据当前停止点的弧度与当前份数的弧度的大小关系，对圆进行上色
  - 利用text 绘制圈外文字
    - 对整个圆弧进行等分，份数为(2 * (Math.PI - openingRadian)) / 6 (6为均分份数)
    - 以起始角度为头，终点角度为尾，遍历每一份，并将当前弧转化为(x,y)
    - 对x轴，当弧度为π时，不需要偏移，当大于π时，向左偏移20px，当小于π时，向右偏移20px
    - 对y轴，当弧度为π时，向上偏移20px，当不等于π时，不需要偏移
  - 使用animation 通过平移旋转根据当前标示值转换 
    - 以svg中心为圆点，填充一张指针图(偏移量的计算原点为图中心)
    - x轴偏移量:当前点坐标curPoint.x-中心点坐标center.x*0.75
    - y轴偏移量:当前点坐标curPoint.y-中心点坐标center.y*0.75
    - 旋转角度:当前的角度的反向即180-curDeg
    - 注:view外层的translate会被内部的transform属性覆盖，且内部transform的设置顺序为先平移后旋转，否则会改变坐标原点

  # 参考资料
    * https://www.sarasoueidan.com/blog/svg-coordinate-systems/
    * https://developer.mozilla.org/zh-CN/docs/Web/SVG/Element
    * https://developer.mozilla.org/zh-CN/docs/Web/SVG/Tutorial/Paths
    * http://www.ab126.com/Geography/3753.html
    * https://github.com/react-native-community/react-native-svg

