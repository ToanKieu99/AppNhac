package com.toanvq.fpoly.appnhac.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRetrofitClient {

    // tạo ra 1 biến Retrofit
    private static Retrofit retrofit = null;


    // tạo 1 function để trả về cấu hình cho Retrofit
    public static Retrofit getClient(String base_url){

        // giao thức để thong qua mạng vs bên server
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10000, TimeUnit.MILLISECONDS) // nếu thời gian chờ lâu thì sẽ ngắt
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000,TimeUnit.MILLISECONDS) // đợi lấu thì sẽ tự đọng ngắt kết nối
                .retryOnConnectionFailure(true) // nếu mất mạng mà lúc sau có kết nối thì se kết nối lại
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();


        // chuyển dữu liệu API từ server về gson
        Gson gson = new GsonBuilder().setLenient().create();

        // gắn vào cho Retrofit
        retrofit = new Retrofit.Builder()
                        .baseUrl(base_url)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

}
