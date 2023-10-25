package com.temporal.demo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class Response {

    private String code;
    private String message;
    private Object data;
}
