package com.zoyo.data.navigation;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.zoyo.data.R;

import static androidx.navigation.Navigation.findNavController;

/**
 * @Description: Android原生控件Navigation的使用, 用于管理Fragment的跳转
 * 1./res/navigation/navigation.xml
 * Fragment之间设置关联
 * 2. <fragment
 * android:id="@+id/fragment_nav_host"
 * android:name="androidx.navigation.fragment.NavHostFragment"/>
 * Fragment的切换功能托管给NavHostFragment
 * 3.相关API
 * 3.1复写onSupportNavigateUp()方法,将返回功能托管给Navigation
 * @Override public boolean onSupportNavigateUp() {
 * return findNavController(this, R.id.fragment_nav_host).navigateUp();
 * }
 * 3.2 NavigationUI.setupWithNavController(View view, NavController controller);
 * 3.3  Navigation.findNavController(v).navigate(R.id.action_oneFragment_to_twoFragment);
 * R.id.action_oneFragment_to_twoFragment :actionId 跳转
 * Navigation.findNavController(v).navigateUp(); 返回上一层
 * @CreateDate: 2019/10/28 10:15
 */
public class NavHostActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) supportFragmentManager.findFragmentById(R.id.fragment_nav_host);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        //设置ToolBar,DrawerLayout的关联
        setupActionBar(navController);
        //设置DrawerLayout里面NavigationView的关联
        setupNavigationMenu(navController);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        // 当目的地发生改变时调用
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                int id = destination.getId();
                if (id == R.id.oneFragment || id == R.id.twoFragment || id == R.id.threeFragment) {
                    bottomNavigationView.setVisibility(View.VISIBLE);
                } else {
                    bottomNavigationView.setVisibility(View.INVISIBLE);
                }
            }
        });
        setupBottomNavMenu(navController);
    }

    /**
     * DrawerLayout内部NavigationView
     *
     * @param navController
     */
    private void setupNavigationMenu(NavController navController) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    /**
     * Toolbar DrawerLayout关联
     *
     * @param navController
     */
    private void setupActionBar(NavController navController) {
        //设置ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        NavGraph graph = navController.getGraph();
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(graph).build();
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder().setDrawerLayout(drawerLayout).build();

        NavigationUI.setupWithNavController(toolbar, navController, drawerLayout);
    }

    /**
     * 底部导航栏
     * BottomNavigationView属性app:menu="@menu/nav_bottom_menu"
     * 关联的menu中item的Id就是目的地Fragment的Id(nav_graph_main.xml中设置)
     * 源码中navController.navigate(item.getItemId(), null, options);
     * 其他 NavigationUI.setupWithNavController(View view, NavController controller);同理
     *
     * @param controller
     */
    private void setupBottomNavMenu(NavController controller) {
        NavigationUI.setupWithNavController(bottomNavigationView, controller);
    }


    /**
     * 将back键点击事件委托出去,如果当前Fragment不是在最顶端,则返回上一个Fragment
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return findNavController(this, R.id.fragment_nav_host).navigateUp();
    }
}
