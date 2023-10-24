package com.temporal.demo.service;

import com.temporal.demo.response.Response;
import com.temporal.demo.workflow.moneytranfer.MoneyTransferWorkflow;
import com.temporal.demo.request.TransferRequest;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.UUID;

// money-transfer-project-template-java-workflow-initiator
@Service
@RequiredArgsConstructor
public class InitiateMoneyTransferService {

    final WorkflowClient workflowClient;
    final WorkflowOptions defaultWorkflowOptions; //todo default

    public Response transfer(TransferRequest transferRequest) throws Exception {

        // WorkflowServiceStubs is a gRPC stubs wrapper that talks to the local Docker instance of the Temporal server.
//        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
//        WorkflowOptions options = WorkflowOptions
//                .newBuilder()
//                .setTaskQueue()
//                .setWorkflowId("money-transfer-workflow")
//                .build();
//        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
//        WorkflowClient client = WorkflowClient.newInstance(service);
//        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
//        MoneyTransferWorkflow workflow = client.newWorkflowStub(MoneyTransferWorkflow.class, options);
//
//        return workflow.transfer(transferRequest);
        var workflow = workflowClient.newWorkflowStub(MoneyTransferWorkflow.class,
                defaultWorkflowOptions
                        .toBuilder()
                        .setWorkflowId(String.valueOf(UUID.randomUUID())).build());
        return workflow.transfer(transferRequest);
    }
}
