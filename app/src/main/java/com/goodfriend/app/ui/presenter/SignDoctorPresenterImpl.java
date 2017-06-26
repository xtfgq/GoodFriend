package com.goodfriend.app.ui.presenter;

import android.util.Log;

import com.goodfriend.app.ui.contract.SignDoctorContract;
import com.goodfriend.app.utils.HttpMethods;
import com.goodfriend.app.utils.Node;
import com.goodfriend.app.utils.ResultObserver;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by guoqiang on 2017/6/23.
 */

public class SignDoctorPresenterImpl extends BasePresenter<SignDoctorContract.View>

{

    @Override
    public void onCreate() {
        Log.e("onc","!!!!=");
    }

    @Override
    public void loadData() {
        HashMap<String, String> map = new HashMap<String,String>();
        map.put("DoctorID", mView.getDoctorId());
        HttpMethods.getInstance().apiService
                .getUsersBySign(Node.getResult("MSUsersBySignDoctorInquiry", map))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultObserver(new HttpMethods.onRequestCallBack() {
                    @Override
                    public void onSuccess(String msg) {

                        Log.e("accept",msg+"!!!!=");
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));
    }

    @Override
    public void cancleNetWork() {

    }
}
