package com.YJuu.fhl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class One_TestActivity extends AppCompatActivity {
    final float ANGLE_PER_TIME = (float)360/100;

    private BibleData  data = new BibleData();
    private HashMap<String, ArrayList<String>> bibleMap = new HashMap<>();
    private ArrayList<String> verse = new ArrayList<>();
    private boolean[] is_long = new boolean[20];

    private ImageButton BackBtn;
    private TextView PhraseTxt;
    private TextView QuestionTxt;
    private TextView TimeTxt;
    private Button AnswerBtn01;
    private Button AnswerBtn02;
    private Button AnswerBtn03;
    private Button AnswerBtn04;
    private ImageView Correct;
    private ImageView Wrong;
    private ImageView TimerBar;

    private Canvas canvas;
    private RectF rectF;
    private Paint paint;

    private ArrayList<Integer> qList =  new ArrayList<>();
    private ArrayList<Integer> numList = new ArrayList<>();
    private int nowQ = 0;
    private int answer = -1;
    private int submit = -1;
    private int progress = 0;
    private int correctNum = 0;
    private boolean checked = false;
    private int time = 10;
    private int millis = 0;
    private int MILLISINFUTURE = 10*1000;
    private int COUNT_DOWN_INTERVAL = 100;
    private CountDownTimer timer;
    private boolean isDialog = false;
    private boolean testPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one__test);

        Intent intent = getIntent();

        data = (BibleData) intent.getSerializableExtra("data");
        bibleMap = data.getBibleMap();
        verse = data.getVerse();
        is_long = data.getIs_long();

        BackBtn = (ImageButton)findViewById(R.id.backBtn);
        PhraseTxt = (TextView)findViewById(R.id.phrase);
        QuestionTxt = (TextView)findViewById(R.id.question);
        TimeTxt = (TextView)findViewById(R.id.timeTxt);
        AnswerBtn01 = (Button)findViewById(R.id.a01);
        AnswerBtn02 = (Button)findViewById(R.id.a02);
        AnswerBtn03 = (Button)findViewById(R.id.a03);
        AnswerBtn04 = (Button)findViewById(R.id.a04);
        Correct = (ImageView)findViewById(R.id.correct);
        Wrong = (ImageView)findViewById(R.id.wrong);
        TimerBar = (ImageView)findViewById(R.id.timer);

        Correct.setVisibility(View.INVISIBLE);
        Wrong.setVisibility(View.INVISIBLE);

        setNumList();
        setTimerBar();
        qList = randomNum(20);
        Test();
        setListener();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        Intent intent;
        if(requestCode == 0){
            if(resultCode == 0) {goMain();}
            else if(resultCode == 1) {
                isDialog = false;
                //테스트가 멈춘경우(다이얼로그 상태) 테스트 재시작
                if(testPause){Test();testPause = false;}
                else if(time == 0){checkAnswer();checked = true;}
                else {resumeTimer();}
            }
        }
        else if(requestCode == 1) {
            if(resultCode == 0){goMain();}
        }
    }

    public void setListener(){
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainPopup();
            }
        });
        AnswerBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checked) {
                    submit = 1;
                    setBtnPink(1);
                    submitAnswer();
                }
            }
        });
        AnswerBtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checked) {
                    submit = 2;
                    setBtnPink(2);
                    submitAnswer();
                }
            }
        });
        AnswerBtn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checked) {
                    submit = 3;
                    setBtnPink(3);
                    submitAnswer();
                }
            }
        });
        AnswerBtn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checked) {
                    submit = 4;
                    setBtnPink(4);
                    submitAnswer();
                }
            }
        });
    }

    public void submitAnswer(){
        timer.cancel();
        time = 10;
        checked = true;
        checkAnswer();
    }

    //시험보기
    public void Test(){
        //액티비티가 종료되지 않았으면(홈화면으로 이동하지 않았으면)
        if(!isFinishing()){
            //홈화면가기 창이 떠있지 않으면
            if(!isDialog) {
                //제출 답안 초기화
                submit = -1;
                //현재 진행상황이 20보다 작으면(19문제 이하로 푼 상황이면)
                if (progress < 20) {
                    //랜덤한 20개 중에서 progress번째에 있는 구절을 문제로
                    nowQ = qList.get(progress);
                    //문제출력
                    printQuestion();
                    //시간 10초
                    time = 10;
                    //밀리초
                    millis = time*10;
                    //타임바 초기화
                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    //타이머 시작
                    setCountDown();
                }
                //현재 진행상황이 20(20문제 전부 푼 경우)
                else {
                    showResultPopup();
                }
            }
            //창이 떠있다면 퍼즈
            else {testPause = true;}
        }
    }

    public void checkAnswer(){
        progress++;
        if(submit == answer){
            System.out.println("submit:"+submit+"/answer:"+answer);
            Correct.setVisibility(View.VISIBLE);
            Correct.bringToFront();
            correctNum++;
            setBtnGreen(answer);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Correct.setVisibility(View.INVISIBLE);
                    setBtnBeige(answer);
                    setBtnBeige(submit);
                    checked = false;
                    Test();
                }
            }, 1500);
        }
        else {
            System.out.println("submit:"+submit+"/answer:"+answer);
            Wrong.setVisibility(View.VISIBLE);
            Wrong.bringToFront();
            setBtnGreen(answer);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Wrong.setVisibility(View.INVISIBLE);
                    setBtnBeige(answer);
                    setBtnBeige(submit);
                    checked = false;
                    Test();
                }
            }, 1500);
        }
    }

    public void setBtnGreen(int ans){
        switch (ans){
            case 1:
                AnswerBtn01.setBackgroundColor(getResources().getColor(R.color.lightgreen)); break;
            case 2:
                AnswerBtn02.setBackgroundColor(getResources().getColor(R.color.lightgreen)); break;
            case 3:
                AnswerBtn03.setBackgroundColor(getResources().getColor(R.color.lightgreen)); break;
            case 4:
                AnswerBtn04.setBackgroundColor(getResources().getColor(R.color.lightgreen)); break;
        }
    }

    public void setBtnPink(int ans){
        switch (ans){
            case 1:
                AnswerBtn01.setBackgroundColor(getResources().getColor(R.color.lightpink)); break;
            case 2:
                AnswerBtn02.setBackgroundColor(getResources().getColor(R.color.lightpink)); break;
            case 3:
                AnswerBtn03.setBackgroundColor(getResources().getColor(R.color.lightpink)); break;
            case 4:
                AnswerBtn04.setBackgroundColor(getResources().getColor(R.color.lightpink)); break;
        }
    }

    public void setBtnBeige(int ans){
        switch (ans){
            case 1:
                AnswerBtn01.setBackgroundResource(R.drawable.beige_button); break;
            case 2:
                AnswerBtn02.setBackgroundResource(R.drawable.beige_button); break;
            case 3:
                AnswerBtn03.setBackgroundResource(R.drawable.beige_button); break;
            case 4:
                AnswerBtn04.setBackgroundResource(R.drawable.beige_button); break;
        }
    }

    public void setCountDown(){
        timer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                millis--;
                time = millis/10;
                String timeTxt = Integer.toString(time+1);
                System.out.println("countDown");
                TimeTxt.setText(timeTxt);
                float angle = 360 - millis * ANGLE_PER_TIME;
                canvas.drawArc(rectF, -90, angle, false, paint);
            }
            @Override
            public void onFinish() {
                checkAnswer();
                TimeTxt.setText("0");
                checked = true;
                canvas.drawArc(rectF, -90, 360, false, paint);
            }
        };
        timer.start();
    }

    public void setTimerBar(){
        Bitmap bitmap = Bitmap.createBitmap(500,500,Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);
        canvas.drawColor(getResources().getColor(R.color.transparent));

        TimerBar.setImageBitmap(bitmap);

        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.hotpink));
        paint.setStrokeWidth(40);
        rectF = new RectF();
        rectF.set(50,50,450,450);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void resumeTimer(){
        int resumeTime = time;
        resumeTime *= 1000;
        timer = new CountDownTimer(resumeTime, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                millis--;
                time = millis/10;
                String timeTxt = Integer.toString(time);
                System.out.println("resume");
                TimeTxt.setText(timeTxt);
                float angle = 360 - millis * ANGLE_PER_TIME;
                canvas.drawArc(rectF, -90, angle, false, paint);
            }

            @Override
            public void onFinish() {
                checkAnswer();
                checked = true;
                canvas.drawArc(rectF, -90, 360, false, paint);
            }
        };
        timer.start();
    }

    public void showMainPopup(){
        timer.cancel();
        isDialog = true;
        Intent intent = new Intent(this, GoMainPopupActivity.class);
        intent.putExtra("progress",progress);
        intent.putExtra("questionNum",20);
        startActivityForResult(intent, 0);

    }

    public void showResultPopup(){
        Intent intent = new Intent(this, ResultPopupActivity.class);
        intent.putExtra("correctNum",correctNum);
        intent.putExtra("questionNum",20);
        startActivityForResult(intent, 1);
    }
    
    public void printQuestion(){
        //화면에 출력할 텍스트
        String printTxt = "";
        //개행을 추가하며 텍스트 만들기

        for(int i=0;i<bibleMap.get(verse.get(nowQ)).size();i++){
            if(bibleMap.get(verse.get(nowQ)).get(i) != null) {
                printTxt += bibleMap.get(verse.get(nowQ)).get(i) + "\n";
            }
        }

        //텍스트 출력
        PhraseTxt.setText(printTxt);
        QuestionTxt.setText("Q. "+String.format("%02d", progress+1)+"/20");
        //텍스트가 길면 18dp, 짧으면 25dp로 설정
        if(is_long[nowQ]){PhraseTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);}
        else{PhraseTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);}

        printButton();
    }
    
    public void printButton(){
        //1~4중에 정답 번호 랜덤 선택
        answer = (int)(Math.random()* 4)+1;
        //확인용
        System.out.println("Phrase Num"+nowQ);
        System.out.println("Answer"+answer+"Button");
        //현재 문제(nowQ)를 제외한 나머지 중에서 3개 랜덤 선택
        ArrayList<Integer> temp = randomExcept(nowQ, 3);
        //정답 번호 순서에 nowQ삽입
        temp.add(answer-1,nowQ);
        int n = 0;
        //버튼 출력 4번 반복
        for(int i = 0; i<4;i++){
            //버튼 번호
            int btnNum = i+1;
            //btnNum번째 버튼에 btnNum)장절 출력
            setBtnTxt(btnNum, btnNum+ ")  "+verse.get(temp.get(n)));
            n++;
        }

    }

    //n번째 버튼에 해당 텍스트 출력
    public void setBtnTxt(int n, String txt){
        switch (n) {
            case 1:
                AnswerBtn01.setText(txt);
            case 2:
                AnswerBtn02.setText(txt);
            case 3:
                AnswerBtn03.setText(txt);
            case 4:
                AnswerBtn04.setText(txt);
        }
    }

    //선지 생성용. num을 제외한 20개 중에 max개 선택
    public ArrayList<Integer> randomExcept(int num, int max){
        ArrayList<Integer> temp = new ArrayList<>();
        numList.remove(num);
        temp = randomNum(max);

        return temp;
    }

    //최대 max개의 정수 선택
    public ArrayList<Integer> randomNum(int max){
        ArrayList<Integer> temp = new ArrayList<>();
        while(temp.size() <max)
        {
            int r = (int)(Math.random()* 20);
            if(numList.contains(r)){
                temp.add(r);
                numList.remove(numList.indexOf(r));
            }
        }
        setNumList();
        return temp;
    }
    
    public void goMain(){
        Intent intent = new Intent(One_TestActivity.this,MainActivity.class);
        //화면전환시 bibleMap과 verse를 Data를 함께 전달
        intent.putExtra("data", (Serializable) data);
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        showMainPopup();
    }

    public void setNumList(){
        numList.clear();
        numList.add(0);
        numList.add(1);
        numList.add(2);
        numList.add(3);
        numList.add(4);
        numList.add(5);
        numList.add(6);
        numList.add(7);
        numList.add(8);
        numList.add(9);
        numList.add(10);
        numList.add(11);
        numList.add(12);
        numList.add(13);
        numList.add(14);
        numList.add(15);
        numList.add(16);
        numList.add(17);
        numList.add(18);
        numList.add(19);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            timer.cancel();
        } catch (Exception e){ }
        timer = null;
    }
}
