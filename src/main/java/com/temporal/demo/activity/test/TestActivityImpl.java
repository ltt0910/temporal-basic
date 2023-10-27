package com.temporal.demo.activity.test;

import org.springframework.util.CollectionUtils;

public class TestActivityImpl implements TestActivity {
    @Override
    public String sayHi(String name) {
        return "Hi " + name + " !";
    }
}
