package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;

public class LoginScreen_Activity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    EditText edtgmail,edtpassword;
    Button btnlogin,btncreateaccount;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen_activity);
        edtgmail = findViewById(R.id.login_gmail_edittext);
        edtpassword = findViewById(R.id.login_password_edittext);
        btnlogin = findViewById(R.id.login_login_button);
        btncreateaccount = findViewById(R.id.login_createaccount_button);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String email = edtgmail.getText().toString().trim();
                String password = edtpassword.getText().toString().trim();

                LoginInWithEmail(email,password);


            }
        });

        btncreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginScreen_Activity.this,SingUp_Activity.class);
                startActivity(i);
            }
        });

    }


    public void LoginInWithEmail(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String uid = mAuth.getCurrentUser().getUid();
                    Toast.makeText(LoginScreen_Activity.this, "Login Successfully"+uid, Toast.LENGTH_SHORT).show();

                    check();
                    // to check the user is provider or requester then go to according to their HomeScreen
                    // For User "authentic" or "login Successfully" then go to new screen i.e Create_profile_customer_provider










                }else {
                    Toast.makeText(LoginScreen_Activity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public  void check(){
        String uid = mAuth.getCurrentUser().getUid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HashMap<String,Object> data= (HashMap<String, Object>) snapshot.getValue();

            String usertype=    data.get("radioButtons").toString();
            System.out.println("usaer type-------"+usertype);

                if (usertype.equals("Provider")){
                    Intent i = new Intent(LoginScreen_Activity.this,Home_screen_provider.class);
                    startActivity(i);
                    finish();
                }
                else if (usertype.equals("Requester")){
                    Intent i = new Intent(LoginScreen_Activity.this,Home_screen_requister.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(LoginScreen_Activity.this, "Authentication Failed provider and customer not go home page", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
