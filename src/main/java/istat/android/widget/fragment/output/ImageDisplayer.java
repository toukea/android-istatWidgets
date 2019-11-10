package istat.android.widget.fragment.output;

import istat.android.base.utils.ImageLoader;
import istat.android.base.tools.ToolKits;
import istat.android.widget.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
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
        initComponents(base);
        return base;
    }

    void initComponents(View layout) {
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
        this.onEchecImage = getBitmapFromResource(getActivity(),
                onEchecImage);
        return this;
    }

    public ImageDisplayer setOnLoadImage(int onLoadImage) {
        this.onLoadImage = getBitmapFromResource(getActivity(),
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

    private class LoadAction extends AsyncTask {
        String url;
        Bitmap image;

        public LoadAction(String url) {
            this.url = url;
        }

        @Override
        public void onPreExecute() {
            // TODO Auto-generated method stub
            imageView.setImageBitmap(onLoadImage);
        }

        @Override
        protected Object doInBackground(Object[] params) {

            // TODO Auto-generated method stub
            // image=ImageBitmap.getBitmapFromURL(url);
            if (ToolKits.WordFormat.isNumber(url))
                image = getBitmapFromResource(getActivity(),
                        Integer.valueOf(url));
            else
                image = ImageLoader.getBitmap(url, getActivity(), imageQuality);

            return null;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Object o) {
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

    }

    /*
     * do when the ImageLoading is complete
     */
    protected void onCompleteImageLoad(Bitmap bitmap, String url) {

    }

    public static Bitmap getBitmapFromResource(Context ctx, int ressource) {
        Bitmap im = null;

        try {
            im = BitmapFactory.decodeResource(ctx.getResources(), ressource);
        } catch (Exception var4) {
            ;
        }

        return im;
    }

}
