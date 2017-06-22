package com.goodfriend.app.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.goodfriend.R;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.editPhone)
    EditText editPhone;
    @BindView(R.id.rlaccount)
    RelativeLayout rlaccount;
    @BindView(R.id.editPass)
    EditText editPass;
    @BindView(R.id.rlpass)
    RelativeLayout rlpass;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_pwd)
    TextView tvPwd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected int getColorId() {
        return R.color.transparent;
    }

    @Override
    public void initView() {

    }




    @OnClick({R.id.tv_login, R.id.tv_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
                break;
            case R.id.tv_pwd:
                break;
        }
    }


}
