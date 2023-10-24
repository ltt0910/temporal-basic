package com.temporal.demo.activity.moneytranfer;

import com.temporal.demo.response.Response;
import com.temporal.demo.request.TransferRequest;

//  money-transfer-project-template-java-activity-implementation
public class AccountActivityImpl implements AccountActivity {

    @Override
    public String transfer(TransferRequest request) {
        return "SUCCESS";
    }

    @Override
    public String validate(TransferRequest request) throws InterruptedException {
        Thread.sleep(40*1000);
        return "SUCCESS";
    }

    @Override
    public String deposit(TransferRequest request) {

        return "SUCCESS";
    }
}
