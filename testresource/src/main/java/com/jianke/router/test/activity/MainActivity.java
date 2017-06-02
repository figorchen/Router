package com.jianke.router.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jianke.router.Router;
import com.jianke.router.test.R;
import com.jianke.router.test.data.TestParcelable;
import com.jianke.router.test.data.TestSerializable;
import com.jianke.router.test.routerservice.TestInterface;

import java.util.ArrayList;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_transfer).setOnClickListener(v -> {
            int intValue = 100;
            int[] intArray = new int[]{101, 102};
            ArrayList<Integer> integerArrayList = new ArrayList<>();
            integerArrayList.add(103);
            integerArrayList.add(104);

            boolean booleanValue = true;
            boolean[] booleanArray = new boolean[]{true, false, true};

            char charValue = '1';
            char[] charArray = new char[]{'2', '3', '4'};

            long longValue = 10000;
            long[] longArray = new long[]{10000, 10000, 10000};

            short shortValue = 10;
            short[] shortArray = new short[]{11, 12, 13};

            float floatValue = 10.1f;
            float[] floatArray = new float[]{11.2f, 12.3f, 13.4f};

            double doubleValue = 100.1d;
            double[] doubleArray = new double[]{101.2d, 102.3d, 103.4d};

            byte byteValue = 5;
            byte[] byteArray = new byte[]{6, 7, 8};

            CharSequence charSequenceValue = "charSequenceValue";
            CharSequence[] charSequenceArray = new CharSequence[]{"charSequenceValue1", "charSequenceValue2", "charSequenceValue3"};
            ArrayList<CharSequence> charSequenceArrayList = new ArrayList<>();
            charSequenceArrayList.add("charSequenceValue4");
            charSequenceArrayList.add("charSequenceValue5");
            charSequenceArrayList.add("charSequenceValue6");

            String stringValue = "stringValue";
            String[] stringArray = new String[]{"stringValue1", "stringValue2", "stringValue3"};
            ArrayList<String> stringArrayList = new ArrayList<>();
            stringArrayList.add("stringValue4");
            stringArrayList.add("stringValue5");
            stringArrayList.add("stringValue6");

            Bundle bundleValue = new Bundle();
            bundleValue.putString("bundle1", "bundleValue1");
            bundleValue.putString("bundle2", "bundleValue2");

            TestParcelable parcelableValue = new TestParcelable("parcelableValue1", "parcelableValue2");
            TestParcelable[] parcelableArray = new TestParcelable[]{new TestParcelable("parcelableValue11", "parcelableValue22"), new TestParcelable("parcelableValue33", "parcelableValue44")};
            ArrayList<TestParcelable> parcelableArrayList = new ArrayList<>();
            parcelableArrayList.add(new TestParcelable("parcelableValue111", "parcelableValue222"));
            parcelableArrayList.add(new TestParcelable("parcelableValue333", "parcelableValue444"));

            TestSerializable serializableValue = new TestSerializable("serializableValue1", "serializableValue2");

            Router.with(this)
                    .obtain(TestInterface.class)
                    .startLoginActivity(intValue,
                            intArray,
                            integerArrayList,
                            booleanValue,
                            booleanArray,
                            charValue,
                            charArray,
                            longValue,
                            longArray,
                            shortValue,
                            shortArray,
                            floatValue,
                            floatArray,
                            doubleValue,
                            doubleArray,
                            byteValue,
                            byteArray,
                            charSequenceValue,
                            charSequenceArray,
                            charSequenceArrayList,
                            stringValue,
                            stringArray,
                            stringArrayList,
                            bundleValue,
                            parcelableValue,
                            parcelableArray,
                            parcelableArrayList,
                            serializableValue);
        });

        findViewById(R.id.btn_activity_result).setOnClickListener(v -> Router.with(this).obtain(TestInterface.class).startSimpleActivity());

        findViewById(R.id.btn_fragment_result).setOnClickListener(v -> Router.with(this).obtain(TestInterface.class).startSimpleFragmentActivity());
    }
}
