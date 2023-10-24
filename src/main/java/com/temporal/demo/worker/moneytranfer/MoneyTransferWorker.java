package com.temporal.demo.worker.moneytranfer;

import com.temporal.demo.activity.moneytranfer.AccountActivityImpl;
import com.temporal.demo.common.Function;
import com.temporal.demo.config.WorkflowClientConfig;
import com.temporal.demo.workflow.moneytranfer.MoneyTransferWorkflow;
import com.temporal.demo.workflow.moneytranfer.MoneyTransferWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.*;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

// money-transfer-project-template-java-worker
@Component
@RequiredArgsConstructor
public class MoneyTransferWorker {
    // Khoi tao worker
    final WorkflowClient workflowClient;
    final WorkerFactoryOptions defaultWorkerFactoryOptions; // dang lay theo default
    final WorkerOptions workerOptions;
    final WorkflowImplementationOptions workflowImplementationOptions;

    @PostConstruct
    public void initMoneyTransferWorker() {
        var workerFactory = WorkerFactory.newInstance(workflowClient, defaultWorkerFactoryOptions);
        var worker = workerFactory.newWorker(Function.MONEY_TRANSFER_TASK_QUEUE.getFunctionName(), workerOptions);
        worker.registerActivitiesImplementations(workflowImplementationOptions, MoneyTransferWorkflow.class);
        worker.registerActivitiesImplementations(new AccountActivityImpl());
        workerFactory.start();
    }
}
