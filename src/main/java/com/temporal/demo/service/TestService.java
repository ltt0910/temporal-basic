package com.temporal.demo.service;

import com.temporal.demo.common.Function;
import com.temporal.demo.workflow.test.TestWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestService {

    final WorkflowClient workflowClient;

    public String sayHi(String name) {

        final TestWorkflow testWorkflow = workflowClient
                .newWorkflowStub(TestWorkflow.class,
                        WorkflowOptions
                                .newBuilder()
                                .setWorkflowId(String.valueOf(UUID.randomUUID()))
                                .setTaskQueue(Function.TEST_TASK_QUEUE.getFunctionName())
                                .build());

        return testWorkflow.sayHi(name);

    }

}
