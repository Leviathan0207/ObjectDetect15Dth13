package com.example.bk.textdetection.Model;

public class BienSoXe {
    private String ID, HoTen, DiaChi, NgaySinh, NgayDangKy, QueQuan, SoCMND;

    public BienSoXe() {
    }

    public BienSoXe(String ID, String hoTen, String diaChi, String ngaySinh, String ngayDangKy, String queQuan, String soCMND) {
        this.ID = ID;
        HoTen = hoTen;
        DiaChi = diaChi;
        NgaySinh = ngaySinh;
        NgayDangKy = ngayDangKy;
        QueQuan = queQuan;
        SoCMND = soCMND;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKy(String ngayDangKy) {
        NgayDangKy = ngayDangKy;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String queQuan) {
        QueQuan = queQuan;
    }

    public String getSoCMND() {
        return SoCMND;
    }

    public void setSoCMND(String soCMND) {
        SoCMND = soCMND;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

}
