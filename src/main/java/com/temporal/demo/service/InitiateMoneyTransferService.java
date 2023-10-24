package com.temporal.demo.service;

import com.temporal.demo.Response;
import com.temporal.demo.moneytransferapp.MoneyTransferWorkflow;
import com.temporal.demo.moneytransferapp.Shared;
import com.temporal.demo.request.TransferRequest;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import org.springframework.stereotype.Service;

import java.util.UUID;

// money-transfer-project-template-java-workflow-initiator
@Service
public class InitiateMoneyTransferService {

    public Response transfer(TransferRequest transferRequest) throws Exception {

        // WorkflowServiceStubs is a gRPC stubs wrapper that talks to the local Docker instance of the Temporal server.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowOptions options = WorkflowOptions
                .newBuilder()
                .setTaskQueue(Shared.MONEY_TRANSFER_TASK_QUEUE)
                .setWorkflowId("money-transfer-workflow")
                .build();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        MoneyTransferWorkflow workflow = client.newWorkflowStub(MoneyTransferWorkflow.class, options);

        return workflow.transfer(transferRequest);

    }
}
