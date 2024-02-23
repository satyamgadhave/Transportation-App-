package com.plutecoder.transpotation_main_project;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Setter_Getter.Add_Vehicle_Setter_Getter;
import Setter_Getter.Request_vehicle_Setter_Getter;


public class ThirdFragment_Requister extends Fragment {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    EditText  vcompanyname , vmodel , vfueltype , vusedfor , vcapacity , vdestinysStart , vdestinyend , vvisitperiod , vcontact ;
    Button btnRequestvehicle;


    RadioGroup radioGroup;
    RadioButton raddioprovider , radiorequester ;

    String selectedOption="AC";

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third__requister, container, false);
        vcompanyname = view.findViewById(R.id.VCompanyName_req);
        vmodel = view.findViewById(R.id.VModel_req);
        vfueltype = view.findViewById(R.id.VFuleType_req);
        vusedfor = view.findViewById(R.id.VUsedFor_req);
        vcapacity = view.findViewById(R.id.VCapacity_req);
        vdestinysStart = view.findViewById(R.id.Vdestinystart_req);
        vdestinyend = view.findViewById(R.id.Vdestinyend_req);
        vvisitperiod = view.findViewById(R.id.Vstay_req);
        vcontact = view.findViewById(R.id.Vcontact_req);
        btnRequestvehicle = view.findViewById(R.id.btnreq_button);
        radioGroup = view.findViewById(R.id.radioGroup);
        raddioprovider = view.findViewById(R.id.radioProvider);
        radiorequester = view.findViewById(R.id.radioRequester);


        btnRequestvehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(raddioprovider.isChecked()){
                    selectedOption = "AC";



                }else{
                    selectedOption = "NonAC";


                }

                addVehicle();
                Toast.makeText(getActivity(), "Request Sended Successfully", Toast.LENGTH_SHORT).show();

            }
        });



        return view;
    }





    public void  addVehicle(){
        Request_vehicle_Setter_Getter addVehicleSetterGetter = new Request_vehicle_Setter_Getter();

        String userId = mAuth.getCurrentUser().getUid();

        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();

      //  String strvnumber = vnumber.getText().toString();
        String strvcompany = vcompanyname.getText().toString();
        String strvmodel = vmodel.getText().toString();
        String strvfueltype = vfueltype.getText().toString();
        String strvusedfor = vusedfor.getText().toString();
        String strvcapacity = vcapacity.getText().toString();
        String strdestinysatart = vdestinysStart.getText().toString();
        String strdestinyend = vdestinyend.getText().toString();
        String strvisitperiod = vvisitperiod.getText().toString();
        String strcontact = vcontact.getText().toString();
       // String vuid = mdatabase.push().getKey();

     //   addVehicleSetterGetter.setVeh_No(strvnumber);
        addVehicleSetterGetter.setVeh_Company_name(strvcompany);
        addVehicleSetterGetter.setVeh_Model(strvmodel);
        addVehicleSetterGetter.setVeh_Fuel_Type(strvfueltype);
        addVehicleSetterGetter.setVeh_Used_for(strvusedfor);
        addVehicleSetterGetter.setVeh_capacity(strvcapacity);
        addVehicleSetterGetter.setVeh_destinysStart(strdestinysatart);
        addVehicleSetterGetter.setVeh_destinysend(strdestinyend);
        addVehicleSetterGetter.setVeh_visitperiod(strvisitperiod);
        addVehicleSetterGetter.setVeh_contact(strcontact);
        addVehicleSetterGetter.setRadioButtons(selectedOption);
    //    addVehicleSetterGetter.setUid(vuid);


        mdatabase.child("Request Vehicle").child(userId).setValue(addVehicleSetterGetter);


    }




}