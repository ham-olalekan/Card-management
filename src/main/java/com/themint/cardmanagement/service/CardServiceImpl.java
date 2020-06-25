package com.themint.cardmanagement.service;

import com.themint.cardmanagement.entity.Card;
import com.themint.cardmanagement.repository.CardRepository;
import com.themint.cardmanagement.vendor.paystack.PaystackClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private static Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository repository;
    private final PaystackClient client;

    @Autowired
    public CardServiceImpl(CardRepository repository,
                           PaystackClient client) {
        this.repository = repository;
        this.client = client;
    }

    @Override
    public Optional<Card> getCard(final String cardNumber) {
        Optional<Card> cardDetails = Optional.ofNullable(repository.findByCardNumber(cardNumber));
        if (cardDetails.isPresent())
            return cardDetails;
        Optional<Card> data;
        data = getCardDetailsFromVendor(cardNumber);
        return data.map(repository::save);
    }

    private Optional<Card> getCardDetailsFromVendor(final String cardNumber) {
        return Optional.ofNullable(client.lookUpCard(cardNumber));
    }
}
