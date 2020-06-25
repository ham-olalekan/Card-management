package com.themint.cardmanagement.model;

import java.io.Serializable;

public class Response<T> implements Serializable {

    private final long serialVersionUID = -7035408621811224565L;

    private boolean success;

    private String message;

    private T payload;

    public Response() {
    }

    public Response(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
