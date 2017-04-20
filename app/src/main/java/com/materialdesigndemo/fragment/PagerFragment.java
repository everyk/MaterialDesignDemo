package com.materialdesigndemo.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.materialdesigndemo.activity.ItemActivity;
import com.materialdesigndemo.activity.R;
import com.materialdesigndemo.utils.MDUtils;


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

        if (MDUtils.CHOOSE == MDUtils.CHILD) {
            mData = new int[]{R.mipmap.xh01, R.mipmap.xh02, R.mipmap.xh03, R.mipmap.xh04, R.mipmap.xh05, R.mipmap.xh06};
        } else if (MDUtils.CHOOSE == MDUtils.SCENERY) {
            mData = new int[]{R.mipmap.fj01, R.mipmap.fj02, R.mipmap.fj03, R.mipmap.fj04, R.mipmap.fj05, R.mipmap.fj06};
        } else if (MDUtils.CHOOSE == MDUtils.PET) {
            mData = new int[]{R.mipmap.gg01, R.mipmap.gg02, R.mipmap.gg03, R.mipmap.gg04, R.mipmap.gg05, R.mipmap.gg06};
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fg_context, container, false);

        RecyclerView rvFg = (RecyclerView) view.findViewById(R.id.rv_fg);

        if (mPage == 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

            rvFg.setLayoutManager(linearLayoutManager);
        } else if (mPage == 1) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvFg.setLayoutManager(linearLayoutManager);
        } else if (mPage == 2) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

            rvFg.setLayoutManager(gridLayoutManager);


        } else if (mPage == 3) {

            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            rvFg.setLayoutManager(staggeredGridLayoutManager);
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

                intent.putExtra("picture",mChild[position]);
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