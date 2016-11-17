package com.example.mohammed.refugeecare.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mohammed.refugeecare.R;
import com.example.mohammed.refugeecare.adapters.ViewPagerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Toolbar toolbar;

    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //inatialize  xml elments
        intialize();

        ArrayList<String> titles = getTitles();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),titles);
        mViewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(mViewPager);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null)
        {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            String uid = user.getUid();
            System.out.println("data : name  "+name+" email :"+email+"  photoUrl"+photoUrl);
            System.out.println("data : uid :"+uid);
        }




    }

    public void goToUserProfile(View  v)
    {
        startActivity(new Intent(this , Profile.class));
    }
    public void goToAddJob(View v)
    {
        startActivity(new Intent(this , AddJob.class));
    }
    private void intialize() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("main Avtivity");

        mViewPager = (ViewPager) findViewById(R.id.viewpager_main);
        tabLayout = (TabLayout) findViewById(R.id.tab);
    }
    public ArrayList<String> getTitles()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("call for help ");
        list.add("donations");
        list.add("jobs");
        list.add("CHARITIES");
        return list;

    }


    public void gotoAddHelpPost(View view)
    {
        Intent i = new Intent(this , WritePost.class);

        i.putExtra("helpOrDonation","help");
        startActivity(i);
    }
    public void gotoAddDonationPost(View v)
    {
        Intent i = new Intent(this , WritePost.class);
        i.putExtra("helpOrDonation","donation");

        startActivity(i);

    }

}
