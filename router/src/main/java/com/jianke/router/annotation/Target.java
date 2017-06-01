package com.jianke.router.annotation;

import android.app.Activity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@java.lang.annotation.Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Target {
    Class<? extends Activity> targetClass() default Activity.class;
    String uri() default "";
    String action() default "";
    String[] category() default "";
    String type() default "";
}
