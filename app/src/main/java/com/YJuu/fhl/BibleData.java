package com.YJuu.fhl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class BibleData implements Serializable {
    //key:장절, value:구절인 HashMap
    private HashMap<String, ArrayList<String>> bibleMap = new HashMap<>();
    //장절을 저장할 ArrayList
    private ArrayList<String> verse = new ArrayList<>();
    private boolean[] complete = new boolean[20];
    private boolean[] is_long = new boolean[20];
    private boolean jumpCheck = true;

    public HashMap<String, ArrayList<String>> getBibleMap() {
        return bibleMap;
    }

    public ArrayList<String> getVerse() {
        return verse;
    }

    public boolean[] getComplete() {
        return complete;
    }

    public boolean[] getIs_long() {
        return is_long;
    }

    public boolean getJumpCheck() {
        return jumpCheck;
    }

    public void setBibleMap(HashMap<String, ArrayList<String>> bibleMap){this.bibleMap = bibleMap;}

    public void setVerse(ArrayList<String> verse){
        this.verse = verse;
    }

    public void setComplete(boolean[] complete){this.complete = complete;}

    public void setIs_long(boolean[] is_long){this.is_long=is_long;}

    public void setJumpCheck(boolean jumpCheck){this.jumpCheck=jumpCheck;}
}
