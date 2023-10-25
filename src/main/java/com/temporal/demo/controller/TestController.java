package com.temporal.demo.controller;

import com.temporal.demo.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TestController {

    final TestService testService;

    @PostMapping("/hi")
    public ResponseEntity<?> transfer(@RequestParam("name") String request)  {
        return ResponseEntity.of(Optional.of(testService.sayHi(request)));
    }

}
