package com.example.mohammed.refugeecare.adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.model.FirstPostData;
import com.example.mohammed.refugeecare.model.JobData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/16/2016.
 */
public class JopRecyclerAdapter extends RecyclerView.Adapter<JopRecyclerAdapter.MyViewHolderJobs>{

    private Context context;
    private ArrayList<JobData> jobData;
    private LayoutInflater mInflater;


    public JopRecyclerAdapter(Context context, ArrayList<JobData> jobData)  {
        this.context = context;
        this.jobData = jobData;
        this.mInflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolderJobs onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

                view   = mInflater.inflate(R.layout.job_recycler_item, parent , false);
                return new NormalJobItemViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolderJobs holder, int position) {


            NormalJobItemViewHolder normalholder = (NormalJobItemViewHolder) holder;
            normalholder.setData(context, jobData, position);

    }


    @Override
    public int getItemCount() {
        return jobData.size();
    }

    public class MyViewHolderJobs extends RecyclerView.ViewHolder
    {
        public MyViewHolderJobs(View itemView) {
            super(itemView);
        }


    }


    public class NormalJobItemViewHolder extends MyViewHolderJobs
    {
        TextView JobTitle;
        TextView company;
        TextView location;
        TextView date;
        TextView link;
        ImageButton imgUser;



        public NormalJobItemViewHolder(View itemView) {
            super(itemView);
            JobTitle = (TextView) itemView.findViewById(R.id.text_job_title);
            company = (TextView) itemView.findViewById(R.id.text_job_company);
            location = (TextView) itemView.findViewById(R.id.text_job_location);
            date = (TextView) itemView.findViewById(R.id.text_job_date);
            link = (TextView) itemView.findViewById(R.id.tetx_job_url);
            imgUser = (ImageButton) itemView.findViewById(R.id.img_user_profile_pic);

        }


        public void setData(final Context context, ArrayList<JobData> currentObj, int position) {

           this.JobTitle.setText(currentObj.get(position).getTextJobTitle());
           this.company.setText(currentObj.get(position).getTextJobCompany());
           this.location.setText(currentObj.get(position).getTextJobLocation());
           this.date.setText(currentObj.get(position).getPosttime());
           this.link.setText(currentObj.get(position).getTextJobLink());
           Picasso.with(context)
                   .load("http://downloadicons.net/sites/default/files/user-icon-2722.png")
                   .into(this.imgUser);

        }


    }

    @Override
    public int getItemViewType(int position) {
        return  position;
    }
}
