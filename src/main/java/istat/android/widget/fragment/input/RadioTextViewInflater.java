package istat.android.widget.fragment.input;

import istat.android.widget.datas.stucture.NameValueList;
import istat.android.widget.R;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

public class RadioTextViewInflater extends BasicInflater {

	@Override
	protected void onGetDatas(NameValueList datas, View name, View value) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onInitCompoment(View basView) {
		// TODO Auto-generated method stub
		setInflationLayout(R.layout.include_radio_text_inflation);
	}

	@Override
	protected void onPerformInflation(View layout) {
		// TODO Auto-generated method stub
		final RadioButton type = (RadioButton) layout
				.findViewById(R.id.type);
		OnClickListener oncl = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.type) {
					unCheckAll();
					((RadioButton) v).setChecked(true);
				}
			}

		};
		type.setOnClickListener(oncl);
	}

	@Override
	protected void onInflationComplete(View nameV, View valueV, String name,
			String value) {
		// TODO Auto-generated method stub
		RadioButton radio = (RadioButton) nameV;
		radio.setChecked(Boolean.valueOf(name));
		((TextView) valueV).setText(value);
	}

	private void unCheckAll() {
		try {
			for (View view : listInflation)
				((RadioButton) (((ViewGroup) view).findViewById(R.id.type)))
						.setChecked(false);
		} catch (Exception e) {
		}
	}
}