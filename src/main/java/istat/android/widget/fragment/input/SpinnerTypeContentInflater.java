package istat.android.widget.fragment.input;

import android.view.ViewGroup.LayoutParams;
import istat.android.widget.R;
import istat.android.widget.view.TouchMe;
import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SpinnerTypeContentInflater extends SimpleSpinnerInflater {
	private List<String[]> subChoices = new ArrayList<String[]>();
	private String dialogTitle = "Choisir le type";

	@Override
	protected void onPerformInflation(View layout) {
		// TODO Auto-generated method stub
		super.onPerformInflation(layout);
		final Button type = (Button) layout.findViewById(R.id.type);
		final Spinner content = (Spinner) layout.findViewById(R.id.content);
		TouchMe minus = (TouchMe) layout.findViewById(R.id.minus);
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
		content.setSelected(true);
		type.setText(getRestrictedChoice()[0]);
		if (0 < subChoices.size()) {
			content.setAdapter(new ArrayAdapter<String>(getActivity(),
					spinnerLayout, subChoices.get(0)));
		}

		if (maxInflatable == minInflatable) {
			android.widget.LinearLayout.LayoutParams params = new android.widget.LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
					0.338f);

			android.widget.LinearLayout.LayoutParams params2 = new android.widget.LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,
					0.662f);
			minus.setVisibility(View.GONE);
			content.setLayoutParams(params);
			type.setLayoutParams(params2);
		}

	}

	@Override
	protected void onInflationComplete(View nameV, View valueV, String name,
			String value) {
		// TODO Auto-generated method stub
		super.onInflationComplete(nameV, valueV, name, value);
		Button button = (Button) nameV;
		Spinner spinner = (Spinner) valueV;
		button.setText(name);
		String[] current_choice = subChoices.get(indexOfInArray(choices, name));
		spinner.setAdapter(new ArrayAdapter<String>(getActivity(),
				spinnerLayout, current_choice));
		spinner.setSelection(indexOfInArray(current_choice, value));
	}

	private int indexOfInArray(String[] array, String choiceString) {
		int index = 0;
		for (CharSequence string : array) {
			if (string.toString().equals(choiceString)) {
				break;
			}
			index++;
		}
		return index;
	}

	private void showTypeChooser(final Button type, final Spinner content) {
		final CharSequence[] items = choices;
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle(dialogTitle);
		// builder.setCancelable(false);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				type.setText(items[item]);
				content.setFocusable(true);
				content.setSelected(true);
				content.requestFocus();
				if (item < subChoices.size())
					content.setAdapter(new ArrayAdapter<String>(getActivity(),
							spinnerLayout, subChoices.get(item)));
				dialog.cancel();

			}
		});
		AlertDialog alert = builder.create();
		alert.show();

		// Array<String> a=new ArrayList(java.util.Arrays.asList(new
		// String[]{""}));
	}

	public void setChooseDialogTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}

	@Override
	protected void onInitCompoment(View basView) {
		// TODO Auto-generated method stub
		setInflationLayout(R.layout.include_type_spinner_inflation);
	}

	private CharSequence[] getRestrictedChoice() {
		// List<CharSequence> tmpChoice = Arrays.asList(choices);
		// for (CharSequence choice : choices) {
		//
		// }

		return choices;
	}

	public void setChoiceList(String[] choices, List<String[]> subChoices) {
		this.choices = choices;
		this.subChoices = subChoices;
	}
}