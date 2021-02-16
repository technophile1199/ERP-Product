package com.example.vrajpatel.way2cis;

public class Dataprovider_ph {
    public Dataprovider_ph(String pur_code,String pur_name,String pur_qty,String pur_total,String pur_date)
    {
        this.setPur_code(pur_code);
        this.setPur_name(pur_name);
        this.setPur_qty(pur_qty);
        this.setPur_total(pur_total);
        this.setPur_date(pur_date);
    }
    String pur_code,pur_name,pur_qty,pur_total,pur_date;

    public String getPur_name() {
        return pur_name;
    }

    public void setPur_name(String pur_name) {
        this.pur_name = pur_name;
    }

    public String getPur_date() {
        return pur_date;
    }

    public void setPur_date(String pur_date) {
        this.pur_date = pur_date;
    }

    public String getPur_code() {
        return pur_code;
    }

    public void setPur_code(String pur_code) {
        this.pur_code = pur_code;
    }

    public String getPur_qty() {
        return pur_qty;
    }

    public void setPur_qty(String pur_qty) {
        this.pur_qty = pur_qty;
    }

    public String getPur_total() {
        return pur_total;
    }

    public void setPur_total(String pur_total) {
        this.pur_total = pur_total;
    }
}
