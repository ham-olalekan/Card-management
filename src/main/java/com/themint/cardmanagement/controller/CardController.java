package com.themint.cardmanagement.controller;

import com.themint.cardmanagement.entity.Card;
import com.themint.cardmanagement.model.HitCountResponse;
import com.themint.cardmanagement.model.Payload;
import com.themint.cardmanagement.model.Response;
import com.themint.cardmanagement.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/card-scheme")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(final CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/verify/{cardNumber}")
    public ResponseEntity<Response> handleCardSchemeLookUp(@PathVariable("cardNumber") final String cardNumber) {
        Optional<Card> cardOptional = cardService.getCard(cardNumber);
        if (!cardOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(false, "", null));
        }
        Payload payload = cardOptional.get().getPayload();
        return ResponseEntity.status(HttpStatus.OK).body(new Response(true, "successful", payload));
    }

    @GetMapping("/stats")
    public ResponseEntity<?> handleCardHitCountFetching(@RequestParam(value = "start", defaultValue = "0") final int start,
                                                        @RequestParam(value = "limit", defaultValue = "5") final int limit) {

        PageRequest pageRequest = PageRequest.of(start, limit);
        Page<Card> hitCountTrackerPage = cardService.getCardHitCount(pageRequest);
        Map<String, Integer> cardToCount = new HashMap<>();

        hitCountTrackerPage.getContent().stream().forEach(card -> {
            cardToCount.put(card.getCardNumber(), card.getHitCount());
        });

        HitCountResponse response = new HitCountResponse(
                true,
                start,
                limit,
                hitCountTrackerPage.getNumberOfElements(),
                cardToCount
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
