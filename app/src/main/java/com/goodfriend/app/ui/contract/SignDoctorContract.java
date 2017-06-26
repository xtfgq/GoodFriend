package com.goodfriend.app.ui.contract;

import com.goodfriend.app.ui.presenter.BasePresenter;
import com.goodfriend.app.ui.view.BaseView;

/**
 * Created by guoqiang on 2017/6/23.
 */

public class SignDoctorContract {
    public interface View extends BaseView{
        String getDoctorId();
    }

    public interface Model {
    }
    public interface Presenter  {
    }
}
