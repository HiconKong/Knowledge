# 技术方案
    + 功能点
        + 定位到当前城市
            + 调用ios 原生sdk(CLLocationManager) 获取本地经纬度 (CLLocation) -> CLGeocoder.reverseGeocodeLocation 将经纬度转化成具体地址显示在ui上
        + 用户选择地图任意一点并显示所选城市
            + GMSMapViewDelegate -> didTapAtCoordinate -> 得到经纬度，反编码后显示当前选择位置的城市名称
        + 用户点击"我的位置"按钮回到设备当前所在位置
            + CLLocationManagerDelegate -> didUpdateLocations -> 得到经纬度，反编码后显示当前选择位置的城市名称
        + 返回
            + 根据外部传入的locationInfo将全局变量的经纬度及位置的初始值赋回去并退出当前页面
        + 确认
            + 根据用户所选择的地点将经纬度及位置传回rn

# 需确认点:
    # 位置无法通过google map sdk获取，需利用ios sdk 反编码获得，导致时间过长
    # maker中的info message 可以自定义，产品需求根据1倾向显示加载圈(目前未获取到具体位置显示"未知")
    # ios 原生api 使用WGS-84坐标，google sdk使用WGS-84经过GCJ-02加密坐标，导致日出日落页面获取的定位与Google-"我的位置"蓝点不匹配
    # ios 原生api在google 地图页面获取的经纬度与Google-"我的位置"蓝点匹配，无法确认ios原生api在两次获取定位中的差异由何造成
    # GoogleSDK 确认使用版本类别 https://cloud.google.com/maps-platform/pricing/sheet/

# CoreLocation框架如何引入

# 相关API:
+ CLLocationManager
    + 坑点
        + IOS 8.0 以上 plist及代码中需要请求权限
        + 获取当前位置需要在主线程中调用才会触发回调
        + WGS-84坐标系(google国内需要经过GCJ-02加密，Google国外使用WGS-84)
    + API 能力
        + startUpdatingLocation
            + 启动更新用户当前位置。
        + stopUpdatingLocation
            + 关闭更新用户当前位置。
        + requestLocation
            + 单次请求用户当前位置。(9.0后新增，按照定位精确度从低到高进行排序，逐个进行定位，耗时较长)
        + distanceFilter
            + 当本次定位和上次定位之间的距离大于或等于这个值时，调用代理方法
        + desiredAccuracy
            + 设置定位精度
                + kCLLocationAccuracyBest
                + kCLLocationAccuracyKilometer
                + kCLLocationAccuracyHundredMeters
                + kCLLocationAccuracyThreeKilometers
                + kCLLocationAccuracyNearestTenMeters
                + kCLLocationAccuracyBestForNavigation
        + requestWhenInUseAuthorization
        + requestAlwaysAuthorization
        

+ CLLocation
    + API 能力
        + coordinate
            + 当前位置的坐标
                + latitude : 纬度
                + longitude : 经度
        + altitude
            + 海拔，高度
        + floor
            + 用户所在建筑物的逻辑层
        + horizontalAccuracy
            + 纬度和经度的精度
        + verticalAccuracy
            + 垂直精度(获取不到海拔时为负数)
        + timestamp
            + 确定此位置的时间
        + speed
            + 瞬时速度，以米每秒为单位
        + course
            + 移动的方向，以角度和相对正北的角度来测量。

CLGeocoder
    + 坑点
        + 直辖市无法获取
        + 反编码时需要等待时间较长
    + 能力:经纬度及详细地址互查(双向)