package com.goodfriend.app.utils;



import com.goodfriend.app.common.ApiService;
import com.goodfriend.app.common.Constants;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.util.List;
import java.util.concurrent.TimeUnit;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;



/**
 * Created by zt on 2017/3/10.
 */

public class HttpMethods {


    private static final int TIME_OUT=10;
    private static final int READ_TIMEOUT = 20;
    private static final int WRITE_TIMEOUT = 10;
     Retrofit retrofit;
    public  ApiService apiService;

    private HttpMethods() {
        /**
         * 构造函数私有化
         * 并在构造函数中进行retrofit的初始化
         */
        OkHttpClient client=new OkHttpClient();
        client.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS).writeTimeout(WRITE_TIMEOUT,
                TimeUnit.SECONDS).readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).
                retryOnConnectionFailure(true);
        Strategy strategy = new AnnotationStrategy();
        Serializer serializer = new Persister(strategy);
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        apiService=retrofit.create(ApiService.class);
    }


    private static class sinalInstance {
        public static final HttpMethods instance = new HttpMethods();
    }

    public  static HttpMethods getInstance(){
        return sinalInstance.instance;
    }


    public interface onRequestCallBack{
        void onSuccess(String msg);
        void onError(String msg);
    }

}
