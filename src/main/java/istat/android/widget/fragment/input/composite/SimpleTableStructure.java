package istat.android.widget.fragment.input.composite;

import istat.android.widget.fragment.input.SimpleEditTextInflater;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SimpleTableStructure extends Fragment {
	String[] rowTitles=new String[0];
	List<SimpleEditTextInflater> rowList = new ArrayList<SimpleEditTextInflater>();
	ViewGroup baseView;
	public void setRowTitles(String[] rowTitles) {
		this.rowTitles = rowTitles;
	}

//	@Override
//	public void onViewCreated(View view, Bundle savedInstanceState) {
//
//		super.onViewCreated(view, savedInstanceState);
//		FragmentTransaction t=getFragmentManager().beginTransaction();
//		for(String s:rowTitles){
//			LinearLayout layout=new LinearLayout(getActivity());
//			layout.setOrientation(LinearLayout.VERTICAL);
//			((ViewGroup)getView()).addView(layout);
//			SimpleFra
//			t.add(arg0, arg1)
//		}
//		t.commit();
//	}
	
}
