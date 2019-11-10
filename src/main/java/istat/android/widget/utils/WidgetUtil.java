package istat.android.widget.utils;

import istat.android.widget.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public final class WidgetUtil {

	public static Integer NumberToDrawable(int num) {
		switch (num) {
		case -2:
			return R.drawable.nbr_2;
		case -1:
			return R.drawable.nbr_1;
		case 0:
			return R.drawable.nbr0;
		case 1:
			return R.drawable.nbr1;
		case 2:
			return R.drawable.nbr2;
		case 3:
			return R.drawable.nbr3;
		case 4:
			return R.drawable.nbr4;
		case 5:
			return R.drawable.nbr5;
		case 6:
			return R.drawable.nbr6;
		case 7:
			return R.drawable.nbr7;
		case 8:
			return R.drawable.nbr8;
		case 9:
			return R.drawable.nbr9;
		case 10:
			return R.drawable.nbr10;
		default:
			return num > 10 ? R.drawable.nbr_10 : R.drawable.nbr_2;
		}
	}

	public static boolean isaNumber(String a) {
		try {
			a = "" + Double.valueOf(a);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static Bitmap imag(int a, Context ctx) {
		Bitmap im = null;
		try {
			im = BitmapFactory.decodeResource(ctx.getResources(), a);
		} catch (Exception e) {
		}
		return im;
	}

	public static Bitmap getBitmapFromPath(String url) {
		Bitmap im = null;
		try {
			im = BitmapFactory.decodeFile(url);
		} catch (Exception e) {
		}
		return im;
	}

	public static Bitmap imag(String url, int scale) {
		BitmapFactory.Options o = new BitmapFactory.Options();
		o.inPurgeable = true;
		o.inSampleSize = scale;
		o.inScaled = true;
		Bitmap im = null;
		try {
			im = BitmapFactory.decodeFile(url, o);
		} catch (Exception e) {
		}
		return im;
	}

	public static void drawimag(Canvas g, Bitmap bmp, int x, int y, int lx,
			int ly, Paint p) {
		g.drawBitmap(bmp, null, new RectF(x, y, x + lx, y + ly), p);
	}

	public static void fillrect(Canvas g, int x, int y, int lx, int ly, Paint p) {
		g.drawRect(x, y, x + lx, y + ly, p);
	}

	public static void drawrect(Canvas g, int x, int y, int lx, int ly, Paint p) {
		g.drawLine(x, y, x + lx, y, p);
		g.drawLine(x, y, x, y + ly, p);
		g.drawLine(x + lx, y, x + lx, y + ly, p);
		g.drawLine(x, y + ly, x + lx, y + ly, p);
	}
}
