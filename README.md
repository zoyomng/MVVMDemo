# MVVMDemo
## core(MVVM的核心部分)
### 基类
#### BaseActivity
#### BaseFragment
#### BaseViewModel
#### BaseFragment
#### BaseAdapter

* adapter的基本写法
``` 
/**
 * adapter的基本写法
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemViewHolder> {
    private List<ItemBean> list;

    public MainAdapter(List<ItemBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMainBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_main, viewGroup, false);
        ItemViewHolder holder = new ItemViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int i) {
        viewHolder.getBinding().setModel(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {


        private ItemMainBinding binding;

        public ItemViewHolder(@NonNull ItemMainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ItemMainBinding getBinding() {
            return this.binding;
        }

        public void setBinding(ItemMainBinding binding) {
            this.binding = binding;
        }

    }
```
* adapter的封装
```
/**
 * adapter的封装
 */
public class BaseAdapter<E> extends RecyclerView.Adapter<ItemViewHolder> {

    public List<E> data;
    public int defaultLayoutRes;
    public int variableId;
    public OnItemClickListener onItemClickListener;

    /**
     * item布局不同,item中变量不同,数据不同
     */
    public BaseAdapter(@LayoutRes int layoutRes, int variableId, List<E> data) {
        this.defaultLayoutRes = layoutRes;
        this.variableId = variableId;
        this.data = data;
    }

    /**
     * @param viewGroup
     * @param layoutRes (viewType)  原来应该返回的是viewType (多种布局中,决定item使用哪一种布局的参数),现在改成条目布局的资源(R.layout.item_***)
     * @return
     */
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int layoutRes) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), layoutRes, viewGroup, false);
        return new ItemViewHolder(binding);
    }

    /**
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, int position) {
        viewHolder.getDataBinding().setVariable(variableId, data.get(position));

        //条目点击事件
        addItemClickListener(viewHolder, data.get(position), position);

        viewHolder.getDataBinding().executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    /**
     * 多种类型布局
     *
     * @param position
     * @return 直接返回条目布局
     */
    @Override
    public int getItemViewType(int position) {
        return getItemLayoutRes(data.get(position));
    }

    @LayoutRes
    private int getItemLayoutRes(E model) {
        return defaultLayoutRes;
    }


    /**
     * 其实就是将在onBindViewHolder()中初始化子控件的点击事件的操作交给外部处理
     *
     * @param viewHolder
     * @param e
     * @param position
     */
    public void addItemClickListener(ItemViewHolder viewHolder, E e, int position) {

    }

    /**
     * TODO DataBinding不是数据改变,显示随之改变吗,为什么还要写这些方法??
     *
     * @param newData
     */
    public void onItemDataChangeed(List<E> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    public void onItemRangeChanged(List<E> newData, int positionStart, int itemCount) {
        this.data = newData;
        notifyItemRangeChanged(positionStart, itemCount);
    }

    public void onItemRangeInserted(List<E> newData, int positionStart, int itemCount) {
        this.data = newData;
        notifyItemRangeInserted(positionStart, itemCount);
    }

    public void onItemRangeRemoved(List<E> newData, int positionStart, int itemCount) {
        this.data = newData;
        notifyItemRangeRemoved(positionStart, itemCount);
    }

}
```
```
public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding dataBinding;

    public ItemViewHolder(@NonNull ViewDataBinding dataBinding) {
        super(dataBinding.getRoot());
        this.dataBinding = dataBinding;
    }

    public ViewDataBinding getDataBinding() {
        return dataBinding;
    }

    public void setDataBinding(ViewDataBinding dataBinding) {
        this.dataBinding = dataBinding;
    }
}
```

* adapter的使用
```
public class MainActivity extends BaseActivity<MainViewModel> {

    @ColorInt
    private static final int[] BG_COLORS = {
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079,
            0xfff25f8c, 0xfffb7f77, 0xfffcc02c, 0xff2fcc87,
            0xff3dc2c7, 0xff47b2f8, 0xffb28bdc, 0xff948079
    };


    @Override
    protected int initViewModelId() {
        return BR.viewModel;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }


    @Override
    public void initData() {
        RecyclerView rvMain = ((ActivityMainBinding) dataBinding).rvMain;
        rvMain.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //第一种:基本的写法
        rvMain.setAdapter(new MainAdapter(initItemDatas()));
        //第二种:封装的写法
        BaseAdapter<ItemBean> adapter = new BaseAdapter<ItemBean>(R.layout.item_main, BR.model, initItemDatas()) {
            @Override
            public void addItemClickListener(ItemViewHolder viewHolder, ItemBean itemBean, int position) {
                super.addItemClickListener(viewHolder, itemBean, position);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                    }
                });
                ((ItemMainBinding) (viewHolder.getDataBinding())).ivAvatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "childClick", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        };
        rvMain.setAdapter(adapter);
    }

    private List<ItemBean> initItemDatas() {
        ArrayList<ItemBean> itemBeans = new ArrayList<>();
        itemBeans.add(new ItemBean("DataBinding的使用", BG_COLORS[0]));
        itemBeans.add(new ItemBean("DataBinding的使用", BG_COLORS[0]));
        itemBeans.add(new ItemBean("DataBinding的使用", BG_COLORS[0]));
        return itemBeans;
    }
}
```
#### 使用@BindingAdapter设置Adapter
```
public class ViewAdapter {
    @BindingAdapter("adapter")
    public static <T> void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        if (adapter == null) {
            return;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }
}
```
* XML文件 

```
 <android.support.v7.widget.RecyclerView
            android:id="@+id/rv_main"
            android:layout_width="0dp"
            android:layout_height="0dp"
           ...
            app:adapter="@{viewModel.adapter}" />
```

#### 使用@BindingAdapter创建Adapter(TODO)
```
public class ViewAdapter {
    @BindingAdapter(value = {"itemLayoutRes", "variableId", "data"})
    public static <T> void setAdapter(RecyclerView recyclerView, @LayoutRes int itemLayoutRes, int variableId, List<T> data) {
        if (recyclerView.getAdapter() == null) {
            BaseAdapter<T> tBaseAdapter = new BaseAdapter<>(itemLayoutRes, variableId, data);
            recyclerView.setAdapter(tBaseAdapter);
        }
    }
}
```
* XML文件
```
<android.support.v7.widget.RecyclerView
            android:id="@+id/rv_main"
            android:layout_width="0dp"
            android:layout_height="0dp"
            ...
            app:data="@{viewModel.itemBeans}"
            app:itemLayoutRes="@{@layout/item_main}"
            app:variableId="@{viewModel.getVariableId()}"/>
```
* 定义属性(效果:XML中对应属性的值可以追踪定位,ctrl+鼠标左击)
```
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <declare-styleable name="RecyclerView">
        <attr name="data" format="reference" />
        <attr name="itemLayoutRes" format="reference" />
        <attr name="variableId" format="reference" />
        <attr name="layoutManager" format="reference" />
        
        <attr name="adapter" format="reference" />
    </declare-styleable>

</resources>
```

### common