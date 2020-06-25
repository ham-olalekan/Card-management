package com.themint.cardmanagement.model;

import java.io.Serializable;

public class Payload implements Serializable {

    private long serialVersionUID = -8040612880648325300L;

    private String scheme;

    private String type;

    private String bank;

    public Payload() {
    }

    public Payload(String scheme,
                   String type,
                   String bank) {
        this.scheme = scheme;
        this.type = type;
        this.bank = bank;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "scheme='" + scheme + '\'' +
                ", type='" + type + '\'' +
                ", bank='" + bank + '\'' +
                '}';
    }
}
