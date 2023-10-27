package com.temporal.demo.workflow.moneytranfer;

import com.temporal.demo.exception.BusinessException;
import com.temporal.demo.request.TransferRequest;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface MoneyTransferWorkflow {

    @WorkflowMethod
    String transfer(TransferRequest transferRequest) throws BusinessException;

}
