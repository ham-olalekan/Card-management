package com.themint.cardmanagement.vendor.paystack.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardBinResponse implements Serializable {
    private final long serialVersionUID = 9212103011127895399L;

    private boolean status;

    private String message;

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CardBinResponse{" +
                "serialVersionUID=" + serialVersionUID +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
