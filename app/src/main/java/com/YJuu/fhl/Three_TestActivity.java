package com.YJuu.fhl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Three_TestActivity extends AppCompatActivity {

    private BibleData  data = new BibleData();
    private HashMap<String, ArrayList<String>> bibleMap = new HashMap<>();
    private ArrayList<String> verse = new ArrayList<>();
    private boolean[] is_long = new boolean[20];

    private ImageButton BackBtn;
    private TextView QuestionTxt;
    private EditText Line1;
    private TextView Line1ems;
    private EditText Line2;
    private TextView Line2ems;
    private EditText Line3;
    private TextView Line3ems;
    private EditText Line4;
    private TextView Line4ems;
    private EditText Line5;
    private TextView Line5ems;
    private EditText Line6;
    private TextView Line6ems;
    private EditText Line7;
    private TextView Line7ems;
    private Button submitBtn;
    private ImageView Correct;
    private ImageView Wrong;

    private ArrayList<Integer> numList = new ArrayList<>();
    private ArrayList<Integer> qList =  new ArrayList<>();
    private ArrayList<Integer> bList = new ArrayList<>();
    private int nowQ = 0;
    private int progress = 0;
    private int correctNum = 0;
    private boolean submited = false;
    private ArrayList<String> lines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three__test);

        Intent intent = getIntent();

        data = (BibleData) intent.getSerializableExtra("data");
        bibleMap = data.getBibleMap();
        verse = data.getVerse();
        is_long = data.getIs_long();

        BackBtn = (ImageButton)findViewById(R.id.backBtn);
        QuestionTxt = (TextView)findViewById(R.id.question);
        Line1 = (EditText)findViewById(R.id.line1) ;
        Line1ems = (TextView)findViewById(R.id.line1ems);
        Line2 = (EditText)findViewById(R.id.line2) ;
        Line2ems = (TextView)findViewById(R.id.line2ems);
        Line3 = (EditText)findViewById(R.id.line3) ;
        Line3ems = (TextView)findViewById(R.id.line3ems);
        Line4 = (EditText)findViewById(R.id.line4) ;
        Line4ems = (TextView)findViewById(R.id.line4ems);
        Line5 = (EditText)findViewById(R.id.line5) ;
        Line5ems = (TextView)findViewById(R.id.line5ems);
        Line6 = (EditText)findViewById(R.id.line6) ;
        Line6ems = (TextView)findViewById(R.id.line6ems);
        Line7 = (EditText)findViewById(R.id.line7) ;
        Line7ems = (TextView)findViewById(R.id.line7ems);
        submitBtn = (Button)findViewById(R.id.submit);
        Correct = (ImageView)findViewById(R.id.correct);
        Wrong = (ImageView)findViewById(R.id.wrong);

        Correct.setVisibility(View.INVISIBLE);
        Wrong.setVisibility(View.INVISIBLE);

        Line1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = Line1.getText().toString();
                int lenTxt = lines.get(1).length() - input.length();
                Line1ems.setText(Integer.toString(lenTxt));
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        Line2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = Line2.getText().toString();
                int lenTxt = lines.get(2).length() - input.length();
                Line2ems.setText(Integer.toString(lenTxt));
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        Line3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = Line3.getText().toString();
                int lenTxt = lines.get(3).length() - input.length();
                Line3ems.setText(Integer.toString(lenTxt));
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        Line4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = Line4.getText().toString();
                int lenTxt = lines.get(4).length() - input.length();
                Line4ems.setText(Integer.toString(lenTxt));
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        Line5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = Line5.getText().toString();
                int lenTxt = lines.get(5).length() - input.length();
                Line5ems.setText(Integer.toString(lenTxt));
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        Line6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = Line6.getText().toString();
                int lenTxt = lines.get(6).length() - input.length();
                Line6ems.setText(Integer.toString(lenTxt));
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        Line7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = Line7.getText().toString();
                int lenTxt = lines.get(7).length() - input.length();
                Line7ems.setText(Integer.toString(lenTxt));
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!submited) {
                    Line1.setEnabled(false);
                    Line2.setEnabled(false);
                    Line3.setEnabled(false);
                    Line4.setEnabled(false);
                    Line5.setEnabled(false);
                    Line6.setEnabled(false);
                    Line7.setEnabled(false);
                    checkAnswer();
                    submited = true;
                }
            }
        });
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMainPopup();
            }
        });

        //랜덤하게 5개를 뽑아 문제리스트 구성
        setNumList();
        qList = randomNum(5);
        //첫 문제 시작
        Test();
    }

    public void checkAnswer(){
        //정답 여부를 확인 할 리스트
        ArrayList<Boolean> corrects = new ArrayList<>();
        corrects.clear();
        boolean correct = false;

        //EditText를 확인하여 각 라인의 정답여부 확인
        for(int i = 1;i<8;i++){
            if(bList.contains(i)){
                corrects.add(checkLine(i));
            }
        }

        if(corrects.contains(false)){ correct = false; }
        else{correct = true;}

        if(correct){
            Correct.setVisibility(View.VISIBLE);
            Correct.bringToFront();
            correctNum++;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                //delayMills이후에 실행할 내용
                public void run() {
                    Correct.setVisibility(View.INVISIBLE);
                    submited = false;
                    Test();
                }
            }, 3500);
        }
        else {
            Wrong.setVisibility(View.VISIBLE);
            Wrong.bringToFront();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Wrong.setVisibility(View.INVISIBLE);
                    submited = false;
                    Test();
                }
            }, 3500);
        }
    }

    public void setdarkbrown(){
        Line1.setTextColor(getResources().getColor(R.color.darkbrown));
        Line2.setTextColor(getResources().getColor(R.color.darkbrown));
        Line3.setTextColor(getResources().getColor(R.color.darkbrown));
        Line4.setTextColor(getResources().getColor(R.color.darkbrown));
        Line5.setTextColor(getResources().getColor(R.color.darkbrown));
        Line6.setTextColor(getResources().getColor(R.color.darkbrown));
        Line7.setTextColor(getResources().getColor(R.color.darkbrown));
    }

    public boolean checkLine(int i){
        boolean correct = false;
        String input = "";
        switch(i){
            case 1:
                input = Line1.getText().toString();
                if(input.equals(lines.get(1))) {
                    correct = true;
                    Line1.setTextColor(getResources().getColor(R.color.green));
                    Line1ems.setText("");
                }
                else{
                    correct = false;
                    Line1.setText(lines.get(1));
                    Line1.setTextColor(getResources().getColor(R.color.hotpink));
                    Line1ems.setText("");
                }
                break;
            case 2:
                input = Line2.getText().toString();
                if(input.equals(lines.get(2))) {
                    correct = true;
                    Line2.setTextColor(getResources().getColor(R.color.green));
                    Line2ems.setText("");
                }
                else{
                    correct = false;
                    Line2.setText(lines.get(2));
                    Line2.setTextColor(getResources().getColor(R.color.hotpink));
                    Line2ems.setText("");
                }
                break;
            case 3:
                input = Line3.getText().toString();
                if(input.equals(lines.get(3))) {
                    correct = true;
                    Line3.setTextColor(getResources().getColor(R.color.green));
                    Line3ems.setText("");
                }
                else{
                    correct = false;
                    Line3.setText(lines.get(3));
                    Line3.setTextColor(getResources().getColor(R.color.hotpink));
                    Line3ems.setText("");
                }
                break;
            case 4:
                input = Line4.getText().toString();
                if(input.equals(lines.get(4))) {
                    correct = true;
                    Line4.setTextColor(getResources().getColor(R.color.green));
                    Line4ems.setText("");
                }
                else{
                    correct = false;
                    Line4.setText(lines.get(4));
                    Line4.setTextColor(getResources().getColor(R.color.hotpink));
                    Line4ems.setText("");
                }
                break;
            case 5:
                input = Line5.getText().toString();
                if(input.equals(lines.get(5))) {
                    correct = true;
                    Line5.setTextColor(getResources().getColor(R.color.green));
                    Line5ems.setText("");
                }
                else{
                    correct = false;
                    Line5.setText(lines.get(5));
                    Line5.setTextColor(getResources().getColor(R.color.hotpink));
                    Line5ems.setText("");
                }
                break;
            case 6:
                input = Line6.getText().toString();
                if(input.equals(lines.get(6))) {
                    correct = true;
                    Line6.setTextColor(getResources().getColor(R.color.green));
                    Line6ems.setText("");
                }
                else{
                    correct = false;
                    Line6.setText(lines.get(6));
                    Line6.setTextColor(getResources().getColor(R.color.hotpink));
                    Line6ems.setText("");
                }
                break;
            case 7:
                input = Line7.getText().toString();
                if(input.equals(lines.get(7))) {
                    correct = true;
                    Line7.setTextColor(getResources().getColor(R.color.green));
                    Line7ems.setText("");
                }
                else{
                    correct = false;
                    Line7.setText(lines.get(7));
                    Line7.setTextColor(getResources().getColor(R.color.hotpink));
                    Line7ems.setText("");
                }
                break;
        }
        return correct;
    }

    public void Test(){
        //액티비티가 종료되지 않았으면
        if(!isFinishing()){
            //진행상황이 5보다 작으면
            if (progress < 5) {
                //현재 문제 번호를 nowQ에 저장
                nowQ = qList.get(progress);
                //문제 출력
                printQuestion();
            }
            //진행상황이 5면(전부 푼 경우)
            else {
                showResultPopup();
                submited = true;
            }
        }
    }

    //문제 출력
    public void printQuestion(){
        progress++;
        //(현재 문제 장절의 라인 수 + 구절 부분 1)를 얻어와서 반을 빈칸으로
        int lineNum = 0;

        for(int i=0;i<bibleMap.get(verse.get(nowQ)).size();i++){
            if(bibleMap.get(verse.get(nowQ)).get(i) != null) {
                lineNum++;
            }
        }
        Log.d("lineNum",Integer.toString(lineNum));

        int blankNum = (lineNum+1)/2;
        Log.d("blankNum",Integer.toString(blankNum));
        int textSize = 18;
        bList.clear();

        //(말씀 + 구절) 중 랜덤으로 빈칸이 될 라인 선택
        while(bList.size() < blankNum){
            int r = (int)(Math.random()* lineNum);
            if(!bList.contains(r+1)){
                bList.add(r+1);
            }
        }

        Log.d("bList",bList.toString());
        //말씀이 길면 18dp, 짧으면 24dp로 글자크기 설정
        if(is_long[nowQ]){ textSize = 18;} else { textSize = 24;}

        lines.clear();
        lines.add("");
        //문제 출력
        int i = 1;
        while(i<8){
            Log.d("i",Integer.toString(i));
            //말씀 출력(bList에 있는 경우 구분해서)
            if(i<lineNum+1){
                lines.add(bibleMap.get(verse.get(nowQ)).get(i-1).replace("\"",""));
                Log.d("lines",lines.toString());
                if(bList.contains(i)){printLine(i, false, lines.get(i), textSize);}
                else {printLine(i, true, lines.get(i),textSize);}
                i++;
            }
            //구절 출력
            else if(i<lineNum+2){
                lines.add(verse.get(nowQ));
                Log.d("line",lines.get(i));
                if(bList.contains(i)){printLine(i, false, lines.get(i),textSize);}
                else {printLine(i, true, lines.get(i),textSize);}
                i++;

            }
            else{
                lines.add("");
                printLine(i, true, null,textSize);
                i++;
            }
        }
        setdarkbrown();
        //문제 번호 출력
        QuestionTxt.setText("Q. "+String.format("%02d", progress)+"/05");
    }

    //lineNum번째 줄에 print여부에 따라 text를 txtSize로 출력
    public void printLine(int lineNum, boolean print, String printText, int txtSize){
        int len = 0;
        switch(lineNum){
            case 1:
                if(print){
                    Line1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(25) });
                    Line1.setText(printText);
                    Line1.setEnabled(false);
                    Line1.getBackground().setColorFilter(getResources().getColor(R.color.transparent), PorterDuff.Mode.SRC_IN);
                    Line1ems.setText(null);
                }
                else{
                    Line1.setText(null);
                    Line1.setEnabled(true);
                    Line1.getBackground().setColorFilter(getResources().getColor(R.color.babybrown), PorterDuff.Mode.SRC_IN);
                    len = printText.length();
                    Line1.setFilters(new InputFilter[] { new InputFilter.LengthFilter(len) });
                    Line1ems.setText(Integer.toString(len));
                }
                Line1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txtSize);
                break;
            case 2:
                if(print){
                    Line2.setFilters(new InputFilter[] { new InputFilter.LengthFilter(25) });
                    Line2.setText(printText);
                    Line2.setEnabled(false);
                    Line2.getBackground().setColorFilter(getResources().getColor(R.color.transparent), PorterDuff.Mode.SRC_IN);
                    Line2ems.setText(null);

                }
                else{
                    Line2.setText(null);
                    Line2.setEnabled(true);
                    Line2.getBackground().setColorFilter(getResources().getColor(R.color.babybrown), PorterDuff.Mode.SRC_IN);
                    len = printText.length();
                    Line2.setFilters(new InputFilter[] { new InputFilter.LengthFilter(len) });
                    Line2ems.setText(Integer.toString(len));
                }
                Line2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txtSize);
                break;
            case 3:
                if(print){
                    Line3.setFilters(new InputFilter[] { new InputFilter.LengthFilter(25) });
                    Line3.setText(printText);
                    Line3.setEnabled(false);
                    Line3.getBackground().setColorFilter(getResources().getColor(R.color.transparent), PorterDuff.Mode.SRC_IN);
                    Line3ems.setText(null);
                }
                else{
                    Line3.setText(null);
                    Line3.setEnabled(true);
                    Line3.getBackground().setColorFilter(getResources().getColor(R.color.babybrown), PorterDuff.Mode.SRC_IN);
                    len = printText.length();
                    Line3.setFilters(new InputFilter[] { new InputFilter.LengthFilter(len) });
                    Line3ems.setText(Integer.toString(len));
                }
                Line3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txtSize);
                break;
            case 4:
                if(print){
                    Line4.setFilters(new InputFilter[] { new InputFilter.LengthFilter(25) });
                    Line4.setText(printText);
                    Line4.setEnabled(false);
                    Line4.getBackground().setColorFilter(getResources().getColor(R.color.transparent), PorterDuff.Mode.SRC_IN);
                    Line4ems.setText(null);
                }
                else{
                    Line4.setText(null);
                    Line4.setEnabled(true);
                    Line4.getBackground().setColorFilter(getResources().getColor(R.color.babybrown), PorterDuff.Mode.SRC_IN);
                    len = printText.length();
                    Line4.setFilters(new InputFilter[] { new InputFilter.LengthFilter(len) });
                    Line4ems.setText(Integer.toString(len));
                }
                Line4.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txtSize);
                break;
            case 5:
                if(printText == null){
                    Line5.setFilters(new InputFilter[] { new InputFilter.LengthFilter(25) });
                    Line5.setText(null);
                    Line5.setEnabled(false);
                    Line5.getBackground().setColorFilter(getResources().getColor(R.color.transparent), PorterDuff.Mode.SRC_IN);
                    Line5ems.setText(null);
                }else {
                    if (print) {
                        Line5.setFilters(new InputFilter[] { new InputFilter.LengthFilter(25) });
                        Line5.setText(printText);
                        Line5.setEnabled(false);
                        Line5.getBackground().setColorFilter(getResources().getColor(R.color.transparent), PorterDuff.Mode.SRC_IN);
                        Line5ems.setText(null);

                    } else {
                        Line5.setText(null);
                        Line5.setEnabled(true);
                        Line5.getBackground().setColorFilter(getResources().getColor(R.color.babybrown), PorterDuff.Mode.SRC_IN);
                        len = printText.length();
                        Line5.setFilters(new InputFilter[] { new InputFilter.LengthFilter(len) });
                        Line5ems.setText(Integer.toString(len));
                    }
                }
                Line5.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txtSize);
                break;
            case 6:
                if(printText == null){
                    Line6.setFilters(new InputFilter[] { new InputFilter.LengthFilter(25) });
                    Line6.setText(null);
                    Line6.setEnabled(false);
                    Line6.getBackground().setColorFilter(getResources().getColor(R.color.transparent), PorterDuff.Mode.SRC_IN);
                    Line6ems.setText(null);
                } else {
                    if (print) {
                        Line6.setFilters(new InputFilter[] { new InputFilter.LengthFilter(25) });
                        Line6.setText(printText);
                        Line6.setEnabled(false);
                        Line6.getBackground().setColorFilter(getResources().getColor(R.color.transparent), PorterDuff.Mode.SRC_IN);
                        Line6ems.setText(null);

                    } else {
                        Line6.setText(null);
                        Line6.setEnabled(true);
                        Line6.getBackground().setColorFilter(getResources().getColor(R.color.babybrown), PorterDuff.Mode.SRC_IN);
                        len = printText.length();
                        Line6.setFilters(new InputFilter[] { new InputFilter.LengthFilter(len) });
                        Line6ems.setText(Integer.toString(len));
                    }
                }
                Line6.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txtSize);
                break;
            case 7:
                if(printText == null){
                    Line7.setFilters(new InputFilter[] { new InputFilter.LengthFilter(25) });
                    Line7.setText(null);
                    Line7.setEnabled(false);
                    Line7.getBackground().setColorFilter(getResources().getColor(R.color.transparent), PorterDuff.Mode.SRC_IN);
                    Line7ems.setText(null);
                } else {
                    if (print) {
                        Line7.setFilters(new InputFilter[] { new InputFilter.LengthFilter(25) });
                        Line7.setText(printText);
                        Line7.setEnabled(false);
                        Line7.getBackground().setColorFilter(getResources().getColor(R.color.transparent), PorterDuff.Mode.SRC_IN);
                        Line7ems.setText(null);

                    } else {
                        Line7.setText(null);
                        Line7.setEnabled(true);
                        Line7.getBackground().setColorFilter(getResources().getColor(R.color.babybrown), PorterDuff.Mode.SRC_IN);
                        len = printText.length();
                        Line7.setFilters(new InputFilter[] { new InputFilter.LengthFilter(len) });
                        Line7ems.setText(Integer.toString(len));
                    }
                }
                Line7.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txtSize);
                break;
        }
    }

    @Override
    public void onBackPressed(){
        showMainPopup();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        Intent intent;
        if(requestCode == 0){
            if(resultCode == 0) {goMain();}
            else if(resultCode == 1) {  }
        }
        else if(requestCode == 1) {
            if(resultCode == 0){goMain();}
        }
    }

    public void showMainPopup(){
        Intent intent = new Intent(this, GoMainPopupActivity.class);
        intent.putExtra("progress",progress);
        intent.putExtra("questionNum",05);
        startActivityForResult(intent, 0);

    }

    public void showResultPopup(){
        Intent intent = new Intent(this, ResultPopupActivity.class);
        intent.putExtra("correctNum",correctNum);
        intent.putExtra("questionNum",05);
        startActivityForResult(intent, 1);
    }

    public void goMain(){
        Intent intent = new Intent(Three_TestActivity.this,MainActivity.class);
        //화면전환시 bibleMap과 verse를 Data를 함께 전달
        intent.putExtra("data", (Serializable) data);
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
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

}
