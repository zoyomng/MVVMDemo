#Android gson工具
##json字符串转换成JavaBean / 资源文件通过流解析转换


```Java
/**
 * ---日期----------维护人---------
 * 2017/11/29     zuoyouming
 * 将json转为javabean
 */
public class GsonUtils {

    public static <T> T json2Bean(String result, Class<T> clz) {

        try {
            if (TextUtils.isEmpty(result)) {
                ToastUtil.shortShow("数据解析错误");
                return null;
            }

            Gson gson = new Gson();
            T t = gson.fromJson(result, clz);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
           ToastUtil.shortShow("数据解析错误");
            return null;
        }

    }

    //资源文件的解析
    public static <T> T json2Bean(Reader result, Class<T> clz) {
        if (result == null) {
            ToastUtil.shortShow("数据解析错误");
            return null;
        }

        Gson gson = new Gson();
        T t = gson.fromJson(result, clz);
        return t;
    }


    public static String toJson(@NonNull Object obj) {

        try {
            Gson gson = new Gson();
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
```


解析assets目录下资源文件
> * area_city.json
```Java
{
  "citys": [
    {
      "area_id": 403,
      "area_name": "阿坝藏族羌族自治州",
      "level": 2,
      "area_level": 2,
      "parent_id": 23,
      "list": 0
    },
    {
      "area_id": 482,
      "area_name": "阿克苏地区",
      "level": 2,
      "area_level": 2,
      "parent_id": 31,
      "list": 0
    },
    {
      "area_id": 490,
      "area_name": "阿拉尔市",
      "level": 2,
      "area_level": 2,
      "parent_id": 31,
      "list": 0
    },
    ...
  ]
}

```

使用方式:
```Java

try {
    InputStream cityJson = App.getInstance().getAssets().open("area_city.json");
    InputStreamReader inputStreamReader = new InputStreamReader(cityJson);
    citysBean = GsonUtils.json2Bean(inputStreamReader, CitysBean.class);

} catch (IOException e) {
    e.printStackTrace();
}
```
