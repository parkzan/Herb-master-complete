package com.example.admin.herb;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.admin.herb.Utils.Utils;
import com.example.admin.herb.adapter.herbList;
import com.example.admin.herb.herb.Herb;
import com.example.admin.herb.herb.Herbdatabase;

import java.util.ArrayList;

public class MainHerbList extends AppCompatActivity {
    private ArrayList<Herb> mHerbList;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_herb_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView HerbListView = (ListView) findViewById(R.id.herb_list_view);
        final Herbdatabase herbdatabase = Herbdatabase.getInstance(this);
        herbdatabase.loadFromDatabase();
        final herbList adapter = new herbList(
                this,
                R.layout.listherb,
                herbdatabase.getHerbList()


        );

      HerbListView.setAdapter(adapter);

       HerbListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Dialog dialog = new Dialog(MainHerbList.this);
                dialog.requestWindowFeature(dialog.getWindow().FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_data);

                Herb herb =herbdatabase.getHerbList().get(i);
                Drawable drawable = Utils.getDrawableFromAssets(MainHerbList.this,herb.Picture);
                ImageView herbImage = (ImageView)dialog.findViewById(R.id.imageView4);
                herbImage.setImageDrawable(drawable);
                TextView textVieTHwname = (TextView)dialog.findViewById(R.id.textTHName);
                textVieTHwname.setText("ชื่อภาษาไทย : "+herb.THname);
                TextView textViewENname = (TextView) dialog.findViewById(R.id.textENName);
                textViewENname.setText("ชื่อสามัญ :"+herb.ENname);
                TextView textnamescience = (TextView)dialog.findViewById(R.id.text_name_science);
                textnamescience.setText("ชื่อวิทยาศาสตร์ : " +herb.SCname);
                TextView textFamily = (TextView)dialog.findViewById(R.id.textFamily);
                textFamily.setText("วงศ์ : " +herb.family);

                Button button = (Button) dialog.findViewById(R.id.buttonOK);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();


            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainHerbList.this,AddHerb.class);
                startActivity(intent);
            }
        });
    }

}
