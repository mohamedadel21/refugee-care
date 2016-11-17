package com.example.mohammed.refugeecare.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/14/2016.
 */
public class HelpNormalData {

    private String userImgsrc;
    private String userName;
    private String time;
    private String place;
    private String postText;
    private String postHrefLink;
    private String postImgsrc;

    static Context context;

    static ArrayList<HelpNormalData> data;

    static ProgressDialog mProgressDialog;
        public static ArrayList<HelpNormalData> getData(Context c) {


            context = c;
            mProgressDialog  = new ProgressDialog(context);

            try {
//                data =  new getAllData().execute(" new String ").get();
                data = getAllData();

            }
            catch (Exception ex)
            {
                System.out.println("exception : "+ex.getMessage());
            }
        return data;


    }

    static ArrayList <HelpNormalData>  allData = new ArrayList<>();
    static ArrayList<HelpNormalData> getAllData()
    {

//        @Override
//        protected ArrayList<HelpNormalData> doInBackground(String... params) {

            DatabaseReference mDatabaseReference =  FirebaseDatabase.getInstance().getReference();
            DatabaseReference firebaseObj = mDatabaseReference.child("helpPosts");



            firebaseObj.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    ArrayList<String> strings = new ArrayList<String>();
                    int numOfChildren = (int) dataSnapshot.getChildrenCount();
                    System.out.println("number of children "+numOfChildren);
/*
                    for (int x= 0 ; x<numOfChildren ; x++ )
                    {
                        String name =  dataSnapshot.getChildren().toString();
                        System.out.println("names of children : "+name);
                        strings.add(name);
                    }*/
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        String name = postSnapshot.getKey();
                        System.out.println("child name"+name);
                        strings.add(name);
                    }

                    for(int x =0 ; x<dataSnapshot.getChildrenCount() ; x++ ) {

                        String descreption  = String.valueOf(dataSnapshot.child(strings.get(x)).child("descreption").getValue());
                        String imgurl       = String.valueOf(dataSnapshot.child(strings.get(x)).child("imgUrl").getValue());
                        String time         = String.valueOf(dataSnapshot.child(strings.get(x)).child("time").getValue());
                        String posturl      = String.valueOf(dataSnapshot.child(strings.get(x)).child("posturl").getValue());


                        System.out.println(
                                "descreption\n" +descreption+
                                        "imgurl     \n" +imgurl     +
                                        "time       \n" +time       +
                                        "posturl    "+ posturl
                        );
                        HelpNormalData helpData = new HelpNormalData();
                        helpData.setPostImgsrc(imgurl);
                        helpData.setPostText(descreption);
                        helpData.setTime(time);
                        helpData.setPostHrefLink(posturl);
                        allData.add(helpData);
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(context, "Error"+databaseError, Toast.LENGTH_SHORT).show();
                }
            });
            mProgressDialog.dismiss();


            return allData;
        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            System.out.println("on pre execute will appear dialog ");
//            mProgressDialog.setMessage("get Data ");
//            mProgressDialog.show();
//
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<HelpNormalData> helpNormalDatas) {
//            super.onPostExecute(helpNormalDatas);
//            System.out.println("on post execute will disappear dialog ");
//
//            mProgressDialog.dismiss();
//
//        }
//    }

    //setter and getter
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public String getUserImgsrc() {
        return userImgsrc;
    }

    public void setUserImgsrc(String userImgsrc) {
        this.userImgsrc = userImgsrc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostHrefLink() {
        return postHrefLink;
    }

    public void setPostHrefLink(String postHrefLink) {
        this.postHrefLink = postHrefLink;
    }

    public String getPostImgsrc() {
        return postImgsrc;
    }

    public void setPostImgsrc(String postImgsrc) {
        this.postImgsrc = postImgsrc;
    }


}
