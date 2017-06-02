package com.jianke.router;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.jianke.router.annotation.ArrayExtra;
import com.jianke.router.annotation.Extra;
import com.jianke.router.annotation.RequestId;
import com.jianke.router.annotation.Target;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * 项目名称：Router
 * 类    名: Router.java
 * 类 描 述: 整个Router项目的入口。用于开启Activity及相互之间传递数据的工具类。
 * TODO: 谌珂 2017/6/2 支持传入flag、支持直接传入bundle和intent数据类型、支持uri传输数据、解决线程安全问题
 * 版    本：1.0.0
 * 创建时间：2016/1/12
 * @author 谌珂
 */
public class Router {

    private static Caller caller;
    private static Hashtable<Class, Object> mCache = new Hashtable<>();
    private static Router router = new Router();

    /**
     * 描 述：配置开启新Activity的发起者
     * 作 者：谌珂
     * 历 史: (1.0.0) 谌珂 2017/6/1
     */
    public static Router with(Context context) {
        caller = Caller.getInstance(context);
        return router;
    }

    /**
     * 描 述：配置开启新Activity的发起者
     * 作 者：谌珂
     * 历 史: (1.0.0) 谌珂 2017/6/1
     */
    public static Router with(Activity activity) {
        caller = Caller.getInstance(activity);
        return router;
    }

    /**
     * 描 述：配置开启新Activity的发起者
     * 作 者：谌珂
     * 历 史: (1.0.0) 谌珂 2017/6/1
     */
    public static Router with(Fragment fragment) {
        caller = Caller.getInstance(fragment);
        return router;
    }

