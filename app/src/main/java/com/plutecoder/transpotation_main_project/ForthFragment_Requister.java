package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class ForthFragment_Requister extends Fragment {

    private FirebaseAuth mAuth;

    TextView fullname,gmail,username,signouttext;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_forth__requister, container, false);
        signouttext = rootView.findViewById(R.id.fragmentfourth_requester_textsignout);
        fullname = rootView.findViewById(R.id.fragmentfourth_requester_textfullName);
        gmail = rootView.findViewById(R.id.fragmentfourth_requester_textgmail);
        username = rootView.findViewById(R.id.fragmentfourth_requester_textusername);

        mAuth = FirebaseAuth.getInstance();




        signouttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),LoginScreen_Activity.class);
                startActivity(i);
                getActivity().finish();

            }
        });


        String userId = mAuth.getCurrentUser().getUid();
        getdata("User/"+userId);

        return rootView;
    }


    public void getdata(String url) {
        System.out.println("-------------" + url);

        try {

            //
            FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
            DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference(url);
            mFirebaseDatabase.keepSynced(true);
            mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("is req----------------" + dataSnapshot.toString());


                    HashMap<String, Object> yourData = (HashMap<String, Object>) dataSnapshot.getValue();

                    String fname=yourData.get("first_Name").toString();
                    String lname=yourData.get("last_Name").toString();
                    fullname.setText(fname+lname);


                    String strgmail=yourData.get("gmail").toString();
                    gmail.setText(strgmail);


                    String userName=yourData.get("user_Name").toString();
                    username.setText(userName);


                }

                @Override
                public void onCancelled(DatabaseError error) {


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}