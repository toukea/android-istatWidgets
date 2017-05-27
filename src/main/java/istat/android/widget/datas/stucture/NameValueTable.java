package istat.android.widget.datas.stucture;

import istat.android.base.tools.JSON;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class NameValueTable extends ArrayList<NameValueList> implements Parcelable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NameValueTable(){}
	@Override
	public String toString(){
		try{
			return toJson().toString();
		}catch(Exception e){
			super.toString();
		}
		return null;
		
	}
	public NameValueTable(String jsons) throws JSONException{
		JSONObject json=new JSONObject(jsons);
		for(JSONObject jsonO:JSON.JSONArrayToJsonList(json.getJSONArray("name_value_table")))
			{
			Log.d("debug", jsonO.toString());
			add(new NameValueList(jsonO.toString()));
			}
	}
	public JSONObject toJson() throws JSONException{
		JSONObject json=new JSONObject();
		for(NameValueList pair:this){
			json.accumulate("name_value_table", pair.toJson());
		}
		return json;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(toString());
	}
	public static final Parcelable.Creator<NameValueTable> CREATOR = new Parcelable.Creator<NameValueTable>() {
		@Override
		public NameValueTable createFromParcel(Parcel src) {
			
			return null;
		}

		@Override
		public NameValueTable[] newArray(int size) {
			// TODO Auto-generated method stub
			return new NameValueTable[size];
		}
	
	};
}

