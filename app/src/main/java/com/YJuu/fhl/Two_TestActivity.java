package com.YJuu.fhl;

import androidx.appcompat.app.AppCompatActivity;

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
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Two_TestActivity extends AppCompatActivity {
    final float ANGLE_PER_TIME = (float) 360 / 200;

    private BibleData data = new BibleData();
    private HashMap<String, ArrayList<String>> bibleMap = new HashMap<>();
    private ArrayList<String> verse = new ArrayList<>();
    private boolean[] is_long = new boolean[20];

    private ImageButton BackBtn;
    private ImageButton BackSpace;
    private TextView VerseTxt;
    private TextView QuestionTxt;
    private TextView TimeTxt;
    private TextView OrderTxt;
    private TextView AnswerTxt;
    private Button AnswerBtn01;
    private Button AnswerBtn02;
    private Button AnswerBtn03;
    private Button AnswerBtn04;
    private Button AnswerBtn05;
    private Button AnswerBtn06;
    private ImageView Correct;
    private ImageView Wrong;
    private ImageView TimerBar;

    private Canvas canvas;
    private RectF rectF;
    private Paint paint;

    private ArrayList<Integer> numList = new ArrayList<>();
    private ArrayList<Integer> qList = new ArrayList<>();
    private ArrayList<Integer> btnList = new ArrayList<>();
    private ArrayList<Integer> aList = new ArrayList<>();
    private ArrayList<Integer> inputList = new ArrayList<>();
    private ArrayList<Integer> corrects = new ArrayList<>();
    private int nowQ = 0;
    private int progress = 0;
    private int correctNum = 0;
    private int now = 1;
    private boolean isDialog = false;
    private boolean testPause = false;
    private boolean finished = false;
    private int time = 20;
    private int millis = 0;
    private int MILLISINFUTURE = 20 * 1000;
    private int COUNT_DOWN_INTERVAL = 100;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two__test);

        Intent intent = getIntent();

        data = (BibleData) intent.getSerializableExtra("data");
        bibleMap = data.getBibleMap();
        verse = data.getVerse();
        is_long = data.getIs_long();

        BackBtn = (ImageButton) findViewById(R.id.backBtn);
        BackSpace = (ImageButton) findViewById(R.id.backspace);
        VerseTxt = (TextView) findViewById(R.id.verseText);
        QuestionTxt = (TextView) findViewById(R.id.question);
        TimeTxt = (TextView) findViewById(R.id.timeTxt);
        OrderTxt = (TextView) findViewById(R.id.order);
        AnswerTxt = (TextView) findViewById(R.id.answer);
        AnswerBtn01 = (Button) findViewById(R.id.a01);
        AnswerBtn02 = (Button) findViewById(R.id.a02);
        AnswerBtn03 = (Button) findViewById(R.id.a03);
        AnswerBtn04 = (Button) findViewById(R.id.a04);
        AnswerBtn05 = (Button) findViewById(R.id.a05);
        AnswerBtn06 = (Button) findViewById(R.id.a06);
        Correct = (ImageView) findViewById(R.id.correct);
        Wrong = (ImageView) findViewById(R.id.wrong);
        TimerBar = (ImageView) findViewById(R.id.timer);

        Correct.setVisibility(View.INVISIBLE);
        Wrong.setVisibility(View.INVISIBLE);

        setNumList();
        setTimerBar();
        setListener();
        qList = randomNum(10);
        Log.d("qList", qList.toString());
        Test();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        Intent intent;
        if (requestCode == 0) {
            if (resultCode == 0) {
                goMain();
            } else if (resultCode == 1) {
                isDialog = false;
                //테스트가 멈춘경우(다이얼로그 상태) 테스트 재시작
                if (testPause) {
                    Test();
                    testPause = false;
                } else if (time == 0) {
                    checkAnswer();
                    finished = true;
                } else {
                    resumeTimer();
                }
            }
        } else if (requestCode == 1) {
            if (resultCode == 0) {
                goMain();
            }
        }
    }

    public void setListener() {
        AnswerBtn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {
                    inputList.add(1);
                    OrderTxt.setText(setOrder(aList.size(), inputList));

                    //now에 들어갈 순서 맞는지 확인 > corrects에 넣기
                    if (1 == aList.get(now - 1)) {
                        corrects.add(1);
                    }
                    //선택 못하게 막기
                    AnswerBtn01.setEnabled(false);
                    AnswerBtn01.setBackgroundColor(getResources().getColor(R.color.beige_gray));

                    now++;
                    //전부 선택됨
                    if (now > aList.size()) {
                        //답확인
                        checkAnswer();
                    }
                }
            }
        });
        AnswerBtn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {
                    inputList.add(2);
                    OrderTxt.setText(setOrder(aList.size(), inputList));
                    //now에 들어갈 순서 맞는지 확인 > corrects에 넣기
                    if (2 == aList.get(now - 1)) {
                        corrects.add(2);
                    }
                    //선택 못하게 막기
                    AnswerBtn02.setEnabled(false);
                    AnswerBtn02.setBackgroundColor(getResources().getColor(R.color.beige_gray));

                    now++;
                    //전부 선택됨
                    if (now > aList.size()) {
                        //답확인
                        checkAnswer();
                    }
                }
            }
        });
        AnswerBtn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {
                    inputList.add(3);
                    OrderTxt.setText(setOrder(aList.size(), inputList));
                    //now에 들어갈 순서 맞는지 확인 > corrects에 넣기
                    if (3 == aList.get(now - 1)) {
                        corrects.add(3);
                    }
                    //선택 못하게 막기
                    AnswerBtn03.setEnabled(false);
                    AnswerBtn03.setBackgroundColor(getResources().getColor(R.color.beige_gray));

                    now++;
                    //전부 선택됨
                    if (now > aList.size()) {
                        //답확인
                        checkAnswer();
                    }
                }
            }
        });
        AnswerBtn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {
                    inputList.add(4);
                    OrderTxt.setText(setOrder(aList.size(), inputList));
                    //now에 들어갈 순서 맞는지 확인 > corrects에 넣기
                    if (4 == aList.get(now - 1)) {
                        corrects.add(4);
                    }
                    //선택 못하게 막기
                    AnswerBtn04.setEnabled(false);
                    AnswerBtn04.setBackgroundColor(getResources().getColor(R.color.beige_gray));

                    now++;
                    //전부 선택됨
                    if (now > aList.size()) {
                        //답확인
                        checkAnswer();
                    }
                }
            }
        });
        AnswerBtn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {
                    inputList.add(5);
                    OrderTxt.setText(setOrder(aList.size(), inputList));
                    //now에 들어갈 순서 맞는지 확인 > corrects에 넣기
                    if (5 == aList.get(now - 1)) {
                        corrects.add(5);
                    }
                    //선택 못하게 막기
                    AnswerBtn05.setEnabled(false);
                    AnswerBtn05.setBackgroundColor(getResources().getColor(R.color.beige_gray));

                    now++;
                    //전부 선택됨
                    if (now > aList.size()) {
                        //답확인
                        checkAnswer();
                    }
                }
            }
        });
        AnswerBtn06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {
                    inputList.add(6);
                    OrderTxt.setText(setOrder(aList.size(), inputList));
                    //now에 들어갈 순서 맞는지 확인 > corrects에 넣기
                    if (6 == aList.get(now - 1)) {
                        corrects.add(6);
                    }
                    //선택 못하게 막기
                    AnswerBtn06.setEnabled(false);
                    AnswerBtn06.setBackgroundColor(getResources().getColor(R.color.beige_gray));
                    now++;
                    //전부 선택됨
                    if (now > aList.size()) {
                        //답확인
                        checkAnswer();
                    }
                }
            }
        });
        BackSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!finished) {
                    int temp = 0;
                    if(!inputList.isEmpty()) {
                        temp = inputList.get(inputList.size() - 1);
                        inputList.remove(inputList.indexOf(temp));
                        OrderTxt.setText(setOrder(aList.size(), inputList));
                        if (corrects.contains(temp)) {
                            corrects.remove(corrects.indexOf(temp));
                        }
                        enableBtn(temp);
                        now--;
                    }
                }
            }
        });
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainPopup();
            }
        });
    }

    public void enableBtn(int num) {
        switch (num) {
            case 1:
                AnswerBtn01.setEnabled(true);
                AnswerBtn01.setBackgroundResource(R.drawable.beige_button);
                break;
            case 2:
                AnswerBtn02.setEnabled(true);
                AnswerBtn02.setBackgroundResource(R.drawable.beige_button);
                break;
            case 3:
                AnswerBtn03.setEnabled(true);
                AnswerBtn03.setBackgroundResource(R.drawable.beige_button);
                break;
            case 4:
                AnswerBtn04.setEnabled(true);
                AnswerBtn04.setBackgroundResource(R.drawable.beige_button);
                break;
            case 5:
                AnswerBtn05.setEnabled(true);
                AnswerBtn05.setBackgroundResource(R.drawable.beige_button);
                break;
            case 6:
                AnswerBtn06.setEnabled(true);
                AnswerBtn06.setBackgroundResource(R.drawable.beige_button);
                break;
        }
    }

    public String setOrder(int num, ArrayList<Integer> list) {
        //order에 출력할 문자열 생성
        String orderTxt = "";
        int i = 0;
        while (i < num) {
            if (i < list.size() - 1) {
                orderTxt += list.get(i) + " - ";
            } else if (i < list.size()) {
                orderTxt += list.get(i);
            } else {
                if(i == 0){orderTxt += "__";}
                else {orderTxt += " - __";}
            }
            i++;
        }
        return orderTxt;
    }

    //시험보기
    public void Test() {
        //액티비티가 종료되지 않았으면(홈화면으로 이동하지 않았으면)
        if (!isFinishing()) {
            //홈화면가기 창이 떠있지 않으면
            if (!isDialog) {
                //제출 답안 초기화
                now = 1;
                //현재 진행상황이 10보다 작으면(10문제 이하로 푼 상황이면)
                if (progress < 10) {
                    //랜덤한 10개 중에서 progress번째에 있는 구절을 문제로
                    nowQ = qList.get(progress);
                    //문제출력
                    printQuestion();
                    inputList.clear();
                    corrects.clear();
                    //시간 20초
                    time = 20;
                    //밀리초
                    millis = time * 10;
                    //타임바 초기화
                    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    //타이머 시작
                    setCountDown();
                }
                //현재 진행상황이 10(10문제 전부 푼 경우)
                else {
                    showResultPopup();
                }
            }
            //창이 떠있다면 퍼즈
            else {
                testPause = true;
            }
        }
    }

    //문제 출력
    public void printQuestion() {
        //구절 출력
        VerseTxt.setText(verse.get(nowQ));
        //문제 번호 출력
        QuestionTxt.setText("Q. " + String.format("%02d", progress + 1) + "/10");

        //말씀 라인 수를 얻어옴
        int lineNum = 0;
        for (int i = 0; i < bibleMap.get(verse.get(nowQ)).size(); i++) {
            if (bibleMap.get(verse.get(nowQ)).get(i) != null) {
                lineNum++;
            }
        }

        btnList.clear();
        //말씀 라인 랜덤 배치
        while (btnList.size() < lineNum) {
            int r = (int) (Math.random() * lineNum);
            if (!btnList.contains(r + 1)) {
                btnList.add(r + 1);
            }
        }
        Log.d("btnList", btnList.toString());

        //답 순서
        aList.clear();
        int j = 1;
        while (aList.size() < lineNum) {
            for (int i = 0; i < lineNum; i++) {
                if (btnList.get(i) == j) {
                    aList.add(i + 1);
                }
            }
            j++;
        }
        Log.d("aList", aList.toString());

        //글자크기 설정
        int textSize = 18;
        if (is_long[nowQ]) {
            textSize = 16;
        } else {
            textSize = 20;
        }

        //버튼 출력
        int nowLine = 0;
        for (int i = 1; i < 7; i++) {
            String line = "";
            if (i < lineNum + 1) {
                nowLine = btnList.get(i - 1);
                line += i + ") ";
                line += bibleMap.get(verse.get(nowQ)).get(nowLine - 1).replace("\"", "");
            } else {
                line = "";
            }
            setButton(i, line, textSize);
        }

        //order에 출력할 문자열 생성
        String orderTxt = "";
        for (int i = 0; i < lineNum - 1; i++) {
            orderTxt += "__ - ";
        }
        orderTxt += "__";
        OrderTxt.setText(orderTxt);
        AnswerTxt.setText("");
    }

    //버튼 출력
    public void setButton(int buttonNum, String text, int textSize) {
        switch (buttonNum) {
            case 1:
                AnswerBtn01.setText(text);
                AnswerBtn01.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                AnswerBtn01.setBackgroundResource(R.drawable.beige_button);
                AnswerBtn01.setEnabled(true);
                break;
            case 2:
                AnswerBtn02.setText(text);
                AnswerBtn02.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                AnswerBtn02.setBackgroundResource(R.drawable.beige_button);
                AnswerBtn02.setEnabled(true);
                break;
            case 3:
                AnswerBtn03.setText(text);
                AnswerBtn03.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                AnswerBtn03.setBackgroundResource(R.drawable.beige_button);
                AnswerBtn03.setEnabled(true);
                break;
            case 4:
                AnswerBtn04.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                if (text == "") {
                    AnswerBtn04.setText("");
                    AnswerBtn04.setBackgroundColor(getResources().getColor(R.color.transparent));
                    AnswerBtn04.setEnabled(false);
                } else {
                    AnswerBtn04.setText(text);
                    AnswerBtn04.setBackgroundResource(R.drawable.beige_button);
                    AnswerBtn04.setEnabled(true);
                }
                break;
            case 5:
                AnswerBtn05.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                if (text == "") {
                    AnswerBtn05.setText("");
                    AnswerBtn05.setBackgroundColor(getResources().getColor(R.color.transparent));
                    AnswerBtn05.setEnabled(false);
                } else {
                    AnswerBtn05.setText(text);
                    AnswerBtn05.setBackgroundResource(R.drawable.beige_button);
                    AnswerBtn05.setEnabled(true);
                }
                break;
            case 6:
                AnswerBtn06.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
                if (text == "") {
                    AnswerBtn06.setText("");
                    AnswerBtn06.setBackgroundColor(getResources().getColor(R.color.transparent));
                    AnswerBtn06.setEnabled(false);
                } else {
                    AnswerBtn06.setText(text);
                    AnswerBtn06.setBackgroundResource(R.drawable.beige_button);
                    AnswerBtn06.setEnabled(true);
                }
                break;
        }
    }

    public void checkAnswer() {
        timer.cancel();
        time = 20;
        finished = true;
        boolean correct = false;
        Log.d("corrects", corrects.toString());
        if (corrects.size() == aList.size()) {
            correct = true;
        } else {
            correct = false;
        }
        AnswerTxt.setText("Answer : " + setOrder(aList.size(), aList));

        setAnswerBtn();
        progress++;

        //정답
        if (correct) {
            Correct.setVisibility(View.VISIBLE);
            Correct.bringToFront();
            correctNum++;
        }
        //오답
        else {
            Wrong.setVisibility(View.VISIBLE);
            Wrong.bringToFront();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Correct.setVisibility(View.INVISIBLE);
                Wrong.setVisibility(View.INVISIBLE);
                finished = false;
                Test();
            }
        }, 3500);
    }

    public void setAnswerBtn() {
        //글자크기 설정
        int textSize = 18;
        if (is_long[nowQ]) {
            textSize = 16;
        } else {
            textSize = 20;
        }

        for (int i = 1; i < aList.size() + 1; i++) {
            String text = "";
            text += aList.get(i - 1) + ") ";
            text += bibleMap.get(verse.get(nowQ)).get(i - 1).replace("\"", "");
            setButton(i, text, textSize);
            if (corrects.contains(i)) {
                setBtnGreen(i);
            } else {
                setBtnPink(i);
            }
        }
    }

    public void setBtnGreen(int ans) {
        switch (ans) {
            case 1:
                AnswerBtn01.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                break;
            case 2:
                AnswerBtn02.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                break;
            case 3:
                AnswerBtn03.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                break;
            case 4:
                AnswerBtn04.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                break;
            case 5:
                AnswerBtn05.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                break;
            case 6:
                AnswerBtn06.setBackgroundColor(getResources().getColor(R.color.lightgreen));
                break;
        }
    }

    public void setBtnPink(int ans) {
        switch (ans) {
            case 1:
                AnswerBtn01.setBackgroundColor(getResources().getColor(R.color.lightpink));
                break;
            case 2:
                AnswerBtn02.setBackgroundColor(getResources().getColor(R.color.lightpink));
                break;
            case 3:
                AnswerBtn03.setBackgroundColor(getResources().getColor(R.color.lightpink));
                break;
            case 4:
                AnswerBtn04.setBackgroundColor(getResources().getColor(R.color.lightpink));
                break;
            case 5:
                AnswerBtn05.setBackgroundColor(getResources().getColor(R.color.lightpink));
                break;
            case 6:
                AnswerBtn06.setBackgroundColor(getResources().getColor(R.color.lightpink));
                break;
        }
    }

    public void setCountDown() {
        timer = new CountDownTimer(MILLISINFUTURE, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                millis--;
                time = millis / 10;
                String timeTxt = Integer.toString(time + 1);
                System.out.println("countDown");
                TimeTxt.setText(timeTxt);
                float angle = 360 - millis * ANGLE_PER_TIME;
                canvas.drawArc(rectF, -90, angle, false, paint);
            }

            @Override
            public void onFinish() {
                checkAnswer();
                TimeTxt.setText("0");
                canvas.drawArc(rectF, -90, 360, false, paint);
            }
        };
        timer.start();
    }

    public void setTimerBar() {
        Bitmap bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);

        canvas = new Canvas(bitmap);
        canvas.drawColor(getResources().getColor(R.color.transparent));

        TimerBar.setImageBitmap(bitmap);

        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.hotpink));
        paint.setStrokeWidth(40);
        rectF = new RectF();
        rectF.set(50, 50, 450, 450);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void resumeTimer() {
        int resumeTime = time;
        resumeTime *= 1000;
        timer = new CountDownTimer(resumeTime, COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                millis--;
                time = millis / 10;
                String timeTxt = Integer.toString(time);
                System.out.println("resume");
                TimeTxt.setText(timeTxt);
                float angle = 360 - millis * ANGLE_PER_TIME;
                canvas.drawArc(rectF, -90, angle, false, paint);
            }

            @Override
            public void onFinish() {
                checkAnswer();
                finished = true;
                canvas.drawArc(rectF, -90, 360, false, paint);
            }
        };
        timer.start();
    }

    public void onBackPressed() {
        showMainPopup();
    }

    public void showMainPopup() {
        timer.cancel();
        isDialog = true;
        Intent intent = new Intent(this, GoMainPopupActivity.class);
        intent.putExtra("progress", progress);
        intent.putExtra("questionNum", 10);
        startActivityForResult(intent, 0);

    }

    public void showResultPopup() {
        Intent intent = new Intent(this, ResultPopupActivity.class);
        intent.putExtra("correctNum", correctNum);
        intent.putExtra("questionNum", 10);
        startActivityForResult(intent, 1);
    }

    public void goMain() {
        Intent intent = new Intent(Two_TestActivity.this, MainActivity.class);
        //화면전환시 bibleMap과 verse를 Data를 함께 전달
        intent.putExtra("data", (Serializable) data);
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    //최대 max개의 정수 선택
    public ArrayList<Integer> randomNum(int max) {
        ArrayList<Integer> temp = new ArrayList<>();
        while (temp.size() < max) {
            int r = (int) (Math.random() * 20);
            if (numList.contains(r)) {
                temp.add(r);
                numList.remove(numList.indexOf(r));
            }
        }
        setNumList();
        return temp;
    }

    public void setNumList() {
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

}