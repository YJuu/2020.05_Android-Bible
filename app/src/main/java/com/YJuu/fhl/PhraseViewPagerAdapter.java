package com.YJuu.fhl;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.HashMap;

public class PhraseViewPagerAdapter extends PagerAdapter {
    private ViewPager mPager = null;
    private int[] mPagerIDsArray;
    private Context mContext = null;
    private ArrayList<String> verse;
    HashMap<String, ArrayList<String>> bibleMap;
    private boolean[] is_long = new boolean[20];
    private boolean[] complete = new boolean[20];


    public PhraseViewPagerAdapter(){

    }

    public PhraseViewPagerAdapter(Context context){
        mContext = context;
    }

    public PhraseViewPagerAdapter(Context context, ArrayList<String> verse, HashMap<String, ArrayList<String>> bibleMap, boolean[] is_long, boolean[] complete){
        mContext = context;
        this.verse = verse;
        this.bibleMap = bibleMap;
        this.is_long = is_long;
        this.complete = complete;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        View view = null;

        if(mContext != null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.page , container, false);

            final int index = position % 20;

            TextView PhraseTxt = (TextView) view.findViewById(R.id.phraseText);
            TextView VerseTxt = (TextView) view.findViewById(R.id.verseText);
            PhraseTxt.setText(setPhraseTxt(index));
            VerseTxt.setText(verse.get(index));

            if(is_long[index]){PhraseTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,17);}
            else{PhraseTxt.setTextSize(TypedValue.COMPLEX_UNIT_DIP,22);}

            PhraseTxt.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    copyPhrase(index);
                    return true;
                }
            });
        }

        container.addView(view);

        return view;
    }

    public void copyPhrase(int num){
        String copyPhrase = "";
        String copyVerse = "";
        String copyTxt = "";
        for(int i=0;i<bibleMap.get(verse.get(num)).size();i++){
            if(bibleMap.get(verse.get(num)).get(i) != null) {
                copyPhrase += bibleMap.get(verse.get(num)).get(i).replace("\"", "") + " " ;
            }
        }
        copyVerse = verse.get(num);
        copyTxt = String.format("[ %s ] %s",copyVerse, copyPhrase);

        ClipboardManager clipboard = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("말씀", copyTxt);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(mContext.getApplicationContext(), "클립보드에 복사완료!", Toast.LENGTH_SHORT).show();
    }


    private String setPhraseTxt(int index){
        //화면에 출력할 텍스트
        String printTxt = "";

        //개행을 추가하며 텍스트 만들기
        for(int i=0;i<bibleMap.get(verse.get(index)).size();i++){
            if(bibleMap.get(verse.get(index)).get(i) != null) {
                printTxt += bibleMap.get(verse.get(index)).get(i) + "\n";
            }
        }

        return printTxt;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (View)object);
    }
}
