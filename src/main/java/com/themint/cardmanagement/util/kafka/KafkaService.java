package com.themint.cardmanagement.util.kafka;

import com.themint.cardmanagement.model.Payload;

public interface KafkaService {
    void publishMessage(Payload payload);
}
