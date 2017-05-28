package istat.android.widget.fragment.input;

import istat.android.base.tools.ToolKits;
import istat.android.widget.datas.stucture.BasicNameValuePair;
import istat.android.widget.datas.stucture.NameValueList;
import istat.android.widget.view.TouchMe;
import istat.android.widget.R;

import java.util.ArrayList;
import java.util.List;

import android.Manifest.permission;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

public abstract class BasicInflater extends Fragment {
    // private Bundle bund;
    protected ViewGroup baseComposite, parentView;
    protected LinearLayout base;
    protected LayoutInflater inflater;
    protected TouchMe addView;
    protected Context mContext;
    private boolean vibrationEnable = true;
    protected boolean inputEnable = true;
    protected TextView headerText;
    protected int maxInflatable = 6;
    protected int minInflatable = 0;
    private int inflationCount = 0;
    private Vibrator vbr;
    private List<Restriction> restriction = new ArrayList<Restriction>();
    private int icone_misus = android.R.drawable.ic_menu_close_clear_cancel;
    private int icone_plus = android.R.drawable.ic_menu_add;
    protected int INFLATION_ROW = 0;
    List<View> listInflation = new ArrayList<View>();
    // HashMap<View, View> listInflationDetails = new HashMap<View, View>();
    Bundle savedInstanceState;

    protected List<View> getListInflation() {
        return listInflation;
    }

    protected View getInflation(int index) {
        if (listInflation.size() > index)
            return listInflation.get(index);
        else return null;
    }

    protected View getInflationType(int index) {
        View base = getInflation(index);
        if (base != null) {
            return base.findViewById(R.id.type);
        }
        return null;
    }

    protected View getInflationContent(int index) {
        View base = getInflation(index);
        if (base != null) {
            return base.findViewById(R.id.content);
        }
        return null;
    }

