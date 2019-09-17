# push 排坑记录
- 友盟
    dealWithNotificationMessage:通知消息回调,方法内部创建通知渠道
    dealWithCustomMessage:自定义消息回调
    getNotification:自定义通知栏样式
    各大厂商push key:
                    - vivo:vivo_payload
                    - 华为:extras
                    - 小米:key_message
                    - 魅族:meizu_payload
- 华为 
    - 标题 非必填 最大长度40
    - 消息内容 必填 最大长度80
- 魅族 
    - 消息内容 必填，字数限制2000以内
- 小米 
    - 友盟继承的UmengNotifyClickActivity，实际上是onNotificationMessageClicked，需要miui7以上才可以触发
- oppo 
    - 通知栏标题不能为空，字数限制1-32，中英文均以一个计算
    - 通知内容不能为空，200字数限制，中英文均以一个计算
    - channelID&channelName配置
- vivo 
    - 混淆文件中的VPushMessageReceiverImpl需要自己实现并上报token，否则会有闪退异常
    - payload内容限制1024字符 
    - channel默认关闭，需要手动打开
    - content内容若包含test或非正式文案无法使用







# 易微联现有push类型统计
+ shareResult:无后续动作
+ cancelShareNotify :无后续动作
+ updateShareNotify:无后续动作
+ videoNotify:无后续动作 (比特眼摄像头报警？)

+ shareNotify (走长链接，根据不同的action做不同的事情)
	+ 前台:
		+ 弹出对话框--选择是否接受分享--长链接发送接受/拒绝反馈--关闭对话框
		  + 接受:长链接发送接受反馈->刷新设备列表->关闭对话框
		  + 拒绝:长链接发送接受反馈->关闭对话框
	+ 后台:
		+ 保存推送信息至本地->启动主页面->弹出对话框--选择是否接受分享--长链接发送接受/拒绝反馈--关闭对话框
		  + 接受:长链接发送接受反馈->刷新设备列表->关闭对话框
		  + 拒绝:长链接发送接受反馈->关闭对话框
	break;

+ advNotify
	+ 前台:
		+ 跳转到指定的url(WebViewScreen)
	+ 后台:
		+ 保存推送信息至本地->启动主页面->跳转到指定的url(WebViewScreen)


+ opsNotify,sceneNotify(没有在android中使用过，或已弃用):
	+ 前台:
		+ 是否处于操作记录页面
          + 是(刷新)
          + 否(跳转到操作记录页面)
	+ 后台:
		+ 保存推送信息至本地->启动主页面->是否处于操作记录页面
          + 是(刷新)
          + 否(跳转到操作记录页面)

+ alarmNotify
	+ 前台:
		+ 弹出toast(进入RFBridge详情中报警历史了解更多)
	+ 后台:
		+ 保存推送信息至本地->启动主页面->弹出toast(进入RFBridge详情中报警历史了解更多)

+ smartLife (iot摄像头及手机摄像头)
	+ 前台:
		+ 跳转到手机摄像头详情页面
	+ 后台:
		+ 保存推送信息至本地->启动主页面->跳转到手机摄像头详情页面

+ upgradeNotify
	+ 前台:
		+ 无后续操作
	+ 后台:
		+ 启动主页面

		
+ 密睿摄像头 android使用极光推送