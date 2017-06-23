package com.goodfriend.app.common;

import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by zt on 2017/3/10.
 */

public interface ApiService {
    @Headers({
            "Content-Type: text/xml; charset=utf-8",
            "SOAPAction: http://tempuri.org/MSUsersBySignDoctorInquiry"
    })
    @POST("WebServices/EhomeWebservice.asmx")
    Observable<ResponseBody> getUsersBySign(@retrofit2.http.Body String str);
}
