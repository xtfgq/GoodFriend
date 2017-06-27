package com.goodfriend.app.ui.activity;

import android.view.View;

import com.eggsy.rxbus.RxBus;
import com.goodfriend.R;
import com.goodfriend.app.event.RxEvent;
import com.goodfriend.app.ui.presenter.BasePresenter;


import butterknife.OnClick;

import static com.goodfriend.R.*;

/**
 * Created by guoqiang on 2017/6/27.
 */

public class PostEventActivity extends BaseToolBarActvity<BasePresenter> {

    private int i = 0;
    @Override
    public int getContentViewID() {
        return layout.activity_post;
    }

    @Override
    public void initView() {
        setToolbarTitleTv("发送事件页面");
    }

    @Override
    protected int getColorId() {
        return color.red;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @OnClick(id.btn_post_main)
    public void clickPostMainEvent(View view) {
        RxBus.post("(main thread) eggsy test " + (i++) + " times");
//        RxBus.post(true);
//        RxBus.post(new Boolean(false));
    }

    @OnClick(id.btn_post_new)
    public void clickPostNewEvent(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RxBus.post("(new thread) eggsy test " + (i++) + " times");
            }
        }).start();
    }

    @OnClick(id.btn_post_event)
    public void clickPostEventObject(View view){
        RxEvent rxEvent = new RxEvent();
        rxEvent.setTimes(i++);
        rxEvent.setContent("post event object");
        RxBus.post(rxEvent);
    }

}
