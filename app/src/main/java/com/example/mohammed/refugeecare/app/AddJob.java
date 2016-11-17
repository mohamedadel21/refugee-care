package com.example.mohammed.refugeecare.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mohammed.refugeecare.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class AddJob extends AppCompatActivity {

    private Toolbar toolbar;


    private ImageButton buttonBack;
    private Button      buttonPost;

    private EditText    txtJobTitle;
    private EditText    txtJobCompany;
    private EditText    txtJobLocation;
    private EditText    txtJobDetail;
    private EditText    txtJobLink;
    private ImageButton btnpickImg;
    private ImageView   imgPicPlace;

    private ProgressBar mProgress;

    private StorageReference firebaseStorage;
    private DatabaseReference mDatabaseReference;

    private static final int GALLARY_REQUEST = 1;
    static int id;
    static String imguri= "";
    private static Uri imgUri ;


//--------------------------------------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);

        firebaseStorage    = FirebaseStorage.getInstance().getReference();
        mDatabaseReference =  FirebaseDatabase.getInstance().getReference();


        toolbar = (Toolbar) findViewById(R.id.job_toolbar);
        setSupportActionBar(toolbar);

        intialize();


    }

    //=====================================================================================================//

    public void closeActivity(View view)
    {
        finish();
    }
    //=====================================================================================================//
                                //intialize data of xml
        private void intialize() {
        buttonBack      = (ImageButton) findViewById(R.id.button_back_job_post);
        buttonPost      = (Button)      findViewById(R.id.button_post_add_job);
        txtJobTitle     = (EditText)    findViewById(R.id.text_job_title);
        txtJobCompany   = (EditText)    findViewById(R.id.text_job_company);
        txtJobLocation  = (EditText)    findViewById(R.id.text_job_location);
        txtJobDetail    = (EditText)    findViewById(R.id.text_job_description);
        txtJobLink      = (EditText)    findViewById(R.id.text_job_link);



        btnpickImg      = (ImageButton) findViewById(R.id.button_get_img_from_gallary_job_activity);
        imgPicPlace     = (ImageView) findViewById(R.id.img_picked_addjop_activity);


        mProgress       = (ProgressBar) findViewById(R.id.progressBar_add_job);


            DatabaseReference imgID = mDatabaseReference.child("imgID");
            imgID.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("inside onDataChange");
                    id = Integer.parseInt( dataSnapshot.getValue().toString() );
                    System.out.println("data : "+dataSnapshot.getValue().toString() );


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


//=====================================================================================================//
//================================= get all data methods ====================================================================//

    public void getImgFromGallary(View view)
    {
        Intent gallaryIntent = new Intent(Intent.ACTION_PICK);
        gallaryIntent.setType("image/*");
        startActivityForResult(gallaryIntent , GALLARY_REQUEST);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLARY_REQUEST && resultCode == RESULT_OK)
        {

            imgUri = data.getData();
            imguri = imgUri.toString();
            System.out.println("uri of img : "+imgUri.toString());
            imgPicPlace.setImageURI(imgUri);

        }

    }

//=====================================================================================================//
           /*      save instance state methods  to save data when rotate (change porteriet/land)    */

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(imgUri == null)
        {}
        else
        {

            outState.putString("imgUri" ,imgUri.toString());
        }

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        if(imguri.isEmpty())
        {}
        else
        {
            imguri  =  savedInstanceState.getString("imgUri").toString();
            Uri uri = Uri.parse(imguri );
            imgPicPlace.setImageURI(uri);
        }
    }
    //=====================================================================================================//
    //get time of post
    public String getTime()
    {
        String result = "";
        try {
            getWebContent download = new getWebContent();
            result = download.execute("http://www.timeapi.org/utc/now").get();
            result = result.substring(0,19);
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

        System.out.println("time : "+result);
        Toast.makeText(getApplicationContext(),"time : "+result, Toast.LENGTH_LONG).show();    //context , msg , duration
        return result;
    }


    public class  getWebContent extends AsyncTask<String , Void , String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            URL url ;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream is = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(is);

                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += String.valueOf(current);
                    data = reader.read();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }

//=================================end of get data=================================================================//
//=====================================================================================================//

//=====================================================================================================//

    /*
    *still need to add location of user
    * add user id
    * add likes , comments in adapter
    *
    * load data from firebase
    *
    *
    *
    */
    //
