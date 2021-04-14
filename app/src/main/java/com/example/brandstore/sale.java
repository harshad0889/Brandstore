package com.example.brandstore;

public class sale {
    private int sid;
    private String s_title;
    private String s_desc;
    private byte[] image;


    public sale(int sid, String s_title, String s_desc, byte[] image) {
        this.sid = sid;
        this.s_title = s_title;
        this.s_desc = s_desc;
        this.image = image;
    }


    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getS_title() {
        return s_title;
    }

    public void setS_title(String s_title) {
        this.s_title = s_title;
    }

    public String getS_desc() {
        return s_desc;
    }

    public void setS_desc(String s_desc) {
        this.s_desc = s_desc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}
