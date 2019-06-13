package com.act.kafka.aop;


import com.act.kafka.enums.LogType;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {
    String format();

    LogType type() default LogType.Other;
}
