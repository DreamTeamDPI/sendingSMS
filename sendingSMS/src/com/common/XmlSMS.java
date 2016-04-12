package com.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by SemmEs on 12.04.2016.
 */
@XmlRootElement
public class XmlSMS {

    String phone;
    String text;

    public XmlSMS() {
    }

    public XmlSMS(String phone, String text) {
        this.phone = phone;
        this.text = text;
    }

    public String getPhone() {
        return phone;
    }
    @XmlElement
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }
    @XmlElement
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "XmlSMS{" +
                "phone='" + phone + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
