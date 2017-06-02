package com.jianke.router.test.data;

import android.os.Parcel;
import android.os.Parcelable;

public class TestParcelable implements Parcelable {
    public TestParcelable(String parcel1, String parcel2) {
        this.parcel1 = parcel1;
        this.parcel2 = parcel2;
    }

    private String parcel1;
    private String parcel2;

    public String getParcel1() {
        return parcel1;
    }

    public void setParcel1(String parcel1) {
        this.parcel1 = parcel1;
    }

    public String getParcel2() {
        return parcel2;
    }

    public void setParcel2(String parcel2) {
        this.parcel2 = parcel2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.parcel1);
        dest.writeString(this.parcel2);
    }

    protected TestParcelable(Parcel in) {
        this.parcel1 = in.readString();
        this.parcel2 = in.readString();
    }

    public static final Creator<TestParcelable> CREATOR = new Creator<TestParcelable>() {
        @Override
        public TestParcelable createFromParcel(Parcel source) {
            return new TestParcelable(source);
        }

        @Override
        public TestParcelable[] newArray(int size) {
            return new TestParcelable[size];
        }
    };
}
