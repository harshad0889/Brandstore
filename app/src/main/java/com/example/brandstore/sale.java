package com.example.brandstore;

public class sale {
    private int sid;
    private String s_title;
    private String s_desc;
    private String prod_name;
    private String sdate;
    private String edate;
    private byte[] image;


    public sale(int sid, String s_title, String s_desc,  String sdate, String edate, byte[] image) {
        this.sid = sid;
        this.s_title = s_title;
        this.s_desc = s_desc;

        this.sdate = sdate;
        this.edate = edate;
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

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
