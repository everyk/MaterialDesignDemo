package com.materialdesigndemo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        TextView tvContext = (TextView) view.findViewById(R.id.tv_context);
        tvContext.setText("《《第" + mPage + "页》》");
        return view;

    }
}
