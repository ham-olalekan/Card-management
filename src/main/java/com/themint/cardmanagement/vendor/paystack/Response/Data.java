package com.themint.cardmanagement.vendor.paystack.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.themint.cardmanagement.entity.Card;
import com.themint.cardmanagement.model.CardStatus;
import com.themint.cardmanagement.model.CardType;

import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data implements Serializable {
    private final long serialVersionUID = 9212103011127895399L;

    @JsonProperty(value = "bin")
    private String bin;

    @JsonProperty(value = "brand")
    private String brand;

    @JsonProperty(value = "country_name")
    private String country;

    @JsonProperty(value = "card_type")
    private String cardType;

    @JsonProperty(value = "bank")
    private String bank;

    public Data() {
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Card toCardInfo() {
        toString();
        Card card = new Card();
        if (Objects.nonNull(brand)) {
            card.setScheme(this.brand);
            card.setBank(this.bank);
            card.setCardNumber(this.bin);
            card.setType(CardType.getType(this.cardType));
            card.setStatus(CardStatus.VALID);
        } else {
            card.setScheme("N/A");
            card.setBank("N/A");
            card.setCardNumber("N/A");
            card.setCardNumber(this.bin);
            card.setStatus(CardStatus.INVALID);
        }
        return card;
    }

    @Override
    public String toString() {
        return "Data{" +
                "serialVersionUID=" + serialVersionUID +
                ", bin='" + bin + '\'' +
                ", brand='" + brand + '\'' +
                ", country='" + country + '\'' +
                ", cardType='" + cardType + '\'' +
                ", bank='" + bank + '\'' +
                '}';
    }
}

