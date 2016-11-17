package com.example.mohammed.refugeecare;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpActivityFragment extends Fragment {

    Firebase fireBase;
    DatabaseReference firebaseDatabase;
    FirebaseAuth firebaseAuth;



    String fullNameString,userNameString,emailString,passwordString
            ,confirmPasswordString,phoneNumberString,jobString,addressString,birthDateString;

    EditText fullName,userName,email,password
            ,confirmPassword,phoneNumber,job,address,birthDate;

    Button signUPButton;
    Spinner genderSpinner,refugeeOrdonatorSpinner;

    String gender="Male";
    String refugeeOrdonator="Refugee";
    String [] genderArray={"Male","Female"};
    String [] refugeeOrdonatorArray={"Refugee","Donator"};
    ProgressBar progressBar2;

  //------------------------------------------------------------------------------------------------------//
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        final View root= inflater.inflate(R.layout.fragment_sign_up, container, false);
        fireBase = new Firebase("https://refugee-care-2.firebaseio.com/users");
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth=FirebaseAuth.getInstance();
        //------------------------------------------------------------------------------//

        progressBar2=(ProgressBar)root.findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.INVISIBLE);


        //--------------------------------------------------------------------------------//
        //------------------------------------------------------------------------------//

        fullName=(EditText)root.findViewById(R.id.firstNameEdittext);
        userName=(EditText)root.findViewById(R.id.UserNameEdittext);
        email=(EditText)root.findViewById(R.id.EMailEditText);
        password=(EditText)root.findViewById(R.id.passwordEdittext);
        confirmPassword=(EditText)root.findViewById(R.id.confirmPasswordEdittext);
        phoneNumber=(EditText)root.findViewById(R.id.phoneEdittext);
        job=(EditText)root.findViewById(R.id.jobEdittext);
        address=(EditText)root.findViewById(R.id.addressEdittext);
        birthDate=(EditText)root.findViewById(R.id.birthDateEdittext);
        genderSpinner=(Spinner)root.findViewById(R.id.genderspinner);
        refugeeOrdonatorSpinner=(Spinner)root.findViewById(R.id.refugeeOrDonatorspinner);
        signUPButton=(Button)root.findViewById(R.id.SIGNUPButton);

        //------------------------------------------------------------------------------//

       spinnersAction();



        signUPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar2.setVisibility(View.VISIBLE);
                fullNameString = fullName.getText().toString();
                userNameString = userName.getText().toString();
                passwordString = password.getText().toString();
                confirmPasswordString = confirmPassword.getText().toString();
                phoneNumberString = phoneNumber.getText().toString();
                jobString = job.getText().toString();
                addressString = address.getText().toString();
                birthDateString = birthDate.getText().toString();
                emailString = email.getText().toString();

                if(validation())
                {

                    //make authontication with email and passsword
                    firebaseAuth.createUserWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar2.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                Toast.makeText(getActivity(), task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                                System.out.println(task.getException().getMessage());
                            }


                            else if(task.isSuccessful()) {

                                String id = task.getResult().getUser().getUid();

                                System.out.println("userId :"+id);
                                DatabaseReference newUser = firebaseDatabase.child("users").child(id);

                                newUser.child("First name").setValue(fullNameString);
                                newUser.child("Last Name").setValue(userNameString);
                                newUser.child("Email").setValue(emailString);
                                newUser.child("Phone Number").setValue(phoneNumberString);
                                newUser.child("Job").setValue(jobString);
                                newUser.child("Address").setValue(addressString);
                                newUser.child("Birth Date").setValue(birthDateString);
                                newUser.child("RefugeeOrDonator").setValue(refugeeOrdonator);

                                Toast.makeText(getActivity(), emailString +" has signed up",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    //put other data in firebase database


//========================================================================================================================//







                }


            }


            //========================================================================================================================//
            private boolean validation()
            {

                fullNameString = fullName.getText().toString();
                userNameString = userName.getText().toString();
                passwordString = password.getText().toString();
                confirmPasswordString = confirmPassword.getText().toString();
                phoneNumberString = phoneNumber.getText().toString();
                jobString = job.getText().toString();
                addressString = address.getText().toString();
                birthDateString = birthDate.getText().toString();
                emailString = email.getText().toString();




                String upperCaseChars = "(.*[A-Z].*)";
                String lowerCaseChars = "(.*[a-z].*)";
                String numbers = "(.*[0-9].*)";



                if (TextUtils.isEmpty(userNameString) || TextUtils.isEmpty(fullNameString) || TextUtils.isEmpty(addressString)
                        || TextUtils.isEmpty(birthDateString) ||  TextUtils.isEmpty(emailString) || TextUtils.isEmpty(passwordString) || TextUtils.isEmpty(jobString)) {
                    Snackbar.make(root,"Please Fill embty Fields",Snackbar.LENGTH_LONG).show();
                    return false;
                }


                else if (!isValidEmail(emailString)) {

                    Snackbar.make(root,"Email is not valid ",Snackbar.LENGTH_LONG).show();
                    return false;
                }

                else if (passwordString.length() > 16 || passwordString.length() < 8) {

                    Snackbar.make(root,"Password should be between 8-16 characters in length.",Snackbar.LENGTH_LONG).show();
                    return false;

                }


                else if (!(passwordString.matches(upperCaseChars)) || !(passwordString.matches(lowerCaseChars))  || !(passwordString.matches(numbers))   ) {

                    Snackbar.make(root,"Password should have compination of chars [upper ,lower],numbers",Snackbar.LENGTH_LONG).show();
                    return false;

                }

/*

                else if (String.valueOf(passwordString) != String.valueOf(confirmPasswordString) ){

                    Snackbar.make(root,"Password doesn`t match.",Snackbar.LENGTH_LONG).show();
                    return false;
                }
*/


                else if (TextUtils.isEmpty(phoneNumberString)  ||  !(phoneNumberString.length() == 11) ) {

                    Snackbar.make(root,"Phone number can`t be empty & at length  equal to 11",Snackbar.LENGTH_LONG).show();
                    return false;

                }

               else
                return  true;

            }
//========================================================================================================================//

            // }
        });

        return root;
    }
    public static boolean isValidEmail(String email)
    {
        boolean isValidEmail = false;

        String emailExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(emailExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
        {
            isValidEmail = true;
        }
        return isValidEmail;
    }

//========================================================================================================================//


    private void spinnersAction() {
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,genderArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(arrayAdapter);


        ArrayAdapter <String>  arrayAdapter2=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,refugeeOrdonatorArray);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        refugeeOrdonatorSpinner.setAdapter(arrayAdapter2);



        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender=genderArray[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        refugeeOrdonatorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                refugeeOrdonator=refugeeOrdonatorArray[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
// ===================================================================================================================//




}
