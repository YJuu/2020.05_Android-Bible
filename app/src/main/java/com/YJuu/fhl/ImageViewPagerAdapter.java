package com.YJuu.fhl;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ortiz.touchview.TouchImageView;

public class ImageViewPagerAdapter extends PagerAdapter {
    private ViewPager mPager = null;
    private int[] mPagerIDsArray;
    private Context mContext = null;
    private TouchImageView img;
    private int images[] = {R.drawable.help01, R.drawable.help02, R.drawable.help03, R.drawable.help04, R.drawable.help05};

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;

    public ImageViewPagerAdapter(Context context) { mContext = context; }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        View view = null;

        if(mContext != null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.image_page , container, false);

            int index = position % 5;

            img = (TouchImageView)view.findViewById(R.id.image);
            img.setImageResource(images[index]);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }

}
