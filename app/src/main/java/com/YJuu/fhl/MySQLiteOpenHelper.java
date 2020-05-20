package com.YJuu.fhl;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private final String TAG = "MySQLiteOpenHelper";

    private static String DB_PATH = "";
    private static String DB_NAME="bible_data.db";

    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
        if(Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else{
            DB_PATH = "/data/data/"+context.getPackageName()+"/databases/";
        }
        this.mContext = context;
    }

    public void createDataBase() throws IOException{
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist){
            this.getReadableDatabase();
            this.close();
            try{
                copyDataBase();
                Log.e(TAG,"createDataBase database created");
            }
            catch (IOException mIOException){
                throw  new Error("ErrorCopingDataBase");
            }
        }
    }

    private boolean checkDataBase(){
        File dbFile = new File(DB_PATH+DB_NAME);
        return dbFile.exists();
    }

    private void copyDataBase() throws IOException{
        InputStream mInput = mContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH+DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;

        while((mLength = mInput.read(mBuffer))>0){
            mOutput.write(mBuffer,0,mLength);
        }

        mOutput.flush();
        mOutput.close();

        mInput.close();
    }

    public boolean openDataBase() throws SQLException{
        String mPath = DB_PATH+DB_NAME;
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }

    public synchronized void close(){
        if(mDataBase != null){mDataBase.close();}
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
