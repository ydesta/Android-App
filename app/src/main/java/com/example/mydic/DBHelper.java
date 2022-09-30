package com.example.mydic;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String DATABASE_NAME = "DICTIONARY_DB.db";
    public static final int DATABASE_VERSION = 1;
    private String DATABASE_LOCATION = "";
    private String DATABASE_FULL_PATH = "";
    private final String TBL_ENG_AMH = "eng_amh";
    private final String TBL_ENG_TGH = "eng_tgn";
    private final String TBL_AMH_TGH = "amh_tgn";
    private final String TBL_BOOKMARK = "bookmark";

    private final String COL_KEY = "key";
    private final String COL_VALUE = "value";
    public SQLiteDatabase mDB;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;

        DATABASE_LOCATION = "data/data/" + mContext.getPackageName() + "/database/";
        DATABASE_FULL_PATH = DATABASE_LOCATION + DATABASE_NAME;
        if (!isExistingDB()) {
            try {
                File dblocation = new File(DATABASE_LOCATION);
                dblocation.mkdirs();
                extractAssetToDatabaseDirectory(DATABASE_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mDB = SQLiteDatabase.openOrCreateDatabase(DATABASE_FULL_PATH, null);

    }

    boolean isExistingDB() {
        File file = new File(DATABASE_FULL_PATH);
        return file.exists();
    }

    public void extractAssetToDatabaseDirectory(String file) throws IOException {
        int length;
        InputStream sourceDatabase = this.mContext.getAssets().open(file);
        File destiontionPath = new File(DATABASE_FULL_PATH);
        OutputStream destion = new FileOutputStream(destiontionPath);
        byte[] buffer = new byte[4096];
        while ((length = sourceDatabase.read(buffer)) > 0) {
            destion.write(buffer, 0, length);
        }
        sourceDatabase.close();
        destion.flush();
        destion.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> getWord(int dicType) {
        String tableName = getTableName(dicType);
        String q = "SELECT * FROM " + tableName;
        Cursor result = mDB.rawQuery(q, null);
        ArrayList<String> source = new ArrayList<>();

        while (result.moveToNext()) {
            source.add(result.getString(result.getColumnIndex(COL_KEY)));
        }
        return source;
    }


    public void addBook(Word word) {
        try {
            String q = "INSERT INTO bookmark([" + COL_KEY + "],[" + COL_VALUE + "]) values(?,?)";
            mDB.execSQL(q, new Object[]{word.key, word.value});
        } catch (SQLException e) {

        }
    }

    public void removeBook(Word word) {
        try {
            String q = "DELETE FROM bookmark WHERE UPPER([" + COL_KEY + "]) = UPPER(?) AND [" + COL_VALUE + "]=?;";
            mDB.execSQL(q, new Object[]{word.key, word.value});
        } catch (SQLException e) {

        }
    }

    public Word getWord(String key, int dicType) {
        String tableName = getTableName(dicType);
        String q = "SELECT * FROM  " + tableName + " where UPPER([key])=UPPER(?)";
        Cursor reCursor = mDB.rawQuery(q, new String[]{key});
        Word word = new Word();

        while (reCursor.moveToNext()) {
            word.key = reCursor.getString(reCursor.getColumnIndex(COL_KEY));
            word.value = reCursor.getString(reCursor.getColumnIndex(COL_VALUE));
        }
        return word;
    }


    public ArrayList<String> getAllWordFromBookmark() {
        String q = "SELECT * FROM bookmark ORDER BY [date] DESC";
        Cursor reCursor = mDB.rawQuery(q, null);
        ArrayList<String> source = new ArrayList<>();

        while (reCursor.moveToNext()) {
            source.add(reCursor.getString(reCursor.getColumnIndex(COL_KEY)));
        }
        return source;
    }

    public String getTableName(int dicType) {
        String tableName = "";
        if (dicType == R.id.action_english) {
            tableName = TBL_ENG_AMH;
        } else if (dicType == R.id.action_amharic) {
            tableName = TBL_ENG_TGH;
        } else if (dicType == R.id.action_tigrigna) {
            tableName = TBL_AMH_TGH;
        }
        return tableName;
    }

    public boolean isWordMark(Word word) {
        String q = "SELECT * FROM bookmark WHERE upper([key])=upper(?) AND ([value])=?";
        Cursor reCursor = mDB.rawQuery(q, new String[]{word.key, word.value});
        return reCursor.getCount() > 0;
    }

    public Word getWordFromBookmark(String key) {
        String q = "SELECT * FROM bookmark WHERE upper([key]) = upper(?)";
        Cursor reCursor = mDB.rawQuery(q, new String[]{key});
        Word word = null;
        while (reCursor.moveToNext()) {
            word = new Word();
            word.key = reCursor.getString(reCursor.getColumnIndex(COL_KEY));
            word.value = reCursor.getString(reCursor.getColumnIndex(COL_VALUE));
        }
        return word;
    }
}
