package com.YJuu.fhl;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SharePopupActivity extends Activity {
    //view안의 요소들과 연결
    private Button kakaoBtn;
    private Button linkBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_popup);

        //view안의 요소들과 연결
        kakaoBtn = (Button)findViewById(R.id.kakaoBtn);
        linkBtn = (Button)findViewById(R.id.linkBtn);
        cancelBtn = (Button)findViewById(R.id.cancelBtn);

        kakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KakaoLink link = new KakaoLink();
                link.downloadLink(SharePopupActivity.this);
                finish();
            }
        });

        linkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("다운로드 링크", "http://www.google.com");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "클립보드에 복사완료!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