//=================================add data to firebase====================================================================//

    // method of button post to start posting data
    public void postJob(View view) throws ExecutionException, InterruptedException {


        if (!validation()) {
            Toast.makeText(getApplicationContext(), " please fill components of job ", Toast.LENGTH_LONG).show();    //context , msg , duration
        }
        else {
            String url = txtJobLink.getText().toString();
            if (!url.isEmpty()) {
                boolean validUrl = URLUtil.isValidUrl(url);
                if (validUrl) {
                  upload();
                } else {
                    showDailog("not valid link", "please enter avalid link");
                }
            }
            else
            {
                upload();
            }
        }
    }

    private void upload() {
        mProgress.setVisibility(View.VISIBLE);
        buttonPost.setVisibility(View.GONE);

        //            System.out.println("image url : " + imgUri.toString());
        Toast.makeText(getApplicationContext(), " will add job ", Toast.LENGTH_LONG).show();    //context , msg , duration


        System.out.println("id outside before " + id);

        //upload image to firebase with unique name from firebase storage imgID
        String imageId = String.valueOf("img" + id);
        StorageReference imgStorage = firebaseStorage.child("images").child(imageId);

        /******************************************************************************************************/

        final DatabaseReference newPost = mDatabaseReference.child("jobs").push();
        if (!(imgUri == null)) {
            mProgress.setVisibility(View.VISIBLE);

            Toast.makeText(getApplicationContext(), "load pic", Toast.LENGTH_LONG).show();    //context , msg , duration
            imgStorage.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    try {

                        Uri downloadUri = taskSnapshot.getDownloadUrl();
                        //add post to data on firebase
                        newPost.child("jobPic").setValue(downloadUri.toString());
                        uploadData(newPost);

                    } catch (Exception e) {
                        showDailog("failed ", "error in post upload");
                    }

                }
            });
        }


        else {
            try {
                    uploadData(newPost);
            } catch (Exception e) {
                showDailog("failed ", "error in post upload");
            }
        }
    }

    private void uploadData(DatabaseReference newPost) {

        mProgress.setVisibility(View.VISIBLE);
        //add post to data on firebase

        final String jobTitle = txtJobTitle.getText().toString().trim();
        final String jobcompany = txtJobCompany.getText().toString().trim();
        final String jobPlace = txtJobLocation.getText().toString().trim();
        final String jobDesc = txtJobDetail.getText().toString().trim();
        final String jobLink = txtJobLink.getText().toString().trim();
        final String curruntTime = getTime();
        System.out.println("getTime method in action : " + curruntTime);

        newPost.child("jobTitle").setValue(jobTitle);
        newPost.child("jobCompany").setValue(jobcompany);
        newPost.child("jobPlace").setValue(jobPlace);
        newPost.child("jobDesc").setValue(jobDesc);
        newPost.child("jobLink").setValue(jobLink);
        newPost.child("time").setValue(curruntTime.toString().trim());

        String postID = newPost.getKey();
        System.out.println("Post key : " + postID);
        System.out.println("id of image outside : " + id);
        mDatabaseReference.child("imgID").setValue(String.valueOf(id + 1));

        mProgress.setVisibility(View.GONE);
        showDailog("success", "post uploaded successfully  ");
        embtyFields();
        //save post  in user account under jobs of user
        //
        // need user id  from shared prefrance :)
        // likes , comments will be added from other place(in action of adapter)


    }
//=====================================================================================================//


    private void embtyFields() {

        buttonPost.setVisibility(View.VISIBLE);
        txtJobTitle.setText(null);
        txtJobCompany.setText(null);
        txtJobLocation.setText(null);
        txtJobDetail.setText(null);
        imgPicPlace.setImageURI(null);

    }


//=====================================================================================================//
            /* check data of post which will be upload*/
    private boolean validation() {

        EditText[] txts = {
                txtJobTitle,
                txtJobCompany,
                txtJobLocation,
        };


        boolean isembty = false;
        for(EditText txt :txts)
        {
            if(txt.getText().toString().isEmpty())
                isembty = true;

        }

        // check if link is right link
        if(isembty)
            return false;
        else
            return true;

    }

//=====================================================================================================//

    public void showDailog( String title , String message  )
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
    //=====================================================================================================//
}
