package com.goodfriend.app.ui.activity;

import android.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.goodfriend.R;
import com.goodfriend.app.ui.fragment.IndexPersonFragmnet;
import com.goodfriend.app.ui.fragment.OneFragment;
import com.goodfriend.app.ui.presenter.BasePresenter;

import java.util.ArrayList;

/**
 * Created by guoqiang on 2017/6/27.
 */

public class MainTabActivity extends BaseToolBarActvity {

    private IndexPersonFragmnet mIndexPersonFragmnet;
    private OneFragment mOneFragment;
    @Override
    protected int getColorId() {
        return R.color.red;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_tab;
    }

    @Override
    public void initView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        if(mIndexPersonFragmnet == null){
//            mIndexPersonFragmnet = IndexPersonFragmnet.newInstance();
//            fragmentTransaction.add(R.id.fragmentContent,mIndexPersonFragmnet);
//        }
//        fragmentTransaction.show(mIndexPersonFragmnet);
        if(mOneFragment == null){
            mOneFragment = OneFragment.newInstance();
            fragmentTransaction.add(R.id.fragmentContent,mOneFragment);
        }
        fragmentTransaction.show(mOneFragment);
        fragmentTransaction.commit();
    }

}
