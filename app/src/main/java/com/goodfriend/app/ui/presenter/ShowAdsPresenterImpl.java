package com.goodfriend.app.ui.presenter;


import android.util.Log;

import com.goodfriend.app.ui.contract.ShowAdsContract;
import com.goodfriend.app.utils.HttpMethods;
import com.goodfriend.app.utils.Node;
import com.goodfriend.app.utils.ResultObserver;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by guoqiang on 2017/6/28.
 */

public class ShowAdsPresenterImpl extends BasePresenter<ShowAdsContract.View>{
    @Override
    public void onCreate() {

    }

    @Override
    public void loadData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("PageSize", mView.getPageSize());
        map.put("PageIndex", mView.getPageIndex());
        HttpMethods.getInstance().apiService.getNewsInquiry(Node.getResult("NewsInquiry", map)) .
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultObserver(new HttpMethods.onRequestCallBack() {
                    @Override
                    public void onSuccess(String msg) {

                        Log.e("accept1234", msg + "!!!!=");
                        mView.onSuccess(msg);
                    }

                    @Override
                    public void onError(String msg) {
                        mView.onError(msg);
                    }
                }));
    }

    @Override
    public void cancleNetWork() {

    }
}
