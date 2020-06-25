package com.themint.cardmanagement.entity;

import com.themint.cardmanagement.model.CardStatus;
import com.themint.cardmanagement.model.CardType;
import com.themint.cardmanagement.model.Payload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Card implements Serializable {

    public final long serialVersionUID = -7594446772514397725L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "card_scheme", nullable = false)
    private String scheme;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", length = 10)
    private CardType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_status", length = 10, nullable = false)
    private CardStatus status;

    @Column(name = "createdDate")
    private Instant createdDate;

    @Column(name = "bank", nullable = false)
    private String bank;

    @Column(name = "hit_count")
    private int hitCount;

    @PrePersist
    private void setDefault() {
        this.createdDate = Instant.now();
        this.hitCount = 1;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getType() {
        return Objects.isNull(this.type) ? "N/A" : this.type.name();
    }

    public Payload getPayload() {
        return new Payload(this.scheme, getType(), this.bank);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public String toString() {
        return "Card{" +
                "serialVersionUID=" + serialVersionUID +
                ", Id=" + Id +
                ", type=" + type +
                ", createdDate=" + createdDate +
                ", bank='" + bank + '\'' +
                '}';
    }
}
