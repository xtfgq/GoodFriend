package com.goodfriend.app.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.goodfriend.R;
import com.goodfriend.app.ui.presenter.BasePresenter;


public class OtherActivity extends BaseToolBarActvity<BasePresenter> {



    @Override
    public int getContentViewID() {
        return R.layout.otheractivity;
    }

    @Override
    public void initView() {

    }

    @Override
    protected int getColorId() {
        return R.color.red;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
