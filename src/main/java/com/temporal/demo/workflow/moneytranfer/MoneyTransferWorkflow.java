package com.temporal.demo.workflow.moneytranfer;

import com.temporal.demo.exception.BusinessException;
import com.temporal.demo.request.TransferRequest;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

// money-transfer-project-template-java-workflow-interface
@WorkflowInterface
public interface MoneyTransferWorkflow {

    // The Workflow method is called by the initiator either via code or CLI.
    @WorkflowMethod
    String transfer(TransferRequest transferRequest) throws BusinessException;

}
