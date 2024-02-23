package com.plutecoder.transpotation_main_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SplashScreen_Activity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_activity);


        // to display screen particular time period  here used-->
        new Handler().postDelayed(()->{

            check();
//            startActivity(new Intent(SplashScreen_Activity.this,LoginScreen_Activity.class));
//            finish();

        },3000);



    }

    public  void check(){
        if(mAuth.getCurrentUser()!=null){
            String uid = mAuth.getCurrentUser().getUid();

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    HashMap<String,Object> data= (HashMap<String, Object>) snapshot.getValue();

                    String usertype=    data.get("radioButtons").toString();
                    System.out.println("usaer type-------"+usertype);

                    if (usertype.equals("Provider")){
                        Intent i = new Intent(SplashScreen_Activity.this,Home_screen_provider.class);
                        startActivity(i);
                        finish();
                    }
                    else if (usertype.equals("Requester")){
                        Intent i = new Intent(SplashScreen_Activity.this,Home_screen_requister.class);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(SplashScreen_Activity.this, "Authentication Failed provider and customer not go home page", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            Intent i = new Intent(SplashScreen_Activity.this,LoginScreen_Activity.class);
            startActivity(i);
            finish();
        }

    }



//    public void check() {
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//
//        if (currentUser != null) {
//            String uid = currentUser.getUid();
//
//            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
//            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    // Your existing code to handle the user's data
//
//                    HashMap<String,Object> data= (HashMap<String, Object>) snapshot.getValue();
//
//                    String usertype = data.get("radioButtons").toString();
//                    System.out.println("usaer type-------" + usertype);
//
//                    if (usertype.equals("Provider")) {
//                        Intent i = new Intent(SplashScreen_Activity.this, Home_screen_provider.class);
//                        startActivity(i);
//                        finish();
//                    } else if (usertype.equals("Requester")) {
//                        Intent i = new Intent(SplashScreen_Activity.this, Home_screen_requister.class);
//                        startActivity(i);
//                        finish();
//                    } else {
//                        Toast.makeText(SplashScreen_Activity.this, "Authentication Failed provider and customer not go home page", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    // Handle the database error
//                }
//            });
//        } else {
//            // There is no signed-in user, handle this case (e.g., show login screen)
//            Intent i = new Intent(SplashScreen_Activity.this, LoginScreen_Activity.class);
//            startActivity(i);
//            finish();
//        }
//    }


}
