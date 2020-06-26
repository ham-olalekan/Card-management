package com.themint.cardmanagement.event;

import com.themint.cardmanagement.entity.Card;
import org.springframework.context.ApplicationEvent;


public class BinlookupEvent extends ApplicationEvent {

    private static final long serialVersionUID = 4598963240219031623L;

    private final Card card;

    public BinlookupEvent(Object source, Card card) {
        super(source);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    @Override
    public String toString() {
        return "BinlookupEvent{" +
                "card=" + card +
                '}';
    }
}
