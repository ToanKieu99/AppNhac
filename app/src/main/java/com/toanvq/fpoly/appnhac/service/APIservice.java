package com.toanvq.fpoly.appnhac.service;

public class APIservice {

    // lấy địa chỉ để truy cập vào forder chung chứa các file móc dữ liệu
    private static String base_url = "https://toankieu001.000webhostapp.com/Server/";

    // function để kết nối giữa Dataservice vs APIRetrofitClient
    public static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
