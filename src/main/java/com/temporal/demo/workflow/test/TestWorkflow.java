package com.temporal.demo.workflow.test;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface TestWorkflow {

    @WorkflowMethod
    String sayHi(String hi);

}
