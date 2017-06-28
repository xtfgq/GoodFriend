package com.goodfriend.app.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.goodfriend.app.ui.activity.BaseToolBarActvity;
import com.goodfriend.app.ui.presenter.BasePresenter;
import com.goodfriend.app.ui.view.IBaseFragmentView;

import butterknife.ButterKnife;

/**
 * Created by guoqiang on 2017/6/27.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements
        IBaseFragmentView {
    public T getmPresenter() {
        return mPresenter;
    }

    public void setmPresenter(T mPresenter) {
        this.mPresenter = mPresenter;
    }

    protected T mPresenter;
    protected Context mContext;
    protected Bundle mBundle;
    private View mContentView;
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }
        super.onSaveInstanceState(outState);
    }
    /**
     * 绑定activity
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mPresenter = initPresenter();
        if(mPresenter!=null)
        mPresenter.onAttch(this);
    }
    /**
     * 运行在onAttach之后
     * 可以接受别人传递过来的参数,实例化对象.
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取bundle,并保存起来
        if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
        } else {
            mBundle = getArguments() == null ? new Bundle() : getArguments();
        }
    }
    /**
     * 运行在onCreate之后
     * 生成view视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = initView(inflater, savedInstanceState);
        ButterKnife.bind(this, mContentView);
        return mContentView;
    }
    /**
     * 运行在onCreateView之后
     * 加载数据
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建presenter
        if (mPresenter != null) {
            mPresenter.onCreate();
            //加载完成再刷新视图
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
                @Override
                public boolean queueIdle() {
                    mPresenter.loadData();
                    return false;
                }
            });
        }
        initListener();
    }

    /**
     * 初始化Fragment应有的视图
     *
     * @return
     */
    public abstract View initView(LayoutInflater inflater, @Nullable Bundle savedInstanceState);
    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract T initPresenter();

    /**
     * 初始化监听器
     */
    public void initListener() {

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mPresenter == null) {
            return;
        }
        if (isVisibleToUser) {
            initDo();
            //相当于Fragment的onResume
            mPresenter.loadData();
        }
    }
    @Override
    public void onDestroyView() {
        if (mPresenter == null)
        mPresenter.onDestroy();
        super.onDestroyView();
    }
    protected abstract void initDo();

    @Override
    public void onDetach() {
        if (mPresenter == null)
        mPresenter.onDetach();
        super.onDetach();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mPresenter == null)
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public Bundle getBundle() {
        return mBundle;
    }

    @Override
    public BaseFragment getFragment() {
        return this;
    }
    @Override
    public Context getContext() {
        return mContext;
    }
    /**
     * @param tofragment 跳转的fragment
     * @param tag        fragment的标签
     */
    @Override
    public void startFragment(Fragment tofragment, String tag) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.hide(this).add(android.R.id.content, tofragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }
    /**
     * 跳转fragment
     *
     * @param tofragment
     */
    @Override
    public void startFragment(Fragment tofragment) {
        startFragment(tofragment, null);
    }
    /**
     * 类似Activity的OnBackgress
     * fragment进行回退
     */
    @Override
    public void onBack() {
        getFragmentManager().popBackStackImmediate();
    }
    @Override
    public ActionBar getSupportActionBar() {
        return ((BaseToolBarActvity) mContext).getSupportActionBar();
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        ((BaseToolBarActvity) mContext).setSupportActionBar(toolbar);
    }
    @Override
    public Window getWindow() {
        return getActivity() == null ? ((BaseToolBarActvity) mContext).getWindow() :
                getActivity().getWindow();
    }
}
