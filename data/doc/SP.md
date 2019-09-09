# Android SharedPreferences

#### 各种数据类型的put()与get()方法

```java

/**
 * SharedPreferences工具类
 */

public class BasePreference {

    private Context context;
    private SharedPreferences sp;
    private String SP_FILE_NAME = "CanYanNet";


    protected BasePreference(Context context) {
        this.context = context;
        sp = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * SP中写入String类型value
     *
     * @param key   键
     * @param value 值
     */
    public void putString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    /**
     * SP中读取String
     *
     * @param key 键
     */
    public String getString(String key) {
        return sp.getString(key, "");
    }


    public void putInt(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public void putLong(String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public long getLong(String key) {
        return sp.getLong(key, 0);
    }

    public void putFloat(String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key) {
        return sp.getFloat(key, -1F);
    }

    public void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public void putStringSet(String key,   Set<String> set) {
        sp.edit().putStringSet(key, set).apply();

    }

    public Set<String> getStringSet(String key) {
        return sp.getStringSet(key,new LinkedHashSet<String>());
    }

    public void remove(String key) {
        sp.edit().remove(key).apply();
    }

    /**
     * 清除指定的信息
     *
     * @param name 键名
     * @param key  若为null 则删除name下所有的键值
     */
    public void clearPreference(String name, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (key != null) {
            editor.remove(key);
        } else {
            editor.clear();
        }
        editor.apply();
    }
}
```

#### 具体字段的存储取值工具类
```java
public class PreferenceUtils extends BasePreference {

    /**
     * 需要增加key就在这里新建
     */
    //用户名的key
    private static final String USER_NAME = "user_name";
    //userId
    private static final String USER_ID = "user_id";


    private PreferenceUtils(Context context) {
        super(context);
    }

    /**
     * 通过Application来获取Context对象，所以在获取preferenceUtils时不需要传入Context。
     *
     * @return
     */
    public synchronized static PreferenceUtils getInstance() {
        if (null == preferenceUtils) {
            preferenceUtils = new PreferenceUtils(App.getInstance());
        }
        return preferenceUtils;
    }

    //userName
    public void setUserName(String name) {
        putString(USER_NAME, name);
    }

    public String getUserName() {
        return getString(USER_NAME);
    }

    //userId
    public void setUserId(String userId) {
        putString(USER_ID, userId);
    }

    public String getUserId() {
        return getString(USER_ID);
    }
}
```