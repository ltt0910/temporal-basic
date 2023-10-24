package com.temporal.demo.worker.moneytranfer;

import com.temporal.demo.activity.moneytranfer.AccountActivityImpl;
import com.temporal.demo.common.Function;
import com.temporal.demo.workflow.moneytranfer.MoneyTransferWorkflow;
import com.temporal.demo.workflow.moneytranfer.MoneyTransferWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.WorkerFactory;
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
    final WorkerFactory workerFactory;
//    final WorkerFactoryOptions defaultWorkerFactoryOptions; // dang lay theo default
//    final WorkerOptions workerOptions;
//    final WorkflowImplementationOptions defaultWorkflowImplementationOptions;

    @PostConstruct
    public void initMoneyTransferWorker() {
        var worker = workerFactory.newWorker(Function.MONEY_TRANSFER_TASK_QUEUE.getFunctionName());
        worker.registerWorkflowImplementationTypes(MoneyTransferWorkflowImpl.class);
        worker.registerActivitiesImplementations(new AccountActivityImpl());
        workerFactory.start();
    }
}
