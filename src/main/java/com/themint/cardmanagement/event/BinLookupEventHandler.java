package com.themint.cardmanagement.event;

import com.themint.cardmanagement.entity.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BinLookupEventHandler {

    private Logger logger = LoggerFactory.getLogger(BinLookupEventHandler.class);

    @EventListener
    private void handleBinLookupEvent(final BinlookupEvent event){
        Card card = event.getCard();
        //save details to redis
    }
}
