package com.temporal.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
    private String accountFrom;
    private String accountTo;
    private double amount;

}
