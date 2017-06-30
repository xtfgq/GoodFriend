package com.goodfriend.app.common;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by guoqiang on 2017/6/20.
 */

public class CustomApplcation extends Application{
    public static List<Activity> mList = new LinkedList<Activity>();
    public static CustomApplcation mInstance;
    public static CustomApplcation getInstance() {
        if (mInstance == null) {
            synchronized (CustomApplcation.class) {
                if (mInstance == null) {
                    mInstance = new CustomApplcation();
                }
            }
        }
        return mInstance;
    }
    public void addActivity(Activity activity) {
        mList.add(activity);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
    /**
     * 结束指定的Activity
     */
    public static void finishSingleActivity(Activity activity) {
        if (activity != null) {
            if (mList.contains(activity)) {
                mList.remove(activity);
            }
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     * 遍历之后才去删除。
     */
    public static void finishSingleActivityByClass(Class cls) {
        Activity tempActivity = null;
        for (Activity activity : mList) {
            if (activity.getClass().equals(cls)) {
                tempActivity = activity;
            }
        }

        finishSingleActivity(tempActivity);
    }
}
