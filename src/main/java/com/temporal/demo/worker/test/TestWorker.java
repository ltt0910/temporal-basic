package com.temporal.demo.worker.test;

import com.temporal.demo.activity.test.TestActivityImpl;
import com.temporal.demo.common.Constants;
import com.temporal.demo.workflow.test.TestWorkflowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestWorker {
    final WorkflowClient workflowClient;
    final WorkerFactory workerFactory;

    @PostConstruct
    public void initWorker() {
        Worker worker = workerFactory.newWorker(Constants.TASK_QUEUE.TEST_TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(TestWorkflowImpl.class);
        worker.registerActivitiesImplementations(new TestActivityImpl());
//        workerFactory.start();
    }
}
