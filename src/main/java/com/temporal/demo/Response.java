package com.temporal.demo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Response {

    private String code;
    private String message;
    private Object data;
}
