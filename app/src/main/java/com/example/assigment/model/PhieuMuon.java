package com.example.assigment.model;

public class PhieuMuon {
    private Integer id,MaQuanLyNhan,MaQuanLyDua,MaMember,Masach,trangthai;
    private Long ngaythue,ngaytra;
    private Sach sach;
    private User user;
    public PhieuMuon(){}
    public PhieuMuon(Integer id, Integer maQuanLyNhan, Integer maQuanLyDua, Integer maMember, Integer masach, Integer trangthai, Long ngaythue, Long ngaytra) {
        this.id = id;
        MaQuanLyNhan = maQuanLyNhan;
        MaQuanLyDua = maQuanLyDua;
        MaMember = maMember;
        Masach = masach;
        this.trangthai = trangthai;
        this.ngaythue = ngaythue;
        this.ngaytra = ngaytra;
    }

    public PhieuMuon(Integer id, Integer maQuanLyNhan, Integer maQuanLyDua, Integer maMember, Integer masach, Integer trangthai, Long ngaythue, Long ngaytra, Sach sach, User user) {
        this.id = id;
        MaQuanLyNhan = maQuanLyNhan;
        MaQuanLyDua = maQuanLyDua;
        MaMember = maMember;
        Masach = masach;
        this.trangthai = trangthai;
        this.ngaythue = ngaythue;
        this.ngaytra = ngaytra;
        this.sach = sach;
        this.user = user;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaQuanLyNhan() {
        return MaQuanLyNhan;
    }

    public void setMaQuanLyNhan(Integer maQuanLyNhan) {
        MaQuanLyNhan = maQuanLyNhan;
    }

    public Integer getMaQuanLyDua() {
        return MaQuanLyDua;
    }

    public void setMaQuanLyDua(Integer maQuanLyDua) {
        MaQuanLyDua = maQuanLyDua;
    }

    public Integer getMaMember() {
        return MaMember;
    }

    public void setMaMember(Integer maMember) {
        MaMember = maMember;
    }

    public Integer getMasach() {
        return Masach;
    }

    public void setMasach(Integer masach) {
        Masach = masach;
    }

    public Integer getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Integer trangthai) {
        this.trangthai = trangthai;
    }

    public Long getNgaythue() {
        return ngaythue;
    }

    public void setNgaythue(Long ngaythue) {
        this.ngaythue = ngaythue;
    }

    public Long getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(Long ngaytra) {
        this.ngaytra = ngaytra;
    }
}
