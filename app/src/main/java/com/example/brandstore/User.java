package com.example.brandstore;

public class User {

    public User(int uid, String usernname, String mobile, String address, String pin) {
        this.uid = uid;
        this.usernname = usernname;
        this.mobile = mobile;
        this.address = address;
        this.pin = pin;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsernname() {
        return usernname;
    }

    public void setUsernname(String usernname) {
        this.usernname = usernname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    private int uid;

    private String usernname,mobile,address,pin;
}
