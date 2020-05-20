package com.YJuu.fhl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class VerseMenuPopupActivity extends Activity {

    private Button v01;
    private Button v02;
    private Button v03;
    private Button v04;
    private Button v05;
    private Button v06;
    private Button v07;
    private Button v08;
    private Button v09;
    private Button v10;
    private Button v11;
    private Button v12;
    private Button v13;
    private Button v14;
    private Button v15;
    private Button v16;
    private Button v17;
    private Button v18;
    private Button v19;
    private Button v20;
    private ScrollView menu;

    private int selected = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_menu);

        v01 = (Button)findViewById(R.id.v01);
        v02 = (Button)findViewById(R.id.v02);
        v03 = (Button)findViewById(R.id.v03);
        v04 = (Button)findViewById(R.id.v04);
        v05 = (Button)findViewById(R.id.v05);
        v06 = (Button)findViewById(R.id.v06);
        v07 = (Button)findViewById(R.id.v07);
        v08 = (Button)findViewById(R.id.v08);
        v09 = (Button)findViewById(R.id.v09);
        v10 = (Button)findViewById(R.id.v10);
        v11 = (Button)findViewById(R.id.v11);
        v12 = (Button)findViewById(R.id.v12);
        v13 = (Button)findViewById(R.id.v13);
        v14 = (Button)findViewById(R.id.v14);
        v15 = (Button)findViewById(R.id.v15);
        v16 = (Button)findViewById(R.id.v16);
        v17 = (Button)findViewById(R.id.v17);
        v18 = (Button)findViewById(R.id.v18);
        v19 = (Button)findViewById(R.id.v19);
        v20 = (Button)findViewById(R.id.v20);
        menu = (ScrollView)findViewById(R.id.MenuScroll);

        Intent intent = getIntent();
        selected = intent.getIntExtra("now",1);
        setOrange(selected);

        v01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }
        });
        v02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 2;
                Intent intent = new Intent();
                setResult(2, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 3;
                Intent intent = new Intent();
                setResult(3, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 4;
                Intent intent = new Intent();
                setResult(4, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 5;
                Intent intent = new Intent();
                setResult(5, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 6;
                Intent intent = new Intent();
                setResult(6, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 7;
                Intent intent = new Intent();
                setResult(7, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 8;
                Intent intent = new Intent();
                setResult(8, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 9;
                Intent intent = new Intent();
                setResult(9, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 10;
                Intent intent = new Intent();
                setResult(10, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 11;
                Intent intent = new Intent();
                setResult(11, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 12;
                Intent intent = new Intent();
                setResult(12, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 13;
                Intent intent = new Intent();
                setResult(13, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 14;
                Intent intent = new Intent();
                setResult(14, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 15;
                Intent intent = new Intent();
                setResult(15, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 16;
                Intent intent = new Intent();
                setResult(16, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 17;
                Intent intent = new Intent();
                setResult(17, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 18;
                Intent intent = new Intent();
                setResult(18, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 19;
                Intent intent = new Intent();
                setResult(19, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
        v20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 20;
                Intent intent = new Intent();
                setResult(20, intent);
                overridePendingTransition(0,0);
                finish();
            }
        });
    }

    public void scrollToButton(View v){
        final View view = v;
        menu.post(new Runnable() {
            public void run() {
                int positionY = view.getTop();
                menu.scrollTo(0, positionY);
            }
        });
    }

    public void setBrown(int num){
        switch (num){
            case 1:
                v01.setTextColor(getResources().getColor(R.color.brown)); break;
            case 2:
                v02.setTextColor(getResources().getColor(R.color.brown)); break;
            case 3:
                v03.setTextColor(getResources().getColor(R.color.brown)); break;
            case 4:
                v04.setTextColor(getResources().getColor(R.color.brown)); break;
            case 5:
                v05.setTextColor(getResources().getColor(R.color.brown)); break;
            case 6:
                v06.setTextColor(getResources().getColor(R.color.brown)); break;
            case 7:
                v07.setTextColor(getResources().getColor(R.color.brown)); break;
            case 8:
                v08.setTextColor(getResources().getColor(R.color.brown)); break;
            case 9:
                v09.setTextColor(getResources().getColor(R.color.brown)); break;
            case 10:
                v10.setTextColor(getResources().getColor(R.color.brown)); break;
            case 11:
                v11.setTextColor(getResources().getColor(R.color.brown)); break;
            case 12:
                v12.setTextColor(getResources().getColor(R.color.brown)); break;
            case 13:
                v13.setTextColor(getResources().getColor(R.color.brown)); break;
            case 14:
                v14.setTextColor(getResources().getColor(R.color.brown)); break;
            case 15:
                v15.setTextColor(getResources().getColor(R.color.brown)); break;
            case 16:
                v16.setTextColor(getResources().getColor(R.color.brown)); break;
            case 17:
                v17.setTextColor(getResources().getColor(R.color.brown)); break;
            case 18:
                v18.setTextColor(getResources().getColor(R.color.brown)); break;
            case 19:
                v19.setTextColor(getResources().getColor(R.color.brown)); break;
            case 20:
                v20.setTextColor(getResources().getColor(R.color.brown)); break;

        }
    }

    public void setOrange(int num){
        switch (num){
            case 1:
                v01.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v01); break;
            case 2:
                v02.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v02); break;
            case 3:
                v03.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v03); break;
            case 4:
                v04.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v04); break;
            case 5:
                v05.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v05); break;
            case 6:
                v06.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v06); break;
            case 7:
                v07.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v07); break;
            case 8:
                v08.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v08); break;
            case 9:
                v09.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v09); break;
            case 10:
                v10.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v10); break;
            case 11:
                v11.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v11); break;
            case 12:
                v12.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v12); break;
            case 13:
                v13.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v13); break;
            case 14:
                v14.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v14); break;
            case 15:
                v15.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v15); break;
            case 16:
                v16.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v16); break;
            case 17:
                v17.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v17); break;
            case 18:
                v18.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v18); break;
            case 19:
                v19.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v19); break;
            case 20:
                v20.setTextColor(getResources().getColor(R.color.orange)); scrollToButton(v20); break;

        }
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        setResult(0, intent);
        overridePendingTransition(0,0);
        finish();
    }
}
