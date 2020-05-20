package com.YJuu.fhl;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ScrollView;

public class MenuDialog extends Dialog {

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
    //private MenuListener menuListener;
    private Context context;

    public interface MenuListener{
        void selectMenu(int data);
    }
    
    public MenuDialog(Context context, int now, MenuListener menuListener){
        super(context);
        this.context = context;
        final MenuListener ML = menuListener;
        selected = now;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(R.color.transparent)));
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

        setOrange(selected);

        v01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 1;
                ML.selectMenu(1);
                dismiss();
            }
        });
        v02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 2;
                ML.selectMenu(2);
                dismiss();
            }
        });
        v03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 3;
                ML.selectMenu(3);
                dismiss();
            }
        });
        v04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 4;
                ML.selectMenu(4);
                dismiss();
            }
        });
        v05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 5;
                ML.selectMenu(5);
                dismiss();
            }
        });
        v06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 6;
                ML.selectMenu(6);
                dismiss();
            }
        });
        v07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 7;
                ML.selectMenu(7);
                dismiss();
            }
        });
        v08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 8;
                ML.selectMenu(8);
                dismiss();
            }
        });
        v09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 9;
                ML.selectMenu(9);
                dismiss();
            }
        });
        v10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 10;
                ML.selectMenu(10);
                dismiss();
            }
        });
        v11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 11;
                ML.selectMenu(11);
                dismiss();
            }
        });
        v12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 12;
                ML.selectMenu(12);
                dismiss();
            }
        });
        v13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 13;
                ML.selectMenu(13);
                dismiss();
            }
        });
        v14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 14;
                ML.selectMenu(14);
                dismiss();
            }
        });
        v15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 15;
                ML.selectMenu(15);
                dismiss();
            }
        });
        v16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 16;
                ML.selectMenu(16);
                dismiss();
            }
        });
        v17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 17;
                ML.selectMenu(17);
                dismiss();
            }
        });
        v18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 18;
                ML.selectMenu(18);
                dismiss();
            }
        });
        v19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 19;
                ML.selectMenu(19);
                dismiss();
            }
        });
        v20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBrown(selected);
                selected = 20;
                ML.selectMenu(20);
                dismiss();
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
                v01.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 2:
                v02.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 3:
                v03.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 4:
                v04.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 5:
                v05.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 6:
                v06.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 7:
                v07.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 8:
                v08.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 9:
                v09.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 10:
                v10.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 11:
                v11.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 12:
                v12.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 13:
                v13.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 14:
                v14.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 15:
                v15.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 16:
                v16.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 17:
                v17.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 18:
                v18.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 19:
                v19.setTextColor(context.getResources().getColor(R.color.brown)); break;
            case 20:
                v20.setTextColor(context.getResources().getColor(R.color.brown)); break;

        }
    }

    public void setOrange(int num){
        switch (num){
            case 1:
                v01.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v01); break;
            case 2:
                v02.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v02); break;
            case 3:
                v03.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v03); break;
            case 4:
                v04.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v04); break;
            case 5:
                v05.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v05); break;
            case 6:
                v06.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v06); break;
            case 7:
                v07.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v07); break;
            case 8:
                v08.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v08); break;
            case 9:
                v09.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v09); break;
            case 10:
                v10.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v10); break;
            case 11:
                v11.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v11); break;
            case 12:
                v12.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v12); break;
            case 13:
                v13.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v13); break;
            case 14:
                v14.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v14); break;
            case 15:
                v15.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v15); break;
            case 16:
                v16.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v16); break;
            case 17:
                v17.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v17); break;
            case 18:
                v18.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v18); break;
            case 19:
                v19.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v19); break;
            case 20:
                v20.setTextColor(context.getResources().getColor(R.color.orange)); scrollToButton(v20); break;

        }
    }

    @Override
    public void onBackPressed(){
        cancel();
    }
}
