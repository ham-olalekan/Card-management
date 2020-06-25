package com.themint.cardmanagement.util.kafka;

import com.themint.cardmanagement.model.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaServiceImpl implements KafkaService {

    public static Logger log = LoggerFactory.getLogger(KafkaServiceImpl.class);

    private KafkaTemplate<String, Payload> kafkaTemplate;

    private String topic = "com.mintfintech.card_verified"; //TODO pass this as an environment variable

    @Autowired
    public KafkaServiceImpl(KafkaTemplate<String, Payload> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishMessage(Payload payload) {
        log.info("Preparing to dispatch message payload: [{}} via kafka messenger", payload);
        kafkaTemplate.send(topic, payload);
    }
}
