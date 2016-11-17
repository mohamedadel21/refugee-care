package com.example.mohammed.refugeecare.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohammed.refugeecare.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Ahmed Khattab 2 on 10/19/2016.
 */
public class Profile extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnFollow;
    private ImageButton btnEditPic;
    private TextView textPhone;
    private TextView textEmail;
    private TextView textName;
    private static boolean isfollow ;
    private Uri mImgFromGallary;
    private ImageView imgProfilePic;

    private  static final int GALLARY_REQUEST = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        intialize();

        SharedPreferences pref = this.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String personGivenName  = pref.getString("personGivenName","username");
        String personEmail      = pref.getString("personEmail","email");
        String personPhoto      = pref.getString("personPhoto","https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg");

        System.out.println("data in profile : "+personGivenName+
                personEmail+
                personPhoto  );
        Picasso
                .with(this)
                .load(personPhoto)
                .into(imgProfilePic);

        textName.setText(personGivenName);
        textEmail.setText(personEmail);


        //intialize button style of follow in xml
        // isfollow = get from firebase
        isfollow = true;
        setFollowDesign(btnFollow);
        // isfollow = get if user follow or not from firebase

    }


    private void intialize() {

        btnBack = (ImageButton)     findViewById(R.id.button_back_profile);

        btnFollow = (Button)        findViewById(R.id.button_follow_profile);
        btnEditPic = (ImageButton)  findViewById(R.id.button_edit_pic_profile);
        textPhone = (TextView)      findViewById(R.id.text_phone_profile);
        textName = (TextView)      findViewById(R.id.text_name_profile);
        textEmail = (TextView)      findViewById(R.id.text_email_profile);
        imgProfilePic = (ImageView) findViewById(R.id.img_profile_pic);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back_profile:
                finish();
                break;
            case R.id.button_call_profile:

                new MakeCall(Profile.this, getPhoneNumber().toString());

                break;
            case R.id.button_follow_profile:
                actionForFollow();
                break;
            case R.id.button_edit_pic_profile:
                getImgFromGallary();
                //begin activity to get pic from gallry and put it in firebase and database
                break;

        }

    }

    private void actionForFollow() {


        //if user isalready follow it will change to unfollow
        if(isfollow) {
            setFollowDesign(btnFollow);
            isfollow = false;
            //set isfollow in firebase to value false
        }
        else {
            setFollowDesign(btnFollow);
            isfollow = true;
            //set isfollow in firebase to value true
        }
    }

    public void setFollowDesign(Button btn)
    {
        int x ;
        if(isfollow == true)
        {

            x = R.drawable.button_raduis_embty;
            btn.setBackgroundResource(x);
            int c = ( R.color.colorAccent);
            btn.setTextColor(Color.GRAY);
            btn.setText("Un Follow");


        }
        else
        {
            x = R.drawable.button_raduis;
            btn.setBackgroundResource(x);
            btn.setTextColor(Color.WHITE);
            btn.setText("Follow +");


        }
    }
    public String getPhoneNumber() {
        String phoneNumber = textPhone.getText().toString();
        return phoneNumber;
    }


    public void getImgFromGallary()
    {
        Intent gallaryIntent = new Intent(Intent.ACTION_PICK);
        gallaryIntent.setType("image/*");
        startActivityForResult(gallaryIntent , GALLARY_REQUEST);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLARY_REQUEST && resultCode == RESULT_OK) {
            Uri imgUri = data.getData();
            imgProfilePic.setImageURI(imgUri);

        }
    }

}
