package com.example.admin.herb.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.herb.R;
import com.example.admin.herb.Utils.Utils;
import com.example.admin.herb.herb.Herb;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class herbList extends ArrayAdapter<Herb> {
    private Context mcContext;
    private int mLayoutResId;
    private ArrayList<Herb> mHerbList;

    public herbList(Context context, int resource,ArrayList<Herb> HerbList) {
        super(context, resource, HerbList);
        this.mcContext = context;
        this.mLayoutResId = resource;
        this.mHerbList = HerbList;
    }

    public View getView(int position , View convertViewm ,ViewGroup parent){
        View itemLayout = View.inflate(mcContext,mLayoutResId,null);
        ImageView HerbpicView = (ImageView) itemLayout.findViewById(R.id.herb_image_list);
        TextView Herbname = (TextView) itemLayout.findViewById(R.id.herb_name_list);
        Herb  herb = mHerbList.get(position);
        Herbname.setText(herb.THname);

        Drawable drawable = Utils.getDrawableFromAssets(mcContext,herb.Picture);
        HerbpicView.setImageDrawable(drawable);
        return itemLayout;
    }




}
