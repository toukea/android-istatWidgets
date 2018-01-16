package istat.android.widget.datas;

import istat.android.base.tools.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class NameValueList extends ArrayList<BasicNameValuePair> implements
        Parcelable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public List<String> getValuesList() {
        List<String> values = new ArrayList<String>();
        for (BasicNameValuePair bnv : this)
            values.add(bnv.getValue());

        return values;
    }

    public List<String> getNamesList() {
        List<String> values = new ArrayList<String>();
        for (BasicNameValuePair bnv : this)
            values.add(bnv.getName());

        return values;
    }

    public NameValueList(ArrayList<BasicNameValuePair> values) {
        super(values);
    }

    public NameValueList(BasicNameValuePair... value) {
        super(Arrays.asList(value));
    }

    public NameValueList(List<String> names, List<String> values) {
        if (names.size() != values.size())
            return;
        for (int i = 0; i < names.size(); i++) {
            add(new BasicNameValuePair(names.get(i), values.get(i)));
        }
    }

    @Override
    public String toString() {
        try {
            return toJson().toString();
        } catch (Exception e) {
            super.toString();
        }
        return null;

    }

    public NameValueList() {
    }

    public NameValueList(String jsons) throws JSONException {
        JSONObject json = new JSONObject(jsons);
        JSONArray tmp = json.optJSONArray("name_value_list");
        if (tmp == null) {
            JSONObject jsonO = json.optJSONObject("name_value_list");
            add(new BasicNameValuePair(jsonO.optString("name"),
                    jsonO.optString("value")));
            return;
        }
        for (JSONObject jsonO : JSON.JSONArrayToJsonList(json
                .getJSONArray("name_value_list")))
            add(new BasicNameValuePair(jsonO.optString("name"),
                    jsonO.optString("value")));
    }

    public JSONObject toJson() throws JSONException {
        JSONObject json = new JSONObject();
        JSONObject basejson = null;
        for (BasicNameValuePair pair : this) {
            basejson = new JSONObject();
            basejson.accumulate("name", pair.getName());
            basejson.accumulate("value", pair.getValue());
            json.accumulate("name_value_list", basejson);
        }
        return json;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(toString());
    }

    public static final Parcelable.Creator<NameValueList> CREATOR = new Parcelable.Creator<NameValueList>() {
        @Override
        public NameValueList createFromParcel(Parcel src) {
            try {
                return new NameValueList(src.readString());
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public NameValueList[] newArray(int size) {

            return new NameValueList[size];
        }

    };
}
