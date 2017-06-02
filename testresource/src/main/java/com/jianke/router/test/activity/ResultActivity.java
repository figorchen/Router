package com.jianke.router.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jianke.router.test.R;

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        findViewById(R.id.btn_finish).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("result", "test in control");
            setResult(RESULT_OK, intent);
            finish();
        });


    }
}
