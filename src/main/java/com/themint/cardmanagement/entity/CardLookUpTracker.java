package com.themint.cardmanagement.entity;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

import java.util.Map;

@RedisHash("lookup_counter")
public class CardLookUpTracker {

    private static final long serialVersionUID = 3580752880525336671L;

    @Id
    private String Id; //bin

    private Map<String, Integer> hitCount;

    @PersistenceConstructor
    public CardLookUpTracker() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Map<String, Integer> getHitCount() {
        return hitCount;
    }

    public void setHitCount(Map<String, Integer> hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public String toString() {
        return "CardLookUpTracker{" +
                "Id='" + Id + '\'' +
                ", hitCount=" + hitCount +
                '}';
    }
}
