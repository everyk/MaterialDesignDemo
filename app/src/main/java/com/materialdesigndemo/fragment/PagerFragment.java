package com.materialdesigndemo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.materialdesigndemo.activity.R;


/**
 * Created by KHQ on 2017/4/14.
 */

public class PagerFragment extends Fragment {


    private int mPage;


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


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fg_context, container, false);
      //  TextView tvContext = (TextView) view.findViewById(R.id.tv_context);
        RecyclerView rvFg = (RecyclerView) view.findViewById(R.id.rv_fg);


     //   tvContext.setText("《《第" + mPage + "页》》");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvFg.setLayoutManager(linearLayoutManager);
        ItemAdapter itemAdapter = new ItemAdapter(getContext());
        rvFg.setAdapter(itemAdapter);

        return view;

    }
}

class ItemAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public ItemAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_adapter, null);


        return new ItemHolder(inflate);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((ItemHolder) holder).tvItem.setText("条目内容" + position);
        ((ItemHolder) holder).tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        private final TextView tvItem;

        public ItemHolder(View inflate) {
            super(inflate);
            tvItem = (TextView) inflate.findViewById(R.id.tv_item);
        }
    }
}