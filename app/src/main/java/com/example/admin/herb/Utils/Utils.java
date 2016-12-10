package com.example.admin.herb.Utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.widget.AdapterView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by ParkZan on 12/5/2016.
 */

public class Utils {

      public static Drawable getDrawableFromAssets(Context context, String pictrueFilename) {
            AssetManager am = context.getAssets();

            try {
                  InputStream stream = am.open(pictrueFilename);
                  Drawable drawable = Drawable.createFromStream(stream, null);
                  return drawable;
            } catch (IOException e) {
                  e.printStackTrace();
                  try {

                        InputStream stream = new FileInputStream(pictrueFilename);
                        Drawable drawable = Drawable.createFromStream(stream, null);
                        return drawable;

                  } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                  }
            }
            return null;
      }
}
