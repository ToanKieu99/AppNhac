package com.toanvq.fpoly.appnhac.service;

import com.toanvq.fpoly.appnhac.model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {


    // GET để tương tác vs bên server
    @GET("songbanner.php")
    // nhận dữ liệu về
    Call<List<Quangcao>> GetDataBanner();

}
