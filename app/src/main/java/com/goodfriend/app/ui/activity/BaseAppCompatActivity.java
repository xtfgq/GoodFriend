package com.goodfriend.app.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.goodfriend.app.common.CustomApplcation;
import com.goodfriend.R;
import com.goodfriend.app.utils.SystemStatusManager;

import butterknife.ButterKnife;

/**
 * Created by guoqiang on 2017/6/20.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.toolbar_title);
        doSetFitsSystemWindows();
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract int getColorId();

    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CustomApplcation.getInstance().finishSingleActivity(this);
    }

    private void doBeforeSetcontentView() {
        CustomApplcation.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTranslucentStatus();

    }

    public void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemStatusManager tintManager = new SystemStatusManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(getColorId());// 状态栏无背景
    }

    public void doSetFitsSystemWindows() {
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
    }

    public void setTitle(String title) {
        if (mToolBar != null) {
            mTitle.setText(title);
        }
    }

}
