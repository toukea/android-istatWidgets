package istat.android.widget.datas;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by istat on 24/11/16.
 */

public class BasicNameValuePair implements Parcelable {
    public String value, name;

    public BasicNameValuePair(String s, String s1) {
        this.name = s;
        this.value = s1;
    }

    protected BasicNameValuePair(Parcel in) {
        value = in.readString();
        name = in.readString();
    }

    public static final Creator<BasicNameValuePair> CREATOR = new Creator<BasicNameValuePair>() {
        @Override
        public BasicNameValuePair createFromParcel(Parcel in) {
            return new BasicNameValuePair(in);
        }

        @Override
        public BasicNameValuePair[] newArray(int size) {
            return new BasicNameValuePair[size];
        }
    };

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(value);
        dest.writeString(name);
    }
}
