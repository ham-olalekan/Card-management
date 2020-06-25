package com.themint.cardmanagement.event;

import com.themint.cardmanagement.entity.Card;
import com.themint.cardmanagement.repository.CardLookUpTrackerRepository;
import com.themint.cardmanagement.repository.CardRepository;
import com.themint.cardmanagement.util.kafka.KafkaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.ForkJoinPool;

@Component
public class BinLookupEventHandler {

    private Logger logger = LoggerFactory.getLogger(BinLookupEventHandler.class);

    private CardLookUpTrackerRepository cardLookUpTrackerRepository;
    private CardRepository cardRepository;
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
        ForkJoinPool.commonPool().execute(()-> {
            Card card = event.getCard();
            updateCounter(card);
            pushMessageToKafka(card);
        });
    }

    /**
     * checks to see if card exists
     * if it exists increment count
     * else start count from one
     *
     * @param card
     */
    private void updateCounter(Card card) {
        int countValue = card.getHitCount();
        card.setHitCount(countValue++);
        cardRepository.save(card);
    }

    //using redis is an overkill. ** terminate **
    /**
    private void updateCounterInRedis(Card card) {
        logger.info("Updating look-up counter in redis :::: for card: [{}]", card);
        Optional<CardLookUpTracker> tracker = cardLookUpTrackerRepository.findById(card.getCardNumber());
        Map<String, Integer> updatedHitCount = new HashMap<>();
        tracker.ifPresent(cardTrackInfo -> {
            Map<String, Integer> currenctTrackerInfo = cardTrackInfo.getHitCount();
            String cardNumber = cardTrackInfo.getId();
            int currentHitCount = currenctTrackerInfo.get(cardNumber) + 1;
            System.out.println("current-number => "+ currenctTrackerInfo);
            updatedHitCount.put(cardNumber, currentHitCount);
            cardTrackInfo.setHitCount(updatedHitCount);
            cardLookUpTrackerRepository.delete(cardTrackInfo);
            CardLookUpTracker updated = cardLookUpTrackerRepository.save(cardTrackInfo);
            System.out.println("updated to => "+ updated.getHitCount().get(cardNumber));
            logger.info("Card lookup info successful updated in redis for [{}]", updated);
        });

        CardLookUpTracker newTrackerInfo = new CardLookUpTracker();
        updatedHitCount.put(card.getCardNumber(), 1);
        newTrackerInfo.setId(card.getCardNumber());
        newTrackerInfo.setHitCount(updatedHitCount);
        cardLookUpTrackerRepository.save(newTrackerInfo);
    }
    */

    /**
     * publishes to kafka broker
     *
     * @param card
     */
    private void pushMessageToKafka(Card card) {
        logger.info("Attempting to publish via kafka for card: [{}]", card.getPayload());
        try {
            KafkaService.publishMessage(card.getPayload());
            logger.info("Successfully published via kafka for card: [{}]", card);
        } catch (Exception ex) {
            logger.error("Error occured for card: [{}] while trying to publish via kafka [{}]", card, ex.getMessage());
            ex.printStackTrace();
        }
    }
}
