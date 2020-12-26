package com.hoanglam.congthucnauan.Class;

public class HuongDan {
    private String BuocLam;
    private String URL_HinhAnh;

    public HuongDan(){
        BuocLam = "";
        URL_HinhAnh = "";
    }

    public String getBuocLam() {
        return BuocLam;
    }

    public void setBuocLam(String buocLam) {
        BuocLam = buocLam;
    }

    public String getURL_HinhAnh() {
        return URL_HinhAnh;
    }

    public void setURL_HinhAnh(String URL_HinhAnh) {
        this.URL_HinhAnh = URL_HinhAnh;
    }
}
