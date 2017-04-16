package com.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.materialdesigndemo.adapter.MyFragmentPageAdapter;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    private Toolbar tbMain;
    private TabLayout tabLayout;
    private ViewPager vpMain;
    private NavigationView navView;
    private DrawerLayout drawer;
    private FloatingActionButton fabMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();


    }

    private void initData() {

        // tabLayout.setupWithViewPager(vpMain);
        setSupportActionBar(tbMain);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, tbMain, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        tbMain.setOnMenuItemClickListener(onMenuItemClick);

        navView.setCheckedItem(R.id.item_1);
        navView.setNavigationItemSelectedListener(this);
        fabMain.setOnClickListener(this);


        MyFragmentPageAdapter myFragmentPageAdapter = new MyFragmentPageAdapter(getSupportFragmentManager(), this);
        vpMain.setAdapter(myFragmentPageAdapter);
        tabLayout.setupWithViewPager(vpMain);


    }

    private void initView() {
        drawer = (DrawerLayout) findViewById(R.id.drawer);

        tbMain = (Toolbar) findViewById(R.id.tb_main);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        navView = (NavigationView) findViewById(R.id.nav_view);
        fabMain = (FloatingActionButton) findViewById(R.id.fab_main);


    }

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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        drawer.closeDrawers();

        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_main:

                Snackbar.make(view, "取消", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "floatonclick", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;


        }
    }
}
