package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Provider_Profile_ShowAdded_Vehicles extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter myAdapter;
    ArrayList<Showadded_retrive_data> list;

    ArrayList<Showadded_retrive_data> requestlist = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_profile_showadded_vehicles);

        recyclerView = findViewById(R.id.recyclerview_showdata);
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        list = new ArrayList<>();


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String uid=mAuth.getUid();

                getdata("Vehicle/"+uid);
//
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                    Showadded_retrive_data showadded_retrive_data = dataSnapshot.getValue(Showadded_retrive_data.class);
//                    list.add(showadded_retrive_data);
//                }
//
//                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



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

                    for (DataSnapshot alert : dataSnapshot.getChildren()) {
                        System.out.println("is key key----------------" + alert.getKey());

                        HashMap<String, Object> yourData = (HashMap<String, Object>) alert.getValue();

//                        ShareItem sendSportReq = new ShareItem();
//                        sendSportReq.setItemName("" + yourData.get("itemname"));
//                        requestlist.add(sendSportReq);

                        Showadded_retrive_data showadded_retrive_data = new Showadded_retrive_data();
                        showadded_retrive_data.setVehiclenumber_retriv("" + yourData.get("veh_No"));
                        showadded_retrive_data.setCompanyname_retriv("" + yourData.get("veh_Company_name"));
                        showadded_retrive_data.setUsedfor_retriv("" + yourData.get("veh_Company_name"));
                        showadded_retrive_data.setFuletype_retriv("" + yourData.get("veh_Fuel_Type"));

                        requestlist.add(showadded_retrive_data);

                    }

                    myAdapter = new MyAdapter(Provider_Profile_ShowAdded_Vehicles.this,requestlist);
                    recyclerView.setAdapter(myAdapter);


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
