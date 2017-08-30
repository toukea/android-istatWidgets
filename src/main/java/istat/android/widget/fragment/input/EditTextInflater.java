package istat.android.widget.fragment.input;

import android.view.View;

import java.util.List;

import istat.android.widget.datas.BasicNameValuePair;
import istat.android.widget.datas.NameValueList;

/**
 * Created by istat on 29/08/17.
 */

public class EditTextInflater extends ButtonTextViewInflater {
    public void setData(List<String> keyWords) {
        NameValueList list = new NameValueList();
        for (String value : keyWords) {
            list.add(new BasicNameValuePair("", value));
        }
        super.setData(list);
    }

    @Override
    protected void onInflationComplete(View nameV, View valueV, String name, String value) {
        super.onInflationComplete(nameV, valueV, name, value);
        nameV.setVisibility(View.GONE);
    }

    @Override
    protected void onInitComponent(View basView) {
        super.onInitComponent(basView);
    }
}
