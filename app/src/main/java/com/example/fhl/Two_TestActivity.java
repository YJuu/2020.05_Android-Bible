package com.example.fhl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;

public class Two_TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two__test);

        Bitmap bitmap = Bitmap.createBitmap(48,48,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(getResources().getColor(R.color.transparent));

        ImageView imageView = (ImageView)findViewById(R.id.imageView);

        imageView.setImageBitmap(bitmap);

        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.hotpink));
        paint.setStrokeWidth(3);
        RectF rectF = new RectF();
        rectF.set(3,3,45,45);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        canvas.drawArc(rectF, -90,90,false, paint);

    }
}
