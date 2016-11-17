package com.example.mohammed.refugeecare.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Ahmed Khattab 2 on 10/20/2016.
 */
public class MakeCall {

    public MakeCall(Context c , String num)
    {

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +num));
        c.startActivity(intent);
    }
}
