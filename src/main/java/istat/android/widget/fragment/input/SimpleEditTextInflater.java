package istat.android.widget.fragment.input;


import java.util.List;

import istat.android.widget.datas.BasicNameValuePair;
import istat.android.widget.datas.NameValueList;
import istat.android.widget.R;


import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class SimpleEditTextInflater extends BasicInflater {
	protected int inputType = InputType.TYPE_CLASS_TEXT;
	private String globalHint="...";boolean autoFocus=false;
	@Override
	protected void onPerformInflation(View layout) {
		
		final EditText content = (EditText) layout.findViewById(R.id.content);
		layout.findViewById(R.id.type).setVisibility(View.GONE);
		content.setInputType(inputType);
		if(autoFocus)
		content.requestFocus();
	}

	public void setInputType(int inputType) {
		this.inputType = inputType;
	}

	@Override
	protected void onGetData(NameValueList datas, View name,
                             View value) {
		
		String tmp=((EditText) value).getText().toString();
		datas.add(new BasicNameValuePair(tmp,
				tmp));
		
	}


	@Override
	protected void onInflationComplete(View nameV, View valueV, String name,
			String value) {
		
		EditText edt = (EditText) valueV;
		if (!TextUtils.isEmpty(value))
			edt.setHint(value);
		else edt.setHint(globalHint);
		if(name==null) return ;
		edt.setText(name);
	}
	public void addItem(String value) {
		
		super.addItem(value, null);

	}
	public void addItem(String value, boolean hint) {
		
		super.addItem(value, null);

	}

	@Override
	protected void onInitComponent(View basView) {
		
		setInflationLayout(R.layout.include_cheker_inflation);
	}
	public void setGlobalHint(String globalHint) {
		if(globalHint!=null)
		this.globalHint = globalHint;
	}
	public void setAutoFocusOnInflate(boolean autoFocus) {
		this.autoFocus = autoFocus;
	}
	public void setData(List<String> dataS){
		for(String s:dataS){
			addItem(s);
		}
	}
}