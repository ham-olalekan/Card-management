package com.themint.cardmanagement.model;

public enum CardType {

    DEBIT,
    CREDIT;

    public boolean isDebit() {
        return this == DEBIT;
    }

    public boolean isCredit() {
        return this == CREDIT;
    }

    public static CardType getType(String name) {
        for (CardType cardType : CardType.values()) {
            if (cardType.name().equalsIgnoreCase(name)) {
                return cardType;
            }
        }
        return null;
    }
}
