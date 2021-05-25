package com.example.brandstore;

public class cart {



    private int pid;
    private String cart_id;
    private String p_name;
    private String a_price;
    private String p_desc;
    private String p_size;
    private String p_qty;
    private String uid;
    private String off;
    private String total_amount;
    private byte[] image;

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
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

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public String getP_size() {
        return p_size;
    }

    public void setP_size(String p_size) {
        this.p_size = p_size;
    }

    public String getP_qty() {
        return p_qty;
    }

    public void setP_qty(String p_qty) {
        this.p_qty = p_qty;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public cart(int pid, String cart_id, String p_name, String a_price, String p_desc, String p_qty, String uid, byte[] image,String p_size,String off) {
        this.pid = pid;
        this.p_name = p_name;
        this.a_price = a_price;
        this.p_desc = p_desc;
        this.cart_id = cart_id;
        this.p_qty = p_qty;
        this.uid = uid;
        this.total_amount = total_amount;
        this.image = image;
        this.p_size = p_size;
        this.off = off;
    }
}
