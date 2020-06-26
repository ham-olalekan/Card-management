package com.themint.cardmanagement.service;

import com.themint.cardmanagement.entity.Card;
import com.themint.cardmanagement.entity.CardLookUpTracker;
import com.themint.cardmanagement.event.BinlookupEvent;
import com.themint.cardmanagement.repository.CardLookUpTrackerRepository;
import com.themint.cardmanagement.repository.CardRepository;
import com.themint.cardmanagement.vendor.CardVendor;
import com.themint.cardmanagement.vendor.paystack.PaystackClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private static Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository repository;
    private final CardVendor client;
    private final CardLookUpTrackerRepository trackerRepository;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public CardServiceImpl(CardRepository repository,
                           CardVendor client,
                           CardLookUpTrackerRepository trackerRepository,
                           ApplicationEventPublisher publisher) {
        this.repository = repository;
        this.client = client;
        this.publisher = publisher;
        this.trackerRepository = trackerRepository;
    }

    /**
     * this method tries to find the card in the local db
     * if not present then proceed to call vendor
     *
     * @param cardNumber
     * @return Card
     */
    @Override
    public Optional<Card> getCard(final String cardNumber) {
        Optional<Card> cardDetails = Optional.ofNullable(repository.findByCardNumber(cardNumber));
        if (cardDetails.isPresent()) {
            publisher.publishEvent(new BinlookupEvent(this, cardDetails.get()));
            return cardDetails;
        }
        Optional<Card> data;
        data = getCardDetailsFromVendor(cardNumber);
        publisher.publishEvent(new BinlookupEvent(this, data.get()));
        return data.map(repository::save);
    }

    /**
     * makes request to vendor to get card info
     *
     * @param cardNumber
     * @return
     */
    private Optional<Card> getCardDetailsFromVendor(final String cardNumber) {
        return Optional.ofNullable(client.lookUpCard(cardNumber));
    }

    /**
     * Fetches the card-number along
     * with hit count
     *
     * @param pageRequest
     *
     * @return
     */
    @Override
    public Page<Card> getCardHitCount(PageRequest pageRequest) {
        Page<Card> lookUpTrackers = repository.findAll(pageRequest);
        return lookUpTrackers;
    }
}
