package com.temporal.demo.workflow.moneytranfer;

import com.temporal.demo.activity.moneytranfer.AccountActivity;
import com.temporal.demo.common.Constants;
import com.temporal.demo.exception.BusinessException;
import com.temporal.demo.request.TransferRequest;
import com.temporal.demo.response.Response;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

// money-transfer-project-template-java-workflow-implementation
public class MoneyTransferWorkflowImpl implements MoneyTransferWorkflow {
    // RetryOptions specify how to automatically handle retries when Activities fail.

    // The transfer method is the entry point to the Workflow.
    // Activity method executions can be orchestrated here or from within other Activity methods.
    @Override
    public String transfer(TransferRequest transferRequest) throws BusinessException, InterruptedException {

        // RetryOptions là đối tượng thử lại khi method có lỗi
        final RetryOptions retryoptions = RetryOptions.newBuilder()
                .setInitialInterval(Duration.ofSeconds(1))
                .setMaximumInterval(Duration.ofSeconds(100))
                .setBackoffCoefficient(2)
                .setMaximumAttempts(5)
                .build();

        final ActivityOptions options = ActivityOptions.newBuilder()
                // Timeout options specify when to automatically timeout Activities if the process is taking too long.
                .setStartToCloseTimeout(Duration.ofSeconds(30))
                .setRetryOptions(retryoptions)
                // Optionally provide customized RetryOptions.
                // Temporal retries failures by default, this is simply an example.
                .build();

        // ActivityStubs enable calls to methods as if the Activity object is local, but actually perform an RPC.
        final AccountActivity account = Workflow.newActivityStub(AccountActivity.class, options);


        String valid = null;
        valid = account.validate(transferRequest);
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
