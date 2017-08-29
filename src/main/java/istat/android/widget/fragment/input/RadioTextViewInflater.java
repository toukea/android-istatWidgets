package istat.android.widget.fragment.input;

import istat.android.widget.datas.NameValueList;
import istat.android.widget.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

public class RadioTextViewInflater extends BasicInflater {

    @Override
    protected void onGetData(NameValueList data, View name, View value) {

    }

    @Override
    protected void onInitComponent(View basView) {
        setInflationLayout(R.layout.include_radio_text_inflation);
    }

    @Override
    protected void onPerformInflation(View layout) {

        final RadioButton type = (RadioButton) layout
                .findViewById(R.id.type);
        OnClickListener onClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.type) {
                    unCheckAll();
                    ((RadioButton) v).setChecked(true);
                }
            }

        };
        type.setOnClickListener(onClickListener);
    }

    @Override
    protected void onInflationComplete(View nameV, View valueV, String name,
                                       String value) {
        RadioButton radio = (RadioButton) nameV;
        radio.setChecked(Boolean.valueOf(name));
        ((TextView) valueV).setText(value);
    }

    protected void unCheckAll() {
        try {
            for (View view : listInflation)
                ((RadioButton) ((view).findViewById(R.id.type)))
                        .setChecked(false);
        } catch (Exception e) {
        }
    }
}