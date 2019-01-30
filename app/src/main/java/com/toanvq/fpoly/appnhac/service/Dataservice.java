package com.toanvq.fpoly.appnhac.service;

import com.toanvq.fpoly.appnhac.model.Album;
import com.toanvq.fpoly.appnhac.model.BaiHat;
import com.toanvq.fpoly.appnhac.model.ChuDe;
import com.toanvq.fpoly.appnhac.model.ChuDeTheLoaiTrongNgay;
import com.toanvq.fpoly.appnhac.model.PlayList;
import com.toanvq.fpoly.appnhac.model.Quangcao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {


    // GET để tương tác vs bên server
    @GET("songbanner.php")
    // nhận dữ liệu về
    Call<List<Quangcao>> GetDataBanner();


    @GET("playlistforcurrent.php")
    Call<List<PlayList>> GetPlayListCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<ChuDeTheLoaiTrongNgay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbum();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> GetBaiHat();

    //do bên server dùng phương thức POST nên bên clien cũng phải dùng phương thức giống nó
    // bên trên là chỉ để tương tác vs đường link để lấy dữ liệu về
    // phương thức dưới này là phải gửi dữ liệu lên để server trả dữ liệu về vì dùng POST
    // gửi dữ liệu idquangcao lên vì trên server cũng để là id quảng cáo. nên muốn lấy dự liệu về cũng phải gửi đúng dữ liệu lên
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaihatTheoPlaylist(@Field("idplaylist") String idplaylist);


    @GET("danhsachcacplaylist.php")
    Call<List<PlayList>> GetDanhSachPlaylist();


    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> Getdanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetAllChuDe();



}
