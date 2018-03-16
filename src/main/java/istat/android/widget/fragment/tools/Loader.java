package istat.android.widget.fragment.tools;

import istat.android.base.utils.ImageLoader;
import istat.android.widget.R;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Loader extends Fragment implements ImageLoader.LoadCallback {
View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView=(ViewGroup) inflater.inflate(R.layout.fragment_loader_layout, container, false);
		return rootView;
	}
	public void setLoadText(String text){
		getLoadTextView().setText(text);
	}
	public void setLoadText(int text){
		getLoadTextView().setText(text);
	}
	public void setLoadImage(int image){
		getLoadImageView().setImageResource(image);
	}
	public void displayLoadImage(String imageURL){
		new ImageLoader(getActivity()).displayImage(imageURL, getLoadImageView(), this);
		getLoadImageView().setVisibility(View.VISIBLE);
	}
	public void  setLoadImageVisible(boolean visible){
		((ImageView)findViewById(R.id.loadImageView)).setVisibility(visible?View.VISIBLE:View.INVISIBLE);
	}
	public void display(boolean display){
		if(display)
	rootView.setVisibility(View.VISIBLE);
		else
			rootView.setVisibility(View.INVISIBLE);
	}
	
	public TextView getLoadTextView(){
		return ((TextView)findViewById(R.id.loadTextView));
	}
	public ImageView getLoadImageView(){
		return ((ImageView)findViewById(R.id.loadImageView));
	}
	public ProgressBar getLoadProgressBar(){
		return ((ProgressBar)findViewById(R.id.loadProgress));
	}
	public  View findViewById(int id){
		return rootView.findViewById(id);	
	}

	@Override
	public boolean onLoad(ImageLoader.PhotoToLoad phLoad) {
		return false;
	}

	@Override
	public void onLoadCompleted(ImageLoader.PhotoToLoad phLoad, boolean success) {

	}

	@Override
	public boolean onLoadSucceed(ImageLoader.PhotoToLoad phLoad, Bitmap bitmap) {
		return false;
	}

	@Override
	public boolean onLoadError(ImageLoader.PhotoToLoad phLoad, Throwable error) {
		return false;
	}
}
