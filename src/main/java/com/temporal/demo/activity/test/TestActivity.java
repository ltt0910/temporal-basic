package com.temporal.demo.activity.test;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface TestActivity {

    @ActivityMethod
    String sayHi(String name);

}
