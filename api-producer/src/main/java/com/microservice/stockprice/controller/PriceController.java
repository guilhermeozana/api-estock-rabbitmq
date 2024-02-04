package com.microservice.stockprice.controller;

import com.microservice.stockprice.service.RabbitMQService;
import lombok.RequiredArgsConstructor;
import org.librabbitmq.constants.RabbitMQConstants;
import org.librabbitmq.dto.PriceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "prices")
@RequiredArgsConstructor
public class PriceController {
    private final RabbitMQService rabbitMQService;
    @PutMapping
    private ResponseEntity alterPrice(@RequestBody PriceDTO priceDTO){
        rabbitMQService.sendMessage(RabbitMQConstants.PRICE_QUEUE, priceDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
