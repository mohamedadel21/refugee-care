package com.example.mohammed.refugeecare.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Ahmed Khattab 2 on 10/14/2016.
 */
public class DonationNormalData {

    private String userImgsrc;
    private String userName;
    private String time;
    private String postText;
    private String postHrefLink;
    private String postImgsrc;

    static ArrayList<DonationNormalData> data;

        static Context mContext;
    // constructor
    static ProgressDialog mProgressDialog;
        public static ArrayList<DonationNormalData> getData(Context c)
    {
        System.out.println("getData Method");

        mContext = c;
        mProgressDialog  = new ProgressDialog(c);

        try {
            data =  new getAllDonationData().execute(" new String ").get();

        }
        catch (Exception ex)
        {
            System.out.println("exception : "+ex.getMessage());
        }
        return data;


    }

    static ArrayList <DonationNormalData>  allData = new ArrayList<>();

    static class getAllDonationData extends AsyncTask<String , Void , ArrayList<DonationNormalData>>
    {

        @Override
        protected ArrayList<DonationNormalData> doInBackground(String... params) {
            DatabaseReference mDatabaseReference =  FirebaseDatabase.getInstance().getReference();
            DatabaseReference firebaseObj = mDatabaseReference.child("donationPosts");



            firebaseObj.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    ArrayList<String> strings = new ArrayList<String>();
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        String name = postSnapshot.getKey();
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
                        DonationNormalData donationData = new DonationNormalData();
                        donationData.setPostImgsrc(imgurl);
                        donationData.setPostText(descreption);
                        donationData.setTime(time);
                        donationData.setPostHrefLink(posturl);
                        allData.add(donationData);
                    }

                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            return allData;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            System.out.println("on pre execute will appear dialog ");
            mProgressDialog.setMessage("get Data ");
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<DonationNormalData> donationNormalDatas) {
            super.onPostExecute(donationNormalDatas);

            System.out.println("on post execute will disappear dialog ");
            mProgressDialog.dismiss();

        }
    }


    public static String[] getAllPostImgs(){
    String[]arrayOfImgs = {
             ""
            ,"https://www.soschildrensvillages.ca/sites/default/files/boy-standing-in-refugee-camp-in-gevgelija-serbia-dsc_8453.jpg"
            ,""
            ,"https://www.soschildrensvillages.ca/sites/default/files/boy-standing-in-refugee-camp-in-gevgelija-serbia-dsc_8453.jpg"
            ,"http://www.newyorker.com/wp-content/uploads/2015/09/Niarchos-Lesvos-1200.jpg"
            ,"https://www.soschildrensvillages.ca/sites/default/files/boy-standing-in-refugee-camp-in-gevgelija-serbia-dsc_8453.jpg"
            ,"http://www.newyorker.com/wp-content/uploads/2015/09/Niarchos-Lesvos-1200.jpg"
            ,"https://www.soschildrensvillages.ca/sites/default/files/boy-standing-in-refugee-camp-in-gevgelija-serbia-dsc_8453.jpg"};
    return arrayOfImgs;
}

    public static String[] getAllUserImgs() {
        //8 items
        String[]arrayOfImgs = {
                "http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/user-male-icon.png"
                ,"http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/user-male-icon.png"
                ,"http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/user-male-icon.png"
                ,"http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/user-male-icon.png"
                ,"http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/user-male-icon.png"
                ,"http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/user-male-icon.png"
                ,"http://icons.iconarchive.com/icons/paomedia/small-n-flat/1024/user-male-icon.png"};
        return arrayOfImgs;
    }

    public String[] getAllUserNames() {
        String userNamesArray []  = {
                "user 1" , "user 2","user 3" , "user 4","user 5" , "user 6","user 7" , "user 8"
        };
        return userNamesArray;
    }

    //setter and getter

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
