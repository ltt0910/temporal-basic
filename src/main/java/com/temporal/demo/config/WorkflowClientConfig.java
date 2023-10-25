package com.temporal.demo.config;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WorkflowClientConfig {

    @Value("${temporal.server}")
    private String temporalServer;

    @Value("${temporal.namespace}")
    private String nameSpace;

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs
                .newServiceStubs(WorkflowServiceStubsOptions.newBuilder().setTarget(temporalServer).build());
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient
                .newInstance(workflowServiceStubs, WorkflowClientOptions.newBuilder().setNamespace(nameSpace).build());
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }

}
