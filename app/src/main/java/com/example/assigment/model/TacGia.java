package com.example.assigment.model;

public class TacGia {
    private String tentacgia, images;
    private Integer matacgia;

    public TacGia(){}
    public TacGia(String tentacgia, String images, Integer matacgia) {
        this.tentacgia = tentacgia;
        this.images = images;
        this.matacgia = matacgia;
    }

    public String getTentacgia() {
        return tentacgia;
    }

    public String getImages() {
        return images;
    }

    public Integer getMatacgia() {
        return matacgia;
    }

    public void setTentacgia(String tentacgia) {
        this.tentacgia = tentacgia;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setMatacgia(Integer matacgia) {
        this.matacgia = matacgia;
    }
}
