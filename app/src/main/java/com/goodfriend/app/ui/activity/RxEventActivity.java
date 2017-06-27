package com.goodfriend.app.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eggsy.rxbus.RxBus;
import com.eggsy.rxbus.ThreadMode;
import com.eggsy.rxbus.annotation.EventSubscribe;
import com.goodfriend.R;
import com.goodfriend.app.event.RxEvent;
import com.goodfriend.app.ui.presenter.BasePresenter;

import butterknife.OnClick;

/**
 * Created by guoqiang on 2017/6/27.
 */

public class RxEventActivity extends BaseToolBarActvity<BasePresenter>{
    private String TAG = RxEventActivity.class.getSimpleName();
    private TestInternalClass testInternalClass = new TestInternalClass();
    @Override
    protected int getColorId() {
        return R.color.red;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_event;
    }

    @Override
    public void initView() {
        setToolbarTitleTv("接收事件页面");
        RxBus.register(this);
        RxBus.register(testInternalClass);
    }
    @EventSubscribe(tmode = ThreadMode.IoThread)
    public void test(String testParam) {
        Log.i(TAG, "test IoThread, main thread id=" + getMainLooper().getThread().getId() + " , result=" + testParam);
        Log.i(TAG, "test IoThread, curr thread id=" + Thread.currentThread().getId() + " , result=" + testParam);
//        Toast.makeText(this, testParam, Toast.LENGTH_SHORT).show();
    }

    @EventSubscribe(tmode = ThreadMode.NewThread)
    public void test2(String testParam) {
        Log.i(TAG, "test NewThread, main thread id=" + getMainLooper().getThread().getId() + " , result=" + testParam);
        Log.i(TAG, "test NewThread, curr thread id=" + Thread.currentThread().getId() + " , result=" + testParam);
//        Toast.makeText(this, testParam, Toast.LENGTH_SHORT).show();
    }

    @EventSubscribe(tmode = ThreadMode.MainThread)
    public void test3(String testParam) {
        Log.i(TAG, "test MainThread, main thread id=" + getMainLooper().getThread().getId() + " , result=" + testParam);
        Log.i(TAG, "test MainThread, curr thread id=" + Thread.currentThread().getId() + " , result=" + testParam);
//        Toast.makeText(this, testParam, Toast.LENGTH_SHORT).show();
    }

    @EventSubscribe(tmode = ThreadMode.ComputationThread)
    public void test4(String testParam) {
        Log.i(TAG, "test ComputationThread, main thread id=" + getMainLooper().getThread().getId() + " , result=" + testParam);
        Log.i(TAG, "test ComputationThread, curr thread id=" + Thread.currentThread().getId() + " , result=" + testParam);
//        Toast.makeText(this, testParam, Toast.LENGTH_SHORT).show();
    }

    @EventSubscribe(tmode = ThreadMode.SingleThread)
    public void test5(String testParam) {
        Log.i(TAG, "test SingleThread, main thread id=" + getMainLooper().getThread().getId() + " , result=" + testParam);
        Log.i(TAG, "test SingleThread, curr thread id=" + Thread.currentThread().getId() + " , result=" + testParam);
//        Toast.makeText(this, testParam, Toast.LENGTH_SHORT).show();
    }

    @EventSubscribe(tmode = ThreadMode.PostThread)
    public void test7(String testParam) {
        Log.i(TAG, "test PostThread, main thread id=" + getMainLooper().getThread().getId() + " , result=" + testParam);
        Log.i(TAG, "test PostThread, curr thread id=" + Thread.currentThread().getId() + " , result=" + testParam);
       Toast.makeText(this, testParam, Toast.LENGTH_SHORT).show();
    }

    @EventSubscribe
    public void testboolean(boolean testParam) {
        Log.i(TAG, "test boolean, main thread id=" + getMainLooper().getThread().getId() + " , result=" + testParam);
        Log.i(TAG, "test boolean, curr thread id=" + Thread.currentThread().getId() + " , result=" + testParam);
//        Toast.makeText(this, testParam, Toast.LENGTH_SHORT).show();
    }

    @EventSubscribe
    public void testBoolean(Boolean testParam) {
        Log.i(TAG, "test Boolean, main thread id=" + getMainLooper().getThread().getId() + " , result=" + testParam);
        Log.i(TAG, "test Boolean, curr thread id=" + Thread.currentThread().getId() + " , result=" + testParam);
//        Toast.makeText(this, testParam, Toast.LENGTH_SHORT).show();
    }

    @EventSubscribe
    public void testCustomEvent(RxEvent event) {
        Log.i(TAG, "test custom Event, event=" + event.toString());
    }

    @OnClick(R.id.btn_test)
    public void clickStepTest(View v) {
        Intent postEventIntent = new Intent(this, PostEventActivity.class);
        startActivity(postEventIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.unRegister(this);
    }
    public class TestInternalClass{
        @EventSubscribe
        public void testCustomEvent(RxEvent event) {
            Log.i(TAG, "test internal class custom Event, event=" + event.toString());
        }
    }
}
