package com.jianke.router.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.jianke.router.Caller;
import com.jianke.router.Router;
import com.jianke.router.test.data.TestParcelable;
import com.jianke.router.test.data.TestSerializable;
import com.jianke.router.test.routerservice.TestInterface;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TransferTest {

    @Test
    public void createIntent() {
        Caller caller = Caller.getInstance(InstrumentationRegistry.getTargetContext());
        try {
            Method createIntent = Router.class.getDeclaredMethod("createIntent", Method.class, Caller.class);
            createIntent.setAccessible(true);

            Method startActivityForResult = TestInterface.class.getDeclaredMethod("startActivityForResult", int.class, String.class, String.class);
            Intent intent = (Intent) createIntent.invoke(null, startActivityForResult, caller);
            Assert.assertEquals(intent.getAction(), "com.jianke.test");
            Set<String> categories = intent.getCategories();
            Assert.assertEquals(categories.contains("custom.category"), true);
            Assert.assertEquals(categories.contains("custom.category1"), true);
            Assert.assertEquals(categories.contains("custom.category2"), true);
            Assert.assertEquals(intent.getData(), Uri.parse("jk://jianke.com/login"));
            Assert.assertEquals(intent.getType(), "test/testLogin");

            Method analyzeParams = Router.class.getDeclaredMethod("analyzeParams", Intent.class, Object[].class);
            analyzeParams.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void startActivityByClass() {
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

        Router.with(InstrumentationRegistry.getTargetContext())
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

        onView(withId(R.id.tv1)).check(matches(withText(String.valueOf(intValue))));
        onView(withId(R.id.tv2)).check(matches(withText(Arrays.toString(intArray))));
        onView(withId(R.id.tv3)).check(matches(withText(integerArrayList.toString())));
        onView(withId(R.id.tv4)).check(matches(withText(String.valueOf(booleanValue))));
        onView(withId(R.id.tv5)).check(matches(withText(Arrays.toString(booleanArray))));
        onView(withId(R.id.tv6)).check(matches(withText(String.valueOf(charValue))));
        onView(withId(R.id.tv7)).check(matches(withText(Arrays.toString(charArray))));
        onView(withId(R.id.tv8)).check(matches(withText(String.valueOf(longValue))));
        onView(withId(R.id.tv9)).check(matches(withText(Arrays.toString(longArray))));
        onView(withId(R.id.tv10)).check(matches(withText(String.valueOf(shortValue))));
        onView(withId(R.id.tv11)).check(matches(withText(Arrays.toString(shortArray))));
        onView(withId(R.id.tv12)).check(matches(withText(String.valueOf(floatValue))));
        onView(withId(R.id.tv13)).check(matches(withText(Arrays.toString(floatArray))));
        onView(withId(R.id.tv14)).check(matches(withText(String.valueOf(doubleValue))));
        onView(withId(R.id.tv15)).check(matches(withText(Arrays.toString(doubleArray))));
        onView(withId(R.id.tv16)).check(matches(withText(String.valueOf(byteValue))));
        onView(withId(R.id.tv17)).check(matches(withText(Arrays.toString(byteArray))));
        onView(withId(R.id.tv18)).check(matches(withText(String.valueOf(charSequenceValue))));
        onView(withId(R.id.tv19)).check(matches(withText(Arrays.toString(charSequenceArray))));
        onView(withId(R.id.tv20)).check(matches(withText(charSequenceArrayList.toString())));
        onView(withId(R.id.tv21)).check(matches(withText(String.valueOf(stringValue))));
        onView(withId(R.id.tv22)).check(matches(withText(Arrays.toString(stringArray))));
        onView(withId(R.id.tv23)).check(matches(withText(stringArrayList.toString())));
        onView(withId(R.id.tv24)).check(matches(withText(bundleValue.getString("bundle1") + bundleValue.getString("bundle2"))));
        onView(withId(R.id.tv25)).check(matches(withText(parcelableValue.getParcel1() + parcelableValue.getParcel2())));
        onView(withId(R.id.tv26)).check(matches(withText(parcelableArray[0].getParcel1() + parcelableArray[0].getParcel2() + parcelableArray[1].getParcel1() + parcelableArray[1].getParcel2())));
        onView(withId(R.id.tv27)).check(matches(withText(parcelableArrayList.get(0).getParcel1() + parcelableArrayList.get(0).getParcel2() + parcelableArrayList.get(1).getParcel1() + parcelableArrayList.get(1).getParcel2())));
        onView(withId(R.id.tv28)).check(matches(withText(serializableValue.getSerializable1() + serializableValue.getSerializable2())));
    }

    @Test
    public void startActivityByAction() {
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

        Router.with(InstrumentationRegistry.getTargetContext())
                .obtain(TestInterface.class)
                .startLoginActivityByAction(intValue,
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

        onView(withId(R.id.tv1)).check(matches(withText(String.valueOf(intValue))));
        onView(withId(R.id.tv2)).check(matches(withText(Arrays.toString(intArray))));
        onView(withId(R.id.tv3)).check(matches(withText(integerArrayList.toString())));
        onView(withId(R.id.tv4)).check(matches(withText(String.valueOf(booleanValue))));
        onView(withId(R.id.tv5)).check(matches(withText(Arrays.toString(booleanArray))));
        onView(withId(R.id.tv6)).check(matches(withText(String.valueOf(charValue))));
        onView(withId(R.id.tv7)).check(matches(withText(Arrays.toString(charArray))));
        onView(withId(R.id.tv8)).check(matches(withText(String.valueOf(longValue))));
        onView(withId(R.id.tv9)).check(matches(withText(Arrays.toString(longArray))));
        onView(withId(R.id.tv10)).check(matches(withText(String.valueOf(shortValue))));
        onView(withId(R.id.tv11)).check(matches(withText(Arrays.toString(shortArray))));
        onView(withId(R.id.tv12)).check(matches(withText(String.valueOf(floatValue))));
        onView(withId(R.id.tv13)).check(matches(withText(Arrays.toString(floatArray))));
        onView(withId(R.id.tv14)).check(matches(withText(String.valueOf(doubleValue))));
        onView(withId(R.id.tv15)).check(matches(withText(Arrays.toString(doubleArray))));
        onView(withId(R.id.tv16)).check(matches(withText(String.valueOf(byteValue))));
        onView(withId(R.id.tv17)).check(matches(withText(Arrays.toString(byteArray))));
        onView(withId(R.id.tv18)).check(matches(withText(String.valueOf(charSequenceValue))));
        onView(withId(R.id.tv19)).check(matches(withText(Arrays.toString(charSequenceArray))));
        onView(withId(R.id.tv20)).check(matches(withText(charSequenceArrayList.toString())));
        onView(withId(R.id.tv21)).check(matches(withText(String.valueOf(stringValue))));
        onView(withId(R.id.tv22)).check(matches(withText(Arrays.toString(stringArray))));
        onView(withId(R.id.tv23)).check(matches(withText(stringArrayList.toString())));
        onView(withId(R.id.tv24)).check(matches(withText(bundleValue.getString("bundle1") + bundleValue.getString("bundle2"))));
        onView(withId(R.id.tv25)).check(matches(withText(parcelableValue.getParcel1() + parcelableValue.getParcel2())));
        onView(withId(R.id.tv26)).check(matches(withText(parcelableArray[0].getParcel1() + parcelableArray[0].getParcel2() + parcelableArray[1].getParcel1() + parcelableArray[1].getParcel2())));
        onView(withId(R.id.tv27)).check(matches(withText(parcelableArrayList.get(0).getParcel1() + parcelableArrayList.get(0).getParcel2() + parcelableArrayList.get(1).getParcel1() + parcelableArrayList.get(1).getParcel2())));
        onView(withId(R.id.tv28)).check(matches(withText(serializableValue.getSerializable1() + serializableValue.getSerializable2())));
    }
}
