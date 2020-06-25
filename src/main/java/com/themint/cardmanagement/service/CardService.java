package com.themint.cardmanagement.service;

import com.themint.cardmanagement.entity.Card;

import java.util.Optional;

public interface CardService {
    Optional<Card> getCard(final String cardNumber);
}
