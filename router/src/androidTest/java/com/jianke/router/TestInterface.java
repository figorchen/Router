package com.jianke.router;

import android.os.Bundle;

import com.jianke.router.annotation.ArrayExtra;
import com.jianke.router.annotation.Extra;
import com.jianke.router.annotation.RequestId;
import com.jianke.router.annotation.Target;
import com.jianke.router.test.LoginActivity;
import com.jianke.router.test.TestParcelable;
import com.jianke.router.test.TestSerializable;

import java.util.ArrayList;

public interface TestInterface {
    @Target(targetClass = LoginActivity.class)
    void startActivity();

    @Target(uri = "jk://jianke.com/login", action = "com.jianke.test", category = {"custom.category", "custom.category1", "custom.category2"}, type = "test/testLogin")
    void startActivityForResult(@RequestId int requestId, @Extra("username") String userName, @Extra("username") String password);
    
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
}
