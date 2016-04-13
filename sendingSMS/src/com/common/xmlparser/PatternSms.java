package com.common.xmlparser;

/**
 * Created by SemmEs on 11.04.2016.
 */
public class PatternSms {

    private String text;
    private String phone;

    public PatternSms() {
    }

    public PatternSms(String text, String phone) {
        this.text = text;
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PatternSms{" +
                "text='" + text + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
