
package com.goodfriend.app.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.goodfriend.R;
import com.goodfriend.app.common.Constants;

import com.goodfriend.app.ui.model.News;
import com.goodfriend.app.utils.DateUtils;
import com.sunfusheng.glideimageview.GlideImageView;
import com.superrecycleview.superlibrary.adapter.BaseViewHolder;
import com.superrecycleview.superlibrary.adapter.SuperBaseAdapter;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by super南仔 on 07/28/16. blog: http://supercwn.github.io/ GitHub:
 * https://github.com/supercwn
 */
public class RefreshAndLoadMoreAdapter extends SuperBaseAdapter<News> {
    private Context mContext;
    private List<News> mList;

    public RefreshAndLoadMoreAdapter(Context context, List<News> data) {
        super(context, data);
        this.mContext=context;
        this.mList=data;
    }
    public List<News> getmList() {
        return mList;
    }

    public void setmList(List<News> mList) {
        this.mList = mList;
    }

    @Override
    protected void convert(BaseViewHolder holder, News item, int position) {
        holder.setText(R.id.tv_title,item.getTitle());
        holder.setText(R.id.tv_content,item.getZhaiyao());
        Date date= DateUtils.str2Date(item.getCreatedDate());
        try {
            int t=DateUtils.isYeaterday(date,null);
            if(t==-1){
                holder.setText(R.id.timetv,"今天 "+DateUtils.StringPattern(item.getCreatedDate(),
                        "yyyy/MM/dd HH:mm:ss","HH:mm"));
            }else if(t==0){
                holder.setText(R.id.timetv,"昨天 "+DateUtils.StringPattern(item.getCreatedDate(),
                        "yyyy" + "/MM/dd HH:mm:ss","HH:mm"));
            }else if(t==1){
                holder.setText(R.id.timetv,DateUtils.StringPattern(item.getCreatedDate(),"yyy" +
                        "y/MM/dd HH:mm:ss","MM/dd HH:mm"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GlideImageView imageView=(GlideImageView)holder.getView(R.id.iv_head);
        imageView.setRadius(10);
        imageView.setBorderWidth(3);
        imageView.setBorderColor(R.color.white);
        imageView.setPressedAlpha(0.3f);
        imageView.setPressedColor(R.color.white);
        imageView.loadImage(Constants.BASE_URL + item.getPic().replace("~", "").replace("\\", "/"),
                R.color.placeholder_color);
//        Glide.with(mContext)
//                .load(Constants.BASE_URL + item.getPic().replace("~", "").replace("\\", "/"))
//                .into((ImageView) holder.getView(R.id.iv_head));

    }

    @Override
    protected int getItemViewLayoutId(int position, News item) {
        return R.layout.home_item;
    }


}
