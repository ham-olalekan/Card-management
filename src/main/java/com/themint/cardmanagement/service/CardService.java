package com.themint.cardmanagement.service;

import com.themint.cardmanagement.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface CardService {
    Optional<Card> getCard(final String cardNumber);
    Page<Card> getCardHitCount(PageRequest pageRequest);
}
