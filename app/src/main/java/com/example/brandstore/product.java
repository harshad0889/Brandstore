package com.example.brandstore;

public class product {
    private int pid;
    private String p_name;
    private String a_price;
    private String o_price;
    private String p_sale;
    private String p_desc;
    private String p_off;
    private String p_category;
    private String p_size;

    private byte[] image;


    public String getP_sale() {
        return p_sale;
    }

    public void setP_sale(String p_sale) {
        this.p_sale = p_sale;
    }

    public String getP_size() {
        return p_size;
    }

    public void setP_size(String p_size) {
        this.p_size = p_size;
    }

    public product(int pid, String p_name,String p_category,String p_desc,String p_size , String a_price,  String p_sale,     byte[] image,String p_off) {
        this.pid = pid;
        this.p_name = p_name;
        this.a_price = a_price;
        this.o_price = o_price;
        this.p_sale = p_sale;
        this.p_size = p_size;
        this.p_desc = p_desc;
        this.p_off = p_off;
        this.p_category = p_category;
        this.image = image;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getA_price() {
        return a_price;
    }

    public void setA_price(String a_price) {
        this.a_price = a_price;
    }

    public String getO_price() {
        return o_price;
    }

    public void setO_price(String o_price) {
        this.o_price = o_price;
    }

    public String getQty() {
        return p_sale;
    }

    public void setQty(String qty) {
        this.p_sale = qty;
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public String getP_off() {
        return p_off;
    }

    public void setP_off(String p_off) {
        this.p_off = p_off;
    }

    public String getP_category() {
        return p_category;
    }

    public void setP_category(String p_category) {
        this.p_category = p_category;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



}
