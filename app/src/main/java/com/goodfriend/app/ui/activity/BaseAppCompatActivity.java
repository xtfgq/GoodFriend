package com.goodfriend.app.ui.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.goodfriend.app.common.CustomApplcation;
import com.goodfriend.app.common.PermissionsResultListener;
import com.goodfriend.app.ui.presenter.BasePresenter;
import com.goodfriend.app.ui.view.BaseView;
import com.goodfriend.app.utils.SystemStatusManager;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by guoqiang on 2017/6/20.
 */
public abstract class BaseAppCompatActivity<T extends BasePresenter> extends AppCompatActivity
implements BaseView{
    private SparseArray<View> mViews;
    protected T mPresenter;
    private PermissionsResultListener mListener;
    private int mRequestCode;
    private List<String> mListPermissions = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        mViews = new SparseArray<>();
        //创建presenter
        mPresenter = initPresenter();
        if(mPresenter!=null)
        mPresenter.onAttch(this);
    }
    protected abstract int getColorId();
    @Override
    protected void onDestroy() {
        super.onDestroy();
       if(mPresenter!=null)
        mPresenter.onDestroy();
        mViews.remove(mViews.size()-1);
        CustomApplcation.getInstance().finishSingleActivity(this);
    }


    private void doBeforeSetcontentView() {
        CustomApplcation.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTranslucentStatus(getColorId());

    }

    public void setTranslucentStatus(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemStatusManager tintManager = new SystemStatusManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(color);// 状态栏无背景
    }

    public void checkPermissions(String[] permissions, int requestCode, PermissionsResultListener listener) {
        //权限不能为空
        if (permissions != null || permissions.length != 0) {
            mListener = listener;
            mRequestCode = requestCode;
            for (int i = 0; i < permissions.length; i++) {
                if (!isHavePermissions(permissions[i])) {
                    mListPermissions.add(permissions[i]);
                }
            }
            //遍历完后申请
            applyPermissions();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == mRequestCode) {
            if (grantResults.length > 0) {
                mListener.onSuccessful(grantResults);
            } else {
                mListener.onFailure();
            }
        }
    }

    //判断权限是否申请
    private boolean isHavePermissions(String permissions) {
        if (ContextCompat.checkSelfPermission(this, permissions) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    //申请权限
    private void applyPermissions() {
        if (!mListPermissions.isEmpty()) {
            int size = mListPermissions.size();
            ActivityCompat.requestPermissions(this, mListPermissions.toArray(new String[size]), mRequestCode);
        }
    }
    protected abstract T initPresenter();
    @Override
    public Context getContext() {
        return this;
    }
    public T getmPresenter() {
        return mPresenter;
    }
}
