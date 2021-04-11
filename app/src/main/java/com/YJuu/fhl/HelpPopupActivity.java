
package com.YJuu.fhl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HelpPopupActivity extends Activity {

    private TextView titleTxt;
    private ImageViewPagerAdapter pagerAdapter;
    private ViewPager imageView;
    private ImageView Dot01;
    private ImageView Dot02;
    private ImageView Dot03;
    private ImageView Dot04;
    private ImageView Dot05;
    private Button cancelBtn;

    private int now = 0;
    private String[] titles = {"메인 화면","말씀 보기", "시험 보기 ★", "시험 보기 ★★","시험 보기 ★★★"} ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_popup);

        titleTxt = (TextView) findViewById(R.id.title);
        imageView = (ViewPager) findViewById(R.id.imageView);
        Dot01 = (ImageView) findViewById(R.id.dot01);
        Dot02 = (ImageView) findViewById(R.id.dot02);
        Dot03 = (ImageView) findViewById(R.id.dot03);
        Dot04 = (ImageView) findViewById(R.id.dot04);
        Dot05 = (ImageView) findViewById(R.id.dot05);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);


        pagerAdapter = new ImageViewPagerAdapter(this);
        imageView.setAdapter(pagerAdapter);
        imageView.setCurrentItem(now, true);

        imageView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                unselectDot(now);
                now = position;
                selectDot(now);
                titleTxt.setText(titles[now]);
                imageView.setCurrentItem(now, true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void unselectDot(int num){
        Drawable unfilled = getResources().getDrawable(R.drawable.ic_favorite_border_24px);
        switch (num){
            case 0:
                Dot01.setBackground(unfilled); break;
            case 1:
                Dot02.setBackground(unfilled); break;
            case 2:
                Dot03.setBackground(unfilled); break;
            case 3:
                Dot04.setBackground(unfilled); break;
            case 4:
                Dot05.setBackground(unfilled); break;
        }
    }

    public void selectDot(int num){
        Drawable filled = getResources().getDrawable(R.drawable.ic_favorite_24px);
        switch (num){
            case 0:
                Dot01.setBackground(filled); break;
            case 1:
                Dot02.setBackground(filled); break;
            case 2:
                Dot03.setBackground(filled); break;
            case 3:
                Dot04.setBackground(filled); break;
            case 4:
                Dot05.setBackground(filled); break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

}
