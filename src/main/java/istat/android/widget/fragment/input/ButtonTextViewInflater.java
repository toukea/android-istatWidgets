package istat.android.widget.fragment.input;

import istat.android.widget.datas.BasicNameValuePair;
import istat.android.widget.datas.NameValueList;
import istat.android.widget.R;

import java.util.List;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ButtonTextViewInflater extends BasicInflater {
    private int inputType = InputType.TYPE_CLASS_TEXT;
    private CharSequence[] choices = new CharSequence[]{"..."};
    private boolean valueHint = false;
    private String globalHint = "";
    String dialogTitle = "choisir le type";

    @Override
    protected void onPerformInflation(View layout) {
        
        final Button type = (Button) layout.findViewById(R.id.type);
        final EditText content = (EditText) layout.findViewById(R.id.content);
        OnClickListener oncl = new OnClickListener() {

            @Override
            public void onClick(View v) {
                
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
    protected void onGetData(NameValueList datas, View name,
                             View value) {
        
        datas.add(new BasicNameValuePair(((Button) name).getText().toString(),
                ((EditText) value).getText().toString()));

    }

    public void setChoiceList(CharSequence[] choices) {
        this.choices = choices;
    }

    @Override
    protected void onInflationComplete(View nameV, View valueV, String name,
                                       String value) {
        
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