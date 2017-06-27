package com.goodfriend.app.ui.activity;

import com.goodfriend.R;
import com.goodfriend.app.ui.presenter.BasePresenter;

/**
 * Created by guoqiang on 2017/6/27.
 */

public class MainTabActivity extends BaseToolBarActvity {
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

    }
}
