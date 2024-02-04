package com.microservice.stockprice.connections;

import jakarta.annotation.PostConstruct;
import org.librabbitmq.constants.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
    private static final String EXCHANGE_NAME = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection (AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName){
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding binding(Queue queue, DirectExchange directExchange){
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, directExchange.getName(), queue.getName(), null);
    }

    //está função é executada assim que nossa classe é instanciada pelo Spring, devido a anotação @Component
    @PostConstruct
    private void construct(){
        Queue stockQueue = this.queue(RabbitMQConstants.STOCK_QUEUE);

        DirectExchange exchange = this.directExchange();

        Binding stockBinding = this.binding(stockQueue, exchange);

        //Criando as filas no RabbitMQ
        this.amqpAdmin.declareQueue(stockQueue);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(stockBinding);
    }
}
