package com.wyl.bus.activity;

import com.wyl.bus.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 
 * @author wyl
 * @date 2015年3月13日 上午10:24:12
 */
public class DisplayImage extends Activity implements OnTouchListener, OnGestureListener {

	private static final String TAG = "DisplayImage";
	private static final int FLING_MIN_DISTANCE = 100;
	private static final int FLING_MIN_VELOCITY = 200;


	/* 相关变量声明 */
	private ImageView mImageView;
	private Button mButton01;
	private Button mButton02;
	private FrameLayout layout1;
	private LinearLayout layoutImage;
	private Bitmap bmp;
	private int id=0;
	private int displayWidth;
	private int displayHeight;
	private float scaleWidth=1;
	private float scaleHeight=1;
	private GestureDetector mGestureDetector;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)    {
		super.onCreate(savedInstanceState);
		/* 加载display.xml Layout */
		setContentView(R.layout.activity_display);

		/* 取得屏幕分辨率大小 */
		DisplayMetrics dm=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		displayWidth=dm.widthPixels;
		displayHeight=dm.heightPixels; 

		/* 初始化相关变量 */
//		Bundle bundle = this.getIntent().getExtras();
//		Integer imageId = bundle.getInt("imageId");
//		Log.i(TAG, "onCreate, imageId = " + imageId);

//		bmp=BitmapFactory.decodeResource(getResources(), imageId); 
		bmp=BitmapFactory.decodeResource(getResources(), R.drawable.gif); 
		mImageView = (ImageView)findViewById(R.id.myImageView);
		mImageView.setImageBitmap(bmp);
		mImageView.setOnTouchListener(this);
		mImageView.setLongClickable(true);

		layout1 = (FrameLayout)findViewById(R.id.layout1);
		layoutImage = (LinearLayout)findViewById(R.id.layoutImage);
		mButton01 = (Button)findViewById(R.id.myButton1);
		mButton02 = (Button)findViewById(R.id.myButton2); 

		/* 缩小按钮onClickListener */
		mButton01.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				small(); 
			}
		});

		/* 放大按钮onClickListener */
		mButton02.setOnClickListener(new Button.OnClickListener() {
			@Override       
			public void onClick(View v) {
				big();
			} 
		});
	}  

	// 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
	@Override
	public boolean onDown(MotionEvent e) {
		
		//			Toast.makeText(this, "onDown", Toast.LENGTH_SHORT).show();    
		Log.i(TAG, "onDown...");

		return false;
	}

	/* 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 
	 * 多个ACTION_MOVE, 1个ACTION_UP触发
	 * 参数解释： 
	 * e1：第1个ACTION_DOWN MotionEvent 
	 * e2：最后一个ACTION_MOVE MotionEvent 
	 * velocityX：X轴上的移动速度，像素/秒 
	 * velocityY：Y轴上的移动速度，像素/秒 
	 * 触发条件 ： 
	 * X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒
	 * @see android.view.GestureDetector$OnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		
		Log.i(TAG, "onFling...");

		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE    
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {    
			// Fling left 

			Toast.makeText(this, "Fling Left", Toast.LENGTH_SHORT).show();    
		} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE    
				&& Math.abs(velocityX) > FLING_MIN_VELOCITY) {    

			// Fling right 

			Toast.makeText(this, "Fling Right", Toast.LENGTH_SHORT).show();    
		}  

		return false;
	}

	// 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发 
	@Override
	public void onLongPress(MotionEvent e) {
		
		Log.i(TAG, "onLongPress...");

	}

	// 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		
		Log.i(TAG, "onScroll...");

		return false;
	}

	// 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
	// 注意和onDown()的区别，强调的是没有松开或者拖动的状态
	@Override
	public void onShowPress(MotionEvent e) {
		
		Log.i(TAG, "onShowPress...");

	}

	// 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		
		Log.i(TAG, "onSingleTapUp...");

		return false;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		Log.i(TAG, "onTouch...");

		// Set button visible
		mButton01.setVisibility(View.VISIBLE);
		mButton02.setVisibility(View.VISIBLE);

//		return  mGestureDetector.onTouchEvent(event);    
		return  true;    
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		
//		super.onTouchEvent(event);
//		
//		Log.i(TAG, "onTouchEvent");
//		// Set button visible
//		mButton01.setVisibility(View.VISIBLE);
//		mButton02.setVisibility(View.VISIBLE);
//		
//		return true;
//	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		super.onKeyDown(keyCode, event);

		Log.i(TAG, "onKeyDown...");
		// Set button visible
		mButton01.setVisibility(View.VISIBLE);
		mButton02.setVisibility(View.VISIBLE);

		return true;
	}

	/* 图片缩小的method */
	private void small()    {
		int bmpWidth=bmp.getWidth(); 
		int bmpHeight=bmp.getHeight();

		Log.i(TAG, "bmpWidth = " + bmpWidth + ", bmpHeight = " + bmpHeight);

		/* 设置图片缩小的比例 */
		double scale=0.8;
		/* 计算出这次要缩小的比例 */ 
		scaleWidth=(float) (scaleWidth*scale); 
		scaleHeight=(float) (scaleHeight*scale); 
		/* 产生reSize后的Bitmap对象 */
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmpWidth, 
				bmpHeight,matrix,true); 

		if(id==0)      {
			/* 如果是第一次按，就删除原来默认的ImageView */
			layoutImage.removeView(mImageView);
		} else {
			/* 如果不是第一次按，就删除上次放大缩小所产生的ImageView */
			layoutImage.removeView((ImageView)findViewById(id));
		} 

		/* 产生新的ImageView，放入reSize的Bitmap对象，再放入Layout中 */
		id++;
		ImageView imageView = new ImageView(this);
		imageView.setId(id);
		imageView.setImageBitmap(resizeBmp);
		layoutImage.addView(imageView);
		Log.i(TAG, "imageView.getWidth() = " + imageView.getWidth()
				+ ", imageView.getHeight() = " + imageView.getHeight());
		setContentView(layout1);
		/* 因为图片放到最大时放大按钮会disable，所以在缩小时把它重设为enable */ 
		mButton02.setEnabled(true);
		mButton02.setTextColor(Color.MAGENTA);
	}

	/* 图片放大的method */
	private void big() {
		int bmpWidth=bmp.getWidth();
		int bmpHeight=bmp.getHeight();

		Log.i(TAG, "bmpWidth = " + bmpWidth + ", bmpHeight = " + bmpHeight);

		/* 设置图片放大的比例 */
		double scale=1.25;
		/* 计算这次要放大的比例 */
		scaleWidth=(float)(scaleWidth*scale);
		scaleHeight=(float)(scaleHeight*scale);
		/* 产生reSize后的Bitmap对象 */
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmpWidth, 
				bmpHeight,matrix,true);

		if(id==0) {
			/* 如果是第一次按，就删除原来设置的ImageView */
			layoutImage.removeView(mImageView);
		} else {
			/* 如果不是第一次按，就删除上次放大缩小所产生的ImageView */ 
			layoutImage.removeView((ImageView)findViewById(id));
		}

		/* 产生新的ImageView，放入reSize的Bitmap对象，再放入Layout中 */
		id++;
		ImageView imageView = new ImageView(this);
		imageView.setId(id);
		imageView.setImageBitmap(resizeBmp);
		layoutImage.addView(imageView);
		setContentView(layout1);
		/* 如果再放大会超过屏幕大小，就把Button disable */
		if( scaleWidth * scale * bmpWidth > bmpWidth * 3 ||
				scaleHeight * scale * bmpHeight > bmpWidth * 3 ||
				scaleWidth * scale * bmpWidth > displayWidth * 5 ||
				scaleHeight * scale * bmpHeight > displayHeight * 5) {
			mButton02.setEnabled(false);
			mButton02.setTextColor(Color.GRAY);
		} else {
			mButton02.setEnabled(true);
			mButton02.setTextColor(Color.MAGENTA);
		}
	} 

}
