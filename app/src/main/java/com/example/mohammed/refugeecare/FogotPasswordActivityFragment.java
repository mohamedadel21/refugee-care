package com.example.mohammed.refugeecare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A placeholder fragment containing a simple view.
 */
public class FogotPasswordActivityFragment extends Fragment {

    Button resetpasswordButton,backButton;
    TextInputLayout emailTextInputLayout;
    EditText emailfield;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar3;
    String emailStr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root= inflater.inflate(R.layout.fragment_fogot_password, container, false);

        resetpasswordButton=(Button)root.findViewById(R.id.resetPasswordbutton);
        backButton=(Button)root.findViewById(R.id.backbutton);
        emailTextInputLayout=(TextInputLayout)root.findViewById(R.id.resetPasswordInput);
        emailfield=(EditText)root.findViewById(R.id.forgotEmailEditTextx);
        progressBar3=(ProgressBar)root.findViewById(R.id.progressBar3);
        progressBar3.setVisibility(View.INVISIBLE);

        firebaseAuth=FirebaseAuth.getInstance();
/*
        resetpasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar3.setVisibility(View.VISIBLE);


                if (TextUtils.isEmpty(emailfield.getText().toString())){
                    Snackbar.make(root,"Email can`t be empty",Snackbar.LENGTH_LONG).show();

                }else {

                    firebaseAuth.sendPasswordResetEmail(emailfield.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar3.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Snackbar.make(root, "We have sent you instructions to reset your password!", Snackbar.LENGTH_SHORT).show();
                                    } else {
                                        Snackbar.make(root, task.getException().getMessage(), Snackbar.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }

            }
        });*/

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SignInActivity.class));

            }
        });

        return root;
    }
}
