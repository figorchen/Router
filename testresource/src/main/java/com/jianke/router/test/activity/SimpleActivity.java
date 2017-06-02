package com.jianke.router.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.jianke.router.Router;
import com.jianke.router.test.R;
import com.jianke.router.test.routerservice.TestInterface;

public class SimpleActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        ((TextView)findViewById(R.id.tv_title)).setText("Activity");
        findViewById(R.id.btn_open).setOnClickListener(v -> Router.with(this).obtain(TestInterface.class).startMainActivity(100));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            ((TextView)findViewById(R.id.tv_result)).setText(data.getStringExtra("result"));
        }
    }
}
