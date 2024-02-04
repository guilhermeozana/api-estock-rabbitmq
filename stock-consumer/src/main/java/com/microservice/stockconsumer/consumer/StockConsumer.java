package com.microservice.stockconsumer.consumer;

import org.librabbitmq.dto.StockDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.librabbitmq.constants.RabbitMQConstants;
import org.springframework.stereotype.Component;

@Component
public class StockConsumer {

    @RabbitListener(queues = RabbitMQConstants.STOCK_QUEUE)
    private void consumer(StockDTO stockDTO){
        System.out.println(stockDTO.productCode);
        System.out.println(stockDTO.quantity);

    }
}
