package com.example.mohammed.refugeecare.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.adapters.CharitiesRecyclerAdapter;
import com.example.mohammed.refugeecare.model.CharitiesData;
import com.example.mohammed.refugeecare.model.CharitiesProfileData;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/19/2016.
 */
public class CharitiesFragment4 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.charities_fragment , container , false);


        CharitiesData ch = new CharitiesData();
        ArrayList<CharitiesProfileData> list = ch.getAllData();
        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recyclerview_charities);
        CharitiesRecyclerAdapter adapter = new CharitiesRecyclerAdapter(getActivity(), list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(linearLayoutManager);

        return view;
    }


}
