package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class FirstFragment_Provider extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter_show_req_veh_on_pro_firastfragment myAdapter;
    ArrayList<Showadded_retrive_data> list;

    ArrayList<Showadded_retrive_data> requestlist;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerview_showdata_firstfragment);
        databaseReference = FirebaseDatabase.getInstance().getReference("Request Vehicle");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        list = new ArrayList<>();


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String uid = mAuth.getCurrentUser().getUid();

                getdata("Request Vehicle");
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



        return rootView;

    }







    public void getdata(String url) {
        System.out.println("-------------" + url);

        try {
            requestlist = new ArrayList<>();
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
                        showadded_retrive_data.setVehiclenumber_retriv("" + yourData.get("veh_Company_name"));
                        showadded_retrive_data.setCompanyname_retriv("" + yourData.get("veh_destinysStart"));
                        showadded_retrive_data.setUsedfor_retriv("" + yourData.get("veh_destinysend"));
                        showadded_retrive_data.setFuletype_retriv("" + yourData.get("veh_capacity"));
                        showadded_retrive_data.setServices_useed_retriv("" + yourData.get("veh_Used_for"));
                        showadded_retrive_data.setUid(alert.getKey().toString());
System.out.println("----"+showadded_retrive_data.getUsedfor_retriv());
                        requestlist.add(showadded_retrive_data);

                    }

                    myAdapter = new MyAdapter_show_req_veh_on_pro_firastfragment(getContext(), requestlist);
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