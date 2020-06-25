package com.themint.cardmanagement.event;

import com.themint.cardmanagement.entity.Card;
import com.themint.cardmanagement.entity.CardLookUpTracker;
import com.themint.cardmanagement.repository.CardLookUpTrackerRepository;
import com.themint.cardmanagement.util.kafka.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BinLookupEventHandler {

    private Logger logger = LoggerFactory.getLogger(BinLookupEventHandler.class);

    private CardLookUpTrackerRepository cardLookUpTrackerRepository;
    private KafkaService KafkaService;

    @Autowired
    public BinLookupEventHandler(CardLookUpTrackerRepository cardLookUpTrackerRepository,
                                 KafkaService kafkaService) {
        this.cardLookUpTrackerRepository = cardLookUpTrackerRepository;
        this.KafkaService = kafkaService;
    }

    @EventListener
    @Async
    private void handleBinLookupEvent(final BinlookupEvent event) {
        Card card = event.getCard();
        updateCounterInRedis(card);
        pushMessageToKafka(card);
    }

    /**
     * finds card lookup info in redis
     * then increment it by one
     */
    private void updateCounterInRedis(Card card) {
        logger.info("Updating look-up counter in redis :::: for card: [{}]", card);
        Optional<CardLookUpTracker> tracker = cardLookUpTrackerRepository.findById(card.getCardNumber());

        tracker.ifPresent(cardTrackInfo -> {
            int count = cardTrackInfo.getCount();
            cardTrackInfo.setCount((count + 1));
            logger.info("Card lookup info successful updated in redis for [{}]", card);
        });

        CardLookUpTracker newTrackerInfo = new CardLookUpTracker();
        newTrackerInfo.setCount(1);
        newTrackerInfo.setId(card.getCardNumber());
    }

    private void pushMessageToKafka(Card card) {

    }
}
