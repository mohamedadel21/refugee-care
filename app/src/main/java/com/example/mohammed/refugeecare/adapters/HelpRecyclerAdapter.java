package com.example.mohammed.refugeecare.adapters;

import android.content.Context;

import com.example.mohammed.refugeecare.model.FirstPostData;
import com.example.mohammed.refugeecare.model.HelpNormalData;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/16/2016.
 */
public class HelpRecyclerAdapter extends MainRecyclerAdapter {
    public HelpRecyclerAdapter(Context context, ArrayList<HelpNormalData> normaPostData, FirstPostData firstPostData) {
        super(context, normaPostData, firstPostData);
    }

}
