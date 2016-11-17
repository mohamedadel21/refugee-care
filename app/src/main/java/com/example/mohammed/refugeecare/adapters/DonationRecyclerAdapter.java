package com.example.mohammed.refugeecare.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.model.DonationNormalData;
import com.example.mohammed.refugeecare.model.FirstPostData;
import com.example.mohammed.refugeecare.model.HelpNormalData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/16/2016.
 */
public class DonationRecyclerAdapter  extends RecyclerView.Adapter<DonationRecyclerAdapter.MyViewHolder>{

    private ArrayList<DonationNormalData> donationData;
    private LayoutInflater mInflater;
    FirstPostData firstPostData;
    Context context;

    public DonationRecyclerAdapter(Context context, ArrayList<DonationNormalData> normaPostData, FirstPostData firstPostData ,int x) {
        donationData = normaPostData;
        this.mInflater = LayoutInflater.from(context);
        this.firstPostData = firstPostData;
        this.context = context;

    }

    public class NormalDonationElementHolder extends DonationRecyclerAdapter.MyViewHolder
    {
        ImageView userImg;
        ImageView postImg;

        TextView userNameText;
        TextView timeText;
        TextView contentOfPost;
        TextView linksText;

        public NormalDonationElementHolder(View itemView) {
            super(itemView , DonationRecyclerAdapter.this.context);
            userImg = (ImageView) itemView.findViewById(R.id.img_profile_pic);
            postImg = (ImageView) itemView.findViewById(R.id.image_postimg);
            userNameText = (TextView) itemView.findViewById(R.id.text_user_name);
            timeText = (TextView) itemView.findViewById(R.id.text_time_stamp);
            contentOfPost = (TextView) itemView.findViewById(R.id.text_status);
            linksText = (TextView) itemView.findViewById(R.id.text_url);

        }

        @Override
        public void setData(Context context, DonationNormalData currentObj, int position) {

            this.userNameText.setText(currentObj.getUserName());
            this.timeText.setText(currentObj.getTime());
            this.contentOfPost.setText(currentObj.getPostText());
            this.linksText.setText(currentObj.getPostHrefLink());



            if(currentObj.getPostImgsrc().isEmpty()){
                postImg.setVisibility(View.GONE);
            }
            else {
                Picasso.with(context).load(currentObj.getPostImgsrc()).into(postImg);
            }
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;


        Log.d("data to know : ","onCreateViewHolder method is called");
        view   = mInflater.inflate(R.layout.recycler_item_post, parent , false);
        return  new NormalElementHolder(view);



    }


    public class NormalElementHolder extends MyViewHolder
    {
        ImageView userImg;
        ImageView postImg;

        TextView userNameText;
        TextView timeText;
        TextView contentOfPost;
        TextView linksText;

        public NormalElementHolder(View itemView) {
            super(itemView , DonationRecyclerAdapter.this.context);
            userImg = (ImageView) itemView.findViewById(R.id.img_profile_pic);
            postImg = (ImageView) itemView.findViewById(R.id.image_postimg);
            userNameText = (TextView) itemView.findViewById(R.id.text_user_name);
            timeText = (TextView) itemView.findViewById(R.id.text_time_stamp);
            contentOfPost = (TextView) itemView.findViewById(R.id.text_status);
            linksText = (TextView) itemView.findViewById(R.id.text_url);

        }
        @Override
        public void setData(Context context, DonationNormalData currentObj, int position) {

            this.userNameText.setText(currentObj.getUserName());
            this.timeText.setText(currentObj.getTime());
            this.contentOfPost.setText(currentObj.getPostText());
            this.linksText.setText(currentObj.getPostHrefLink());



            if(currentObj.getPostImgsrc().isEmpty()){
                postImg.setVisibility(View.GONE);
            }
            else {
                Picasso.with(context).load(currentObj.getPostImgsrc()).into(postImg);
            }
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        Context context;

        public MyViewHolder(View itemView, Context c) {
            super(itemView);
            this.context = c;
        }
        public void setData(Context context, HelpNormalData currentObj, int position) {

        }
        public void setData(Context context, DonationNormalData currentObj, int position) {

        }




    }

    @Override
    public int getItemCount() {

            return donationData.size();

    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {


        Log.d("data to know : ","onBindViewHolder method is called , "+position);

            MyViewHolder holder =  myViewHolder;
            DonationNormalData currentObj = donationData.get(position);
            holder.setData(this.context,currentObj , position);





    }


}
