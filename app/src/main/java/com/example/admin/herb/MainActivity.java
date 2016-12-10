package com.example.admin.herb;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.herb.Utils.Utils;
import com.example.admin.herb.herb.Herb;
import com.example.admin.herb.herb.Herbdatabase;


import java.util.ArrayList;

import static com.example.admin.herb.R.layout.activity_main;
import static com.example.admin.herb.R.layout.activity_main_herb_list;
import static com.example.admin.herb.R.layout.content_main_herb_list;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase mDb;
    Database mHelper;
    Cursor mCursor;
    ArrayList<Herb> dirArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(content_main_herb_list);
        Intent intent = new Intent(this,MainHerbList.class);
        startActivity(intent);




    }

}
