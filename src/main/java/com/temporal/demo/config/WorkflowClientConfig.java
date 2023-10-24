package com.temporal.demo.config;

import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactoryOptions;
import io.temporal.worker.WorkerOptions;
import io.temporal.worker.WorkflowImplementationOptions;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;

@Configuration
@RequiredArgsConstructor
@EnableAutoConfiguration
public class WorkflowClientConfig {

    @Value("${temporal.server}")
    private String temporalServer;

    @Value("${temporal.namespace}")
    private String nameSpace;

    @Bean
    public WorkflowClient workflowClient() throws SSLException {
        var workflowServiceStubsOptions = WorkflowServiceStubsOptions.newBuilder().setTarget(temporalServer);
        var workflowOptions = workflowServiceStubsOptions.setSslContext(GrpcSslContexts.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build()).build();

        var workflowService = WorkflowServiceStubs.newServiceStubs(workflowOptions);
        return WorkflowClient.newInstance(workflowService, WorkflowClientOptions.newBuilder().setNamespace(nameSpace).build());
    }

    //todo add config workflow && activity neu can

    //todo add tham so WorkerFactoryOptions
    @Bean
    public WorkerFactoryOptions defaultWorkerFactoryOptions() {
        return WorkerFactoryOptions.newBuilder().build();
    }


    //todo add tham so workerOptions
    @Bean
    public WorkerOptions workerOptions() {
        return WorkerOptions.newBuilder().build();
    }

    @Bean
    public WorkflowImplementationOptions workflowImplementationOptions() {
        return WorkflowImplementationOptions.newBuilder().build();
    }

    @Bean
    public WorkflowOptions defaultWorkflowOptions() {
        return WorkflowOptions.newBuilder().build();
    }
}
