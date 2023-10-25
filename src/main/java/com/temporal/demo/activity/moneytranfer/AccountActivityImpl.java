package com.temporal.demo.activity.moneytranfer;

import com.temporal.demo.request.TransferRequest;

import static com.temporal.demo.common.Constants.STATUS.SUCCESS;

//  money-transfer-project-template-java-activity-implementation
public class AccountActivityImpl implements AccountActivity {

    @Override
    public String transfer(TransferRequest request) {
        return SUCCESS;
    }

    @Override
    public String validate(TransferRequest request) throws InterruptedException {
//        Thread.sleep(30L * 1000);
        return SUCCESS;
    }

    @Override
    public String deposit(TransferRequest request) {

        return SUCCESS;
    }
}
