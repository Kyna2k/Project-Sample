package com.example.assigment.model;

public class User {
    private String HoTen, Email,Avatar,Username, password;
    private Integer type,id;
    public User(){};
    public User(String hoTen, String email, String avatar, String username, String password, Integer type) {
        HoTen = hoTen;
        Email = email;
        Avatar = avatar;
        Username = username;
        this.password = password;
        this.type = type;
    }

    public User(String hoTen, String email, String avatar, String username, String password, Integer type, Integer id) {
        HoTen = hoTen;
        Email = email;
        Avatar = avatar;
        Username = username;
        this.password = password;
        this.type = type;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
