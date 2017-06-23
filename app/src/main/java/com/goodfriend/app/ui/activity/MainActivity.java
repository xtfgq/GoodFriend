package com.goodfriend.app.ui.activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.goodfriend.R;
import com.goodfriend.app.common.PermissionsResultListener;
import com.goodfriend.app.ui.view.ImageCycleView;
import com.goodfriend.app.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by guoqiang on 2017/6/20.
 */

public class MainActivity extends BaseToolBarActvity {
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
    @Override
    protected int getColorId() {
        return R.color.red;
    }

    @Override
    public void initView() {
        setToolbarTitleTv("xxx");
        getPermisson();
        mImageUrl=new ArrayList<String>();
        mImageUrl.add("http://ehome.staging.topmd.cn:81/" +
                "ueditor/net/upload/image/20160926/6361050831635160295248500.jpg");
        mImageUrl.add("http://ehome.staging.topmd.cn:81/ueditor/net/upload/image" +
                "/20160926/6361050887459653274633315.jpg");
        mImageUrl.add("http://ehome.staging.topmd.cn:81/ueditor/net/upload/image/20160926/6361050921977527587668365.jpg");
        mObject=new ArrayList<Map<String, Object>>();
        for(int i=0;i<3;i++) {
            Map<String, Object> map;
            map = new HashMap<String, Object>();
            map.put("ID", i);
            mObject.add(map);
        }

        viewPager.setImageResources(mImageUrl,mObject,
                new ImageCycleView.ImageCycleViewListener(){
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
    private void getPermisson(){
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

}
