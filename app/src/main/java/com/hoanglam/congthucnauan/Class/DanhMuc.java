package com.hoanglam.congthucnauan.Class;

public class DanhMuc {
    private  int MaLoai;
    private String TenLoai;
    private  int TrangThai;

    public DanhMuc(int maLoai, String tenLoai, int trangThai) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
        TrangThai = trangThai;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }
}
