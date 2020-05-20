package com.YJuu.fhl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GoMainPopupActivity extends Activity {
    Button cancelBtn;
    Button goMainBtn;
    TextView noticeTxt;

    int progress = 0;
    int questionNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gomain_popup);

        Intent intent = getIntent();
        progress = intent.getIntExtra("progress",0);
        questionNum = intent.getIntExtra("questionNum",0);

        cancelBtn = (Button)findViewById(R.id.cancelBtn);
        goMainBtn = findViewById(R.id.goMainBtn);
        noticeTxt = (TextView)findViewById(R.id.notice);

        int remain = questionNum-progress;
        noticeTxt.setText(progress+"문제를 풀었고, "+remain+"문제 남았어요!");

        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }
        });

        goMainBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        setResult(1, intent);
        finish();
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