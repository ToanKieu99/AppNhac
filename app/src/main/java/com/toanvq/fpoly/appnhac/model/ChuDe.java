package com.toanvq.fpoly.appnhac.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChuDe {

    @SerializedName("IdChuDe")
    @Expose
    private String idChuDe;
    @SerializedName("TenChhuDe")
    @Expose
    private String tenChhuDe;
    @SerializedName("HinhChuDe")
    @Expose
    private String hinhChuDe;

    public String getIdChuDe() {
        return idChuDe;
    }

    public void setIdChuDe(String idChuDe) {
        this.idChuDe = idChuDe;
    }

    public String getTenChhuDe() {
        return tenChhuDe;
    }

    public void setTenChhuDe(String tenChhuDe) {
        this.tenChhuDe = tenChhuDe;
    }

    public String getHinhChuDe() {
        return hinhChuDe;
    }

    public void setHinhChuDe(String hinhChuDe) {
        this.hinhChuDe = hinhChuDe;
    }

}