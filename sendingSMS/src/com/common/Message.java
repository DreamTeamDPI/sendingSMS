package com.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by SemmEs on 12.04.2016.
 */

@XmlRootElement
public class Message {

    String name;
    ArrayList<XmlSMS> listSms;

    public Message() {
    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<XmlSMS> getXmlSMSList() {
        return listSms;
    }
    @XmlElementWrapper(name = "listSms")
    @XmlElement(name = "xmlSMS")
    public void setXmlSMSList(ArrayList<XmlSMS> listSms) {
        this.listSms = listSms;
    }

    @Override
    public String toString() {
        return "Message{" +
                "listSms=" + listSms +
                ", name='" + name + '\'' +
                '}';
    }
}
