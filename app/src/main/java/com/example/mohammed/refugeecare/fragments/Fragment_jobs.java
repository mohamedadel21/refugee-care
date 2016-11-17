package com.example.mohammed.refugeecare.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.adapters.CustomListAdapter;
import com.example.mohammed.refugeecare.model.JobData;
import com.example.mohammed.refugeecare.model.JobsModel;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/29/2016.
 */
public class Fragment_jobs extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_jobs, container ,false);

        ListView mListView ;


        ArrayList<JobsModel> alldata = getAllData();

        mListView = (ListView) view.findViewById(R.id.listView);
        CustomListAdapter adapter = new CustomListAdapter(getActivity(), alldata);
        mListView.setAdapter(adapter);

        return view;

    }
    public ArrayList<JobsModel> getAllData()
    {

        ArrayList<JobsModel> alldata = new ArrayList<>();

        JobsModel model = new JobsModel();
        model.setJobName("System Engineer");
        model.setCompanyName("Medicare");
        model.setTime("in 1 hour");

        model.setJobPlace("Maadi, Cairo");
        model.setJobTime("full Time");

        model.setExperienc("3-5 years");
        model.setImgSrc("https://s3-eu-west-1.amazonaws.com/wuzzuf/files/company_logo/Medicare-Egypt-9088-xs.PNG");
        model.setJobink("http://wuzzuf.net/jobs/p/58593-System-Engineer-Medicare-Cairo-Egypt");
        alldata.add(model);


         model = new JobsModel();

        model.setJobName("Accounting Manager");
        model.setCompanyName("Medicare");
        model.setTime("1 hour ago");

        model.setJobPlace("Nasr City, Cairo");
        model.setJobTime("full Time");

        model.setExperienc("5-15 Yrs of Exp");
        model.setImgSrc("https://s3-eu-west-1.amazonaws.com/wuzzuf/files/company_logo/Riadco-2000-Egypt-20289-1476693872-xs.png");
        model.setJobink("http://wuzzuf.net/jobs/p/58592-Accounting-Manager-Riadco-2000-Cairo-Egypt");
        alldata.add(model);

//==============================================================================================================//
        model = new JobsModel();
        model.setJobName("Product designer -Ready Garments");
        model.setCompanyName("Gold Style Textile Company");
        model.setTime("15/10/2016");

        model.setJobPlace("المنطقه الصناعيه, portsaid");
        model.setJobTime("5-10 Yrs of Exp ");

        model.setExperienc("5-10 Yrs of Exp");
        model.setImgSrc("https://s3-eu-west-1.amazonaws.com/wuzzuf/files/company_logo/Gold-Style-Textile-Company-Egypt-20431-1477563218-xs.jpg");
        model.setJobink("http://wuzzuf.net/jobs/p/58590-Product-designer--Ready-Garments-Gold-Style-Textile-Company-portsaid-Egypt");
        alldata.add(model);
//==============================================================================================================//
//==============================================================================================================//
        model = new JobsModel();
        model.setJobName("English Translator");
        model.setCompanyName("Mercury Communications");
        model.setTime("12 hours ago");

        model.setJobPlace("Heliopolis, Cairo");
        model.setJobTime("5-10 Yrs of Exp ");

        model.setExperienc("· Experienced · 3+ Yrs of Exp ·");
        model.setImgSrc("https://s3-eu-west-1.amazonaws.com/wuzzuf/files/company_logo/Mercury-Communications-Egypt-20338-1477235041-xs.jpg");
        model.setJobink("http://wuzzuf.net/jobs/p/58068-English-Translator-Mercury-Communications-Cairo-Egypt");
        alldata.add(model);
//==============================================================================================================//
//==============================================================================================================//
        model = new JobsModel();
        model.setJobName("Linux System Administrator");
        model.setCompanyName("Mhgoz.com");
        model.setTime("14 hours ago");

        model.setJobPlace("Nasr City - Cairo, Egypt");
        model.setJobTime("5-10 Yrs of Exp ");

        model.setExperienc("2-5 Yrs of Exp");
        model.setImgSrc("https://s3-eu-west-1.amazonaws.com/wuzzuf/files/company_logo/Mhgoz-com-Egypt-15276-1457036127-xs.png");
        model.setJobink("http://wuzzuf.net/jobs/p/58589-Linux-System-Administrator-Mhgoz-com-Nasr-City---Cairo-Egypt");
        alldata.add(model);
//==============================================================================================================//

        //==============================================================================================================//
        model = new JobsModel();
        model.setJobName("Events coordinator");
        model.setCompanyName("Confidential\n");
        model.setTime("15/10/2016");

        model.setJobPlace("Dokki, Egypt");
        model.setJobTime("5-10 Yrs of Exp ");

        model.setExperienc("1 - 3 Yrs of Exp ");
        model.setImgSrc("https://s3-eu-west-1.amazonaws.com/wuzzuf/files/company_logo/company-logo.png");
        model.setJobink("http://wuzzuf.net/jobs/p/58587-Events-coordinator-Dokki-Egypt");
        alldata.add(model);
//==============================================================================================================//

        //==============================================================================================================//
        model = new JobsModel();
        model.setJobName("English Instructor - 6 October City");
        model.setCompanyName("American Academy");
        model.setTime("15/10/2016");

        model.setJobPlace("Dokki, Egypt");
        model.setJobTime(" 1+ Yrs of Exp ");

        model.setExperienc("1 - 3 Yrs of Exp ");
        model.setImgSrc("https://s3-eu-west-1.amazonaws.com/wuzzuf/files/company_logo/American-Academy-Egypt-4460-xs.jpg");
        model.setJobink("http://wuzzuf.net/jobs/p/58585-English-Instructor---6-October-City-American-Academy-cairo-giza-Egypt");
        alldata.add(model);
//==============================================================================================================//


        return alldata;

    }
}
