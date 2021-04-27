package com.example.brandstore;

public class order {




    public order(int order_id) {
        this.order_id = order_id;
    }

    private int order_id;
    private String cart_id;
    private String p_name;
    private String a_price;
    private String p_desc;
    private String p_size;
    private String p_qty;
    private String uid;



    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

}
