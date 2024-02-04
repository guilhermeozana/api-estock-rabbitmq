package com.microservice.stockconsumer.exception;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class StockErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        String consumerQueue = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
        System.out.println(consumerQueue);

        String message = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
        System.out.println(message);

        System.out.println(t.getCause().getMessage());

        throw new AmqpRejectAndDontRequeueException("Do not return to queue");

    }
}
