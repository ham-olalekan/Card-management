package com.themint.cardmanagement.vendor.paystack;

import com.themint.cardmanagement.common.exception.BINLookupException;
import com.themint.cardmanagement.entity.Card;
import com.themint.cardmanagement.util.http.HttpService;
import com.themint.cardmanagement.vendor.paystack.Response.CardBinResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PaystackClient {

    private static final Logger log = LoggerFactory.getLogger(PaystackClient.class);

    private final HttpService httpService;

    private final String API = "https://api.paystack.co/decision/bin/%s";//TODO pass this as an env
    private final String TOKEN = "sk_test_50afcb23d84100f7b9e9833be77e171cc0d2ec88";//TODO pass this as an env

    @Autowired
    public PaystackClient(final HttpService httpService) {
        this.httpService = httpService;
    }

    /**
     * makes a GET request to paystack API
     *
     * @return an instance of Card entity
     */
    public Card lookUpCard(final String cardNumber) throws BINLookupException {
        final UriComponentsBuilder url =
                UriComponentsBuilder.fromUriString(String.format(API, cardNumber));
        final HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.add("Authorization", String.format("Bearer %s", TOKEN));
        try {
            ResponseEntity<CardBinResponse> responseEntity = httpService.get(url,
                    requestHeaders, CardBinResponse.class);
            return responseEntity.getBody().getData().toCardInfo();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Paystack BIN Request Error", ex);
            throw new BINLookupException(ex.getMessage());
        }
    }
}
