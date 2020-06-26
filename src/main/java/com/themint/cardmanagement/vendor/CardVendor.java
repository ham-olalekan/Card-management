package com.themint.cardmanagement.vendor;

import com.themint.cardmanagement.entity.Card;

public interface CardVendor {
    Card lookUpCard(final String cardNumber);
}
