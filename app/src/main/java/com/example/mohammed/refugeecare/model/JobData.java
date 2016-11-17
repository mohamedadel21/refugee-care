package com.example.mohammed.refugeecare.model;

import android.content.Context;
import android.widget.ProgressBar;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Khattab 2 on 10/16/2016.
 */
public class JobData {


    private String textJobTitle;
    private String textJobCompany;
    private String textJobLocation;
    private String textJobLink;
    private String textjobDesc;
    private String posttime;

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    private String userImg;

    public JobData()
    {}
    public JobData(Context context , String textJobTitle,
                   String textJobCompany, String textJobLocation,
                   String textJobDate, String textJobLink, String userImg) {

        this.textJobTitle    = textJobTitle;
        this.textJobCompany  = textJobCompany;
        this.textJobLocation = textJobLocation;
        this.textJobLink     = textJobLink;
        this.userImg = userImg;
    }

    ArrayList<JobData> allData;
    public ArrayList<JobData> GetJobData()
{

        DatabaseReference mDatabaseReference =  FirebaseDatabase.getInstance().getReference();

        allData = new ArrayList<>();


        DatabaseReference firebaseObj = mDatabaseReference.child("jobs");

        firebaseObj.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                ArrayList<String> strings = new ArrayList<String>();


                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String name = postSnapshot.getKey();
                    strings.add(name);
                }
                for(int x =0 ; x<dataSnapshot.getChildrenCount() ; x++ )
                {


                    String jobCompany   = String.valueOf(dataSnapshot.child(strings.get(x)).child("jobCompany").getValue());
                    String jobDesc      = String.valueOf(dataSnapshot.child(strings.get(x)).child("jobDesc").getValue());
                    String jobLink      = String.valueOf(dataSnapshot.child(strings.get(x)).child("jobLink").getValue());
                    String jobPlace     = String.valueOf(dataSnapshot.child(strings.get(x)).child("jobPlace").getValue());
                    String jobTitle     = String.valueOf(dataSnapshot.child(strings.get(x)).child("jobTitle").getValue());
                    String jobtime      = String.valueOf(dataSnapshot.child(strings.get(x)).child("time").getValue() );

                    System.out.println("jobCompany\n" +jobCompany+
                            "jobDesc   \n" +jobDesc   +
                            "jobLink   \n" +jobLink   +
                            "jobPlace  \n" +jobPlace  +
                            "jobTitle  \n" +jobTitle  +
                            "jobtime   "   +jobtime     );
                    JobData jdata = new JobData();

                    jdata.setTextJobCompany(jobCompany);
                    jdata.setTextjobDesc(jobDesc);
                    jdata.setTextJobLocation(jobPlace);
                    jdata.setTextJobTitle(jobTitle);
                    jdata.setTextJobLink(jobLink);
                    jdata.setPosttime(jobtime);
                    allData.add(jdata);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return allData;
    }

    public String getTextJobTitle() {
        return textJobTitle;
    }

    public void setTextJobTitle(String textJobTitle) {
        this.textJobTitle = textJobTitle;
    }

    public String getTextJobCompany() {
        return textJobCompany;
    }

    public void setTextJobCompany(String textJobCompany) {
        this.textJobCompany = textJobCompany;
    }

    public String getTextJobLocation() {
        return textJobLocation;
    }

    public void setTextJobLocation(String textJobLocation) {
        this.textJobLocation = textJobLocation;
    }

    public String getTextJobLink() {
        return textJobLink;
    }

    public void setTextJobLink(String textJobLink) {
        this.textJobLink = textJobLink;
    }

    public String getTextjobDesc() {
        return textjobDesc;
    }

    public void setTextjobDesc(String textjobDesc) {
        this.textjobDesc = textjobDesc;
    }

    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    public ArrayList<JobData> getAllData() {
        return allData;
    }

    public void setAllData(ArrayList<JobData> allData) {
        this.allData = allData;
    }
}
