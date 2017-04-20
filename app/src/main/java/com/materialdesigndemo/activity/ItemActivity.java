package com.materialdesigndemo.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;



public class ItemActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private int picture;
    private ImageView ivPicItem;

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
        if (supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle("测试内容");
        Glide.with(ItemActivity.this).load(picture).into(ivPicItem);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_item);
        collapsingToolbar = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ivPicItem = (ImageView) findViewById(R.id.iv_pic_item);


    }
}
