package com.hoanglam.congthucnauan.Class;

import java.io.Serializable;

public class Account implements Serializable {
    private String Username;
    private String AnhDaiDien;
    private String Password;
    private String HoTen;
    private String SDT;
    private String Email;
    private String LoaiTK;

    public Account(){

    }
    public Account(String username, String anhDaiDien, String password, String hoTen, String SDT, String email, String loaiTK) {
        Username = username;
        AnhDaiDien = anhDaiDien;
        Password = password;
        HoTen = hoTen;
        this.SDT = SDT;
        Email = email;
        LoaiTK = loaiTK;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getAnhDaiDien() {
        return AnhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        AnhDaiDien = anhDaiDien;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLoaiTK() {
        return LoaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        LoaiTK = loaiTK;
    }
}
