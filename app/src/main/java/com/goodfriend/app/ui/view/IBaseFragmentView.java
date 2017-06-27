package com.goodfriend.app.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.goodfriend.app.ui.fragment.BaseFragment;

/**
 * Created by guoqiang on 2017/6/27.
 */

public interface IBaseFragmentView extends BaseView {
    Bundle getBundle();

    FragmentManager getFragmentManager();
    BaseFragment getFragment();
    /**
     * 回退
     */
    void onBack();

    /**
     * 获取Acitivity
     *
     * @return
     */
    Activity getActivity();

    /**
     * Frgament跳转.
     *
     * @param tofragment
     */
    void startFragment(Fragment tofragment);

    /**
     * Frgament跳转.
     *
     * @param tofragment
     */
    void startFragment(Fragment tofragment, String tag);
    Window getWindow();
    ActionBar getSupportActionBar();

    void setSupportActionBar(@Nullable Toolbar toolbar);

}
