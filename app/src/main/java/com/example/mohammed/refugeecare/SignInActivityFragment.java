package com.example.mohammed.refugeecare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mohammed.refugeecare.app.MainActivity;

import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

/**
 * A placeholder fragment containing a simple view.
 */
public class SignInActivityFragment extends Fragment {

    FireBase fireBase;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    EditText emailEditText,passwordEditText;
    Button signinButton,signupButton,ForgotmypasswordButton;
    SignInButton SigninwithgoogleButton;

    final  static int RC_SIGN_IN=1;
    final static String TAG="MAIN_ACTIVITY";
    private GoogleApiClient mGoogleApiClient;
    View root;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        root =inflater.inflate(R.layout.fragment_sign_in, container, false);
        firebaseAuth=FirebaseAuth.getInstance();

        intialize();
        googleSignInButtonAction();
        signInButtonAction();
        forgetPaswordButtonAction();
        signUpButtonAction();


        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser()!=null ){

                }
            }
        };

        return root;
    }
//====================================================================================================================//

    private void googleSignInButtonAction() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient=new GoogleApiClient.Builder(getActivity()).enableAutoManage(getActivity(),
                new GoogleApiClient.OnConnectionFailedListener(){
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getActivity(),"you got an error",Toast.LENGTH_LONG).show();
                    }

                }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        SigninwithgoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
    }
//====================================================================================================================//

    private void signInButtonAction() {
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(emailEditText.getText().toString(),passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 8) {
                                Snackbar.make(root,"password must be larger than 8 characters",Snackbar.LENGTH_LONG).show();   ;
                            } else {
                                Snackbar.make(root,task.getException().getMessage(),Snackbar.LENGTH_LONG).show();   ;
                            }
                        } else {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);

                        }
                    }
                });
            }
        });

    }
//====================================================================================================================//

    private void forgetPaswordButtonAction() {
        ForgotmypasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),FogotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }
    //=====================================================================================================//

    private void signUpButtonAction() {
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    //=====================================================================================================//
    private void intialize() {

        emailEditText=(EditText)root.findViewById(R.id.emailEditText);
        passwordEditText=(EditText)root.findViewById(R.id.edpassworditText);
        signinButton=(Button)root.findViewById(R.id.LoginButton);
        signupButton=(Button)root.findViewById(R.id.SignupButton);
        SigninwithgoogleButton =(SignInButton)root.findViewById(R.id.SigninWithGoogleButton);
        ForgotmypasswordButton=(Button)root.findViewById(R.id.ForgotmypasswordButton);
        progressBar= (ProgressBar)root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

//=====================================================================================================//

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
//=====================================================================================================//

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                System.out.println("result.isSuccess()");
                GoogleSignInAccount acct = result.getSignInAccount();

                System.out.println("get user data from email of user ");

                String personGivenName  = acct.getGivenName();
                String personEmail      = acct.getEmail();
                Uri    personPhoto      = acct.getPhotoUrl();

                System.out.println("before upload user data to firebase ");
                System.out.println(
                        "personGivenName \n" +personGivenName +
                        "personEmail     \n" +personEmail     +
                        "personPhoto     "+personPhoto);



                 System.out.println("before put user data to sharred prefrance ");

                SharedPreferences pref = getActivity().getSharedPreferences("MyPref", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("personGivenName"  ,  personGivenName);
                editor.putString("personEmail"      ,  personEmail    );
                editor.putString("personPhoto"      ,  personPhoto.toString()    );
                editor.commit();




                startActivity(new Intent(getActivity(),MainActivity.class));
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);



            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }
//=====================================================================================================//

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(getActivity() , "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });



    }
//=====================================================================================================//

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
//=====================================================================================================//

}
