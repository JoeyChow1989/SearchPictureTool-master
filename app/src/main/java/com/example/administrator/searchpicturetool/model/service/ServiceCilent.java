package com.example.administrator.searchpicturetool.model.service;

import com.example.administrator.searchpicturetool.config.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by wenhuaijun on 2016/1/25 0025.
 * 服务器连接客户端
 */
public class ServiceCilent {
    public  static Service mService;
    public static Service getService(){
        if(mService==null){
            createService();
        }
        return mService;
    }

    private static void createService(){
        mService = createRetrofit().create(Service.class);
    }

    private static Retrofit createRetrofit(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return  retrofit;
    }
}
