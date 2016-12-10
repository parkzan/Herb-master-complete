package com.example.admin.herb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 5/12/2559.
 */

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "herbs";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "detailherbs";

    public static final String COL_THNAME = "c_name_th";
    public static final String COL_ENNAME = "c_name_en";
    public static final String COL_ORDINARY = "c_ordinary";
    public static final String COL_NAMESCIENCE = "c_namescience";
    public static final String COL_FAMILY = "c_family";
    public static  final String COL_PICTURE ="c_picture";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME
                +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_THNAME + " TEXT, " + COL_ENNAME + " TEXT, "
                + COL_NAMESCIENCE + " TEXT, "
                + COL_FAMILY + " TEXT, "
                + COL_PICTURE + " TEXT);");
/*, " + COL_ORDER + " TEXT*/
        db.execSQL("INSERT INTO "+ TABLE_NAME +" (" + COL_THNAME + ", " + COL_ENNAME + ", " + COL_NAMESCIENCE + ", " + COL_FAMILY +", "+COL_PICTURE + ") VALUES ('ขิง', 'Ginger', 'Zingiber officinale  Roscoe'" + ", 'ZINGIBERACEAE'" + ", 'ginger.jpg');");
        db.execSQL("INSERT INTO "+ TABLE_NAME +" (" + COL_THNAME + ", " + COL_ENNAME + ", " + COL_NAMESCIENCE + ", " + COL_FAMILY +", "+COL_PICTURE +  ") VALUES ('ไข่เน่า', 'Rottem Egg', 'Vitex glabrata R. Br.'" + ", 'VERBENACEAE' " + ", 'khainao.jpg');");
        db.execSQL("INSERT INTO "+ TABLE_NAME +" (" + COL_THNAME + ", " + COL_ENNAME + ", " + COL_NAMESCIENCE + ", " + COL_FAMILY +", "+COL_PICTURE +  ") VALUES ('รางจืดต้น', 'Jued', 'crotalaria spectabilis Roth.'" + ", 'Fabaceae' " + ", 'langjeai.JPG');");

        }

    public void onUpgrade(SQLiteDatabase db, int oldVersion
            , int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}