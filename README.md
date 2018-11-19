


#### Material Design风格，用一个小Demo使用下部分控件，先看下最终效果：

![md.gif](http://upload-images.jianshu.io/upload_images/2470723-184a9bb065885e60.gif?imageMogr2/auto-orient/strip)

#### 话不多说，一步步来实现
- Toolbar
Android开发中，为了使用自定义的标题栏，会把系统原生的Actionbar隐藏掉，因为每个Activity最顶部的标题栏就是一个Actionbar，由于设计的原因，Actionbar只能位于Activity的顶部，就不能实现MaterialDesign的一些效果，那么Toolbar登场了。
  要使用Toolbar,需要在android:theme指定一个AppTheme主题
  打开AndroidManifest.xml
```
<application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
```
 其中android:theme="@style/AppTheme"就是需要指定的AppTheme主题，好，点击进去，来到res/values/styles.xml
```
<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
</resources>
```
  这里定义了一个AppTheme的主题，指定的parent主题是Theme.AppCompat.Light.DarkActionBar，
       要使用Toolbar需要指定一个不带ActionBar的主题，通常有两种主题可选：
       Theme.AppCompat.NoActionBar    表示深色主题，即界面的主体颜色为深色，陪衬颜色为淡色；
       Theme.appCompat.Light.NoactionBar表示淡色主题，即界面的主题颜色为淡色陪衬颜色为深色；
        这里选择淡色主题：
```
<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
</resources>
```
   修改activity_main.xml
```
 <LinearLayout  xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <android.support.v7.widget.Toolbar
            android:id="@+id/tb_main"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
            app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar" />
    
    </LinearLayout>
```

  1.  这里使用了xmlns:app 指定的一个新的命名空间，是因为Maternal Design是在Android 5.0才出现，所以在小于5.0的系统Material属性不存在，为了能够兼容老系统使用app:attribute

 2.  @android/ ?android 的区别：@android引用的是系统的资源，?android引用的是本应用theme内的资源
    修改 MainActivity 
```
public class MainActivity extends AppCompatActivity {
    private Toolbar tbMain;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbMain = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(tbMain);
```
    效果图：

![toolbar.png](http://upload-images.jianshu.io/upload_images/2470723-47f03186c167054e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


   ToolBar常用功能：
    先看效果


![title.gif](http://upload-images.jianshu.io/upload_images/2470723-6d232854b5ae7d6a.gif?imageMogr2/auto-orient/strip)
实现如下：
```
  <activity
            android:name=".MainActivity"
            android:label="Demo">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```
  android:label=" " 指定显示在ToolBar中的文字内容，如果没有指定默认显示应用名称
           res/下创建menu文件夹/创建menu_main.xml 代码如下：
  ```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context=".MainActivity"
    >
    <item android:id="@+id/action_edit"
        android:title="edit"
        android:orderInCategory="80"
        android:icon="@mipmap/ab_edit"
        app:showAsAction="ifRoom" />
    <item android:id="@+id/action_share"
        android:title="edit"
        android:orderInCategory="90"
        android:icon="@mipmap/ab_share"
        app:showAsAction="ifRoom" />
    <item android:id="@+id/action_settings"
        android:title="settings"
        android:orderInCategory="100"
        app:showAsAction="never"/>
</menu>
```
```
public class MainActivity extends AppCompatActivity {
    private Toolbar tbMain;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tbMain = (Toolbar) findViewById(R.id.tb_main);
         setSupportActionBar(tbMain);
        tbMain.setOnMenuItemClickListener(onMenuItemClick);
       
    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            String msg = "";
            switch (item.getItemId()) {
                case R.id.action_edit:
                    msg += "Click edit";
                    break;
                case R.id.action_share:
                    msg += "Click share";
                    break;
                case R.id.action_settings:
                    msg += "Click setting";
                    break;
            }
            if (!msg.equals("")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };
             @Override
            public boolean onCreateOptionsMenu(Menu menu) {
 
            getMenuInflater().inflate(R.menu.menu_main, menu);
 
            return true;
 
        }

}
```
现在的标题默认在左边，有时候项目需要在中间，这个也比较容易实现
首先 toolBar.setTitle(" ");
```
 <android.support.v7.widget.Toolbar
            android:id="@+id/tb_main"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
            app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="居中标题"
                android:textColor="@color/colorWhite" />
        </android.support.v7.widget.Toolbar>
```

![titleCenter.png](http://upload-images.jianshu.io/upload_images/2470723-85233ff51b218b19.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


-  DrawerLayout(滑动菜单)
我们先看效果

![dawerlayout.gif](http://upload-images.jianshu.io/upload_images/2470723-d60d7833237fd422.gif?imageMogr2/auto-orient/strip)
只要修改布局就可以实现了；
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <android.support.v7.widget.Toolbar
            android:id="@+id/tb_main"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
            app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar" />
      </LinearLayout>
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:backgrround="?attr/colorPrimary"
            android:text="侧面菜单"
         />
</android.support.v4.widget.DrawerLayout>
```
注意android:layout_gravity的设定
我们往侧面菜单中添加布局，这里用到一个新的控件 NavigationView，老规矩，先看实现效果

![drawer.gif](http://upload-images.jianshu.io/upload_images/2470723-115741430affada7.gif?imageMogr2/auto-orient/strip)
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <android.support.v7.widget.Toolbar
            android:id="@+id/tb_main"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
            app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar" />
            
    </LinearLayout>
    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_header"
        app:menu="@menu/nav_menu" />
</android.support.v4.widget.DrawerLayout>
```
只需要修改布局，设置app:headerLayout    app:menu 属性即可

#### 接下来我们实现一个左右滑动的效果
- TabLayout+ViewPger来实现
我们还是先来看效果

![viewpager.gif](http://upload-images.jianshu.io/upload_images/2470723-11c53b24a9d5199a.gif?imageMogr2/auto-orient/strip)
我们分析下：上面的标签是TabLayout，下面的内容变化是通过ViewPager+Fragment实现的
1. 修改activity_main.xml
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">
        <android.support.v7.widget.Toolbar
            android:id="@+id/tb_main"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
            app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar" />
        <android.support.design.widget.TabLayout
            android:id="@+id/tablayout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:tabBackground="@color/colorPrimary"
          />
        <android.support.v4.view.ViewPager
            android:id="@+id/vp_main"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@color/colorWhite" />
    </LinearLayout>
    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_header"
        app:menu="@menu/nav_menu" />
</android.support.v4.widget.DrawerLayout>
```
2. 切换ViewPager，显示不同的Fragment，这里先用一个相同的布局

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        />
</LinearLayout>
```
3.创建Fragment

```
public class PageFragment extends Fragment {
 
    private int mPage;
    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt("page");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,container,false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("第"+mPage+"页");
        return view;
    }
}
```
4.adapter
```
public class ViewPageAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3", "Tab4", "Tab5"};
    public ViewPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }
    @Override
    public Fragment getItem(int position) {
        return PagerFragment.newInstance(position + 1);
    }
    @Override
    public int getCount() {
        return 5;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
```
#### 到这里就实现上面的效果啦~~~

这里tab注意两个小点

1. tab的颜色修改
```
  <android.support.design.widget.TabLayout
            android:id="@+id/tablayout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:tabBackground="@color/colorPrimary"
            app:tabIndicatorColor="@color/ColorYellow"
            app:tabSelectedTextColor="@color/ColorYellow"
            app:tabTextColor="@color/colorWhite" />
```


![tabcolor.gif](http://upload-images.jianshu.io/upload_images/2470723-98ff1e511a6967a3.gif?imageMogr2/auto-orient/strip)

2. tab内容的显示和tab的长度

```
/*
        * TabGravity有两种效果，TabLayout.GRAVITY_CENTER和TabLayout.GRAVITY_FILL
        * 前者是居中，后者是尽可能的填充
        * */
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        /*
        * TabMode也有两种效果，TabLayout.MODE_SCROLLABLE和TabLayout.MODE_FIXED
        * 前者是可滚动的tabs,后者是固定的tabs并同时显示所以的tabs
        * */
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
```
我们看下前者和后者的实现效果

![tab_scrollable.gif](http://upload-images.jianshu.io/upload_images/2470723-b47ada481c42a375.gif?imageMogr2/auto-orient/strip)

![tab_fixed.gif](http://upload-images.jianshu.io/upload_images/2470723-af013d8f829fea4b.gif?imageMogr2/auto-orient/strip)
######革命尚未成功，同志仍需努力，好，我们接着来
我们给他添加点内容，这里我只显示了图片
- RecyclerView+CardView添加内容
![cardview.gif](http://upload-images.jianshu.io/upload_images/2470723-6b927a1a1f06eb4b.gif?imageMogr2/auto-orient/strip)
我们来分析下，其实就是不同的frangment显示了不同的布局
1.首先修改Fragment布局
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv_fg"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
       />
</LinearLayout>
```
2.在修改item的布局
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <android.support.v7.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="15dp"
        android:elevation="6dp"
        app:cardCornerRadius="6dp">
        <ImageView
            android:id="@+id/iv_picture"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </android.support.v7.widget.CardView>
</LinearLayout>
```
 CardView有两个属性值得注意：app:cardCornerRadius属性指定圆角的弧度
app:elevation属性指定卡片的高度
3.RecyclerView设置显示
RecyclerView可以实现ListView、GridView的显示效果，但是需要通过layoutManager设置
```
public class PagerFragment extends Fragment {
    private int mPage;
    private int[] mData;
    public static PagerFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt("page", page);
        PagerFragment pagerFragment = new PagerFragment();
        pagerFragment.setArguments(args);
        return pagerFragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt("page");
        mData = new int[]{R.mipmap.n1, R.mipmap.n2, R.mipmap.n3, R.mipmap.n4, R.mipmap.n5, R.mipmap.n6,
                R.mipmap.s1, R.mipmap.s2, R.mipmap.s3, R.mipmap.s4, R.mipmap.s5, R.mipmap.s6};
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content, container, false);
        RecyclerView rvFg = (RecyclerView) view.findViewById(R.id.rv_fg);
        if (mPage == 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            rvFg.setLayoutManager(linearLayoutManager);
        } else if (mPage == 1) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            rvFg.setLayoutManager(staggeredGridLayoutManager);
        } else if (mPage == 2) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            rvFg.setLayoutManager(gridLayoutManager);
        } else if (mPage == 3) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            rvFg.setLayoutManager(linearLayoutManager);
        } else if (mPage == 4) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvFg.setLayoutManager(linearLayoutManager);
        }
        ItemAdapter itemAdapter = new ItemAdapter(getContext(), mData);
        rvFg.setAdapter(itemAdapter);
        return view;
    }
}
```
mData是图片数组，图片是从网上找的，放在mipmap里
mPage是第几个Fragment，这里不同的Fragment设置显示不同的样式
然后在 ItemAdapter中通过Glide加载图片

#### 到这里已经完成了80%，行百里者半九十，保持严谨的态度，我们能让它更完美

- Toolbar滑动隐藏与显示和CollapsingToolbarLayout(可折叠标题栏)

![toolbar.gif](http://upload-images.jianshu.io/upload_images/2470723-265c590d2b6b4923.gif?imageMogr2/auto-orient/strip)
这里就是让ToolBar上滑的时候隐藏，下拉又显示出来
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true">
    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">
        <android.support.v7.widget.Toolbar
            android:id="@+id/tb_main"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:layout_scrollFlags="scroll|enterAlways|snap"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
            app:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">
        </android.support.v7.widget.Toolbar>
        <android.support.design.widget.TabLayout
            android:id="@+id/tablayout"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:tabBackground="@color/colorPrimary"
            app:tabGravity="fill"
            app:tabIndicatorColor="@color/ColorYellow"
            app:tabSelectedTextColor="@color/ColorYellow"
            app:tabTextColor="@color/colorWhite" />
    </android.support.design.widget.AppBarLayout>
    <android.support.v4.view.ViewPager
        android:id="@+id/vp_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/colorWhite"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />
</android.support.design.widget.CoordinatorLayout>
```
我们只是修改了布局
CoordinatorLayout可以监听所有子控件的的各种事件，同时也是一个加强版的FragmentLayout
AppBarLayout是一个垂直方向的Linearlayout
app:layout_behavior用来处理可滚动View与AppbarLayout的联动
当AppBar接收到View滑动的时候，它的子控件就可以指定如何处理这些事件，通过 app:layout_scrollFlags="scroll|enterAlways|snap"指定实现，其中scroll表示当View上滑的时候，ToolBar会一起向上滑动并隐藏，enterAlways表示当View下滑的时候，ToolBar会一起向下滑动并重新显示，snap表示当ToolBar还没有完全显示或隐藏的时候，根据当前的滑动距离，自动选择显示或隐藏。


![CollapsingToolbar.gif](http://upload-images.jianshu.io/upload_images/2470723-11695ea4c99ec7b6.gif?imageMogr2/auto-orient/strip)

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:context="com.materialdesigndemo.activity.ItemActivity">
    <android.support.design.widget.AppBarLayout
        android:id="@+id/appBar"
        android:layout_width="match_parent"
        android:layout_height="250dp"
        android:fitsSystemWindows="true">
        <android.support.design.widget.CollapsingToolbarLayout
            android:id="@+id/collapsing_toolbar"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fitsSystemWindows="true"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
            app:contentScrim="?attr/colorPrimary"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">
            <ImageView
                android:id="@+id/iv_pic_item"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:fitsSystemWindows="true"
                android:scaleType="centerCrop"
                app:layout_collapseMode="parallax" />
            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar_item"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:layout_collapseMode="pin"></android.support.v7.widget.Toolbar>
        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>
    <android.support.v4.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
            <TextView
                android:id="@+id/tv_message"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="10dp"
                android:text="Content  Content  Content  Content  Content  Content" />
        </LinearLayout>
    </android.support.v4.widget.NestedScrollView>
    <android.support.design.widget.FloatingActionButton
        android:id="@+id/float_bt"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="15dp"
        android:scaleType="centerCrop"
        app:layout_anchor="@+id/appBar"
        app:layout_anchorGravity="bottom|end" />
</android.support.design.widget.CoordinatorLayout>
```
还需要把状态栏设置成透明，res下新建values-21，在创建styles.xml
```
<resources>
    <style name="itemTheme" parent="AppTheme">
        <item name="android:statusBarColor">@android:color/transparent</item>
    </style>
</resources>
```

由于Android5.0之前的系统不能识别itemTheme,所以需要对values/styles.xml进行修改
```
<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>
    <style name="itemTheme" parent="AppTheme"></style>
</resources>
```
最后让ItemAcivity使用这个主题，修改AndroidManifest.xml
```
 <activity
            android:name=".ItemActivity"
            android:theme="@style/itemTheme" />
```


它俩有实现起来什么区别呢
很明显相同点是CoordinatorLayout作为父布局，都有子控件AppBarLayout和子控件可滚动View（通过app:layout_behavior指定布局行为）
不同点是AppBarLayout里的子控件不同，标题隐藏或显示是把ToolBar作为AppBarLayout的子控件，通过app:layout_scrollFlags指定实现；
而可折叠标题栏是把CollapsingToolbarLayout作为AppBarLayout的子控件，也是通过app:layout_scrollFlags指定实现，但是CollapsingToolbarLayout还有两个子控件ImageView和ToolBar组成高级的标题栏；
到这里基本功能已经实现了，在来一个控件作为结束
- Snackbar

![snackbar.gif](http://upload-images.jianshu.io/upload_images/2470723-60ee1dd2f20c5383.gif?imageMogr2/auto-orient/strip)
```
 Snackbar.make(view, "确定点击？", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ItemActivity.this, "floatOnClick", Toast.LENGTH_SHORT).show();
                    }
                }).show();
```
用法比较简单，和Toast差不多，个人觉得是加了一个再次确认的操作防止误操作。
好了，就到这里了，不足之请留言指正。

github地址：[https://github.com/everyk/MaterialDesignDemo.git](https://github.com/everyk/MaterialDesignDemo.git)
