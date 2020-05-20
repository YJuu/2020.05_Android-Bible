package com.YJuu.fhl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultPopupActivity extends Activity {
    //view안의 요소들과 연결
    ImageButton exitBtn;
    TextView resultTxt;
    TextView messageTxt;

    int correctNum = 0;
    int questionNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_popup);

        Intent intent = getIntent();
        correctNum = intent.getIntExtra("correctNum",0);
        questionNum = intent.getIntExtra("questionNum",0);

        //view안의 요소들과 연결
        exitBtn = (ImageButton)findViewById(R.id.exitBtn);
        resultTxt = (TextView)findViewById(R.id.resultTxt);
        messageTxt = (TextView)findViewById(R.id.messageTxt);

        resultTxt.setText(String.format("%02d", correctNum)+"/"+String.format("%02d", questionNum));
        String message = setMessage();
        messageTxt.setText(message);

        //exit버튼을 누르면 팝업 종료
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        setResult(0, intent);
        finish();
    }

    public String setMessage(){
        String message="";
        if(correctNum == questionNum){message = "경★축"+"\n"+"만 점";}
        else if(correctNum/questionNum > 0.75){message = "대단하네요♥";}
        else if(correctNum/questionNum > 0.5){message = "잘했어요!";}
        else {message = "더 공부해요:)"; }
        return message;
    }
}
