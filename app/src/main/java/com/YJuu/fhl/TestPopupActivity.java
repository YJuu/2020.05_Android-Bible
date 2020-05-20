package com.YJuu.fhl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TestPopupActivity extends Activity {
    //view안의 요소들과 연결
     RadioGroup starRadio;
     RadioButton one_star;
     RadioButton two_star;
     RadioButton three_star;
     Button startBtn;
     ImageButton exitBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_popup);

        starRadio = (RadioGroup)findViewById(R.id.starRadio);
        one_star = (RadioButton)findViewById(R.id.one_star);
        two_star = (RadioButton)findViewById(R.id.two_star);
        three_star = (RadioButton)findViewById(R.id.three_star);
        startBtn = (Button)findViewById(R.id.startBtn);
        exitBtn = (ImageButton)findViewById(R.id.exitBtn);

        //start버튼을 누르면 라디오 버튼의 정보를 받아서 TestActivity실행
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //라디오버튼에서 선택된 버튼의 id반환
                int id = starRadio.getCheckedRadioButtonId();
                //선택된 버튼이 one_star일때 One_TestActivity로 전환
                if(id == one_star.getId()){
                    Intent intent = new Intent();
                    setResult(1, intent);
                    finish();
                }
                //선택된 버튼이 two_star일때 Two_TestActivity로 전환
                else if(id == two_star.getId()){
                    Intent intent = new Intent();
                    setResult(2, intent);
                    finish();
                }
                //선택된 버튼이 three_star일때 Three_TestActivity로 전환
                else if(id == three_star.getId()){
                    Intent intent = new Intent();
                    setResult(3, intent);
                    finish();
                }
            }
        });

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
    public void onBackPressed(){
        Intent intent = new Intent();
        setResult(0, intent);
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
