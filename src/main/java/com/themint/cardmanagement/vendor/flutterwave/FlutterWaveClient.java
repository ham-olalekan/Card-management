package com.themint.cardmanagement.vendor.flutterwave;

import com.themint.cardmanagement.util.http.HttpService;
import com.themint.cardmanagement.vendor.paystack.PaystackClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlutterWaveClient {
    private static final Logger log = LoggerFactory.getLogger(PaystackClient.class);

    private final HttpService httpService;

    private final String API = "https://api.paystack.co/decision/bin/%s";//TODO pass this as an env
    private final String TOKEN = "sk_test_50afcb23d84100f7b9e9833be77e171cc0d2ec88";//TODO pass this as an env

    @Autowired
    public FlutterWaveClient(HttpService httpService) {
        this.httpService = httpService;
    }
}
