package com.example.admin.herb.herb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.admin.herb.Database;

import java.util.ArrayList;

/**
 * Created by ParkZan on 12/9/2016.
 */

public class Herbdatabase {
    private static Herbdatabase  mHerbbase;
    private Context mcontext;
    private Database mHelper;
    private SQLiteDatabase mDb;

    private ArrayList<Herb> mHerbs = new ArrayList<>();

    public static Herbdatabase getInstance(Context context) {
        if (mHerbbase == null) {
            mHerbbase = new Herbdatabase(context);
        }
        return mHerbbase;
    }
    public Herbdatabase (Context context){
        this.mcontext = context;
    }
    public void loadFromDatabase(){
        mHerbs.clear();

        mHelper = new Database(mcontext);
        mDb = mHelper.getWritableDatabase();

        Cursor mCursor = mDb.query(Database.TABLE_NAME,null,null,null,null,null,null);
        while(mCursor.moveToNext()){
            String THname  = mCursor.getString(mCursor.getColumnIndex(Database.COL_THNAME));
            String ENname = mCursor.getString(mCursor.getColumnIndex(Database.COL_ENNAME));
            String SCname = mCursor.getString(mCursor.getColumnIndex(Database.COL_NAMESCIENCE));
            String Familyname = mCursor.getString(mCursor.getColumnIndex(Database.COL_FAMILY));
            String picture = mCursor.getString(mCursor.getColumnIndex(Database.COL_PICTURE));
            Herb herb = new Herb(THname,picture,ENname,SCname,Familyname);
           mHerbs.add(herb);
        }
        mCursor.close();
    }

    public ArrayList<Herb> getHerbList(){

        return mHerbs;
    }

}
