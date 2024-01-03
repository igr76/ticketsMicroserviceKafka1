package com.example.stmlabs.kafka;

import com.example.stmlabs.model.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class KafkaProducer {
    private final String topic;
    private final KafkaTemplate<String, Ticket> kafkaTemplate;

    public KafkaProducer(@Value("${application.kafka.topic}")String topic, KafkaTemplate<String, Ticket> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Ticket value) {
        try {
            log.info("value:{}", value);
            kafkaTemplate.send(topic, value)
                    .whenComplete(
                            (result, ex) -> {
                                if (ex == null) {
                                    log.info(
                                            "message id:{} was sent, offset:{}",
                                            value.getId(),
                                            result.getRecordMetadata().offset());
                                } else {
                                    log.error("message id:{} was not sent", value.getId(), ex);
                                }
                            });
        } catch (Exception ex) {
            log.error("send error, value:{}", value, ex);
        }
    }
}
