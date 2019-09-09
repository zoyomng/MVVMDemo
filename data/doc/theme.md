#Android Theme
##应用快速启动-显示Splash界面

![splash](../img/ic_splash.png "splash")

> * AndroidManifest.xml
```xml
<activity
    android:name=".ui.activity.Activity_Splash"
    android:configChanges="mnc|mcc|locale|touchscreen|keyboard|keyboardHidden|fontScale|uiMode|orientation|screenSize|smallestScreenSize|layoutDirection"
    android:screenOrientation="portrait"
    android:theme="@style/SplashTheme">
    <intent-filter>
        <action android:name="android.intent.action.MAIN"/>
        <category android:name="android.intent.category.LAUNCHER"/>
    </intent-filter>
</activity>
```

> * Splash界面主题
```xml
<style name="SplashTheme" parent="@style/Theme.AppCompat.Light.NoActionBar.FullScreen">
    <item name="android:windowBackground">@mipmap/ic_splash</item>
</style>
```

> 应用快速启动-显示Splash界面,避免应用启动时几秒的延迟白屏显示
