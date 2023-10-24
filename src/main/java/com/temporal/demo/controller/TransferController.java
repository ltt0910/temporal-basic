package com.temporal.demo.controller;

import com.temporal.demo.request.TransferRequest;
import com.temporal.demo.service.InitiateMoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class TransferController {

    @Autowired
    private InitiateMoneyTransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest request) throws Exception {
        return ResponseEntity.of(Optional.of(transferService.transfer(request)));
    }

}
