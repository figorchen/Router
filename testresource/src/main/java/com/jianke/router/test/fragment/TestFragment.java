package com.jianke.router.test.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jianke.router.Router;
import com.jianke.router.test.R;
import com.jianke.router.test.routerservice.TestInterface;

import static android.app.Activity.RESULT_OK;

public class TestFragment extends Fragment {
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_simple, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mView.findViewById(R.id.btn_open).setOnClickListener(v-> Router.with(this).obtain(TestInterface.class).startMainActivity(100));
        ((TextView)mView.findViewById(R.id.tv_title)).setText("Fragment");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            ((TextView)mView.findViewById(R.id.tv_result)).setText(data.getStringExtra("result"));
        }
    }
}
