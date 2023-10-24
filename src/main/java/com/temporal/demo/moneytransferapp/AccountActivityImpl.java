package com.temporal.demo.moneytransferapp;

import com.temporal.demo.response.Response;
import com.temporal.demo.request.TransferRequest;

//  money-transfer-project-template-java-activity-implementation
public class AccountActivityImpl implements AccountActivity {

    @Override
    public Response transfer(TransferRequest request) {

        return Response.builder()
                .code("ERROR").message("Faild")
                .data(null)
                .build();
    }

    @Override
    public Response validate(TransferRequest request) {
        return Response.builder()
                .code("ERROR").message("Faild")
                .data(null)
                .build();
    }

    @Override
    public Response deposit(TransferRequest request) {

        return Response.builder()
                .code("ERROR").message("Faild")
                .data(null)
                .build();
    }
}
