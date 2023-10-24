package com.temporal.demo.moneytransferapp;

//  money-transfer-project-template-java-activity-interface

import com.temporal.demo.Response;
import com.temporal.demo.request.TransferRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AccountActivity {

    @ActivityMethod
    Response validate(TransferRequest request);

    @ActivityMethod
    Response deposit(TransferRequest request);

    @ActivityMethod
    Response transfer(TransferRequest request);
}
