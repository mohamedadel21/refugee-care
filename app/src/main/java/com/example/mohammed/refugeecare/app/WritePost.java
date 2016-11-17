package com.example.mohammed.refugeecare.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by Ahmed Khattab 2 on 10/17/2016.
 */
public class WritePost extends AppCompatActivity {



    private Button btnPost;
    private ImageButton buttonBack;
    private ImageButton buttonAddPhoto;
    private ImageView   imgPicPlace;

    private EditText textDesc;
    private EditText textLink;
    private int postType;
    private Toolbar toolbar;
    private Button buttonAddPost;

//=====================================================================================================//
    private static final int GALLARY_REQUEST = 2;
    private StorageReference firebaseStorage;
    private DatabaseReference mDatabaseReference;
    private ProgressBar mProgress;
    static int id;

    static String imguri= "";
    static Uri imgUri;

    public static int helpOrDonation; //1--> help post  2-->donation post
//=====================================================================================================//

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_post_help);

        firebaseStorage    = FirebaseStorage.getInstance().getReference();
        mDatabaseReference =  FirebaseDatabase.getInstance().getReference();

        intialize();

        toolbar = (Toolbar) findViewById(R.id.toolbar_write_post);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        this.postType =intent.getIntExtra("postType",0);

        String hOrD = intent.getStringExtra("helpOrDonation").toString();


        if(hOrD.equals("help"))
            helpOrDonation = 1;
        else if (hOrD.equals("donation"))
            helpOrDonation = 2;


    }
//=====================================================================================================//

    //intialize all xml data in java
    private void intialize() {


        btnPost             = (Button)      findViewById(R.id.button_post_add_need);
        buttonBack          = (ImageButton) findViewById(R.id.button_back_need_post);
        buttonAddPhoto      = (ImageButton) findViewById(R.id.button_get_img_from_gallary);
        imgPicPlace         = (ImageView)   findViewById(R.id.img_from_gallary_need_post);
        textDesc            = (EditText)    findViewById(R.id.edit_text_post_descreption);
        textLink            = (EditText)    findViewById(R.id.edit_text_post_url);
        buttonAddPost       = (Button)      findViewById(R.id.button_add_post);
        mProgress           = (ProgressBar) findViewById(R.id.progressBar_add_post);


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

//===============================================================================================================//


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


    public class  getWebContent extends AsyncTask<String , Void , String>{

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

//=====================================================================================================//
                //get place when add post
    public String getCurrentPlace()
    {
        String place = null;
        return place;
    }

//========================== end of get data methods ===========================================================================//
//=====================================================================================================//

    public void closeActivity(View view)
    {
            finish();
    }
//=====================================================================================================//


//===============================put all data to firebase ================================================================================//

    // method of button post to start posting data
    public void writePost(View view) throws ExecutionException, InterruptedException {
        String desceVal = textDesc.getText().toString().trim();
        String urlVal = textLink.getText().toString().trim();

        if( desceVal.isEmpty() )
        {
            Toast.makeText(getApplicationContext(), " please fill at least descreption of post ", Toast.LENGTH_LONG).show();    //context , msg , duration
        }
        else {
            String url = textLink.getText().toString();
            if(!url.isEmpty())
            {
                boolean validUrl = URLUtil.isValidUrl(url);
                if(validUrl) {
                    upload();
                }
                else
                {
                    showDailog("not valid link", "please enter avalid link");

                }
            }
            else{
                upload();
            }

        }
/******************************************************************************************************/

//------------------------------------------------------------------------------------------------------------//



/******************************************************************************************************/

            }
    DatabaseReference newPost;

    private void upload() {
        mProgress.setVisibility(View.VISIBLE);
        buttonAddPost.setVisibility(View.GONE);

        Toast.makeText(getApplicationContext(), " will write post", Toast.LENGTH_LONG).show();    //context , msg , duration



        if(helpOrDonation == 1)               // this case user need to ask for help
            newPost = mDatabaseReference.child("helpPosts").push();
        else if(helpOrDonation == 2)                // this case user need to upload donation
            newPost = mDatabaseReference.child("donationPosts").push();

        System.out.println("imguri inpost "+imgUri+"   !(imgUri == null)"+(!(imgUri == null)) );
        if (!(imgUri == null)) {
            mProgress.setVisibility(View.VISIBLE);

            System.out.println("id outside before " + id);
            //upload image to firebase with unique name from firebase storage imgID
            String imageId = String.valueOf("img" + id);
            StorageReference imgStorage = firebaseStorage.child("images").child(imageId);
            imgStorage.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    try {

                        Uri downloadUri = taskSnapshot.getDownloadUrl();
                        //add post to data on firebase
                        newPost.child("imgUrl").setValue(downloadUri.toString().trim());
                        uploadData(newPost);

                        showDailog("success", "post uploaded successfully  ");
                        embtyFields();

                    } catch (Exception e) {
                        showDailog("failed ", "error in post upload");

                    }
                }

            });
        }
        else{

            try {
                uploadData(newPost);
                showDailog("success", "post uploaded successfully  ");
                embtyFields();
            } catch (Exception e) {
                showDailog("failed ", ("error in post upload"+e.getMessage().toString()));

            }
        }

    }

    private void uploadData(DatabaseReference newPost) {

        mProgress.setVisibility(View.VISIBLE);

        String curruntTime = getTime();
        System.out.println("getTime method in action : " + curruntTime);

        newPost.child("descreption").setValue(textDesc.getText().toString().trim());
        newPost.child("posturl").setValue(textLink.getText().toString().trim());
        newPost.child("time").setValue(curruntTime.toString().trim());
        //save post id in shared prefrances , in user account under posts
        //
        // need user id  from shared prefrance :)
        // likes , comments will be added from other place(in action of adapter)
        String postID = newPost.getKey();
        System.out.println("Post key : " + postID);
        System.out.println("id of image outside : " + id);
        mDatabaseReference.child("imgID").setValue(String.valueOf(id + 1));

        mProgress.setVisibility(View.GONE);
    }

//=====================================================================================================//


    private void embtyFields() {
        buttonAddPost.setVisibility(View.VISIBLE);
        textDesc.setText("");
        textLink.setText("");
        imgPicPlace.setImageURI(null);

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
                        finish();
                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
        //========================================================================================================//
    //validate data before upload

    private boolean checkURL(String urlVal) {

        return URLUtil.isValidUrl(urlVal);
    }

//=======================================end of put data to firebase ==============================================================//

    private boolean validation() {
        String textneed = textDesc.getText().toString();
        String textlink =textLink.getText().toString();

        if( textneed.isEmpty()  &&  textlink.isEmpty())
            return false;
        else
            return true;

    }



//=====================================================================================================//


}
