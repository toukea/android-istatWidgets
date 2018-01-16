package istat.android.widget.fragment.input;

import istat.android.widget.datas.BasicNameValuePair;
import istat.android.widget.datas.NameValueList;
import istat.android.widget.R;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class CheckBoxEditTextInflater extends BasicInflater {
    private int inputType = InputType.TYPE_CLASS_TEXT;
    private boolean valueHint = false;
    private String globalHint = "";

    @Override
    protected void onPerformInflation(View layout) {
        final EditText content = (EditText) layout.findViewById(R.id.content);
        content.setInputType(inputType);
        content.setSelected(true);
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    @Override
    protected void onGetData(NameValueList data, View name,
                             View value) {
        String tmp = ((EditText) value).getText().toString();
        data.add(new BasicNameValuePair("" + (((CheckBox) name).isChecked()),
                tmp));

    }


    @Override
    protected void onInflationComplete(View nameV, View valueV, String name,
                                       String value) {
        EditText edt = (EditText) valueV;
        if (!TextUtils.isEmpty(globalHint))
            edt.setHint(value);
        if (valueHint) {
            edt.setHint(value);
        } else {
            edt.setText(value);
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
        setInflationLayout(R.layout.include_cheker_inflation);
    }

    public void setGlobalHint(String globalHint) {
        this.globalHint = globalHint;
    }

}