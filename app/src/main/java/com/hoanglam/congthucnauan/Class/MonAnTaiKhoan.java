package com.hoanglam.congthucnauan.Class;

public class MonAnTaiKhoan {
    private String MaMon;
    private String TenMon;
    private String AnhDaiDien;
    private String MoTa;
    private String DoKho;
    private String ThoiGianNau;
    private String NguyenLieu;
    private int LuotXem;
    private int LuotThich;
    private String NguoiTao;
    private int LoaiMon;
    private int TrangThai ;
    private String Username;

    //món dã tạo
    public MonAnTaiKhoan(String maMon, String tenMon, String anhDaiDien,
                      String thoiGianNau, int luotXem, int luotThich) {
        MaMon = maMon;
        TenMon = tenMon;
        AnhDaiDien = anhDaiDien;
        ThoiGianNau = thoiGianNau;
        LuotXem = luotXem;
        LuotThich = luotThich;
    }
    //món đã thích
    public MonAnTaiKhoan(String maMon, String tenMon, String anhDaiDien, String thoiGianNau,
                         int luotXem, int luotThich, String nguoiTao, String username) {
        MaMon = maMon;
        TenMon = tenMon;
        AnhDaiDien = anhDaiDien;
        ThoiGianNau = thoiGianNau;
        LuotXem = luotXem;
        LuotThich = luotThich;
        NguoiTao = nguoiTao;
        Username = username;
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

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getDoKho() {
        return DoKho;
    }

    public void setDoKho(String doKho) {
        DoKho = doKho;
    }

    public String getThoiGianNau() {
        return ThoiGianNau;
    }

    public void setThoiGianNau(String thoiGianNau) {
        ThoiGianNau = thoiGianNau;
    }

    public String getNguyenLieu() {
        return NguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        NguyenLieu = nguyenLieu;
    }

    public int getLuotXem() {
        return LuotXem;
    }

    public void setLuotXem(int luotXem) {
        LuotXem = luotXem;
    }

    public int getLuotThich() {
        return LuotThich;
    }

    public void setLuotThich(int luotThich) {
        LuotThich = luotThich;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        NguoiTao = nguoiTao;
    }

    public int getLoaiMon() {
        return LoaiMon;
    }

    public void setLoaiMon(int loaiMon) {
        LoaiMon = loaiMon;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
