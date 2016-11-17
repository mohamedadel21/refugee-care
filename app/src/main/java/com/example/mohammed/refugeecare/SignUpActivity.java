package com.example.mohammed.refugeecare;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.widget.ImageButton;

public class SignUpActivity extends AppCompatActivity {


    private static final int GALLARY_REUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);




    }
    public void chooseProgilePic(View view)
    {
        /*//getImage from gallary and put it in picture
        Intent i = new Intent(Intent.ACTION_PICK);
        startActivityForResult(i , GALLARY_REUEST);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLARY_REUEST && resultCode == RESULT_OK)
        {
            Uri imgUri = data.getData();



        }
    }
}
