package com.temporal.demo.workflow.moneytranfer;

import com.temporal.demo.activity.moneytranfer.AccountActivity;
import com.temporal.demo.common.Constants;
import com.temporal.demo.exception.BusinessException;
import com.temporal.demo.request.TransferRequest;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

// money-transfer-project-template-java-workflow-implementation
@Slf4j
public class MoneyTransferWorkflowImpl implements MoneyTransferWorkflow {
    // RetryOptions specify how to automatically handle retries when Activities fail.

    // The transfer method is the entry point to the Workflow.
    // Activity method executions can be orchestrated here or from within other Activity methods.
    @Override
    public String transfer(TransferRequest transferRequest) throws BusinessException {

        // RetryOptions là đối tượng thử lại khi method có lỗi
        final RetryOptions retryoptions = RetryOptions.newBuilder()
                .setInitialInterval(Duration.ofSeconds(1))
                .setMaximumInterval(Duration.ofSeconds(500))
                .setBackoffCoefficient(2)
                .setMaximumAttempts(3)
                .build();

        final ActivityOptions options = ActivityOptions.newBuilder()
                // Timeout options specify when to automatically timeout Activities if the process is taking too long.
                .setStartToCloseTimeout(Duration.ofSeconds(10))
                // Retry when Activities error or timeout
                .setRetryOptions(retryoptions)
                .build();

        // ActivityStubs enable calls to methods as if the Activity object is local, but actually perform an RPC.
        final AccountActivity account = Workflow.newActivityStub(AccountActivity.class, options);


        String valid = null;
        try {
            valid = account.validate(transferRequest);
        } catch (InterruptedException ie) {
            log.error("Valid fail with InterruptedException  {}", ie.getMessage());
            throw new BusinessException("Valid Time out");
        }
        if (valid.equals(Constants.STATUS.ERROR)) {
            return "Valid fail";
        }
        String transfer = account.transfer(transferRequest);
        if (transfer.equals(Constants.STATUS.ERROR)) {
            return "transfer fail";
        }
        String deposit = account.deposit(transferRequest);
        if (deposit.equals(Constants.STATUS.ERROR)) {
            return "deposit fail";
        }

        return "Transaction successfully!";

    }
}
