# Android 不同设备引起的适配

## 定位(高德)
> * 华为手机

> 如华为手机未开启“位置信息”，将不能进行定位，需要引导用户进行设置

```java
/**
 * 定位监听
 */
private AMapLocationListener locListener = new AMapLocationListener() {
    @Override
    public void onLocationChanged(AMapLocation location) {
        if (null != location) {
            //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
            if (location.getErrorCode() == 0) {
                if (mOnLocListener != null) {
                    mOnLocListener.locSuccess(location);
                }
            } else if (location.getErrorCode() == 12 && !mOpenLocationInfoDialogIsShowing) {
                // 如华为手机未开启“位置信息”，将不能进行定位，需要引导用户进行设置
                showOpenLocationInfoDialog();
                mOnLocListener.locFail();
            } else {
                if (mOnLocListener != null) {
                    mOnLocListener.locFail();
                }
            }
        } else {
            if (mOnLocListener != null) {
                mOnLocListener.locFail();
            }
        }
    }
};
```