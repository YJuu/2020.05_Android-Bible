package com.YJuu.fhl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

public class MyDBHandler {
    private final String TAG = "MyDBHandler";

    private final Context mContext;
    private SQLiteDatabase mDB = null;
    private MySQLiteOpenHelper mHelper = null;

    public MyDBHandler(Context context){
        this.mContext = context;
        mHelper = new MySQLiteOpenHelper(context);
    }

public MyDBHandler createDataBase() throws SQLException{
        try{
            mHelper.createDataBase();
        }
        catch(IOException mIOException){
            Log.e(TAG, mIOException.toString()+" UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
}

public MyDBHandler open() throws SQLException{
        try{
            mHelper.openDataBase();
            mHelper.close();
            mDB = mHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException){
            Log.e(TAG,"open >>"+mSQLException.toString());
            throw mSQLException;
        }
        return this;
}

    public Cursor select_bible(){
        mDB = mHelper.getReadableDatabase();
        Cursor c = mDB.query("bibleTable", null, null, null, null, null, null);
        return c;
    }

    public Cursor select_settings(){
        mDB = mHelper.getReadableDatabase();
        Cursor c = mDB.query("settings", null, null, null, null, null, null);
        return c;
    }

/*
    public void insert(String verse, String phrase, String is_long, String complete){
        Log.d(TAG,"insert");
        mDB = mHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("verse", verse);
        value.put("phrase", phrase);
        value.put("is_long", is_long);
        value.put("complete", complete);

        mDB.insert("bible",null,value);
    }
*/
    public void update_bible(String verse, String complete){
        Log.d(TAG,"update");
        mDB = mHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("verse", verse);
        value.put("complete", complete);
        mDB.update("bibleTable",value, "verse=?",new String[]{verse});
    }

    public void update_settings(int jumpCheck){
        Log.d(TAG,"update");
        mDB = mHelper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("jump_check", jumpCheck);
        mDB.update("settings",value, "_id=1",null);
    }

    public void delete(String verse){
        Log.d(TAG,"delete");
        mDB = mHelper.getWritableDatabase();
        mDB.delete("bible", "verse=?",new String[]{verse});
    }

    public void close(){
        mHelper.close();
    }
}
