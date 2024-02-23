package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import Setter_Getter.Showadded_retrive_data_on_Requester_firstfragmentrequester;


public class FirstFragment_Requister extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter_show_pro_veh_on_req_firastfragmentRequester myAdapter;
    ArrayList<Showadded_retrive_data_on_Requester_firstfragmentrequester> list;

    ArrayList<Showadded_retrive_data_on_Requester_firstfragmentrequester> requestlist ;


    private EditText etStartingDestinationFilter;
    private Button btnApplyFilter;

    private Animation_Button_Req_firstfragment animationButton;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_first__requister, container, false);
        etStartingDestinationFilter = rootView.findViewById(R.id.etStartingDestinationFilter);
        btnApplyFilter = rootView.findViewById(R.id.btnApplyFilter);
        animationButton = rootView.findViewById(R.id.animationButton);

        btnApplyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyFilter();
            }
        });

        if (animationButton != null) {
            animationButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    animationButton.playAnimation();
                }
            });
        } else {
            Log.e("MyFragment", "animationButton is null");
        }
        recyclerView = rootView.findViewById(R.id.recyclerview_requester_showdata_firstfragment);


        recyclerView = rootView.findViewById(R.id.recyclerview_requester_showdata_firstfragment);
        databaseReference = FirebaseDatabase.getInstance().getReference("Provider Vehicle");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();



        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String uid = mAuth.getCurrentUser().getUid();

                getdata("Provider Vehicle");

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

                            for(DataSnapshot child: alert.getChildren()) {
                                System.out.println("is key key----------------" + child.getKey());


                                HashMap<String, Object> yourData = (HashMap<String, Object>) child.getValue();

                                if (!yourData.get("radioButtons").equals("luggage transport"))  {


                                    Showadded_retrive_data_on_Requester_firstfragmentrequester showadded_retrive_data_on_requester_firstfragmentrequester = new Showadded_retrive_data_on_Requester_firstfragmentrequester();
                                    showadded_retrive_data_on_requester_firstfragmentrequester.setCarServicereq_retrive("" + yourData.get("veh_Used_for"));
                                    showadded_retrive_data_on_requester_firstfragmentrequester.setStartreq_retrive(" " + yourData.get("veh_start"));
                                    showadded_retrive_data_on_requester_firstfragmentrequester.setEndreq_retrive(" " + yourData.get("veh_end"));
                                    showadded_retrive_data_on_requester_firstfragmentrequester.setCapacityreq_retrive(" " + yourData.get("veh_Capacity"));
                                    showadded_retrive_data_on_requester_firstfragmentrequester.setComapanyModelreq_retrive(" " + yourData.get("veh_Company_name") + " - " + yourData.get("veh_Model"));
                                    showadded_retrive_data_on_requester_firstfragmentrequester.setPerkmacreq_retrive(" " + yourData.get("veh_priceperkm"));
                                    showadded_retrive_data_on_requester_firstfragmentrequester.setPerkmnonacreq_retrive(" " + yourData.get("veh_non_ac"));
                                    showadded_retrive_data_on_requester_firstfragmentrequester.setPetrolreq_retrive("" + yourData.get("veh_Fuel_Type"));
                                    showadded_retrive_data_on_requester_firstfragmentrequester.setVehicleNumberreq_retrive("" + yourData.get("veh_No"));


                                    showadded_retrive_data_on_requester_firstfragmentrequester.setUidreq(alert.getKey().toString());
                                    //System.out.println("--------"+showadded_retrive_data_on_requester_firstfragmentrequester);

                                    requestlist.add(showadded_retrive_data_on_requester_firstfragmentrequester);

                                }
                            }

//
//                            showadded_retrive_data.setUid(alert.getKey().toString());
//                            System.out.println("----"+showadded_retrive_data.getUsedfor_retriv());
//                            requestlist.add(showadded_retrive_data);

                        }

                        myAdapter = new MyAdapter_show_pro_veh_on_req_firastfragmentRequester(getContext(), requestlist);
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



    private void applyFilter() {
        String filterText = etStartingDestinationFilter.getText().toString().trim().toLowerCase();
        ArrayList<Showadded_retrive_data_on_Requester_firstfragmentrequester> filteredList = new ArrayList<>();

        //for (Showadded_retrive_data_on_Requester_firstfragmentrequester item : requestlist) {
            // Adjust the condition to match your filter criteria
            //if (item.getStartreq_retrive().toLowerCase().contains(filterText)) {
           //     filteredList.add(item);
           // }
       // }
        for (Showadded_retrive_data_on_Requester_firstfragmentrequester item : requestlist) {
            // Adjust the condition to match your filter criteria
            if (!item.getCarServicereq_retrive().toLowerCase().contains("luggage") && // Exclude vehicles used for luggage
                    item.getStartreq_retrive().toLowerCase().contains(filterText)) {
                filteredList.add(item);
            }
        }


        // Update the RecyclerView with the filtered data
        myAdapter = new MyAdapter_show_pro_veh_on_req_firastfragmentRequester(getContext(), filteredList);
        recyclerView.setAdapter(myAdapter);
    }




}