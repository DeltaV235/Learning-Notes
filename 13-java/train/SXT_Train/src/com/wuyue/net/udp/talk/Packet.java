package com.wuyue.net.udp.talk;

import java.io.Serializable;
import java.util.Date;

public class Packet implements Serializable {
    private String senderName;
    private Date sendDate;
    private String msg;

    public Packet(String senderName) {
        this.senderName = senderName;
    }

    public Packet(String senderName, String msg) {
        this.senderName = senderName;
        this.msg = msg;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
