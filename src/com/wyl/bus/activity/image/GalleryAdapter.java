package com.wyl.bus.activity.image;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;

/**
 * 
 * @author wyl
 * @date 2015年3月13日 上午10:59:00
 */
public class GalleryAdapter extends BaseAdapter {

	private Context context;

	private ArrayList<MyImageView> imageViews = new ArrayList<MyImageView>();

    private GalleryPositionListener positionListener;

    private ArrayList<HelpTopicImageBean>   helpTopicImage = new ArrayList<HelpTopicImageBean>();
    private int position = 0;
    private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Bitmap bitmap = (Bitmap) msg.obj;
			Bundle bundle = msg.getData();
			String url = bundle.getString("url");
			for (int i = 0; i < imageViews.size(); i++) {
				if (imageViews.get(i).getTag().equals(url)) {
					imageViews.get(i).setImageBitmap(bitmap);
				}
			}
		}
	};

	public void setData(List<Integer> data) {
		notifyDataSetChanged();
	}

	public GalleryAdapter(Context context,ArrayList<HelpTopicImageBean>   helpTopicImage,int position) {
		this.context = context;
        this.helpTopicImage = helpTopicImage;
        this.position = position;
        for(int i=0; i<helpTopicImage.size(); i++){
        	String url = helpTopicImage.get(i).getImgUrl();
        	//TODO 空指针
            Bitmap bitmap = ImageLoader.getInstance().loadImageSync(url);
            bitmaps.add(bitmap);
        }
	}

	@Override
	public int getCount() {
		return helpTopicImage.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyImageView view = new MyImageView(context);
		view.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));

        if (bitmaps.get(position) != null) {
            view.setImageBitmap(bitmaps.get(position));
        }
        if (!this.imageViews.contains(view)) {
            imageViews.add(view);
        }
        positionListener.movePosition(position);
		return view;
	}

     public void getPositionListener(GalleryPositionListener positionListener) {
            this.positionListener = positionListener;
     }

    public interface GalleryPositionListener{
           public void movePosition(int index);
    }
}
