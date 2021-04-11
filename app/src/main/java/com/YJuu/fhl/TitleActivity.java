package com.YJuu.fhl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.kakao.util.helper.Utility.getPackageInfo;

//import static com.kakao.util.helper.Utility.getPackageInfo;

public class TitleActivity extends AppCompatActivity {
    //key:장절, value:구절인 HashMap
    private HashMap<String, ArrayList<String>> bibleMap = new HashMap<>();
    //장절을 저장할 ArrayList
    private ArrayList<String> verse = new ArrayList<>();
    private boolean[] complete = new boolean[20];
    private boolean[] is_long = new boolean[20];
    private boolean jumpCheck = true;
    private int last = 1;
    private BibleData data = new BibleData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity_title.xml과 연결
        setContentView(R.layout.activity_title);
        getKeyHash(TitleActivity.this);

        //DB로부터 Data를 읽어와 bibleMap과 verse에 저장하는 함수 호출
        setData();

        data.setBibleMap(bibleMap);
        data.setVerse(verse);
        data.setIs_long(is_long);
        data.setComplete(complete);
        data.setJumpCheck(jumpCheck);
        data.setLast(last);

        Handler hand = new Handler();
        //타이틀 화면 표시 2초후 Main화면으로 넘어가는 함수

        hand.postDelayed(new Runnable(){
            @Override
            public void run(){
                //현재 화면과 전환할 화면 설정
                Intent intent = new Intent(TitleActivity.this,MainActivity.class);
                //화면전환시 Data를 함께 전달
                intent.putExtra("data", (Serializable) data);

                //화면전환
                startActivity(intent);
                //현재화면은 종료
                finish();
            }
        }, 2000);

    }

    //txt파일로부터 Data를 읽어와 bibleMap에 저장
    private void setData(){
        try{
            bibleMap = readFromDB();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private HashMap<String, ArrayList<String>> readFromDB() throws Exception{
        MyDBHandler mDbOpenHelper = new MyDBHandler(getApplicationContext());
        mDbOpenHelper.createDataBase();
        mDbOpenHelper.open();
        HashMap<String, ArrayList<String>> bibledata = new HashMap<>();

        Cursor bible_Cursor = mDbOpenHelper.select_bible();
        Cursor settings_Cursor = mDbOpenHelper.select_settings();
        settings_Cursor.moveToNext();

        if(settings_Cursor.getInt(settings_Cursor.getColumnIndex("jump_check")) == 0){jumpCheck = true;}
        else {jumpCheck = false;}
        last = settings_Cursor.getInt(settings_Cursor.getColumnIndex("last"));

        while(bible_Cursor.moveToNext()){
            int tempIndex;
            String tempVerse;
            ArrayList<String> tempPhrase = new ArrayList<>();
            String tempIs_long;
            String tempComplete;

            tempIndex = bible_Cursor.getPosition();
            tempVerse = bible_Cursor.getString(bible_Cursor.getColumnIndex("verse"));
            tempIs_long = bible_Cursor.getString(bible_Cursor.getColumnIndex("is_long"));
            tempComplete = bible_Cursor.getString(bible_Cursor.getColumnIndex("complete"));
            tempPhrase.add(bible_Cursor.getString(bible_Cursor.getColumnIndex("phrase01")));
            tempPhrase.add(bible_Cursor.getString(bible_Cursor.getColumnIndex("phrase02")));
            tempPhrase.add(bible_Cursor.getString(bible_Cursor.getColumnIndex("phrase03")));
            tempPhrase.add(bible_Cursor.getString(bible_Cursor.getColumnIndex("phrase04")));
            tempPhrase.add(bible_Cursor.getString(bible_Cursor.getColumnIndex("phrase05")));
            tempPhrase.add(bible_Cursor.getString(bible_Cursor.getColumnIndex("phrase06")));

            verse.add(tempVerse);
            bibledata.put(tempVerse,tempPhrase);
            putBoolean(tempIndex,tempIs_long, is_long);
            putBoolean(tempIndex,tempComplete, complete);
        }

        mDbOpenHelper.close();
        return bibledata;
    }

    private void putBoolean(int index, String is_long, boolean[] temp){
        boolean c;
        if(index>= 0 && index < temp.length){
            if(is_long.equals("T")){c = true;}
            else{c = false;}
            temp[index] = c;
        }
    }

    public static String getKeyHash(final Context context) {
        PackageInfo packageInfo = getPackageInfo(context, PackageManager.GET_SIGNATURES);
        if (packageInfo == null)
            return null;

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String keyHash = new String(Base64.encode(md.digest(), 0));

                Log.d("keyHash", keyHash);
                return Base64.encodeToString(md.digest(), Base64.NO_WRAP);
            } catch (NoSuchAlgorithmException e) {
                String TAG = "KeyHash";
                Log.w(TAG, "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
        return null;
    }
}
