package com.microservice.stockprice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    public void sendMessage(String queueName, Object message){
        try {
            String messageJson = objectMapper.writeValueAsString(message);

            this.rabbitTemplate.convertAndSend(queueName, messageJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
