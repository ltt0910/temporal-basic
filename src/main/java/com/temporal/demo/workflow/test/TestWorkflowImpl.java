package com.temporal.demo.workflow.test;

import com.temporal.demo.activity.test.TestActivity;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class TestWorkflowImpl implements TestWorkflow {
    @Override
    public String sayHi(String hi) {

        // Cấu hình retry
        final RetryOptions retryOptions = RetryOptions.newBuilder()
                .setInitialInterval(Duration.ofSeconds(5)) // Đợi 5 giây trước khi thử lại lần đầu tiên
                .setMaximumInterval(Duration.ofSeconds(60))
                .setMaximumAttempts(3)
                .build();

        final ActivityOptions activityOptions = ActivityOptions.newBuilder()
                .setRetryOptions(retryOptions)
                .setStartToCloseTimeout(Duration.ofSeconds(10))
                .build();

        final TestActivity testWorkflow = Workflow.newActivityStub(TestActivity.class, activityOptions);
        return testWorkflow.sayHi("Thành");
    }
}
