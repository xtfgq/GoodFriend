package com.goodfriend.app.ui.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goodfriend.R;
import com.goodfriend.app.common.PermissionsResultListener;
import com.goodfriend.app.ui.contract.SignDoctorContract;
import com.goodfriend.app.ui.dialog.KyDialogBuilder;
import com.goodfriend.app.ui.presenter.BasePresenter;
import com.goodfriend.app.ui.presenter.SignDoctorPresenterImpl;
import com.goodfriend.app.ui.view.ImageCycleView;
import com.goodfriend.app.utils.HttpMethods;
import com.goodfriend.app.utils.Node;
import com.goodfriend.app.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by guoqiang on 2017/6/20.
 */

public class MainActivity extends BaseToolBarActvity<SignDoctorPresenterImpl> implements
        SignDoctorContract.View {
    //广告轮播 图片链接
    ArrayList<String> mImageUrl = null;
    //广告通用对象
    ArrayList<Map<String, Object>> mObject = null;
    @BindView(R.id.tv_search_bg)
    TextView tvSearchBg;
    @BindView(R.id.activity_ele_search)
    LinearLayout activityEleSearch;
    @BindView(R.id.viewPager)
    ImageCycleView viewPager;
    @BindView(R.id.iv_reg)
    ImageView ivReg;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.iv_rule)
    ImageView ivRule;
    @BindView(R.id.iv_consult)
    ImageView ivConsult;
    @BindView(R.id.iv_notice)
    ImageView ivNotice;
    @BindView(R.id.iv_black)
    ImageView ivBlack;
    @BindView(R.id.lltwo)
    LinearLayout lltwo;
    @BindView(R.id.llone)
    LinearLayout llone;

    @Override
    protected int getColorId() {
        return R.color.red;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new SignDoctorPresenterImpl();
    }

    @Override
    public void initView() {
        setToolbarTitleTv("xxx");
        getPermisson();
//        getData();
        mImageUrl = new ArrayList<String>();
        mImageUrl.add("http://ehome.staging.topmd.cn:81/" +
                "ueditor/net/upload/image/20160926/6361050831635160295248500.jpg");
        mImageUrl.add("http://ehome.staging.topmd.cn:81/ueditor/net/upload/image" +
                "/20160926/6361050887459653274633315.jpg");
        mImageUrl.add("http://ehome.staging.topmd.cn:81/ueditor/net/upload/image/20160926/6361050921977527587668365.jpg");
        mObject = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map;
            map = new HashMap<String, Object>();
            map.put("ID", i);
            mObject.add(map);
        }

        viewPager.setImageResources(mImageUrl, mObject,
                new ImageCycleView.ImageCycleViewListener() {
                    @Override
                    public void displayImage(String imageURL, ImageView imageView) {
                        Glide.with(MainActivity.this).load(imageURL).into(imageView);
                        ViewGroup.LayoutParams para;
                        para = imageView.getLayoutParams();
                        para.width = ScreenUtils.getScreenWidth(MainActivity.this);
                        para.height = para.width * 2 / 5;
                        imageView.setLayoutParams(para);
                    }

                    @Override
                    public void onImageClick(int position, View imageView, ArrayList<Map<String,
                            Object>> mObject) {

                    }
                });
    }


    @OnClick(R.id.tv_search_bg)
    public void onViewClicked() {

    }


    @Override
    public int getContentViewID() {
        return R.layout.activity_main;
    }

    private void getPermisson() {
        checkPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA,}, 300,
                new PermissionsResultListener() {
                    @Override
                    public void onSuccessful(int[] grantResults) {
                        for (int i = 0; i < grantResults.length; i++) {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(MainActivity.this, "同意权限", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "拒绝权限", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getData() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("DoctorID", "19");
        String str = Node.getResult("MSUsersBySignDoctorInquiry", map);
        //rxjava1
        HttpMethods.getInstance().apiService
                .getUsersBySign(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody)
                            throws Exception {
                        Log.e("accept", responseBody.string() + "===========");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable)
                            throws Exception {
                        Log.e("throwable", throwable + "--------------》");

                    }
                });
        //rxjava2


    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getDoctorId() {
        return "19";
    }

    @Override
    public void onError(String e) {

    }

    @Override
    public void onSuccess(String result) {

    }

    @Override
    protected boolean isBackShow() {
        return false;
    }

    public void tanchu() {
        final KyDialogBuilder builder = new KyDialogBuilder(this);
        builder.setTitle("查找附近wifi网络");

        builder.setMessage("当您开始阅读本书时，人类已经进入了二十一世纪。");
        builder.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                builder.dismiss();
            }

        });
        builder.setPositiveButton("确认", new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                builder.dismiss();
            }

        });
        builder.setPositiveNormalTextColor(0xFF48CE3A);
        builder.setPositivePressedTextColor(0xFF48CE3A);
        builder.setBackgroundAlpha(160);
        builder.show();
    }



    @OnClick({R.id.iv_reg, R.id.iv_code, R.id.iv_rule, R.id.iv_consult, R.id.iv_notice, R.id.iv_black})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_reg:
                tanchu();
                break;
            case R.id.iv_code:
                startActivity(new Intent(MainActivity.this,MainTabActivity.class));
                break;
            case R.id.iv_rule:
                break;
            case R.id.iv_consult:
                break;
            case R.id.iv_notice:
                break;
            case R.id.iv_black:
                break;
        }
    }
}
