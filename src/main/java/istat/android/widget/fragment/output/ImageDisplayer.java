package istat.android.widget.fragment.output;

import istat.android.base.image.ImageBitmap;
import istat.android.base.image.ImageLoader;
import istat.android.base.sys.AsyncAction;
import istat.android.base.util.ToolKits;
import istat.android.widget.R;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ImageDisplayer extends Fragment {
	protected View base;
	protected ViewGroup baseComposite;
	protected static int LAYOUT = R.layout.layout_imagefragment;
	private ImageView imageView;
	private ProgressBar loader;
	boolean loaderEnable = true, autoResize = false;
	Bitmap onLoadImage, onEchecImage;
	public static int QUALITY_HIGHT = 0, QUALITY_LOW = 1;
	private int imageQuality = 0;

	public ImageDisplayer() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		base = inflater.inflate(LAYOUT, container);
		baseComposite = container;
		initCompoments(base);
		return base;
	}

	void initCompoments(View layout) {
		imageView = (ImageView) base.findViewById(R.id.imageView);
		loader = (ProgressBar) base.findViewById(R.id.loader);
		// imgLoader=new ImageLoader(getActivity());
	}

	public void loadImage(String url) {
		if (isLoaderEnable())
			setLoaderVisible(true);
		else
			setLoaderVisible(false);
		this.new LoadAction(url).execute();
	}

	public ImageDisplayer setOnEchecImage(Bitmap onEchecImage) {
		this.onEchecImage = onEchecImage;
		return this;
	}

	public ImageDisplayer setOnLoadImage(Bitmap onLoadImage) {
		this.onLoadImage = onLoadImage;
		return this;
	}

	public ImageDisplayer setOnEchecImage(int onEchecImage) {
		this.onEchecImage = ImageBitmap.getBitmapFromRessource(getActivity(),
				onEchecImage);
		return this;
	}

	public ImageDisplayer setOnLoadImage(int onLoadImage) {
		this.onLoadImage = ImageBitmap.getBitmapFromRessource(getActivity(),
				onLoadImage);
		return this;
	}

	public ImageDisplayer setLoaderEnable(boolean loaderEnable) {
		this.loaderEnable = loaderEnable;
		return this;
	}

	public ImageDisplayer setAutoResize(boolean autoResize) {
		this.autoResize = autoResize;
		return this;
	}

	public void setLoaderVisible(boolean loaderVisible) {

		if (loaderVisible)
			loader.setVisibility(View.VISIBLE);
		else
			loader.setVisibility(View.INVISIBLE);
	}

	public boolean isLoaderEnable() {
		return loaderEnable;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public ProgressBar getLoader() {
		return loader;
	}

	public View getBaseInflation() {
		return base;
	}

	public int getImageQuality() {
		return imageQuality;
	}

	public ImageDisplayer setImageQuality(int imageQuality) {
		this.imageQuality = imageQuality;
		return this;
	}

	private class LoadAction extends AsyncAction {
		String url;
		Bitmap image;

		public LoadAction(String url) {
			this.url = url;
		}

		@Override
		public void onActionPreExecute() {
			// TODO Auto-generated method stub
			imageView.setImageBitmap(onLoadImage);
		}

		@Override
		public void onExecute() {
			// TODO Auto-generated method stub
			// image=ImageBitmap.getBitmapFromURL(url);
			if (ToolKits.WordFormat.isNumber(url))
				image = ImageBitmap.getBitmapFromRessource(getActivity(),
						Integer.valueOf(url));
			else
				image = ImageLoader.getBitmap(url, getActivity(), imageQuality);
		}

		@Override
		public void onActionProgress(Message msg) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onActionComplete() {
			// TODO Auto-generated method stub
			setLoaderVisible(false);

			if (image != null) {
				if (autoResize) {
					imageView.setBackgroundDrawable(new BitmapDrawable(image));
					imageView.setImageDrawable(null);
				} else
					imageView.setImageBitmap(image);
			} else
				imageView.setImageBitmap(onEchecImage);

			onCompleteImageLoad(image, url);

		}

		@Override
		public void onStop() {
			// TODO Auto-generated method stub

		}

	}

	/*
	 * do when the ImageLoading is complete
	 */
	protected void onCompleteImageLoad(Bitmap bitmap, String url) {

	}

}
