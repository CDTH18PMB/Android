package com.hoanglam.congthucnauan.Class;

public class MonAn {
    private String MaMon;
    private String TenMon;
    private String AnhDaiDien;
    private String ThoiGianNau;
    private String LuotXem;
    private String LuotThich;
    private String NguoiTao;

    public MonAn() {
    }

    public MonAn(String maMon, String tenMon, String anhDaiDien, String thoiGianNau, String luotXem, String luotThich, String nguoiTao) {
        MaMon = maMon;
        TenMon = tenMon;
        AnhDaiDien = anhDaiDien;
        ThoiGianNau = thoiGianNau;
        LuotXem = luotXem;
        LuotThich = luotThich;
        NguoiTao = nguoiTao;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String maMon) {
        MaMon = maMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public String getAnhDaiDien() {
        return AnhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        AnhDaiDien = anhDaiDien;
    }

    public String getThoiGianNau() {
        return ThoiGianNau;
    }

    public void setThoiGianNau(String thoiGianNau) {
        ThoiGianNau = thoiGianNau;
    }

    public String getLuotXem() {
        return LuotXem;
    }

    public void setLuotXem(String luotXem) {
        LuotXem = luotXem;
    }

    public String getLuotThich() {
        return LuotThich;
    }

    public void setLuotThich(String luotThich) {
        LuotThich = luotThich;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        NguoiTao = nguoiTao;
    }
}
