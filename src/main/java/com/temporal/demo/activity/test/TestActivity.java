package com.temporal.demo.activity.test;

import com.temporal.demo.response.Response;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface TestActivity {

    @ActivityMethod
    String sayHi(String name);

}
