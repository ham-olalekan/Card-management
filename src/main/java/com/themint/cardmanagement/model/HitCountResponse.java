package com.themint.cardmanagement.model;

import java.io.Serializable;
import java.util.Map;

public class HitCountResponse implements Serializable {

    private long serialVersionUID = 8255853241981100169L;

    private boolean success;

    private int start;

    private int limit;

    private int size;

    private Map<String, Integer> payload;

    public HitCountResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<String, Integer> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Integer> payload) {
        this.payload = payload;
    }

    public HitCountResponse(boolean success,
                            int start,
                            int limit,
                            int size,
                            Map<String, Integer> payload) {
        this.success = success;
        this.start = start;
        this.limit = limit;
        this.size = size;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "HitCountResponse{" +
                "success=" + success +
                ", start=" + start +
                ", limit=" + limit +
                ", size=" + size +
                ", payload=" + payload +
                '}';
    }
}
