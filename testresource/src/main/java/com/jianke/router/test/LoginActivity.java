package com.jianke.router.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;
    private TextView tv13;
    private TextView tv14;
    private TextView tv15;
    private TextView tv16;
    private TextView tv17;
    private TextView tv18;
    private TextView tv19;
    private TextView tv20;
    private TextView tv21;
    private TextView tv22;
    private TextView tv23;
    private TextView tv24;
    private TextView tv25;
    private TextView tv26;
    private TextView tv27;
    private TextView tv28;
    private TextView tv29;
    private TextView tv30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv10 = (TextView) findViewById(R.id.tv10);
        tv11 = (TextView) findViewById(R.id.tv11);
        tv12 = (TextView) findViewById(R.id.tv12);
        tv13 = (TextView) findViewById(R.id.tv13);
        tv14 = (TextView) findViewById(R.id.tv14);
        tv15 = (TextView) findViewById(R.id.tv15);
        tv16 = (TextView) findViewById(R.id.tv16);
        tv17 = (TextView) findViewById(R.id.tv17);
        tv18 = (TextView) findViewById(R.id.tv18);
        tv19 = (TextView) findViewById(R.id.tv19);
        tv20 = (TextView) findViewById(R.id.tv20);
        tv21 = (TextView) findViewById(R.id.tv21);
        tv22 = (TextView) findViewById(R.id.tv22);
        tv23 = (TextView) findViewById(R.id.tv23);
        tv24 = (TextView) findViewById(R.id.tv24);
        tv25 = (TextView) findViewById(R.id.tv25);
        tv26 = (TextView) findViewById(R.id.tv26);
        tv27 = (TextView) findViewById(R.id.tv27);
        tv28 = (TextView) findViewById(R.id.tv28);
        tv29 = (TextView) findViewById(R.id.tv29);
        tv30 = (TextView) findViewById(R.id.tv30);

        Intent intent = getIntent();
        tv1.setText(String.valueOf(intent.getIntExtra("int", -1)));
        tv2.setText(Arrays.toString(intent.getIntArrayExtra("intArray")));
        tv3.setText(intent.getIntegerArrayListExtra("intArrayList").toString());

        tv4.setText(String.valueOf(intent.getBooleanExtra("boolean", false)));
        tv5.setText(Arrays.toString(intent.getBooleanArrayExtra("booleanArray")));

        tv6.setText(String.valueOf(intent.getCharExtra("char", '-')));
        tv7.setText(Arrays.toString(intent.getCharArrayExtra("charArray")));

        tv8.setText(String.valueOf(intent.getLongExtra("long", -1)));
        tv9.setText(Arrays.toString(intent.getLongArrayExtra("longArray")));

        tv10.setText(String.valueOf(intent.getShortExtra("short", (short) -1)));
        tv11.setText(Arrays.toString(intent.getShortArrayExtra("shortArray")));

        tv12.setText(String.valueOf(intent.getFloatExtra("float", -1)));
        tv13.setText(Arrays.toString(intent.getFloatArrayExtra("floatArray")));

        tv14.setText(String.valueOf(intent.getDoubleExtra("double", -1)));
        tv15.setText(Arrays.toString(intent.getDoubleArrayExtra("doubleArray")));

        tv16.setText(String.valueOf(intent.getByteExtra("byte", (byte) 0)));
        tv17.setText(Arrays.toString(intent.getByteArrayExtra("byteArray")));

        tv18.setText(String.valueOf(intent.getCharSequenceExtra("charSequence")));
        tv19.setText(Arrays.toString(intent.getCharSequenceArrayExtra("charSequenceArray")));
        tv20.setText(intent.getCharSequenceArrayListExtra("charSequenceArrayList").toString());

        tv21.setText(String.valueOf(intent.getStringExtra("string")));
        tv22.setText(Arrays.toString(intent.getStringArrayExtra("stringArray")));
        tv23.setText(intent.getStringArrayListExtra("stringArrayList").toString());

        Bundle bundle = intent.getBundleExtra("bundle");
        tv24.setText(bundle.getString("bundle1") + bundle.getString("bundle2"));

        TestParcelable parcel = intent.getParcelableExtra("parcelable");
        tv25.setText(parcel.getParcel1() + parcel.getParcel2());

        Parcelable[] parcelArrays = intent.getParcelableArrayExtra("parcelableArray");
        tv26.setText(((TestParcelable)parcelArrays[0]).getParcel1() + ((TestParcelable)parcelArrays[0]).getParcel2() + ((TestParcelable)parcelArrays[1]).getParcel1() + ((TestParcelable)parcelArrays[1]).getParcel2());

        ArrayList<TestParcelable> parcelArrayList = intent.getParcelableArrayListExtra("parcelableArrayList");
        tv27.setText(parcelArrayList.get(0).getParcel1() + parcelArrayList.get(0).getParcel2() + parcelArrayList.get(1).getParcel1() + parcelArrayList.get(1).getParcel2());

        TestSerializable serializable = (TestSerializable) intent.getSerializableExtra("serializable");
        tv28.setText(serializable.getSerializable1() + serializable.getSerializable2());
    }
}

