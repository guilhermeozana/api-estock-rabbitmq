package com.microservice.stockprice.controller;

import com.microservice.stockprice.service.RabbitMQService;
import lombok.RequiredArgsConstructor;
import org.librabbitmq.constants.RabbitMQConstants;
import org.librabbitmq.dto.StockDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "stocks")
@RequiredArgsConstructor
public class StockController {
    private final RabbitMQService rabbitMQService;
    @PutMapping
    private ResponseEntity alterStock(@RequestBody StockDTO stockDTO){
        rabbitMQService.sendMessage(RabbitMQConstants.STOCK_QUEUE, stockDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
