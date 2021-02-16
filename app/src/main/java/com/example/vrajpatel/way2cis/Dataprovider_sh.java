package com.example.vrajpatel.way2cis;

public class Dataprovider_sh {
    public Dataprovider_sh(String sale_code,String sale_name,String sale_qty,String sale_total,String sale_date)
    {
        this.setSale_code(sale_code);
        this.setSale_name(sale_name);
        this.setSale_qty(sale_qty);
        this.setSale_total(sale_total);
        this.setSale_date(sale_date);
    }
    String sale_code,sale_name,sale_qty,sale_total,sale_date;

    public String getSale_code() {
        return sale_code;
    }

    public void setSale_code(String sale_code) {
        this.sale_code = sale_code;
    }

    public String getSale_name() {
        return sale_name;
    }

    public void setSale_name(String sale_name) {
        this.sale_name = sale_name;
    }

    public String getSale_qty() {
        return sale_qty;
    }

    public void setSale_qty(String sale_qty) {
        this.sale_qty = sale_qty;
    }

    public String getSale_total() {
        return sale_total;
    }

    public void setSale_total(String sale_total) {
        this.sale_total = sale_total;
    }

    public String getSale_date() {
        return sale_date;
    }

    public void setSale_date(String sale_date) {
        this.sale_date = sale_date;
    }
}
