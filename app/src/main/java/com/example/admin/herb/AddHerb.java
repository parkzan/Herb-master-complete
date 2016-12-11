package com.example.admin.herb;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.hardware.Camera;
import com.bumptech.glide.Glide;
import com.kbeanie.imagechooser.api.ChooserType;
import com.kbeanie.imagechooser.api.ChosenImage;
import com.kbeanie.imagechooser.api.ChosenImages;
import com.kbeanie.imagechooser.api.ImageChooserListener;
import com.kbeanie.imagechooser.api.ImageChooserManager;
import com.kbeanie.imagechooser.exceptions.ChooserException;

public class AddHerb extends AppCompatActivity  implements ImageChooserListener {
    EditText THname;
    EditText ENname;
    EditText SCname;
    EditText familyname;
    ImageView picture;
    SQLiteDatabase db;
    Database database;

    private ImageChooserManager Imagechoose;
    private String Image_addcamera;
    boolean checkpicture ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_herb);

        ImageView imageView_add = (ImageView)findViewById(R.id.imageView_add);
        imageView_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  String [] choose ={"เลือกภาพจากอัลบั้ม", "ถ่ายภาพ"};

                new AlertDialog.Builder(AddHerb.this)
                        .setItems(choose, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (i){
                                    case 0:
                                        chooseImge();
                                        checkpicture = true;
                                        break;
                                    case 1:
                                        cameraPictrue();
                                        checkpicture = true;
                                        break;

                                }


                            }
                        })
                        .show();
            }
        });

         THname = (EditText)findViewById(R.id.editText3_thname);
         ENname = (EditText)findViewById(R.id.editText7_enname);
         SCname = (EditText)findViewById(R.id.editText8_sc);
         familyname = (EditText)findViewById(R.id.editText9_family);
        picture = (ImageView)findViewById(R.id.imageView_add);
        Button buttonAdd = (Button)findViewById(R.id.button_add);
        final Intent refresh = new Intent(this,MainHerbList.class);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namethai = THname.getText().toString();
                String nameeng = ENname.getText().toString();
                String namesc = SCname.getText().toString();
                String namefamily = familyname.getText().toString();


                AlertDialog.Builder dialog = new AlertDialog.Builder(AddHerb.this);
                database = new Database(AddHerb.this);
                db = database.getWritableDatabase();
                if(namethai.length()>0&&nameeng.length()>0&&namesc.length()>0&&namefamily.length()>0/*&&Image_addcamera.length()>0*/) {
                    db.execSQL("INSERT INTO " + Database.TABLE_NAME + " ("
                            + Database.COL_THNAME + ", " + Database.COL_ENNAME
                            + ", " + Database.COL_NAMESCIENCE + ", " + Database.COL_FAMILY + ", " + Database.COL_PICTURE + ") VALUES ('" + namethai
                            + "', '" + nameeng + "', '" + namesc + "', '" + namefamily + "', '" + Image_addcamera + "');");
                    startActivity(refresh);
                    finish();
                }
                else {
                    dialog.setTitle("ผิดพลาด");
                    dialog.setMessage("กรุณาป้อนให้ครบ");
                    dialog.setIcon(R.mipmap.ic_launcher);
                    dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.show();
                }


            }
        });



    }

    @Override
    public void onImageChosen(final ChosenImage chosenImage) {
        new Handler(Looper.getMainLooper()).post(
                new Runnable() {
                    @Override
                    public void run() {
                        if(chosenImage !=null){
                            Image_addcamera = chosenImage.getFilePathOriginal();
                            Glide.with(AddHerb.this).load(Image_addcamera).into(picture);
                        }else {

                        }
                    }
                }

        );
    }
    protected  void onActivityResult(int requestCode,int resultCode,Intent returnpage){
        super.onActivityResult(requestCode,resultCode,returnpage);

        if(resultCode == RESULT_OK
                && (requestCode == ChooserType.REQUEST_PICK_PICTURE
                || requestCode == ChooserType.REQUEST_CAPTURE_PICTURE)){
            if(Imagechoose == null){
                return;
            }
            Imagechoose.submit(requestCode, returnpage);
        }
    }
    private void chooseImge(){
        Bundle bundle = new Bundle();
        bundle.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, false);
        Imagechoose = new ImageChooserManager(this, ChooserType.REQUEST_PICK_PICTURE,true);
        Imagechoose.setExtras(bundle);
        Imagechoose.setImageChooserListener(this);
        Imagechoose.clearOldFiles();

        try{
            Imagechoose.choose();
        }catch (ChooserException e){
            e.printStackTrace();
        }
    }
    private void cameraPictrue(){
        Imagechoose = new ImageChooserManager(this,ChooserType.REQUEST_CAPTURE_PICTURE,true);
        Imagechoose.setImageChooserListener(this);
        try{
            Imagechoose.choose();
        }catch (ChooserException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onError(String s) {

    }

    @Override
    public void onImagesChosen(ChosenImages chosenImages) {

    }
}
