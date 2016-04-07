package com.common.sms;

/**
 * Created by SemmEs on 03.04.2016.
 */
public class SMS {

    private int id;
    private String phone;
    private String text;
    private String data;
    private int status;
    private double price;

    public SMS() {
    }

    public SMS(int id,String phone, String text, String data, int status, double price) {
        this.phone = phone;
        this.text = text;
        this.data = data;
        this.status = status;
        this.price = price;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SMS{" +
                "phone='" + phone + '\'' +
                ", text='" + text + '\'' +
                ", data=" + data +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
}
