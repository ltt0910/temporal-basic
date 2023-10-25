package com.temporal.demo.activity.test;

public class TestActivityImpl implements TestActivity {
    @Override
    public String sayHi(String name) {
        return "Hi " + name + " !";
    }
}
