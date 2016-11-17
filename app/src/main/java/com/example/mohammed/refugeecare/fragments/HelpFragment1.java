package com.example.mohammed.refugeecare.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.adapters.HelpRecyclerAdapter;
import com.example.mohammed.refugeecare.model.FirstPostData;
import com.example.mohammed.refugeecare.model.HelpNormalData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/13/2016.
 */
public class HelpFragment1 extends Fragment {

    ArrayList<HelpNormalData> allData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one_help, container ,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_fragment1);


    /*    if (savedInstanceState != null) {
            System.out.println(" in on onCreateView    savedInstanceState != null");
            //probably orientation change
            allData = (ArrayList<HelpNormalData>) savedInstanceState.getSerializable("list");
        }
        else {
    */
        allData = HelpNormalData.getData(getActivity());
        HelpRecyclerAdapter adapter = new HelpRecyclerAdapter(getActivity() , allData, new FirstPostData());


        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);



        return view;

    }


    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putSerializable("list", (Serializable) allData);
    }

}
