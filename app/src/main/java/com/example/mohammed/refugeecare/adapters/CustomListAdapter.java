package com.example.mohammed.refugeecare.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.model.JobsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/29/2016.
 */
public class CustomListAdapter extends BaseAdapter{

    Context context;
    ArrayList<JobsModel> data;

    LayoutInflater inflater;

    public CustomListAdapter(Context context , ArrayList<JobsModel> data)
    {
        this.context= context;
        this.data = data;
        inflater = (LayoutInflater) LayoutInflater
                .from(context);


    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public JobsModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {



        if (convertView == null){

            convertView = inflater.inflate(R.layout.list_item , parent,false);
        }


        ImageView picture    = (ImageView) convertView.findViewById(R.id.img_profile_pic);
        TextView  time       = (TextView) convertView.findViewById(R.id.text_time_job_api);
        TextView  companyName= (TextView) convertView.findViewById(R.id.text_company_name_job_api);
        TextView  jobfullpartTime    = (TextView) convertView.findViewById(R.id.text_full_part_time);
        TextView  experience   = (TextView) convertView.findViewById(R.id.text_expreience_job_api);
        TextView  place      = (TextView) convertView.findViewById(R.id.text_job_place_api);
        TextView  jobName      = (TextView) convertView.findViewById(R.id.text_job_name_api);
        Button    applayBtn       = (Button) convertView.findViewById(R.id.button_applay_job_api);

        String strpicture   = data.get(position).getImgSrc();
        String strJobTime   = data.get(position).getJobTime();

        String strtime      = data.get(position).getTime();
        String strjobName       = data.get(position).getJobName();
        String strcompanyName = data.get(position).getCompanyName();

        String strexperience = data.get(position).getExperienc();
        String strplace = data.get(position).getJobPlace();

        final String strUrl = data.get(position).getImgSrc();
        final String jobUrl = data.get(position).getJobink();


        System.out.println("data :"+strpicture+
                strJobTime+
                strtime+
                strjobName  );
        String strjobFullPart =  data.get(position).getJobTime();


        jobName.setText(strjobName);
        jobfullpartTime.setText(strjobFullPart);

        time.setText(strtime       );
        companyName.setText(strcompanyName);
        experience.setText(strexperience);
        place.setText(strplace);
        jobfullpartTime.setText(strJobTime);




        Picasso.with(context).load(strUrl).into(picture);



        applayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(jobUrl));
                context.startActivity(i);

            }
        });


        return convertView;
    }
}
