package com.example.mohammed.refugeecare.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.adapters.JopRecyclerAdapter;
import com.example.mohammed.refugeecare.app.AddJob;
import com.example.mohammed.refugeecare.model.FirstPostData;
import com.example.mohammed.refugeecare.model.JobData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/13/2016.
 */
public class JopFragment3 extends Fragment {

    FloatingActionButton fab ;
    ArrayList<JobData> allJobData;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three_jobs, container ,false);


   /*     if(savedInstanceState != null) {
            allJobData = (ArrayList<JobData>) savedInstanceState.getSerializable("listjob");
        }
        else{
      */      allJobData = new JobData().GetJobData();

        if(allJobData.isEmpty())
        {}
        else {
            fab = (FloatingActionButton) view.findViewById(R.id.FAB);
            JopRecyclerAdapter adapter = new JopRecyclerAdapter(getActivity(), allJobData);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(linearLayoutManager);


            this.fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), AddJob.class);
                    startActivity(i);
                }
            });
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onclick(View view)
    {
        Intent i = new Intent(getActivity() , AddJob.class);
        startActivity(i);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putSerializable("listjob", (Serializable) allJobData);
    }
}
