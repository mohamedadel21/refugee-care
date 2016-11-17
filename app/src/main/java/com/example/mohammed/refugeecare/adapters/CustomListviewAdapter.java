package com.example.mohammed.refugeecare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.app.MakeCall;

/**
 * Created by Ahmed Khattab 2 on 10/20/2016.
 */
public class CustomListviewAdapter extends BaseAdapter {

    private Context context;
    private String[]phones;
    private LayoutInflater inflater;

    public CustomListviewAdapter(Context c , String[] phones)
    {
        this.phones = phones;
        this.context = c;

         inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return phones.length;
    }

    @Override
    public Object getItem(int position) {
        return phones[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.list_view_item , parent ,false);

        ImageView imgCall = (ImageView) convertView.findViewById(R.id.button_call_profile);


        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MakeCall(context,phones[position] );
            }
        });

        TextView txt = (TextView) convertView.findViewById(R.id.text_phone_list_view);
        txt.setText(phones[position]);


        return convertView;
    }
}
