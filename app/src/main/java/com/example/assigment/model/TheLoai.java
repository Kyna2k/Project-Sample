package com.example.assigment.model;

public class TheLoai {
    private Integer matheloai;
    private String tentheloai;
    public TheLoai(){}

    public TheLoai(Integer matheloai, String tentheloai) {
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
    }

    public Integer getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(Integer matheloai) {
        this.matheloai = matheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }
}
