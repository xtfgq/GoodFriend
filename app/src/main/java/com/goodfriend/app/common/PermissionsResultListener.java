package com.goodfriend.app.common;

/**
 * 权限封装
 */
public interface PermissionsResultListener {

    //成功
    void onSuccessful(int[] grantResults);

    //失败
    void onFailure();
}
