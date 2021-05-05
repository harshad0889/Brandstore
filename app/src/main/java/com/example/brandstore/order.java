package com.example.brandstore;

public class order {
    private int order_id;
    private int uid;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDelivery_amt() {
        return delivery_amt;
    }

    public void setDelivery_amt(String delivery_amt) {
        this.delivery_amt = delivery_amt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPay_mode() {
        return pay_mode;
    }

    public void setPay_mode(String pay_mode) {
        this.pay_mode = pay_mode;
    }

    public String getO_date() {
        return o_date;
    }

    public void setO_date(String o_date) {
        this.o_date = o_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getCart_psize() {
        return cart_psize;
    }

    public void setCart_psize(String cart_psize) {
        this.cart_psize = cart_psize;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_cat() {
        return p_cat;
    }

    public void setP_cat(String p_cat) {
        this.p_cat = p_cat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public order(int order_id, int uid, String delivery_amt, String status, String pay_mode, String o_date, String username, String phone, String address, String pin, int cart_id, int pid, int qty, String cart_psize, String p_name, String p_cat, int price, byte[] image, int total) {
        this.order_id = order_id;
        this.uid = uid;
        this.delivery_amt = delivery_amt;
        this.status = status;
        this.pay_mode = pay_mode;
        this.o_date = o_date;
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.pin = pin;
        this.cart_id = cart_id;
        this.pid = pid;
        this.qty = qty;
        this.cart_psize = cart_psize;
        this.p_name = p_name;
        this.p_cat = p_cat;
        this.price = price;
        this.image = image;
        this.total = total;
    }

    private String delivery_amt;
    private String status;
    private String pay_mode;
    private String o_date;
    private String username;
    private String phone;
    private String address;
    private String pin;
    private int cart_id;
    private int pid;
    private int qty;
    private String cart_psize;
    private String p_name;
    private String p_cat;
    private int price;
    private byte[] image;
    private int total;





}
