package istat.android.widget.fragment.input;

import istat.android.widget.datas.stucture.NameValueList;
import istat.android.widget.view.TouchMe;
import istat.android.widget.R;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ButtonTextViewInflater extends BasicInflater {
	private int inputType = InputType.TYPE_CLASS_TEXT;
	private CharSequence[] choices = new CharSequence[] { "..." };
	private boolean valueHint = false;
	private String globalHint = "";
	String dialogTitle="choisir le type";

	@Override
	protected void onPerformInflation(View layout) {
		// TODO Auto-generated method stub
		final Button type = (Button) layout.findViewById(R.id.type);
		final EditText content = (EditText) layout.findViewById(R.id.content);
		OnClickListener oncl = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.type) {
					showTypeChooser(type, content);
				}
			}

		};
		type.setOnClickListener(oncl);
		content.setOnClickListener(oncl);
		content.setInputType(inputType);
		content.setSelected(true);
		type.setText(getRestrictedChoice()[0]);
		content.setHint(getRestrictedChoice()[0]);
	}

	public void setInputType(int inputType) {
		this.inputType = inputType;
	}

	private CharSequence[] getRestrictedChoice() {
		List<CharSequence> tmpChoice = java.util.Arrays.asList(choices);
		for (CharSequence choice : choices) {

		}

		return choices;
	}

	@Override
	protected void onGetDatas(NameValueList datas, View name,
			View value) {
		// TODO Auto-generated method stub
		datas.add(new BasicNameValuePair(((Button) name).getText().toString(),
				((EditText) value).getText().toString()));
		
	}

	public void setChoiceList(CharSequence[] choices) {
		this.choices = choices;
	}

	@Override
	protected void onInflationComplete(View nameV, View valueV, String name,
			String value) {
		// TODO Auto-generated method stub
		Button btn = (Button) nameV;
		EditText edt = (EditText) valueV;
		btn.setText(name);
		if (!TextUtils.isEmpty(globalHint))
			edt.setHint(value);
		if (valueHint)
			edt.setHint(value);
		else
			edt.setText(value);
		
		valueHint = false;
	}

	public void addItem(String name, String value, boolean hint) {
		// TODO Auto-generated method stub
		valueHint = hint;
		super.addItem(name, value);

	}
	public void addItem(String name, String value, String hint) {
		// TODO Auto-generated method stub
		globalHint=hint;
		super.addItem(name, value);
		globalHint=null;

	}

	@Override
	protected void onInitCompoment(View basView) {
		// TODO Auto-generated method stub
		setInflationLayout(R.layout.include_inflation);
	}

	private void showTypeChooser(final Button type, final EditText content) {
		final CharSequence[] items = choices;
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle(dialogTitle);
		// builder.setCancelable(false);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {

				content.setHint(items[item] + "...");
				type.setText(items[item]);
				content.setFocusable(true);
				content.setSelected(true);
				content.requestFocus();

				dialog.cancel();

			}
		});
		AlertDialog alert = builder.create();
		alert.show();

		// Array<String> a=new ArrayList(java.util.Arrays.asList(new
		// String[]{""}));
	}

	public void setGlobalHint(String globalHint) {
		this.globalHint = globalHint;
	}
	public void setChooseDialogTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}


}