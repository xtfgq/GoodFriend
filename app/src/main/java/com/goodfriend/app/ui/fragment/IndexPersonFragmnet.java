package com.goodfriend.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.goodfriend.R;
import com.goodfriend.app.ui.contract.SignDoctorContract;
import com.goodfriend.app.ui.presenter.SignDoctorPresenterImpl;

/**
 * Created by guoqiang on 2017/6/27.
 */

public class IndexPersonFragmnet extends BaseFragment<SignDoctorPresenterImpl>
        implements SignDoctorContract.View{
    @Override
    public View initView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, null);
    }

    @Override
    protected SignDoctorPresenterImpl initPresenter() {
        return new SignDoctorPresenterImpl();
    }

    @Override
    public String getDoctorId() {
        return "19";
    }
    public static IndexPersonFragmnet newInstance() {
        Bundle args = new Bundle();

        IndexPersonFragmnet fragment = new IndexPersonFragmnet();
        fragment.setArguments(args);
        return fragment;
    }
}
