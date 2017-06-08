package istat.android.widget.view;

import istat.android.widget.util.WidgetUtil;
import istat.android.widget.R;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Slider extends View {
	Context ctx;
	public boolean effect, hasbeenTouched = false, isScaled = false,
			isStoped = true, AutoResizable = false, AutoSlide = true;
	int MyScale = 2;
	Bitmap bmp;
	Paint p = new Paint();
	Bitmap arrow = Bitmap.createBitmap(560, 560, Config.ARGB_8888);
	Bitmap arrow2 = arrow;
	Bitmap canvs = Bitmap.createBitmap(560, 560, Config.ARGB_8888),
			bckcanvs = canvs;
	public String SLIDER_NOTIFY_INTENT = "istat.android.slider.SLIDE_INTENT";
	// List<Bitmap> BitmapList;
	List<String> ImagePhathList;
	int Pictx = 0, velos = 60;
	int looperControl = 0;
	TimerTask timeTask2, timeTask;

	// List<Integer> ImageList;

	int ImagMarg = 20, SlideLevel = 0, SlideSpeed = 5000,
			SlideClickedLevel = 0, NextSpeed = 100;
	int POSITION = 2, Marging = 30;
	Timer t, sldTimer;

	public Slider(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		addListener();
		ctx = context;
		init();
		// TODO Auto-generated constructor stub
	}

	public Slider(Context context, AttributeSet attrs) {
		super(context, attrs);
		addListener();
		ctx = context;
		init();
		// TODO Auto-generated constructor stub
	}

	public Slider(Context context) {
		super(context);
		addListener();
		ctx = context;
		// init();
		// TODO Auto-generated constructor stub
	}

	// @SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// super.onDraw(canvas);
		if (canvs != null & bckcanvs != null) {

			if (!AutoResizable) {

				if (Pictx < -this.getWidth() || Pictx > this.getWidth()) {
					istat.android.widget.util.WidgetUtil.drawimag(canvas,
							canvs, 0, 0, this.getWidth(), this.getHeight(), p);
				} else {
					if (POSITION == 2)
						istat.android.widget.util.WidgetUtil.drawimag(canvas,
								canvs, Pictx + this.getWidth(), 0,
								this.getWidth(), this.getHeight(), p);
					else if (POSITION == 1)
						istat.android.widget.util.WidgetUtil.drawimag(canvas,
								canvs, Pictx - this.getWidth(), 0,
								this.getWidth(), this.getHeight(), p);
					else
						istat.android.widget.util.WidgetUtil.drawimag(canvas,
								canvs, 0, 0, this.getWidth(), this.getHeight(),
								p);
				}
				istat.android.widget.util.WidgetUtil.drawimag(canvas, bckcanvs,
						Pictx, 0, this.getWidth(), this.getHeight(), p);
			} else {

				// ------------------------------------------------------------------------------------------------------
				// int cote=0;
				int margingy = 50;
				int margingx = 20;
				float h = 0;
				h = ((float) (canvs.getHeight() * this.getWidth()) / (float) canvs
						.getWidth());
				margingy = (int) ((this.getHeight() - h) / 2);

				float w = ((float) (canvs.getWidth() * this.getHeight()) / (float) canvs
						.getHeight());
				margingx = (int) ((this.getWidth() - w) / 2);

				if (Pictx < -this.getWidth() || Pictx > this.getWidth()) {

					if (canvs.getWidth() >= canvs.getHeight()) {

						istat.android.widget.util.WidgetUtil.drawimag(canvas,
								canvs, 0, 0 + margingy, this.getWidth(),
								(int) h, p);

					} else {

						istat.android.widget.util.WidgetUtil.drawimag(canvas,
								canvs, 0 + margingx, 0, (int) w,
								this.getHeight(), p);
					}

				} else {
					if (POSITION == 2) {
						if (canvs.getWidth() >= canvs.getHeight()) {
							istat.android.widget.util.WidgetUtil.drawimag(
									canvas, canvs, Pictx + this.getWidth(),
									margingy, this.getWidth(), (int) h, p);
						} else
							istat.android.widget.util.WidgetUtil.drawimag(
									canvas, canvs, Pictx + this.getWidth() + 2
											* margingx, 0, (int) w,
									this.getHeight(), p);

					} else if (POSITION == 1) {
						if (canvs.getWidth() >= canvs.getHeight()) {
							istat.android.widget.util.WidgetUtil.drawimag(
									canvas, canvs, Pictx - this.getWidth(),
									margingy, this.getWidth(), (int) h, p);
						} else
							istat.android.widget.util.WidgetUtil.drawimag(
									canvas, canvs, Pictx - this.getWidth()
											+ margingx, 0, (int) w,
									this.getHeight(), p);

					} else {
						istat.android.widget.util.WidgetUtil.drawimag(canvas,
								canvs, 0, 0, this.getWidth(), this.getHeight(),
								p);

					}

				}
				// -----------------------------------------------
				h = ((float) (bckcanvs.getHeight() * this.getWidth()) / (float) bckcanvs
						.getWidth());
				margingy = (int) ((this.getHeight() - h) / 2);

				w = ((float) (bckcanvs.getWidth() * this.getHeight()) / (float) bckcanvs
						.getHeight());
				margingx = (int) ((this.getWidth() - w) / 2);

				if (bckcanvs.getWidth() >= bckcanvs.getHeight()) {

					istat.android.widget.util.WidgetUtil.drawimag(canvas,
							bckcanvs, Pictx, margingy, this.getWidth(),
							(int) h, p);
				} else
					istat.android.widget.util.WidgetUtil.drawimag(canvas,
							bckcanvs, margingx + Pictx, 0, this.getWidth() - 2
									* margingx, this.getHeight(), p);
				// -------------------------------------------------------------------------------------------------------

			}

		}
		// -------------------------------------------------------------------------------ARROW
		// DRAW------------------------------------------------
		if (effect) {

			/*
			 * if(POSITION==0) //DrawUtil.drawimag(canvas, bmp,Marging/2,
			 * 0,Slider.this.getWidth()-Marging, Slider.this.getHeight(), p);
			 * DrawUtil.drawimag(canvas, bmp,0, 0,Slider.this.getWidth(),
			 * Slider.this.getHeight(), p); else
			 */
			if (POSITION == 1) {
				// DrawUtil.drawimag(canvas, bmp,0, 0,Marging,
				// Slider.this.getHeight(), p);
				WidgetUtil.drawimag(canvas, arrow, 1, (Slider.this.getHeight())
						/ 2 - Marging, Marging + Marging / 4, Marging * 3, p);
				WidgetUtil.drawimag(canvas, arrow2, Slider.this.getWidth()
						- Marging, (Slider.this.getHeight() - Marging) / 2,
						Marging - 4, Marging * 2, p);
			} else if (POSITION == 2) {
				// DrawUtil.drawimag(canvas, bmp,Slider.this.getWidth()-Marging,
				// 0,Marging, Slider.this.getHeight(), p);
				WidgetUtil.drawimag(canvas, arrow, 0,
						(Slider.this.getHeight() - Marging) / 2, Marging - 4,
						Marging * 2, p);
				WidgetUtil.drawimag(canvas, arrow2, Slider.this.getWidth() - 8
						- Marging, (Slider.this.getHeight()) / 2 - Marging,
						Marging + Marging / 4, Marging * 3, p);
			}

		} else {
			try {
				WidgetUtil.drawimag(canvas, arrow, 4,
						(Slider.this.getHeight() - Marging) / 2, Marging - 0,
						Marging * 2, p);
				WidgetUtil.drawimag(canvas, arrow2, Slider.this.getWidth() - 8
						- Marging, (Slider.this.getHeight() - Marging) / 2,
						Marging - 0, Marging * 2, p);
			} catch (Exception e) {
			}

		}
		// --------------------------------------------------------------------------------------------------------------------------------------------

		if (!hasbeenTouched) {
			// control slider cursor effect
			final Handler handler = new Handler();
			TimerTask timeTask = new TimerTask() {
				public void run() {
					handler.post(new Runnable() {
						public void run() {// restor standar image
							hasbeenTouched = true;
							effect = false;
							invalidate();
						}
					});
				}
			};

			if (!isStoped)
				t.schedule(timeTask, 180);
		}

	}

	public void setImagePhathList(List<String> imagePhathList) {
		ImagePhathList = imagePhathList;
	}

	private void init() {

		bmp = WidgetUtil.imag(R.drawable.efct, ctx);
		arrow = WidgetUtil.imag(R.drawable.arrow_left, ctx);
		arrow2 = WidgetUtil.imag(R.drawable.arrow_right, ctx);
		this.invalidate();
	}

	public void start() {
		if (ImagePhathList != null)
			if (ImagePhathList.size() > 0) {
				slidRunTask();
				StartSliding();
			}
		// initdimentioning();
	}

	public void setMyScale(int scale) {
		MyScale = scale;
		this.isScaled = true;
	}

	public void setScaled(boolean isScaled) {
		this.isScaled = isScaled;
	}

	private void slideThread() {
		final Handler handler = new Handler();
		// sldTimer.cancel();
		TimerTask timeTask2 = new TimerTask() {
			public void run() {
				handler.post(new Runnable() {// slide picture
					public void run() {
						if (POSITION == 2)
							Pictx = Pictx - velos;
						else if (POSITION == 1)
							Pictx = Pictx + velos;
						invalidate();

					}
				});
			}
		};
		if (isStoped)
			sldTimer.schedule(timeTask2, 0, NextSpeed);

	}

	private void slidRunTask() {
		if (POSITION == 2)
			Pictx = velos;
		else if (POSITION == 1)
			Pictx = -velos;

		if (ImagePhathList != null)
			if (ImagePhathList.size() > 0) {
				Bitmap tmp = canvs;
				if (SlideLevel >= ImagePhathList.size())
					SlideLevel = 0;
				if (SlideLevel < 0)
					SlideLevel = ImagePhathList.size() - 1;

				if (WidgetUtil.isaNumber(ImagePhathList.get(SlideLevel))) {
					try {
						canvs = istat.android.widget.util.WidgetUtil
								.imag(Integer.valueOf(ImagePhathList
										.get(SlideLevel)), ctx);
					} catch (Exception e) {
					}
				} else if (!isScaled) {
					try {
						canvs = istat.android.widget.util.WidgetUtil
								.getBitmapFromPath(ImagePhathList
										.get(SlideLevel));
					} catch (Exception e) {
					}
				} else {
					try {
						canvs = istat.android.widget.util.WidgetUtil.imag(
								ImagePhathList.get(SlideLevel), MyScale);
					} catch (Exception e) {
					}
				}
				if (canvs == null) {
					if (POSITION == 2)
						SlideLevel++;
					else if (POSITION == 1)
						SlideLevel--;

					if (SlideLevel >= ImagePhathList.size())
						SlideLevel = 0;
					if (SlideLevel < 0)
						SlideLevel = ImagePhathList.size() - 1;

					canvs = tmp;// Util.DrawUtil.imag(R.drawable.ancs2,ctx);
					senToBroadcast();
					looperControl++;
					try {
						if (looperControl <= ImagePhathList.size()) {
							slidRunTask();
						} else {
							canvs = WidgetUtil.imag(
									R.drawable.no_network_event, ctx);
							bckcanvs = canvs;
						}
					} catch (Exception e) {
					}
				} else {
					looperControl = 0;

					if (SlideLevel > 0) {
						if (WidgetUtil.isaNumber(ImagePhathList
								.get(SlideLevel - 1))) {
							try {
								bckcanvs = istat.android.widget.util.WidgetUtil
										.imag(Integer.valueOf(ImagePhathList
												.get(SlideLevel - 1)), ctx);
							} catch (Exception e) {
							}
						} else if (!isScaled)
							try {
								bckcanvs = istat.android.widget.util.WidgetUtil
										.getBitmapFromPath(ImagePhathList
												.get(SlideLevel - 1));
							} catch (Exception e) {
							}
						else
							try {
								bckcanvs = istat.android.widget.util.WidgetUtil
										.imag(ImagePhathList
												.get(SlideLevel - 1), MyScale);
							} catch (Exception e) {
							}

					} else if (SlideLevel == 0) {

						if (!isScaled)
							try {
								bckcanvs = istat.android.widget.util.WidgetUtil
										.getBitmapFromPath(ImagePhathList
												.get(ImagePhathList.size() - 1));
							} catch (Exception e) {
							}
						else
							try {
								bckcanvs = istat.android.widget.util.WidgetUtil
										.imag(ImagePhathList.get(ImagePhathList
												.size() - 1), MyScale);
							} catch (Exception e) {
							}

					}
					if (POSITION == 1)
						bckcanvs = tmp;
					// if(POSITION==2)canvs=tmp;
					// -------------------hand bad path error----------
					SlideLevel++;

					if (bckcanvs == null)
						bckcanvs = tmp;

				}

			}
	}

	private void StartSliding() {
		// final Handler handler = new Handler();

		t = new Timer();
		sldTimer = new Timer();
		timeTask = new TimerTask() {
			public void run() {
				Slider.this.post(new Runnable() {
					// handler.post(new Runnable() {
					public void run() {

						slidRunTask();

					}
				}

				);
			}

		};
		timeTask2 = new TimerTask() {
			public void run() {
				Slider.this.post(new Runnable() {
					public void run() {// set Forward effect
						effect = true;
						POSITION = 2;
						hasbeenTouched = false;
						invalidate();
					}
				});
			}
		};
		if (isStoped) {
			if (AutoSlide) {
				t.schedule(timeTask2, SlideSpeed + 45, SlideSpeed);
				t.schedule(timeTask, 0, SlideSpeed);
			} else {
				t.schedule(timeTask2, SlideSpeed + 45);
				t.schedule(timeTask, 45);
			}
		}

		slideThread();
		isStoped = false;
	}

	public boolean isStoped() {
		return isStoped;
	}

	public void StopSliding() {

		try {
			if (!isStoped) {
				t.cancel();
				sldTimer.cancel();

			}
			isStoped = true;
		} catch (Exception e) {
			isStoped = false;
		}
	}

	public void setMarging(int marging) {
		Marging = marging;
	}

	public void setSpeed(int speed) {
		SlideSpeed = speed;
	}

	private void addListener() {
		this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent e) {

				switch (e.getAction()) {
				case MotionEvent.ACTION_DOWN:
					OnTouchMode(e);
					break;

				case MotionEvent.ACTION_UP:
					OnRelease();
					break;
				case MotionEvent.ACTION_CANCEL:
					OnRelease();
					break;
				}
				invalidate();
				return false;
			}
		});
	}

	private void OnTouchMode(MotionEvent e) {

		effect = true;
		hasbeenTouched = true;
		StopSliding();
		if (e.getX() < Marging + 5) {
			POSITION = 1;

		}
		if (e.getX() > Slider.this.getWidth() - Marging - 5) {
			POSITION = 2;

		}
		if (e.getX() < Slider.this.getWidth() - Marging * 2
				& e.getX() > Marging * 2) {
			POSITION = 0;
			SlideClickedLevel = SlideLevel - 1;

		}

	}

	public void OnRelease() {
		// Log.i("OnRelease","activate");
		effect = false;
		if (POSITION == 1) {

			SlideLevel = SlideLevel - 2;
			senToBroadcast();
			// StartSliding();
		}
		if (POSITION == 2) {

			// t.cancel();
			senToBroadcast();
			// StartSliding();

		}
		if (POSITION == 0) {
			// t.cancel();
			SlideLevel--;
			// StartSliding();
		}
		StartSliding();
	}

	private void senToBroadcast() {
		// Log.i("Service_senTobrocast",
		// "broadsast active new="+info.getNbrNewEvents()+" pharm="+info.getNbrPharmacies()+" objt="+info);

		Intent intent = new Intent(SLIDER_NOTIFY_INTENT);

		intent.putExtra("level", SlideLevel);
		// intent.putExtra("NbrPharm", info.getNbrPharmacies());

		ctx.sendBroadcast(intent);

	}

	public void setNextSpeed(int nextSpeed) {
		NextSpeed = nextSpeed;
	}

	public int getNextSpeed() {
		return NextSpeed;
	}

	public void setAutoSlide(boolean autoSlide) {
		AutoSlide = autoSlide;
	}

	public boolean isAutoSlide() {
		return AutoSlide;
	}

	public Integer getPosition() {
		return POSITION;
	}

	public Integer getSpeed() {
		return SlideSpeed;
	}

	public Integer getVelocity() {
		return velos;
	}

	public void setVelocity(int velos) {
		this.velos = velos;
	}

	public Integer getLevel() {
		return SlideLevel;
	}

	public void setLevel(int val) {
		SlideLevel = val;
	}

	/*
	 * public Integer getSlideClickedLevel(){ return SlideClickedLevel; } public
	 * int getSlideLevel() { return SlideLevel; }
	 */
	public int getAnnonceNumber() {
		return ImagePhathList.size();
	}

	public boolean isAutoResizable() {
		return AutoResizable;
	}

	public void setAutoResizable(boolean autoResizable) {
		AutoResizable = autoResizable;
	}
}
