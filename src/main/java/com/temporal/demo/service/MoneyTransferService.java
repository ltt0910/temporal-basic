package com.temporal.demo.service;

import com.temporal.demo.common.Function;
import com.temporal.demo.exception.BusinessException;
import com.temporal.demo.request.TransferRequest;
import com.temporal.demo.workflow.moneytranfer.MoneyTransferWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

// money-transfer-project-template-java-workflow-initiator
@Service
@RequiredArgsConstructor
public class MoneyTransferService {

    final WorkflowClient workflowClient;

    public String transfer(TransferRequest transferRequest) throws BusinessException {

        MoneyTransferWorkflow moneyTransferWorkflow = workflowClient.newWorkflowStub(MoneyTransferWorkflow.class,
                WorkflowOptions.newBuilder()
                        .setTaskQueue(Function.MONEY_TRANSFER_TASK_QUEUE.getFunctionName())
                        .setWorkflowId(String.valueOf(UUID.randomUUID()))
                        .build());

        String result = "";
        try {
            result = moneyTransferWorkflow.transfer(transferRequest);
        } catch (BusinessException be) {
            result = "Fail";
        }
        return result;
    }
}
