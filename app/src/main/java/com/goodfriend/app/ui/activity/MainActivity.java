package com.goodfriend.app.ui.activity;


import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.goodfriend.R;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by guoqiang on 2017/6/20.
 */

public class MainActivity extends BaseAppCompatActivity {
    @BindView(R.id.tv_search_bg)
    TextView tvSearchBg;

    @BindView(R.id.activity_ele_search)
    LinearLayout activityEleSearch;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getColorId() {
        return R.color.red;
    }

    @Override
    public void initView() {
        setTitle("良友排号");
    }


    @OnClick(R.id.tv_search_bg)
    public void onViewClicked() {

    }


}
