package com.example.tlucontact.DBDV;

public class DBDV {
    private int imv_dbdv_avatar;
    private String name;
    private String phone_number;
    private String address;

    public DBDV(int imv_dbdv_avatar, String name, String phone_number, String address) {
        this.imv_dbdv_avatar = imv_dbdv_avatar;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
    }

    public int getImv_dbdv_avatar() {
        return imv_dbdv_avatar;
    }

    public void setImv_dbdv_avatar(int imv_dbdv_avatar) {
        this.imv_dbdv_avatar = imv_dbdv_avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DBDV(String name, String phone_number, String address) {
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
    }

    public DBDV() {
    }
}
