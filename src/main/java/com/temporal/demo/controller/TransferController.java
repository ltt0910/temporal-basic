package com.temporal.demo.controller;

import com.temporal.demo.exception.BusinessException;
import com.temporal.demo.request.TransferRequest;
import com.temporal.demo.service.MoneyTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TransferController {

    final MoneyTransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest request) throws BusinessException {
        return ResponseEntity.of(Optional.of(transferService.transfer(request)));
    }

}
