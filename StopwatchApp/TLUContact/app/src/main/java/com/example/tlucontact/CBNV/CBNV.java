package com.example.tlucontact.CBNV;

public class CBNV {
    private int imv_cbnv_avatar;
    private String name;
    private String position;
    private String phone_number;
    private String email;
    private String donvi;

    public CBNV(int imv_cbnv_avatar, String name, String phone_number, String position, String email, String donvi) {
        this.imv_cbnv_avatar = imv_cbnv_avatar;
        this.name = name;
        this.phone_number = phone_number;
        this.position = position;
        this.email = email;
        this.donvi = donvi;
    }

    public int getImv_cbnv_avatar() {
        return imv_cbnv_avatar;
    }

    public void setImv_cbnv_avatar(int imv_cbnv_avatar) {
        this.imv_cbnv_avatar = imv_cbnv_avatar;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public CBNV(String name, String position, String email, String phone_number, String donvi) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone_number = phone_number;
        this.donvi = donvi;
    }

    public CBNV() {
    }
}
