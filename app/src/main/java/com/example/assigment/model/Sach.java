package com.example.assigment.model;

import java.io.Serializable;

public class Sach implements Serializable {
    private Integer Masach,giathue,matacgia,maNXB,maloaisach,somuon;
    private String tensach,images,tentacgia,nxb,tenmaloai,imagesNXB,imagesTacGia;



    public Sach()
    {

    }

    public Sach(Integer masach, Integer giathue, Integer matacgia, Integer maNXB, Integer maloaisach, String tensach, String images, String tentacgia, String nxb, String tenmaloai) {
        Masach = masach;
        this.giathue = giathue;
        this.matacgia = matacgia;
        this.maNXB = maNXB;
        this.maloaisach = maloaisach;
        this.tensach = tensach;
        this.images = images;
        this.tentacgia = tentacgia;
        this.nxb = nxb;
        this.tenmaloai = tenmaloai;
    }

    public String getImagesTacGia() {
        return imagesTacGia;
    }

    public void setImagesTacGia(String imagesTacGia) {
        this.imagesTacGia = imagesTacGia;
    }

    public Integer getMasach() {
        return Masach;
    }
    public String getImagesNXB() {
        return imagesNXB;
    }

    public Integer getSomuon() {
        return somuon;
    }

    public void setSomuon(Integer somuon) {
        this.somuon = somuon;
    }

    public void setImagesNXB(String imagesNXB) {
        this.imagesNXB = imagesNXB;
    }
    public void setMasach(Integer masach) {
        Masach = masach;
    }

    public Integer getGiathue() {
        return giathue;
    }

    public void setGiathue(Integer giathue) {
        this.giathue = giathue;
    }

    public Integer getMatacgia() {
        return matacgia;
    }

    public void setMatacgia(Integer matacgia) {
        this.matacgia = matacgia;
    }

    public Integer getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(Integer maNXB) {
        this.maNXB = maNXB;
    }

    public Integer getMaloaisach() {
        return maloaisach;
    }

    public void setMaloaisach(Integer maloaisach) {
        this.maloaisach = maloaisach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTentacgia() {
        return tentacgia;
    }

    public void setTentacgia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getTenmaloai() {
        return tenmaloai;
    }

    public void setTenmaloai(String tenmaloai) {
        this.tenmaloai = tenmaloai;
    }
}
