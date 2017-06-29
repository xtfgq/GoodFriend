package com.goodfriend.app.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goodfriend.R;
import com.goodfriend.app.ui.adapter.RefreshAndLoadMoreAdapter;
import com.goodfriend.app.ui.contract.ShowAdsContract;
import com.goodfriend.app.ui.model.News;
import com.goodfriend.app.ui.model.NewsDate;
import com.goodfriend.app.ui.presenter.ShowAdsPresenterImpl;
import com.goodfriend.app.utils.JsonTools;
import com.superrecycleview.superlibrary.recycleview.ProgressStyle;
import com.superrecycleview.superlibrary.recycleview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by guoqiang on 2017/6/27.
 */

public class IndexPersonFragmnet extends BaseFragment<ShowAdsPresenterImpl>
        implements ShowAdsContract.View, SuperRecyclerView.LoadingListener {
    @BindView(R.id.super_recycle_view)
    SuperRecyclerView superRecyclerView;

    private int page = 1;
    private RefreshAndLoadMoreAdapter mAdapter;
    private List<News> dataList = new ArrayList<>();

    @Override
    public View initView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, null);
    }

    @Override
    protected ShowAdsPresenterImpl initPresenter() {
        return new ShowAdsPresenterImpl();
    }

    public static IndexPersonFragmnet newInstance() {
        Bundle args = new Bundle();
        args.putString("bundle", "123456");
        IndexPersonFragmnet fragment = new IndexPersonFragmnet();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public String getPageSize() {
        return 10 + "";
    }

    @Override
    public String getPageIndex() {
        return page + "";
    }

    @Override
    public void onError(String e) {

    }

    @Override
    public void onSuccess(String result) {
        if(result.contains("MessageCode")&&page>1){
            superRecyclerView.setNoMore(true);
        }else {
            NewsDate date = JsonTools.getData(result.toString(), NewsDate.class);
            List<News> list = date.getData();
            if (page == 1) dataList.clear();
            for (News n : list) {
                dataList.add(n);
            }
            mAdapter.setmList(dataList);
            mAdapter.notifyDataSetChanged();
            if (page == 1) {
                superRecyclerView.completeRefresh();
            } else {
                superRecyclerView.completeLoadMore();
            }
        }
    }

    @Override
    public void onRefresh() {
        page = 1;
        getmPresenter().loadData();

    }

    @Override
    public void onLoadMore() {
        page++;
        getmPresenter().loadData();
    }

    @Override
    protected void initDo() {
        page = 1;
    }

    @Override
    public void initListener() {
        super.initListener();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        superRecyclerView.setLayoutManager(layoutManager);
        superRecyclerView.setRefreshEnabled(true);// 可以定制是否开启下拉刷新
        superRecyclerView.setLoadMoreEnabled(true);// 可以定制是否开启加载更多
        superRecyclerView.setLoadingListener(this);// 下拉刷新，上拉加载的监听
        superRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);// 下拉刷新的样式
        superRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);// 上拉加载的样式
        superRecyclerView.setArrowImageView(R.mipmap.iconfont_downgrey);// 设置下拉箭头
        mAdapter = new RefreshAndLoadMoreAdapter(getContext(), dataList);
        superRecyclerView.setAdapter(mAdapter);
    }
}
