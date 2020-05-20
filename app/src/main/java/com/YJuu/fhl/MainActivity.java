package com.YJuu.fhl;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {
    private BibleData data = new BibleData();
    private HashMap<String, ArrayList<String>> bibleMap = new HashMap<>();
    private ArrayList<String> verse = new ArrayList<>();
    private boolean[] is_long = new boolean[20];
    private boolean[] complete = new boolean[20];
    private boolean jumpCheck = true;
    private int r = 1;
    private int last = 1;

    private TextView DdayTxt;
    private TextView PhraseTxt;
    private TextView VerseTxt;
    private Button ViewBtn;
    private Button TestBtn;
    private ImageButton ShareBtn;
    private ImageButton HelpBtn;
    private ImageButton ContactBtn;
    private long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //화면구성요소와 연결
        DdayTxt = (TextView)findViewById(R.id.Dday);
        PhraseTxt = (TextView)findViewById(R.id.phrase) ;
        VerseTxt = (TextView)findViewById(R.id.verse);
        ViewBtn = (Button)findViewById(R.id.viewbtn);
        TestBtn = (Button)findViewById(R.id.testbtn);
        ShareBtn = (ImageButton)findViewById(R.id.shareBtn);
        HelpBtn = (ImageButton)findViewById(R.id.helpBtn);
        ContactBtn = (ImageButton)findViewById(R.id.contactBtn);

        //화면전환시 전달된 Data를 저장
        Intent intent = getIntent();
        data = (BibleData) intent.getSerializableExtra("data");
        bibleMap = data.getBibleMap();
        verse = data.getVerse();
        is_long = data.getIs_long();
        last = data.getLast();

        //Dday표시하는 함수
        updateDday(calculate_Dday());
        //랜덤한 구절을 표시하는 함수
        printRandomPhrase();

        PhraseTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                last = r;
                data.setLast(last);
                //현재 화면과 전환할 화면 설정
                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
                //화면전환시 bibleMap과 verse를 Data를 함께 전달
                intent.putExtra("data", (Serializable) data);
                //화면전환
                startActivity(intent);
                finish();
            }
        });

        ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSharePopup(v);
            }
        });

        ContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showContactPopup(v);
            }
        });

        //View버튼을 클릭하면 ViewActivity로 전환
        ViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //현재 화면과 전환할 화면 설정
                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
                //화면전환시 bibleMap과 verse를 Data를 함께 전달
                intent.putExtra("data", (Serializable) data);
                //화면전환
                startActivity(intent);
                finish();
            }
        });

        //Test버튼을 클릭하면 난이도 선택 팝업
        TestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTestPopup();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        Intent intent;
        if(requestCode == 1)
        {
            switch (resultCode) {
                case 0:
                    break;
                case 1:
                    intent = new Intent(MainActivity.this, One_TestActivity.class);
                    //화면전환시 bibleMap과 verse를 Data를 함께 전달
                    intent.putExtra("data", (Serializable) data);
                    startActivity(intent);
                    finish();
                    break;
                case 2:
                    intent = new Intent(MainActivity.this, Two_TestActivity.class);
                    //화면전환시 bibleMap과 verse를 Data를 함께 전달
                    intent.putExtra("data", (Serializable) data);
                    startActivity(intent);
                    finish();
                    break;
                case 3:
                    intent = new Intent(MainActivity.this, Three_TestActivity.class);
                    //화면전환시 bibleMap과 verse를 Data를 함께 전달
                    intent.putExtra("data", (Serializable) data);
                    startActivity(intent);
                    finish();
                    break;

            }
        }

    }

    //다운로드 팝업을 띄우는 함수
    private void showSharePopup(View v){
        Intent intent = new Intent(MainActivity.this, SharePopupActivity.class);
        startActivity(intent);
    }

    //연결 팝업을 띄우는 함수
    private void showContactPopup(View v){
        Intent intent = new Intent(MainActivity.this, ContactPopupActivity.class);
        startActivity(intent);
    }

    //시험 난이도 팝업을 띄우는 함수
    private void showTestPopup(){
        Intent intent = new Intent(this, TestPopupActivity.class);
        startActivityForResult(intent, 1);
    }

    public void setDB(){
        MyDBHandler mDbOpenHelper = new MyDBHandler(getApplicationContext());
        mDbOpenHelper.createDataBase();
        mDbOpenHelper.open();

        if(jumpCheck){mDbOpenHelper.update_settings(0, last);}
        else {mDbOpenHelper.update_settings(1, last);}

        for(int i=0;i<20;i++){
            String com = boolToString(complete[i]);
            mDbOpenHelper.update_bible(verse.get(i), com);
        }
        mDbOpenHelper.close();
    }

    public String boolToString(boolean b){
        String txt;
        if(b == true){txt = "T";}
        else {txt = "F";}
        return txt;
    }

    //2초 이내에 뒤로가기를 두번 눌러야 종료
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "두 번 눌러 안녕:)", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() - time < 2000) {
            setDB();
            finish();
        }
    }

    //랜덤하게 말씀 출력
    private void printRandomPhrase(){
        //화면에 출력할 텍스트
        String printTxt = "";
        //1~20랜덤 선택
        r = (int)(Math.random()* 20);
        //개행을 추가하며 텍스트 만들기

        for(int i=0;i<bibleMap.get(verse.get(r)).size();i++){
            if(bibleMap.get(verse.get(r)).get(i) != null) {
                printTxt += bibleMap.get(verse.get(r)).get(i) + "\n";
            }
        }

        //텍스트 출력
        PhraseTxt.setText(printTxt);
        VerseTxt.setText(verse.get(r));
        //텍스트가 길면 18dp, 짧으면 25dp로 설정
        if(is_long[r]){PhraseTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);}
        else{PhraseTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);}
    }

    //Dday계산
    private int calculate_Dday(){
        long Dday = 0;
        long today;
        long dday;

        //calendar 생성(오늘날짜 불러옴)
        Calendar calendar = Calendar.getInstance();
        Calendar dCalendar = Calendar.getInstance();

        //Dday날짜 지정
        dCalendar.set(2020,Calendar.JUNE,7);

        //남은 일 수를 구함
        today = calendar.getTimeInMillis()/(24*60*60*1000);
        dday = dCalendar.getTimeInMillis()/(24*60*60*1000);

        Dday = dday-today;

        return (int)Dday;
    }

    //Dday출력
    private void updateDday(int Dday){
        //Dday가 지나지 않은 경우
       if(Dday> 0){
            DdayTxt.setText(String.format("D-%d",Dday));
        }

       //Dday인 경우
       else if(Dday == 0){
            DdayTxt.setText(String.format("D-day"));
        }

       //Dday가 지난경우
       else{
            Dday = Math.abs(Dday);
            DdayTxt.setText(String.format("D+%d",Dday));
        }
    }

}