    protected void setInflationLayout(int layout) {
        INFLATION_ROW = layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        this.inflater = inflater;
        this.mContext = inflater.getContext();
        parentView = container;
        baseComposite = (ViewGroup) inflater.inflate(R.layout.base_inflater,
                container, false);
        base = new LinearLayout(mContext);
        base.setOrientation(LinearLayout.VERTICAL);
        baseComposite.addView(base, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        initComponent();
        onRestoreComponent(savedInstanceState);
        return baseComposite;
    }

    public NameValueList getDatas() {
        NameValueList data = new NameValueList();

        for (View baseView : listInflation) {
            ViewGroup tmpGroup = (ViewGroup) baseView;
            onGetDatas(data, tmpGroup.findViewById(R.id.type),
                    tmpGroup.findViewById(R.id.content));
        }

        if (data.size() >= maxInflatable) {
            addView.setBackgroundResource(icone_misus);
        }

        return data;
    }

    protected abstract void onGetDatas(NameValueList datas, View name,
                                       View value);

    public List<String> getDataValues() {
        List<String> data = new ArrayList<String>();

        for (View baseView : listInflation) {
            ViewGroup tmpGroup = (ViewGroup) baseView;
            data.add(((EditText) tmpGroup.findViewById(R.id.content)).getText()
                    .toString());

        }
        return data;
    }

    public List<String> getDataNames() {
        List<String> data = new ArrayList<String>();

        for (View baseView : listInflation) {
            ViewGroup tmpGroup = (ViewGroup) baseView;
            data.add(((Button) tmpGroup.findViewById(R.id.type)).getText()
                    .toString());

        }
        return data;
    }

    public int getItemsCount() {
        return listInflation.size();
    }

    private void initInflateContent(final View layout) {
        final View content = layout.findViewById(R.id.content);
        TouchMe minus = (TouchMe) layout.findViewById(R.id.minus);
        OnClickListener onClick = new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (v.getId() == R.id.minus) {
                    int index = listInflation.indexOf(layout);
                    onClickOnRemoveItem(layout, index);
                    removeLayout(layout);
                }
            }

        };
        minus.setOnClickListener(onClick);
        // -------------------------------
        content.setSelected(true);
        // --------------------------------
        onPerformInflation(layout);
        if (inflationCount < minInflatable) {
            minus.setVisibility(TouchMe.INVISIBLE);
        }

    }

    protected abstract void onInitComponent(View basView);

    private void initComponent() {
        addView = (TouchMe) ((ViewGroup) baseComposite).findViewById(R.id.plus);
        vbr = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
        headerText = (TextView) ((ViewGroup) baseComposite)
                .findViewById(R.id.headertext);
        addView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (inflationCount >= maxInflatable - 1) {
                    addView.setBackgroundResource(icone_misus);
                    addView.setVisibility(View.INVISIBLE);
                }
                if (inflationCount >= maxInflatable) {
                    if (isVibrationEnable())
                        vbr.vibrate(90);
                } else {
                    addItem();
                    View view = listInflation.get(listInflation.size() - 1)
                            .findViewById(R.id.content);
                    view.requestFocus();
                    view.setSelected(true);

                }
            }
        });
        onInitComponent(baseComposite);
    }

    protected abstract void onPerformInflation(final View layout);

	/*
     * private void removeLayout(View layout) { ((ViewGroup)
	 * base).removeView(layout);
	 * 
	 * inflationCount--; listInflation.remove(layout); if (inflationCount <
	 * maxInflatable) { if (maxInflatable != minInflatable) ;
	 * addView.setVisibility(View.VISIBLE);
	 * addView.setBackgroundResource(icone_plus);//
	 * plus.setVisibility(TouchMe.VISIBLE); }
	 * 
	 * }
	 */

    protected void addItem() {
        addItem(null, null);
    }

    public final void addItem(String name, String value) {
        if (base != null && inflater != null) {
            View layout = inflater.inflate(INFLATION_ROW, null/* parentView */);
            // ((LinearLayout) base).addView(layout);
            ((LinearLayout) base).addView(layout);
            listInflation.add(layout);
            initInflateContent(layout);
            inflationCount++;
            View nameV = layout.findViewById(R.id.type), valueV = layout
                    .findViewById(R.id.content);
            onInflationComplete(nameV, valueV, name, value);
            onInputEnable(nameV, valueV, inputEnable);
            onAddItem(inflationCount);
        }
    }

	/*
     * protected void addItem() { if (base != null && inflater != null) {
	 * 
	 * View layout = inflater.inflate(INFLATION_ROW, parentView); //
	 * ((LinearLayout) base).addView(layout); ((LinearLayout)
	 * base).addView(layout, new LayoutParams( LayoutParams.MATCH_PARENT,
	 * LayoutParams.WRAP_CONTENT)); listInflation.add(layout);
	 * initInflateContent(layout); inflationCount++; } }
	 */

    protected abstract void onInflationComplete(View nameV, View valueV,
                                                String name, String value);

    public void removeAllItem() {
        try {
            for (View view : listInflation)
                ((ViewGroup) base).removeView(view);
            listInflation = new ArrayList<View>();
            inflationCount = 0;
            // plus.setVisibility(TouchMe.VISIBLE);
        } catch (Exception e) {
        }
    }

    public void removeItem(int position) {
        if (position < listInflation.size()) {
            View layout = listInflation.get(position);
            inflationCount--;
            listInflation.remove(layout);
            ((ViewGroup) base).removeView(layout);
            if (inflationCount < maxInflatable) {
                if (maxInflatable != minInflatable)
                    ;
                addView.setVisibility(View.VISIBLE);
                addView.setBackgroundResource(icone_plus);// plus.setVisibility(TouchMe.VISIBLE);
            }
            onRemoveItem(position);

        }
    }

    private void removeLayout(View layout) {
        int index = listInflation.indexOf(layout);
        if (index >= 0)
            removeItem(index);

    }

    protected void onClickOnRemoveItem(View view, int index) {

    }

    protected void onRemoveItem(int index) {

    }

    protected void onAddItem(int index) {

    }

    public void setMaxInflatable(int maxInflatable) {
        this.maxInflatable = maxInflatable;
    }

    public void setMinInflatable(int minInflatable) {
        this.minInflatable = minInflatable;
        int dif = 0;
        if (inflationCount < minInflatable) {
            dif = minInflatable - inflationCount;
            for (int i = 0; i < dif; i++)
                addItem();
        }
    }

    public void setHeaderText(String text) {
        headerText.setText(text);
    }

    public void setVibrationEnable(boolean vibrationEnable) {
        this.vibrationEnable = vibrationEnable;
    }

    public boolean isVibrationEnable() {
        return ToolKits.Software.hasPermission(getActivity(),
                permission.VIBRATE) && vibrationEnable;
    }

    public void pushRestrinction(String type, int number) {
        boolean update = false;
        for (Restriction rect : restriction) {
            if (rect.type.equals(type)) {
                restriction.remove(rect);
                update = true;
            }
        }
        if (!update)
            restriction.add(new Restriction(type, number));
    }

    public void removeRestriction(String type) {
        for (Restriction rect : restriction) {
            if (rect.type.equals(type))
                restriction.remove(rect);
        }
    }

    public void setData(NameValueList datas) {
        removeAllItem();
        for (BasicNameValuePair data : datas)
            addItem(data.getName(), data.getValue());
    }

    public void addData(NameValueList datas) {
        for (BasicNameValuePair data : datas)
            addItem(data.getName(), data.getValue());
    }

    public void iconeConfig(int plus, int minus) {
        icone_misus = minus;
        icone_plus = plus;
    }

    private void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        // imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private static class Restriction {
        public String type;
        public int number;

        public Restriction(String type, int number) {
            this.type = type;
            this.number = number;
        }

        /**
         * @param type
         * @param restrictList
         * @return a int that represent type's Restriction limit from a
         * RestrictionList
         */
        public static int getRestrictionLimitFromList(String type,
                                                      List<Restriction> restrictList) {
            for (Restriction restriction : restrictList) {
                if (restriction.type.equals(type))
                    return restriction.number;
            }

            return 0;
        }
    }

    public void setInputEnable(boolean value) {
        inputEnable = value;
    }

    public boolean isInputEnable() {
        return inputEnable;
    }

    public void setInflationLayoutVisibility(int value) {
        base.setVisibility(value);
    }

    public void setInflationLayoutVisible(boolean value) {
        base.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    public boolean isInflationLayoutVisible() {
        return base.getVisibility() == View.VISIBLE;
    }

    public LinearLayout getBaseInflation() {
        return base;
    }

    public ViewGroup getParentView() {
        return parentView;
    }

    @Override
    public View getView() {
        // TODO Auto-generated method stub
        return baseComposite != null ? baseComposite : super.getView();
    }

    public TextView getHeaderTextView() {
        return headerText;
    }

    protected void onInputEnable(View nameV, View valueV, boolean state) {
        if (nameV != null)
            nameV.setEnabled(state);
        if (valueV != null)
            valueV.setEnabled(state);
    }

    public void setHeaderBackgroundColor(int color) {
        getView().findViewById(R.id.headerLayout).setBackgroundColor(color);
    }

    public void setInflationBackgroundColor(int color) {
        base.setBackgroundColor(color);
    }

    public boolean isRestoring() {
        return savedInstanceState != null;
    }

    protected void onRestoreComponent(Bundle saved) {

    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return getListInflation().size();
    }
}
