package com.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.materialdesigndemo.adapter.MyFragmentPageAdapter;


public class MainActivity extends AppCompatActivity {


    private Toolbar tbMain;
    private TabLayout tabLayout;
    private ViewPager vpMain;
    private NavigationView navView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = (DrawerLayout) findViewById(R.id.drawer);

        tbMain = (Toolbar) findViewById(R.id.tb_main);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        navView = (NavigationView) findViewById(R.id.nav_view);


        setSupportActionBar(tbMain);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, tbMain, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        tbMain.setOnMenuItemClickListener(onMenuItemClick);

        navView.setCheckedItem(R.id.item_1);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                drawer.closeDrawers();

                return false;

            }
        });


        MyFragmentPageAdapter myFragmentPageAdapter = new MyFragmentPageAdapter(getSupportFragmentManager(), this);
         vpMain.setAdapter(myFragmentPageAdapter);
        tabLayout.setupWithViewPager(vpMain);

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


}
