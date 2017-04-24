package com.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.BoringLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


public class ItemActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private int picture;
    private ImageView ivPicItem;
    private FloatingActionButton floatBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        picture = getIntent().getIntExtra("picture", 1);
        initView();
        initData();


    }

    private void initData() {
        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle("标题");
        Glide.with(ItemActivity.this).load(picture).into(ivPicItem);

        floatBt.setOnClickListener(this);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_item);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ivPicItem = (ImageView) findViewById(R.id.iv_pic_item);

        floatBt = (FloatingActionButton) findViewById(R.id.float_bt);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.float_bt:

                Snackbar.make(view, "确定点击？", Snackbar.LENGTH_SHORT).setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(ItemActivity.this, "floatOnClick", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;


        }


    }


}
