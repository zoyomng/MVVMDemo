# Android 第三方支付

## 支付宝支付

```
AliPay.alipayPay(orderInfo, Activity_Pay.this);
```

> orderInfo : 后台传回的加密后的支付串 传参类型String

#### 支付方法
```java
public class AliPay {

    private static Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                ToastUtil.shortShow("支付成功");

            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                ToastUtil.shortShow("支付失败");
            }
        }
    };
    private static Activity mContext;

    public static void alipayPay(final String orderInfo, Activity mContext) {
        AliPay.mContext = mContext;
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(mContext); // 按需修改此处的PayActivity
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
```

## 微信支付

```
WXPayResultBean wxPayResultBean = GsonUtils.json2Bean(response, WXPayResultBean.class);
WXPayResultBean.DataBean wxData = wxPayResultBean.data;

WechatPay.wechatPay(wxData, mContext);
```

> wxData : WXPayResultBean.DataBean

##### 传参类型JavaBean
```java
public class WXPayResultBean implements Serializable {

    /**
     * flag : success
     * message : 请求成功
     * error_code : 0000
     * data : {"appid":"wxbb417d4b343286c0","partnerid":"1464332102","noncestr":"bss3I62W1T5gZPaj","prepayid":"wx20171221120811b5400f0a6e0013051063","package":"Sign=WXPay","timestamp":1513829291,"sign":"BB2291ADDDA6185BA72CBBB0F942B191","free":-1}
     */

    public String flag;
    public String message;
    public String error_code;
    public DataBean data;

    public static class DataBean {
        /**
         * appid : wxbb417d4b343286c0
         * partnerid : 1464332102
         * noncestr : bss3I62W1T5gZPaj
         * prepayid : wx20171221120811b5400f0a6e0013051063
         * package : Sign=WXPay
         * timestamp : 1513829291
         * sign : BB2291ADDDA6185BA72CBBB0F942B191
         * free : -1
         */

        public String appid;
        public String partnerid;
        public String noncestr;
        public String prepayid;
        @SerializedName("package")
        public String packageX;
        public int timestamp;
        public String sign;
        public int free;
    }
}
```

#### 支付方法
```java
public class WechatPay {

    private static IWXAPI api;

    /**
     * 调起微信支付
     *
     * @param data
     */
    public static void wechatPay(WXPayResultBean.DataBean data , Context mContext) {

        api = WXAPIFactory.createWXAPI(mContext, null);
        api.registerApp(Constants.WX_APPID);

        PayReq req = new PayReq();
        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
        req.appId			= data.appid;
        req.partnerId		= data.partnerid;
        req.prepayId		= data.prepayid;
        req.nonceStr		= data.noncestr;
        req.timeStamp		= String.valueOf(data.timestamp);
        req.packageValue	= data.packageX;
        req.sign			= data.sign;
        req.extData			= "app data"; // optional
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }
}
```

#### 支付结果回调
```java
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);

        api = WXAPIFactory.createWXAPI(this, Constants.WX_APPID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {

        String payResult = "";

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {

            RxMainTabBean instance = RxMainTabBean.instance();
            instance.fragmentTabHost4Main = FRAGMENT_ORDER_TAB;

            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    payResult = "支付成功!";

                    break;
                default:
                    payResult = "微信支付失败!";
                    ToastUtil.shortShow(payResult);

                    break;
            }
        } else {
            finish();
        }
    }
}
```

#### AndroidManifest.xml
```
<!-- 支付宝支付：start -->
<activity
    android:name="com.alipay.sdk.app.H5PayActivity"
    android:configChanges="orientation|keyboardHidden|navigation|screenSize"
    android:exported="false"
    android:screenOrientation="behind"
    android:windowSoftInputMode="adjustResize|stateHidden"/>
<activity
    android:name="com.alipay.sdk.app.H5AuthActivity"
    android:configChanges="orientation|keyboardHidden|navigation"
    android:exported="false"
    android:screenOrientation="behind"
    android:windowSoftInputMode="adjustResize|stateHidden"/>

<!-- 微信支付:start -->
<activity
    android:name=".wxapi.WXPayEntryActivity"
    android:exported="true"
    android:launchMode="singleTop"/>

<provider
    android:name="android.support.v4.content.FileProvider"
    android:authorities="com.tangchaoke.hrhj.huarunhaojing"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/file_paths"/>
</provider>
```