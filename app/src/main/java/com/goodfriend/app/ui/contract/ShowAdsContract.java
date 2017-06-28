package com.goodfriend.app.ui.contract;

import com.goodfriend.app.ui.view.BaseView;

/**
 * Created by guoqiang on 2017/6/28.
 */

public class ShowAdsContract {
    public interface View extends BaseView {
        String getPageSize();
        String getPageIndex();
        void onError(String e);
        void onSuccess(String result);
    }
    public interface Model {
    }
    public interface Presenter  {
    }
}
