package com.example.fhl;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;

    public class ViewActivity extends AppCompatActivity {
        private final int _01 = R.id.v01;
        private final int _02 = R.id.v02;
        private final int _03 = R.id.v03;
        private final int _04 = R.id.v04;
        private final int _05 = R.id.v05;
        private final int _06 = R.id.v06;
        private final int _07 = R.id.v07;
        private final int _08 = R.id.v08;
        private final int _09 = R.id.v09;
        private final int _10 = R.id.v10;
        private final int _11 = R.id.v11;
        private final int _12 = R.id.v12;
        private final int _13 = R.id.v13;
        private final int _14 = R.id.v14;
        private final int _15 = R.id.v15;
        private final int _16 = R.id.v16;
        private final int _17 = R.id.v17;
        private final int _18 = R.id.v18;
        private final int _19 = R.id.v19;
        private final int _20 = R.id.v20;

        private HashMap<String, ArrayList<String>> bibleMap = new HashMap<>();
        private ArrayList<String> verse = new ArrayList<>();
        private boolean[] is_long = new boolean[20];
        private boolean[] complete = new boolean[20];
        private PhraseViewPagerAdapter pagerAdapter;
        private TextView PageTxt;
        private ImageButton PreBtn;
        private ImageButton NextBtn;
        private ImageButton BackBtn;
        private ImageButton MenuBtn;
        private ImageButton ComBtn;
        private ViewPager PhraseView;
        private CheckBox JumpCheck;

    private int now = 1;
    private int pre = 1;
    private int preState = 0;
    private boolean end = false;
    private boolean jumpCheck = true;
    private boolean jump = true;
    private int nowMenu = _01;
    private MenuItem nowItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        PageTxt = (TextView)findViewById(R.id.page);
        PreBtn = (ImageButton)findViewById(R.id.preBtn);
        NextBtn = (ImageButton)findViewById(R.id.nextBtn);
        BackBtn = (ImageButton)findViewById(R.id.backBtn);
        MenuBtn = (ImageButton)findViewById(R.id.menuBtn);
        ComBtn = (ImageButton)findViewById(R.id.comBtn);
        PhraseView = (ViewPager)findViewById(R.id.phraseView);
        JumpCheck = (CheckBox)findViewById(R.id.jumpCheck);

        //화면전환시 전달된 Data를 저장
        Intent intent = getIntent();
        bibleMap = (HashMap<String, ArrayList<String>>) intent.getExtras().getSerializable("bibleMap");
        verse = intent.getExtras().getStringArrayList("verse");
        is_long = intent.getBooleanArrayExtra("is_long");
        complete = intent.getBooleanArrayExtra("complete");
        jumpCheck = intent.getBooleanExtra("jumpCheck", true);
        if(jumpCheck){JumpCheck.setChecked(true);}
        else{JumpCheck.setChecked(false);}

        pagerAdapter = new PhraseViewPagerAdapter(this, verse, bibleMap, is_long,complete);
        PhraseView.setAdapter(pagerAdapter);

        setListener();
        PhraseView.setCurrentItem(now-1, true);
        printPage();
    }

    public void setListener(){
        PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                now--;
                jump = jumpCheck;
                if(now == 0){now = 20;}
                if(jump){
                    while(complete[now-1]){
                        now--;
                        if(now == 0){now=20;}
                    }
                }
                PhraseView.setCurrentItem(now-1, true);
                printPage();

            }
        });

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                now++;
                jump = jumpCheck;
                if (now == 21) {now = 1;}
                if(jump) {
                    while (complete[now - 1]) {
                        now++;
                        if (now == 21) {now = 1;}
                    }
                }
                PhraseView.setCurrentItem(now-1, true);
                printPage();
            }
        });

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMain();
            }
        });

        MenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }
        });

        ComBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCompleteBtn();
            }
        });

        PhraseView.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                now = position+1;

                int add = 0;
                printPage();
                if(jump && complete[now-1]) {
                    if (now == 1) {
                        if (pre == 2) { now = 20; }
                        else if (pre == 20) { now = 2; }
                    } else if (now == 20) {
                        if (pre == 1) { now = 19; }
                        else if (pre == 19) { now = 1; }
                    } else {
                        if (pre < now) { now++; }
                        else { now--; }
                    }
                    PhraseView.setCurrentItem(now-1, true);
                    printPage();
                }
                pre = now;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == 1){jump = jumpCheck;}
                if (state == 0 && preState == 1) {
                    int pageCount = pagerAdapter.getCount();
                    if (PhraseView.getCurrentItem() == 0) {
                        PhraseView.setCurrentItem(19, true);

                    } else if (PhraseView.getCurrentItem() == 19) {
                        PhraseView.setCurrentItem(0, true);
                    }
                }
                preState = state;
            }
        });

        JumpCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    jumpCheck = true;
                }
                else{
                    jumpCheck = false;
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        goMain();
    }

    private void showMenu(View button){
        PopupMenu popup = new PopupMenu(this, button);
        popup.getMenuInflater().inflate(R.menu.verse_menu,popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                nowItem = item;
                switch (item.getItemId()){
                    case _01:
                        now = 1; nowMenu = _01;
                        break;
                    case _02:
                        now = 2; nowMenu = _02;
                        break;
                    case _03:
                        now = 3; nowMenu = _03;
                        break;
                    case _04:
                        now = 4; nowMenu = _04;
                        break;
                    case _05:
                        now = 5; nowMenu = _05;
                        break;
                    case _06:
                        now = 6; nowMenu = _06;
                        break;
                    case _07:
                        now = 7; nowMenu = _07;
                        break;
                    case _08:
                        now = 8; nowMenu = _08;
                        break;
                    case _09:
                        now = 9; nowMenu = _09;
                        break;
                    case _10:
                        now = 10; nowMenu = _10;
                        break;
                    case _11:
                        now = 11; nowMenu = _11;
                        break;
                    case _12:
                        now = 12; nowMenu = _12;
                        break;
                    case _13:
                        now = 13; nowMenu = _13;
                        break;
                    case _14:
                        now = 14; nowMenu = _14;
                        break;
                    case _15:
                        now = 15; nowMenu = _15;
                        break;
                    case _16:
                        now = 16; nowMenu = _16;
                        break;
                    case _17:
                        now = 17; nowMenu = _17;
                        break;
                    case _18:
                        now = 18; nowMenu = _18;
                        break;
                    case _19:
                        now = 19; nowMenu = _19;
                        break;
                    case _20:
                        now = 20; nowMenu = _20;
                        break;
                }
                jump = false;

                PhraseView.setCurrentItem(now-1, false);
                return true;
            }
        });
        popup.show();
    }

    private void setCompleteBtn(){
        Drawable happy = getResources().getDrawable(R.drawable.ic_sentiment_very_satisfied_24px);
        Drawable unhappy = getResources().getDrawable(R.drawable.ic_sentiment_dissatisfied_24px);
        int background_yellow = Color.parseColor("#FFE047");
        int background_transparent_yellow = Color.parseColor("#99FFE047");

            if(!complete[now-1]) {
                complete[now-1] = true;
                ComBtn.setBackground(happy);
                ComBtn.getBackground().setColorFilter(background_yellow, PorterDuff.Mode.SRC_IN);
            }
            else{
                complete[now-1] = false;
                ComBtn.setBackground(unhappy);
                ComBtn.getBackground().setColorFilter(background_transparent_yellow, PorterDuff.Mode.SRC_IN);
            }
        }

    private void goMain(){

        //현재 화면과 전환할 화면 설정
        Intent intent = new Intent(ViewActivity.this,MainActivity.class);
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

    private void printPage(){
        Drawable happy = getResources().getDrawable(R.drawable.ic_sentiment_very_satisfied_24px);
        Drawable unhappy = getResources().getDrawable(R.drawable.ic_sentiment_dissatisfied_24px);
        int background_yellow = Color.parseColor("#FFE047");
        int background_transparent_yellow = Color.parseColor("#99FFE047");
        String pageTxt = "";

        pageTxt = now+"/20";
        PageTxt.setText(pageTxt);

        if(complete[now-1]) {
            ComBtn.setBackground(happy);
            ComBtn.getBackground().setColorFilter(background_yellow, PorterDuff.Mode.SRC_IN);
        }
        else{
            ComBtn.setBackground(unhappy);
            ComBtn.getBackground().setColorFilter(background_transparent_yellow, PorterDuff.Mode.SRC_IN);
        }
    }
}
