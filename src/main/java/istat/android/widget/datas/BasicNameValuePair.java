package istat.android.widget.datas;

/**
 * Created by istat on 24/11/16.
 */

public class BasicNameValuePair {
    String value, name;

    public BasicNameValuePair(String s, String s1) {
        this.name = s;
        this.value = s1;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
