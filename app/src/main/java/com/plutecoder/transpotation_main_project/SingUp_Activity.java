package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Setter_Getter.Person;

public class SingUp_Activity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    EditText edtfirstname,edtlastname,edtgmail,edtusername,edtpassword;
    Button btncreateaccount,btnlogin;
    RadioGroup radioGroup;
    RadioButton raddioprovider , radiorequester ;

    String selectedOption="Provider";


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup_activity);
        edtfirstname = findViewById(R.id.singup_firstname_edittext);
        edtlastname = findViewById(R.id.singup_lastname_edittext);
        edtgmail = findViewById(R.id.signup_gmail_edittext);
        edtusername = findViewById(R.id.signup_username_edittext);
        edtpassword = findViewById(R.id.singup_passowrd_edittext);
        btncreateaccount = findViewById(R.id.signup_createaccount_button);
        radioGroup = findViewById(R.id.radioGroup);
        raddioprovider = findViewById(R.id.radioProvider);
        radiorequester = findViewById(R.id.radioRequester);



        btncreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

if(raddioprovider.isChecked()){
    selectedOption = "Provider";



}else{
    selectedOption = "Requester";


}

                String gmail = edtgmail.getText().toString().trim();
                String password = edtpassword.getText().toString().trim();

                Signup(gmail,password);



            }
        });





    }





    public void Signup(String gmail, String password){
        mAuth.createUserWithEmailAndPassword(gmail, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    String userId = mAuth.getCurrentUser().getUid();
                    Toast.makeText(SingUp_Activity.this, "User Created"+userId, Toast.LENGTH_SHORT).show();

                    String strfirstname = edtfirstname.getText().toString();
                    String strlastname = edtlastname.getText().toString();
                    String struser = edtusername.getText().toString();
                    String strgmail = edtgmail.getText().toString();
                   // String strradiobuttons = selectedOption.toString().toString();

                    Person person = new Person();
                    person.setFirst_Name(strfirstname);
                    person.setLast_Name(strlastname);
                    person.setUser_Name(struser);
                    person.setGmail(strgmail);

                  //  System.out.println("gmail,,,,,,,,,,,,,,.................."+strgmail);
                    person.setRadioButtons(selectedOption);

                    DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();
                    mdatabase.child("User").child(userId).setValue(person);


                    if(selectedOption.equals("Provider")){
                        Intent i = new Intent(SingUp_Activity.this,Home_screen_provider.class);
                        startActivity(i);
                        finish();
                    }else{
                        Intent i = new Intent(SingUp_Activity.this,Home_screen_requister.class);
                        startActivity(i);
                        finish();

                    }
//                    Intent i = new Intent(SingUp_Activity.this,Create_profile_customer_provider.class);
//                    startActivity(i);
//                    finish();
//                    Intent i = new Intent(SingUp_Activity.this,HomeScreen_Activity.class);
//                    startActivity(i);
//                    finish();
                }else {
                    Log.e("TAG","Signup failed",task.getException());
                    Toast.makeText(SingUp_Activity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }






}