    /**
     * 描 述：获取模块内Router交互服务
     * 作 者：谌珂
     * 历 史: (1.0.0) 谌珂 2017/5/27
     * @param clazz 服务定义接口
     * @param <T> 传入及最终返回服务的接口类型
     * @return Router交互服务
     */
    @SuppressWarnings("unchecked")
    public <T> T obtain(final Class<T> clazz) {
        if(mCache.get(clazz) != null) {   //先从缓存里面获取服务，如果有则直接返回
            return (T) mCache.get(clazz);
        }
        //生成动态代理服务
        T service = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                /* 1.解析@Target注解
                 * 2.如果有class则通过Activity.class创建Intent，如果没有则通过uri创建隐式Intent
                 * 3.解析参数列表。如果包含带@RequestId注解的参数则记录其值，准备启动带返回值的方法；依次解析@Extra注解的参数，并把值放进2创建的意图中
                 * 4.拿到Caller开始调用
                 */
                Intent intent = createIntent(method, caller.obtainContext());
                int requestId = analyzeParams(intent, method, args);
                caller.startActivityForResult(intent, requestId);


                return null;
            }
        });
        //把服务添加进缓存
        mCache.put(clazz, service);
        return service;
    }

    /**
     * 描 述：解析@Target注解，如果有class则通过Activity.class创建Intent，如果没有则通过uri创建隐式Intent
     * 作 者：谌珂
     * 历 史: (1.0.0) 谌珂 2017/5/31
     * @param method 开启Activity的某个方法
     * @param context 上下文对象
     * @return Intent
     */
    private static Intent createIntent(Method method, Context context) {
        Intent intent;
        Target target = method.getAnnotation(Target.class);
        if(target.targetClass() != Activity.class) {
            intent = new Intent(context, target.targetClass());
        } else {
            intent = new Intent();
            if(!TextUtils.isEmpty(target.action())) {
                intent.setAction(target.action());
            }
            for (String category: target.category()) {
                intent.addCategory(category);
            }
            if(!TextUtils.isEmpty(target.uri()) && !TextUtils.isEmpty(target.type())) {
                intent.setDataAndType(Uri.parse(target.uri()), target.type());
                return intent;
            }
            if(!TextUtils.isEmpty(target.uri())) {
                intent.setData(Uri.parse(target.uri()));
            }
            if(!TextUtils.isEmpty(target.type())) {
                intent.setType(target.type());
            }
        }
        return intent;
    }

    /**
     * 描 述：解析参数列表，把数据填充进Intent
     * 作 者：谌珂
     * 历 史: (1.0.0) 谌珂 2017/5/31
     * @param args 参数列表
     * @return 如果有requestId则返回，否则返回-1
     */
    @SuppressWarnings("unchecked")
    private static int analyzeParams(Intent intent, Method method,  Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int requestId = -1;
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            if(parameterAnnotations[i].length != 1) {
                throw new IllegalArgumentException("Require 1 annotation, but now is "
                        + parameterAnnotations[i].length + " \""
                        + Arrays.toString(parameterAnnotations[i])
                        + "\" in method : " + method.getName());
            }
            Annotation annotation = parameterAnnotations[i][0];
            if(annotation instanceof Extra) {
                String key = ((Extra) annotation).value();
                switch (parameterTypes[i].getSimpleName()){       //根据属性的类型名称put进bundle，如果匹配到了则进入下一轮循环
                    case "int" :
                    case "Integer" :
                        intent.putExtra(key, (Integer) args[i]);
                        continue;
                    case "int[]" :
                        intent.putExtra(key, (int[]) args[i]);
                        continue;
                    case "Integer[]" :
                        intent.putExtra(key, Utils.getIntArray((Integer[]) args[i]));
                        continue;
                    case "boolean" :
                    case "Boolean" :
                        intent.putExtra(key, (Boolean) args[i]);
                        continue;
                    case "boolean[]" :
                        intent.putExtra(key, (boolean[]) args[i]);
                        continue;
                    case "Boolean[]" :
                        intent.putExtra(key, Utils.getBooleanArray((Boolean[]) args[i]));
                        continue;
                    case "byte" :
                    case "Byte" :
                        intent.putExtra(key, (Byte) args[i]);
                        continue;
                    case "byte[]" :
                        intent.putExtra(key, (byte[]) args[i]);
                        continue;
                    case "Byte[]" :
                        intent.putExtra(key, Utils.getByteArray((Byte[]) args[i]));
                        continue;
                    case "float" :
                    case "Float" :
                        intent.putExtra(key, (Float) args[i]);
                        continue;
                    case "float[]" :
                        intent.putExtra(key, (float[]) args[i]);
                        continue;
                    case "Float[]" :
                        intent.putExtra(key, Utils.getFloatArray((Float[]) args[i]));
                        continue;
                    case "double" :
                    case "Double" :
                        intent.putExtra(key, (Double) args[i]);
                        continue;
                    case "double[]" :
                        intent.putExtra(key, (double[]) args[i]);
                        continue;
                    case "Double[]" :
                        intent.putExtra(key, Utils.getDoubleArray((Double[]) args[i]));
                        continue;
                    case "char" :
                    case "Character" :
                        intent.putExtra(key, (Character) args[i]);
                        continue;
                    case "char[]" :
                        intent.putExtra(key, (char[]) args[i]);
                        continue;
                    case "Character[]" :
                        intent.putExtra(key, Utils.getCharacterArray((Character[]) args[i]));
                        continue;
                    case "short" :
                    case "Short" :
                        intent.putExtra(key, (Short) args[i]);
                        continue;
                    case "short[]" :
                        intent.putExtra(key, (short[]) args[i]);
                        continue;
                    case "Short[]" :
                        intent.putExtra(key, Utils.getShortArray((Short[]) args[i]));
                        continue;
                    case "long" :
                    case "Long" :
                        intent.putExtra(key, (Long) args[i]);
                        continue;
                    case "long[]" :
                        intent.putExtra(key, (long[]) args[i]);
                        continue;
                    case "Long[]" :
                        intent.putExtra(key, Utils.getLongArray((Long[]) args[i]));
                        continue;
                    case "CharSequence" :
                        intent.putExtra(key, (CharSequence) args[i]);
                        continue;
                    case "CharSequence[]" :
                        intent.putExtra(key, (CharSequence[]) args[i]);
                        continue;
                    case "String" :
                        intent.putExtra(key, (String) args[i]);
                        continue;
                    case "String[]" :
                        intent.putExtra(key, (String[]) args[i]);
                        continue;
                    case "Bundle" :
                        intent.putExtra(key, (Bundle) args[i]);
                        continue;
                    default:
                        Log.i("Router#analyzeParams", "can not find the type with field, it will find by interface type.");
                        break;
                }
                if(parameterTypes[i].getSimpleName().contains("[]")) {   //能传数组类型的都已经在switch中解决了，能运行到这还带[]的一定是Parcelable[]
                    intent.putExtra(key, (Parcelable[]) args[i]);
                } else {           //判断是不是Parcelable或者Serializable类型数据
                    for (Class<?> lInterface : parameterTypes[i].getInterfaces()) {
                        if(TextUtils.equals(lInterface.getSimpleName(), "Parcelable")){
                            intent.putExtra(key, (Parcelable) args[i]);
                            break;
                        } else if(TextUtils.equals(lInterface.getSimpleName(), "Serializable")) {
                            intent.putExtra(key, (Serializable) args[i]);
                            break;
                        }
                    }
                }
            } else if(annotation instanceof ArrayExtra) {
                Class type = ((ArrayExtra)annotation).type();
                String key = ((ArrayExtra)annotation).key();
                if(Integer.class.isAssignableFrom(type)) {
                    intent.putIntegerArrayListExtra(key, (ArrayList<Integer>) args[i]);
                }
                if(Parcelable.class.isAssignableFrom(type)) {
                    intent.putParcelableArrayListExtra(key, (ArrayList<Parcelable>) args[i]);
                }
                if(String.class.isAssignableFrom(type)) {
                    intent.putStringArrayListExtra(key, (ArrayList<String>) args[i]);
                }
                if(CharSequence.class.isAssignableFrom(type)) {
                    intent.putCharSequenceArrayListExtra(key, (ArrayList<CharSequence>) args[i]);
                }
            } else if(annotation instanceof RequestId) {
                if(requestId != -1) {
                    throw new IllegalArgumentException("Can not call method \""
                            + method.getName() + "\" with multiply annotation \""
                            + RequestId.class + "\"");
                }
                requestId = (int) args[i];
            } else {
                throw new IllegalArgumentException("Invalid annotation \""
                        + annotation.toString() + "\" in method : "
                        + method.getName());
            }
        }
        return requestId;
    }


}
