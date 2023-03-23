package com.example.assigment.model;

public class NXB {
    private String images,tenNXB;
    private Integer maNXB;

    public NXB(){}
    public NXB(String images, String tenNXB, Integer maNXB) {
        this.images = images;
        this.tenNXB = tenNXB;
        this.maNXB = maNXB;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public Integer getMaNXB() {
        return maNXB;
    }

    public void setMaNXB(Integer maNXB) {
        this.maNXB = maNXB;
    }
}
