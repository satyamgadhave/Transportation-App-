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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.HashMap;


public class FourthFragment_Provider extends Fragment {

    private FirebaseAuth mAuth;
   // private FirebaseFirestore db;
    TextView fullname,gmail,username,signouttext ,vehileShow;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fourth, container, false);
        mAuth = FirebaseAuth.getInstance();
      //  db = FirebaseFirestore.getInstance();

        // Find the TextView in the layout by its ID
        signouttext = rootView.findViewById(R.id.SignOut_text_Profil_Provide);
        vehileShow = rootView.findViewById(R.id.VechicleYourThatadd_profilpage);
        gmail = rootView.findViewById(R.id.fragmentfourth_profile_textgmail);
        username = rootView.findViewById(R.id.fragmentfourth_profile_textusername);
        fullname = rootView.findViewById(R.id.fragmentfourth_profile_textfullName);






    signouttext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getActivity(),LoginScreen_Activity.class);
            startActivity(i);
            getActivity().finish();

        }
    });

    vehileShow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getActivity(),Provider_Profile_ShowAdded_Vehicles.class);
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