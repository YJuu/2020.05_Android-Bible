package com.example.fhl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class One_TestActivity extends AppCompatActivity {
    private HashMap<String, ArrayList<String>> bibleMap = new HashMap<>();
    private ArrayList<String> verse = new ArrayList<>();
    private boolean[] is_long = new boolean[20];
    private boolean[] complete = new boolean[20];
    private boolean jumpCheck = true;

    private ImageButton BackBtn;
    private TextView PhraseTxt;
    private TextView QuestionTxt;
    private TextView TimeTxt;
    private ProgressBar TimerBar;
    private Button AnswerBtn01;
    private Button AnswerBtn02;
    private Button AnswerBtn03;
    private Button AnswerBtn04;
    private ImageView Correct;
    private ImageView Wrong;

    private ArrayList<Integer> qList =  new ArrayList<>();
    private ArrayList<Integer> numList = new ArrayList<>();
    private int nowQ = 0;
    private int answer = -1;
    private int submit = -1;
    private int progress = 0;
    private int correctNum = 0;
    private boolean checked = false;
    private int time = 11;
    int timeProgress = 0;
    private int MILLISINFUTURE = 11*1000;
    private int COUNT_DOWN_INTERVAL = 1000;
    private CountDownTimer timer;
    private boolean isDialog = false;
    private boolean testPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one__test);

        Intent intent = getIntent();
        bibleMap = (HashMap<String, ArrayList<String>>) intent.getExtras().getSerializable("bibleMap");
        verse = intent.getExtras().getStringArrayList("verse");
        is_long = intent.getBooleanArrayExtra("is_long");
        complete = intent.getBooleanArrayExtra("complete");
        jumpCheck = intent.getBooleanExtra("jumpCheck",true);

        BackBtn = (ImageButton)findViewById(R.id.backBtn);
        PhraseTxt = (TextView)findViewById(R.id.phrase);
        QuestionTxt = (TextView)findViewById(R.id.question);
        TimeTxt = (TextView)findViewById(R.id.timeTxt);
        TimerBar = (ProgressBar)findViewById(R.id.timer);
        AnswerBtn01 = (Button)findViewById(R.id.a01);
        AnswerBtn02 = (Button)findViewById(R.id.a02);
        AnswerBtn03 = (Button)findViewById(R.id.a03);
        AnswerBtn04 = (Button)findViewById(R.id.a04);
        Correct = (ImageView)findViewById(R.id.correct);
        Wrong = (ImageView)findViewById(R.id.wrong);

        Correct.setVisibility(View.INVISIBLE);
        Wrong.setVisibility(View.INVISIBLE);

        setNumList();
        qList = randomNum(20);
        Test();
        setListener();
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
        time = 11;
        checked = true;
        checkAnswer();
    }

    public void Test(){
        if(!isFinishing()){
            if(!isDialog) {
                submit = -1;
                if (progress < 20) {
                    nowQ = qList.get(progress);
                    printQuestion();
                    time = 11;
                    setCountDown();
                } else {
                    showResultPopup();
                }
            }
            else {testPause = true;}
        }
    }

    public void checkAnswer(){

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
                AnswerBtn01.setBackgroundColor(getResources().getColor(R.color.transparent_green)); break;
            case 2:
                AnswerBtn02.setBackgroundColor(getResources().getColor(R.color.transparent_green)); break;
            case 3:
                AnswerBtn03.setBackgroundColor(getResources().getColor(R.color.transparent_green)); break;
            case 4:
                AnswerBtn04.setBackgroundColor(getResources().getColor(R.color.transparent_green)); break;
        }
    }

    public void setBtnPink(int ans){
        switch (ans){
            case 1:
                AnswerBtn01.setBackgroundColor(getResources().getColor(R.color.transparent_pink)); break;
            case 2:
                AnswerBtn02.setBackgroundColor(getResources().getColor(R.color.transparent_pink)); break;
            case 3:
                AnswerBtn03.setBackgroundColor(getResources().getColor(R.color.transparent_pink)); break;
            case 4:
                AnswerBtn04.setBackgroundColor(getResources().getColor(R.color.transparent_pink)); break;
        }
    }

    public void setBtnBeige(int ans){
        switch (ans){
            case 1:
                AnswerBtn01.setBackgroundColor(getResources().getColor(R.color.transparent_beige)); break;
            case 2:
                AnswerBtn02.setBackgroundColor(getResources().getColor(R.color.transparent_beige)); break;
            case 3:
                AnswerBtn03.setBackgroundColor(getResources().getColor(R.color.transparent_beige)); break;
            case 4:
                AnswerBtn04.setBackgroundColor(getResources().getColor(R.color.transparent_beige)); break;
        }
    }

    public void setCountDown(){
        timer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                String timeTxt = Integer.toString(time);
                System.out.println("countDown");
                TimeTxt.setText(timeTxt);
            }
            @Override
            public void onFinish() {
                checkAnswer();
                checked = true;
            }
        };
        timer.start();
    }

    public void setTimerBar(){

        timeProgress = time/10*100;
    }

    public void resumeTimer(){
        int resumeTime = time;
        resumeTime *= 1000;
        timer = new CountDownTimer(resumeTime, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                String timeTxt = Integer.toString(time);
                System.out.println("resume");
                TimeTxt.setText(timeTxt);
            }

            @Override
            public void onFinish() {
                checkAnswer();
                checked = true;
            }
        };
        timer.start();
    }

    public void showMainPopup(){
        timer.cancel();
        //Inflater 생성
        LayoutInflater inflater = getLayoutInflater();
        //inflater로 팝업 레이아웃을 불러옴
        View view = inflater.inflate(R.layout.activity_gomain_popup, null);

        final Dialog dialog = new Dialog(this);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.cancel();
                isDialog = false;
                if(testPause){Test();testPause = false;}
                else if(time == 0){checkAnswer();checked = true;}
                else {resumeTimer();}
            }
        });

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width=1000;
        params.height=600;
        dialog.getWindow().setAttributes(params);
        isDialog = true;

        //view안의 요소들과 연결
        final Button cancelBtn = (Button)view.findViewById(R.id.cancelBtn);
        final Button goMainBtn = (Button)view.findViewById(R.id.goMainBtn);
        final TextView noticeTxt = (TextView)view.findViewById(R.id.notice);

        int remain = 20-progress+1;
        noticeTxt.setText(progress-1+"문제를 풀었고, "+remain+"문제 남았어요!");

        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        goMainBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goMain();
                dialog.cancel();
            }
        });

        //팝업창 띄우기
        dialog.show();
    }

    public String setMessage(){
        String message="";
        if(correctNum<11){message = "더 공부해요:)"; }
        else if(correctNum<16){message = "잘했어요!";}
        else if(correctNum<20){message = "대단하네요♥";}
        else{message = "경★축"+"\n"+"만 점";}

        return message;
    }

    public void showResultPopup(){
        //Inflater 생성
        LayoutInflater inflater = getLayoutInflater();
        //inflater로 팝업 레이아웃을 불러옴
        View view = inflater.inflate(R.layout.activity_result_popup, null);

        final Dialog dialog = new Dialog(this);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                goMain();
                dialog.cancel();
            }
        });
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width=700;
        params.height=600;
        dialog.getWindow().setAttributes(params);

        //view안의 요소들과 연결
        final ImageButton exitBtn = (ImageButton)view.findViewById(R.id.exitBtn);
        final TextView resultTxt = (TextView)view.findViewById(R.id.resultTxt);
        final TextView messageTxt = (TextView)view.findViewById(R.id.messageTxt);

        resultTxt.setText(correctNum+"/20");
        String message = setMessage();
        messageTxt.setText(message);

        exitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        //팝업창 띄우기
        dialog.show();
    }
    
    public void printQuestion(){
        progress++;
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
        QuestionTxt.setText("Q. "+progress+"/20");
        //텍스트가 길면 18dp, 짧으면 25dp로 설정
        if(is_long[nowQ]){PhraseTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);}
        else{PhraseTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,25);}

        printButton();
    }
    
    public void printButton(){
        answer = (int)(Math.random()* 4)+1;
        System.out.println("Phrase Num"+nowQ);
        System.out.println("Answer"+answer+"Button");

        ArrayList<Integer> temp = randomExcept(nowQ, 3);
        temp.add(answer-1,nowQ);
        int n = 0;

        for(int i = 0; i<4;i++){
            int btnNum = i+1;
            setBtnTxt(btnNum, btnNum+ ")  "+verse.get(temp.get(n)));
            n++;
        }

    }

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

    public ArrayList<Integer> randomExcept(int num, int max){
        ArrayList<Integer> temp = new ArrayList<>();
        numList.remove(num);
        temp = randomNum(max);

        return temp;
    }

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
        intent.putExtra("bibleMap",bibleMap);
        intent.putExtra("verse",verse);
        intent.putExtra("is_long",is_long);
        intent.putExtra("complete",complete);
        intent.putExtra("jumpCheck",jumpCheck);
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
