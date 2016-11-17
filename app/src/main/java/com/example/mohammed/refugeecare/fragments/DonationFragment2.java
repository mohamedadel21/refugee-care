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
import com.example.mohammed.refugeecare.adapters.DonationRecyclerAdapter;
import com.example.mohammed.refugeecare.model.DonationNormalData;
import com.example.mohammed.refugeecare.model.FirstPostData;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/13/2016.
 */
public class DonationFragment2 extends Fragment {

    ArrayList<DonationNormalData> allData;
    RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two_donation, container , false);

        recycler = (RecyclerView) view.findViewById(R.id.recycler_fragment2);


           allData = DonationNormalData.getData(getActivity());

            DonationRecyclerAdapter adapter = new DonationRecyclerAdapter(getActivity(), allData, new FirstPostData(), 5);


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            recycler.setAdapter(adapter);
            recycler.setLayoutManager(linearLayoutManager);


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putSerializable("list", (Serializable) allData);
    }


}
