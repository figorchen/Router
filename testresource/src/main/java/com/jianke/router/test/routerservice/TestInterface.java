package com.jianke.router.test.routerservice;

import android.os.Bundle;

import com.jianke.router.annotation.ArrayExtra;
import com.jianke.router.annotation.Extra;
import com.jianke.router.annotation.RequestId;
import com.jianke.router.annotation.Target;
import com.jianke.router.test.activity.LoginActivity;
import com.jianke.router.test.activity.ResultActivity;
import com.jianke.router.test.activity.SimpleActivity;
import com.jianke.router.test.activity.SimpleFragmentActivity;
import com.jianke.router.test.data.TestParcelable;
import com.jianke.router.test.data.TestSerializable;

import java.util.ArrayList;

public interface TestInterface {
    @Target(targetClass = LoginActivity.class)
    void startLoginActivity(@Extra("int") int intValue,
                            @Extra("intArray") int[] intArray,
                            @ArrayExtra(key = "intArrayList", type = Integer.class) ArrayList intArrayList,
                            @Extra("boolean") boolean booleanValue,
                            @Extra("booleanArray") boolean[] booleanArray,
                            @Extra("char") char charValue,
                            @Extra("charArray") char[] charArray,
                            @Extra("long") long longValue,
                            @Extra("longArray") long[] longArray,
                            @Extra("short") short shortValue,
                            @Extra("shortArray") short[] shortArray,
                            @Extra("float") float floatValue,
                            @Extra("floatArray") float[] floatArray,
                            @Extra("double") double doubleValue,
                            @Extra("doubleArray") double[] doubleArray,
                            @Extra("byte") byte byteValue,
                            @Extra("byteArray") byte[] byteArray,
                            @Extra("charSequence") CharSequence charSequenceValue,
                            @Extra("charSequenceArray") CharSequence[] charSequenceArray,
                            @ArrayExtra(key = "charSequenceArrayList", type = CharSequence.class) ArrayList charSequenceArrayList,
                            @Extra("string") String stringValue,
                            @Extra("stringArray") String[] stringArray,
                            @ArrayExtra(key = "stringArrayList", type = String.class) ArrayList stringArrayList,
                            @Extra("bundle") Bundle bundleValue,
                            @Extra("parcelable") TestParcelable parcelableValue,
                            @Extra("parcelableArray") TestParcelable[] parcelableArray,
                            @ArrayExtra(key = "parcelableArrayList", type = TestParcelable.class) ArrayList parcelableArrayList,
                            @Extra("serializable") TestSerializable serializableValue);

    @Target(uri = "jk://jianke.com/login", action = "com.jianke.test", category = "custom.category", type = "test/testLogin")
    void startLoginActivityByAction(@Extra("int") int intValue,
                                    @Extra("intArray") int[] intArray,
                                    @ArrayExtra(key = "intArrayList", type = Integer.class) ArrayList intArrayList,
                                    @Extra("boolean") boolean booleanValue,
                                    @Extra("booleanArray") boolean[] booleanArray,
                                    @Extra("char") char charValue,
                                    @Extra("charArray") char[] charArray,
                                    @Extra("long") long longValue,
                                    @Extra("longArray") long[] longArray,
                                    @Extra("short") short shortValue,
                                    @Extra("shortArray") short[] shortArray,
                                    @Extra("float") float floatValue,
                                    @Extra("floatArray") float[] floatArray,
                                    @Extra("double") double doubleValue,
                                    @Extra("doubleArray") double[] doubleArray,
                                    @Extra("byte") byte byteValue,
                                    @Extra("byteArray") byte[] byteArray,
                                    @Extra("charSequence") CharSequence charSequenceValue,
                                    @Extra("charSequenceArray") CharSequence[] charSequenceArray,
                                    @ArrayExtra(key = "charSequenceArrayList", type = CharSequence.class) ArrayList charSequenceArrayList,
                                    @Extra("string") String stringValue,
                                    @Extra("stringArray") String[] stringArray,
                                    @ArrayExtra(key = "stringArrayList", type = String.class) ArrayList stringArrayList,
                                    @Extra("bundle") Bundle bundleValue,
                                    @Extra("parcelable") TestParcelable parcelableValue,
                                    @Extra("parcelableArray") TestParcelable[] parcelableArray,
                                    @ArrayExtra(key = "parcelableArrayList", type = TestParcelable.class) ArrayList parcelableArrayList,
                                    @Extra("serializable") TestSerializable serializableValue);

    @Target(targetClass = ResultActivity.class)
    void startMainActivity(@RequestId int requestId);

    @Target(targetClass = SimpleActivity.class)
    void startSimpleActivity();

    @Target(targetClass = SimpleFragmentActivity.class)
    void startSimpleFragmentActivity();
}
