package com.jianke.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@java.lang.annotation.Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ArrayExtra {
    String key();
    Class type();
}