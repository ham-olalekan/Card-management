package com.themint.cardmanagement.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

@RedisHash("lookup_counter")
public class CardLookUpTracker {

    private static final long serialVersionUID = 3580752880525336671L;

    @Id
    private String Id; //bin

    private int count;

    @PersistenceConstructor
    public CardLookUpTracker() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
