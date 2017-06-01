package com.jianke.router;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * 项目名称：Caller <br/>
 * 类    名: Caller.java <br/>
 * 类 描 述: 封装了开启者的行为方法。<br/>
 * 版    本：1.0.0 <br/>
 * 创建时间：2016/1/12 <br/>
 * @author 谌珂 <br/>
 */
public class Caller {
    private Context mContext;
    private Fragment mFragment;
    private Activity mActivity;

    private Caller(Activity mActivity) {
        this.mActivity = mActivity;
    }

    private Caller(Fragment mFragment) {
        this.mFragment = mFragment;
    }

    private Caller(Context mContext) {
        this.mContext = mContext;
    }

    public static Caller getInstance(Context context) {
        return new Caller(context);
    }

    public static Caller getInstance(Fragment fragment) {
        return new Caller(fragment);
    }

    public static Caller getInstance(Activity activity) {
        return new Caller(activity);
    }

    /**
     * 描 述：有返回值开启Activity方法（不支持Context）。<br/>
     * 作 者：谌珂<br/>
     * 历 史: (1.0.0) 谌珂 2017/5/31 <br/>
     * @param intent 开启Activity的Intent
     * @param requestId 等同于{@link Activity#startActivityForResult(Intent, int)}或{@link Fragment#startActivityForResult(Intent, int)}中的requestId
     * @throws IllegalStateException 当{@link #mActivity}、{@link #mFragment}同时为空时抛出异常。
     */
    @TargetApi(11)
    public void startActivityForResult(Intent intent, int requestId) {
        if(mActivity != null) {
            mActivity.startActivityForResult(intent, requestId);
            return;
        }
        if(mFragment != null) {
            mFragment.startActivityForResult(intent, requestId);
            return;
        }
        if(mContext != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            return;
        }
        throw new IllegalStateException("Caller is invalids");
    }

    /**
     * 描 述：与context做对比<br/>
     * 作 者：谌珂<br/>
     * 历 史: (1.0.0) 谌珂 2017/5/31 <br/>
     * @param context 需要做对比的对象
     * @return 如果{@link #mContext}与context相等则返回true
     */
    public boolean equalsTo(Context context) {
        return context != null && context.equals(mContext);
    }

    /**
     * 描 述：与context做对比<br/>
     * 作 者：谌珂<br/>
     * 历 史: (1.0.0) 谌珂 2017/5/31 <br/>
     * @param activity 需要做对比的对象
     * @return 如果{@link {@link #mActivity}}activity相等则返回true
     */
    public boolean equalsTo(Activity activity) {
        return activity != null && activity.equals(mActivity);
    }

    /**
     * 描 述：与context做对比<br/>
     * 作 者：谌珂<br/>
     * 历 史: (1.0.0) 谌珂 2017/5/31 <br/>
     * @param fragment 需要做对比的对象
     * @return 如果{@link {@link #mFragment}}fragment相等则返回true
     */
    public boolean equalsTo(Fragment fragment) {
        return fragment != null && fragment.equals(mFragment);
    }

    /**
     * 描 述：获取一个Context对象<br/>
     * 作 者：谌珂<br/>
     * 历 史: (1.0.0) 谌珂 2017/5/31 <br/>
     */
    public Context obtainContext() {
        if(mContext != null) {
            return mContext;
        }
        if(mActivity != null) {
            return mActivity;
        }
        if(mFragment != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return mFragment.getContext();
            } else {
                return mFragment.getActivity();
            }
        }
        throw new IllegalStateException("Caller is invalids");
    }
}
