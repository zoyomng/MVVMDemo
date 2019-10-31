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

    private void setupNavigationMenu(NavController navController) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void setupActionBar(NavController navController) {
        //设置ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        NavGraph graph = navController.getGraph();
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(graph).build();
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder().setDrawerLayout(drawerLayout).build();

        NavigationUI.setupWithNavController(toolbar, navController, drawerLayout);
    }

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
