package com.temporal.demo.controller;

import com.temporal.demo.exception.BusinessException;
import com.temporal.demo.request.TransferRequest;
import com.temporal.demo.service.MoneyTransferService;
import com.temporal.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TestController {

    final TestService testService;

    @PostMapping("/hi")
    public ResponseEntity<?> transfer(@RequestParam("name") String request) throws BusinessException {
        return ResponseEntity.of(Optional.of(testService.sayHi(request)));
    }

}
