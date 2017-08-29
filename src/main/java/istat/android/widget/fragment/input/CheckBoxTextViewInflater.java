package istat.android.widget.fragment.input;

import istat.android.widget.datas.BasicNameValuePair;
import istat.android.widget.datas.NameValueList;
import istat.android.widget.R;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TextView;

public class CheckBoxTextViewInflater extends BasicInflater {
    private int inputType = InputType.TYPE_CLASS_TEXT;
    private boolean valueHint = false;
    private String globalHint = "";

    @Override
    protected void onPerformInflation(View layout) {
        final TextView content = (TextView) layout.findViewById(R.id.content);
        content.setInputType(inputType);
        content.setSelected(true);
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    @Override
    protected void onGetData(NameValueList data, View name,
                             View value) {
        String tmp = ((TextView) value).getText().toString();
        data.add(new BasicNameValuePair("" + (((CheckBox) name).isChecked()),
                tmp));
    }

    @Override
    protected void onInflationComplete(View nameV, View valueV, String name,
                                       String value) {
        TextView textView = (TextView) valueV;
        if (!TextUtils.isEmpty(globalHint))
            textView.setHint(value);
        if (valueHint) {
            textView.setHint(value);
        } else {
            textView.setText(value);
        }
        valueHint = false;
    }

    public void addItem(String name, String value, boolean hint) {
        valueHint = hint;
        super.addItem(name, value);

    }

    public void addItem(String name, String value, String hint) {
        globalHint = hint;
        super.addItem(name, value);
        globalHint = null;

    }

    @Override
    protected void onInitComponent(View basView) {
        setInflationLayout(R.layout.include_cheker_text_inflation);
    }

    public void setGlobalHint(String globalHint) {
        this.globalHint = globalHint;
    }

}