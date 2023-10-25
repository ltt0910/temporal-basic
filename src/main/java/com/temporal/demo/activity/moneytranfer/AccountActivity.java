package com.temporal.demo.activity.moneytranfer;

//  money-transfer-project-template-java-activity-interface

import com.temporal.demo.request.TransferRequest;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AccountActivity {

    @ActivityMethod
    String validate(TransferRequest request) throws InterruptedException;

    @ActivityMethod
    String deposit(TransferRequest request);

    @ActivityMethod
    String transfer(TransferRequest request);
}
