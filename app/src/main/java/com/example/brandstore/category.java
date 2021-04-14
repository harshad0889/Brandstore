package com.example.brandstore;

public class category {


    private  int catid;
    private String cat_name;
    private byte[] img;




    public category(int catid, String cat_name, byte[] img) {
        this.catid = catid;
        this.cat_name = cat_name;
        this.img = img;

    }




    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }



}
