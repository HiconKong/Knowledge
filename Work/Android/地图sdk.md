+ 谷歌开放平台 - 账号 隐私 权限 能力
    + 权限
        + 位置权限:如果您的应用程序通过启用“我的位置”图层来访问用户的当前位置，则必须按照位置数据 指南中的说明 请求位置权限。
            android.permission.ACCESS_COARSE_LOCATION - 允许API使用WiFi或移动单元数据（或两者）来确定设备的位置。API返回的位置精确度大致相当于城市街区。
            android.permission.ACCESS_FINE_LOCATION - 允许API从可用的位置提供商处确定尽可能精确的位置，包括全球定位系统（GPS）以及WiFi和移动单元数据。


        + 外部存储权限:如果您的目标是版本8.3或更高版本的Google Play服务SDK，则您不再需要WRITE_EXTERNAL_STORAGE使用Maps SDK for Android 的 权限。如果您要定位早期版本的Google Play服务SDK，则必须申请该 android.permission.WRITE_EXTERNAL_STORAGE 权限。

            <uses-permission
                    android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
            注意：如果您的应用定位到API级别23（Android 6.0），这需要使用 运行时权限，则应定位到版本8.3或更高版本的Google Play服务SDK。

            权限自动合并到您的清单中
        + 以下权限在Google Play服务清单中定义，并在构建时自动合并到您应用的清单中。你 并不需要明确地将它们添加到您的清单：

            android.permission.INTERNET - 由API用于从Google地图服务器下载地图图块。
            android.permission.ACCESS_NETWORK_STATE - 允许API检查连接状态，以确定是否可以下载数据。

    + 能力
        + Maps SDK for Android		
        + BigQuery API					
        + Cloud Datastore API					
        + Cloud SQL					
        + Cloud Storage					
        + Google Cloud APIs					
        + Google Cloud Storage JSON API					
        + Google Play Android Developer API					
        + Google Play Game Services Publishing API					
        + Service Management API					
        + Stackdriver Debugger API					
        + Stackdriver Logging API					
        + Stackdriver Monitoring API					
        + Stackdriver Trace API	



+ 数据准确性

+ api能力

