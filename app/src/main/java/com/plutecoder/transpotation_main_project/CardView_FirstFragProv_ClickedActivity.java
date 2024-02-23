package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CardView_FirstFragProv_ClickedActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TextView vcompanyname , vmodel , vfueltype , vusedfor , vcapacity , vdestinysStart , vdestinyend , vvisitperiod , vcontact , vcustomer_want ;
    Button btnRequestvehicle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardview_firstfragprov_clickedactivity);

        vcompanyname = findViewById(R.id.VCompanyName_req);
        vmodel = findViewById(R.id.VModel_req);
        vfueltype = findViewById(R.id.VFuleType_req);
        vusedfor = findViewById(R.id.VUsedFor_req);
        vcapacity = findViewById(R.id.VCapacity_req);
        vdestinysStart = findViewById(R.id.Vdestinystart_req);
        vdestinyend = findViewById(R.id.Vdestinyend_req);
        vvisitperiod = findViewById(R.id.Vstay_req);
        vcontact = findViewById(R.id.Vcontact_req);
        vcustomer_want = findViewById(R.id.VACNonAc_req);
        btnRequestvehicle =findViewById(R.id.btnconrid_button);

        Intent intent = getIntent();
        if (intent.hasExtra("RID")) {
            String itemId = intent.getStringExtra("RID");
            // Now you have the unique ID of the item to retrieve data from Firebase
            // Use this ID to fetch the specific data for that item from Firebase
            getdata("Request Vehicle/" + itemId);
        } else {
            // Handle the case where the unique ID is not present in the Intent
        }
//        mAuth = FirebaseAuth.getInstance();
//
//        String userId = mAuth.getCurrentUser().getUid();
//        getdata("Request Vehicle/"+userId);


        btnRequestvehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CardView_FirstFragProv_ClickedActivity.this, "btnRequestvehicle clicked", Toast.LENGTH_SHORT).show();
            }
        });



    }






    public void getdata(String url) {

       // String url = "Request Vehicle/" + userId;
        System.out.println("-------------" + url);

        try {

            //
            FirebaseDatabase mFirebaseInstance = FirebaseDatabase.getInstance();
            DatabaseReference mFirebaseDatabase = mFirebaseInstance.getReference(url);
            mFirebaseDatabase.keepSynced(true);
            mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("CardView_FirstFragProv_ClickedActivity is req----------------" + dataSnapshot.toString());


                    HashMap<String, Object> yourData = (HashMap<String, Object>) dataSnapshot.getValue();

                  String strvcompanyname = yourData.get("veh_Company_name").toString();
                  vcompanyname.setText(strvcompanyname);

                  String strvmodel = yourData.get("veh_Model").toString();
                  vmodel.setText(strvmodel);

                  String strfuletype = yourData.get("veh_Fuel_Type").toString();
                  vfueltype.setText(strfuletype);

                  String strusedfor = yourData.get("veh_Used_for").toString();
                  vusedfor.setText(strusedfor);

                  String strcapacity = yourData.get("veh_contact").toString();
                  vcapacity.setText(strcapacity);

                  String strdestinysatart = yourData.get("veh_destinysStart").toString();
                  vdestinysStart.setText(strdestinysatart);

                  String strdestinyend = yourData.get("veh_destinysend").toString();
                  vdestinyend.setText(strdestinyend);

                  String strvisitperiod = yourData.get("veh_visitperiod").toString();
                  vvisitperiod.setText(strvisitperiod);

                  String strcontact = yourData.get("veh_contact").toString();
                  vcontact.setText(strcontact);

                  String strcustomerwant = yourData.get("radioButtons").toString();
                  vcustomer_want.setText(strcustomerwant);









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
