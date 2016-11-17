package com.example.mohammed.refugeecare.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.app.CharityProfile;
import com.example.mohammed.refugeecare.model.CharitiesProfileData;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/19/2016.
 */
public class CharitiesRecyclerAdapter extends RecyclerView.Adapter<CharitiesRecyclerAdapter.CharitiesViewHolder> {
    private Context context;
    private ArrayList<CharitiesProfileData> charitiesData;
    private LayoutInflater mInflater;

    public CharitiesRecyclerAdapter(Context context, ArrayList<CharitiesProfileData> charitiesData) {
        this.context       =  context;
        this.charitiesData =  charitiesData;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CharitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.charities_recycler_item,parent , false);
        CharitiesViewHolder holder = new CharitiesViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(CharitiesViewHolder holder, int position) {
        CharitiesProfileData data = charitiesData.get(position);
        holder.setData(data , position);

    }

    @Override
    public int getItemCount() {
        return charitiesData.size();
    }

    public class CharitiesViewHolder extends RecyclerView.ViewHolder {

        ImageButton btnPic;
        ImageButton btnCall;
        Button btnFollow;
        Button btnDetail;

        TextView charityName;
        TextView charityAddress;
        TextView postContent;


        public CharitiesViewHolder(View itemView) {
            super(itemView);
            btnPic  = (ImageButton) itemView.findViewById(R.id.button_pic_charity_item);
            btnCall = (ImageButton) itemView.findViewById(R.id.button_call_charity_item);
            btnFollow   = (Button) itemView.findViewById(R.id.button_follow_charity_item);
            btnDetail   = (Button) itemView.findViewById(R.id.button_show_more_charity_item);
            charityAddress = (TextView) itemView.findViewById(R.id.text_address_charity_item);

            charityName = (TextView) itemView.findViewById(R.id.text_name_charity_item);
            postContent = (TextView) itemView.findViewById(R.id.text_post_content_charity_item);



        }

        private void setAction(final int position) {




            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "go to charity profile need to set data in intent  ", Toast.LENGTH_LONG).show();    //context , msg , duration
                    Intent i = new Intent(context , CharityProfile.class);

                    i.putExtra("name"   ,charitiesData.get(position).getCharityName());
                    i.putExtra("help"   ,charitiesData.get(position).getCharityTypeOfGive());
                    i.putExtra("address",charitiesData.get(position).getCharityAddress());
                    i.putExtra("notes"  ,charitiesData.get(position).getCharityNotes());
                    i.putExtra("phones" ,charitiesData.get(position).getCharityPhones());
                    i.putExtra("papers" ,charitiesData.get(position).getCharityNeeds());


                    System.out.println("data : "+charitiesData.get(position).getCharityName());
                    System.out.println("data : "+charitiesData.get(position).getCharityTypeOfGive());
                    System.out.println("data : "+charitiesData.get(position).getCharityAddress());
                    System.out.println("data : "+charitiesData.get(position).getCharityNotes());
                    System.out.println("data : "+charitiesData.get(position).getCharityPhones());
                    //dont forget to put data in intent
                    context.startActivity(i);
                }
            });
        }

        public  void setData(CharitiesProfileData charitiesData , int position){


            this.charityName.setText(charitiesData.getCharityName().toString());
            this.charityAddress.setText(charitiesData.getCharityAddress());
            this.postContent.setText(charitiesData.getCharityTypeOfGive());
            setAction(position);
        }
    }

}
