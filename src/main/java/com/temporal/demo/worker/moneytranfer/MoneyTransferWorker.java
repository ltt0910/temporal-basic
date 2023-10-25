package com.temporal.demo.worker.moneytranfer;

import com.temporal.demo.activity.moneytranfer.AccountActivityImpl;
import com.temporal.demo.common.Function;
import com.temporal.demo.workflow.moneytranfer.MoneyTransferWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

// money-transfer-project-template-java-worker
@Component
@RequiredArgsConstructor
public class MoneyTransferWorker {

    final WorkflowClient workflowClient;
    final WorkerFactory workerFactory;

    // Khoi tao worker
    @PostConstruct
    public void initWorker() {
        Worker worker = workerFactory.newWorker(Function.MONEY_TRANSFER_TASK_QUEUE.getFunctionName());
        worker.registerWorkflowImplementationTypes(MoneyTransferWorkflowImpl.class);
        worker.registerActivitiesImplementations(new AccountActivityImpl());
        workerFactory.start();
    }
}
