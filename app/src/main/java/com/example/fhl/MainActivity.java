package com.example.fhl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private HashMap<String, ArrayList<String>> bibleMap = new HashMap<>();
    private ArrayList<String> verse = new ArrayList<>();
    private boolean[] is_long = new boolean[20];
    private boolean[] complete = new boolean[20];
    private boolean jumpCheck = true;

    private TextView DdayTxt;
    private TextView PhraseTxt;
    private TextView VerseTxt;
    private Button ViewBtn;
    private Button TestBtn;

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

        //화면전환시 전달된 Data를 저장
        Intent intent = getIntent();
        bibleMap = (HashMap<String, ArrayList<String>>) intent.getExtras().getSerializable("bibleMap");
        verse = intent.getExtras().getStringArrayList("verse");
        is_long = intent.getBooleanArrayExtra("is_long");
        complete = intent.getBooleanArrayExtra("complete");
        jumpCheck = intent.getBooleanExtra("jumpCheck",true);

        //Dday표시하는 함수
        updateDday(calculate_Dday());
        //랜덤한 구절을 표시하는 함수
        printRandomPhrase();

        //View버튼을 클릭하면 ViewActivity로 전환
        ViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //현재 화면과 전환할 화면 설정
                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
                //화면전환시 bibleMap과 verse를 Data를 함께 전달
                intent.putExtra("bibleMap",bibleMap);
                intent.putExtra("verse",verse);
                intent.putExtra("is_long",is_long);
                intent.putExtra("complete",complete);
                intent.putExtra("jumpCheck",jumpCheck);
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

    //팝업을 띄우는 함수
    private void showTestPopup(){
        //dialog를 생성하는 builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Inflater 생성
        LayoutInflater inflater = getLayoutInflater();
        //inflater로 팝업 레이아웃을 불러옴
        View view = inflater.inflate(R.layout.activity_test_popup, null);
        //view와 builder연결
        builder.setView(view);

        //view안의 요소들과 연결
        final RadioGroup starRadio = (RadioGroup)view.findViewById(R.id.starRadio);
        final RadioButton one_star = (RadioButton)view.findViewById(R.id.one_star);
        final RadioButton two_star = (RadioButton)view.findViewById(R.id.two_star);
        final RadioButton three_star = (RadioButton)view.findViewById(R.id.three_star);
        final Button startBtn = (Button)view.findViewById(R.id.startBtn);
        final ImageButton exitBtn = (ImageButton)view.findViewById(R.id.exitBtn);

        //dialog생성
        final AlertDialog dialog = builder.create();
        //뒤로가기로 창 끄기 가능
        builder.setCancelable(true);

        //start버튼을 누르면 라디오 버튼의 정보를 받아서 TestActivity실행
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //라디오버튼에서 선택된 버튼의 id반환
                int id = starRadio.getCheckedRadioButtonId();
                //선택된 버튼이 one_star일때 One_TestActivity로 전환
                if(id == one_star.getId()){
                    //현재 화면과 전환할 화면 설정
                    Intent intent = new Intent(MainActivity.this,One_TestActivity.class);
                    //화면전환시 bibleMap과 verse를 Data를 함께 전달
                    intent.putExtra("bibleMap",bibleMap);
                    intent.putExtra("verse",verse);
                    intent.putExtra("is_long",is_long);
                    intent.putExtra("complete",complete);
                    intent.putExtra("jumpCheck",jumpCheck);
                    //화면전환
                    startActivity(intent);
                    dialog.cancel();
                    finish();
                }
                //선택된 버튼이 two_star일때 Two_TestActivity로 전환
                else if(id == two_star.getId()){
                    //현재 화면과 전환할 화면 설정
                    Intent intent = new Intent(MainActivity.this,Two_TestActivity.class);
                    //화면전환시 bibleMap과 verse를 Data를 함께 전달
                    intent.putExtra("bibleMap",bibleMap);
                    intent.putExtra("verse",verse);
                    intent.putExtra("is_long",is_long);
                    intent.putExtra("complete",complete);
                    intent.putExtra("jumpCheck",jumpCheck);
                    //화면전환
                    startActivity(intent);
                    dialog.cancel();
                    finish();
                }
                //선택된 버튼이 three_star일때 Three_TestActivity로 전환
                else if(id == three_star.getId()){
                    //현재 화면과 전환할 화면 설정
                    Intent intent = new Intent(MainActivity.this,Three_TestActivity.class);
                    //화면전환시 bibleMap과 verse를 Data를 함께 전달
                    intent.putExtra("bibleMap",bibleMap);
                    intent.putExtra("verse",verse);
                    intent.putExtra("is_long",is_long);
                    intent.putExtra("complete",complete);
                    intent.putExtra("jumpCheck",jumpCheck);
                    //화면전환
                    startActivity(intent);
                    dialog.cancel();
                    finish();
                }
            }
        });

        //exit버튼을 누르면 팝업 종료
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        //팝업창 띄우기
        dialog.show();
    }

    public void setDB(){
        MyDBHandler mDbOpenHelper = new MyDBHandler(getApplicationContext());
        mDbOpenHelper.createDataBase();
        mDbOpenHelper.open();

        if(jumpCheck){mDbOpenHelper.update_settings(0);}
        else {mDbOpenHelper.update_settings(1);}

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
        int r = (int)(Math.random()* 20);
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
        dCalendar.set(2020,Calendar.APRIL,12);

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
