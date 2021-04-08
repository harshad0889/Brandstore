package com.example.brandstore;

public class UserModel {


    public String user_id;
    public String username;

    public String phnno;
    public String password;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public UserModel(String user_id, String username, String phone,String password) {
        this.user_id = user_id;
        this.username = username;
        this.phnno = phone;
        this.password = password;

    }
}
