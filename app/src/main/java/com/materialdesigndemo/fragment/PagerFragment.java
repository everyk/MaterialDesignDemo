package com.materialdesigndemo.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.materialdesigndemo.activity.ItemActivity;
import com.materialdesigndemo.activity.R;


/**
 * Created by KHQ on 2017/4/14.
 */

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

class ItemAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private int[] mChild;



    public ItemAdapter(Context context, int[] child) {
        this.mContext = context;
        this.mChild = child;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_adapter, parent, false);


        return new ItemHolder(inflate);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        Glide.with(mContext).load(mChild[position]).into(((ItemHolder) holder).ivPicture);


        ((ItemHolder) holder).ivPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ItemActivity.class);

                intent.putExtra("picture", mChild[position]);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mChild.length;
    }

    private class ItemHolder extends RecyclerView.ViewHolder {


        private final ImageView ivPicture;

        public ItemHolder(View inflate) {
            super(inflate);
            ivPicture = (ImageView) inflate.findViewById(R.id.iv_picture);

        }
    }
}