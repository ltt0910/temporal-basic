package com.temporal.demo.common;

public enum Function {
    MONEY_TRANSFER_TASK_QUEUE("MONEY_TRANSFER_TASK_QUEUE");
    private final String functionName;

    Function(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return functionName;
    }
}
