package com.YJuu.fhl;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.Serializable;
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

    private BibleData data;
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
    private boolean jumpCheck = true;
    private boolean jump = true;
    private int nowItem = _01;
    private int nowMenu = _01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        PageTxt = (TextView) findViewById(R.id.page);
        PreBtn = (ImageButton) findViewById(R.id.preBtn);
        NextBtn = (ImageButton) findViewById(R.id.nextBtn);
        BackBtn = (ImageButton) findViewById(R.id.backBtn);
        MenuBtn = (ImageButton) findViewById(R.id.menuBtn);
        ComBtn = (ImageButton) findViewById(R.id.comBtn);
        PhraseView = (ViewPager) findViewById(R.id.phraseView);
        JumpCheck = (CheckBox) findViewById(R.id.jumpCheck);

        //화면전환시 전달된 Data를 저장
        Intent intent = getIntent();
        data = (BibleData) intent.getSerializableExtra("data");
        bibleMap = data.getBibleMap();
        verse = data.getVerse();
        is_long = data.getIs_long();
        complete = data.getComplete();
        jumpCheck = data.getJumpCheck();

        if (jumpCheck) {
            JumpCheck.setChecked(true);
        } else {
            JumpCheck.setChecked(false);
        }

        pagerAdapter = new PhraseViewPagerAdapter(this, verse, bibleMap, is_long, complete);
        PhraseView.setAdapter(pagerAdapter);

        setListener();
        PhraseView.setCurrentItem(now - 1, true);
        printPage();
    }

    public void setListener() {
        PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                now--;
                jump = jumpCheck;
                if (now == 0) {
                    now = 20;
                }
                if (jump) {
                    while (complete[now - 1]) {
                        now--;
                        if (now == 0) {
                            now = 20;
                        }
                    }
                }
                PhraseView.setCurrentItem(now - 1, true);
                printPage();

            }
        });

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                now++;
                jump = jumpCheck;
                if (now == 21) {
                    now = 1;
                }
                if (jump) {
                    while (complete[now - 1]) {
                        now++;
                        if (now == 21) {
                            now = 1;
                        }
                    }
                }
                PhraseView.setCurrentItem(now - 1, true);
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
                now = position + 1;

                int add = 0;
                printPage();
                if (jump && complete[now - 1]) {
                    if (now == 1) {
                        if (pre == 2) {
                            now = 20;
                        } else if (pre == 20) {
                            now = 2;
                        }
                    } else if (now == 20) {
                        if (pre == 1) {
                            now = 19;
                        } else if (pre == 19) {
                            now = 1;
                        }
                    } else {
                        if (pre < now) {
                            now++;
                        } else {
                            now--;
                        }
                    }
                    PhraseView.setCurrentItem(now - 1, true);
                    printPage();
                }
                pre = now;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 1) {
                    jump = jumpCheck;
                }
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
                if (((CheckBox) v).isChecked()) {
                    jumpCheck = true;
                } else {
                    jumpCheck = false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        goMain();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        if (requestCode == 1) {
            switch (resultCode) {
                case 0:
                    break;
                case 1:
                    now = 1;
                    break;
                case 2:
                    now = 2;
                    break;
                case 3:
                    now = 3;
                    break;
                case 4:
                    now = 4;
                    break;
                case 5:
                    now = 5;
                    break;
                case 6:
                    now = 6;
                    break;
                case 7:
                    now = 7;
                    break;
                case 8:
                    now = 8;
                    break;
                case 9:
                    now = 9;
                    break;
                case 10:
                    now = 10;
                    break;
                case 11:
                    now = 11;
                    break;
                case 12:
                    now = 12;
                    break;
                case 13:
                    now = 13;
                    break;
                case 14:
                    now = 14;
                    break;
                case 15:
                    now = 15;
                    break;
                case 16:
                    now = 16;
                    break;
                case 17:
                    now = 17;
                    break;
                case 18:
                    now = 18;
                    break;
                case 19:
                    now = 19;
                    break;
                case 20:
                    now = 20;
                    break;
            }
            jump = false;
            PhraseView.setCurrentItem(now - 1, false);
            Log.d("resultcode",Integer.toString(resultCode));
        }
    }

    private void showMenu(View button) {
        Intent intent = new Intent(this, VerseMenuPopupActivity.class);
        intent.putExtra("now",now);
        startActivityForResult(intent, 1);
    }

    private void setCompleteBtn() {
        Drawable happy = getResources().getDrawable(R.drawable.ic_sentiment_very_satisfied_24px);
        Drawable unhappy = getResources().getDrawable(R.drawable.ic_sentiment_dissatisfied_24px);
        int background_yellow = Color.parseColor("#FFE047");
        int background_transparent_yellow = Color.parseColor("#99FFE047");

        if (!complete[now - 1]) {
            complete[now - 1] = true;
            ComBtn.setBackground(happy);
            ComBtn.getBackground().setColorFilter(background_yellow, PorterDuff.Mode.SRC_IN);
        } else {
            complete[now - 1] = false;
            ComBtn.setBackground(unhappy);
            ComBtn.getBackground().setColorFilter(background_transparent_yellow, PorterDuff.Mode.SRC_IN);
        }
    }

    private void goMain() {

        data.setBibleMap(bibleMap);
        data.setVerse(verse);
        data.setIs_long(is_long);
        data.setComplete(complete);
        data.setJumpCheck(jumpCheck);


        //현재 화면과 전환할 화면 설정
        Intent intent = new Intent(ViewActivity.this, MainActivity.class);
        //화면전환시 bibleMap과 verse를 Data를 함께 전달
        intent.putExtra("data", (Serializable) data);
        intent.addFlags (Intent.FLAG_ACTIVITY_NO_ANIMATION);
        //화면전환
        startActivity(intent);
        finish();
    }

    private void printPage() {
        Drawable happy = getResources().getDrawable(R.drawable.ic_sentiment_very_satisfied_24px);
        Drawable unhappy = getResources().getDrawable(R.drawable.ic_sentiment_dissatisfied_24px);
        int background_yellow = Color.parseColor("#FFE047");
        int background_transparent_yellow = Color.parseColor("#99FFE047");
        String pageTxt = "";

        pageTxt = now + "/20";
        PageTxt.setText(pageTxt);

        if (complete[now - 1]) {
            ComBtn.setBackground(happy);
            ComBtn.getBackground().setColorFilter(background_yellow, PorterDuff.Mode.SRC_IN);
        } else {
            ComBtn.setBackground(unhappy);
            ComBtn.getBackground().setColorFilter(background_transparent_yellow, PorterDuff.Mode.SRC_IN);
        }
    }
}
