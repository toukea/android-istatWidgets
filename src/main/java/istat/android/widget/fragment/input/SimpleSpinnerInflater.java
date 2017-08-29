package istat.android.widget.fragment.input;

import istat.android.base.tools.ToolKits;
import istat.android.widget.datas.BasicNameValuePair;
import istat.android.widget.datas.NameValueList;
import istat.android.widget.R;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SimpleSpinnerInflater extends BasicInflater {
	protected String[] choices = new String[] { "..." };
	protected int spinnerLayout=android.R.layout.simple_list_item_1;

	@Override
	protected void onPerformInflation(View layout) {
		
		final Spinner choiceSpinner = (Spinner) layout
				.findViewById(R.id.content);
		choiceSpinner.setAdapter(new ArrayAdapter(mContext,
				spinnerLayout, choices));

	}
	public void setSpinnerLayout(int spinnerLayout) {
		this.spinnerLayout = spinnerLayout;
	}

	@Override
	protected void onGetData(NameValueList datas, View name, View value) {
		
		String tmp = ((EditText) value).getText().toString();
		datas.add(new BasicNameValuePair(tmp, tmp));

	}

	@Override
	protected void onInflationComplete(View nameV, View valueV, String name,
			String value) {
		
		((Spinner)valueV).setSelection(ToolKits.Word.parseInt(value));
	}
	public void addItem(int position) {
		
		super.addItem(null, position+"");
	}

	@Override
	protected void onInitComponent(View basView) {
		
		setInflationLayout(R.layout.include_spinner_inflation);
	}

	public List<Integer> getDataAsPositions() {
		List<Integer> data = new ArrayList<Integer>();

		for (View baseView : listInflation) {
			ViewGroup tmpGroup = (ViewGroup) baseView;
			data.add(((Spinner) tmpGroup.findViewById(R.id.content))
					.getSelectedItemPosition());

		}
		return data;
	}

	public static String[] convertToStringArray(CharSequence[] charSequences) {
		if (charSequences instanceof String[]) {
			return (String[]) charSequences;
		}

		String[] strings = new String[charSequences.length];
		for (int index = 0; index < charSequences.length; index++) {
			strings[index] = charSequences[index].toString();
		}

		return strings;
	}

	public void setChoiceList(String[] choices) {
		this.choices = choices;
	}

}
