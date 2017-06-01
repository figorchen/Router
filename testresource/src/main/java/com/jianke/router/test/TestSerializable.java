package com.jianke.router.test;

import java.io.Serializable;

public class TestSerializable implements Serializable {
    public TestSerializable(String serializable1, String serializable2) {
        this.serializable1 = serializable1;
        this.serializable2 = serializable2;
    }

    private String serializable1;
    private String serializable2;

    public String getSerializable1() {
        return serializable1;
    }

    public void setSerializable1(String serializable1) {
        this.serializable1 = serializable1;
    }

    public String getSerializable2() {
        return serializable2;
    }

    public void setSerializable2(String serializable2) {
        this.serializable2 = serializable2;
    }
}
